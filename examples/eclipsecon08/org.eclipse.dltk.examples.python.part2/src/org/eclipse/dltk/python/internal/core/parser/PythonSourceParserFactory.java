package org.eclipse.dltk.python.internal.core.parser;

import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.ast.parser.ISourceParserFactory;

/**
 * Returns instances of the Python source parser
 */
public class PythonSourceParserFactory implements ISourceParserFactory {

	/*
	 * @see org.eclipse.dltk.ast.parser.ISourceParserFactory#createSourceParser()
	 */
	public ISourceParser createSourceParser() {
		return new PythonSourceParser();
	}
}
