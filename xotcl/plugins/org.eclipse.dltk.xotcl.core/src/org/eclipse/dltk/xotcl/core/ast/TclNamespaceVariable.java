package org.eclipse.dltk.xotcl.core.ast;

import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.references.SimpleReference;

public class TclNamespaceVariable extends FieldDeclaration {

	public TclNamespaceVariable(SimpleReference ref, int declStart, int declEnd) {
		super(ref.getName(), ref.sourceStart(), ref.sourceEnd(), declStart,
				declEnd);
	}
}
