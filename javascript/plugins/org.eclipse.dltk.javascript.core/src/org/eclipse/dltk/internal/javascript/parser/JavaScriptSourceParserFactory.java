package org.eclipse.dltk.internal.javascript.parser;

import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.ast.parser.ISourceParserFactory;

/**
 * Returns instances of the JavaScript source parser 
 */
public class JavaScriptSourceParserFactory implements ISourceParserFactory {

	public ISourceParser createSourceParser() {
		return new JavaScriptSourceParser();
	}

}
