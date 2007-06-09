/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.core.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.dltk.debug.core.model.IScriptValue;
import org.eclipse.dltk.debug.core.model.IScriptVariable;

public class ScriptValue extends ScriptDebugElement implements IScriptValue {

	private IScriptVariable variable;

	protected ScriptValue(IScriptVariable variable) {		
		this.variable = variable;
	}

	public String getReferenceTypeName() throws DebugException {
		return variable.getTypeString();
	}

	public String getValueString() throws DebugException {
		return variable.getValueString();
	}

	public IVariable[] getVariables() throws DebugException {
		return variable.getChildren();
	}

	public boolean hasVariables() throws DebugException {
		return variable.hasChildren();
	}

	public boolean isAllocated() throws DebugException {
		return true;
	}
	
	public String toString(){
		return variable.getValueString();
	}

	public IDebugTarget getDebugTarget() {
		return variable.getDebugTarget();
	}
	
	public String getReferenceId() {
		return variable.getId();
	}
}