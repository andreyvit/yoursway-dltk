package org.eclipse.dltk.tcl.core;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.references.TypeReference;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.core.search.matching.PatternLocator;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclExecuteExpression;
import org.eclipse.dltk.tcl.core.BasicTclMatchLocatorParser;
import org.eclipse.dltk.tcl.internal.parser.TclParseUtils;

public class TclMatchLocatorParser extends BasicTclMatchLocatorParser {

	public TclMatchLocatorParser(MatchLocator locator) {
		super(locator);
	}

	private ASTVisitor visitor = new ASTVisitor() {

		public boolean visitGeneral(ASTNode node) throws Exception {
			// XOTclMatchLocatorParser.this.processStatement(node);
			PatternLocator locator = TclMatchLocatorParser.this
					.getPatternLocator();
			if (node instanceof TclStatement) {
				TclStatement statement = (TclStatement) node;
				// process variables.
				FieldDeclaration[] fields = TclParseUtils
						.returnVariableDeclarations(statement);
				for (int k = 0; k < fields.length; ++k) {
					locator.match(fields[k], TclMatchLocatorParser.this
							.getNodeSet());
				}
				TclMatchLocatorParser.this.processReferences(statement);
			} else if (node instanceof FieldDeclaration) {
				locator.match((FieldDeclaration) node,
						TclMatchLocatorParser.this.getNodeSet());
			} else if (node instanceof CallExpression) {
				locator.match((CallExpression) node, TclMatchLocatorParser.this
						.getNodeSet());
			}

			return true;
		}

		public boolean visit(MethodDeclaration s) throws Exception {
			TclMatchLocatorParser.this.getPatternLocator().match(
					TclMatchLocatorParser.this.processMethod(s),
					TclMatchLocatorParser.this.getNodeSet());
			return true;
		}

		public boolean visit(TypeDeclaration s) throws Exception {
			TclMatchLocatorParser.this.getPatternLocator().match(
					TclMatchLocatorParser.this.processType(s),
					TclMatchLocatorParser.this.getNodeSet());
			return true;
		}
	};

	protected void parseBodies(MethodDeclaration method) {
	}

	protected void processStatement(ASTNode node) {
		if (node != null) {
			try {
				node.traverse(visitor);
			} catch (Exception e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}
		}
	}

	protected void processReferences(TclStatement statement) {
		Expression commandId = statement.getAt(0);
		PatternLocator locator;
		locator = this.getPatternLocator();
		if (commandId != null && commandId instanceof SimpleReference) {
			String name = ((SimpleReference) commandId).getName();
			if (name.startsWith("::")) {
				name = name.substring(2);
			}
			if (!kwMap.containsKey(name)) {
				String[] ns = name.split("::");
				for (int i = 0; i < ns.length; ++i) {
					if (ns[i].length() > 0) {
						if (i == ns.length - 1) {
							locator.match(new CallExpression(commandId
									.sourceStart(), commandId.sourceEnd(),
									null, ns[i], null), this.getNodeSet());
						} else {
							locator.match(new TypeReference(commandId
									.sourceStart(), commandId.sourceEnd(),
									ns[i]), this.getNodeSet());
						}
					}
				}
			}
		}
		for (int j = 1; j < statement.getCount(); ++j) {
			Expression st = statement.getAt(j);
			if (st instanceof TclExecuteExpression) {
				TclExecuteExpression expr = (TclExecuteExpression) st;
				List exprs = expr.parseExpression();
				for (int i = 0; i < exprs.size(); ++i) {
					if (exprs.get(i) instanceof TclStatement) {
						this.processReferences((TclStatement) exprs.get(i));
					}
				}
			} else if (st instanceof StringLiteral) {
				int pos = 0;
				StringLiteral literal = (StringLiteral) st;
				String value = literal.getValue();
				pos = value.indexOf("$");
				while (pos != -1) {
					SimpleReference ref = TclParseUtils.findVariableFromString(
							literal, pos);
					if (ref != null) {
						ref.setName(ref.getName().substring(1));
						ref.setEnd(ref.sourceEnd() - 1);
						locator.match(ref, this.getNodeSet());
						pos = pos + ref.getName().length();
					}
					pos = value.indexOf("$", pos + 1);
				}
			} else if (st instanceof SimpleReference) {
				SimpleReference ref = (SimpleReference) st;
				String name = ref.getName();
				if (name.startsWith("$")) { // This is variable usage.
					ref.setName(name.substring(1));
					ref.setEnd(ref.sourceEnd() - 1);
					locator.match(ref, this.getNodeSet());
				}
			}
		}
	}
}
