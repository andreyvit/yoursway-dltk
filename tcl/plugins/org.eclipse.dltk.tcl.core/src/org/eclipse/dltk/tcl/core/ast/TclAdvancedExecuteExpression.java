/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
package org.eclipse.dltk.tcl.core.ast;

import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.tcl.ast.TclConstants;

public class TclAdvancedExecuteExpression extends Block {
	public TclAdvancedExecuteExpression(int start, int end) {
		this.setStart(start);
		this.setEnd(end);
	}

	public int getKind() {
		return TclConstants.TCL_EXECUTE_EXPRESSION;
	}
}
