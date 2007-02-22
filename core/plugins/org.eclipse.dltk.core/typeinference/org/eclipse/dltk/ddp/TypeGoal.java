package org.eclipse.dltk.ddp;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.utils.CorePrinter;

public class TypeGoal implements IGoal {

	private final Expression expr;
	private IContext context;

	private ICalculatedType answer;

	private List parents = new ArrayList();
	private List subgoals = new ArrayList();
	private List modifiedSubgoals = new ArrayList();

	public TypeGoal(Expression expr, IContext context, IGoal parent) {
		this.expr = expr;
		this.context = context;
		if (parent != null)
			this.parents.add(parent);
	}

	public IContext getContext() {
		return context;
	}

	public Collection getParents() {
		return parents;
	}

	public void setParents(Collection parents) {
		this.parents.clear();
		this.parents.addAll(parents);
	}

	public Collection getSubgoals() {
		return subgoals;
	}

	public ICalculatedType getAnswer() {
		return answer;
	}

	public void setAnswer(ICalculatedType answer) {
		this.answer = answer;
	}

	public Expression getExpr() {
		return expr;
	}

	public void subgoalUpdated(IGoal subgoal) {
		this.modifiedSubgoals .add(subgoal);		
	}
	
	public Collection getUpdatedSubgoals () {
		return modifiedSubgoals;
	}
	
	public String toString() {
		StringWriter writer = new StringWriter();
		CorePrinter printer = new CorePrinter(writer);
		this.printNode(printer, "");
		printer.flush();
		printer.close();
		return writer.getBuffer().toString();
	}

	private void printNode(CorePrinter printer, String prefix) {
		printer.print(prefix + "Goal is type of " + expr.toString() + ", ans = " + getAnswer() + " ,");
		//printer.indent();
		if (subgoals.isEmpty()) {
			printer.println(" LEAF");
		} else 
			printer.println(" subgoals:");
			
		for (Iterator iterator = subgoals.iterator(); iterator.hasNext();) {
			TypeGoal name = (TypeGoal) iterator.next(); //XXX: fixme
			name.printNode(printer, prefix + "  ");
		}
		//printer.dedent();
	}

}
