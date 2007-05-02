/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.tests.navigator;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.ui.navigator.ScriptExplorerLabelProvider;
import org.eclipse.dltk.internal.ui.navigator.ScriptNavigatorLabelProvider;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.ScriptElementImageProvider;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.dltk.ui.viewsupport.AppearanceAwareLabelProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;


public class TestScriptNavigatorLabelProvider extends ScriptNavigatorLabelProvider {
	protected IPreferenceStore getPreferenceStare() {
		return DLTKUIPlugin.getDefault().getPreferenceStore();
	}

	protected ScriptExplorerLabelProvider createLabelProvider() {
		{
			return new ScriptExplorerLabelProvider(
					AppearanceAwareLabelProvider.DEFAULT_TEXTFLAGS | 
					ScriptElementLabels.P_COMPRESSED,
					AppearanceAwareLabelProvider.DEFAULT_IMAGEFLAGS | 
					ScriptElementImageProvider.SMALL_ICONS, 
					fContentProvider,
					getPreferenceStare()) {
				protected ImageDescriptor getSourceModuleIcon(IModelElement element, int renderFlags) {
					if (DLTKCore.DEBUG) {
						System.err.println("label provider returns ghost for source modules...");
					}
					return DLTKPluginImages.DESC_OBJS_GHOST;
				}
			};
		}
	}
}
