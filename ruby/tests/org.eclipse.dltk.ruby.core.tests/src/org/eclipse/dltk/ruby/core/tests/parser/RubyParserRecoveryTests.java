/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.core.tests.parser;

import org.eclipse.dltk.ruby.core.tests.Activator;

public class RubyParserRecoveryTests extends AbstractRubyParserTests {
	public RubyParserRecoveryTests(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	public static Suite suite() {
		return new Suite(RubyParserRecoveryTests.class);
	}

	public void testJRubyParserAt() throws Exception {
		processScript("/workspace/parse/test_at.rb");
	}

	public void testJRubyParserColon() throws Exception {
		processScript("/workspace/parse/test_colon.rb");
	}

	public void testJRubyParserColonUnsafe() throws Exception {
		processScript("/workspace/parse/test_colon_unsafe.rb");
	}

	public void testJRubyParserComma() throws Exception {
		processScript("/workspace/parse/test_comma.rb");
	}

	public void testJRubyParserCommaUnsafe() throws Exception {
		processScript("/workspace/parse/test_comma_unsafe.rb");
	}

	public void testJRubyParserDollar() throws Exception {
		processScript("/workspace/parse/test_dollar.rb");
	}

	public void testJRubyParserDot() throws Exception {
		processScript("/workspace/parse/test_dot.rb");
	}

	public void testJRubyParserHash() throws Exception {
		processScript("/workspace/parse/test_hash.rb");
	}

	public void testJRubyParserHashUnsafe() throws Exception {
		processScript("/workspace/parse/test_hash_unsafe.rb");
	}

	public void testJRubyParserSpaceParenthesis() throws Exception {
		processScript("/workspace/parse/test_space_parenthesis.rb");
	}

	public void testJRubyParserParenthesis() throws Exception {
		processScript("/workspace/parse/test_parenthesis.rb");
	}
}
