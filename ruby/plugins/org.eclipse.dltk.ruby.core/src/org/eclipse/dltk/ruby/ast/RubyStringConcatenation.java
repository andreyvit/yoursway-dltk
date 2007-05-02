package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTListNode;

public class RubyStringConcatenation extends ASTListNode {

	public RubyStringConcatenation(int start, int end) {
		super(start, end);
	}

	public void addNode(ASTNode s) {
		super.addNode(s);
		if (this.sourceStart() == -1)
			this.setStart(s.sourceStart());
		if (this.sourceEnd() == -1)
			this.setEnd(s.sourceEnd());
	}
	
	

}
