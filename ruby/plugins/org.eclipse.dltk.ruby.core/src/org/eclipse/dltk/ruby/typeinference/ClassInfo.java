/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.evaluation.types.IEvaluatedType;

public class ClassInfo {

	private final TypeDeclaration type;

	private final ISourceModule sm;

	private final RubyClassType et;

	public ClassInfo(TypeDeclaration type, ISourceModule sourceModule, RubyClassType et) {
		this.type = type;
		this.sm = sourceModule;
		this.et = et;
	}

	public IEvaluatedType getEvaluatedType() {
		return et;
	}

	public ISourceModule getSourceModule() {
		return sm;
	}

	public TypeDeclaration getType() {
		return type;
	}

	public String[] getFullyQualifiedName() {
		return et.getFQN();
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj instanceof ClassInfo) {
			ClassInfo peer = (ClassInfo) obj;
			return sm.equals(peer.sm) && et.equals(peer.et)
					&& type.sourceStart() == peer.type.sourceStart()
					&& type.sourceEnd() == peer.type.sourceEnd();
		}
		return false;
	}

	public int hashCode() {
		return sm.hashCode() ^ et.hashCode() ^ type.sourceStart();
	}

}
