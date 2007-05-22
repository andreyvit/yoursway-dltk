/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.launching;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.ruby.core.RubyNature;

public class RubyLaunchConfigurationDelegate extends
		AbstractScriptLaunchConfigurationDelegate {

	public String getLanguageId() {
		return RubyNature.NATURE_ID;
	}

	protected String getEnvironmentLibName() {
        return "RUBYLIB";
	}

	protected String getCharsetInterpreterFlag(String charset) {
		if (charset.equals("UTF-8")) {
			return "-KU";
		} else if (charset.equals("EUC")) {
			return "-KE";
		} else if (charset.equals("SJIS")) {
			return "-KS";
		}

		return "-KA";
    }

	public String getInterpreterArguments(ILaunchConfiguration configuration)
			throws CoreException {
		String args = super.getInterpreterArguments(configuration);

		IProject project = getScriptProject(configuration).getProject();
		IResource resource = project
				.findMember(getMainScriptName(configuration));
		if (resource instanceof IFile) {
			IFile file = (IFile) resource;
			String charset = file.getCharset();
			if (args.indexOf("-K") == -1) {
				args += " " + getCharsetInterpreterFlag(charset) + " ";
			}
		}

		return args;
	}
}