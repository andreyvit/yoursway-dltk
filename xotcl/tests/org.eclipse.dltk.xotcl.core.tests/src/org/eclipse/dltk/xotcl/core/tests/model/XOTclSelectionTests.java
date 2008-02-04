/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 *******************************************************************************/

package org.eclipse.dltk.xotcl.core.tests.model;

import junit.framework.Test;

import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.tests.model.AbstractModelCompletionTests;
import org.eclipse.dltk.xotcl.core.tests.Activator;

public class XOTclSelectionTests extends AbstractModelCompletionTests {

	private static final String SELECTION_PROJECT = "XOTCL_Selection";

	public XOTclSelectionTests(String name) {
		super(Activator.PLUGIN_ID, name);
		super.fTestProjectName = "org.eclipse.dltk.xotcl.core.tests";
	}

	public void setUpSuite() throws Exception {
		if (PROJECT == null) {
			PROJECT = setUpScriptProjectTo("XOTCL_Selection", "Selection");

			super.setUpSuite();
			ResourcesPlugin.getWorkspace().build(
					IncrementalProjectBuilder.FULL_BUILD,
					new NullProgressMonitor());
			waitForAutoBuild();
		}
	}

	public static Test suite() {
		return new Suite(XOTclSelectionTests.class);
	}

	public void testselection001() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection001.tcl");

		String source = cu.getSource();
		int start = source.indexOf("proc fa") + 5;

		String sub = source.substring(start, start + 2);
		assertEquals("fa", sub);

		IModelElement[] elements = cu.codeSelect(start, 2);
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IMethod method = cu.getType("a").getMethod("fa");
		assertNotNull(method);
		assertEquals(method, elements[0]);
	}

	public void testselection002() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection001.tcl");

		String source = cu.getSource();
		int start = source.indexOf("proc fac") + 5;

		String sub = source.substring(start, start + 3);
		assertEquals("fac", sub);

		IModelElement[] elements = cu.codeSelect(start, 3);
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IMethod method = cu.getType("a").getType("c").getMethod("fac");
		assertNotNull(method);
		assertEquals(method, elements[0]);
	}

	public void testselection003() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection001.tcl");

		String source = cu.getSource();
		int start = source.indexOf("proc fbac") + 5;

		String sub = source.substring(start, start + 4);
		assertEquals("fbac", sub);

		IModelElement[] elements = cu.codeSelect(start, 4);
		assertNotNull(elements);
		assertEquals(
				"Failed to select fbac method from namespace ::a::c declared from ::b",
				1, elements.length);
		IMethod method = cu.getType("a").getType("c").getMethod("fbac");
		assertNotNull(method);
		assertEquals(method, elements[0]);
	}

	public void testselection004() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection001.tcl");

		String source = cu.getSource();
		int start = source.indexOf("proc fb") + 5;

		String sub = source.substring(start, start + 2);
		assertEquals("fb", sub);

		IModelElement[] elements = cu.codeSelect(start, 2);
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IMethod method = cu.getType("b").getMethod("fb");
		assertNotNull(method);
		assertEquals(method, elements[0]);
	}

	public void testselection005() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection001.tcl");

		String source = cu.getSource();
		String s = "::a::c::fac";
		int start = source.indexOf(s);

		String sub = source.substring(start, start + s.length());
		assertEquals(s, sub);

		IModelElement[] elements = cu.codeSelect(start, s.length());
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IMethod method = cu.getType("a").getType("c").getMethod("fac");
		assertNotNull(method);
		assertEquals(method, elements[0]);
	}

	public void testselection006() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection001.tcl");

		String source = cu.getSource();
		String s = "::a::fa";
		int start = source.indexOf(s);

		String sub = source.substring(start, start + s.length());
		assertEquals(s, sub);

		IModelElement[] elements = cu.codeSelect(start, s.length());
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IMethod method = cu.getType("a").getMethod("fa");
		assertNotNull(method);
		assertEquals(method, elements[0]);
	}

	public void testselection007() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection001.tcl");

		String source = cu.getSource();
		String s = "::a::c::fbac";
		int start = source.indexOf(s);

		String sub = source.substring(start, start + s.length());
		assertEquals(s, sub);

		IModelElement[] elements = cu.codeSelect(start, s.length());
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IMethod method = cu.getType("a").getType("c").getMethod("fbac");
		assertNotNull(method);
		assertEquals(method, elements[0]);
	}

	public void testselection008() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection001.tcl");

		String source = cu.getSource();
		String s = "::a::c::feac";
		int start = source.indexOf(s);

		String sub = source.substring(start, start + s.length());
		assertEquals(s, sub);

		IModelElement[] elements = cu.codeSelect(start, s.length());
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IMethod method = cu.getType("a").getType("c").getMethod("feac");
		assertNotNull(method);
		assertEquals(method, elements[0]);
	}

	public void testselection009() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection001.tcl");

		String source = cu.getSource();
		String s = "::a::f::q::faf_q";
		int start = source.indexOf(s);

		String sub = source.substring(start, start + s.length());
		assertEquals(s, sub);

		IModelElement[] elements = cu.codeSelect(start, s.length());
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IMethod method = cu.getType("a").getType("f").getType("q").getMethod(
				"faf_q");
		assertNotNull(method);
		assertEquals(method, elements[0]);
	}

	public void testSelection010() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection001.tcl");

		String source = cu.getSource();
		String s = "::a::f::q::fafq";
		int start = source.indexOf(s);

		String sub = source.substring(start, start + s.length());
		assertEquals(s, sub);

		IModelElement[] elements = cu.codeSelect(start, s.length());
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IMethod method = cu.getType("a").getType("f").getType("q").getMethod(
				"fafq");
		assertNotNull(method);
		assertEquals(method, elements[0]);
	}

	public void testSelection011() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection001.tcl");

		String source = cu.getSource();
		String s = "::a::f::q::t::fafqt";
		int start = source.indexOf(s);

		String sub = source.substring(start, start + s.length());
		assertEquals(s, sub);

		IModelElement[] elements = cu.codeSelect(start, s.length());
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IMethod method = cu.getType("a").getType("f").getType("q").getType("t")
				.getMethod("fafqt");
		assertNotNull(method);
		assertEquals(method, elements[0]);
	}

	// Variable selection.
	public void testSelection012() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection002.tcl");

		String source = cu.getSource();
		String s = "puts $x1";
		int start = source.indexOf(s) + 5;

		String sub = source.substring(start, start + 3);
		assertEquals("$x1", sub);

		IModelElement[] elements = cu.codeSelect(start, 3);
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IField field = cu.getField("x1");
		assertNotNull(field);
		assertEquals(field, elements[0]);
	}

	public void testSelection013() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection002.tcl");

		String source = cu.getSource();
		String s = "puts \"a $x1";
		int start = source.indexOf(s) + 8;

		String sub = source.substring(start, start + 3);
		assertEquals("$x1", sub);

		IModelElement[] elements = cu.codeSelect(start, 3);
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IField field = cu.getField("x1");
		assertNotNull(field);
		assertEquals(field, elements[0]);
	}

	public void testSelection014() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection002.tcl");

		String source = cu.getSource();
		String s = "puts \"a$x1";
		int start = source.indexOf(s) + 7;

		String sub = source.substring(start, start + 3);
		assertEquals("$x1", sub);

		IModelElement[] elements = cu.codeSelect(start, 3);
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IField field = cu.getField("x1");
		assertNotNull(field);
		assertEquals(field, elements[0]);
	}

	public void testSelection015() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection002.tcl");

		String source = cu.getSource();
		String s = "puts \"$x1\"";
		int start = source.indexOf(s) + 6;

		String sub = source.substring(start, start + 3);
		assertEquals("$x1", sub);

		IModelElement[] elements = cu.codeSelect(start, 3);
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IField field = cu.getField("x1");
		assertNotNull(field);
		assertEquals(field, elements[0]);
	}

	public void testSelection016() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection002.tcl");

		String source = cu.getSource();
		String s = "puts ${x1}";
		int start = source.indexOf(s) + 5;

		String sub = source.substring(start, start + 5);
		assertEquals("${x1}", sub);

		IModelElement[] elements = cu.codeSelect(start, 5);
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IField field = cu.getField("x1");
		assertNotNull(field);
		assertEquals(field, elements[0]);
	}

	public void testSelection017() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection002.tcl");

		String source = cu.getSource();
		String s = "puts ${x1}";
		int start = source.indexOf(s) + 5;

		String sub = source.substring(start, start + 5);
		assertEquals("${x1}", sub);

		IModelElement[] elements = cu.codeSelect(start, 5);
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IField field = cu.getField("x1");
		assertNotNull(field);
		assertEquals(field, elements[0]);
	}

	public void process018(int add, int size) throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection002.tcl");

		String source = cu.getSource();
		String s = "puts ${x1 2}";
		int start = source.indexOf(s) + 5;

		String sub = source.substring(start, start + 7);
		assertEquals("${x1 2}", sub);

		IModelElement[] elements = cu.codeSelect(start + add, size);
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IField field = cu.getField("x1 2");
		assertNotNull(field);
		assertEquals(field, elements[0]);
	}

	public void process023(int add, int size) throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection002.tcl");

		String source = cu.getSource();
		String s = "puts \"${x1 2}\"";
		int start = source.indexOf(s) + 6;

		String sub = source.substring(start, start + 7);
		assertEquals("${x1 2}", sub);

		IModelElement[] elements = cu.codeSelect(start + add, size);
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IField field = cu.getField("x1 2");
		assertNotNull(field);
		assertEquals(field, elements[0]);
	}

	public void testSelection018() throws ModelException {
		process018(0, 7);
	}

	public void testSelection019() throws ModelException {
		process018(2, 1);
	}

	public void testSelection019a() throws ModelException {
		process018(2, 0);
	}

	public void testSelection020() throws ModelException {
		process018(3, 1);
	}

	public void testSelection020a() throws ModelException {
		process018(3, 0);
	}

	public void REM_testSelection021() throws ModelException {
		process018(4, 1);
	}

	public void testSelection021a() throws ModelException {
		process018(4, 0);
	}

	public void testSelection022() throws ModelException {
		process018(5, 1);
	}

	public void testSelection022a() throws ModelException {
		process018(5, 0);
	}

	public void REM_testSelection023() throws ModelException {
		process023(5, 0);
	}

	public void testSelection023a() throws ModelException {
		process023(0, 7);
	}

	public void testSelection023b() throws ModelException {
		process023(2, 1);
	}

	public void testSelection023c() throws ModelException {
		process023(2, 0);
	}

	public void testSelection023d() throws ModelException {
		process023(3, 1);
	}

	public void testSelection023e() throws ModelException {
		process023(3, 0);
	}

	public void REM_testSelection023f() throws ModelException {
		process023(4, 1);
	}

	public void testSelection023g() throws ModelException {
		process023(4, 0);
	}

	public void REM_testSelection023h() throws ModelException {
		process023(5, 1);
	}

	public void REM_testSelection023i() throws ModelException {
		process023(5, 0);
	}

	public void process024(int add, int size) throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection002.tcl");

		String source = cu.getSource();
		String s = "puts $x2(0)";
		int start = source.indexOf(s) + 5;

		String sub = source.substring(start, start + 6);
		assertEquals("$x2(0)", sub);

		IModelElement[] elements = cu.codeSelect(start + add, size);
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IField field1 = cu.getField("x2");
		assertNotNull(field1);
		assertEquals(field1, elements[0]);
	}

	public void REM_testSelection024a() throws ModelException {
		process024(0, 6);
	}

	public void REM_testSelection024b() throws ModelException {
		process024(3, 0);
	}

	public void process025(int add, int size) throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection002.tcl");

		String source = cu.getSource();
		String s = "puts \"$x2(0)";
		int start = source.indexOf(s) + 6;

		String sub = source.substring(start, start + 6);
		assertEquals("$x2(0)", sub);

		IModelElement[] elements = cu.codeSelect(start + add, size);
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IField field = cu.getField("x2(0)");
		assertNotNull(field);
		assertEquals(field, elements[0]);
	}

	public void REM_testSelection025a() throws ModelException {
		process024(0, 6);
	}

	public void REM_testSelection025b() throws ModelException {
		process024(3, 0);
	}

	public void process026(int add, int size) throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection002.tcl");

		String source = cu.getSource();
		String s = "puts \"${x2(0)}";
		int start = source.indexOf(s) + 6;

		String sub = source.substring(start, start + 6);
		assertEquals("${x2(0)}", sub);

		IModelElement[] elements = cu.codeSelect(start + add, size);
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IField field = cu.getField("x2(0)");
		assertNotNull(field);
		assertEquals(field, elements[0]);
	}

	public void REM_testSelection026a() throws ModelException {
		process024(0, 6);
	}

	public void REM_testSelection026b() throws ModelException {
		process024(3, 0);
	}

	public void testSelection27a() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection002.tcl");

		String source = cu.getSource();
		String s = "puts $x3";
		int start = source.indexOf(s) + 5;

		String sub = source.substring(start, start + 3);
		assertEquals("$x3", sub);

		IModelElement[] elements = cu.codeSelect(start, 3);
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IField field = cu.getField("x3");
		assertNotNull(field);
		assertEquals(field, elements[0]);
	}

	public void testSelection27b() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection002.tcl");

		String source = cu.getSource();
		String s = "puts ${x3}";
		int start = source.indexOf(s) + 6;

		String sub = source.substring(start, start + 4);
		assertEquals("{x3}", sub);

		IModelElement[] elements = cu.codeSelect(start, 4);
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IField field = cu.getField("x3");
		assertNotNull(field);
		assertEquals(field, elements[0]);
	}

	public void REM_testSelection27c() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection002.tcl");

		String source = "set x3 42\nputs ${x3}";
		String s = "puts ${x3}";
		int start = source.indexOf(s) + 6;

		String sub = source.substring(start, start + 4);
		assertEquals("{x3}", sub);

		IModelElement[] elements = cu.codeSelect(start, source.length() - 1);
		assertNotNull(elements);
		assertEquals(1, elements.length);
		IField field = cu.getField("x3");
		assertNotNull(field);
		assertEquals(field, elements[0]);
	}

	public IModelElement process028(ISourceModule cu, String pattern, int sadd,
			int add, int size) throws ModelException {
		String source = cu.getSource();
		int start = source.indexOf(pattern) + sadd;

		String sub = source.substring(start, start + pattern.length() - sadd);
		assertEquals(pattern.substring(sadd), sub);

		IModelElement[] elements = cu.codeSelect(start + add, size);
		assertNotNull(elements);
		assertEquals(1, elements.length);

		return elements[0];
	}

	public void testSelection028() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection003.tcl");
		String s = "puts $::a::c::vac";
		int i = 5;
		IModelElement element = process028(cu, s, i, 0, s.length() - i);
		IField field = cu.getType("a").getType("c").getField("vac");
		assertEquals(field, element);
	}

	public void testSelection029() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection003.tcl");
		String s = "puts $::a::c::vac2";
		int i = 5;
		IModelElement element = process028(cu, s, i, 0, s.length() - i);
		IField field = cu.getType("a").getType("c").getField("vac2");
		assertEquals(field, element);
	}

	public void testSelection030() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection003.tcl");
		String s = "puts $::a::c::vac4";
		int i = 5;
		IModelElement element = process028(cu, s, i, 0, s.length() - i);
		IField field = cu.getType("a").getType("c").getField("vac4");
		assertEquals(field, element);
	}

	public void testSelection031() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection003.tcl");
		String s = "puts $::a::f::q::t::vafqt0";
		int i = 5;
		IModelElement element = process028(cu, s, i, 0, s.length() - i);
		IField field = cu.getType("a").getType("f").getType("q").getType("t")
				.getField("vafqt0");
		assertEquals(field, element);
	}

	public void testSelection032() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection003.tcl");
		String s = "puts $::a::f::q::vafq";
		int i = 5;
		IModelElement element = process028(cu, s, i, 0, s.length() - i);
		IField field = cu.getType("a").getType("f").getType("q").getField(
				"vafq");
		assertEquals(field, element);
	}

	public void testSelection033() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection003.tcl");
		String s = "puts $::a::f::q::vafq2";
		int i = 5;
		IModelElement element = process028(cu, s, i, 0, s.length() - i);
		IField field = cu.getType("a").getType("f").getType("q").getField(
				"vafq2");
		assertEquals(field, element);
	}

	public void testSelection034() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection003.tcl");
		String s = "puts $::a::f::vaf";
		int i = 5;
		IModelElement element = process028(cu, s, i, 0, s.length() - i);
		IField field = cu.getType("a").getType("f").getField("vaf");
		assertEquals(field, element);
	}

	public void testSelection035() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection003.tcl");
		String s = "puts $::a::va";
		int i = 5;
		IModelElement element = process028(cu, s, i, 0, s.length() - i);
		IField field = cu.getType("a").getField("va");
		assertEquals(field, element);
	}

	public void REM_testSelection036() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection003.tcl");
		String s = "puts $::b::a::c::vca3";
		int i = 5;
		IModelElement element = process028(cu, s, i, 0, s.length() - i);
		IField field = cu.getType("b").getType("a").getType("c").getField(
				"vca3");
		assertEquals(field, element);
	}

	public void testSelection037() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection003.tcl");
		String s = "puts $::b::vb";
		int i = 5;
		IModelElement element = process028(cu, s, i, 0, s.length() - i);
		IField field = cu.getType("b").getField("vb");
		assertEquals(field, element);
	}

	public void testSelection038() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection002.tcl");
		String s = "puts [$x6]";
		int i = 7;
		IModelElement element = process028(cu, s, i, 0, s.length() - i);
		IField field = cu.getField("x6");
		assertEquals(field, element);
	}

	public void testSelection039() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection002.tcl");
		String s = "puts \"[$x6]\"";
		int i = 8;
		IModelElement element = process028(cu, s, i, 0, s.length() - i);
		IField field = cu.getField("x6");
		assertEquals(field, element);
	}

	public void testSelection040() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection002.tcl");
		String s = "puts $x7";
		int i = 6;
		IModelElement element = process028(cu, s, i, 0, s.length() - i);
		IField field = cu.getField("x7");
		assertEquals(field, element);
	}

	// -----------------------XOTcl specific tests------------------------

	public static IField getFieldByName(IModelElement[] elements, String name) {
		for (int i = 0; i < elements.length; i++) {
			IModelElement element = elements[i];
			if (element instanceof IField
					&& ((IField) element).getElementName().equals(name))
				return (IField) element;
		}
		return null;
	}

	public static IMethod getMethodByName(IModelElement[] elements, String name) {
		for (int i = 0; i < elements.length; i++) {
			IModelElement element = elements[i];
			if (element instanceof IMethod
					&& ((IMethod) element).getElementName().equals(name))
				return (IMethod) element;
		}
		return null;
	}

	private static IModelElement process041(ISourceModule cu, String pattern,
			int startIndex, int size) throws ModelException {
		String source = cu.getSource();
		assertTrue(-1 != source.indexOf(pattern));
		int start = source.indexOf(pattern) + startIndex;

		String sub = source.substring(start, start + size);

		IModelElement[] elements = cu.codeSelect(start, size);
		assertNotNull(elements);
		assertEquals(1, elements.length);
		return elements[0];
	}

	public void REM_testSelection041() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection004.tcl");
		IModelElement[] children = cu.getField("obj").getChildren();
		IField field = getFieldByName(children, "a");
		assertNotNull(field);

		String s = "obj set a 0";
		IModelElement element = process041(cu, s, 8, 1);
		assertEquals(field, element);
	}

	public void REM_testSelection042() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection004.tcl");
		String s = "Class create C3 -superclass {C1 C2}";
		IModelElement element = process041(cu, s, 29, 2);
		IType type = cu.getType("C1");
		assertEquals(type, element);
	}

	public void REM_testSelection043() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection004.tcl");
		String s = "Class create C3 -superclass {C1 C2}";
		IModelElement element = process041(cu, s, 32, 2);
		IType type = cu.getType("C2");
		assertEquals(type, element);
	}

	public void REM_testSelection044() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection004.tcl");
		String s = "Class create C4 -superclass C1";
		IModelElement element = process041(cu, s, 28, 2);
		IType type = cu.getType("C1");
		assertEquals(type, element);
	}

	public void testSelection045() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection004.tcl");
		String s = "C1 create c1_inst";
		IModelElement element = process041(cu, s, 10, 7);
		IField field = cu.getField("c1_inst");
		assertEquals(field, element);
	}

	public void testSelection046() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection004.tcl");
		String s = "c1_inst foo";
		IModelElement element = process041(cu, s, 8, 3);
		IMethod method = cu.getType("C1").getMethod("foo");
		assertEquals(method, element);
	}

	public void testSelection047() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection004.tcl");
		String s = "C1 c1_proc";
		IModelElement element = process041(cu, s, 3, 7);
		IMethod method = cu.getType("C1").getMethod("c1_proc");
		assertEquals(method, element);
	}

	public void testSelection048() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection004.tcl");
		String s = "obj obj_proc";
		IModelElement element = process041(cu, s, 4, 8);
		IMethod method = getMethodByName(cu.getField("obj").getChildren(),
				"obj_proc");
		assertEquals(method, element);
	}

	// -------------------------------- Newly added Tcl constructs testing
	// ----------------------

	public void REM_testSelection049() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection005.tcl");
		String s = "puts $i";
		IModelElement element = process041(cu, s, 5, 2);
		IField field = cu.getField("i");
		assertEquals(field, element);
	}

	public void REM_testSelection050() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection005.tcl");
		String s = "puts $g";
		IModelElement element = process041(cu, s, 5, 2);
		IField field = cu.getField("g");
		assertEquals(field, element);
	}

	// ------------------------------------------------------------------------------------------

	public void testSelection051() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection006.tcl");
		String s = "C0 instproc foo {} {return \"::n1::C0::foo\"}";
		IModelElement element = process041(cu, s, 0, 2);
		IType type = cu.getType("n1").getType("C0");
		assertEquals(type, element);
	}

	public void REM_testSelection052() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection006.tcl");
		String s = "Class C1 -superclass C0";
		IModelElement element = process041(cu, s, 21, 2);
		IType type = cu.getType("C0");
		assertEquals(type, element);
	}

	public void REM_testSelection053() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection006.tcl");
		String s = "Class C2 -superclass ::n1::C0";
		IModelElement element = process041(cu, s, 27, 2);
		IType type = cu.getType("n1").getType("C0");
		assertEquals(type, element);
	}

	public void REM_testSelection054() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection006.tcl");
		String s = "c1 foo";
		IModelElement element = process041(cu, s, 3, 3);
		IMethod method = cu.getType("C0").getMethod("foo");
		assertEquals(method, element);
	}

	public void testSelection055() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection006.tcl");
		String s = "c2 foo";
		IModelElement element = process041(cu, s, 3, 3);
		IMethod method = cu.getType("n1").getType("C0").getMethod("foo");
		assertEquals(method, element);
	}

	public void testSelection056() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection006.tcl");
		String s = "n1::n2::C1";
		IModelElement element = process041(cu, s, 8, 2);
		IType type = cu.getType("n1").getType("n2").getType("C1");
		assertEquals(type, element);
	}

	public void testSelection057() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection006.tcl");
		String s = "n1::n2::C2";
		IModelElement element = process041(cu, s, 8, 2);
		IType type = cu.getType("n1").getType("n2").getType("C2");
		assertEquals(type, element);
	}

	public void REM_testSelection059() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection006.tcl");
		String s = "c3 foo";
		IModelElement element = process041(cu, s, 3, 3);
		IMethod method = cu.getType("C0").getMethod("foo");
		assertEquals(method, element);
	}

	public void testSelection060() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection006.tcl");
		String s = "c0 foo";
		IModelElement element = process041(cu, s, 3, 3);
		IMethod method = cu.getType("n1").getType("C0").getMethod("foo");
		assertEquals(method, element);
	}

	public void testSelection061() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection006.tcl");
		String s = "C0 bar";
		IModelElement element = process041(cu, s, 3, 3);
		IMethod method = cu.getType("C0").getMethod("bar");
		assertEquals(method, element);
	}

	public void testSelection062() throws Exception {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection006.tcl");
		String s = "::n1::C0 bar";
		IModelElement element = process041(cu, s, 9, 3);
		IMethod method = cu.getType("n1").getType("C0").getMethod("bar");
		assertEquals(method, element);
	}

	public void REM_testSelection063() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection006.tcl");
		String s = "::n1::C0 bar";
		IModelElement element = process041(cu, s, 2, 2);
		IType type = cu.getType("n1");
		assertEquals(type, element);
	}

	public void REM_testSelection064() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection006.tcl");
		String s = "n1::n2::C1 create";
		IModelElement element = process041(cu, s, 0, 2);
		IType type = cu.getType("n1");
		assertEquals(type, element);
	}

	public void REM_testSelection058() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection006.tcl");
		String s = "n1::n2::C1 create";
		IModelElement element = process041(cu, s, 4, 2);
		IType type = cu.getType("n1").getType("n2");
		assertEquals(type, element);
	}

	public void REM_testSelection065() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection006.tcl");
		String s = "Class C3 -superclass {::n1::C0 C0}";
		IModelElement element = process041(cu, s, 28, 2);
		IType type = cu.getType("n1").getType("C0");
		assertEquals(type, element);
	}
	
	public void REM_testSelection066() throws ModelException {
		ISourceModule cu = getSourceModule(SELECTION_PROJECT, "src",
				"selection006.tcl");
		String s = "Class C3 -superclass {::n1::C0 C0}";
		IModelElement element = process041(cu, s, 31, 2);
		IType type = cu.getType("C0");
		assertEquals(type, element);
	}
}
