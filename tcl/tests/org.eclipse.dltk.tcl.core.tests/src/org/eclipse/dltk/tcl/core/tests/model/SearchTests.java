/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.core.tests.model;

import java.util.List;

import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.tests.model.AbstractDLTKSearchTests;


public class SearchTests extends AbstractDLTKSearchTests implements IDLTKSearchConstants {
	private static final String TCLSEARCH = "TCLSearch";

	public SearchTests(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	public static Suite suite() {
		return new Suite(SearchTests.class);
	}

	public void setUpSuite() throws Exception {
		super.setUpSuite();
		up();
	}

	public void tearDownSuite() throws Exception {
		deleteProject(TCLSEARCH);
		super.tearDownSuite();
	}
	
	private void up() throws Exception {
		if (SCRIPT_PROJECT == null) {
			SCRIPT_PROJECT = setUpScriptProject(TCLSEARCH);
		}
	}

	/**
	 * Simple type declaration test.
	 */
	public void testNamespaceDeclaration01() throws Exception { 
		up();
		IType type = getSourceModule(TCLSEARCH, "src", "p/X.tcl").getType("X");
		search(type, DECLARATIONS, getSearchScope(TCLSEARCH));
		assertSearchResults("src/p/X.tcl p/X", resultCollector);
	}

	public void testNamespaceDeclaration02() throws Exception {
		up();
		IScriptFolder pkg = this.getScriptFolder(TCLSEARCH, "src", new Path("p1"));
		IDLTKSearchScope scope = SearchEngine.createSearchScope(new IModelElement[] {
			pkg
		});
		search("Y", TYPE, DECLARATIONS, scope);
		assertSearchResults("src/p1/test.tcl p1/Y", this.resultCollector);
	}
	public void testNamespaceDeclaration03() throws Exception {
		up();
		IDLTKSearchScope scope = SearchEngine.createSearchScope(new IModelElement[]{ getScriptProject(TCLSEARCH) });
		search("*", TYPE, DECLARATIONS, scope);
		assertSearchResults(
				"src/X.tcl X\n" +
				"src/p/X.tcl p/X\n" +
				"src/p1/test.tcl p1/Y\n" +
				"src/p2/X.tcl p2/X\n" +
				"src/p2/X.tcl p2/Y\n" +
				"src/p3/X.tcl p3/X\n" +
				"src/p3/X.tcl p3/X$T1\n" +
				"src/p3/X.tcl p3/X$T1$T1\n" +
				"src/p3/X.tcl p3/Y\n" +
				"src/p3/X.tcl p3/Y$T2\n" +
				"src/p3/X.tcl p3/Y$T2$T3\n" +
				"src/p3/X.tcl p3/Y$T2$T3$T4\n" +
				"src/p3/X.tcl p3/Z\n" +
				"src/p3/X.tcl p3/Z$T2\n" +
				"src/p3/X.tcl p3/Z$T2$T3\n" +
				"src/p3/X.tcl p3/Z$T2$T3$T4\n" +
				"src/p4/t.tcl p4/alfa\n"+
				"src/q5/AQ.tcl q5/I\n"+
				"src/q5/AQ.tcl q5/I2"
		, this.resultCollector);
	}		
	public void testNamespaceDeclaration05() throws Exception {
		up();
		IType type = getSourceModule(TCLSEARCH, "src", "p3/X.tcl").getType("Z").getType("T2");
		search(type, DECLARATIONS, getSearchScope(TCLSEARCH));
		assertSearchResults("src/p3/X.tcl p3/Z$T2", resultCollector);	
	}
	public void testNamespaceDeclaration06() throws Exception {
		up();
		IType type = getSourceModule(TCLSEARCH, "src", "p3/X.tcl").getType("Z").getType("T2").getType("T3");
		search(type, DECLARATIONS, getSearchScope(TCLSEARCH));
		assertSearchResults("src/p3/X.tcl p3/Z$T2$T3", resultCollector);	
	}
	public void testNamespaceDeclaration07() throws Exception {
		up();
		IType type = getSourceModule(TCLSEARCH, "src", "p3/X.tcl").getType("Z").getType("T2").getType("T3").getType("T4");
		search(type, DECLARATIONS, getSearchScope(TCLSEARCH));
		assertSearchResults("src/p3/X.tcl p3/Z$T2$T3$T4", resultCollector);	
	}
	public void testNamespaceDeclaration08() throws Exception {
		up();
		IType type = getSourceModule(TCLSEARCH, "src", "p3/X.tcl").getType("Z");
		search(type, DECLARATIONS, getSearchScope(TCLSEARCH));
		assertSearchResults("src/p3/X.tcl p3/Z", resultCollector);	
	}
	public void testNamespaceDeclaration09() throws Exception {
		up();
		IType type = getSourceModule(TCLSEARCH, "src", "p4/t.tcl").getType("alfa");
		search(type, DECLARATIONS, getSearchScope(TCLSEARCH));
		assertSearchResults("src/p4/t.tcl p4/alfa", resultCollector);	
	}
	public void testMethodDeclaration00() throws Exception {
		up();
		IDLTKSearchScope scope = SearchEngine.createSearchScope(new IModelElement[]{ getScriptProject(TCLSEARCH) });		
		search("*", METHOD, DECLARATIONS, scope);
		assertSearchResults(
			"src/X.tcl  X$a()\n"+
			"src/p/X.tcl  p/X$foo()\n"+
			"src/p1/test.tcl  p1/Y$foo()\n"+
			"src/p2/X.tcl  p2/X$foo()\n"+
			"src/p2/X.tcl  p2/Y$foo()\n"+
			"src/p3/X.tcl  p3/X$src_p3_X_X_function(arg1, arg2, arg3)\n"+
			"src/p3/X.tcl  p3/X$foo()\n"+
			"src/p3/X.tcl  p3/X$T1$src_p3_X_X_T1_function(arg1, arg2, arg3)\n"+
			"src/p3/X.tcl  p3/X$T1$T1$src_p3_X_X_T1_T1_function(arg1, arg2, arg3)\n"+
			"src/p3/X.tcl  p3/Y$src_p3_X_Y_function(arg1, arg2, arg3)\n"+
			"src/p3/X.tcl  p3/Y$foo()\n"+
			"src/p3/X.tcl  p3/Y$T2$T3$T4$src_p3_X_Y_T2_T3_T4_function(arg1, arg2, arg3)\n"+
			"src/p3/X.tcl  p3/Z$foo()\n"+
			"src/p3/X.tcl  $src_p3_X_function(arg1, arg2, arg3)\n"+
			"src/p3/X.tcl  p3/global2$namespace2$function(arg1, arg2, arg3)\n"+
			"src/q5/AQ.tcl  q5/I$k(arg)\n"+
			"src/q5/AQ.tcl  q5/I2$k(arg)\n"+
			"src/q5/AQ.tcl  $m()"
		, this.resultCollector);
	}
	/**
	 * Simple method declaration test.
	 */
	public void testMethodDeclaration01() throws Exception { // was testSimpleMethodDeclaration
		up();
		IType type = getSourceModule(TCLSEARCH, "src", new Path( "p/X.tcl") ).getType("X");
		IMethod method = type.getMethod("foo");

		search(
			method, 
			DECLARATIONS, 
			getSearchScope(TCLSEARCH));
		assertSearchResults(
			"src/p/X.tcl  p/X$foo()\n"+
			"src/p1/test.tcl  p1/Y$foo()\n"+
			"src/p2/X.tcl  p2/X$foo()\n"+
			"src/p2/X.tcl  p2/Y$foo()\n"+
			"src/p3/X.tcl  p3/X$foo()\n"+
			"src/p3/X.tcl  p3/Y$foo()\n"+
			"src/p3/X.tcl  p3/Z$foo()",
			this.resultCollector);
	}
	/**
	 * Simple method declaration test.
	 */
	public void testMethodDeclaration02() throws Exception { // was testSimpleMethodDeclaration
		up();
		IType type = getSourceModule(TCLSEARCH, "src", new Path( "p3/X.tcl") ).getType("Y").getType("T2").getType("T3").getType("T4");
		IMethod method = type.getMethod("src_p3_X_Y_T2_T3_T4_function");

		search(
			method, 
			DECLARATIONS, 
			getSearchScope(TCLSEARCH));
		assertSearchResults(
			"src/p3/X.tcl  p3/Y$T2$T3$T4$src_p3_X_Y_T2_T3_T4_function(arg1, arg2, arg3)",
			this.resultCollector);
	}
		
	public void testMethodReference01() throws Exception {
		up();
		IType type = getSourceModule(TCLSEARCH, "src", new Path( "q5/AQ.tcl")).getType("I");
		IMethod method = type.getMethod("k");

		search(
			method, 
			REFERENCES, 
			getSearchScope(TCLSEARCH));
		assertSearchResults(
			"src/q5/AQ.tcl q5/I\n"+
			"src/q5/AQ.tcl q5/I\n"+
			"src/q5/AQ.tcl  $m()\n" +
			"src/q5/AQ.tcl\n" +
			"src/q5/AQ.tcl",
			this.resultCollector);
	}
	public void testMethodReference02() throws Exception {
		up();

		IMethod method = getSourceModule(TCLSEARCH, "src", new Path( "q5/AQ.tcl")).getMethod("m");

		search(
			method, 
			REFERENCES, 
			getSearchScope(TCLSEARCH));
		assertSearchResults(
			"src/q5/AQ.tcl q5/I\n"+
			"src/q5/AQ.tcl q5/I2\n"+
			"src/q5/AQ.tcl",
			this.resultCollector);
	}	
	public void testTypeReference01() throws Exception {
		up();
		IType type = getSourceModule(TCLSEARCH, "src", new Path( "X.tcl")).getType("X");

		search(
			type, 
			REFERENCES,
			getSearchScope(TCLSEARCH));
		assertSearchResults(
			"src/X.tcl",
			this.resultCollector);
	}
	public void testTypeReference02() throws Exception {
		up();
		IType type = getSourceModule(TCLSEARCH, "src", new Path( "q5/AQ.tcl")).getType("I");

		search(
			type, 
			REFERENCES,
			getSearchScope(TCLSEARCH));
		assertSearchResults(
			"src/q5/AQ.tcl q5/I\n"+
			"src/q5/AQ.tcl q5/I2\n"+
			"src/q5/AQ.tcl  $m()\n"+
			"src/q5/AQ.tcl\n" +
			"src/q5/AQ.tcl",
			this.resultCollector);
	}
	public void testVariableDeclaration01() throws Exception {
		up();
		IField field = getSourceModule(TCLSEARCH, "src", new Path( "p/X.tcl")).getField("globalX");

		search(
			field, 
			DECLARATIONS,
			getSearchScope(TCLSEARCH));
		assertSearchResults(
			"src/p/X.tcl globalX",
			this.resultCollector);
	}
	public void testVariableDeclaration02() throws Exception {
		up();
		ISourceModule module = getSourceModule(TCLSEARCH, "src", new Path( "p3/X.tcl"));

		search(
			"*", 
			FIELD,
			DECLARATIONS,
			getSearchScope(TCLSEARCH));
		assertSearchResults(
			"src/p/X.tcl globalX\n"+
			"src/p3/X.tcl p3/X$T1$T1$v6\n"+
			"src/p3/X.tcl p3/X$T1$v5\n"+
			"src/p3/X.tcl p3/X$v1\n"+
			"src/p3/X.tcl p3/X$v2\n"+
			"src/p3/X.tcl p3/X$v3\n"+
			"src/p3/X.tcl p3/X$v4\n" +
			"src/p3/X.tcl v8\n"+
			"src/p3/X.tcl p3/Y$T2$T3$v10\n"+
			"src/p3/X.tcl p3/Y$T2$v9\n"+
			"src/p3/X.tcl p3/Y$v7\n"+
			"src/p3/X.tcl p3/Z$T2$T3$T4$v11\n"+
			"src/p4/t.tcl p4/alfa$superFU\n"+
			"src/p4/t.tcl p4/alfa$superFU\n"+
			"src/p4/t.tcl p4/alfa$superFU",
			this.resultCollector);
	}
	public void testVariableDeclaration03() throws Exception {
		up();
		IDLTKProject project = getScriptProject(TCLSEARCH);

		List sources = searchSourceOnly(
			"*", 
			FIELD,
			DECLARATIONS,
			getSearchScope(TCLSEARCH));
		assertNotNull(sources);
		for (int i = 0; i < sources.size(); i++) {
			ISourceModule element = (ISourceModule)sources.get(i);
			assertNotNull(element);
			System.out.println(element.getElementName());
		}
	}
}
