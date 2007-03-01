package org.eclipse.dltk.core;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.core.ISourceModuleInfoCache.ISourceModuleInfo;

public interface ISourceElementParser {
	
	/**
	 * Parses selected contens with ast creation.
	 * @param astCashe TODO
	 */
	ModuleDeclaration parseSourceModule(char[] contents, ISourceModuleInfo astCashe); 
	void setRequirestor( ISourceElementRequestor requestor );
}
