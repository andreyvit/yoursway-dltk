package org.eclipse.dltk.xotcl.core.tests.parser;

import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.xotcl.core.TclParseUtil.CodeModel;
import org.eclipse.dltk.xotcl.internal.core.parser.XOTclSourceParser;

class TestTclParser extends XOTclSourceParser {
	public TestTclParser( String content ) {
		this.content = content;
		this.codeModel = new CodeModel(content);
	}
	
	public char[] getFileName() {
		return "myfile.tcl".toCharArray();
	}

	public IProblemReporter getProblemReporter() {
		return null;
	}
}