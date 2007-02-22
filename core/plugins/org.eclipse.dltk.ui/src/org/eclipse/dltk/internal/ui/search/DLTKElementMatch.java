/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.search;

import org.eclipse.search.ui.text.Match;

/**
 * A search match with additional java-specific info.
 */
public class DLTKElementMatch extends Match {
	private int fAccuracy;
	private int fMatchRule;
	private boolean fIsWriteAccess;
	private boolean fIsReadAccess;
	private boolean fIsScriptdoc;
	
	DLTKElementMatch(Object element, int matchRule, int offset, int length, int accuracy, boolean isReadAccess, boolean isWriteAccess, boolean isJavadoc) {
		super(element, offset, length);
		fAccuracy= accuracy;
		fMatchRule= matchRule;
		fIsWriteAccess= isWriteAccess;
		fIsReadAccess= isReadAccess;
		fIsScriptdoc= isJavadoc;
	}

	public int getAccuracy() {
		return fAccuracy;
	}

	public boolean isWriteAccess() {
		return fIsWriteAccess;
	}

	public boolean isReadAccess() {
		return fIsReadAccess;
	}

	public boolean isScriptdoc() {
		return fIsScriptdoc;
	}
	
	public int getMatchRule() {
		return fMatchRule;
	}
}
