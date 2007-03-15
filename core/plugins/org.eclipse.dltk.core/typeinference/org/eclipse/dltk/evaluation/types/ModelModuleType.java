package org.eclipse.dltk.evaluation.types;

import org.eclipse.dltk.core.IModule;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class ModelModuleType implements IEvaluatedType
{
	private IModule fModule = null;
	private int fStepCount = 1;
	public ModelModuleType( IModule module ) {
		this.fModule = module;
	}
	public ModelModuleType( IModule module, int stepCount ) {
		this.fModule = module;
		this.fStepCount = stepCount;
	}
	public String getTypeName( ) {

		if( this.fModule != null ) {
			return "model module:" + this.fModule.getElementName();
		}
		else {
			return "model module: unknown";
		}
	}
	public IModule getModule( ) {

		return this.fModule;
	}
	public int getStepCount( ) {

		return this.fStepCount;
	}
	public boolean subtypeOf(IEvaluatedType type) {
		// TODO Auto-generated method stub
		return false;
	}

}
