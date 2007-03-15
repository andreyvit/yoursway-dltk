package org.eclipse.dltk.ti.types;


public class RecursionTypeCall implements IEvaluatedType
{

	public static final IEvaluatedType INSTANCE = new RecursionTypeCall();

	/**
	 * The constructor is private so that we can rely on comparing with
	 * <code>INSTANCE</code>.
	 */
	private RecursionTypeCall() {
	}
	
	public String getTypeName( ) {

		return "recursion type call";
	}

	public boolean subtypeOf(IEvaluatedType type) {
		// TODO Auto-generated method stub
		return false;
	}

}
