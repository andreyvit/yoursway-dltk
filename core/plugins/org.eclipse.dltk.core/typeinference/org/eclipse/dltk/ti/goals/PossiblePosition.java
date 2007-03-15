/**
 * 
 */
package org.eclipse.dltk.ti.goals;

import org.eclipse.core.resources.IResource;

public class PossiblePosition {
	private final IResource resource;
	private final int offset;
	private final int length;
	
	public PossiblePosition(IResource resource, int offset, int length) {
		super();
		this.resource = resource;
		this.offset = offset;
		this.length = length;
	}
	public IResource getResource() {
		return resource;
	}
	public int getOffset() {
		return offset;
	}
	public int getLength() {
		return length;
	}	
}