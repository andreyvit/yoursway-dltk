/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.debug.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.dltk.dbgp.IDbgpProperty;

public class RubyComplexVariable extends RubyVariable {
			
	public RubyComplexVariable(RubyStackFrame frame, IDbgpProperty property) {
		super(frame, property);
		// TODO Auto-generated constructor stub
	}

	public IVariable[] getVariables() throws DebugException {
		return new IVariable[0];
	}

	public boolean hasVariables() throws DebugException {		
		return true;
	}	
}
