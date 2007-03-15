package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ti.types.IEvaluatedType;

public interface IArgumentsContext {

	IEvaluatedType getArgumentType(String name);
	
}
