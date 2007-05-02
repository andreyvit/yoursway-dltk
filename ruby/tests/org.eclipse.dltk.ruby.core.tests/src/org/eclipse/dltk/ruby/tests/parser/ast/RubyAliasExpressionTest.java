/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.tests.parser.ast;

import org.eclipse.dltk.ruby.ast.RubyAliasExpression;
import org.eclipse.dltk.ruby.tests.Activator;
import org.eclipse.dltk.ruby.tests.parser.AbstractASTTest;
			
public class RubyAliasExpressionTest extends AbstractASTTest {

	public RubyAliasExpressionTest(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	protected Class getExpectedClass () {
		return RubyAliasExpression.class;	
	}
	
	public void test0 () throws Exception {
		String text = "alias :foo :doo";
		checkNode (text, 0, text.length());
	}		
	
	
	public void test1 () throws Exception {
		String text = "\n\n\n\nalias foo doo\n\n\n";
		checkNode (text, 4, text.length()-3);
	}
	
	public void test3 () throws Exception {
		String text = "(alias :foo :doo)";
		checkNode (text, 1, text.length()-1);
	}
	
}
