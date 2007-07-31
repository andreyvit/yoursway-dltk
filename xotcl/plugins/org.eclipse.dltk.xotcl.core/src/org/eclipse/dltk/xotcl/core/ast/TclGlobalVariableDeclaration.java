package org.eclipse.dltk.xotcl.core.ast;

import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.references.SimpleReference;

/**
 * Could contain not one global variable declaration.
 * @author Haiodo
 *
 */
public class TclGlobalVariableDeclaration extends FieldDeclaration {
	public TclGlobalVariableDeclaration(String name, int nameStart,
			int nameEnd, int declStart, int declEnd) {
		super(name, nameStart, nameEnd, declStart, declEnd);
	}
	public TclGlobalVariableDeclaration(SimpleReference ref, int declStart, int declEnd) {
		super(ref.getName(), ref.sourceStart(), ref.sourceEnd(), declStart, declEnd);
	}
}
