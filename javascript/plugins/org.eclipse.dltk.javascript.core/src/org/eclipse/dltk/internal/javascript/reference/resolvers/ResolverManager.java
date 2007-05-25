/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.javascript.reference.resolvers;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.core.ISourceModule;

public class ResolverManager {

	static private IResolverFactory[] registredResolvers;

	static {
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
				.getExtensionPoint("org.eclipse.dltk.javascript.core.resolver");
		IExtension[] ext = extensionPoint.getExtensions();
		ArrayList resolvers = new ArrayList();
		for (int a = 0; a < ext.length; a++) {
			IConfigurationElement[] elements = ext[a]
					.getConfigurationElements();
			IConfigurationElement myElement = elements[0];
			try {
				IResolverFactory resolver = (IResolverFactory) myElement
						.createExecutableExtension("class");
				resolvers.add(resolver);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		registredResolvers = new IResolverFactory[resolvers.size()];
		resolvers.toArray(registredResolvers);
	}

	public static ReferenceResolverContext createResolverContext(
			ISourceModule module, Map settings) {
		ReferenceResolverContext cm = new ReferenceResolverContext(module,
				settings);
		for (int a = 0; a < registredResolvers.length; a++) {
			IReferenceResolver create = registredResolvers[a].create();
			if (create instanceof SourceBasedResolver) {
				cm.resolvers.add(0, create);
			} else if (create.canResolve(module)) {
				cm.resolvers.add(create);
			}
		}
		return cm;
	}
}
