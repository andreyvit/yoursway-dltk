/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.launching;

import java.util.Map;

public class InterpreterRunnerConfiguration {
	private String scriptToLaunch;

	private String[] interpreterArgs;

	private String[] programArgs;

	private String[] environment;

	private Map interpreterSpecificAttributesMap;

	private String workingDirectory;

	private static final String[] emptyStringArray = new String[0];

	public InterpreterRunnerConfiguration(String scriptToLaunch) {
		if (scriptToLaunch == null) {
			throw new IllegalArgumentException();
		}

		this.scriptToLaunch = scriptToLaunch;
	}

	public void setInterpreterArguments(String[] args) {
		if (args == null) {
			throw new IllegalArgumentException("null arguments!");
		}
		
		this.interpreterArgs = args;
	}

	public String[] getInterpreterArguments() {
		if (interpreterArgs == null) {
			return emptyStringArray;
		}

		return interpreterArgs;
	}

	public void setProgramArguments(String[] args) {
		if (args == null) {
			throw new IllegalArgumentException("null arguments!");
		}
		
		this.programArgs = args;
	}

	public String[] getProgramArguments() {
		if (programArgs == null) {
			return emptyStringArray;
		}
		
		return programArgs;
	}

	public void setEnvironment(String[] environment) {
		this.environment = environment;
	}

	public String getScriptToLaunch() {
		return scriptToLaunch;
	}

	public String[] getEnvironment() {
		return environment;
	}

	public void setWorkingDirectory(String path) {
		workingDirectory = path;
	}

	public String getWorkingDirectory() {
		return workingDirectory;
	}

	public void setInterpreterSpecificAttributesMap(Map map) {
		interpreterSpecificAttributesMap = map;
	}

	public Map getInterpreterSpecificAttributesMap() {
		return interpreterSpecificAttributesMap;
	}
}
