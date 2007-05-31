package org.eclipse.dltk.launching;

import org.eclipse.core.runtime.CoreException;

public interface IInterpreterConfigModifier {
	InterpreterConfig modify(String exe, InterpreterConfig config) throws CoreException;
}
