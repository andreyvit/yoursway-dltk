/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.internal.core.util.Messages;

public class TclLanguageToolkit implements IDLTKLanguageToolkit {

	protected static String[] patterns = {
			"#!/usr/bin/tclsh",
			"#!/usr/bin/expect",
			"# ;;; Local Variable: \\*\\*\\*\\s*\r*\n# ;;; mode: tcl \\*\\*\\*\\s*\r*\n# ;;; End: \\*\\*\\*",
			"#!/bin/sh\\s*\r*\n#.*\\\\s*\r*\nexec .*tclsh .*",
			"#!/bin/sh\\s*\r*\n#.*\\\\s*\r*\nexec .*expect .*",
			"#!/bin/sh\\s*\r*\n#.*\\\\s*\r*\nexec .*wish.*" };
	protected static IDLTKLanguageToolkit sInstance = new TclLanguageToolkit();

	public String[] getLanguageFileExtensions() {
		return new String[] { "tcl", "exp", "test" };
	}

	private IStatus isTclHeadered(File file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			int size = Math.min((int) file.length(), 1024 * 1024); // DON'T
																	// READ
																	// FILES
																	// WITH SIZE
																	// MORE THAN
																	// 1 Mb
			char buf[] = new char[size + 1];
			reader.read(buf);

			String header = new String(buf);

			if (header != null) {
				for (int i = 0; i < patterns.length; i++) {
					Pattern p = Pattern.compile(patterns[i], Pattern.MULTILINE);
					Matcher m = p.matcher(header);
					if (m.find()) {
						return IModelStatus.VERIFIED_OK;
					}
				}
			}
		} catch (FileNotFoundException e) {
			return new Status(IStatus.ERROR, TclPlugin.PLUGIN_ID, -1,
					"Can't open file", null);
		} catch (IOException e) {
			return new Status(IStatus.ERROR, TclPlugin.PLUGIN_ID, -1,
					"Can't read file", null);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// Nothing to do
				}
			}
		}

		return new Status(IStatus.ERROR, TclPlugin.PLUGIN_ID, -1,
				"Header not found", null);
	}

	private boolean isSureNotTCLFile(IPath path) {
		String name = path.toOSString();
		String[] exts = { "so", "a", "la", "c", "h" };
		for (int i = 0; i < exts.length; ++i) {
			if (name.endsWith("." + exts[i])) {
				return true;
			}
		}
		if (Platform.getOS().equals(Platform.OS_LINUX)) {
			name = path.lastSegment();
			if (name.startsWith("lib")
					&& Character.isDigit(name.charAt(name.length() - 1))) {
				return true;
			}
		}
		return false;
	}

	public boolean isScriptLikeFileName(String name) {
		String[] exts = getLanguageFileExtensions();
		for (int i = 0; i < exts.length; ++i) {
			if (name.endsWith("." + exts[i])) {
				return true;
			}
		}
		if (name.toLowerCase().equals("tclindex")) {
			return true;
		}
		return false;
	}

	public TclLanguageToolkit() {
	}

	public boolean languageSupportZIPBuildpath() {
		return false;
	}

	public IStatus validateSourceModule(IModelElement parent, String name) {
		return validateSourceModuleName(name);
	}

	public IStatus validateSourceModuleName(String name) {
		if (name == null) {
			return new Status(IStatus.ERROR, TclPlugin.PLUGIN_ID, -1,
					Messages.convention_unit_nullName, null);
		}
		if (!isScriptLikeFileName(name)) {
			return new Status(IStatus.ERROR, TclPlugin.PLUGIN_ID, -1,
					MessageFormat.format(
							Messages.convention_unit_notScriptName,
							new String[] {
									getLanguageFileExtensions().toString(),
									"Tcl" }), null);
		}
		return IModelStatus.VERIFIED_OK;
	}

	public IStatus validateSourceModule(IPath path) {
		IStatus status = validateSourceModuleName(path.lastSegment());

		if (status == IModelStatus.VERIFIED_OK)
			return status;

		if (isSureNotTCLFile(path)) {
			return new Status(IStatus.ERROR, TclPlugin.PLUGIN_ID, -1,
					MessageFormat.format(
							Messages.convention_unit_notScriptName,
							new String[] {
									getLanguageFileExtensions().toString(),
									"Tcl" }), null);
		}

		if (isTclHeadered(path.toFile()) == IModelStatus.VERIFIED_OK)
			return IModelStatus.VERIFIED_OK;

		return status;
	}

	public IStatus validateSourceModule(IResource resource) {
		IStatus status = validateSourceModuleName(resource.getName());

		if (status == IModelStatus.VERIFIED_OK)
			return status;

		if (resource != null) {
			IPath path = resource.getRawLocation();

			if (path == null) {
				return status;
			}

			if (isTclHeadered(path.toFile()) == IModelStatus.VERIFIED_OK) {
				return IModelStatus.VERIFIED_OK;
			}
		}
		return status;
	}

	public boolean validateSourcePackage(IPath path) {
		File file = new File(path.toOSString());
		if (file != null && file.isDirectory()) {
			String members[] = file.list(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					if (name.toLowerCase().equals("pkgindex.tcl")
							|| name.toLowerCase().equals("tclindex")) {
						return true;
					}
					return false;
				}
			});
			if (members != null && members.length > 0) {
				return true;
			}
		}
		return false;
	}

	public String getNatureId() {
		return TclNature.NATURE_ID;
	}

	public static IDLTKLanguageToolkit getDefault() {
		return sInstance;
	}

	public String getDelimeterReplacerString() {
		return "::";
	}

	public String getLanguageName() {
		return "Tcl";
	}
}
