package org.eclipse.dltk.xotcl.internal.core.parser;

import org.eclipse.dltk.tcl.internal.parser.TclSourceElementParser;
import org.eclipse.dltk.tcl.internal.parser.TclSourceElementRequestVisitor;

public class XOTclSourceElementParser extends TclSourceElementParser {
	protected TclSourceElementRequestVisitor createVisitor() {
		return new XOTclSourceElementRequestVisitor(
				this.getRequestor(), this.getProblemReporter());
	}
}
