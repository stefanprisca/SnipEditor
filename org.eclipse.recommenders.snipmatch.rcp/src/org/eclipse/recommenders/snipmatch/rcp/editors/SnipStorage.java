package org.eclipse.recommenders.snipmatch.rcp.editors;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;

public class SnipStorage implements IStorage {

    private String snipContents;
    private File snippetStorage;

    public SnipStorage(String contents) {
        snipContents = contents;
        // this.snippetStorage=snippet;
    }

    @Override
    public Object getAdapter(Class adapter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public InputStream getContents() throws CoreException {
        // TODO Auto-generated method stub
        return new ByteArrayInputStream(snipContents.getBytes());
    }

    @Override
    public IPath getFullPath() {
        // TODO Auto-generated method stub
        IPath location = new Path(snippetStorage.getAbsolutePath());
        return location;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Snip storage";
    }

    @Override
    public boolean isReadOnly() {
        // TODO Auto-generated method stub
        return true;
    }

}
