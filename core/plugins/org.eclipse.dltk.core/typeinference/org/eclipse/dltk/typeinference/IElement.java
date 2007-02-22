package org.eclipse.dltk.typeinference;

public interface IElement {
	
	IElementKind getKind();
	
	ITypeModel getModel();
	
	IScope getScope();
	
}
