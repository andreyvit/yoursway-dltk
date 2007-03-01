package org.eclipse.dltk.ast.expressions;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;

public class CallExpression extends Expression {
	private Statement receiver;
	private String name;
	
	private CallArgumentsList args;
	
	public CallExpression(Statement receiver, String name, CallArgumentsList args) {
		if (name == null){
			throw new IllegalArgumentException();
		}
		
		if (args == null) {
			throw new IllegalArgumentException();
		}
		
		this.receiver = receiver;
		this.name = name;
		this.args = args;
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
	
	public Statement getReceiver() {
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
