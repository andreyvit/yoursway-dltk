package org.eclipse.dltk.tcl.internal.parser;

import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.ast.parser.ISourceParserFactory;

/**
 * Returns instances of the Tcl source parser
 */
public class TclSourceParserFactory implements ISourceParserFactory {

	/*
	 * @see org.eclipse.dltk.ast.parser.ISourceParserFactory#createSourceParser()
	 */
	public ISourceParser createSourceParser() {
		return new TclSourceParser();
	}

}
