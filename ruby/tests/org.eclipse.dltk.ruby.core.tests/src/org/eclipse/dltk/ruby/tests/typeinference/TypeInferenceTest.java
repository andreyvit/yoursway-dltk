/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.tests.typeinference;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ruby.typeinference.RubyTypeInferencingUtils;
import org.eclipse.dltk.ti.ITypeInferencer;

public class TypeInferenceTest extends AbstractTypeInferencingTests {

	private static final String SRC_PROJECT = "typeinference";

	public TypeInferenceTest(String name) {
		super("org.eclipse.dltk.ruby.core.tests", name);
	}

	public void setUpSuite() throws Exception {
		PROJECT = setUpScriptProject(SRC_PROJECT);
		super.setUpSuite();
		waitUntilIndexesReady();
		ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
		
	}
	
	public void tearDownSuite() throws Exception {
		deleteProject(SRC_PROJECT);
		super.tearDownSuite();
	}

	public void executeTest(String folder, String name, ITypeInferencer inferencer, Collection assertions) throws Exception {
		waitForAutoBuild();
		ISourceModule cu = getSourceModule(SRC_PROJECT, folder, name);
		ModuleDeclaration rootNode = RubyTypeInferencingUtils.parseSource(cu);
		for (Iterator iter = assertions.iterator(); iter.hasNext();) {
			IAssertion assertion = (IAssertion) iter.next();
			assertion.check(rootNode, cu, inferencer);
		}
	}

}
