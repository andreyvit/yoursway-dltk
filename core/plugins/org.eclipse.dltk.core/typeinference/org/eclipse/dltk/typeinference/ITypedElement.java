package org.eclipse.dltk.typeinference;

public interface ITypedElement extends IElement {

	ITypeDescriptor getCalculatedType();
	
}
