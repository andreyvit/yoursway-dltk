package org.eclipse.dltk.evaluation.types;


public class RecursionTypeCall implements IEvaluatedType
{

	public static final IEvaluatedType INSTANCE = new RecursionTypeCall();

	public String getTypeName( ) {

		return "recursion type call";
	}

}
