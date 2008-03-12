/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.validators.internal.core.externalchecker;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.validators.internal.core.ValidatorsCore;

public final class ExternalCheckerMarker {

	public static final String PROBLEM_ID = ValidatorsCore.PLUGIN_ID + ".externalcheckerproblem"; //$NON-NLS-1$
	
	private static class ProblemRemover implements IResourceVisitor {

		private CoreException exception;

		public ProblemRemover() {
		}

		public boolean hasErrors() {
			return exception != null;
		}

		public CoreException getException() {
			return exception;
		}

		public boolean visit(IResource resource) {
			try {
				ExternalCheckerMarker.clearMarkers(resource);
			} catch (CoreException e) {
				exception = e;
				return false;
			}
			return true;
		}
	}

	private ExternalCheckerMarker() {

	}

	public static IMarker setMarker(IResource res, int line, int start,
			int end, String msg, int severity, int priority)
			throws CoreException {
		IMarker m = res.createMarker(PROBLEM_ID);

		m.setAttribute(IMarker.LINE_NUMBER, line);
		m.setAttribute(IMarker.MESSAGE, msg);
		m.setAttribute(IMarker.SEVERITY, severity);
		m.setAttribute(IMarker.PRIORITY, priority);
		m.setAttribute(IMarker.CHAR_START, start);
		m.setAttribute(IMarker.CHAR_END, end);

		return m;
	}

	public static void clearMarkers(IResource resource) throws CoreException {
		resource.deleteMarkers(PROBLEM_ID, true, IResource.DEPTH_INFINITE);
	}
	
	public static void clearMarkers(IProject project) throws CoreException {
		ProblemRemover visitor = new ProblemRemover();
		project.accept(visitor);
		if (visitor.hasErrors()) {
			throw visitor.getException();
		}
	}
}