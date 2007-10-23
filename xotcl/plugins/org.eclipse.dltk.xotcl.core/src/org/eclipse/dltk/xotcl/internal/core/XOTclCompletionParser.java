package org.eclipse.dltk.xotcl.internal.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.codeassist.complete.CompletionNodeFound;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.internal.core.codeassist.completion.CompletionOnKeywordArgumentOrFunctionArgument;
import org.eclipse.dltk.tcl.internal.core.codeassist.completion.TclCompletionParser;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodCallStatement;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclProcCallStatement;

public class XOTclCompletionParser extends TclCompletionParser {
	protected CompletionVisitor createCompletionVisitor(int position) {
		return new CompletionVisitor(position, this.getModule()) {
			public boolean visit(Expression s) throws Exception {
				if (s.sourceStart() <= position && s.sourceEnd() >= position) {
					List exprs = new ArrayList();
					if (s instanceof XOTclMethodCallStatement) {
						XOTclMethodCallStatement pcs = (XOTclMethodCallStatement) s;

						exprs.add(pcs.getInstNameRef());
						exprs.add(pcs.getCallName());
						if (pcs.getArgs() != null ) {
							exprs.addAll(pcs.getArgs().getChilds());
						}
						processArgumentCompletion(s, exprs);
					}
				}
				return true;
			}
			public boolean visit(Statement s) throws Exception {
				if (s.sourceStart() <= position && s.sourceEnd() >= position) {
					List exprs = new ArrayList();
					if (s instanceof XOTclProcCallStatement) {
						XOTclProcCallStatement pcs = (XOTclProcCallStatement) s;

						exprs.add(pcs.getInstNameRef());
						exprs.add(pcs.getCallName());
						if (pcs.getArguments() != null) {
							exprs.addAll(pcs.getArguments().getChilds());
						}
						processArgumentCompletion(s, exprs);
					}
				}

				return super.visit(s);
			}
			private void processArgumentCompletion(Statement s, List exprs) {
				TclStatement statement = new TclStatement(exprs);
				statement.setStart(s.sourceStart());
				statement.setEnd(s.sourceEnd());

				ASTNode completionNode = null;
				for (int i = 0; i < exprs.size(); ++i) {
					ASTNode n = (ASTNode) exprs.get(i);
					if (n.sourceStart() <= position
							&& n.sourceEnd() >= position) {
						completionNode = n;
					}
				}
				String token = "";
				if (completionNode != null
						&& completionNode instanceof SimpleReference) {
					token = ((SimpleReference) completionNode).getName();
				}
				String[] keywords;
				if (token == null) {
					keywords = checkKeywords("", MODULE);
				} else {
					keywords = checkKeywords(token, MODULE);
				}

				ASTNode nde = new CompletionOnKeywordArgumentOrFunctionArgument(
						token, completionNode, statement, keywords);
				assistNodeParent = TclParseUtil.getPrevParent(module, s);
				throw new CompletionNodeFound(nde, null/* ((TypeDeclaration)inNode).scope */);
			}
		};
	}
}
