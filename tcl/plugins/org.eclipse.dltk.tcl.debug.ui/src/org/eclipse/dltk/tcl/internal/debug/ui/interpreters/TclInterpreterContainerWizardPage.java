/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.debug.ui.interpreters;

import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterComboBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterContainerWizardPage;
import org.eclipse.dltk.ui.wizards.IBuildpathContainerPageExtension;

public class TclInterpreterContainerWizardPage extends
		AbstractInterpreterContainerWizardPage implements
		IBuildpathContainerPageExtension {
	private TclInterpreterComboBlock block;
	private IScriptProject project;
	private IBuildpathEntry[] currentEntries;

	protected AbstractInterpreterComboBlock getInterpreterBlock() {
		if (block == null) {
			block = new TclInterpreterComboBlock();
		}
		block.initialize(project, currentEntries);
		return block;
	}

	public void initialize(IScriptProject project,
			IBuildpathEntry[] currentEntries) {
		this.project = project;
		this.currentEntries = currentEntries;
		if (block != null) {
			block.initialize(project, currentEntries);
		}
	}
}
