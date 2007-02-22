/*
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - initial API and implementation
 *
 * $Id: ChangeBundleClasspath.java,v 1.3 2007/02/22 08:40:26 asobolev Exp $
 */
package org.eclipse.dltk.common.build;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version 1.1.0
 */
public class ChangeBundleClasspath {

	private static final String BUNDLE_MANIFEST = "META-INF/MANIFEST.MF";

	private static final String BUILD_PROPERTIES = "build.properties";

	private static final String NEW_BUNDLE_CLASSPATH = ".";

	private File bundlesDirectoryFile;

	private String bundleDirectoryMatchRegex = "org\\.eclipse\\.(emf|emft|eodm|dltk).*";

	private boolean includeTest = false;

	private boolean verbose = false;

	private Pattern bundleClassPathPattern;

	private Pattern bundleDirectoryPattern;

	public static void main(String[] args) {
		ChangeBundleClasspath changeBundleClasspath = new ChangeBundleClasspath();

		for (int i = 0; i < args.length; i++) {
			if ("-bundlesDirectory".equals(args[i])) {
				changeBundleClasspath.setBundlesDirectoryFile(new File(
					args[++i]));
			} else if ("-bundleDirectoryMatchRegex".equals(args[i])) {
				changeBundleClasspath.setBundleDirectoryMatchRegex(args[++i]);
			} else if ("-includeTest".equals(args[i])) {
				changeBundleClasspath.setIncludeTest(true);
			} else if ("-verbose".equals(args[i])) {
				changeBundleClasspath.setVerbose(true);
			}
		}

		try {
			changeBundleClasspath.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setBundlesDirectoryFile(File bundlesDirectoryFile) {
		this.bundlesDirectoryFile = bundlesDirectoryFile;
	}

	public void setBundleDirectoryMatchRegex(String bundleDirectoryMatchRegex) {
		this.bundleDirectoryMatchRegex = bundleDirectoryMatchRegex;
	}

	public void setIncludeTest(boolean includeTest) {
		this.includeTest = includeTest;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public void execute()
			throws Exception {
		if (bundlesDirectoryFile != null && bundlesDirectoryFile.isDirectory()) {
			File[] bundleDirectories = bundlesDirectoryFile.listFiles();
			for (int i = 0; i < bundleDirectories.length; i++) {
				File bundleDirectory = bundleDirectories[i];
				if (bundleDirectory.isDirectory()) {
					if (bundleDirectoryPattern == null) {
						bundleDirectoryPattern = Pattern
							.compile(bundleDirectoryMatchRegex);
					}

					String name = bundleDirectory.getName();
					if (includeTest || name.indexOf(".test.") < 0) {
						if (bundleDirectoryPattern.matcher(name).matches()) {
							adjustBundle(bundleDirectory);
						} else {
							log("The directory "
								+ name
								+ " doesn't match the bundleDirectoryMatchRegex ("
								+ bundleDirectoryMatchRegex + ")");
						}
					} else {
						log("The directory "
							+ name
							+ " was not included because it contains either '.test' or '.doc'");
					}
				}
			}
		} else {
			throw new IllegalArgumentException(
				"bundlesDirectoryFile has to be a valid directory.");
		}
	}

	protected void adjustBundle(File bundleDirectory)
			throws IOException {
		String bundleClasspath = null;
		File bundleManifestFile = new File(bundleDirectory, BUNDLE_MANIFEST)
			.getAbsoluteFile();
		if (bundleManifestFile.isFile()) {
			log("Reading file " + bundleManifestFile.getAbsolutePath());
			if (bundleClassPathPattern == null) {
				// Look for "Bundle-ClassPath:" followed by some characters up to
				//   a comma (if any) on the first line, followed optionally
				//   by a comma and any amount of other stuff up to but not
				//   including the next header (if any)
				bundleClassPathPattern = Pattern.compile(
						"Bundle-ClassPath:(\\s*([^,\n\r]*)(?:,.*?(?=^\\S+:)|,.*)?)",
						java.util.regex.Pattern.MULTILINE | java.util.regex.Pattern.DOTALL);
			}

			String content = BuildUtil.readFile(bundleManifestFile);
			Matcher matcher = bundleClassPathPattern.matcher(content);
			if (matcher.find()) {
				bundleClasspath = matcher.group(2);
				log("bundleClasspath = " + bundleClasspath);
				
				if (!NEW_BUNDLE_CLASSPATH.equals(bundleClasspath)) {
					String newContent = matcher.replaceFirst(
							"Bundle-ClassPath: " + NEW_BUNDLE_CLASSPATH + ", $1");
					
					writeNewContent(bundleManifestFile, content, newContent);
				} else {
					// don't update build.properties if the bundle classpath
					//    already includes '.', because otherwise replaceall
					//    will mess it up because '.' is a wildcard (besides,
					//    it wouldn't be necessary, anyway)
					bundleClasspath = null;
				}
			}
		}

		if (bundleClasspath != null) {
			File buildPropertiesFile = new File(bundleDirectory,
				BUILD_PROPERTIES).getAbsoluteFile();
			adjustFile(buildPropertiesFile, bundleClasspath);
		}
	}

	protected void adjustFile(File file, String bundleClasspath)
			throws IOException {
		if (file.isFile()) {
			log("Reading file " + file.getAbsolutePath());
			String content = BuildUtil.readFile(file);
			String newContent = content.replaceAll(bundleClasspath,
				NEW_BUNDLE_CLASSPATH);
			writeNewContent(file, content, newContent);
		}
	}

	protected void writeNewContent(File file, String oldContent,
			String newContent)
			throws IOException {
		if (!newContent.equals(oldContent)) {
			log("Writing file " + file.getAbsolutePath());
			BuildUtil.writeFile(file, newContent);
		} else {
			log("File " + file.getAbsolutePath()
				+ " didn't change after replacing the bundleClasspath.");
		}
	}

	public void log(String message) {
		if (verbose) {
			System.out.println("- " + message);
		}
	}
}