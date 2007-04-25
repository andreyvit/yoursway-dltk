/**
 * 
 */
package org.eclipse.dltk.ti.goals;

import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.ast.ASTNode;

public class PossiblePosition {
	private final IResource resource;
	private final int offset;
	private final int length;
	private final ASTNode node;
	
	public PossiblePosition(IResource resource, int offset, int length) {
		super();
		this.resource = resource;
		this.offset = offset;
		this.length = length;
		this.node = null;
	}
	
	public PossiblePosition(IResource resource, int offset, int length, ASTNode node) {
		super();
		this.resource = resource;
		this.offset = offset;
		this.length = length;
		this.node = node;
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

	/**
	 * Node could be null
	 */
	public ASTNode getNode() {
		return node;
	}
	
}