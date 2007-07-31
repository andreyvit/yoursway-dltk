package org.eclipse.dltk.xotcl.core.ast.xotcl;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.utils.CorePrinter;

public class XOTclVariableDeclaration extends FieldDeclaration {
	private Expression initializer;
	private TypeDeclaration type;
	private String name;
	public XOTclVariableDeclaration(SimpleReference name, Expression initializer, int start, int end) {
		super(name.getName(), name.sourceStart(), name.sourceEnd(), start, end);
		this.initializer = initializer;
	}
	public Expression getInitializer() {
		return this.initializer;
	}
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if( this.initializer != null ) {
				this.initializer.traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}
	public void printNode(CorePrinter output) {
		output.formatPrint( "Variable declaration:" + this.getName());
		if( this.initializer != null ) {
			this.initializer.printNode(output);
		}
		output.formatPrintLn("");
	}
	public void setDeclaringType( TypeDeclaration type ) {
		this.type = type;
	}
	public TypeDeclaration getDeclaringType() {
		return this.type;
	}
	public void setDeclaringTypeName(String name) {
		this.name = name;
	}
	public String getDeclaringTypeName() {
		return this.name;
	}
}
