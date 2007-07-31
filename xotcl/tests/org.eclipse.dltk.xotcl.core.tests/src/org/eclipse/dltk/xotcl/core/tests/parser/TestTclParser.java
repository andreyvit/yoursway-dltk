package org.eclipse.dltk.xotcl.core.tests.parser;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.xotcl.core.ITclParser;
import org.eclipse.dltk.xotcl.core.TclParseUtil;
import org.eclipse.dltk.xotcl.core.TclParseUtil.CodeModel;

class TestTclParser implements ITclParser {
	private String content;
	private CodeModel codeModel;
	public TestTclParser( String content ) {
		this.content = content;
		this.codeModel = new CodeModel(content);
	}
	public CodeModel getCodeModel() {
		return this.codeModel;
	}
	public String substring( int start, int end ) {
		return this.content.substring(start, end);
	}

	public String getContent() {
		return this.content;
	}

	public char[] getFileName() {
		return "myfile.tcl".toCharArray();
	}

	public IProblemReporter getProblemReporter() {
		return null;
	}

	public void parse(String content, int offset, ASTNode parent) {
	}

	public ASTNode processLocal(TclCommand command, int offset) {
		return TclParseUtil.convertToAST(command, this, offset, this.getContent(), 0);
	}
	public int getStartPos() {
		return 0;
	}
}