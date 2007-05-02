package org.eclipse.dltk.ruby.ast;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.CallArgumentsList;

public class RubyCallArgumentsList extends CallArgumentsList {
	
	public void addArgument (ASTNode value, int kind) {
		if (value == null)
			return;
		RubyCallArgument r = new RubyCallArgument(value, kind);
		this.addNode(r);
	}
	
	public void autosetOffsets () {
		List expressions = this.getChilds();
		int size = expressions.size();
		if (size > 0) { ASTNode first = (ASTNode) expressions.get(0); ASTNode last = (ASTNode) expressions.get(size - 1);
			this.setStart(first.sourceStart());
			this.setEnd (last.sourceEnd());
		}
	}

}
