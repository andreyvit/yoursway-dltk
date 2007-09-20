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

package org.eclipse.dltk.internal.testing.buildpath;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import org.eclipse.dltk.testing.DLTKTestingPlugin;
import org.eclipse.osgi.service.resolver.VersionRange;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;
import org.osgi.framework.Version;

/**
 * 
 */
public class BuildPathSupport {

	public static class JUnitPluginDescription {
		private final String fBundleId;
		private final VersionRange fVersionRange;
		private final boolean fIsOrbitBundle;

		public JUnitPluginDescription(String bundleId, VersionRange versionRange, boolean isOrbitBundle) {
			fBundleId= bundleId;
			fVersionRange= versionRange;
			fIsOrbitBundle= isOrbitBundle;
		}

		public Bundle getBundle() {
			Bundle[] bundles= DLTKTestingPlugin.getDefault().getBundles(fBundleId, null);
			if (bundles != null) {
				for (int i= 0; i < bundles.length; i++) {
					Bundle curr= bundles[i];
					String version= (String) curr.getHeaders().get(Constants.BUNDLE_VERSION);
					try {
						if (fVersionRange.isIncluded(Version.parseVersion(version))) {
							return curr;
						}
					} catch (IllegalArgumentException e) {
						// ignore
					}
				}
			}
			return null;
		}

		public String getBundleId() {
			return fBundleId;
		}

		public boolean isOrbitBundle() {
			return fIsOrbitBundle;
		}
	}
	
	public static IPath getBundleLocation(JUnitPluginDescription pluginDesc) {
		Bundle bundle= pluginDesc.getBundle();
		if (bundle == null)
			return null;

		URL local= null;
		try {
			local= FileLocator.toFileURL(bundle.getEntry("/")); //$NON-NLS-1$
		} catch (IOException e) {
			return null;
		}
		String fullPath= new File(local.getPath()).getAbsolutePath();
		return Path.fromOSString(fullPath);
	}

	public static IPath getSourceLocation(JUnitPluginDescription pluginDesc) {
		Bundle bundle= pluginDesc.getBundle();
		if (bundle == null)
			return null;

		String version= (String) bundle.getHeaders().get(Constants.BUNDLE_VERSION);
		if (version == null) {
			return null;
		}

		Bundle sourceBundle= null;
		if (pluginDesc.isOrbitBundle()) {
			Bundle[] bundles= DLTKTestingPlugin.getDefault().getBundles(pluginDesc.getBundleId() + ".source", version); //$NON-NLS-1$
			if (bundles != null && bundles.length > 0) {
				sourceBundle= bundles[0];
			}
		} else {
			sourceBundle= DLTKTestingPlugin.getDefault().getBundle("org.eclipse.jdt.source"); //$NON-NLS-1$
		}
		if (sourceBundle == null) {
			return null;
		}
		URL local= null;
		try {
			local= FileLocator.toFileURL(sourceBundle.getEntry("/")); //$NON-NLS-1$
		} catch (IOException e) {
			return null;
		}
		String fullPath= new File(local.getPath()).getAbsolutePath() + File.separator + "src" + File.separator + pluginDesc.getBundleId() + "_" + version; //$NON-NLS-1$ //$NON-NLS-2$
		return Path.fromOSString(fullPath);
	}
}
