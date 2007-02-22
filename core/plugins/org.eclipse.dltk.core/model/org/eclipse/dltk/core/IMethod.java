package org.eclipse.dltk.core;

public interface IMethod extends IMember {

	public String[] getParameters() throws ModelException;
	public String[] getParameterInitializers() throws ModelException;
	
	boolean isConstructor() throws ModelException;
	/**
	 * Returns the signature of this method. This includes the signatures for the
	 * parameter types and return type, but does not include the method name,
	 * exception types, or type parameters.
	 * <p>
	 * For example, a source method declared as <code>public void foo(String text, int length)</code>
	 * would return <code>"(QString;I)V"</code>.
	 * </p>
	 * <p>
	 * The type signatures embedded in the method signature may be either unresolved
	 * (for source types) or resolved (for binary types), and either basic (for
	 * basic types) or rich (for parameterized types). See {@link Signature} for
	 * details.
	 * </p>
	 *
	 * @return the signature of this method
	 * @exception ModelException if this element does not exist or if an
	 *      exception occurs while accessing its corresponding resource.
	 * @see Signature
	 */
	String getSignature() throws ModelException;
	
	String getFullyQualifiedName(String enclosingTypeSeparator);
	
	String getFullyQualifiedName();
	
	public String getTypeQualifiedName(String enclosingTypeSeparator, boolean showParameters) throws ModelException;
}