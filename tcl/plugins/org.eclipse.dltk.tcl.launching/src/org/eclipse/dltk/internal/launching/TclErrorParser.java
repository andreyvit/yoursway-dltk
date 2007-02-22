package org.eclipse.dltk.internal.launching;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.IStreamListener;
import org.eclipse.debug.core.model.IStreamMonitor;
import org.eclipse.dltk.core.IModelMarker;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;


public class TclErrorParser implements IStreamListener {
	
	private IProject project;
	private int position = 0;
	
	private class ErrorPattern {
		String fRegex;
		Pattern pattern;
		String groups[];		
		IRegion region;
		
		/**
		 * Creates error pattern based on given regexp.
		 * @param regex regular expression of the error
		 * @param flags flags for Pattern.compile(regex, flags)
		 */
		public ErrorPattern (String regex, int flags) {
			fRegex = regex;
			pattern = Pattern.compile(regex, flags);
		}
		
		/**
		 * Returns region of error inside text
		 * or null if there is no error
		 * @param text
		 * @return
		 */
		public IRegion matches (String text) {
			region = null;
			Matcher m = pattern.matcher(text);
			if (m.find()) {
				int start = m.start();
				int end = m.end();
				groups = new String[m.groupCount() + 1];
				for (int i = 0; i <= m.groupCount(); i++) {
					groups[i] = m.group(i);
				}
				region = new Region(start, end - start); 
				return region;
			}
			return null; 
		}
		
		public String getGroup(int n) {
			return groups[n];
		}
		
		public IRegion getRegion () {
			return region;
		}
		

	}
	
	private final ErrorPattern[] patterns = {
			new ErrorPattern("invalid command name \".*\"$.*$.*$.*\\(file \"(.*)\" line (.*)\\)$", 
					Pattern.MULTILINE | Pattern.DOTALL)
	};
	
	public TclErrorParser (IProject p) {
		if (p == null)
			throw new IllegalArgumentException();
		project = p;
	}
	
	/**
	 * Record a new marker denoting a runtime problem
	 */
	void createRuntimeProblemMarker(String msg) {
		IMarker marker = null;
		int severity = IMarker.SEVERITY_ERROR;

		try {
			marker = this.project.createMarker(IModelMarker.SCRIPT_RUNTIME_PROBLEM_MARKER);
			marker.setAttributes(new String[] {
					IMarker.MESSAGE, IMarker.SEVERITY, IMarker.LOCATION, 
			}, new Object[] {
					msg, new Integer(severity), "Runtime error"					
			});
		} catch (CoreException e) {
			// could not create marker: cannot do much
			e.printStackTrace();
		}
	}
	
	private void reportProblem (ErrorPattern pat) {
		
	}

	public void streamAppended(String text, IStreamMonitor monitor) {
		String remainer = (monitor.getContents() + text).substring(position);
		boolean found = true;
		while (found) { //try patterns while something founds
			found = false;
			for (int i = 0; i < patterns.length; i++) {
				ErrorPattern p = patterns[i];
				IRegion reg = p.matches(remainer);
				if (reg != null) {
					reportProblem(p);
					remainer = remainer.substring(reg.getOffset() + reg.getLength());
					position = reg.getOffset() + reg.getLength();
					found = true;
					break;
				}
			}
		}
	}
}
