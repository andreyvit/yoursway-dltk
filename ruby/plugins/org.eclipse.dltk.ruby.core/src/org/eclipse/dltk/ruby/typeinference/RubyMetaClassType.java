package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.evaluation.types.IClassType;
import org.eclipse.dltk.evaluation.types.IEvaluatedType;

public class RubyMetaClassType implements IClassType {
	
	IEvaluatedType[] instanceTypes;
	IMethod[] methods;
	
	

	public RubyMetaClassType(IEvaluatedType[] instanceTypes, IMethod[] methods) {
		this.instanceTypes = instanceTypes;
		this.methods = methods;
	}



	public String getTypeName() {
		return null;
	}



	public IEvaluatedType[] getInstanceTypes() {
		return instanceTypes;
	}



	public IMethod[] getMethods() {
		return methods;
	}
	
	

}
