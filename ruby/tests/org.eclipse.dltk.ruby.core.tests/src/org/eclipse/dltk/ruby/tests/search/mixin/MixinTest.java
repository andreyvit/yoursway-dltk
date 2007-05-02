/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.tests.search.mixin;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.tests.model.AbstractDLTKSearchTests;
import org.eclipse.dltk.ti.ITypeInferencer;

public class MixinTest extends AbstractDLTKSearchTests implements IDLTKSearchConstants {

	private static final String SRC_PROJECT = "mixins";

	public MixinTest(String name) {
		super("org.eclipse.dltk.ruby.core.tests", name);
	}
	

	public void setUpSuite() throws Exception {
		super.setUpSuite();		
		up();
		waitUntilIndexesReady();
		ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
	}
	
	private void up() throws Exception {
		if (SCRIPT_PROJECT == null) {
			SCRIPT_PROJECT = setUpScriptProject(SRC_PROJECT);
		}
	}
	
	public void tearDownSuite() throws Exception {
		deleteProject(SRC_PROJECT);
		super.tearDownSuite();
	}

	public void executeTest(Collection assertions) throws Exception {
		waitUntilIndexesReady();
		for (Iterator iter = assertions.iterator(); iter.hasNext();) {
			IAssertion assertion = (IAssertion) iter.next();
			assertion.check();
		}
	}

}
