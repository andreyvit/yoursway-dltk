package org.eclipse.dltk.tcl.internal.parser;

import org.eclipse.dltk.compiler.SourceElementRequestVisitor;
import org.eclipse.dltk.core.AbstractSourceElementParser;
import org.eclipse.dltk.tcl.core.TclNature;

public class TclSourceElementParser extends AbstractSourceElementParser {
	
	/*
	 * @see org.eclipse.dltk.core.AbstractSourceElementParser#createVisitor()
	 */
	protected SourceElementRequestVisitor createVisitor() {
		return new TclSourceElementRequestVisitor(this.getRequestor(), this
				.getProblemReporter());
	}

	/*
	 * @see org.eclipse.dltk.core.AbstractSourceElementParser#getNatureId()
	 */
	protected String getNatureId() {
		return TclNature.NATURE_ID;
	}
}
