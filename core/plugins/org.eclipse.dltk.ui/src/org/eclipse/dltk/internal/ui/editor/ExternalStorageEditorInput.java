package org.eclipse.dltk.internal.ui.editor;

import org.eclipse.core.resources.IStorage;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.PlatformUI;


public class ExternalStorageEditorInput implements IEditorInput, IStorageEditorInput {
	private IStorage fStorage;	
	public ExternalStorageEditorInput(IStorage storage) {
		this.fStorage = storage;
	}

	public boolean exists() {
		return fStorage != null;
	}

	public ImageDescriptor getImageDescriptor() {
		return PlatformUI.getWorkbench().getEditorRegistry().getImageDescriptor(this.fStorage.getName());		
	}

	public String getName() {
		return fStorage.getName();
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return fStorage.getFullPath().toOSString();
	}

	public Object getAdapter(Class adapter) {
		if( adapter == this.getClass() ) {
			return this;
		}
		if(adapter == IModelElement.class && fStorage instanceof IModelElement ) {
			return fStorage;
		}
		return null;
	}

	public IStorage getStorage() {
		return this.fStorage;
	}
	public boolean equals(Object obj) {
        if (this == obj) {
			return true;
		}
        if (!(obj instanceof ExternalStorageEditorInput)) {
			return false;
		}
        ExternalStorageEditorInput other = (ExternalStorageEditorInput) obj;
        return fStorage.equals(other.fStorage);
    }
	  /* (non-Javadoc)
     * Method declared on Object.
     */
    public int hashCode() {
        return fStorage.hashCode();
    }
}
