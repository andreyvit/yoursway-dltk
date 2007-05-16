/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.debug.ui.interpreters;


import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterLibraryBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.AddScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.LibraryLabelProvider;
import org.eclipse.dltk.tcl.internal.debug.ui.TclDebugUIPlugin;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IBaseLabelProvider;


/**
 * Control used to edit the libraries associated with a Interpreter install
 */
public class TclInterpreterLibraryBlock extends AbstractInterpreterLibraryBlock {

	public TclInterpreterLibraryBlock(AddScriptInterpreterDialog d) {
		super(d);
	}

	protected IBaseLabelProvider getLabelProvider() {
		return new LibraryLabelProvider();
	}
	protected IDialogSettings getDialogSettions() {
		return TclDebugUIPlugin.getDefault().getDialogSettings();
	}
}
