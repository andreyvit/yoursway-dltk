package org.eclipse.dltk.ruby.internal.launching.debug;

import org.eclipse.dltk.launching.IInterpreterConfigModifier;
import org.eclipse.dltk.launching.IInterpreterConfigModifierFactory;

public class BasicDebuggerConfigModifierFactory implements
		IInterpreterConfigModifierFactory {

	public IInterpreterConfigModifier createInterpreterConfigModifier() {
		return new BasicDebuggerConfigModifier();
	}
}
