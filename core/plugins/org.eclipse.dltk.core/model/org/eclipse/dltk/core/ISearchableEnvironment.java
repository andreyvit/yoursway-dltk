package org.eclipse.dltk.core;

import org.eclipse.dltk.compiler.env.INameEnvironment;
import org.eclipse.dltk.internal.core.NameLookup;

public interface ISearchableEnvironment extends INameEnvironment {
	public NameLookup getNameLookup();
}
