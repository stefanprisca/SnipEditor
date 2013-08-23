package org.eclipse.recommenders.snipeditor.ui.preferences;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.recommenders.snipeditor.ui.internal.SnipDSLActivator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class SnipPreferencePage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {

    public SnipPreferencePage() {
        super(GRID);
        setPreferenceStore(SnipDSLActivator.getInstance().getPreferenceStore());
        setDescription("A demonstration of a preference page implementation");
    }

    /**
     * Creates the field editors. Field editors are abstractions of the common
     * GUI blocks needed to manipulate various types of preferences. Each field
     * editor knows how to save and restore itself.
     */
    public void createFieldEditors() {
        addField(new DirectoryFieldEditor(PreferenceConstants.SNIPPET_PATH,
                "&Local Snippets Directory:", getFieldEditorParent()));
        addField(new FileFieldEditor(PreferenceConstants.SNIPPET_INDEX,
                "&Local Snippets Index File:", getFieldEditorParent()));
    }

    @Override
    protected Control createContents(Composite parent) {

        Composite comp = (Composite) super.createContents(parent);

        Button createIndex = new Button(comp, SWT.PUSH);
        createIndex.setText("Generate Index File");
        createIndex.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent evt) {
                IPath pathToSnippets = new Path(SnipDSLActivator.getInstance()
                        .getPreferenceStore()
                        .getString(PreferenceConstants.SNIPPET_PATH));
                if (pathToSnippets.isAbsolute()) {
                    System.out.println("Valid Path");
                    SnipDSLActivator
                            .getInstance()
                            .getPreferenceStore()
                            .setValue(
                                    PreferenceConstants.SNIPPET_INDEX,
                                    pathToSnippets.append("/index.txt")
                                            .toString());
                    createIndex();
                }
            }
        });
        return comp;

    }

    public void createIndex() {
        if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
            Shell shell = new Shell(PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow().getShell(), SWT.NO_TRIM
                    | SWT.NO_FOCUS | SWT.NO_BACKGROUND);
            try {
                CreateIndexOperation indexer = new CreateIndexOperation();
                new ProgressMonitorDialog(shell).run(true, true, indexer);
                // MessageDialog.openInformation(shell,
                // Constants.MSG_DIALOG_TITLE,
                // "Load "+indexer.getIndexNumber()+" tempaltes into index success!");
            } catch (Exception e) {
                e.printStackTrace();
                // MessageDialog.openError(shell, Constants.MSG_DIALOG_TITLE,
                // Constants.MSG_FAIL_TO_INDEX_TEMPLATES);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
    }

}
