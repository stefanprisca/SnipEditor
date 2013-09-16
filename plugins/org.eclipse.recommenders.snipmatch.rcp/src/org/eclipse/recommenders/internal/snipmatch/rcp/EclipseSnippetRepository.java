/**
 * Copyright (c) 2010, 2013 Darmstadt University of Technology.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Marcel Bruch - initial API and implementation.
 */
package org.eclipse.recommenders.internal.snipmatch.rcp;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.recommenders.internal.rcp.RcpPlugin;
import org.eclipse.recommenders.rcp.IRcpService;
import org.eclipse.recommenders.snipmatch.ISnippetRepository;
import org.eclipse.recommenders.snipmatch.SimpleSnippetRepository;
import org.eclipse.recommenders.snipmatch.Snippet;

import com.google.common.collect.ImmutableSet;
import com.google.common.eventbus.Subscribe;

public class EclipseSnippetRepository implements ISnippetRepository,
        IRcpService {

    @Inject
    SnipmatchRcpPreferences prefs;

    @Inject
    IWorkspaceRoot root;

    SimpleSnippetRepository delegate;

    @Override
    @PostConstruct
    public void open() throws IOException {
        try {
            IProject project = root.getProject(".snipmatch");
            if (!project.exists()) {
                ImportSnippetsRepositoryJob job = new ImportSnippetsRepositoryJob(
                        root, prefs.getLocation());
                job.schedule();
                job.join();
            }
            project.open(null);
            File path = project.getLocation().toFile();
            delegate = new SimpleSnippetRepository(path);
            delegate.open();
        } catch (Exception e) {
            RcpPlugin.logError(e, "Failed to open snippets project");
        }
    }

    @Override
    @PreDestroy
    public void close() throws IOException {
        delegate.close();
    }

    public void index() throws IOException {
        delegate.index();
    }

    @Override
    public List<Snippet> search(String query) throws IOException {
        return delegate.search(query);
    }

    @Override
    public ImmutableSet<Snippet> getSnippets() {
        return delegate.getSnippets();
    }

    @Subscribe
    public void onEvent(SnippetRepositoryChangedEvent e) throws IOException {
        close();
        open();
    }

    public static class SnippetRepositoryChangedEvent {
    }
}
