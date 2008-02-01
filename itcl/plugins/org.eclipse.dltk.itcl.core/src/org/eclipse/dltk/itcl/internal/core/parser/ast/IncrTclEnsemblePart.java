package org.eclipse.dltk.itcl.internal.core.parser.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.statements.Block;

/**
 * Contains ITcl ensemble part
 */
public class IncrTclEnsemblePart extends Declaration {
	private Block body;

	protected List arguments = new ArrayList();

	public IncrTclEnsemblePart(int start, int end) {
		super(start, end);
	}

	public void acceptBody(Block block) {
		this.acceptBody(block, true);
	}

	public void acceptBody(Block block, boolean replace) {
		this.body = block;

		if (block != null) {
			if (replace) {
				this.setEnd(block.sourceEnd());
			}
		}
	}

	public List getStatements() {
		if (this.body == null) {
			this.body = new Block(this.sourceStart(), this.sourceEnd());
		}
		return this.body.getStatements();
	}

	public Block getBody() {
		return this.body;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			// Arguments
			if (this.arguments != null) {
				Iterator it = this.arguments.iterator();
				while (it.hasNext()) {
					Argument arg = (Argument) it.next();
					arg.traverse(visitor);
				}
			}
			// Body
			if (this.body != null) {
				this.body.traverse(visitor);
			}
			//
			visitor.endvisit(this);
		}
	}

	public List getArguments() {
		return this.arguments;
	}

	public void addArgument(Argument arg) {
		this.arguments.add(arg);
	}

	public void acceptArguments(List arguments) {
		this.arguments = arguments;
	}
}
