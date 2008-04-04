/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core.mixin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.PriorityClassDLTKExtensionManager;
import org.eclipse.dltk.core.mixin.IMixinParser;

public class MixinManager {

	private final static String MIXIN_EXTPOINT = DLTKCore.PLUGIN_ID + ".mixin"; //$NON-NLS-1$
	
	private static PriorityClassDLTKExtensionManager mixinInstance = new PriorityClassDLTKExtensionManager(
			MIXIN_EXTPOINT);

	public static IMixinParser getMixinParser(String natureId)
			throws CoreException {
		
		return (IMixinParser) mixinInstance.getObject(natureId);
	}

	public static IMixinParser getMixinParser(IModelElement element)
			throws CoreException {
		return (IMixinParser) mixinInstance.getObject(element);
	}
}
;