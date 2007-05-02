/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.text;

import org.eclipse.dltk.ruby.internal.ui.RubyUI;
import org.eclipse.dltk.ui.text.ScriptOutlineInformationControl;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Shell;

public class RubyOutlineInformationControl extends ScriptOutlineInformationControl {
	
	
	public RubyOutlineInformationControl(Shell parent, int shellStyle, int treeStyle, String commandId) {
		super(parent, shellStyle, treeStyle, commandId);
	}

	protected IPreferenceStore getPreferenceStore() {
		return RubyUI.getDefault().getPreferenceStore();
	}
}
