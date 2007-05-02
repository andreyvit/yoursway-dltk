/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.tests.parser.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ruby.ast.RubyArrayExpression;
import org.eclipse.dltk.ruby.tests.Activator;
import org.eclipse.dltk.ruby.tests.parser.AbstractASTTest;
			
public class RubyArrayExpressionTest extends AbstractASTTest {

	public RubyArrayExpressionTest(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	protected Class getExpectedClass () {
		return RubyArrayExpression.class;	
	}
	
	public void test0 () throws Exception {
		String text = "[]";
		checkNode (text, 0, text.length());
	}
	
	public void test1 () throws Exception {
		String text = "[1, 2,\n3,4]";
		RubyArrayExpression node = (RubyArrayExpression)checkNode (text, 0, text.length());
		assertEquals(RubyArrayExpression.ARRAY_BRACKETS, node.getArrayKind());
	}
	
	public void test2 () throws Exception {
		String text = "%w'abc def'";
		RubyArrayExpression node = (RubyArrayExpression) checkNode (text, 0, text.length());
		assertEquals(2, node.getChilds().size());
		assertEquals(RubyArrayExpression.ARRAY_WSMALL, node.getArrayKind());
	}
	
	public void test3 () throws Exception {
		String text = "%W'abc def'";
		RubyArrayExpression node = (RubyArrayExpression) checkNode (text, 0, text.length());
		assertEquals(2, node.getChilds().size());
		assertEquals(RubyArrayExpression.ARRAY_WBIG, node.getArrayKind());
	}
	
}
