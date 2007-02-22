package org.eclipse.dltk.core;

public interface IField extends IMember {
	String getFullyQualifiedName(String enclosingTypeSeparator);
	
	String getFullyQualifiedName();
	
	public String getTypeQualifiedName(String enclosingTypeSeparator, boolean showParameters) throws ModelException;	
}
