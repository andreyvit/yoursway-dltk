package org.eclipse.dltk.xotcl.internal.core.parser.processors.xotcl;

import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.xotcl.core.IXOTclModifiers;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclFieldDeclaration;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclProcCallStatement;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclVariableDeclaration;

public class XOTclObjectSetProcessor extends AbstractTclCommandProcessor {

	public XOTclObjectSetProcessor() {
	}

	public ASTNode process(TclStatement statement, ITclParser parser, 
			ASTNode parent) {
		if( this.getDetectedParameter() == null || !(this.getDetectedParameter() instanceof TypeDeclaration ) ) {
			return null;
		}
		TypeDeclaration type = (TypeDeclaration) this.getDetectedParameter();

		if (statement.getCount() < 3) {
			this.report(parser, "Wrong number of arguments.", statement, ProblemSeverities.Error);
			return null;
		}
		Expression variableName = statement.getAt(2);
		if (variableName == null) {
			throw new RuntimeException("empty variable name");
		}
		
		if (3 == statement.getCount()) {
			int start = statement.getAt(1).sourceStart();
			int end = statement.getAt(1).sourceEnd();
			ASTListNode args = new ASTListNode(variableName.sourceStart(), variableName.sourceEnd());
			args.addNode(variableName);
			XOTclProcCallStatement call = new XOTclProcCallStatement(new SimpleReference(start, end,"set"),type,args);
			this.addToParent(parent, call);
			return call;
		}
		else {
			Expression variableValue = null;
			variableValue = statement.getAt(3);
			SimpleReference variable = TclParseUtil.makeVariable(variableName);
			if (null == variable) throw new RuntimeException("empty variable");
			XOTclFieldDeclaration var = new XOTclVariableDeclaration(variable, variableValue, statement.sourceStart(), statement.sourceEnd());
			var.setDeclaringType(type);
			var.setDeclaringTypeName(type.getName());
			var.setModifier(IXOTclModifiers.AccXOTcl);
			this.addToParent(parent, var);
			return var;
		}
	}

}
