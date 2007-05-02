/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.tclchecker;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.jface.preference.IPreferenceStore;


public class TclChecker {
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

	private boolean processImpl(ISourceModule module) throws CoreException {

		IResource res = ResourcesPlugin.getWorkspace().getRoot().findMember(
				module.getPath());

		// For example, resource from library
		if (res == null) {
			return false;
		}

		String code = module.getSource();

		TclCheckerMarker.clearMarkers(res);

		try {
			String[] output = TclCheckerHelper.execTclChecker(code, store);
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

	public void check(final List sourceModules, final String workName,
			boolean userJob) {

		if (!canCheck()) {
			throw new IllegalStateException("TclChecker cannot be executed");
		}

		Job job = new Job(workName) {
			protected IStatus run(IProgressMonitor monitor) {
				try {
					monitor.beginTask(workName, sourceModules.size());

					Iterator it = sourceModules.iterator();

					while (it.hasNext()) {
						if (monitor.isCanceled())
							return Status.CANCEL_STATUS;

						ISourceModule module = (ISourceModule) it.next();

						if (!module.exists()) {
							continue;
						}

						processImpl(module);

						monitor.worked(1);
					}
				} catch (CoreException e) {
					return e.getStatus();
				} finally {
					monitor.done();
				}

				return Status.OK_STATUS;
			}
		};

		job.setPriority(Job.SHORT);
		job.setUser(userJob);
		job.schedule();
	}
}
