/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.editor;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.actions.SelectionConverter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextSelection;


/**
 * A special text selection that gives access to the resolved and
 * enclosing element.
 */
public class ModelTextSelection extends TextSelection {

	private IModelElement fElement;
	private IModelElement[] fResolvedElements;

	private boolean fEnclosingElementRequested;
	private IModelElement fEnclosingElement;

	/**
	 * Creates a new text selection at the given offset and length.
	 */
	public ModelTextSelection(IModelElement element, IDocument document, int offset, int length) {
		super(document, offset, length);
		fElement= element;
	}

	/**
	 * Resolves the <code>IModelElement</code>s at the current offset. Returns
	 * an empty array if the string under the offset doesn't resolve to a
	 * <code>IModelElement</code>.
	 *
	 * @return the resolvedscriptelements at the current offset
	 * @throws ModelException passed from the underlying code resolve API
	 */
	public IModelElement[] resolveElementAtOffset() throws ModelException {
		if (fResolvedElements != null)
			return fResolvedElements;
		// long start= System.currentTimeMillis();
		fResolvedElements= SelectionConverter.codeResolve(fElement, this);
		// System.out.println("Time resolving element: " + (System.currentTimeMillis() - start));
		return fResolvedElements;
	}

	public IModelElement resolveEnclosingElement() throws ModelException {
		if (fEnclosingElementRequested)
			return fEnclosingElement;
		fEnclosingElementRequested= true;
		fEnclosingElement= SelectionConverter.resolveEnclosingElement(fElement, this);
		return fEnclosingElement;
	}
}
