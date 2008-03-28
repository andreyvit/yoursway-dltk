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

public class RubyParserTests extends AbstractRubyParserTests {
	public RubyParserTests(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	public static Suite suite() {
		return new Suite(RubyParserTests.class);
	}

	public void testJRubyParser001() throws Exception {
		processScript("/workspace/parse/test_call.rb");
	}
	public void testBug180142() throws Exception {
		processScript("/workspace/parse/b180142_cgi.rb");
	}
	public void testBug183493() throws Exception {
		processScript("/workspace/parse/b183493.rb");
	}	
	public void testBug183298() throws Exception {
		processScript("/workspace/parse/b183298.rb");
	}	
	
// public void testJRubyParser002() throws Exception {
// processScript("/workspace/parse/test_iterator.rb");
// }
}
