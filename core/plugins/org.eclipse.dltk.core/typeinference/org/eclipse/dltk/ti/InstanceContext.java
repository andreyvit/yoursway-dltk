package org.eclipse.dltk.ti;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ti.types.ClassType;

public class InstanceContext extends BasicContext implements IInstanceContext {

	private final ClassType instanceType;

	public InstanceContext(ISourceModule sourceModule, ModuleDeclaration rootNode, ClassType instanceType) {
		super(sourceModule, rootNode);
		this.instanceType = instanceType;
	}
	
	public InstanceContext(ISourceModuleContext parent, ClassType instanceType) {
		super(parent);
		this.instanceType = instanceType;
	}

	public ClassType getInstanceType() {
		return instanceType;
	}

}
