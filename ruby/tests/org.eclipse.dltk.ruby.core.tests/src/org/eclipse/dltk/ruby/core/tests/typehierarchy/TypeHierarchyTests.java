/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.core.tests.typehierarchy;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ITypeHierarchy;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.tests.model.AbstractDLTKSearchTests;
import org.eclipse.dltk.ruby.core.tests.Activator;


public class TypeHierarchyTests extends AbstractDLTKSearchTests implements IDLTKSearchConstants {
	private static final String PROJECT = "typehierarchy";

	public TypeHierarchyTests(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	public static Suite suite() {
		return new Suite(TypeHierarchyTests.class);
	}

	public void setUpSuite() throws Exception {
		deleteProject(PROJECT);
		SCRIPT_PROJECT.close();
		SCRIPT_PROJECT = null;
		up();
		super.setUpSuite();
	}

	public void tearDownSuite() throws Exception {
		deleteProject(PROJECT);
		super.tearDownSuite();
	}
	
	private void up() throws Exception {
		if (SCRIPT_PROJECT == null) {
			SCRIPT_PROJECT = setUpScriptProject(PROJECT);
		}
	}
	
	public void _testBuildHierarcy001() throws Exception {
		up();
		IType type = getSourceModule(PROJECT, "src", "src1.rb").getType("BB");
//		search(type, DECLARATIONS, getSearchScope(PROJECT));
//		assertSearchResults("src/p3/X.tcl p3/Z$T2", resultCollector);	
		ITypeHierarchy hierarchy = type.newTypeHierarchy(new NullProgressMonitor());
		assertNotNull(hierarchy);
		IType[] types = hierarchy.getSuperclass(type);
		assertNotNull(types);
		
	}
}
