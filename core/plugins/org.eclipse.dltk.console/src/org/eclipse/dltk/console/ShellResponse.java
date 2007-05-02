/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.console;

import java.util.List;

public class ShellResponse {
	private List completions;

	private String description;

	public ShellResponse() {

	}

	public ShellResponse(String description) {
		this.description = description;
	}

	public ShellResponse(List completions) {
		this.completions = completions;
	}

	public List getCompletions() {
		return completions;
	}

	public String getDescription() {
		return this.description;
	}
}
