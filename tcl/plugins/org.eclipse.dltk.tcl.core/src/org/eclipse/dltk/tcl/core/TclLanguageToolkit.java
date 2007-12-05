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
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.AbstractLanguageToolkit;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelStatus;

public class TclLanguageToolkit extends AbstractLanguageToolkit {

	protected static String[] header_patterns = {
			"#!\\s*/usr/bin/tclsh",
			"#!\\s*/usr/bin/expect",
			"#!\\s*/usr/bin/wish",
			"# ;;; Local Variable: \\*\\*\\*\\s*\r*\n# ;;; mode: tcl \\*\\*\\*\\s*\r*\n# ;;; End: \\*\\*\\*",
			"#!\\s*/bin/(ba|tc)?sh\\s*\r*\n#.*\\\\s*\r*\nexec .*tclsh .*",
			"#!\\s*/bin/(ba|tc)?sh\\s*\r*\n#.*\\\\s*\r*\nexec .*expect .*",
			"#!\\s*/bin/(ba|tc)?sh\\s*\r*\n#.*\\\\s*\r*\nexec .*wish.* .*",
			"#!\\s*/bin/(ba|tc)?sh\\s*\r*\n\\s*exec .*wish.* .*",
			"#!\\s*/bin/(ba|tc)?sh\\s*\r*\n\\s*exec .*tclsh.* .*",
			"#!\\s*/bin/(ba|tc)?sh\\s*\r*\n\\s*exec .*expect.* .*" };
	protected static String[] footer_patterns = { "# ;;; Local Variable: \\*\\*\\*\\s*\r*\n# ;;; mode: tcl \\*\\*\\*\\s*\r*\n# ;;; End: \\*\\*\\*" };

	protected static IDLTKLanguageToolkit sInstance = new TclLanguageToolkit();

	public String[] getLanguageFileExtensions() {
		return new String[] { "tcl", "exp", "test" };
	}

	private IStatus isTclHeadered(File file) {
		try {
			if (checkHeader(file)) {
				return IModelStatus.VERIFIED_OK;
			}
			if (checkFooter(file)) {
				return IModelStatus.VERIFIED_OK;
			}
		} catch (FileNotFoundException e) {
			return new Status(IStatus.ERROR, TclPlugin.PLUGIN_ID, -1,
					"Can't open file", null);
		} catch (IOException e) {
			return new Status(IStatus.ERROR, TclPlugin.PLUGIN_ID, -1,
					"Can't read file", null);
		}

		return new Status(IStatus.ERROR, TclPlugin.PLUGIN_ID, -1,
				"Header not found", null);
	}

	private boolean checkHeader(File file) throws FileNotFoundException,
			IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			int size = Math.min((int) file.length(), 2 * 1024); // DON'T
			// read not more than 2k of file header.
			char buf[] = new char[size + 1];
			reader.read(buf);

			String header = new String(buf);

			if (header != null) {
				if (checkBufferForPatterns(header, header_patterns)) {
					return true;
				}
			}
			return false;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// Nothing to do
				}
			}
		}
	}

	private boolean checkFooter(File file) throws FileNotFoundException,
			IOException {
		RandomAccessFile raFile = new RandomAccessFile(file, "r");
		try {
			long len = 2 * 1024;
			long fileSize = raFile.length();
			long offset = fileSize - len;
			if (offset < 0) {
				offset = 0;
			}
			raFile.seek(offset);
			byte buf[] = new byte[(int) (len + 1)];
			int code = raFile.read(buf);
			if (code != -1) {
				String content = new String(buf, 0, code);
				if (checkBufferForPatterns(content, footer_patterns)) {
					return true;
				}
			}
			return false;
		} finally {
			raFile.close();
		}

	}

	private boolean checkBufferForPatterns(String header, String[] patterns) {
		for (int i = 0; i < patterns.length; i++) {
			Pattern p = Pattern.compile(patterns[i], Pattern.MULTILINE);
			Matcher m = p.matcher(header);
			if (m.find()) {
				return true;
			}
		}
		return false;
	}

	private boolean isSureNotTCLFile(IPath path) {
		String name = path.toOSString();
		String[] exts = { "so", "a", "la", "c", "h", "log" };
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
		if (super.isScriptLikeFileName(name)) {
			return true;
		}
		if (name.toLowerCase().equals("tclindex")) {
			return true;
		}
		return false;
	}

	public TclLanguageToolkit() {
	}

	public IStatus validateSourceModule(IPath path) {
		IStatus status = validateSourceModuleName(path.lastSegment());

		if (status == IModelStatus.VERIFIED_OK)
			return status;

		if (isSureNotTCLFile(path)) {
			return createNotScriptFileStatus();
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
