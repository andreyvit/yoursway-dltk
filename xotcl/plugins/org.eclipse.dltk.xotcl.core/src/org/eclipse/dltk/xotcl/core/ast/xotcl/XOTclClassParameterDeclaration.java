package org.eclipse.dltk.xotcl.core.ast.xotcl;

import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.tcl.core.ast.TclVariableDeclaration;

public class XOTclClassParameterDeclaration extends TclVariableDeclaration {

	public XOTclClassParameterDeclaration(SimpleReference name,
			Expression initializer, int start, int end) {
		super(name, initializer, start, end);
	}
}
