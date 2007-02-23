package org.eclipse.dltk.evaluation.types;

import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IType;

public class CombinedClassType implements IClassType {

	String[] fqn;
	IType[] fragments;
	IMethod[] methods;
	
	
	
	public CombinedClassType(String[] fqn, IType[] fragments, IMethod[] methods) {
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

}
