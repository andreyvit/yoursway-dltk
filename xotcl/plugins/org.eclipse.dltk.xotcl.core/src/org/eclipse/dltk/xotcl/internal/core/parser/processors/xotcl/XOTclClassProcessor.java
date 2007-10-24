package org.eclipse.dltk.xotcl.internal.core.parser.processors.xotcl;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.internal.parser.TclParseUtils;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.xotcl.core.IXOTclModifiers;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclClassParameterDeclaration;

public class XOTclClassProcessor extends AbstractTclCommandProcessor {

	public ASTNode process(TclCommand command, ITclParser parser, int offset,
			ASTNode parent) {
		TclStatement statement = (TclStatement) parser.processLocal(command,
				offset, parent);
		if (statement.getCount() < 2) {
			this.report(parser, "Incorrect XOTcl class declaration", statement,
					ProblemSeverities.Error);
			return null;
		}
		int startIndex = 2;
		SimpleReference name = null;
		Expression nameExpr = statement.getAt(1);
		if (nameExpr instanceof SimpleReference) {
			if (((SimpleReference) nameExpr).getName().equals("create")) {
				// Skip create command. Possibly could not pressent
				startIndex = 3;
				Expression ex = statement.getAt(2);
				if (ex instanceof SimpleReference) {
					name = (SimpleReference) ex;
				} else {
					this.report(parser, "A name expected.", statement,
							ProblemSeverities.Error);
					return null;
				}
			} else {
				name = (SimpleReference) nameExpr;
			}
		} else {
			this.report(parser, "A name or 'create' command expected.",
					nameExpr, ProblemSeverities.Error);
			return null;
		}
		TypeDeclaration type = new TypeDeclaration(name.getName(), name
				.sourceStart(), name.sourceEnd(), statement.sourceStart(),
				statement.sourceEnd());
		// process operations
		for (int i = startIndex; i < statement.getCount(); i++) {
			Expression at = statement.getAt(i);
			if (at instanceof SimpleReference) {
				String value = ((SimpleReference) at).getName();
				if (value.equals("-superclass")) {
					Expression sc = statement.getAt(i + 1);
					if (sc instanceof SimpleReference) {
						type.addSuperClass(sc);
					} else if (sc instanceof TclBlockExpression) {
						String bl = ((TclBlockExpression) sc).getBlock();
						type.addSuperClass(new SimpleReference(
								sc.sourceStart() + 1, sc.sourceEnd() - 1,
								bl.substring(1, bl.length() - 1).trim()));
					}
					i += 1;
				} else if (value.equals("-parameter")) {
					Expression sc = statement.getAt(i + 1);
					if (sc instanceof TclBlockExpression) {
						List/* < Statement > */st = null;

						st = ((TclBlockExpression) sc).parseBlockSimple();

						List arguments = TclParseUtils.parseArguments(st);
						// Lets add all arguments as variable declarations for
						// selected class.
						for (int j = 0; j < arguments.size(); j++) {
							Argument e = (Argument) arguments.get(j);
							SimpleReference ref = e.getRef();
							XOTclClassParameterDeclaration decl = new XOTclClassParameterDeclaration(
									ref, (Expression) e.getInitialization(), e
											.sourceStart(), e.sourceEnd());
							type.getStatements().add(decl);
						}
					}
					i += 1;
				} else if (value.equals("-")) {
					// TODO ?
				}
			}
		}

		type.setModifiers(IXOTclModifiers.AccXOTcl);
		this.addToParent(parent, type);
		return type;
	}
}
