package org.eclipse.dltk.compiler.problem;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;


public class DLTKProblemReporter implements IProblemReporter {
//	private static void clearProblems(IResource res) throws CoreException {
//		res.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
//	}

	protected IMarker reportProblem(IResource res, int line, int start,
			int end, String msg, int severity, int priority)
			throws CoreException {
		IMarker m = res.createMarker(IMarker.PROBLEM);

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
	
	public IMarker reportProblem(IProblem problem)
			throws CoreException {
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
	
	protected IProblemFactory getProblemFactory(){
		return factory;
	}
	
	public DLTKProblemReporter(IResource resource, IProblemFactory factory){
		if (resource == null){
			throw new NullPointerException("resource cannot be null");
		}
		
		if (factory == null){
			throw new NullPointerException("factory cannot be null");
		}
				
		this.resource = resource;
		this.factory = factory;
	}
	
	//dummy method
	public void reportTestProblem(){
		IProblem problem = new DefaultProblem("originatingFileName", "message", 0,
				null, IMarker.SEVERITY_INFO, 0,
				1, 0, 0 );
		try {
			reportProblem(problem);
		} catch (CoreException e) {
			e.printStackTrace();
		}		
	}
}
