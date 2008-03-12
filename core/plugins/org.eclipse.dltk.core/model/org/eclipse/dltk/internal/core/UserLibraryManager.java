/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IBuildpathContainer;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.util.Util;
import org.osgi.service.prefs.BackingStoreException;

/**
 * 
 */
public class UserLibraryManager {

	public final static String BP_USERLIBRARY_PREFERENCES_PREFIX = DLTKCore.PLUGIN_ID
			+ ".userLibrary."; //$NON-NLS-1$

	private Map userLibraries;

	public UserLibraryManager() {
		initialize();
	}

	/*
	 * Gets the library for a given name or <code>null</code> if no such
	 * library exists.
	 */
	public synchronized UserLibrary getUserLibrary(String libName,
			IDLTKLanguageToolkit toolkit) {
		return (UserLibrary) this.userLibraries.get(makeLibraryName(libName,
				toolkit));
	}

	public static String makeLibraryName(String libName, IDLTKLanguageToolkit toolkit) {
		if( toolkit == null ) {
			return "#" + libName; //$NON-NLS-1$
		}
		return toolkit.getNatureId() + "#" + libName; //$NON-NLS-1$
	}

	private String getLibraryName(String key) {
		int pos = key.indexOf("#"); //$NON-NLS-1$
		if (pos != -1) {
			return key.substring(pos + 1);
		}
		return key;
	}

	private IDLTKLanguageToolkit getToolkitFromKey(String key) {
		int pos = key.indexOf("#"); //$NON-NLS-1$
		if (pos != -1) {
			String nature = key.substring(0, pos);
			try {
				return DLTKLanguageManager.getLanguageToolkit(nature);
			} catch (CoreException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/*
	 * Returns the names of all defined user libraries. The corresponding
	 * classpath container path is the name appended to the CONTAINER_ID.
	 */
	public synchronized String[] getUserLibraryNames(
			IDLTKLanguageToolkit toolkit) {
		Set set = this.userLibraries.keySet();
		Set result = new HashSet();
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int pos = key.indexOf("#"); //$NON-NLS-1$
			if (pos != -1) {
				String nature = key.substring(0, pos);
				if (toolkit.getNatureId().equals(nature)) {
					result.add(getLibraryName(key));
				}
			}
		}

		return (String[]) result.toArray(new String[result.size()]);
	}

	private void initialize() {
		this.userLibraries = new HashMap();
		IEclipsePreferences instancePreferences = ModelManager
				.getModelManager().getInstancePreferences();
		String[] propertyNames;
		try {
			propertyNames = instancePreferences.keys();
		} catch (BackingStoreException e) {
			Util.log(e, "Exception while initializing user libraries"); //$NON-NLS-1$
			return;
		}

		boolean preferencesNeedFlush = false;
		for (int i = 0, length = propertyNames.length; i < length; i++) {
			String propertyName = propertyNames[i];
			if (propertyName.startsWith(BP_USERLIBRARY_PREFERENCES_PREFIX)) {
				String propertyValue = instancePreferences.get(propertyName,
						null);
				if (propertyValue != null) {
					String libName = propertyName
							.substring(BP_USERLIBRARY_PREFERENCES_PREFIX
									.length());
					StringReader reader = new StringReader(propertyValue);
					UserLibrary library;
					try {
						library = UserLibrary.createFromString(reader);
					} catch (IOException e) {
						Util
								.log(
										e,
										"Exception while initializing user library " + libName); //$NON-NLS-1$
						instancePreferences.remove(propertyName);
						preferencesNeedFlush = true;
						continue;
					}
					this.userLibraries.put(libName, library);
				}
			}
		}
		if (preferencesNeedFlush) {
			try {
				instancePreferences.flush();
			} catch (BackingStoreException e) {
				Util.log(e, "Exception while flusing instance preferences"); //$NON-NLS-1$
			}
		}
	}

	public void updateUserLibrary(String libName, String encodedUserLibrary) {
		try {
			// find affected projects
			IPath containerPath = new Path(DLTKCore.USER_LIBRARY_CONTAINER_ID)
					.append(getLibraryName(libName));
			IScriptProject[] allJavaProjects = DLTKCore.create(
					ResourcesPlugin.getWorkspace().getRoot())
					.getScriptProjects();
			ArrayList affectedProjects = new ArrayList();
			for (int i = 0; i < allJavaProjects.length; i++) {
				IScriptProject javaProject = allJavaProjects[i];
				IBuildpathEntry[] entries = javaProject.getRawBuildpath();
				for (int j = 0; j < entries.length; j++) {
					IBuildpathEntry entry = entries[j];
					if (entry.getEntryKind() == IBuildpathEntry.BPE_CONTAINER) {
						if (containerPath.equals(entry.getPath())) {
							affectedProjects.add(javaProject);
							break;
						}
					}
				}
			}

			// decode user library
			UserLibrary userLibrary = encodedUserLibrary == null ? null
					: UserLibrary.createFromString(new StringReader(
							encodedUserLibrary));

			// update user libraries map
			if (userLibrary != null) {
				this.userLibraries.put(libName, userLibrary);
			} else {
				this.userLibraries.remove(libName);
			}

			// update affected projects
			int length = affectedProjects.size();
			if (length == 0)
				return;
			IScriptProject[] projects = new IScriptProject[length];
			affectedProjects.toArray(projects);
			IBuildpathContainer[] containers = new IBuildpathContainer[length];
			IDLTKLanguageToolkit toolkit = getToolkitFromKey(libName);
			if (userLibrary != null && toolkit != null) {
				UserLibraryBuildpathContainer container = new UserLibraryBuildpathContainer(
						getLibraryName(libName), toolkit);
				for (int i = 0; i < length; i++) {
					containers[i] = container;
				}
			}
			DLTKCore.setBuildpathContainer(containerPath, projects, containers,
					null);
		} catch (IOException e) {
			Util.log(e,
					"Exception while decoding user library '" + libName + "'."); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (ModelException e) {
			Util.log(e,
					"Exception while setting user library '" + libName + "'."); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	public synchronized void removeUserLibrary(String libName,
			IDLTKLanguageToolkit toolkit) {
		IEclipsePreferences instancePreferences = ModelManager
				.getModelManager().getInstancePreferences();
		String propertyName = BP_USERLIBRARY_PREFERENCES_PREFIX
				+ makeLibraryName(libName, toolkit);
		instancePreferences.remove(propertyName);
		try {
			instancePreferences.flush();
		} catch (BackingStoreException e) {
			Util.log(e, "Exception while removing user library " + libName); //$NON-NLS-1$
		}
		// this.userLibraries was updated during the PreferenceChangeEvent (see
		// preferenceChange(...))
	}

	public synchronized void setUserLibrary(String libName,
			IBuildpathEntry[] entries, boolean isSystemLibrary,
			IDLTKLanguageToolkit toolkit) {
		IEclipsePreferences instancePreferences = ModelManager
				.getModelManager().getInstancePreferences();
		String propertyName = BP_USERLIBRARY_PREFERENCES_PREFIX
				+ makeLibraryName(libName, toolkit);
		try {
			String propertyValue = UserLibrary.serialize(entries,
					isSystemLibrary);
			instancePreferences.put(propertyName, propertyValue); // sends out
			// a
			// PreferenceChangeEvent
			// (see
			// preferenceChange(...))
		} catch (IOException e) {
			Util.log(e, "Exception while serializing user library " + libName); //$NON-NLS-1$
			return;
		}
		try {
			instancePreferences.flush();
		} catch (BackingStoreException e) {
			Util.log(e, "Exception while saving user library " + libName); //$NON-NLS-1$
		}
		// this.userLibraries was updated during the PreferenceChangeEvent (see
		// preferenceChange(...))
	}

}
