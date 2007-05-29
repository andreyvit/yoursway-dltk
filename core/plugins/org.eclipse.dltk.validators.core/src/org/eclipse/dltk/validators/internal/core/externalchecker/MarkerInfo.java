package org.eclipse.dltk.validators.internal.core.externalchecker;

import org.eclipse.core.resources.IResource;

public class MarkerInfo {

	private int line;
	private IResource resource;
	public void setLine(int line) {
		this.line = line;
	}
	public int getLine() {
		return line;
	}
	public void setResource(IResource resource) {
		this.resource = resource;
	}
	public IResource getResource() {
		return resource;
	}

}
