/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.launching;

import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.python.core.PythonNature;

public class PythonLaunchConfigurationDelegate  extends AbstractScriptLaunchConfigurationDelegate {

	//protected String getEnvironmentLibName()
    //{
    //   return "PYTHONLIB";
    //}

    public String getLanguageId() {
		return PythonNature.NATURE_ID;
	}
}