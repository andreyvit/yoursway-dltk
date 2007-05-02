/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.text;

import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.jface.text.reconciler.MonoReconciler;
import org.eclipse.ui.texteditor.ITextEditor;

public class ScriptReconciler extends MonoReconciler
{
	public ScriptReconciler( ITextEditor editor, IReconcilingStrategy strategy, boolean isIncremental ) {
		super(strategy, isIncremental);
	}
}
