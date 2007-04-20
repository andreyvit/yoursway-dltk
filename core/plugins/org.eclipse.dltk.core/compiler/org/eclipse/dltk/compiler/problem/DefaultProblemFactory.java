package org.eclipse.dltk.compiler.problem;

import java.util.Locale;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;


public class DefaultProblemFactory implements IProblemFactory {
	private Locale locale;
	
	public DefaultProblemFactory() {
		this(Locale.getDefault());
	}
	
	public DefaultProblemFactory(Locale loc) {
		this.locale = loc;
		if (Locale.getDefault().equals(loc)){
			// if (DEFAULT_LOCALE_TEMPLATES == null){
				// DEFAULT_LOCALE_TEMPLATES = loadMessageTemplates(loc);
			// }
			// this.messageTemplates = DEFAULT_LOCALE_TEMPLATES;
		} else {
			// this.messageTemplates = loadMessageTemplates(loc);
		}
	}
	
	
	public IProblem createProblem(String originatingFileName, int problemId,
			String[] problemArguments, String[] messageArguments, int severity,
			int startPosition, int endPosition, int lineNumber, int columnNumber ) {

		String message = getLocalizedMessage(problemId, messageArguments);
						
		return new DefaultProblem(null, message, problemId,
				problemArguments, severity, startPosition, endPosition, lineNumber, columnNumber );
	}

	public Locale getLocale() {
		return locale;
	}

	public String getLocalizedMessage(int problemId, String[] messageArguments) {
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < messageArguments.length; i++) {
			b.append(messageArguments[i]);
			if( i != messageArguments.length - 1 ) {
				b.append(" ");
			}
		}
		return b.toString();
	}
	public IProblemReporter createReporter(IResource resource ) {
		try {
			resource.deleteMarkers(IMarker.PROBLEM, true,
					IResource.DEPTH_INFINITE);
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return new DLTKProblemReporter(resource, this);
	}
}
