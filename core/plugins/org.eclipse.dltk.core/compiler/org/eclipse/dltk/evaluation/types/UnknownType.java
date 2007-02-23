package org.eclipse.dltk.evaluation.types;

public class UnknownType implements IEvaluatedType
{

	public static final IEvaluatedType INSTANCE = new UnknownType();
	
	/**
	 * The constructor is private so that we can rely on comparing with
	 * <code>INSTANCE</code>.
	 */
	private UnknownType() {
	}
	
	public String getTypeName( ) {
		return "unknown";
	}

}
