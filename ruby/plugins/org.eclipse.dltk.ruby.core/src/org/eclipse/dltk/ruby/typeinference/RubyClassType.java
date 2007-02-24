package org.eclipse.dltk.ruby.typeinference;

import java.util.Arrays;

import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.evaluation.types.IClassType;

public class RubyClassType implements IClassType {

	String[] fqn;
	IType[] fragments;
	IMethod[] methods;
	
	
	
	public RubyClassType(String[] fqn, IType[] fragments, IMethod[] methods) {
		this.fqn = fqn;
		this.fragments = fragments;
		this.methods = methods;
	}


	public String getTypeName() {
		if (fqn != null && fqn.length > 0) {
			return fqn[fqn.length - 1]; 
		}
		return null;
	}
	
	
	public IMethod[] getAllMethods () {
		return methods;
	}
	
	public IType[] getTypeDeclarations () {
		return fragments;
	}
	
	public String[] getFQN() {
		return fqn;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj instanceof RubyClassType) {
			RubyClassType peer = (RubyClassType) obj;
			return Arrays.equals(fqn, peer.fqn);
		}
		return false;
	}

	public int hashCode() {
		return Arrays.hashCode(fqn);
	}

}
