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
/**
 * 
 */
package org.eclipse.recommenders.internal.snipmatch.rcp.editors;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.xbase.typesystem.references.ITypeReferenceOwner;

/**
 * @author Stefan
 * 
 */
public class MetadataPage extends FormPage {

    private boolean dirty = false;
    private String snippetName = "";
    private ArrayList<String> aliases = new ArrayList<String>();
    private ArrayList<String> types = new ArrayList<String>();
    private final MetadataPage instance;
    private String summary;

    /**
     * @param id
     * @param title
     */
    public MetadataPage(String id, String title) {
        super(id, title);
        instance = this;
        // TODO Auto-generated constructor stub
    }

    /**
     * @param editor
     * @param id
     * @param title
     */
    public MetadataPage(FormEditor editor, String id, String title) {
        super(editor, id, title);
        instance = this;
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("unchecked")
    protected void createFormContent(IManagedForm managedForm) {
        FormToolkit toolkit = managedForm.getToolkit();
        ScrolledForm form = managedForm.getForm();
        form.setText("Metadata Page");
        final Composite body = form.getBody();
        toolkit.decorateFormHeading(form.getForm());
        toolkit.paintBordersFor(body);
        body.setLayout(new GridLayout(4, false));

        SnipEditorFileInput editorInp;
        if (getEditorInput() instanceof SnipEditorFileInput) {
            editorInp = (SnipEditorFileInput) getEditorInput();
            Label snipetNameLbl = toolkit.createLabel(body, "NameLbl");
            snipetNameLbl.setText("Snippet Name:");
            final Text snippetNameTxt = toolkit.createText(body, "NameTxt",
                    SWT.BORDER);
            snippetNameTxt.setText(editorInp.getName());

            GridData data = new GridData(GridData.FILL_HORIZONTAL);
            data.horizontalSpan = 2;
            Button addTypes = toolkit.createButton(body, "AddTypes", SWT.PUSH);
            addTypes.setText("Check for types!");

            addTypes.setLayoutData(data);

            Label summary = toolkit.createLabel(body, "summaryLbl");
            summary.setText("Description: ");
            data = new GridData(GridData.BEGINNING);

            data.widthHint = 300;
            data.heightHint = 50;
            final Text summaryTxt = toolkit.createText(body, "summaryTxt",
                    SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
            summaryTxt.setText(editorInp.getContents(
                    SnipEditorFileInput.VAR_DESCRIPTION).toString());
            summaryTxt.setLayoutData(data);

            ListViewer typesListView = new ListViewer(body, SWT.BORDER
                    | SWT.SHADOW_IN | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);

            final List typesList = typesListView.getList();

            data = new GridData(GridData.FILL_BOTH);
            data.heightHint = 100;
            data.widthHint = 300;
            data.verticalSpan = 10;
            data.horizontalSpan = 2;
            typesList.setLayoutData(data);

            Label patterns = toolkit.createLabel(body, "patternsLbl",
                    SWT.BEGINNING);
            patterns.setText("Search Phrases:");

            data = new GridData(GridData.BEGINNING);
            data.heightHint = 100;
            data.widthHint = 300;
            data.verticalSpan = 4;

            final ListViewer aliasesListView = new ListViewer(body, SWT.BORDER
                    | SWT.SHADOW_IN | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);

            final List aliasesList = aliasesListView.getList();

            aliasesList.setLayoutData(data);

            java.util.List<String> aliasesVal = (java.util.List<String>) editorInp
                    .getContents(SnipEditorFileInput.VAR_ALIASES);

            for (String p : aliasesVal) {
                aliasesList.add(p);

            }

            data = new GridData(GridData.CENTER);
            data.widthHint = 100;
            final Button addNewPatern = toolkit.createButton(body,
                    "AddNewPattern", SWT.PUSH);
            addNewPatern.setText("Add New");
            addNewPatern.setLayoutData(data);
            final Button editPatern = toolkit.createButton(body, "editPattern",
                    SWT.PUSH);
            editPatern.setText("Edit");
            editPatern.setLayoutData(data);
            editPatern.setEnabled(false);
            final Button removePattern = toolkit.createButton(body,
                    "removePattern", SWT.PUSH);
            removePattern.setText("Remove");
            removePattern.setLayoutData(data);
            removePattern.setEnabled(false);
            aliasesList.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    removePattern.setEnabled(true);
                    editPatern.setEnabled(true);
                }
            });
            summaryTxt.addKeyListener(new KeyListener() {

                @Override
                public void keyReleased(KeyEvent e) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    // TODO Auto-generated method stub
                    setSummary(summaryTxt.getText());
                }
            });
            snippetNameTxt.addKeyListener(new KeyListener() {

                @Override
                public void keyReleased(KeyEvent e) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    // TODO Auto-generated method stub
                    setSnippetName(snippetNameTxt.getText());
                }
            });

            addNewPatern.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {

                    Point position = new Point(addNewPatern.getLocation().x
                            + addNewPatern.getBounds().width, addNewPatern
                            .getLocation().y);
                    new MetaFieldEditor(instance, body, SWT.None, aliasesList,
                            body.toDisplay(position));

                }
            });

            editPatern.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {

                    Point position = new Point(editPatern.getLocation().x
                            + editPatern.getBounds().width, editPatern
                            .getLocation().y);
                    new MetaFieldEditor(instance, body, SWT.None, aliasesList,
                            body.toDisplay(position), aliasesList
                                    .getSelectionIndex());
                }
            });
            removePattern.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    aliasesList.remove(aliasesList.getSelectionIndex());

                }
            });

            addTypes.addSelectionListener(new SelectionAdapter() {
                @SuppressWarnings("restriction")
                public void widgetSelected(SelectionEvent e) {
                    typesList.removeAll();
                    System.out.println("This widget has been selected!");
                    if (getEditor() instanceof MultiPageSnipEditor) {

                        ITypeReferenceOwner typeReferences = ((MultiPageSnipEditor) getEditor())
                                .getTypeReference();

                        for (Resource r : typeReferences
                                .getContextResourceSet().getResources()) {
                            for (EObject content : r.getContents()) {
                                if (content instanceof JvmType
                                        && !((JvmType) content)
                                                .getQualifiedName().contains(
                                                        "CodeSnippet_")) {
                                    // System.out.println(content);
                                    typesList.add(((JvmType) content)
                                            .getQualifiedName());
                                }
                            }

                        }
                    } else {

                        MessageDialog.openError(
                                body.getShell(),
                                "Wrong Editor",
                                "The on which this page opens must be an"
                                        + " instance of MultiPageSnipEditor.class");
                    }
                }
            });
        }
    }

    public void setDirty(boolean arg) {
        dirty = arg;
        getEditor().editorDirtyStateChanged();
    }

    @Override
    public boolean isDirty() {
        return dirty;
    }

    /**
     * @param snippetName
     *            the snippetName to set
     */
    public void setSnippetName(String snippetName) {
        if (getEditorInput() instanceof SnipEditorFileInput) {
            this.snippetName = snippetName;
            setDirty(true);
        }
    }

    public String getSnippetName() {
        return snippetName;
    }

    /**
     * @param patterns
     *            the patterns to set
     */
    public void setAliases(String[] aliases) {
        if (getEditorInput() instanceof SnipEditorFileInput) {
            this.aliases.clear();
            for (String alias : aliases) {
                System.out.println(alias);
                this.aliases.add(alias);
            }

            setDirty(true);
        }
    }

    public ArrayList<String> getAliases() {
        return aliases;
    }

    /**
     * @param types
     *            the types to set
     */
    public void setTypes(ArrayList<String> types) {
        this.types = types;

    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setSummary(String summary) {
        this.summary = summary;
        setDirty(true);
    }

    public String getSummary() {
        return summary;
    }

}
