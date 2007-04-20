package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.statements.CompoundStatement;
import org.eclipse.dltk.ast.statements.Statement;

public class RubyStringConcatenation extends CompoundStatement {

	public RubyStringConcatenation(int start, int end) {
		super(start, end);
	}

	public void addStatement(Statement s) {
		super.addStatement(s);
		if (this.sourceStart() == -1)
			this.setStart(s.sourceStart());
		if (this.sourceEnd() == -1)
			this.setEnd(s.sourceEnd());
	}
	
	

}
