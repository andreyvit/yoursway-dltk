package org.eclipse.dltk.ruby.ast;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

public class RubyBlock extends Expression {

	private Map vars = new HashMap();
	private Statement asterixParameter = null;
	private Statement body;

	public RubyBlock(int start, int end, Statement body) {
		super(start, end);
		this.body = body;
	}

	public Statement getAsterixParameter() {
		return asterixParameter;
	}

	public void setAsterixParameter(Statement asterixParameter) {
		this.asterixParameter = asterixParameter;
	}

	public RubyBlock(int start, int end) {
		super(start, end);
	}

	public Set getVars() {
		return vars.keySet();
	}

	public void addVar(Statement var) {
		addVar(var, null);
	}

	public void addVar(Statement var, Statement defaultValue) {
		vars.put(var, defaultValue);
	}

	public Statement getBody() {
		return body;
	}

	public void setBody(Statement body) {
		this.body = body;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			// if (var != null)
			// var.traverse(visitor);
			if (body != null)
				body.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
