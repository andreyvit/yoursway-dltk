/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core.mixin;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.mixin.IMixinParser;


public class MixinManager {

	private final static String LANGUAGE_EXTPOINT = DLTKCore.PLUGIN_ID
			+ ".mixin";

	private final static String NATURE_ATTR = "nature";

	private static Map parsers;

	private static void initialize() {
		if (parsers != null) {
			return;
		}

		parsers = new HashMap(5);
		IConfigurationElement[] cfg = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(LANGUAGE_EXTPOINT);

		for (int i = 0; i < cfg.length; i++) {
			String nature = cfg[i].getAttribute(NATURE_ATTR);
			if (parsers.get(nature) != null)
				System.err.println("TODO log redeclaration");
			parsers.put(nature, cfg[i]);
		}
	}

	private static String findScriptNature(IProject project)
			throws CoreException {
		initialize();

		String[] natureIds = project.getDescription().getNatureIds();
		for (int i = 0; i < natureIds.length; i++) {
			String natureId = natureIds[i];

			if (parsers.containsKey(natureId)) {
				return natureId;
			}
		}

		return null;
	}

	public static IMixinParser getMixinParser(String natureId)
			throws CoreException {
		initialize();

		Object ext = parsers.get(natureId);

		if (ext != null) {
			if (ext instanceof IMixinParser)
				return (IMixinParser) ext;

			IConfigurationElement cfg = (IConfigurationElement) ext;
			IMixinParser toolkit = (IMixinParser) cfg
					.createExecutableExtension("class");
			parsers.put(natureId, toolkit);
			return toolkit;
		}
		return null;
	}


	public static IMixinParser getMixinParser(IModelElement element)
			throws CoreException {
		IProject project = element.getScriptProject().getProject();
		String natureId = findScriptNature(project);
		if (natureId != null) {
			IMixinParser toolkit = getMixinParser(natureId);
			if (toolkit != null) {
				return toolkit;
			}
		}
		return null;
	}
}
