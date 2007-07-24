/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.ui.preferences;

import org.eclipse.dltk.python.internal.ui.text.folding.PythonFoldingPreferenceBlock;
import org.eclipse.dltk.ui.preferences.FoldingConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.text.folding.IFoldingPreferenceBlock;
import org.eclipse.jface.preference.PreferencePage;


public class PythonFoldingConfigurationBlock extends FoldingConfigurationBlock {

	public PythonFoldingConfigurationBlock(OverlayPreferenceStore store, PreferencePage p) {
		super(store, p);		
	}
	
	protected IFoldingPreferenceBlock createFoldingPreferenceBlock () {
		return new PythonFoldingPreferenceBlock(fStore);
	}
}
