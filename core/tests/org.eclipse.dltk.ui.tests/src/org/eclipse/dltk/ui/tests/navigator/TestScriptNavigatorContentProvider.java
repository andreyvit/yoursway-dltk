/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.tests.navigator;

import org.eclipse.dltk.internal.ui.navigator.ScriptNavigatorContentProvider;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.preference.IPreferenceStore;


public class TestScriptNavigatorContentProvider extends ScriptNavigatorContentProvider {

	protected IPreferenceStore getPreferenceStore() {
		return DLTKUIPlugin.getDefault().getPreferenceStore();
	}
}
