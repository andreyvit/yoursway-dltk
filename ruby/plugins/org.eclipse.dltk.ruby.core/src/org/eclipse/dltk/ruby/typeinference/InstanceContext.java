package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ti.BasicContext;
import org.eclipse.dltk.ti.ISourceModuleContext;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class InstanceContext extends BasicContext implements IInstanceContext {

	private final IEvaluatedType instanceType;

	public InstanceContext(ISourceModule sourceModule, ModuleDeclaration rootNode, IEvaluatedType instanceType) {
		super(sourceModule, rootNode);
		this.instanceType = instanceType;
	}
	
	public InstanceContext(ISourceModuleContext parent, IEvaluatedType instanceType) {
		super(parent);
		this.instanceType = instanceType;
	}

	public IEvaluatedType getInstanceType() {
		return instanceType;
	}

}
