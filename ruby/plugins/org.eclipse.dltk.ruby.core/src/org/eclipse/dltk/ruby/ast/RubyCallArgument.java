package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.utils.CorePrinter;

public class RubyCallArgument extends ASTNode {
	
	public final static int SIMPLE = 0;	
	
	public final static int VARARG = 1;
	
	public final static int BLOCK = 2;
	
	private ASTNode value;
	
	private int kind;

	public ASTNode getValue() {
		return value;
	}

	public void setValue(ASTNode value) {
		this.value = value;
	}

	public RubyCallArgument(ASTNode value) {
		super(value.sourceStart(), value.sourceEnd());
		this.value = value;
	}
	
	public RubyCallArgument(ASTNode value, int kind) {
		super(value.sourceStart(), value.sourceEnd());
		this.value = value;
		this.kind = kind;
	}
	
	public int getArgumentKind() {
		return kind;
	}
	
	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void printNode(CorePrinter output) {
		// TODO Auto-generated method stub

	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (getValue() != null)
				getValue().traverse(visitor);
			visitor.endvisit(this);
		}
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof RubyCallArgument))
			return false;
		RubyCallArgument arg = (RubyCallArgument) obj;
		return (arg.kind == kind && arg.value == value);
	}

	
	
	

}
