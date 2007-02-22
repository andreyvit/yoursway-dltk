package org.eclipse.dltk.internal.core;

import java.util.ArrayList;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.CompletionRequestor;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.WorkingCopyOwner;
import org.eclipse.dltk.utils.CorePrinter;


public class SourceType extends NamedMember implements IType {

	public SourceType(ModelElement parent, String name) {
		super(parent, name);
	}

	public int getElementType() {
		return TYPE;
	}

	public String[] getSuperClasses() throws ModelException {
		SourceTypeElementInfo info = (SourceTypeElementInfo) this
				.getElementInfo();
		return info.superclassNames;
	}

	public void printNode(CorePrinter output) {
		output.formatPrint("DLTK Source Type:" + getElementName());
		output.indent();
		try {
			IModelElement modelElements[] = this.getChildren();
			for (int i = 0; i < modelElements.length; ++i) {
				IModelElement element = modelElements[i];
				if (element instanceof ModelElement) {
					((ModelElement) element).printNode(output);
				} else {
					output.print("Unknown element:" + element);
				}
			}
		} catch (ModelException ex) {
			output.formatPrint(ex.getLocalizedMessage());
		}
		output.dedent();
	}

	public IField getField(String fieldName) {
		return new SourceField(this, fieldName);
	}

	public IField[] getFields() throws ModelException {
		ArrayList list = getChildrenOfType(FIELD);
		IField[] array = new IField[list.size()];
		list.toArray(array);
		return array;
	}

	public IType getType(String typeName) {
		return new SourceType(this, typeName);
	}

	public IType[] getTypes() throws ModelException {
		ArrayList list = getChildrenOfType(TYPE);
		IType[] array = new IType[list.size()];
		list.toArray(array);
		return array;
	}

	public IMethod getMethod(String selector) {
		return new SourceMethod(this, selector);
	}

	public IMethod[] getMethods() throws ModelException {
		ArrayList list = getChildrenOfType(METHOD);
		IMethod[] array = new IMethod[list.size()];
		list.toArray(array);
		return array;
	}

	protected char getHandleMementoDelimiter() {
		return JEM_TYPE;
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

	public void codeComplete(char[] snippet, int insertion, int position,
			char[][] localVariableTypeNames, char[][] localVariableNames,
			int[] localVariableModifiers, boolean isStatic,
			CompletionRequestor requestor) throws ModelException {
		// TODO Auto-generated method stub

	}

	public void codeComplete(char[] snippet, int insertion, int position,
			char[][] localVariableTypeNames, char[][] localVariableNames,
			int[] localVariableModifiers, boolean isStatic,
			CompletionRequestor requestor, WorkingCopyOwner owner)
			throws ModelException {
		// TODO Auto-generated method stub

	}

	public IScriptFolder getScriptFolder() {
		IModelElement parentElement = this.parent;
		while (parentElement != null) {
			if (parentElement.getElementType() == IModelElement.SCRIPT_FOLDER) {
				return (IScriptFolder) parentElement;
			} else {
				parentElement = parentElement.getParent();
			}
		}
		Assert.isTrue(false); // should not happen
		return null;
	}
	/**
	 * @see IType#getTypeQualifiedName()
	 */
	public String getTypeQualifiedName() {
		return this.getTypeQualifiedName("$");
	}
	/**
	 * @see IType#getTypeQualifiedName(char)
	 */
	public String getTypeQualifiedName(String enclosingTypeSeparator) {
		try {
			return getTypeQualifiedName(enclosingTypeSeparator, false/*don't show parameters*/);
		} catch (ModelException e) {
			// exception thrown only when showing parameters
			return null;
		}
	}
	/*
	 * @see IType
	 */
	public IMethod[] findMethods(IMethod method) {
		try {
			return findMethods(method, getMethods());
		} catch (ModelException e) {
			// if type doesn't exist, no matching method can exist
			return null;
		}
	}
}
