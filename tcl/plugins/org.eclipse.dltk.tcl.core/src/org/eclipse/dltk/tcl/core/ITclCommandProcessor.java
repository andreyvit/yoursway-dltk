package org.eclipse.dltk.tcl.core;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;

/**
 * Interface used to build correct AST model for Tcl and XOTcl scripts.
 * @author Haiodo
 *
 */
public interface ITclCommandProcessor {
	/**
	 * Could be called from multiple threads.
	 * Shold be thread safe.
	 * @param parent
	 * @param command
	 */
	ASTNode process( TclCommand command, ITclParser parser, int offset, ASTNode parent );
	/** 
	 * It module are set, processor could append some nodes to module.
	 * Other changes aren't supported.
	 */
	void setCurrentASTTree( ModuleDeclaration module );
	
	void setDetectedParameter(Object parameter);
}
