package org.eclipse.dltk.internal.core;

import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.utils.CorePrinter;

public class SourceField extends NamedMember implements IField {

	public SourceField(ModelElement parent, String name) {
		super(parent, name);
	}

	public int getElementType() {
		return FIELD;
	}

	public void printNode(CorePrinter output) {
		output.formatPrint("DLTK Source field:" + getElementName());
	}

	protected char getHandleMementoDelimiter() {
		return JEM_FIELD;
	}
	
	public String getFullyQualifiedName(String enclosingTypeSeparator) {
		try {
			return getFullyQualifiedName(enclosingTypeSeparator, false/*
																		 * don't
																		 * show
																		 * parameters
																		 */);
		} catch (ModelException e) {
			// exception thrown only when showing parameters
			return null;
		}
	}

	public String getFullyQualifiedName() {
		return getFullyQualifiedName("$");
	}	
}
