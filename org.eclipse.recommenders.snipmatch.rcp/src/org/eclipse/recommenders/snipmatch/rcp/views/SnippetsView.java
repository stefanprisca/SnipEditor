/**
 * Copyright (c) 2012 Cheng Chen
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *    Cheng Chen - initial API and implementation and/or initial documentation
 */

package org.eclipse.recommenders.snipmatch.rcp.views;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.recommenders.snipmatch.rcp.internal.SnipDSLActivator;
import org.eclipse.recommenders.snipmatch.rcp.core.LightweightSnippet;
import org.eclipse.recommenders.snipmatch.rcp.editors.SnipEditorFileInput;
import org.eclipse.recommenders.snipmatch.rcp.preferences.PreferenceConstants;
import org.eclipse.recommenders.utils.gson.GsonUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ViewPart;

import com.google.gson.reflect.TypeToken;

//import com.google.gson.reflect.TypeToken;

public class SnippetsView extends ViewPart {

    private ListViewer snippetsList;
    private Text searchText;
    private Button editButton;
    private Button deleteButton;

    private Map<String, LightweightSnippet> snippetsMap = new HashMap<String, LightweightSnippet>();

    // private SubmitBox editBox = null;

    @Override
    public void createPartControl(Composite parent) {
        FormLayout layout = new FormLayout();
        layout.marginHeight = 5;
        layout.marginWidth = 5;
        parent.setLayout(layout);

        Composite searchComposite = new Composite(parent, SWT.NONE);
        createSearchComposite(searchComposite);
        FormData searchFormData = new FormData();
        searchFormData.left = new FormAttachment(0, 5);
        searchFormData.right = new FormAttachment(100, -5);
        searchComposite.setLayoutData(searchFormData);

        Composite listComposite = new Composite(parent, SWT.NONE);
        FormData resultFormData = new FormData();
        resultFormData.left = new FormAttachment(0, 5);
        resultFormData.top = new FormAttachment(0, 35);
        resultFormData.right = new FormAttachment(100, -5);
        resultFormData.bottom = new FormAttachment(100, -5);
        listComposite.setLayoutData(resultFormData);
        createSnippetsListComposite(listComposite);

        loadSnippetsList();
        // editBox = new SubmitBox();
    }

    private void createSearchComposite(Composite parent) {
        FormLayout layout = new FormLayout();
        parent.setLayout(layout);

        searchText = new Text(parent, SWT.SINGLE | SWT.BORDER);
        FormData formData = new FormData();
        formData.top = new FormAttachment(0, 5);
        formData.left = new FormAttachment(0, 10);
        formData.bottom = new FormAttachment(100, -5);
        formData.right = new FormAttachment(100, -100);
        searchText.setLayoutData(formData);
        searchText.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.keyCode == SWT.CR) {
                    e.doit = false;
                    searchAction();
                }
            }

        });

        // button menu ->delete when you feel like doing so
        Composite menuComposite = new Composite(parent, SWT.NONE);
        FormData buttonFormData = new FormData();
        buttonFormData.width = 90;
        buttonFormData.top = new FormAttachment(0, 3);
        buttonFormData.right = new FormAttachment(100, -10);
        menuComposite.setLayoutData(buttonFormData);
        menuComposite.setLayout(new RowLayout(SWT.VERTICAL));
        final Button b1 = new Button(menuComposite, SWT.NONE);
        b1.setLayoutData(new RowData(80, 25));
        b1.setText("Search");
        b1.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent evt) {
                // searchAction();
            }
        });
    }

    private void searchAction() {
        if (!searchText.getText().trim().isEmpty()) {
            searchSnippets(searchText.getText().trim().toLowerCase());
            editButton.setEnabled(false);
            deleteButton.setEnabled(false);
        } else
            loadSnippetsList();
    }

    private void createSnippetsListComposite(final Composite parent) {
        parent.setLayout(new FormLayout());

        snippetsList = new ListViewer(parent, SWT.BORDER | SWT.H_SCROLL
                | SWT.V_SCROLL);
        FormData formData = new FormData();
        formData.top = new FormAttachment(0, 5);
        formData.left = new FormAttachment(0, 10);
        formData.right = new FormAttachment(100, -100);
        formData.bottom = new FormAttachment(100, -5);
        snippetsList.getList().setLayoutData(formData);

        snippetsList.addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(DoubleClickEvent event) {
                // TODO Auto-generated method stub

                LightweightSnippet map = snippetsMap.get(snippetsList.getList()
                        .getSelection()[0]);
                // System.out.println(map.filePath);

                File jsonFile = new File(map.filePath);
                if (jsonFile.exists()) {
                    IWorkbenchPage page = PlatformUI.getWorkbench()
                            .getActiveWorkbenchWindow().getActivePage();
                    // SnipEditorStorageInput snipInput=new
                    // SnipEditorStorageInput(jsonFile);
                    // new SnipEditorFileInput(jsonFile);

                    try {
                        // System.out.println("Double click");
                        // page.openEditor(snipInput, "MultiPageSnipEditor");
                        IFileEditorInput input = new FileEditorInput(
                                new SnipEditorFileInput(jsonFile).getFile());
                        page.openEditor(new SnipEditorFileInput(jsonFile),
                                "org.eclipse.recommenders.snipmatch.MultiPage");

                    } catch (PartInitException pie) {
                        throw new RuntimeException(pie);
                    }
                }

            }

        });

        Composite menuComposite = new Composite(parent, SWT.NONE);
        FormData buttonFormData = new FormData();
        buttonFormData.width = 90;
        buttonFormData.right = new FormAttachment(100, -10);
        menuComposite.setLayoutData(buttonFormData);
        menuComposite.setLayout(new RowLayout(SWT.VERTICAL));
        Button button1 = new Button(menuComposite, SWT.PUSH);
        button1.setLayoutData(new RowData(80, 25));
        button1.setText("Refresh");
        button1.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent evt) {
                // SnipDSLActivator.getInstance().getSearchEngine().updateIndex();
                loadSnippetsList();
                editButton.setEnabled(false);
                deleteButton.setEnabled(false);
                searchText.setText("");
            }
        });

        Button newButton = new Button(menuComposite, SWT.PUSH);
        newButton.setLayoutData(new RowData(80, 25));
        newButton.setText("New");
        newButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent evt) {
                // TODO
            }
        });

    }

    private void loadSnippetsList() {
        snippetsList.getList().removeAll();
        String indexFilePath = SnipDSLActivator.getInstance()
                .getPreferenceStore()
                .getString(PreferenceConstants.SNIPPET_INDEX);
        String snippetsPath = SnipDSLActivator.getInstance()
                .getPreferenceStore()
                .getString(PreferenceConstants.SNIPPET_PATH);
        File indexFile = new File(indexFilePath);
        if (indexFile.exists()) {
            Type listType = new TypeToken<java.util.List<LightweightSnippet>>() {
            }.getType();

            java.util.List<LightweightSnippet> sfMapList = GsonUtil
                    .deserialize(indexFile, listType);
            for (LightweightSnippet element : sfMapList) {
                String summary = element.description;
                if (summary == null)
                    continue;
                snippetsList.add(summary);

                // lazy change the element path to current path
                // TODO: generate index file!
                IPath elemPath = new Path(element.filePath);
                element.filePath = snippetsPath + '/' + elemPath.lastSegment();
                snippetsMap.put(summary, element);

            }
        }
    }

    private void searchSnippets(String text) {
        snippetsList.getList().removeAll();
        String indexFilePath = SnipDSLActivator.getInstance()
                .getPreferenceStore()
                .getString(PreferenceConstants.SNIPPET_INDEX);
        File indexFile = new File(indexFilePath);
        if (indexFile.exists()) {
            Type listType = new TypeToken<java.util.List<LightweightSnippet>>() {
            }.getType();
            java.util.List<LightweightSnippet> sfMapList = GsonUtil
                    .deserialize(indexFile, listType);
            for (LightweightSnippet element : sfMapList) {
                if (element.description.toLowerCase().contains(text))
                    snippetsList.add(element.description);
            }
        }
    }

    @Override
    public void setFocus() {
        snippetsList.getList().setFocus();
    }
}
