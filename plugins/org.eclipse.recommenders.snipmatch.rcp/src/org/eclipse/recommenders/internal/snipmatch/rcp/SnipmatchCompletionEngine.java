/**
 * Copyright (c) 2013 Madhuranga Lakjeewa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Madhuranga Lakjeewa - initial API and implementation.
 */
package org.eclipse.recommenders.internal.snipmatch.rcp;

import static org.eclipse.recommenders.internal.snipmatch.rcp.Constants.*;
import static org.eclipse.recommenders.rcp.utils.Selections.getFirstSelected;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.recommenders.snipmatch.Snippet;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Caret;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.services.IDisposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;

/**
 * This class creates the SnipMatch search box.
 */
public class SnipmatchCompletionEngine implements IDisposable {

    private static final int SEARCH_BOX_WIDTH = 360;
    private static final int SEARCH_BOX_HEIGHT = 200;

    private static final Logger LOG = LoggerFactory
            .getLogger(SnipmatchCompletionEngine.class);

    @Inject
    EclipseSnippetRepository repo;
    @Inject
    TemplateProcessor processor;
    @Inject
    ColorRegistry colorRegistry;
    @Inject
    FontRegistry fontRegistry;

    private JavaContentAssistInvocationContext ctx;

    private Shell searchShell;
    private EditorFocusListener2 editorFocusListener = new EditorFocusListener2();

    private StyledText searchText;
    private Color searchBg;
    private Font searchFont;

    private Shell resultsShell;
    private Color resultsBg;
    private ListViewer resultsViewer;

    public void show(JavaContentAssistInvocationContext ctx) {
        createSearchPopup(ctx);
    }

    private void createSearchPopup(JavaContentAssistInvocationContext context) {
        ctx = context;
        Shell parentShell = ctx.getViewer().getTextWidget().getShell();
        resultsBg = colorRegistry.get(P_SEARCH_RESULTS_BACKGROUND);
        searchShell = new Shell(parentShell, SWT.ON_TOP);
        searchShell.setSize(SEARCH_BOX_WIDTH, 30);
        searchShell.setLayout(new FillLayout());

        searchBg = colorRegistry.get(P_SEARCH_BOX_BACKGROUND);
        searchFont = fontRegistry
                .get("org.eclipse.recommenders.snipmatch.rcp.searchTextFont");
        searchText = new StyledText(searchShell, SWT.BORDER);
        searchText.setBackground(searchBg);
        searchText.setMargins(8, 6, 8, 6);
        searchText.setFont(searchFont);
        searchText.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                try {
                    String query = searchText.getText().trim();
                    List<Snippet> results = repo.search(query);
                    displayResults(results);
                } catch (IOException ex) {
                    LOG.error(
                            "Searching snippet reposiory failed with exception",
                            ex);
                }
            }
        });

        searchText.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.keyCode == SWT.ARROW_DOWN || e.keyCode == SWT.ARROW_UP) {
                    if (resultsViewer != null) {
                        org.eclipse.swt.widgets.List list = resultsViewer
                                .getList();
                        list.setFocus();
                        list.setSelection(0);
                    }
                }
            }
        });

        // Add listener to capture a click event on editor
        Display.getDefault().addFilter(SWT.FocusIn, editorFocusListener);

        Point anchor = computePopupPosition();
        searchShell.setLocation(anchor.x, anchor.y);
        searchShell.open();
        searchShell.setFocus();

        /**
         * When the searchBoxText disposes with shell editorFocusListener should
         * be removed.
         */
        searchText.addDisposeListener(new DisposeListener() {
            @Override
            public void widgetDisposed(DisposeEvent e) {
                Display.getDefault().removeFilter(SWT.FocusIn,
                        editorFocusListener);
            }
        });
    }

    private Point computePopupPosition() {
        StyledText styledText = ctx.getViewer().getTextWidget();
        Caret caret = styledText.getCaret();
        Point location = caret.getLocation();
        return styledText.toDisplay(location.x, location.y + caret.getSize().y);
    }

    private void displayResults(List<Snippet> result) {
        if (resultsShell == null) {
            showResultsPopup();
        }
        resultsViewer.setInput(result);
        Point anchor = computePopupPosition();
        resultsShell.setLocation(anchor.x, anchor.y + 35);
        if (!resultsShell.isDisposed()) {
            resultsShell.setVisible(true);
        }
    }

    private void showResultsPopup() {
        resultsShell = new Shell(searchShell, SWT.BORDER | SWT.RESIZE);
        resultsShell.setSize(SEARCH_BOX_WIDTH, SEARCH_BOX_HEIGHT);
        resultsShell.setLayout(new FillLayout(SWT.VERTICAL));
        resultsViewer = new ListViewer(resultsShell, SWT.FULL_SELECTION);
        resultsViewer.getList().setFont(searchFont);
        resultsViewer.getList().setBackground(resultsBg);
        resultsViewer.setContentProvider(ArrayContentProvider.getInstance());
        resultsViewer.setLabelProvider(new LabelProvider() {
            @Override
            public String getText(Object element) {
                return ((Snippet) element).getDescription();
            }
        });
        resultsViewer.addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(DoubleClickEvent event) {
                applySelection();
            }
        });
        resultsViewer.addOpenListener(new IOpenListener() {

            @Override
            public void open(OpenEvent event) {
                applySelection();
            }
        });
    }

    private void applySelection() {
        Optional<Snippet> snippet = getFirstSelected(resultsViewer
                .getSelection());
        processor.insertTemplate(snippet.orNull());
        dispose();
    }

    @Override
    public void dispose() {
        // not sure why we need to set this to invisible
        if (searchShell != null && !searchShell.isDisposed()) {
            searchShell.setVisible(false);
            searchShell.dispose();
        }
        if (resultsShell != null && !resultsShell.isDisposed()) {
            resultsShell.setVisible(false);
            resultsShell.dispose();
        }
    }

    public class EditorFocusListener2 implements Listener {

        @Override
        public void handleEvent(Event event) {
            Widget widget = event.widget;
            // is widget on of the popup shells?
            if (isShellOrOneOfItsDirectChildren(widget, searchShell)
                    || isShellOrOneOfItsDirectChildren(widget, resultsShell)) {
                return;
            }
            dispose();
        }

        private boolean isShellOrOneOfItsDirectChildren(Widget widget,
                Shell shell) {
            // results shell may be null. handle that case:
            if (shell == null) {
                return false;
            }
            if (widget == shell) {
                return true;
            }
            for (Control c : shell.getChildren()) {
                if (widget == c) {
                    return true;
                }
            }
            return false;
        }
    }
}
