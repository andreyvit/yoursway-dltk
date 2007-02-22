package org.eclipse.dltk.typeinference;

import java.util.Collection;

public interface IKnownTypeDescriptor extends ITypeDescriptor, IElement {

	String getName();
	
	Collection getMethods();
	
	ITypeModel getModel();
	
	IScope getScope();
}
