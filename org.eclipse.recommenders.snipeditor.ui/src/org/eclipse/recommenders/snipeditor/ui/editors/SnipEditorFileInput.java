package org.eclipse.recommenders.snipeditor.ui.editors;

import java.io.ByteArrayInputStream;

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
import org.eclipse.recommenders.snipeditor.ui.core.Effect;
import org.eclipse.recommenders.snipeditor.ui.internal.SnipDSLActivator;
import org.eclipse.recommenders.utils.gson.GsonUtil;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPersistableElement;

public class SnipEditorFileInput implements IFileEditorInput {

    public static final int VAR_CODE = 0;
    public static final int VAR_NAME = 1;
    public static final int FULL_SNIPPET = 2;

    private IFile localSnip;
    private Effect snippet;

    public SnipEditorFileInput(java.io.File jsonSnippet) {
        localSnip = createFile(jsonSnippet);
        // snipStorage = new SnipStorage(snippet.getCode());
    }

    @Override
    public IStorage getStorage() throws CoreException {
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

    public Object getContents(int contentID) {
        switch (contentID) {
        case VAR_CODE:
            return snippet.getCode();
        case VAR_NAME:
            return getName();
        case FULL_SNIPPET:
            return snippet;
        }
        return null;
    }

    private IFile createFile(java.io.File json) {
        IFile file = null;
        try {

            IFolder src = createExternalProjectSrcFolder();
            IPath location = new Path(json.getAbsolutePath());
            snippet = GsonUtil.deserialize(json, Effect.class);
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
        IProject project = ws.getRoot().getProject("External Snippets");

        IJavaProject javaProject = null;
        IFolder folder = null;
        try {
            if (!project.exists())
                project.create(null);

            if (!project.isOpen())
                project.open(null);

            IProjectDescription description = project.getDescription();
            description.setNatureIds(new String[] { JavaCore.NATURE_ID,
                    "org.eclipse.xtext.ui.shared.xtextNature" });

            // create the project
            project.setDescription(description, null);
            javaProject = JavaCore.create(project);

            // set the build path
            IClasspathEntry [] buildPath = {
                    JavaCore.newSourceEntry(project.getFullPath().append("src")),
                    JavaRuntime.getDefaultJREContainerEntry() };

            javaProject.setRawClasspath(buildPath, project.getFullPath()
                    .append("bin"), null);

            // create folder by using resources package
            folder = project.getFolder("src");
            if (!folder.exists())
                folder.create(false, true, null);

        } catch (CoreException ce) {

        }

        return folder;
    }

}
