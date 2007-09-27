package org.eclipse.dltk.xotcl.core.tests.parser;

import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.tcl.core.TclParseUtil.CodeModel;
import org.eclipse.dltk.tcl.internal.parser.ext.ExtTclSourceParser;

class TestTclParser extends ExtTclSourceParser {
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