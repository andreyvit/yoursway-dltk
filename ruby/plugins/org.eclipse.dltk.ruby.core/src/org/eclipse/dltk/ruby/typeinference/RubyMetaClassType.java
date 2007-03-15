package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.evaluation.types.IClassType;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class RubyMetaClassType implements IClassType {
	
	public final static RubyMetaClassType OBJECT_METATYPE = new RubyMetaClassType(
			RubyClassType.OBJECT_CLASS, null);

	private IEvaluatedType instanceType;
	private IMethod[] methods;
	
	

	/**
	 * @deprecated
	 */
	public RubyMetaClassType(IEvaluatedType[] instanceTypes, IMethod[] methods) {
		this(instanceTypes[0], methods);
	}
	
	public RubyMetaClassType(IEvaluatedType instanceType, IMethod[] methods) {
		Assert.isLegal(instanceType != null);
		this.instanceType = instanceType;
		this.methods = methods;
	}



	public String getTypeName() {
		return "Metaclass_of(" + instanceType.getTypeName() + ")";
	}



	/**
	 * @deprecated
	 */
	public IEvaluatedType[] getInstanceTypes() {
		return new IEvaluatedType[] {instanceType};
	}

	public IEvaluatedType getInstanceType() {
		return instanceType;
	}

	public IMethod[] getMethods() {
		return methods;
	}
	
	

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj instanceof RubyMetaClassType) {
			RubyMetaClassType peer = (RubyMetaClassType) obj;
			return instanceType.equals(peer.instanceType);
		}
		return false;
	}

	public int hashCode() {
		return instanceType.hashCode() ^ 0x42424242;
	}

	public boolean subtypeOf(IEvaluatedType type) {
		// TODO Auto-generated method stub
		return false;
	}

}
