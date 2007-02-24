package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ddp.BasicContext;
import org.eclipse.dltk.evaluation.types.IEvaluatedType;

public class InstanceContext extends BasicContext {

	private final IEvaluatedType instanceType;

	public InstanceContext(ISourceModule sourceModule, ModuleDeclaration rootNode, IEvaluatedType instanceType) {
		super(sourceModule, rootNode);
		this.instanceType = instanceType;
	}
	
	public InstanceContext(BasicContext parent, IEvaluatedType instanceType) {
		super(parent);
		this.instanceType = instanceType;
	}

	public IEvaluatedType getInstanceType() {
		return instanceType;
	}

}
