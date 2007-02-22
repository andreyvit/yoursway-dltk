package org.eclipse.dltk.tcl.launching;

import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.tcl.core.TclNature;

public class TclLaunchConfigurationDelegate extends
		AbstractScriptLaunchConfigurationDelegate {

	protected String getEnvironmentLibName() {
		return "TCLLIBPATH";
	}

	public String getInterpreterArguments(ILaunchConfiguration configuration) {
		return "";
	}

	public String getLanguageId() {
		return TclNature.NATURE_ID;
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
