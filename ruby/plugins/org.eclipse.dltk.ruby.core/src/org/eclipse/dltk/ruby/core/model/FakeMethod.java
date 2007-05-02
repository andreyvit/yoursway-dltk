/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.core.model;

import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.internal.core.SourceMethod;

public class FakeMethod extends SourceMethod {

	private static final String[] NO_STRINGS = new String[0];

	private String receiver;
	
	private String[] parameters = NO_STRINGS;
	
	private String[] parameterInitializers = NO_STRINGS;
	
	private int flags;
	
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public FakeMethod(ModelElement parent, String name) {
		super(parent, name);
	}

	public IDLTKProject getScriptProject() {		
		return parent.getScriptProject();
	}

	public int getFlags() {
		return flags;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	public void setParameterInitializers(String[] parameterInitializers) {
		this.parameterInitializers = parameterInitializers;
	}

	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}

	public String[] getParameterInitializers() throws ModelException {
		return parameterInitializers;
	}

	public String[] getParameters() throws ModelException {
		return parameters;
	}
	

}
