package org.eclipse.dltk.xotcl.core;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.xotcl.core.TclParseUtil.CodeModel;

public interface ITclParser {
	// Used to parser inner elements
	void parse( String content, int offset, ASTNode parent );
	CodeModel getCodeModel();
//	String getContent();
	String substring(int start, int end);
	IProblemReporter getProblemReporter();
	char[] getFileName();
	ASTNode processLocal(TclCommand command, int offset, ASTNode parent);
	int getStartPos();
}
