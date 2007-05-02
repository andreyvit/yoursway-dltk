/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.formatting;


public class MidBlockMarker extends AbstractBlockMarker {

	public MidBlockMarker(String aKeyword, int aLine) {
		super(aKeyword, aLine);
	}

	protected void indentAfterPrint(IndentationState state) {
		state.incIndentationLevel() ;
	}


	protected void indentBeforePrint(IndentationState state) {
		state.decIndentationLevel() ;
	}

}
