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
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.dltk.core.AbstractLanguageToolkit;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.tcl.core.extensions.ITclLanguageExtension;
import org.eclipse.dltk.tcl.internal.core.TclExtensionManager;

public class TclLanguageToolkit extends AbstractLanguageToolkit {

	private static final String TCL_CONTENT_TYPE = "org.eclipse.dltk.tclContentType";

	private static final String[] FILTER_EXTS = { "so", "a", "la", "c", "h",
			"log" };

	private String[] extensions = new String[] { "tcl", "exp", "test" };

	protected static Pattern[] header_patterns = {
			Pattern.compile("#!\\s*/usr/bin/tclsh", Pattern.MULTILINE),
			Pattern.compile("#!\\s*/usr/bin/expect", Pattern.MULTILINE),
			Pattern.compile("#!\\s*/usr/bin/wish", Pattern.MULTILINE),
			Pattern
					.compile(
							"# ;;; Local Variable: \\*\\*\\*\\s*\r*\n# ;;; mode: tcl \\*\\*\\*\\s*\r*\n# ;;; End: \\*\\*\\*",
							Pattern.MULTILINE),
			Pattern
					.compile(
							"#!\\s*/bin/(ba|tc)?sh\\s*\r*\n#.*\\\\s*\r*\nexec .*tclsh .*",
							Pattern.MULTILINE),
			Pattern
					.compile(
							"#!\\s*/bin/(ba|tc)?sh\\s*\r*\n#.*\\\\s*\r*\nexec .*expect .*",
							Pattern.MULTILINE),
			Pattern
					.compile(
							"#!\\s*/bin/(ba|tc)?sh\\s*\r*\n#.*\\\\s*\r*\nexec .*wish.* .*",
							Pattern.MULTILINE),
			Pattern.compile(
					"#!\\s*/bin/(ba|tc)?sh\\s*\r*\n\\s*exec .*wish.* .*",
					Pattern.MULTILINE),
			Pattern.compile(
					"#!\\s*/bin/(ba|tc)?sh\\s*\r*\n\\s*exec .*tclsh.* .*",
					Pattern.MULTILINE),
			Pattern.compile(
					"#!\\s*/bin/(ba|tc)?sh\\s*\r*\n\\s*exec .*expect.* .*",
					Pattern.MULTILINE) };

	protected static Pattern[] footer_patterns = { Pattern
			.compile(
					"# ;;; Local Variable: \\*\\*\\*\\s*\r*\n# ;;; mode: tcl \\*\\*\\*\\s*\r*\n# ;;; End: \\*\\*\\*",
					Pattern.MULTILINE) };

	protected static IDLTKLanguageToolkit sInstance = new TclLanguageToolkit();

	public String[] getLanguageFileExtensions() {
		return extensions;
	}

	private boolean isSureNotTCLFile(IPath path) {
		String extension = path.getFileExtension();
		if (extension != null) {
			String[] exts = FILTER_EXTS;
			for (int i = 0; i < exts.length; ++i) {
				if (extension.equals(exts[i])) {
					return true;
				}
			}
		}
		if (Platform.getOS().equals(Platform.OS_LINUX)) {
			extension = path.lastSegment();
			if (extension.startsWith("lib")
					&& Character.isDigit(extension
							.charAt(extension.length() - 1))) {
				return true;
			}
		}
		return false;
	}

	public boolean isScriptLikeFileName(String name) {
		if (super.isScriptLikeFileName(name)) {
			return true;
		}
		if (name.toLowerCase().equals("tclindex")) {
			return true;
		}
		return false;
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

	public IStatus validateSourceModule(IPath path) {
		IStatus status = validateSourceModuleName(path.lastSegment());

		if (status == IModelStatus.VERIFIED_OK)
			return status;

		if (isSureNotTCLFile(path)) {
			return createNotScriptFileStatus();
		}

		if (checkTclFile(path) == IModelStatus.VERIFIED_OK)
			return IModelStatus.VERIFIED_OK;

		return status;
	}

	public static IStatus checkTclFile(IPath path) {
		return checkPatterns(path.toFile(), header_patterns, footer_patterns);
		// return checkContentType(path);
	}

	public static IStatus checkTclFile(Reader reader) {
		return checkPatterns(reader, header_patterns, footer_patterns);
	}

	public IStatus validateSourceModule(IResource resource) {
		if (resource.getType() == IResource.FILE) {
			IFile file = (IFile) resource;
			IContentDescription description;
			try {
				description = file.getContentDescription();
				if (description != null) {
					System.out.println(file.getName() + ":"
							+ description.getContentType().getName());
					if (description.getContentType().getId().equals(
							TCL_CONTENT_TYPE)) {
						return IModelStatus.VERIFIED_OK;
					}
				}
			} catch (CoreException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}
		}
		IStatus status = validateSourceModuleName(resource.getName());

		if (status == IModelStatus.VERIFIED_OK)
			return status;

		if (resource != null) {
			IPath path = resource.getRawLocation();

			if (path == null) {
				return status;
			}

			if (checkTclFile(path) == IModelStatus.VERIFIED_OK) {
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
					File f = new File(dir.getPath() + File.separator + name);
					if (f.isDirectory()) {
						File[] listFiles = f.listFiles();
						if (listFiles != null) {
							for (int i = 0; i < listFiles.length; i++) {
								if (listFiles[i].isFile()
										&& isScriptLikeFileName(listFiles[i]
												.getName())) {
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

	protected String getCorePluginID() {
		return TclPlugin.PLUGIN_ID;
	}
}
