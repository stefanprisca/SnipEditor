package org.eclipse.recommenders.snipeditor.ui.editors;

import java.io.File;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.recommenders.snipeditor.ui.core.Effect;
import org.eclipse.recommenders.utils.gson.GsonUtil;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;

public class SnipEditorStorageInput implements IStorageEditorInput {

    private Effect snippet;

    public SnipEditorStorageInput(File json) {
        snippet = GsonUtil.deserialize(json, Effect.class);
    }

    public String getCode() {
        return snippet.getCode();
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub

        return snippet.getEnvironmentName() != null ? snippet
                .getEnvironmentName() + ".cSnip" : "No_name" + ".cSnip";
    }

    @Override
    public String getToolTipText() {
        // TODO Auto-generated method stub
        return "Editing " + snippet.getEnvironmentName();
    }

    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SnipEditorStorageInput other = (SnipEditorStorageInput) obj;
        if (snippet.getEnvironmentName() != other.getName())
            return false;
        return true;
    }

    @Override
    public IStorage getStorage() throws CoreException {
        // TODO try and create a valid storage, file-editors should support this
        return new SnipStorage(getCode());
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
    public IPersistableElement getPersistable() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getAdapter(Class adapter) {
        // TODO Auto-generated method stub
        return null;
    }

}
