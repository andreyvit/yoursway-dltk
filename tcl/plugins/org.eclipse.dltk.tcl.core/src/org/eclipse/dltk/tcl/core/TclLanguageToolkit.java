/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.core;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.AbstractLanguageToolkit;
import org.eclipse.dltk.core.DLTKContentTypeManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.tcl.core.extensions.ITclLanguageExtension;
import org.eclipse.dltk.tcl.internal.core.TclExtensionManager;

public class TclLanguageToolkit extends AbstractLanguageToolkit {

	private static final String TCL_CONTENT_TYPE = "org.eclipse.dltk.tclContentType";

	private String[] extensions = new String[] { "tcl", "exp", "test" };

	protected static IDLTKLanguageToolkit sInstance = new TclLanguageToolkit();

	public String[] getLanguageFileExtensions() {
		return extensions;
	}

	public TclLanguageToolkit() {
		// Initialize all possible file extensions from all tclExtension's
		List exts = new ArrayList();
		exts.addAll(Arrays.asList(this.extensions));
		ITclLanguageExtension[] languageExtensions = TclExtensionManager
				.getDefault().getExtensions();
		for (int i = 0; i < languageExtensions.length; i++) {
			String[] fileExtensions = languageExtensions[i].getFileExtensions();
			if (fileExtensions != null) {
				exts.addAll(Arrays.asList(fileExtensions));
			}
		}
		this.extensions = (String[]) exts.toArray(new String[exts.size()]);
	}

	public boolean validateSourcePackage(IPath path) {
		File file = new File(path.toOSString());
		if (file != null && file.isDirectory()) {
			String members[] = file.list(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					File f = new File(dir.getPath() + File.separator + name);
					if (f.isDirectory()) {
						File[] listFiles = f.listFiles();
						if (listFiles != null) {
							for (int i = 0; i < listFiles.length; i++) {
								if (listFiles[i].isFile()
										&& DLTKContentTypeManager
												.isValidFileNameForContentType(
														TclLanguageToolkit
																.getDefault(),
														listFiles[i].getName())) {
									return true;
								}
							}
						}
					}
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

	public String getLanguageContentType() {
		return TCL_CONTENT_TYPE;
	}
}
