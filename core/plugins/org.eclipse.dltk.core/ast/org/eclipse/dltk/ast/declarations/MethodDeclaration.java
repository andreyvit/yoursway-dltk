/*
 * (c) 2002, 2005 xored software and others all rights reserved. http://www.xored.com
 */

package org.eclipse.dltk.ast.declarations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.CompoundStatement;
import org.eclipse.dltk.internal.compiler.lookup.MethodScope;
import org.eclipse.dltk.utils.CorePrinter;

public class MethodDeclaration extends Declaration {
	public MethodScope scope;

	protected int bodyStart = -1;

	protected int bodyEnd = -1;

	protected List arguments = new ArrayList();

	private Block body = new Block();

	private List decorators;

	private String declaringTypeName;

	public MethodDeclaration(DLTKToken function_t, DLTKToken name) {

		super(name, function_t.getColumn(), name.getColumn()
				+ name.getText().length());
	}

	public MethodDeclaration(String name, int nameStart, int nameEnd,
			int declStart, int declEnd) {
		super(declStart, declEnd);
		this.setName(name);
		this.setNameStart(nameStart);
		this.setNameEnd(nameEnd);
	}
	
	

	public MethodDeclaration(int start, int end) {
		super(start, end);
	}

	public void setDecorators(List decorators) {
		this.decorators = decorators;
	}

	public List getDecorators() {
		return this.decorators;
	}

	public int getKind() {
		return D_METHOD;
	}

	public void traverse(ASTVisitor visitor) throws Exception {

		if (visitor.visit(this)) {
			// Deocrators
			if (decorators != null) {
				Iterator it = decorators.iterator();
				while (it.hasNext()) {
					Decorator dec = (Decorator) it.next();
					dec.traverse(visitor);
				}
			}

			// Arguments
			Iterator it = arguments.iterator();
			while (it.hasNext()) {
				Argument arg = (Argument) it.next();
				arg.traverse(visitor);
			}

			// Body
			if (body != null) {
				body.traverse(visitor);
			}

			visitor.endvisit(this);
		}
	}

	public List getArguments() {
		return arguments;
	}
	
	public void addArgument (Argument arg) {
		this.arguments.add(arg);
	}

	public void acceptArguments(List arguments) {
		this.arguments = arguments;
	}

	public void acceptBody(Block block) {
		acceptBody(block, true);
	}
	
	public void setBody (CompoundStatement statement) {
		Block b = new Block (statement.sourceStart(), statement.sourceEnd());
		b.acceptStatements(statement.getStatements());
		acceptBody(b, true);
	}

	public void acceptBody(Block block, boolean replace) {
		this.body = block;

		if (block != null) {
			this.bodyStart = block.sourceStart();
			this.bodyEnd = block.sourceEnd();
			if (replace) {
				this.setEnd(block.sourceEnd());
			}
		}
	}

	public List getStatements() {
		if (body == null) {
			body = new Block(sourceStart(), sourceEnd());
		}
		return body.getStatements();
	}

	public Block getBody() {
		return body;
	}

	public void printNode(CorePrinter output) {
		if (decorators != null) {
			Iterator i = decorators.iterator();
			while (i.hasNext()) {
				((Decorator) i.next()).printNode(output);
			}
		}
		output.formatPrintLn("Method" + this.getSourceRange().toString() 
				+ this.getNameSourceRange().toString() +  ": " + super.toString());
		output.formatPrintLn("(");
		if (this.arguments != null && this.arguments.size() > 0) {
			boolean first = true;
			Iterator i = this.arguments.iterator();
			while (i.hasNext()) {
				Argument argument = (Argument) i.next();
				if (!first) {
					output.formatPrintLn(", ");
				} else {
					first = false;
				}
				argument.printNode(output);
			}
		}
		output.formatPrintLn(")");
		if (this.body != null) {
			this.body.printNode(output);
		}
	}

	public void setDeclaringTypeName(String name) {
		if (name != null && name.length() > 0) {
			this.declaringTypeName = name;
		}
	}

	public String getDeclaringTypeName() {
		return declaringTypeName;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof MethodDeclaration)) {
			return false;
		}
		MethodDeclaration d = (MethodDeclaration) obj;
		if ((this.declaringTypeName == null && d.declaringTypeName != null)
				|| (this.declaringTypeName != null && d.declaringTypeName == null)) {
			return false;
		}
		// Only name.
		return d.name.equals(this.name)
				&& d.nameStart == this.nameStart
				&& d.nameEnd == this.nameEnd
				&& (this.declaringTypeName == null || this.declaringTypeName
						.equals(d.declaringTypeName));
	}
}
