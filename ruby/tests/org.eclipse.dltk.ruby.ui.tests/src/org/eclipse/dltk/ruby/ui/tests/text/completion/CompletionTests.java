package org.eclipse.dltk.ruby.ui.tests.text.completion;

import junit.framework.Test;

import org.eclipse.dltk.codeassist.RelevanceConstants;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.tests.model.AbstractModelCompletionTests;
import org.eclipse.dltk.core.tests.model.CompletionTestsRequestor;
import org.eclipse.dltk.ruby.ui.tests.internal.RubyUITestsPlugin;

public class CompletionTests extends AbstractModelCompletionTests {

	private static final int RELEVANCE = (RelevanceConstants.R_DEFAULT
			+ RelevanceConstants.R_INTERESTING + RelevanceConstants.R_CASE + RelevanceConstants.R_NON_RESTRICTED);

	public CompletionTests(String name) {
		super(RubyUITestsPlugin.PLUGIN_ID, name);
	}

	public void setUpSuite() throws Exception {
		PROJECT = setUpScriptProject("completion");

		super.setUpSuite();
	}

	public static Test suite() {
		return new Suite(CompletionTests.class);
	}

	private String makeResult(String[] elements, String[] completions, int[] relevance) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < elements.length; ++i) {
			buffer.append("element:" + elements[i] + "    completion:" + completions[i]
					+ "    relevance:" + relevance[i]);

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
		ISourceModule cu = getSourceModule("completion", "src", "new.rb");

		String str = cu.getSource();
		String completeBehind = "Foo.new.";
		int cursorLocation = str.lastIndexOf(completeBehind) + completeBehind.length();
		cu.codeComplete(cursorLocation, requestor);

		assertEquals(makeResult(new String[] { "dining_philosopher", "ultimate_answer" }), requestor
				.getResults());
	}

}
