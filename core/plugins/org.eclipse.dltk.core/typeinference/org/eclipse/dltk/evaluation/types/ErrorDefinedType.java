package org.eclipse.dltk.evaluation.types;

import org.eclipse.dltk.ti.types.IEvaluatedType;


public class ErrorDefinedType implements IEvaluatedType
{

	public static final IEvaluatedType INSTANCE = new ErrorDefinedType();

	public String getTypeName( ) {

		return "error defined";
	}

	public boolean subtypeOf(IEvaluatedType type) {
		// TODO Auto-generated method stub
		return false;
	}

}
