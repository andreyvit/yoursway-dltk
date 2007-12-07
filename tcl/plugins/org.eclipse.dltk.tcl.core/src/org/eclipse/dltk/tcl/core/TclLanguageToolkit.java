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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

	private static final String[] FILTER_EXTS = { "so", "a", "la", "c", "h", "log" };

	private static final String[] EXTENSIONS = new String[] { "tcl", "exp", "test" };

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
		return EXTENSIONS;
	}

	private IStatus isTclHeadered(File file) {
		try {
			if (checkHeader(file)) {
				return IModelStatus.VERIFIED_OK;
			}
			if (file.length() > bufferLength && checkFooter(file)) {
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
	private final int bufferLength = 2* 1024;
	private byte buf[] = new byte[bufferLength + 1];
	private boolean checkHeader(File file) throws FileNotFoundException,
			IOException {
		FileInputStream reader = null;
		try {
			reader = new FileInputStream(file);
			reader.read(buf);

			String header = new String(buf);

			if (header != null) {
				if (checkBufferForPatterns(header, header_patterns)) {
					return true;
				}
				if( file.length() < bufferLength ) {
					if (checkBufferForPatterns(header, footer_patterns)) {
						return true;
					}
				}
			}
			return false;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
	}

	private boolean checkFooter(File file) throws FileNotFoundException,
			IOException {
		RandomAccessFile raFile = new RandomAccessFile(file, "r");
		try {
			long len = bufferLength;
			long fileSize = raFile.length();
			long offset = fileSize - len;
			if (offset < 0) {
				offset = 0;
			}
			raFile.seek(offset);
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

	private boolean checkBufferForPatterns(CharSequence header, Pattern[] patterns) {
		for (int i = 0; i < patterns.length; i++) {
			Matcher m = patterns[i].matcher(header);
			if (m.find()) {
				return true;
			}
		}
		return false;
	}

	private boolean isSureNotTCLFile(IPath path) {
		String extension = path.getFileExtension();
		String[] exts = FILTER_EXTS;
		for (int i = 0; i < exts.length; ++i) {
			if (extension.equals(exts[i])) {
				return true;
			}
		}
		if (Platform.getOS().equals(Platform.OS_LINUX)) {
			extension = path.lastSegment();
			if (extension.startsWith("lib")
					&& Character.isDigit(extension.charAt(extension.length() - 1))) {
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
