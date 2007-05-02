/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core.search;

import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.core.IModelElement;

/**
 * A Script search match that represents a method reference. The element is the
 * inner-most enclosing member that references this method.
 * <p>
 * This class is intended to be instantiated and subclassed by clients.
 * </p>
 * 
 * 
 */
public class MethodReferenceMatch extends SearchMatch {
	private boolean constructor;
	private boolean synthetic;
	private ASTNode node;

	/**
	 * Creates a new method reference match.
	 * 
	 * @param enclosingElement
	 *            the inner-most enclosing member that references this method
	 * @param accuracy
	 *            one of {@link #A_ACCURATE} or {@link #A_INACCURATE}
	 * @param offset
	 *            the offset the match starts at, or -1 if unknown
	 * @param length
	 *            the length of the match, or -1 if unknown
	 * @param insideDocComment
	 *            <code>true</code> if this search match is inside a doc
	 *            comment, and <code>false</code> otherwise
	 * @param participant
	 *            the search participant that created the match
	 * @param resource
	 *            the resource of the element
	 */
	public MethodReferenceMatch(IModelElement enclosingElement, int accuracy,
			int offset, int length, boolean insideDocComment,
			SearchParticipant participant, IResource resource) {
		super(enclosingElement, accuracy, offset, length, participant, resource);
		setInsideDocComment(insideDocComment);
	}

	/**
	 * Creates a new method reference match.
	 * 
	 * @param enclosingElement
	 *            the inner-most enclosing member that references this method
	 * @param accuracy
	 *            one of {@link #A_ACCURATE} or {@link #A_INACCURATE}
	 * @param offset
	 *            the offset the match starts at, or -1 if unknown
	 * @param length
	 *            the length of the match, or -1 if unknown
	 * @param constructor
	 *            <code>true</code> if this search match a constructor
	 *            <code>false</code> otherwise
	 * @param synthetic
	 *            <code>true</code> if this search match a synthetic element
	 *            <code>false</code> otherwise
	 * @param insideDocComment
	 *            <code>true</code> if this search match is inside a doc
	 *            comment, and <code>false</code> otherwise
	 * @param participant
	 *            the search participant that created the match
	 * @param resource
	 *            the resource of the element
	 * 
	 */
	public MethodReferenceMatch(IModelElement enclosingElement, int accuracy,
			int offset, int length, boolean constructor, boolean synthetic,
			boolean insideDocComment, SearchParticipant participant,
			IResource resource, ASTNode node) {
		this(enclosingElement, accuracy, offset, length, insideDocComment,
				participant, resource);
		this.constructor = constructor;
		this.synthetic = synthetic;
		this.node = node;
	}

	/**
	 * Returns whether the reference is on a constructor.
	 * 
	 * @return Returns whether the reference is on a constructor or not.
	 * 
	 */
	public final boolean isConstructor() {
		return this.constructor;
	}

	/**
	 * Returns whether the reference is on a synthetic element. Note that this
	 * field is only used for constructor reference. This happens when default
	 * constructor declaration is used or implicit super constructor is called.
	 * 
	 * @return whether the reference is synthetic or not.
	 * 
	 */
	public final boolean isSynthetic() {
		return this.synthetic;
	}

	public ASTNode getNode() {
		return node;
	}
	
}
