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

import static org.eclipse.core.runtime.Status.OK_STATUS;
import static org.eclipse.ui.internal.misc.StatusUtil.newStatus;

import java.io.File;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

public class ImportSnippetsRepositoryJob extends WorkspaceJob {

    private static final String PROJECT_NAME = ".snipmatch";
    private String url;
    private IWorkspaceRoot root;
    private IProject project;
    private File path;

    public ImportSnippetsRepositoryJob(IWorkspaceRoot root, String url) {
        super("cloning snippets repository");
        this.root = root;
        this.url = url;
    }

    @Override
    public IStatus runInWorkspace(IProgressMonitor monitor)
            throws CoreException {
        try {
            path = new File(root.getLocation().toFile(), PROJECT_NAME);
            project = root.getProject(PROJECT_NAME);
            cloneSnippetsRepo();
            createAndOpen();
            addJavaNature();
        } catch (Exception e) {
            return newStatus(Constants.BUNDLE_ID, e);
        }
        return OK_STATUS;
    }

    private void cloneSnippetsRepo() throws GitAPIException,
            InvalidRemoteException, TransportException {
        if (path.exists()) {
            return;
        }
        CloneCommand clone = Git.cloneRepository();
        clone.setBare(false);
        clone.setBranch("master");
        clone.setCloneAllBranches(false);
        clone.setDirectory(path).setURI(url);
        clone.call();
    }

    private void createAndOpen() throws CoreException {
        project.create(null);
        project.open(null);
    }

    private void addJavaNature() throws CoreException {
        IProjectDescription desc = project.getDescription();
        String[] ids = desc.getNatureIds();
        ids = ArrayUtils.add(ids, JavaCore.NATURE_ID);
        desc.setNatureIds(ids);
    }

}
