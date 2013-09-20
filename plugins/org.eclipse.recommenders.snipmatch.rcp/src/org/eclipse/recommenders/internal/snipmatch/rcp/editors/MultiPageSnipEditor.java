/*******************************************************************************
 * Copyright (c) 2013 Stefan.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Stefan - initial API and implementation
 ******************************************************************************/
package org.eclipse.recommenders.internal.snipmatch.rcp.editors;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.recommenders.templates.typing.SnipDSLTypeComputer;
import org.eclipse.recommenders.snipmatch.Snippet;
import org.eclipse.recommenders.utils.gson.GsonUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.*;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.ide.IDE;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.xbase.typesystem.references.ITypeReferenceOwner;

import com.google.inject.Inject;

/**
 * An example showing how to create a multi-page editor. This example has 3
 * pages:
 * <ul>
 * <li>page 0 contains a nested text editor.
 * <li>page 1 allows you to change the font used in page 2
 * <li>page 2 shows the words in page 0 in sorted order
 * </ul>
 */
public class MultiPageSnipEditor extends FormEditor implements
        IResourceChangeListener {

    @Inject
    XtextEditor snipEditor;

    @Inject
    SnipDSLTypeComputer computer;

    /** The text editor used in page 0. */
    private TextEditor editor;
    private ITypeReferenceOwner typeReferences;

    private IPropertyListener propertyChangeListener = new IPropertyListener() {

        @Override
        public void propertyChanged(Object source, int propId) {
            // TODO Auto-generated method stub
            if (propId == PROP_DIRTY) {

                System.out.println("Got a property change");

            }
        }
    };

    /**
     * Creates a multi-page editor example.
     */
    public MultiPageSnipEditor() {

        super();
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
        // this.addPropertyListener(propertyChangeListener);
    }

    /**
     * Creates page 0 of the multi-page editor, which contains a text editor.
     */
    void createEditorPage() {
        try {

            editor = snipEditor;
            // editor.setPartProperty("Name", "Snipeditor");
            // System.out.println(editor.getPartName() +"   "+
            // getEditorInput().getName());
            int index = addPage(editor, getEditorInput());

            setPageText(index, getEditorInput().getName());
        } catch (PartInitException e) {
            ErrorDialog.openError(getSite().getShell(),
                    "Error creating nested text editor", null, e.getStatus());
        }
    }

    /**
     * The <code>MultiPageEditorPart</code> implementation of this
     * <code>IWorkbenchPart</code> method disposes all nested editors.
     * Subclasses may extend.
     */
    public void dispose() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
        // this.removePropertyListener(propertyChangeListener);
        super.dispose();
    }

    /**
     * Saves the multi-page editor's document.
     */
    public void doSave(IProgressMonitor monitor) {
        getEditor(0).doSave(monitor);

        IEditorInput editInp = getEditor(0).getEditorInput();

        if (editInp instanceof SnipEditorFileInput) {
            SnipEditorFileInput fEditInp = (SnipEditorFileInput) editInp;
            try {
                byte[] contents = new byte[fEditInp.getFile().getContents()
                        .available()];
                fEditInp.getFile().getContents()
                        .read(contents, 0, contents.length);
                if (pages.get(1) instanceof MetadataPage) {
                    MetadataPage page2 = ((MetadataPage) pages.get(1));
                    File snippetFile = new File(fEditInp.getExternalPath());

                    Snippet temp = ((SnipEditorFileInput) page2
                            .getEditorInput()).getSnippet();

                    temp.setName(page2.getSnippetName());
                    temp.setCode(new String(contents));
                    temp.setAliases(page2.getAliases());
                    System.out.println(temp.getName() + " / "
                            + page2.getSnippetName());
                    GsonUtil.serialize(temp, snippetFile);
                    page2.setDirty(false);
                }

            } catch (IOException e) { // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (CoreException e) {

            }
        }
        typeReferences = computer.getReferenceOwner();

    }

    /**
     * Saves the multi-page editor's document as another file. Also updates the
     * text for page 0's tab, and updates this multi-page editor's input to
     * correspond to the nested editor's.
     */
    public void doSaveAs() {
        IEditorPart editor = getEditor(0);
        editor.doSaveAs();
        setPageText(0, editor.getTitle());
        setInput(editor.getEditorInput());
    }

    /*
     * (non-Javadoc) Method declared on IEditorPart
     */
    public void gotoMarker(IMarker marker) {
        setActivePage(0);
        IDE.gotoMarker(getEditor(0), marker);

    }

    /**
     * The <code>MultiPageEditorExample</code> implementation of this method
     * checks that the input is an instance of <code>IFileEditorInput</code>.
     */
    public void init(IEditorSite site, IEditorInput editorInput)
            throws PartInitException {
        if (!(editorInput instanceof IFileEditorInput)
                && !(editorInput instanceof IStorageEditorInput))
            throw new PartInitException(
                    "Invalid Input: Must be IFileEditorInput/IStorageEditorInput");

        // snipEditor.init(site, editorInput);
        if (editorInput instanceof FileEditorInput) {
            editorInput = new SnipEditorFileInput(
                    ((FileEditorInput) editorInput).getFile());
        }

        // System.out.println(site);
        super.init(site, editorInput);

    }

    /*
     * (non-Javadoc) Method declared on IEditorPart.
     */
    public boolean isSaveAsAllowed() {
        return true;
    }

    /**
     * Closes all project files on project close.
     */
    public void resourceChanged(final IResourceChangeEvent event) {
        if (event.getType() == IResourceChangeEvent.PRE_CLOSE) {
            Display.getDefault().asyncExec(new Runnable() {
                public void run() {
                    IWorkbenchPage[] pages = getSite().getWorkbenchWindow()
                            .getPages();
                    for (int i = 0; i < pages.length; i++) {
                        if (((FileEditorInput) editor.getEditorInput())
                                .getFile().getProject()
                                .equals(event.getResource())) {
                            IEditorPart editorPart = pages[i].findEditor(editor
                                    .getEditorInput());
                            pages[i].closeEditor(editorPart, true);
                        }
                    }
                }
            });
        }
    }

    @Override
    protected void addPages() {
        // TODO Auto-generated method stub
        createEditorPage();
        try {
            addPage(new MetadataPage(this, "metaPg", "Metadata"));
        } catch (PartInitException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ITypeReferenceOwner getTypeReference() {
        typeReferences = computer.getReferenceOwner();
        return typeReferences;
    }
}
