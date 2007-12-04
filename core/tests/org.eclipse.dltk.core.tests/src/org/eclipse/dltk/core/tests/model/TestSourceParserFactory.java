package org.eclipse.dltk.core.tests.model;

import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.ast.parser.ISourceParserFactory;

public class TestSourceParserFactory implements ISourceParserFactory {

	/*
	 * @see org.eclipse.dltk.ast.parser.ISourceParserFactory#createSourceParser()
	 */
	public ISourceParser createSourceParser() {
		return new TestSourceParser();
	}
}
