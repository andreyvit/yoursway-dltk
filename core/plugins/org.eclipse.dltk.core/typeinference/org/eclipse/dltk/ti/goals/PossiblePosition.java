/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
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