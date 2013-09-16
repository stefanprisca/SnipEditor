package org.eclipse.recommenders.internal.snipmatch.rcp;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.recommenders.internal.snipmatch.rcp.editors.SnipEditorFileInput;
import org.eclipse.recommenders.rcp.utils.ObjectToBooleanConverter;
import org.eclipse.recommenders.rcp.utils.Selections;
import org.eclipse.recommenders.snipmatch.ISnippetRepository;
import org.eclipse.recommenders.snipmatch.Snippet;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ViewPart;

import com.google.common.base.Optional;

public class SnippetsView extends ViewPart {
    private DataBindingContext bindingContext;

    @Inject
    private ISnippetRepository repo;

    private Text txtSearch;
    private List list;
    private ListViewer viewer;
    private Button btnEdit;
    private Button btnRemove;
    private Button btnAdd;

    @Override
    public void createPartControl(Composite parent) {

        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(2, false));

        txtSearch = new Text(composite, SWT.BORDER | SWT.ICON_SEARCH
                | SWT.SEARCH | SWT.CANCEL);
        txtSearch.setMessage("type filter text");
        txtSearch.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
                1, 1));
        txtSearch.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                try {
                    Collection<Snippet> matches = repo.search(txtSearch
                            .getText());
                    viewer.setInput(matches);
                } catch (IOException x) {
                    getViewSite().getActionBars().getStatusLineManager()
                            .setErrorMessage(x.getMessage());
                }
            }
        });

        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.keyCode == SWT.ARROW_DOWN && list.getItemCount() != 0) {
                    list.setFocus();
                    list.setSelection(0);
                }
            }
        });
        new Label(composite, SWT.NONE);

        viewer = new ListViewer(composite);
        list = viewer.getList();
        list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3));

        btnAdd = new Button(composite, SWT.NONE);
        btnAdd.setEnabled(false);
        btnAdd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
                1, 1));
        btnAdd.setText("Add");
        btnAdd.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

            }
        });

        btnEdit = new Button(composite, SWT.NONE);
        btnEdit.setEnabled(false);
        btnEdit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
                1, 1));
        btnEdit.setText("Edit...");
        btnEdit.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Optional<Snippet> s = Selections.getFirstSelected(viewer);
                // repo.remove(s);
                // viewer.setInput(input)
            }
        });

        btnRemove = new Button(composite, SWT.NONE);
        btnRemove.setEnabled(false);
        btnRemove.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false,
                1, 1));
        btnRemove.setText("Remove");
        btnRemove.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Optional<Snippet> s = Selections.getFirstSelected(viewer);
                // viewer.refresh(s.get())
            }
        });

        viewer.setLabelProvider(new LabelProvider() {
            @Override
            public String getText(Object element) {
                return ((Snippet) element).getDescription();
            }
        });
        viewer.addOpenListener(new IOpenListener() {

            @Override
            public void open(OpenEvent event) {
                // open in editor
                Optional<Snippet> snippet = Selections.getFirstSelected(event
                        .getSelection());
                File location = snippet.get().getLocation();
                IWorkbenchPage page = PlatformUI.getWorkbench()
                        .getActiveWorkbenchWindow().getActivePage();
                // SnipEditorFileInput input = new
                // SnipEditorFileInput(location);
                // try {
                // page.openEditor(input,
                // "org.eclipse.recommenders.snipeditor.SnipDSL");
                // } catch (PartInitException pie) {
                // Throwables.propagate(pie);
                // }
            }
        });

        viewer.addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(DoubleClickEvent event) {
                // TODO Auto-generated method stub

                Optional<Snippet> snippet = Selections.getFirstSelected(event
                        .getSelection());
                File location = snippet.get().getLocation();
                if (location.exists()) {
                    System.out.println(location);
                    IWorkbenchPage page = PlatformUI.getWorkbench()
                            .getActiveWorkbenchWindow().getActivePage();
                    // SnipEditorStorageInput snipInput=new
                    // SnipEditorStorageInput(jsonFile);
                    // new SnipEditorFileInput(jsonFile);

                    try {
                        // System.out.println("Double click");
                        // page.openEditor(snipInput, "MultiPageSnipEditor");
                        IFileEditorInput input = new FileEditorInput(
                                new SnipEditorFileInput(location).getFile());
                        page.openEditor(new SnipEditorFileInput(location),
                                "org.eclipse.recommenders.snipmatch.MultiPage");

                    } catch (PartInitException pie) {
                        throw new RuntimeException(pie);
                    }
                }

            }

        });

        viewer.setContentProvider(new ArrayContentProvider());
        viewer.setSorter(new ViewerSorter());
        viewer.setInput(repo.getSnippets());
        bindingContext = initDataBindings();
    }

    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }

    protected DataBindingContext initDataBindings() {
        DataBindingContext bindingContext = new DataBindingContext();
        //
        IObservableValue selectionValue = ViewerProperties.singleSelection()
                .observe(viewer);
        IObservableValue enabledBtnEditValue = PojoProperties.value("enabled")
                .observe(btnEdit);
        UpdateValueStrategy strategy = new UpdateValueStrategy();
        strategy.setConverter(new ObjectToBooleanConverter());
        bindingContext.bindValue(selectionValue, enabledBtnEditValue, strategy,
                null);

        //
        IObservableValue enabledBtnRemoveValue = PojoProperties
                .value("enabled").observe(btnRemove);
        bindingContext.bindValue(selectionValue, enabledBtnRemoveValue,
                strategy, new UpdateValueStrategy(
                        UpdateValueStrategy.POLICY_NEVER));
        //
        return bindingContext;
    }
}
