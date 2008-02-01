/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.wizards.buildpath;

import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.internal.ui.util.CoreUtility;
import org.eclipse.dltk.internal.ui.wizards.BuildpathAttributeConfiguration;
import org.eclipse.dltk.ui.DLTKUIPlugin;

public class BuildpathAttributeConfigurationDescriptors {

	private static class Descriptor {
	
		private IConfigurationElement fConfigElement;
		private BuildpathAttributeConfiguration fInstance;
	
		private static final String ATT_NAME = "attributeName"; //$NON-NLS-1$
		private static final String ATT_CLASS = "class"; //$NON-NLS-1$	
	
		public Descriptor(IConfigurationElement configElement) throws CoreException {
			fConfigElement = configElement;
			fInstance= null;
	
			String name = configElement.getAttribute(ATT_NAME);
			String pageClassName = configElement.getAttribute(ATT_CLASS);
	
			if (name == null) {
				throw new CoreException(new Status(IStatus.ERROR, DLTKUIPlugin.PLUGIN_ID, 0, "Invalid extension (missing attributeName)", null)); //$NON-NLS-1$
			}
			if (pageClassName == null) {
				throw new CoreException(new Status(IStatus.ERROR, DLTKUIPlugin.PLUGIN_ID, 0, "Invalid extension (missing class name): " + name, null)); //$NON-NLS-1$
			}
		}
	
		public BuildpathAttributeConfiguration getInstance() throws CoreException  {
			if (fInstance == null) {
				Object elem= CoreUtility.createExtension(fConfigElement, ATT_CLASS);
				if (elem instanceof BuildpathAttributeConfiguration) {
					fInstance= (BuildpathAttributeConfiguration) elem;
				} else {
					throw new CoreException(new Status(IStatus.ERROR, DLTKUIPlugin.PLUGIN_ID, 0, "Invalid extension (page not of type IClasspathContainerPage): " + getKey(), null)); //$NON-NLS-1$
				}
			}
			return fInstance;
		}
		
		public String getKey() {
			return fConfigElement.getAttribute(ATT_NAME);
		}
	}
	
	private static final String ATT_EXTENSION = "classpathAttributeConfiguration"; //$NON-NLS-1$
	
	private HashMap fDescriptors;
	
	public BuildpathAttributeConfigurationDescriptors() {
		fDescriptors= null;
	}
	
	private HashMap getDescriptors() {
		if (fDescriptors == null) {
			fDescriptors= readExtensions();
		}
		return fDescriptors;
	}
	
	public boolean containsKey(String attributeKey) {
		return getDescriptors().containsKey(attributeKey);
	}
	
	public BuildpathAttributeConfiguration get(final String attributeKey) {
		final Descriptor desc= (Descriptor) getDescriptors().get(attributeKey);
		if (desc == null) {
			return null;
		}
		final BuildpathAttributeConfiguration[] res= { null };
		SafeRunner.run(new ISafeRunnable() {

			public void handleException(Throwable exception) {
				DLTKUIPlugin.log(exception);
				getDescriptors().remove(attributeKey); // remove from list
			}

			public void run() throws Exception {
				res[0]= desc.getInstance();
			}
		});
		return res[0];
	}
	
	private static HashMap readExtensions() {
		IConfigurationElement[] elements = Platform.getExtensionRegistry().getConfigurationElementsFor(DLTKUIPlugin.PLUGIN_ID, ATT_EXTENSION);
		HashMap descriptors= new HashMap(elements.length * 2);
		for (int i= 0; i < elements.length; i++) {
			try {
				Descriptor curr= new Descriptor(elements[i]);
				descriptors.put(curr.getKey(), curr);
			} catch (CoreException e) {
				DLTKUIPlugin.log(e);
			}
		}
		return descriptors;
	}

}
