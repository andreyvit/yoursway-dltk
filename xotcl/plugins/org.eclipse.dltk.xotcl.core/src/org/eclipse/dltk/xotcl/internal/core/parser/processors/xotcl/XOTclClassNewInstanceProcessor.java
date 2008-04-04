package org.eclipse.dltk.xotcl.internal.core.parser.processors.xotcl;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.xotcl.core.IXOTclModifiers;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclExInstanceVariable;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclInstanceVariable;
import org.eclipse.dltk.xotcl.internal.core.parser.XOTclCommandDetector.XOTclGlobalClassParameter;

public class XOTclClassNewInstanceProcessor extends AbstractTclCommandProcessor {

	public XOTclClassNewInstanceProcessor() {
	}

	public ASTNode process(TclStatement statement, ITclParser parser, 
			ASTNode parent) {
		Object param = this.getDetectedParameter();
		if (param == null
				|| !(param instanceof TypeDeclaration || param instanceof XOTclGlobalClassParameter)) {
			return null;
		}

		if( statement.getCount() == 1 ) {
			return null;
		}
		Expression e = statement.getAt(1);
		String commandOrName = extractSimpleReference(e);
		if (commandOrName == null) {
			this.report(parser,
					"A command or a name expected after class name.", e,
					ProblemSeverities.Error);
			return null;
		}
		if( statement.getCount() < 3) {
			return null;
		}
		if (commandOrName.equals("create")) {
			e = statement.getAt(2);
			commandOrName = extractSimpleReference(e);
			if (commandOrName == null && e != null ) {
				this.report(parser,
						"A a name expected after 'create' command.", e,
						ProblemSeverities.Error);
				return null;
			}
		}
		if (param instanceof TypeDeclaration) {
			TypeDeclaration type = (TypeDeclaration) param;
			XOTclInstanceVariable var = new XOTclInstanceVariable(
					commandOrName, e.sourceStart(), e.sourceEnd(), statement
							.sourceStart(), statement.sourceEnd());
			var.setClassInstanceName((SimpleReference) statement.getAt(0));
			var.setDeclaringType(type);
			var.setModifier(IXOTclModifiers.AccXOTcl);
			this.addToParent(parent, var);
			return var;
		} else {
			XOTclGlobalClassParameter classParam = (XOTclGlobalClassParameter) param;
			XOTclExInstanceVariable var = new XOTclExInstanceVariable(
					commandOrName, e.sourceStart(), e.sourceEnd(), statement
							.sourceStart(), statement.sourceEnd());
			var.setClassInstanceName((SimpleReference) statement.getAt(0));
			 var.setDeclaringClassParameter(classParam);
			var.setModifier(IXOTclModifiers.AccXOTcl);
			this.addToParent(parent, var);
			return var;
		}
	}
}
