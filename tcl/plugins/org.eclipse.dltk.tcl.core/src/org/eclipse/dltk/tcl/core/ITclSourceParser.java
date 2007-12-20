package org.eclipse.dltk.tcl.core;

import org.eclipse.dltk.ast.parser.ISourceParser;

public interface ITclSourceParser extends ISourceParser {
	void setOffset(int offset);
	void setProcessorsState(boolean state);
}
