/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.compiler.problem;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;

public class DLTKProblemReporter implements IProblemReporter {
	// private static void clearProblems(IResource res) throws CoreException {
	// res.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
	// }

	protected IMarker reportProblem(IResource res, int line, int start,
			int end, String msg, int severity, int priority)
			throws CoreException {
		IMarker m = res.createMarker(DefaultProblem.MARKER_TYPE_PROBLEM);

		m.setAttribute(IMarker.LINE_NUMBER, line);
		m.setAttribute(IMarker.MESSAGE, msg);
		m.setAttribute(IMarker.SEVERITY, severity);
		m.setAttribute(IMarker.PRIORITY, priority);
		m.setAttribute(IMarker.CHAR_START, start);
		m.setAttribute(IMarker.CHAR_END, end);

		return m;
	}

	private IResource resource;
	private IProblemFactory factory;
	private boolean cleaned = false;

	public IMarker reportProblem(IProblem problem) throws CoreException {
		int severity = IMarker.SEVERITY_INFO;

		if (problem.isError()) {
			severity = IMarker.SEVERITY_ERROR;
		} else if (problem.isWarning()) {
			severity = IMarker.SEVERITY_WARNING;
		}

		return reportProblem(resource, problem.getSourceLineNumber(), problem
				.getSourceStart(), problem.getSourceEnd(),
				problem.getMessage(), severity, IMarker.PRIORITY_NORMAL);
	}

	protected IProblemFactory getProblemFactory() {
		return factory;
	}

	public DLTKProblemReporter(IResource resource, IProblemFactory factory) {
		if (resource == null) {
			throw new NullPointerException(Messages.DLTKProblemReporter_resourceCannotBeNull);
		}

		if (factory == null) {
			throw new NullPointerException(Messages.DLTKProblemReporter_factoryCannotBeNull);
		}

		this.resource = resource;
		this.factory = factory;
	}

	// dummy method
	public void reportTestProblem() {
		IProblem problem = new DefaultProblem("originatingFileName", "message", //$NON-NLS-1$ //$NON-NLS-2$
				0, null, IMarker.SEVERITY_INFO, 0, 1, 0, 0);
		try {
			reportProblem(problem);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public void clearMarkers() {
		if (this.resource != null) {
			try {
				this.resource.deleteMarkers(DefaultProblem.MARKER_TYPE_PROBLEM,
						true, IResource.DEPTH_INFINITE);
			} catch (CoreException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}
			this.cleaned  = true;
		}
	}

	public boolean isMarkersCleaned() {
		return this.cleaned;
	}
}
