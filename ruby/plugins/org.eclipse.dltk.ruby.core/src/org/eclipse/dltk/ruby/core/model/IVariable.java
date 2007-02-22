package org.eclipse.dltk.ruby.core.model;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.ast.references.VariableKind;

public interface IVariable extends IElement {

	VariableKind getVariableKind();

	String getName();
	
	ICalculatedType calculateType(IProgressMonitor progress);

}
