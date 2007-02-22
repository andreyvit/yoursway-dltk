package org.eclipse.dltk.debug.core.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;

public interface IScriptVariable extends IVariable {
	boolean isConstant() throws DebugException;

	String getTypeString();

	String getValueString();

	boolean hasChildren();

	IScriptVariable[] getChildren();
}
