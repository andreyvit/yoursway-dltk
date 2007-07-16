/**
 * 
 */
package org.eclipse.dltk.internal.debug.ui;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.editors.text.ILocationProvider;

public class ExternalFileEditorInput implements IPathEditorInput,
		ILocationProvider {
	private File file;

	public ExternalFileEditorInput(File file) {
		super();
		this.file = file;
	}

	public boolean exists() {
		return file.exists();
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public String getName() {
		return file.getName();
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return file.getAbsolutePath();
	}

	public Object getAdapter(Class adapter) {
		if (ILocationProvider.class.equals(adapter)) {
			return this;
		}

		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	public IPath getPath(Object element) {
		if (element instanceof ExternalFileEditorInput) {
			ExternalFileEditorInput input = (ExternalFileEditorInput) element;
			return Path.fromOSString(input.file.getAbsolutePath());
		}
		return null;
	}

	public IPath getPath() {
		return Path.fromOSString(file.getAbsolutePath());
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (o instanceof ExternalFileEditorInput) {
			ExternalFileEditorInput input = (ExternalFileEditorInput) o;
			return file.equals(input.file);
		}

		if (o instanceof IPathEditorInput) {
			IPathEditorInput input = (IPathEditorInput) o;
			return getPath().equals(input.getPath());
		}

		return false;
	}

	public int hashCode() {
		return file.hashCode();
	}
}