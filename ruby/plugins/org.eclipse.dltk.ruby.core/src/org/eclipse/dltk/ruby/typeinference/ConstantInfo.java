/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ast.ASTNode;

public class ConstantInfo {

	private final ClassInfo container;

	private final String name;

	private final ASTNode declaration;

	public ConstantInfo(ClassInfo container, ASTNode declaration, String name) {
		this.container = container;
		this.declaration = declaration;
		this.name = name;
	}

	public ClassInfo getContainer() {
		return container;
	}

	public String getName() {
		return name;
	}

	public ASTNode getDeclaration() {
		return declaration;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj instanceof ConstantInfo) {
			ConstantInfo peer = (ConstantInfo) obj;
			return name.equals(peer.name)
					&& (container == peer.container || container != null
							&& container.equals(peer.container))
					&& declaration.sourceStart() == peer.declaration.sourceStart()
					&& declaration.sourceEnd() == peer.declaration.sourceEnd();
		}
		return false;
	}

	public int hashCode() {
		return name.hashCode() ^ (container == null ? 0 : container.hashCode())
				^ declaration.sourceStart();
	}

}
