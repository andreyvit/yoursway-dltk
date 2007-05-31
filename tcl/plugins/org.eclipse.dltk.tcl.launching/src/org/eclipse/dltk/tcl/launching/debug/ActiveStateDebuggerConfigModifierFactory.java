package org.eclipse.dltk.tcl.launching.debug;
import org.eclipse.dltk.launching.IInterpreterConfigModifier;
import org.eclipse.dltk.launching.IInterpreterConfigModifierFactory;


public class ActiveStateDebuggerConfigModifierFactory implements
		IInterpreterConfigModifierFactory {

	public ActiveStateDebuggerConfigModifierFactory() {
	}

	public IInterpreterConfigModifier createInterpreterConfigModifier() {
		return new ActiveStateDebuggerConfigModifier();
	}
}
