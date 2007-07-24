/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.ui.preferences;

import org.eclipse.dltk.ui.preferences.BuildPathsPropertyPage;
import org.eclipse.dltk.ui.util.BusyIndicatorRunnableContext;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class PythonBuildPathPropertyPage extends BuildPathsPropertyPage implements IWorkbenchPropertyPage {
	public PythonBuildPathPropertyPage() {
	}

	protected BuildpathsBlock createBuildPathBlock(IWorkbenchPreferenceContainer pageContainer) {
		return new PythonBuildPathsBlock(new BusyIndicatorRunnableContext(), 
				this, getSettings().getInt(INDEX), false, pageContainer);
	}
}
