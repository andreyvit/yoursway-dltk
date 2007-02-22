/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.debug.ui.interpreters;


import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterLibraryBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.AddDLTKInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.LibraryLabelProvider;
import org.eclipse.dltk.ruby.internal.debug.ui.RubyDebugUIPlugin;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IBaseLabelProvider;

/**
 * Control used to edit the libraries associated with a Interpreter install
 */
public class RubyInterpreterLibraryBlock extends AbstractInterpreterLibraryBlock {

	/**
	 * the prefix for dialog setting pertaining to this block
	 */
	protected static final String DIALOG_SETTINGS_PREFIX = "RubyInterpreterLibraryBlock"; //$NON-NLS-1$

	public RubyInterpreterLibraryBlock(AddDLTKInterpreterDialog d) {
	    super(d);
	}
	protected IBaseLabelProvider getLabelProvider() {
		return new LibraryLabelProvider();
	}
	protected IDialogSettings getDialogSettions() {
		return RubyDebugUIPlugin.getDefault().getDialogSettings();
	}
}
