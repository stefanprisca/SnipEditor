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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.recommenders.snipmatch.Snippet;
import org.eclipse.recommenders.utils.gson.GsonUtil;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.PlatformUI;

public class SnipEditorFileInput implements IFileEditorInput {

    public static final int VAR_CODE = 0;
    public static final int VAR_NAME = 1;
    public static final int VAR_ALIASES = 2;
    public static final int VAR_AUTHOR = 3;
    public static final int VAR_DESCRIPTION = 5;
    // public static final int FULL_SNIPPET = 2;

    private Snippet snippet;
    IFile localSnip;

    private File externalSnippet;

    public SnipEditorFileInput(IFile localSnip) {
        this.localSnip = localSnip;
    }

    public SnipEditorFileInput(java.io.File jsonSnippet) {

        localSnip = createFile(jsonSnippet);
        externalSnippet = jsonSnippet;
        // snipStorage = new SnipStorage(snippet.getCode());
    }

    public String getExternalPath() {
        return externalSnippet.getAbsolutePath();
    }

    public Snippet getSnippet(){
        return snippet;
    }
    @Override
    public IStorage getStorage() {
        // TODO Auto-generated method stub

        return localSnip;
    }

    @Override
    public boolean exists() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        if(snippet.getName()!="")
            return snippet.getName();
        else
            return localSnip.getName();
    }

    @Override
    public IPersistableElement getPersistable() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getToolTipText() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getAdapter(Class adapter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IFile getFile() {
        // TODO Auto-generated method stub
        return localSnip;
    }

    @Override
    public int hashCode() {
        return localSnip.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IFileEditorInput)) {
            return false;
        }
        IFileEditorInput other = (IFileEditorInput) obj;
        return localSnip.equals(other.getFile());
    }

    public void setName(String newName){
        snippet.setName(newName);
        
    }
    public void setAliases(List<String> aliases){
        snippet.setAliases(aliases);
    }
    
    public void setCode(String code){
        snippet.setCode(code);
        
    }
    public Object getContents(int contentID) {
        System.out.println(snippet);
        switch (contentID) {
        case VAR_CODE:
            return snippet.getCode();
        case VAR_NAME:
            return getName();
        case VAR_ALIASES:
            return snippet.getAliases();
        case VAR_DESCRIPTION:
            return snippet.getDescription();

            // case FULL_SNIPPET:
            // return snippet;
        }
        return null;
    }

    private IFile createFile(java.io.File json) {
        IFile file = null;
        try {

            IFolder src = createExternalProjectSrcFolder();
            IPath location = new Path(json.getAbsolutePath());
            snippet = GsonUtil.deserialize(json, Snippet.class);
            location = location.removeFileExtension().addFileExtension("cSnip");
            file = src.getFile(location.lastSegment());
            if (!file.exists())
                file.create(new ByteArrayInputStream(snippet.getCode()
                        .getBytes()), false, null);
        } catch (CoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return file;

    }

    private IFolder createExternalProjectSrcFolder() {
        IWorkspace ws = ResourcesPlugin.getWorkspace();
        final IProject project = ws.getRoot().getProject("External Snippets");

        IJavaProject javaProject = null;
        IFolder folder = null;
        try {
            if (!project.exists()) {
                project.create(null);
                PlatformUI.getWorkbench().addWorkbenchListener(
                        new IWorkbenchListener() {

                            @Override
                            public boolean preShutdown(IWorkbench workbench,
                                    boolean forced) {
                                // TODO Auto-generated method stub
                                try {
                                    System.out
                                            .println("The project should delete");
                                    project.delete(true, true, null);
                                    return true;
                                } catch (CoreException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                    return false;
                                }
                            }

                            @Override
                            public void postShutdown(IWorkbench workbench) {
                                // TODO Auto-generated method stub

                            }

                        });
            }

            if (!project.isOpen())
                project.open(null);

            IProjectDescription description = project.getDescription();
            description.setNatureIds(new String[] { JavaCore.NATURE_ID,
                    "org.eclipse.xtext.ui.shared.xtextNature" });

            // create the project
            project.setDescription(description, null);
            javaProject = JavaCore.create(project);

            // System.out.println(path);
            // set the build path

            javaProject.setRawClasspath(createClassPath(project), project
                    .getFullPath().append("bin"), null);

            // create folder by using resources package
            folder = project.getFolder("src");
            if (!folder.exists())
                folder.create(false, true, null);

        } catch (CoreException ce) {

        }

        return folder;
    }

    private IClasspathEntry[] createClassPath(IProject project) {
        ArrayList<IClasspathEntry> classPath = new ArrayList<IClasspathEntry>();
        // Add default buildpath
        classPath.add(JavaCore.newSourceEntry(project.getFullPath().append(
                "src")));
        classPath.add(JavaRuntime.getDefaultJREContainerEntry());

        // add libraries that are in the eclipse plugin folder
        IPath path = new Path(JavaCore.getClasspathVariable("ECLIPSE_HOME")
                .toString() + "/plugins/");
        LinkedList<File> list = new LinkedList<File>();
        File dir = new File(path.toOSString());
        // list.addAll(dir.listFiles());

        for (File f : dir.listFiles()) {

            // tmp = list.removeFirst();
            if (f.isDirectory()) {
                continue;
            } else if (f.getAbsolutePath().endsWith(".jar")
                    && f.getAbsolutePath().contains("swt")) {

                classPath.add(JavaCore.newLibraryEntry(
                        new Path(f.getAbsolutePath()), null, path));
            }
        }
        IClasspathEntry[] a = {};
        return classPath.toArray(a);

    }

}
