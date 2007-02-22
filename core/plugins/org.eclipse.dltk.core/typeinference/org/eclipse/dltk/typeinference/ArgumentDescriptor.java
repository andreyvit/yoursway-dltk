package org.eclipse.dltk.typeinference;

import org.eclipse.core.runtime.Assert;

public class ArgumentDescriptor {
	
	private final String name;
	
	private ITypeDescriptor type;
	
	private int index = -1;

	private final IMethodDescriptor method;

	public ArgumentDescriptor(IMethodDescriptor method, String name, ITypeDescriptor type) {
		this.method = method;
		this.name = name;
		this.type = type;
	}
	
	void $setParent(IMethodDescriptor method, int index) {
		Assert.isNotNull(method);
		Assert.isLegal(index >= 0);
		Assert.isTrue(this.index == -1 || this.index == index);
		this.index = index;
	}

	public ITypeDescriptor getType() {
		if (method instanceof UserMethodDescriptor)
			((UserMethodDescriptor) method).needArguments();
		return type;
	}

	public void setType(ITypeDescriptor type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}
	
}
