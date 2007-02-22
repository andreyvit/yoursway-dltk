package org.eclipse.dltk.ruby.core.model;

public interface ITypeFragment extends IElement {
	
	IType[] getSuperTypes ();

	ITypeFragment[] getTypes ();
		
	IMethod[] getMethods ();
	
	IVariable[] getVariables ();		
	
	TypeKind getKind();

}
