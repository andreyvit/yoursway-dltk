package org.eclipse.dltk.core;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.ISourceElementRequestor;

public interface ISourceElementParser {
	
	/**
	 * Parses selected contens with ast creation.
	 */
	ModuleDeclaration parseSourceModule(char[] contents); 
	void setRequirestor( ISourceElementRequestor requestor );
}
