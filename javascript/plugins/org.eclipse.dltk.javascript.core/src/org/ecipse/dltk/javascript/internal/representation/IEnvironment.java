package org.ecipse.dltk.javascript.internal.representation;

import org.eclipse.dltk.internal.javascript.typeinference.IReference;


public interface IEnvironment {
	public IReference query(String name);	
}
