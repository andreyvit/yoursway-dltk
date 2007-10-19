package org.eclipse.dltk.xotcl.core.ast.xotcl;

import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.xotcl.internal.core.parser.XOTclCommandDetector.XOTclGlobalClassParameter;

/**
 * Contains link to external class of this instance variable
 */
public class XOTclExInstanceVariable extends FieldDeclaration {
	private SimpleReference classInstanceName;
	XOTclGlobalClassParameter declaringClassParameter;
	public XOTclExInstanceVariable(String name, int nameStart, int nameEnd,
			int declStart, int declEnd) {
		super(name, nameStart, nameEnd, declStart, declEnd);
	}
	
	public SimpleReference getClassInstanceName() {
		return classInstanceName;
	}

	public void setClassInstanceName(SimpleReference classInstanceName) {
		this.classInstanceName = classInstanceName;
	}

	public XOTclGlobalClassParameter getDeclaringClassParameter() {
		return declaringClassParameter;
	}

	public void setDeclaringClassParameter(
			XOTclGlobalClassParameter declaringClassParameter) {
		this.declaringClassParameter = declaringClassParameter;
	}
}
