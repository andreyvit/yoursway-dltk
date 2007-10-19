package org.eclipse.dltk.xotcl.internal.core;

import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.tcl.internal.core.codeassist.completion.TclCompletionParser;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodCallStatement;

public class XOTclCompletionParser extends TclCompletionParser {
	protected CompletionVisitor createCompletionVisitor(int position) {
		return new CompletionVisitor(position, this.getModule()) {

			public boolean visit(Statement s) throws Exception {
				if (s.sourceStart() <= position && s.sourceEnd() >= position) {
					if (s instanceof XOTclMethodCallStatement ) {
						
					}
				}
				return super.visit(s);
			}
		};
	}
}
