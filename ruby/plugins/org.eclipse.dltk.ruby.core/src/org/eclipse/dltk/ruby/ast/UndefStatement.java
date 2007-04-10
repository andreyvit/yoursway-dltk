package org.eclipse.dltk.ruby.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;

public class UndefStatement extends Statement {

	private final List parameters = new ArrayList ();

	public List getParameters() {
		return parameters;
	}

	public UndefStatement(int start, int end) {
		super(start, end);
	}
	
	public void addParameter (String p) {
		this.parameters.add(p);
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void printNode(CorePrinter output) {
		// TODO Auto-generated method stub

	}

	public void traverse(ASTVisitor visitor) throws Exception {
		// TODO Auto-generated method stub

	}

}
