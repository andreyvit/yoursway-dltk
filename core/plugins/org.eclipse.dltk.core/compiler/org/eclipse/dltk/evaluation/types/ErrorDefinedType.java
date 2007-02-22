package org.eclipse.dltk.evaluation.types;


public class ErrorDefinedType implements IEvaluatedType
{

	public static final IEvaluatedType INSTANCE = new ErrorDefinedType();

	public String getTypeName( ) {

		return "error defined";
	}

}
