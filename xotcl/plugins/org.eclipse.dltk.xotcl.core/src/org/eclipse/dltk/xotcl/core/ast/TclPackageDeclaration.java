package org.eclipse.dltk.xotcl.core.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;

public class TclPackageDeclaration extends Declaration {
	public final static int STYLE_PROVIDE = 0;
	public final static int STYLE_IFNEEDED = 1;
	public final static int STYLE_REQUIRE = 2;
	public final static int STYLE_FORGET = 3;
	private ASTNode version;
	private boolean exact = false;
	private int style;
	private Expression script = null;

	public TclPackageDeclaration(SimpleReference name, ASTNode version, int style, int start, int end) {
		super(start, end);
		this.setName(name.getName());
		this.setNameStart(name.sourceStart());
		this.setNameEnd(name.sourceEnd());
		this.setStyle(style);
		this.version = version;
	}
	public int getStyle() {
		return this.style;
	}
	public void setStyle(int style) {
		this.style = style;
	}

	public ASTNode getVersion() {
		return this.version;
	}
	public boolean isExact() {
		return this.exact;
	}
	public void setExact(boolean exact) {
		this.exact = exact;
	}
	public Expression getScript() {
		return this.script;
	}
	public void setScript(Expression script) {
		this.script = script;
	}
	public void traverse(ASTVisitor pVisitor) throws Exception {
		if (pVisitor.visit(this)) {
			this.getRef().traverse(pVisitor);
			if( this.version != null ) {
				this.version.traverse(pVisitor);
			}
			if( this.script != null ) {
				this.script.traverse(pVisitor);
			}
			pVisitor.endvisit(this);
		}
	}
}
