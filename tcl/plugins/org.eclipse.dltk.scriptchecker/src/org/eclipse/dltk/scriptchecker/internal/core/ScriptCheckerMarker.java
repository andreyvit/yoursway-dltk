package org.eclipse.dltk.scriptchecker.internal.core;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;

public final class ScriptCheckerMarker {

	public static final String PROBLEM_ID = ScriptCheckerPlugin.PLUGIN_ID
			+ ".scriptcheckerproblem";
	
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
				ScriptCheckerMarker.clearMarkers(resource);
			} catch (CoreException e) {
				exception = e;
				return false;
			}
			return true;
		}
	}

	private ScriptCheckerMarker() {

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
