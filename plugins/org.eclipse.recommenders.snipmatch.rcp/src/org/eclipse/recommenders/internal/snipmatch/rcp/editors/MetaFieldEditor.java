/*******************************************************************************
 * Copyright (c) 2013 Stefan Prisca.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Stefan Prisca - initial API and implementation
 ******************************************************************************/
package org.eclipse.recommenders.internal.snipmatch.rcp.editors;

import java.util.ArrayList;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Point;
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
    MetadataPage owner;

    public MetaFieldEditor(MetadataPage owner,Composite parent, int style, Control editable,
            Point position) {
        super(parent, style);
        this.owner=owner;
        editableControl = editable;
        Shell sh = new Shell(parent.getShell(), SWT.ON_TOP);
        sh.setLocation(position);

        createContents(sh, -1);
        // TODO Auto-generated constructor stub
    }

    public MetaFieldEditor(MetadataPage owner, Composite parent, int style, Control editable,
            Point position, int index) {
        super(parent, style);
        this.owner=owner;
        editableControl = editable;
        Shell sh = new Shell(parent.getShell(), SWT.ON_TOP);
        sh.setLocation(position);
        createContents(sh, index);
        // TODO Auto-generated constructor stub
    }

    private void createContents(final Shell sh, final int index) {
        // TODO Auto-generated method stub
        sh.setVisible(true);
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
        newItem.setToolTipText("Press Enter in order to confirm, Esc to cancel");
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
                            owner.setAliases(((List) editableControl).getItems());
                        } else {
                            if (newItem.getText() != "") {

                                
                                ((List) editableControl).add(newItem.getText());
                                owner.setAliases(((List) editableControl).getItems());
                            }
                        }
                    }

                    sh.dispose();
                }
            }
        });

        sh.pack();

    }

}
