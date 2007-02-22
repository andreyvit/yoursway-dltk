/*
 * (c) 2002, 2005 xored software and others all rights reserved. http://www.xored.com
 */

package org.eclipse.dltk.ast.declarations;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.PositionInformation;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.internal.core.SourceRange;
import org.eclipse.dltk.utils.CorePrinter;

public abstract class Declaration extends Statement implements Modifiers {
	public final static int D_ARGUMENT = 3000;

	public final static int D_CLASS = 3001;

	public final static int D_METHOD = 3002;

	public final static int D_DECLARATOR = 3004;

	protected int nameStart;

	protected int nameEnd;

	protected String name;

	protected int modifiers;

	protected Declaration() {
		this.modifiers = 0;
		this.nameStart = 0;
		this.nameEnd = -1;
	}

	protected Declaration(int start, int end) {
		super(start, end);
		this.modifiers = 0;
	}

	protected Declaration(DLTKToken name, int start, int end) {
		super(start, end);
		if (name != null) {
			this.name = name.getText();
			this.nameStart = name.getColumn();
			this.nameEnd = nameStart + this.name.length();
		}
	}

	public final int getNameStart() {
		return nameStart;
	}

	public final int getNameEnd() {
		return nameEnd;
	}
	
	protected ISourceRange getNameSourceRange () {
		return new SourceRange(getNameStart(), getNameEnd() - getNameStart() + 1);
	}

	public final String getName() {
		return name;
	}

	public final int getModifiers() {
		return modifiers;
	}

	public final PositionInformation getPositionInformation() {
		return new PositionInformation(nameStart, nameEnd, sourceStart(),
				sourceEnd());
	}

	public final void setModifier(int mods) {
		modifiers |= mods;
	}

	public final void setModifiers(int mods) {
		modifiers = mods;
	}

	public final void setName(String name) {
		this.name = name;
	}

	protected void setNameEnd(int end) {
		nameEnd = end;
	}

	protected void setNameStart(int start) {
		nameStart = start;
	}

	public boolean isStatic() {
		return (modifiers & AccStatic) != 0;
	}

	public boolean isPublic() {
		return (modifiers & AccPublic) != 0;
	}

	public boolean isPrivate() {
		return (modifiers & AccPrivate) != 0;
	}

	public boolean isProtected() {

		return (modifiers & AccProtected) != 0;
	}

	public boolean isFinal() {
		return (modifiers & AccFinal) != 0;
	}

	public boolean isAbstract() {
		return (modifiers & AccAbstract) != 0;
	}

	public boolean isInterface() {
		return (modifiers & AccInterface) != 0;
	}

	public String toString() {
		String buf = "";

		if (this.isStatic()) {
			buf += "static ";
		}
		if (this.name != null) {
			buf += " " + this.name;
		}
		return buf;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void printNode(CorePrinter output) {
		// TODO Auto-generated method stub

	}

	public void traverse(ASTVisitor pVisitor) throws Exception {
		if (pVisitor.visit(this))
			pVisitor.endvisit(this);
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Declaration)) {
			return false;
		}
		Declaration d = (Declaration) obj;
		// Only name.
		return d.name.equals(this.name) && d.nameStart == this.nameStart
				&& d.nameEnd == this.nameEnd && super.equals(obj);
	}

	public int hashCode() {
		return this.name.hashCode();
	}
	
	public String debugString () {
		return super.debugString() + this.getNameSourceRange().toString();
	}
	
}
