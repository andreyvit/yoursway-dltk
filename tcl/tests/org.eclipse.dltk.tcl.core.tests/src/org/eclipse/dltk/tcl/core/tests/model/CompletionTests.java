package org.eclipse.dltk.tcl.core.tests.model;

import junit.framework.Test;

import org.eclipse.dltk.codeassist.RelevanceConstants;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.tests.model.AbstractModelCompletionTests;
import org.eclipse.dltk.core.tests.model.CompletionTestsRequestor;

public class CompletionTests extends AbstractModelCompletionTests {

	private static final int RELEVANCE = (RelevanceConstants.R_DEFAULT
			+ RelevanceConstants.R_INTERESTING + RelevanceConstants.R_CASE + RelevanceConstants.R_NON_RESTRICTED);

	public CompletionTests(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	public void setUpSuite() throws Exception {
		PROJECT = setUpScriptProject("Completion");

		super.setUpSuite();
	}

	public static Test suite() {
		return new Suite(CompletionTests.class);
	}

	private String makeResult(String[] elements, String[] completions,
			int[] relevance) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < elements.length; ++i) {
			buffer.append("element:" + elements[i] + "    completion:"
					+ completions[i] + "    relevance:" + relevance[i]);

			if (i != elements.length - 1) {
				buffer.append("\n");
			}
		}
		return buffer.toString();
	}

	private String makeResult(String[] elements) {
		String[] completions = new String[elements.length];
		int[] relevance = new int[elements.length];
		for (int i = 0; i < elements.length; ++i) {
			completions[i] = elements[i];
			relevance[i] = RELEVANCE;
		}
		return makeResult(elements, completions, relevance);
	}

	private String makeResult(String[] elements, int[] relevance) {
		String[] completions = new String[elements.length];
		for (int i = 0; i < elements.length; ++i) {
			completions[i] = elements[i];
		}
		return makeResult(elements, completions, relevance);
	}

	public void testCompletion001() throws ModelException {
		CompletionTestsRequestor requestor = new CompletionTestsRequestor();
		ISourceModule cu = getSourceModule("Completion", "src",
				"CompletionKeywordNamespace1.tcl");

		String str = cu.getSource();
		String completeBehind = "nam";
		int cursorLocation = str.lastIndexOf(completeBehind)
				+ completeBehind.length();
		cu.codeComplete(cursorLocation, requestor);

		assertEquals(
				makeResult(new String[] { "namespace", "namespace eval" }),
				requestor.getResults());
	}

	public void testCompletion002() throws ModelException {
		CompletionTestsRequestor requestor = new CompletionTestsRequestor();
		ISourceModule cu = getSourceModule("Completion", "src",
				"CompletionKeywordNamespace1.tcl");

		String str = cu.getSource();
		String completeBehind = "pa";
		int cursorLocation = str.lastIndexOf(completeBehind)
				+ completeBehind.length();
		cu.codeComplete(cursorLocation, requestor);

		assertEquals(makeResult(new String[] { "package", "package provide",
				"package require" }), requestor.getResults());

	}

	public void testCompletion003() throws ModelException {
		for (int i = 0; i < 10; ++i) {
			process003(i);
		}
	}

	private void process003(int add) throws ModelException {
		CompletionTestsRequestor requestor = new CompletionTestsRequestor();
		ISourceModule cu = getSourceModule("Completion", "src",
				"Completion002.tcl");

		String str = cu.getSource();
		int cursorLocation = str.indexOf("#2") + 4 + add;
		cu.codeComplete(cursorLocation, requestor);

		assertEquals(makeResult(new String[] { "::a::c::fac" },
				new int[] { 18 }), requestor.getResults());
	}

	public void testCompletion004() throws ModelException {
		CompletionTestsRequestor requestor = new CompletionTestsRequestor();
		ISourceModule cu = getSourceModule("Completion", "src",
				"Completion002.tcl");

		String str = cu.getSource();
		int cursorLocation = str.indexOf("#1") + 4 + 9;
		cu.codeComplete(cursorLocation, requestor);

		assertEquals(makeResult(new String[] { "::a::c::fac", "::a::c::fbac",
				"::a::c::feac" }), requestor.getResults());

	}

	public void testCompletion005() throws ModelException {
		CompletionTestsRequestor requestor = new CompletionTestsRequestor();
		ISourceModule cu = getSourceModule("Completion", "src",
				"Completion002.tcl");

		String str = cu.getSource();
		int cursorLocation = str.indexOf("#3") + 4 + 6;
		cu.codeComplete(cursorLocation, requestor);

		assertEquals(makeResult(new String[] { "::a::f::faf",
				"::a::f::q::faf_q", "::a::f::q::fafq", "::a::f::q::t::fafqt",
				"::a::fa" }), requestor.getResults());

	}

	public void testCompletion006() throws ModelException {
		CompletionTestsRequestor requestor = new CompletionTestsRequestor();
		ISourceModule cu = getSourceModule("Completion", "src",
				"Completion002.tcl");

		String str = cu.getSource();
		int cursorLocation = str.indexOf("#4") + 4 + 6;
		cu.codeComplete(cursorLocation, requestor);

		assertEquals(makeResult(new String[] { "::b::fb" }, new int[] { 22 }),
				requestor.getResults());

	}

	public void testCompletion007() throws ModelException {
		CompletionTestsRequestor requestor = new CompletionTestsRequestor();
		ISourceModule cu = getSourceModule("Completion", "src",
				"completion003.tcl");

		String str = cu.getSource();
		String s = "puts";
		int cursorLocation = str.indexOf(s) + s.length() + 1;
		cu.codeComplete(cursorLocation, requestor);

		assertEquals(makeResult(new String[] { "$::x", "$x" }, new int[] { 18,
				18 }), requestor.getResults());

	}

	public void testCompletion008() throws ModelException {
		CompletionTestsRequestor requestor = new CompletionTestsRequestor();
		ISourceModule cu = getSourceModule("Completion", "src",
				"completion004.tcl");

		String str = cu.getSource();
		String s = "puts \"";
		int cursorLocation = str.indexOf(s) + s.length() + 2;
		cu.codeComplete(cursorLocation, requestor);

		assertEquals(makeResult(new String[] { "$::x", "$x" }, new int[] { 18,
				18 }), requestor.getResults());

	}
}
