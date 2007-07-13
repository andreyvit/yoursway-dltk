/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.ruby.basicdebugger.preferences;

import org.eclipse.dltk.ruby.basicdebugger.RubyBasicDebuggerPlugin;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.ImprovedAbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class RubyBasicDebuggerPreferencePage extends
		AbstractConfigurationBlockPreferencePage {
	public static final String PAGE_ID = "org.eclipse.dltk.ruby.preferences.debug.basicdebugger";

	protected IPreferenceConfigurationBlock createConfigurationBlock(
			OverlayPreferenceStore store) {
		return new ImprovedAbstractConfigurationBlock(store, this) {
			public Control createControl(Composite parent) {
				Composite composite = SWTFactory.createComposite(parent, parent
						.getFont(), 1, 1, GridData.FILL);
				SWTFactory.createLabel(composite, "There are no settings now.", 1);
				return composite;
			}
		};
	}

	protected String getHelpId() {
		return null;
	}

	protected void setDescription() {
		setDescription(PreferenceMessages.PreferencesDescription);
	}

	protected void setPreferenceStore() {
		setPreferenceStore(RubyBasicDebuggerPlugin.getDefault()
				.getPreferenceStore());
	}
}
