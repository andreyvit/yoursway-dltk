package org.eclipse.dltk.ruby.internal.parser;

import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.ast.parser.ISourceParserFactory;

/**
 * Returns instances of the JRuby source parser 
 */
public class JRubySourceParserFactory implements ISourceParserFactory {

	/*
	 * @see org.eclipse.dltk.ast.parser.ISourceParserFactory#createSourceParser()
	 */
	public ISourceParser createSourceParser() {
		return new JRubySourceParser();
	}

}
