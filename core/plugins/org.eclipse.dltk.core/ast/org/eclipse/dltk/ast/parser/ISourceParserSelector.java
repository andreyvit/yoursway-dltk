package org.eclipse.dltk.ast.parser;

public interface ISourceParserSelector {

	/**
	 * Returns the currently selected source parser as configured in the
	 * preferences
	 * 
	 * @param sourceParsers list of available source parsers
	 * 
	 * @return source parser
	 */
	ISourceParser select(ISourceParser[] sourceParsers);
}
