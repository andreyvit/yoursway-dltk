/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.preferences;

import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class ScriptCorePreferencePage extends
		AbstractConfigurationBlockPreferencePage {

	protected IPreferenceConfigurationBlock createConfigurationBlock(
			OverlayPreferenceStore store) {
		return new ImprovedAbstractConfigurationBlock(store, this) {
			public Control createControl(Composite parent) {
				return SWTFactory.createComposite(parent, parent.getFont(), 1,
						1, GridData.FILL);
			}
		};
	}

	protected String getHelpId() {
		return null;
	}

	protected void setDescription() {
		setDescription(""); // TODO:
	}

	protected void setPreferenceStore() {
		setPreferenceStore(DLTKUIPlugin.getDefault().getPreferenceStore());
	}
}
