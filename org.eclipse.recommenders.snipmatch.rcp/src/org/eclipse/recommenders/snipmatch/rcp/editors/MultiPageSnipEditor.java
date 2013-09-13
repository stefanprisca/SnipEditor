package org.eclipse.recommenders.snipmatch.rcp.editors;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.recommenders.templates.typing.SnipDSLTypeComputer;
import org.eclipse.recommenders.templates.rcp.internal.SnipDSLActivator;
import org.eclipse.recommenders.snipmatch.rcp.core.Snippet;
import org.eclipse.recommenders.snipmatch.rcp.preferences.PreferenceConstants;
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
public class MultiPageSnipEditor extends MultiPageEditorPart implements
        IResourceChangeListener {

    @Inject
    XtextEditor snipEditor;

    @Inject
    SnipDSLTypeComputer computer;
   
    
    
    /** The text editor used in page 0. */
    private TextEditor editor;
    private ITypeReferenceOwner typeReferences;
    
    private IPropertyListener propertyChangeListener=new IPropertyListener() {
        
        @Override
        public void propertyChanged(Object source, int propId) {
            // TODO Auto-generated method stub
            if(propId==PROP_DIRTY){
               
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
        //this.addPropertyListener(propertyChangeListener);
    }

    /**
     * Creates page 0 of the multi-page editor, which contains a text editor.
     */
    void createPage0() {
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

    void createPage1() {
        final Group pageContents = new Group(getContainer(), SWT.V_SCROLL
                | SWT.H_SCROLL);
        pageContents.setLayout(new GridLayout(4, false));
        pageContents.setText("METADATA");

        SnipEditorFileInput editorInp;
        if (getEditorInput() instanceof SnipEditorFileInput) {
            editorInp = (SnipEditorFileInput) getEditorInput();

            GridData data = new GridData(GridData.BEGINNING);

            Label snipName = new Label(pageContents, SWT.None);
            snipName.setText("Snippet Name: ");

            Text snipNameTxt = new Text(pageContents, SWT.BORDER
                    | SWT.BEGINNING);
            snipNameTxt.setText(editorInp.getName());
            // snipNameTxt.setLayoutData(data);

            data = new GridData(GridData.FILL_HORIZONTAL);
            data.horizontalSpan = 2;
            Button addTypes = new Button(pageContents, SWT.PUSH);
            addTypes.setText("Check for types!");

            addTypes.setLayoutData(data);

             
            Label summary = new Label(pageContents, SWT.None);
            summary.setText("Description: ");
            data = new GridData(GridData.BEGINNING);

            data.widthHint = 300;
            data.heightHint = 50;
            Text summaryTxt = new Text(pageContents, SWT.BORDER | SWT.V_SCROLL
                    | SWT.H_SCROLL | SWT.MULTI);
            summaryTxt.setText(editorInp.getContents(
                    SnipEditorFileInput.VAR_DESCRIPTION).toString());
            summaryTxt.setLayoutData(data);

            ListViewer typesListView = new ListViewer(pageContents, SWT.BORDER
                    | SWT.SHADOW_IN | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);

            final List typesList = typesListView.getList();
            
            
            data = new GridData(GridData.FILL_BOTH);
            data.heightHint = 100;
            data.widthHint = 300;
            data.verticalSpan = 10;
            data.horizontalSpan = 2;
            typesList.setLayoutData(data);

            Label patterns = new Label(pageContents, SWT.BEGINNING);
            patterns.setText("Search Phrases:");

            data = new GridData(GridData.BEGINNING);
            data.heightHint = 100;
            data.widthHint = 300;
            data.verticalSpan = 4;

            final ListViewer patternsListView = new ListViewer(pageContents,
                    SWT.BORDER | SWT.SHADOW_IN | SWT.V_SCROLL | SWT.H_SCROLL
                            | SWT.MULTI);

            final List patternsList = patternsListView.getList();
           
            patternsList.setLayoutData(data);

            java.util.List<String> patternsVal = (java.util.List<String>) editorInp
                    .getContents(SnipEditorFileInput.VAR_PATTERNS);

            for (String p : patternsVal) {
                patternsList.add(p);

            }

            data = new GridData(GridData.CENTER);
            data.widthHint = 100;
            final Button addNewPatern = new Button(pageContents, SWT.PUSH);
            addNewPatern.setText("Add New");
            addNewPatern.setLayoutData(data);
            final Button editPatern = new Button(pageContents, SWT.PUSH);
            editPatern.setText("Edit");
            editPatern.setLayoutData(data);
            editPatern.setEnabled(false);
            final Button removePattern = new Button(pageContents, SWT.PUSH);
            removePattern.setText("Remove");
            removePattern.setLayoutData(data);
            removePattern.setEnabled(false);
            
            patternsList.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    removePattern.setEnabled(true);
                    editPatern.setEnabled(true);               
                }
            });

            addNewPatern.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                  //  firePropertyChange(PROP_DIRTY);
                    Point position = new Point(addNewPatern.getLocation().x+addNewPatern.getBounds().width,
                            addNewPatern.getLocation().y );
                    new MetaFieldEditor(pageContents, SWT.None, patternsList,
                            pageContents.toDisplay(position));
                    firePropertyChange(PROP_DIRTY);
                }
            });
            editPatern.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                   // firePropertyChange(PROP_DIRTY);
                    Point position = new Point(editPatern.getLocation().x+editPatern.getBounds().width,
                            editPatern.getLocation().y );
                   new MetaFieldEditor(pageContents, SWT.None, patternsList,
                            pageContents.toDisplay(position), patternsList
                                  .getSelectionIndex());
                   
                   
                }
            });
            removePattern.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    patternsList.remove(patternsList.getSelectionIndex());
                   // firePropertyChange(PROP_DIRTY);

                }
            });

            addTypes.addSelectionListener(new SelectionAdapter() {
                @SuppressWarnings("restriction")
                public void widgetSelected(SelectionEvent e) {
                    typesList.removeAll();
                    if(typeReferences==null){
                        typeReferences = computer.getReferenceOwner();
                    }
                    for (Resource r : typeReferences.getContextResourceSet().getResources()) {
                        for (EObject content : r.getContents()) {
                            if (content instanceof JvmType
                                    &&! ((JvmType) content).getQualifiedName().contains("CodeSnippet_")) {
                                // System.out.println(content);
                                typesList.add(((JvmType) content)
                                        .getQualifiedName());
                            }
                        }

                    }

                }
            });

        }
        pageContents.pack();
        int index = addPage(pageContents);
        setPageText(index, "Metadata");
        
    }

    /**
     * Creates page 2 of the multi-page editor, which shows the sorted text.
     */
    void createPage2() {
    }

    /**
     * Creates the pages of the multi-page editor.
     */
    protected void createPages() {
        createPage0();
        createPage1();
        // createPage2();
    }

    /**
     * The <code>MultiPageEditorPart</code> implementation of this
     * <code>IWorkbenchPart</code> method disposes all nested editors.
     * Subclasses may extend.
     */
    public void dispose() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
      //  this.removePropertyListener(propertyChangeListener);
        super.dispose();
    }

    /**
     * Saves the multi-page editor's document.
     */
    public void doSave(IProgressMonitor monitor) {
        getEditor(0).doSave(monitor);

        System.out.println("Save initiated!");
        IEditorInput editInp = getEditor(0).getEditorInput();

        if (editInp instanceof IFileEditorInput) {
            IFileEditorInput fEditInp = (IFileEditorInput) editInp;
            try {
                byte[] contents = new byte[fEditInp.getFile().getContents()
                        .available()];
                fEditInp.getFile().getContents()
                        .read(contents, 0, contents.length);
                String toSnippets = SnipDSLActivator.getInstance()
                        .getPreferenceStore()
                        .getString(PreferenceConstants.SNIPPET_PATH);
                IPath pathToSnippetFile = new Path(toSnippets + '/'
                        + fEditInp.getName());
                pathToSnippetFile = pathToSnippetFile.removeFileExtension()
                        .addFileExtension("json");
                File json = pathToSnippetFile.toFile();
                System.out.println(json.getAbsolutePath());
                Snippet snippet = GsonUtil.deserialize(json, Snippet.class);
                snippet.setCode(new String(contents));
                
                GsonUtil.serialize(snippet, json);

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
}
