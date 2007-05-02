/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.tests.parser.ast;

import org.eclipse.dltk.ruby.ast.RubyDVarExpression;
import org.eclipse.dltk.ruby.tests.Activator;
import org.eclipse.dltk.ruby.tests.parser.AbstractASTTest;
			
public class RubyDVarExpressionTest extends AbstractASTTest {

	public RubyDVarExpressionTest(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	protected Class getExpectedClass () {
		return RubyDVarExpression.class;	
	}
	
	public void test0 () throws Exception {
		String text = "";
		checkNode (text, 0, text.length());
	}		
	
}
