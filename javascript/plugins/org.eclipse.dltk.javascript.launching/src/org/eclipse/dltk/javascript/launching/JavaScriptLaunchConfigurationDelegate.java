package org.eclipse.dltk.javascript.launching;

import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.javascript.core.JavaScriptNature;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;

public class JavaScriptLaunchConfigurationDelegate extends
		AbstractScriptLaunchConfigurationDelegate {

	protected String getEnvironmentLibName() {
		return "TCLLIBPATH";
	}

	public String getInterpreterArguments(ILaunchConfiguration configuration) {
		return "";
	}

	public String getLanguageId() {
		return JavaScriptNature.NATURE_ID;
	}

	protected void appendLibraryPathToString(StringBuffer buf, IPath path,
			boolean notLast) {
		String osString = path.toPortableString();
		if (osString.indexOf(" ") != -1) {
			osString = "{" + osString + "}";
		}
		buf.append(osString);
		if (notLast) {
			buf.append(" ");
		}
	}
}
