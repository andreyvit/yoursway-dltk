/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.tests.assist;

import junit.framework.Test;

import org.eclipse.dltk.core.tests.model.AbstractModelCompletionTests;

public class AutoRubySelectionTests extends AbstractModelCompletionTests {
	
	
	private static final String SELECTION_PROJECT = "Selection";
	

	public AutoRubySelectionTests(String name) {
		super("org.eclipse.dltk.ruby.core.tests", name);
	}

	public void setUpSuite() throws Exception {
		PROJECT = setUpScriptProject(SELECTION_PROJECT);

		super.setUpSuite();
	}

	public static Test suite() {
		return new Suite(AutoRubySelectionTests.class);
	}
	
	
	
	
	
}
