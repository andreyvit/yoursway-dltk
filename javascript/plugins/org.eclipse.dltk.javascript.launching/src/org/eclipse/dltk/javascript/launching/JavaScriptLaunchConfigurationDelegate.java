package org.eclipse.dltk.javascript.launching;

import org.eclipse.dltk.javascript.core.JavaScriptNature;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;

public class JavaScriptLaunchConfigurationDelegate extends
		AbstractScriptLaunchConfigurationDelegate {

	public String getLanguageId() {
		return JavaScriptNature.NATURE_ID;
	}
}
