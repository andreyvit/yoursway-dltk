package org.eclipse.dltk.tcl.core.ast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

public class TclSwitchStatement extends Statement {
	
	private static final String END_OF_OPTIONS_MARKER = "--"; 
	
	public static final Set options = new HashSet();
	static {
		String[] opts = {"-exact", "-glob", "-regexp", END_OF_OPTIONS_MARKER};
		options.addAll(Arrays.asList(opts));
	}

	private ASTNode string;
	private ASTListNode alternatives;
	
	public TclSwitchStatement(int startPos, int endPos) {
		this.setStart(startPos);
		this.setEnd(endPos);
	}
	
	public TclSwitchStatement(ASTNode string, List /*<BinaryExpression>*/ alternatives, int startPos, int endPos) {
		this.string = string;
		this.setStart(startPos);
		this.setEnd(endPos);
		this.setAlternatives(alternatives);
	}
	
	public static boolean isOptionsEndMarker(String option) {
		return END_OF_OPTIONS_MARKER.equals(option);
	}
	
	public void setString(ASTNode string) {
		this.string = string;
	}

	public void setAlternatives(List alternatives) {
		if (alternatives.isEmpty()) this.alternatives = null;
		else {
			int start = ((BinaryExpression)alternatives.get(0)).sourceStart();
			int end = ((BinaryExpression)alternatives.get(alternatives.size()-1)).sourceEnd();
			this.alternatives = new ASTListNode(start, end, alternatives);
		}
	}

	public int getKind() {
		return this.S_SWITCH;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this))
		{
			if (null != string) string.traverse(visitor);
			if (null != alternatives) alternatives.traverse(visitor);
			visitor.endvisit(this);
		}
	}
	
	public ASTNode getString() {
		return this.string;
	}
	
	public ASTListNode getAlternatives() {
		return this.alternatives;
	}

}
