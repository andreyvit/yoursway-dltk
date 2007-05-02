package org.eclipse.dltk.ast.expressions;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.utils.CorePrinter;

public class CallExpression extends Expression {
	private ASTNode receiver;
	private String name;
	
	private CallArgumentsList args;
	
	public CallExpression(int start, int end, ASTNode receiver, String name, CallArgumentsList args) {
		super(start, end);
		if (name == null){
			throw new IllegalArgumentException();
		}
		
		if (args == null) {
//			throw new IllegalArgumentException();
			args = new CallArgumentsList();
		}
		
		this.receiver = receiver;
		this.name = name;
		this.args = args;
	}
	public CallExpression(ASTNode receiver, String name, CallArgumentsList args) {
		this(0, 0, receiver, name, args );
	}

	public int getKind() {		
		return 0;
	}

	public void traverse(ASTVisitor pVisitor) throws Exception {
		if( pVisitor.visit( this ) ) {
			if( receiver != null ) {
				receiver.traverse( pVisitor );
			}
			
			if( args != null ) {
				args.traverse( pVisitor );
			}
			pVisitor.endvisit( this );
		}
	}
	
	public ASTNode getReceiver() {
		return receiver;
	}
	
	public String getName() {
		return name;
	}
	
	public CallArgumentsList getArgs () {
		return args;
	}
	
	public void printNode(CorePrinter output) {
		output.formatPrint("CallExpression" + this.getSourceRange().toString() + ":");
		if (this.receiver != null) {
			output.formatPrint("{");
			this.receiver.printNode(output);
			output.formatPrint("}.");
		}
		output.formatPrint(this.getName() + "(");
		if (this.getArgs() != null) {
			this.getArgs().printNode(output);
		}
		output.formatPrintLn(")");
	}

}
