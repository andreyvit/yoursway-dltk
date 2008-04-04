/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.tclchecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.dltk.utils.PlatformFileUtils;
import org.eclipse.jface.preference.IPreferenceStore;

public final class TclCheckerHelper {
	private static final String REGEX = "((?:\\w:)?[^:]+):(\\d+)\\s+\\((\\w+)\\)\\s+(.*)";

	private static final String QUIET_OPTION = "-quiet";

	private static final String W1_OPTION = "-W1";

	private static final String W2_OPTION = "-W2";

	private static final String W3_OPTION = "-W3";

	private static final Object PCX_OPTION = "-pcx";
	private static final Object NO_PCX_OPTION = "-nopcx";

	private static final String SUPPRESS_OPTION = "-suppress";

	private static final Pattern pattern;

	static {
		pattern = Pattern.compile(REGEX);
	}

	private TclCheckerHelper() {
	}

	private static String[] makeTclCheckerCmdLine(IPreferenceStore store) {
		List cmdLine = new ArrayList();

		passOriginalArguments(store, cmdLine);

		return (String[]) cmdLine.toArray(new String[cmdLine.size()]);
	}

	public static String[] makeTclCheckerCmdLine(IPreferenceStore store,
			String path) {
		List cmdLine = new ArrayList();

		passOriginalArguments(store, cmdLine);

		cmdLine.add(path);

		return (String[]) cmdLine.toArray(new String[cmdLine.size()]);
	}

	public static void passOriginalArguments(IPreferenceStore store,
			List cmdLine) {
		File validatorFile = PlatformFileUtils
				.findAbsoluteOrEclipseRelativeFile(new File(store
						.getString(TclCheckerConstants.PREF_PATH)));
		cmdLine.add(validatorFile.getAbsoluteFile().toString());

		// cmdLine.add(QUIET_OPTION);

		int mode = store.getInt(TclCheckerConstants.PREF_MODE);

		if (mode == TclCheckerConstants.MODE_ERRORS) {
			cmdLine.add(W1_OPTION);
		} else if (mode == TclCheckerConstants.MODE_ERRORS_AND_USAGE_WARNINGS) {
			cmdLine.add(W2_OPTION);
		} else if (mode == TclCheckerConstants.MODE_ALL) {
			cmdLine.add(W3_OPTION);
		}

		// Suppress
		List problems = TclCheckerProblemDescription.getProblemIdentifiers();
		Iterator it = problems.iterator();
		while (it.hasNext()) {
			String warningName = (String) it.next();
			if (store.getBoolean(warningName)) {
				cmdLine.add(SUPPRESS_OPTION);
				cmdLine.add(warningName);
			}
		}

		boolean noPcx = store.getBoolean(TclCheckerConstants.PREF_NO_PCX);
		if (noPcx) {
			cmdLine.add(NO_PCX_OPTION);
		} else {
			// pcx paths
			List paths = getPcxPaths(store);
			for (Iterator iterator = paths.iterator(); iterator.hasNext();) {
				String path = (String) iterator.next();
				cmdLine.add(PCX_OPTION);
				cmdLine.add(path);
			}
		}
	}

	public static boolean canExecuteTclChecker(IPreferenceStore store) {
		File file = PlatformFileUtils
				.findAbsoluteOrEclipseRelativeFile(new File(store
						.getString(TclCheckerConstants.PREF_PATH)));

		if (!file.exists()) {
			return false;
		}

		return true;
	}

	public static TclCheckerProblem parseProblem(String problem,
			TclCheckerMessageFilter filter) {
		Matcher matcher = pattern.matcher(problem);

		if (!matcher.find())
			return null;

		String file = matcher.group(1);
		int lineNumber = Integer.parseInt(matcher.group(2));
		String messageID = matcher.group(3);
		String message = matcher.group(4);

		if (filter != null && !filter.accept(messageID)) {
			return null;
		}

		return new TclCheckerProblem(file, lineNumber, messageID, message);
	}

	public static String[] execTclChecker(String code, IPreferenceStore store,
			OutputStream console) throws IOException, CoreException {
		Process process = DebugPlugin.exec(makeTclCheckerCmdLine(store), null);

		// Writing tcl code to TclChecker
		OutputStreamWriter output = null;

		try {
			output = new OutputStreamWriter(process.getOutputStream());
			output.write(code);
		} finally {
			if (output != null) {
				output.close();
			}
		}

		// Reading TclChecker output
		Set lines = new HashSet();

		BufferedReader input = null;

		try {
			input = new BufferedReader(new InputStreamReader(process
					.getInputStream()));

			String line = null;
			while ((line = input.readLine()) != null) {
				lines.add(line);
				if (console != null) {
					console.write((line + "\n").getBytes());
				}
			}
		} finally {
			if (input != null) {
				input.close();
			}
		}

		return (String[]) lines.toArray(new String[lines.size()]);
	}

	public static String[] execTclCheckerPath(String path,
			IPreferenceStore store, OutputStream console) throws IOException,
			CoreException {
		Process process = DebugPlugin.exec(makeTclCheckerCmdLine(store, path),
				null);

		// Reading TclChecker output
		Set lines = new HashSet();

		BufferedReader input = null;

		try {
			input = new BufferedReader(new InputStreamReader(process
					.getInputStream()));

			String line = null;
			while ((line = input.readLine()) != null) {
				lines.add(line);
				if (console != null) {
					console.write((line + "\n").getBytes());
				}
			}
		} finally {
			if (input != null) {
				input.close();
			}
		}

		return (String[]) lines.toArray(new String[lines.size()]);
	}

	public static void passEnvironment(Map map, IPreferenceStore store) {
		// File file = new
		// File(store.getString(TclCheckerConstants.PREF_PCX_PATH));
		// file = PlatformFileUtils.findAbsoluteOrEclipseRelativeFile(file);
		// if (file.exists() && file.isDirectory()) {
		// map.put(TclCheckerConstants.TCL_DEVKIT_LOCAL_VARIABLE, file
		// .getAbsolutePath());
		// }
	}

	public static List getPcxPaths(IPreferenceStore store) {
		String pcxPathsValue = store
				.getString(TclCheckerConstants.PREF_PCX_PATH);
		List values = new ArrayList();
		int start = 0;
		for (int i = 0; i < pcxPathsValue.length(); i++) {
			if (pcxPathsValue.charAt(i) == File.pathSeparatorChar && start < i) {
				String path = pcxPathsValue.substring(start, i);
				if (path.length() > 0) {
					values.add(path);
				}
				start = i + 1;
			}
		}
		if (start < pcxPathsValue.length()) {
			String path = pcxPathsValue
					.substring(start, pcxPathsValue.length());
			if (path.length() > 0) {
				values.add(path);
			}
		}
		return values;
	}

	public static void setPcxPaths(IPreferenceStore store, List paths) {
		StringBuffer buffer = new StringBuffer();
		boolean first = true;
		for (Iterator iterator = paths.iterator(); iterator.hasNext();) {
			String path = (String) iterator.next();
			if (!first) {
				buffer.append(File.pathSeparator);
			} else {
				first = false;
			}
			buffer.append(path);
		}
		store.setValue(TclCheckerConstants.PREF_PCX_PATH, buffer.toString());
	}
}
