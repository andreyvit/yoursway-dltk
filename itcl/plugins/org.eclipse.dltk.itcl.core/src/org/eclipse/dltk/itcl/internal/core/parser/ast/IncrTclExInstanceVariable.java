package org.eclipse.dltk.itcl.internal.core.parser.ast;

import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.itcl.internal.core.parser.IncrTclCommandDetector.IncrTclGlobalClassParameter;

/**
 * Contains link to external class of this instance variable
 */
public class IncrTclExInstanceVariable extends FieldDeclaration {
	private SimpleReference classInstanceName;
	IncrTclGlobalClassParameter declaringClassParameter;
	public IncrTclExInstanceVariable(String name, int nameStart, int nameEnd,
			int declStart, int declEnd) {
		super(name, nameStart, nameEnd, declStart, declEnd);
	}
	
	public SimpleReference getClassInstanceName() {
		return classInstanceName;
	}

	public void setClassInstanceName(SimpleReference classInstanceName) {
		this.classInstanceName = classInstanceName;
	}

	public IncrTclGlobalClassParameter getDeclaringClassParameter() {
		return declaringClassParameter;
	}

	public void setDeclaringClassParameter(
			IncrTclGlobalClassParameter declaringClassParameter) {
		this.declaringClassParameter = declaringClassParameter;
	}
}
