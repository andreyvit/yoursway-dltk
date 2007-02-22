package org.eclipse.dltk.evaluation.types;


public class UnknownType implements IEvaluatedType
{

	public static final IEvaluatedType INSTANCE = new UnknownType();

	public String getTypeName( ) {

		return "unknown";
	}

}
