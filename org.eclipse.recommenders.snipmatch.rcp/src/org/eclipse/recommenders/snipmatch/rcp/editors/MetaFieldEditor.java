package org.eclipse.recommenders.snipmatch.rcp.editors;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MetaFieldEditor extends Composite {

    private Control editableControl;

    public MetaFieldEditor(Composite parent, int style, Control editable,
            Rectangle bounds) {
        super(parent, style);
        editableControl = editable;
        Shell sh = new Shell(parent.getShell());
        // sh.setFocus();
        createContents(sh, -1);
        // TODO Auto-generated constructor stub
    }

    public MetaFieldEditor(Composite parent, int style, Control editable,
            Rectangle bounds, int index) {
        super(parent, style);
        editableControl = editable;
        Shell sh = new Shell(parent.getShell());
        createContents(sh, index);
        // TODO Auto-generated constructor stub
    }

    private void createContents(final Shell sh, final int index) {
        // TODO Auto-generated method stub
        sh.setVisible(true);
        sh.setText("Add a new item");
        // Composite contents=new Composite(sh, SWT.None);
        sh.setLayout(new GridLayout());
        final Text newItem = new Text(sh, SWT.BORDER);
        if (index != -1) {
            newItem.setText(((List) editableControl).getItem(index));

        }
        GridData layoutData = new GridData();
        // layoutData.horizontalIndent=300;
        layoutData.grabExcessHorizontalSpace = true;
        layoutData.widthHint = 300;
        newItem.setLayoutData(layoutData);
        newItem.setFocus();
        newItem.setToolTipText("Press Enter in order to confirm");
        newItem.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                if (e.character == SWT.LF || e.character == SWT.CR) {
                    if (editableControl instanceof List) {
                        if (index != -1) {
                            ((List) editableControl).setItem(index,
                                    newItem.getText());
                        } else {
                            ((List) editableControl).add(newItem.getText());
                        }
                    }

                    sh.dispose();
                }
            }
        });

        sh.pack();

    }

}
