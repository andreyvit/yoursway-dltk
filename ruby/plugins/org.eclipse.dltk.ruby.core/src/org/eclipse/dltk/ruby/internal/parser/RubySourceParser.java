package org.eclipse.dltk.ruby.internal.parser;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.dltk.ast.declarations.ISourceParser;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;


public class RubySourceParser implements IExecutableExtension, ISourceParser {
	private int fSkipLength;

	private int fLastBlockExit;

	/**
	 * Parses selected context to module declaration using python parser.
	 *
	 */
	public ModuleDeclaration parse(String content) {// throws
		// SourceParseException
		// {

//		ModuleDeclaration moduleDeclaration = new ModuleDeclaration(content.length());
//		StringReader reader = new StringReader(content);
//		RubyLexer rubyLexer = new RubyLexer(reader);
//		rubyLexer.setSourceHandler( this );
//
//		RubyParser rubyParser = new RubyParser(rubyLexer);
//		rubyParser.setLength(content.length());
//		rubyParser.setModuleDeclaration(moduleDeclaration);
//
//		try {
//			rubyParser.program();
//		} catch (RecognitionException e) {
//			e.printStackTrace();
//			// throw new SourceParseException( e );
//		} catch (TokenStreamException e) {
//			e.printStackTrace();
//			// throw new SourceParseException( e );
//		}

//		return moduleDeclaration;
		return null;
	}

	public void startSkip() {

		fSkipLength = 0;
	}

	public void acceptLine(int column) {

		// this.fTokenStream.new_line += column - 1;
	}

	public void tab(int line, int position, int oldPos) {

		// this.fTokenStream.new_line -= ( position - oldPos - 1 );
	}

	public void skipToken(String string) {

		fSkipLength += string.length();
	}

	public void setLastBlockExit(int column) {

		fLastBlockExit = column;
	}

	public int getLastBlockExit() {
		return this.fLastBlockExit;
	}

	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		// TODO Auto-generated method stub

	}

}
