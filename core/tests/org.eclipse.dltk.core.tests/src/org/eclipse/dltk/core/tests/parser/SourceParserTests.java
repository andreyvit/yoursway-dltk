package org.eclipse.dltk.core.tests.parser;

import junit.framework.Assert;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.tests.model.AbstractModelTests;
import org.eclipse.dltk.core.tests.model.TestConstants;
import org.eclipse.dltk.core.tests.model.TestSourceParser;

public class SourceParserTests extends AbstractModelTests {

	private static final String PARSER_NAME = "Test Source Parser";
	private static final String PARSER_ID = "org.eclipse.dltk.core.tests.sourceParser";
	private static final int PARSER_PRIORITY = 1;

	public SourceParserTests(String name) {
		super("org.eclipse.dltk.core.tests", name);
	}

	public void testGetSourceParser() {
		ISourceParser parser = null;

		try {
			parser = DLTKLanguageManager
					.getSourceParser(TestConstants.NATURE_ID);
		} catch (CoreException e) {
			/*
			 *  this should actually never happen b/c the getSourceParser 
			 *  method doesn't actually throw an exception
			 */			
			Assert.fail("unexpected CoreException");
		}

		assertNotNull(parser);
		assertTrue((parser instanceof TestSourceParser));

		/*
		 * these tests are 'dependent' upon the two test tests above working -
		 * this could be broken out into its own top level test
		 */
		testParserConfig(parser);
	}

	private void testParserConfig(ISourceParser parser) {
		// these are configured to the same value in plugin.xml
		assertEquals(PARSER_NAME, parser.getName());
		assertEquals(PARSER_NAME, parser.getDescription());

		assertEquals(PARSER_ID, parser.getId());
		assertEquals(TestConstants.NATURE_ID, parser.getNatureId());

		assertEquals(PARSER_PRIORITY, parser.getPriority());
	}

}
