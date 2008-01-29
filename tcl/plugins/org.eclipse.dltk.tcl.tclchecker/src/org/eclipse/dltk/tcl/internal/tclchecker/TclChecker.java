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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceModule;
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

	private boolean processImpl(ISourceModule module, IProgressMonitor monitor,
			OutputStream console) throws CoreException {

		IResource res = module.getResource();

		String code = module.getSource();

		TclCheckerMarker.clearMarkers(res);
		String mpath = res.getLocation().toOSString();

		try {
			String[] output = TclCheckerHelper.execTclCheckerPath(mpath, store,
					console);
			parseProblems(res, code, output, filter);
		} catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR,
					TclCheckerPlugin.PLUGIN_ID, 0,
					"I/O problem with TclChecker", e));
		}

		return true;
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
			String loc = module.getResource().getLocation().toOSString();
			pathToSource.put(loc, module);
			arguments.add(loc);
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cmdLine.add("-@");
		cmdLine.add(patternFile.toOSString());
		Process process;
		BufferedReader input = null;
		String checkingFile = null;

		TclCheckerCodeModel model = null;
		try {
			if (monitor != null) {
				monitor.subTask("Launching tclchecker...");
			}
			process = DebugPlugin.exec((String[]) cmdLine
					.toArray(new String[cmdLine.size()]), null);

			if (monitor != null) {
				monitor.subTask("Checking...");
			}
			// Reading TclChecker output
			// Set lines = new HashSet();

			try {
				input = new BufferedReader(new InputStreamReader(process
						.getInputStream()));

				String line = null;
				while ((line = input.readLine()) != null) {
					// lines.add(line);
					if (console != null) {
						console.write((line + "\n").getBytes());
					}
					TclCheckerProblem problem = TclCheckerHelper.parseProblem(
							line, filter);
					if( line.startsWith(SCANNING) && monitor != null ) {
						monitor.subTask("TclChecker " + line);
						if (monitor != null) {
							monitor.worked(1);
						}
					}
					if( line.startsWith(CHECKING) && monitor != null ) {
						monitor.subTask("TclChecker " + line);
						if (monitor != null) {
							monitor.worked(1);
						}
					}
					if( monitor != null ) {
						if( monitor.isCanceled() ) {
							process.destroy();
							return;
						}
					}
					if (problem != null) {
						String file = problem.getFile();
						ISourceModule module = (ISourceModule) pathToSource
								.get(file);
						if (module != null) {
							if (checkingFile == null
									|| !file.equals(checkingFile)) {
//								if (monitor != null) {
//									monitor.subTask("TclChecker parse problems for:"
//											+ Path.fromOSString(file)
//													.lastSegment());
//								}
								checkingFile = file;
								model = new TclCheckerCodeModel(module
										.getSource());
							}
							if (model != null) {
								TclCheckerProblemDescription desc = problem
										.getDescription();

								int[] bounds = model.getBounds(problem
										.getLineNumber() - 1);

								IResource res = module.getResource();
								if (TclCheckerProblemDescription.isError(desc
										.getCategory())) {
									reportErrorProblem(res, problem, bounds[0],
											bounds[1]);
								} else if (TclCheckerProblemDescription
										.isWarning(desc.getCategory()))
									reportWarningProblem(res, problem,
											bounds[0], bounds[1]);
							}
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
					TclCheckerPlugin.getDefault().getLog().log(
							new Status(IStatus.ERROR,
									TclCheckerPlugin.PLUGIN_ID,
									"Error during tcl_checker execution:\n"
											+ error));
				}
			} catch (IOException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		// For last
		if (sourceModules.size() > 0) {
			if (monitor != null) {
				monitor.worked(1);
			}
		}

		// try {
		// String[] output = TclCheckerHelper.execTclCheckerPath(mpath, store,
		// console);
		// // parseProblems(res, code, output, filter);
		// } catch (IOException e) {
		// throw new CoreException(new Status(IStatus.ERROR,
		// TclCheckerPlugin.PLUGIN_ID, 0,
		// "I/O problem with TclChecker", e));
		// }
	}
}
