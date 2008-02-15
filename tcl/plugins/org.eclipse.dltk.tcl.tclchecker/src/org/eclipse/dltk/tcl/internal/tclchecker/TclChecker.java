/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.tclchecker;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.jface.preference.IPreferenceStore;

public class TclChecker {
	private static final String CHECKING = "checking:";

	private static final String SCANNING = "scanning:";

	private static class TclCheckerCodeModel {
		private String[] codeLines;

		private int[] codeLineLengths;

		public TclCheckerCodeModel(String code) {
			this.codeLines = code.split("\n");
			int count = this.codeLines.length;

			this.codeLineLengths = new int[count];

			int sum = 0;
			for (int i = 0; i < count; ++i) {
				this.codeLineLengths[i] = sum;
				sum += this.codeLines[i].length() + 1;
			}
		}

		public int[] getBounds(int lineNumber) {
			String codeLine = codeLines[lineNumber];
			String trimmedCodeLine = codeLine.trim();

			int start = codeLineLengths[lineNumber]
					+ codeLine.indexOf(trimmedCodeLine);
			int end = start + trimmedCodeLine.length();

			return new int[] { start, end };
		}
	}

	protected static IMarker reportErrorProblem(IResource resource,
			TclCheckerProblem problem, int start, int end) throws CoreException {

		return TclCheckerMarker.setMarker(resource, problem.getLineNumber(),
				start, end, problem.getDescription().getMessage(),
				IMarker.SEVERITY_ERROR, IMarker.PRIORITY_NORMAL);
	}

	protected static IMarker reportWarningProblem(IResource resource,
			TclCheckerProblem problem, int start, int end) throws CoreException {

		return TclCheckerMarker.setMarker(resource, problem.getLineNumber(),
				start, end, problem.getDescription().getMessage(),
				IMarker.SEVERITY_WARNING, IMarker.PRIORITY_NORMAL);
	}

	private ISourceModule checkingModule;
	private IPreferenceStore store;

	private TclCheckerMessageFilter filter;

	private static void parseProblems(IResource res, String code,
			String[] output, TclCheckerMessageFilter filter)
			throws CoreException {
		TclCheckerCodeModel model = new TclCheckerCodeModel(code);

		for (int i = 0; i < output.length; ++i) {
			TclCheckerProblem problem = TclCheckerHelper.parseProblem(
					output[i], filter);

			if (problem == null) {
				continue;
			}

			TclCheckerProblemDescription desc = problem.getDescription();

			int[] bounds = model.getBounds(problem.getLineNumber() - 1);

			if (TclCheckerProblemDescription.isError(desc.getCategory()))
				reportErrorProblem(res, problem, bounds[0], bounds[1]);
			else if (TclCheckerProblemDescription.isWarning(desc.getCategory()))
				reportWarningProblem(res, problem, bounds[0], bounds[1]);
		}
	}

	public TclChecker(IPreferenceStore store) {
		if (store == null) {
			throw new NullPointerException("store cannot be null");
		}

		this.store = store;
		this.filter = new StaticTclCheckerMessageFilter();
	}

	public boolean canCheck() {
		return TclCheckerHelper.canExecuteTclChecker(store);
	}

	public void check(final List sourceModules, IProgressMonitor monitor,
			OutputStream console) {
		if (!canCheck()) {
			throw new IllegalStateException("TclChecker cannot be executed");
		}

		List arguments = new ArrayList();
		Map pathToSource = new HashMap();
		for (Iterator iterator = sourceModules.iterator(); iterator.hasNext();) {
			ISourceModule module = (ISourceModule) iterator.next();
			try {
				char[] sourceAsCharArray = module.getSourceAsCharArray();
				if( sourceAsCharArray.length == 0 ) {
					continue;
				}
			} catch (ModelException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}
			String loc = module.getResource().getLocation().toOSString();
			pathToSource.put(loc, module);
			arguments.add(loc);
		}
		if( arguments.size() == 0 ) {
			if(monitor != null) {
				monitor.done();
			}
			return;
		}
		List cmdLine = new ArrayList();
		TclCheckerHelper.passOriginalArguments(store, cmdLine);
		IPath stateLocation = TclCheckerPlugin.getDefault().getStateLocation();
		IPath patternFile = stateLocation.append("pattern.txt");
		try {
			BufferedOutputStream locs = new BufferedOutputStream(
					new FileOutputStream(patternFile.toFile(), false));
			for (Iterator arg = arguments.iterator(); arg.hasNext();) {
				String path = (String) arg.next();
				locs.write((path + "\n").getBytes());
			}
			locs.close();
		} catch (FileNotFoundException e1) {
			if (DLTKCore.DEBUG) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		cmdLine.add("-@");
		cmdLine.add(patternFile.toOSString());
		Process process;
		BufferedReader input = null;
		String checkingFile = null;
		int scanned = 0;
		int checked = 0;

		TclCheckerCodeModel model = null;
		if (monitor == null)
			monitor = new NullProgressMonitor();

		monitor.beginTask("Executing TclChecker...",
				sourceModules.size() * 2 + 1);

		try {
			monitor.subTask("Launching TclChecker...");
			process = DebugPlugin.exec((String[]) cmdLine
					.toArray(new String[cmdLine.size()]), null);

			monitor.worked(1);

			input = new BufferedReader(new InputStreamReader(process
					.getInputStream()));

			String line = null;
			while ((line = input.readLine()) != null) {
				// lines.add(line);
				if (console != null) {
					console.write((line + "\n").getBytes());
				}
				TclCheckerProblem problem = TclCheckerHelper.parseProblem(line,
						filter);
				if (line.startsWith(SCANNING)) {
					String fileName = line.substring(SCANNING.length() + 1)
							.trim();
					fileName = Path.fromOSString(fileName).lastSegment();
					if (monitor.isCanceled()) {
						process.destroy();
						return;
					}
					monitor
							.subTask(MessageFormat
									.format(
											"TclChecker scanning \"{0}\" ({1} to scan)...",
											new Object[] {
													fileName,
													new Integer(sourceModules
															.size()
															- scanned) }));
					monitor.worked(1);
					scanned++;
				}
				if (line.startsWith(CHECKING)) {
					String fileName = line.substring(CHECKING.length() + 1)
							.trim();
					checkingFile = fileName;
					checkingModule = (ISourceModule) pathToSource
							.get(checkingFile);
					if (checkingModule == null) {
						// Lets search for fileName. If it is present one
						// time, associate with it.
						Set paths = pathToSource.keySet();
						String fullPath = null;
						for (Iterator iterator = paths.iterator(); iterator
								.hasNext();) {
							String p = (String) iterator.next();
							if (p.endsWith(fileName)) {
								if (fullPath != null) {
									fullPath = null;
									break;
								}
								fullPath = p;
							}
						}
						if (fullPath != null) {
							checkingModule = (ISourceModule) pathToSource
									.get(fullPath);
						}
					}
					if (checkingModule != null) {
						model = new TclCheckerCodeModel(checkingModule
								.getSource());
					} else {
						model = null;
					}

					fileName = Path.fromOSString(fileName).lastSegment();
					if (monitor.isCanceled()) {
						process.destroy();
						return;
					}
					monitor
							.subTask(MessageFormat
									.format(
											"TclChecker checking  \"{0}\" ({1} to check)...",
											new Object[] {
													fileName,
													new Integer(sourceModules
															.size()
															- checked) }));
					monitor.worked(1);
					checked++;
				}
				if (problem != null && checkingFile != null
						&& checkingModule != null) {
					if (model != null) {
						TclCheckerProblemDescription desc = problem
								.getDescription();

						int[] bounds = model
								.getBounds(problem.getLineNumber() - 1);

						IResource res = checkingModule.getResource();
						if (TclCheckerProblemDescription.isError(desc
								.getCategory())) {
							reportErrorProblem(res, problem, bounds[0],
									bounds[1]);
						} else if (TclCheckerProblemDescription.isWarning(desc
								.getCategory()))
							reportWarningProblem(res, problem, bounds[0],
									bounds[1]);
					}
				}
			}
			StringBuffer errorMessage = new StringBuffer();
			// We need also read errors.
			input = new BufferedReader(new InputStreamReader(process
					.getErrorStream()));

			line = null;
			while ((line = input.readLine()) != null) {
				// lines.add(line);
				if (console != null) {
					console.write((line + "\n").getBytes());
				}
				errorMessage.append(line).append("\n");
			}
			String error = errorMessage.toString();
			if (error.length() > 0) {
				TclCheckerPlugin.getDefault().getLog()
						.log(
								new Status(IStatus.ERROR,
										TclCheckerPlugin.PLUGIN_ID,
										"Error during tcl_checker execution:\n"
												+ error));
			}
		} catch (Exception e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		} finally {
			monitor.done();
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
