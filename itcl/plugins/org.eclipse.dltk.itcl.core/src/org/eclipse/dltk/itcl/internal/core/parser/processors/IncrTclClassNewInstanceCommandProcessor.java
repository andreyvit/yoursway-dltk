package org.eclipse.dltk.itcl.internal.core.parser.processors;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.itcl.internal.core.IIncrTclModifiers;
import org.eclipse.dltk.itcl.internal.core.parser.IncrTclCommandDetector.IncrTclGlobalClassParameter;
import org.eclipse.dltk.itcl.internal.core.parser.ast.IncrTclExInstanceVariable;
import org.eclipse.dltk.itcl.internal.core.parser.ast.IncrTclInstanceVariable;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;

public class IncrTclClassNewInstanceCommandProcessor extends
		AbstractTclCommandProcessor {

	public IncrTclClassNewInstanceCommandProcessor() {
	}

	public ASTNode process(TclStatement statement, ITclParser parser, 
			ASTNode parent) {
		Object param = this.getDetectedParameter();
		if (param == null
				|| !(param instanceof TypeDeclaration || param instanceof IncrTclGlobalClassParameter)) {
			return null;
		}

		if (statement.getCount() == 1) {
			return null;
		}
		Expression e = statement.getAt(1);
		String name = extractSimpleReference(e);
		if (name == null) {
			this.report(parser,
					"A instance ame expected after class name.", e,
					ProblemSeverities.Error);
			return null;
		}
		
		if (param instanceof TypeDeclaration) {
			TypeDeclaration type = (TypeDeclaration) param;
			IncrTclInstanceVariable var = new IncrTclInstanceVariable(
					name, e.sourceStart(), e.sourceEnd(), statement
							.sourceStart(), statement.sourceEnd());
			var.setClassInstanceName((SimpleReference) statement.getAt(0));
			var.setDeclaringType(type);
			var.setModifier(IIncrTclModifiers.AccIncrTcl);
			this.addToParent(parent, var);
			return var;
		} else {
			IncrTclGlobalClassParameter classParam = (IncrTclGlobalClassParameter) param;
			IncrTclExInstanceVariable var = new IncrTclExInstanceVariable(
					name, e.sourceStart(), e.sourceEnd(), statement
							.sourceStart(), statement.sourceEnd());
			var.setClassInstanceName((SimpleReference) statement.getAt(0));
			var.setDeclaringClassParameter(classParam);
			var.setModifier(IIncrTclModifiers.AccIncrTcl);
			this.addToParent(parent, var);
			return var;
		}
	}

}
