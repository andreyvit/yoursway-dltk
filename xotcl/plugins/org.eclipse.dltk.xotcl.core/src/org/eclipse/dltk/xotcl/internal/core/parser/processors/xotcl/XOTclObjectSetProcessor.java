package org.eclipse.dltk.xotcl.internal.core.parser.processors.xotcl;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.xotcl.core.IXOTclModifiers;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclFieldDeclaration;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclVariableDeclaration;

public class XOTclObjectSetProcessor extends AbstractTclCommandProcessor {

	public XOTclObjectSetProcessor() {
	}

	public ASTNode process(TclCommand command, ITclParser parser, int offset,
			ASTNode parent) {
		if( this.getDetectedParameter() == null || !(this.getDetectedParameter() instanceof TypeDeclaration ) ) {
			return null;
		}
		TypeDeclaration type = (TypeDeclaration) this.getDetectedParameter();
		
		TclStatement statement = (TclStatement) parser.processLocal(command, offset, parent);
		if (statement.getCount() < 4) {
			// This works like get command
			return null;
		}
		Expression variableName = statement.getAt(2);
		Expression variableValue = null;
		variableValue = statement.getAt(3);
		if (variableName == null) {
			throw new RuntimeException("empty variable name");
		}
		SimpleReference variable = TclParseUtil.makeVariable(variableName);
		XOTclFieldDeclaration var = new XOTclVariableDeclaration(variable, variableValue, statement.sourceStart(), statement.sourceEnd());
		var.setDeclaringType(type);
		var.setDeclaringTypeName(type.getName());
		var.setModifier(IXOTclModifiers.AccXOTcl);
		this.addToParent(parent, var);
		return var;
	}

}
