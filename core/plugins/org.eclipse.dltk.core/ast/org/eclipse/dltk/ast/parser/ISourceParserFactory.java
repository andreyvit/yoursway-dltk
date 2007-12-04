package org.eclipse.dltk.ast.parser;

public interface ISourceParserFactory {

	/**
	 * Creates a new instance of an <code>ISourceParser</code> implementation
	 * 
	 * @return source parser
	 */
	ISourceParser createSourceParser();	
}
