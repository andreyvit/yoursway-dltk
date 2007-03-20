package org.eclipse.dltk.codeassist;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.env.ISourceModule;

public interface IAssistParser {
	/**
	 * Possible this method may be not needed in future.
	 */
	void setSource(ModuleDeclaration unit);

	/**
	 * Used to parse inner content of methods, and other non type or module statements.
	 */
	void parseBlockStatements(ASTNode node, ASTNode unit, int position);
	
	public ModuleDeclaration parse(ISourceModule sourceModule);
	
	// XXX wtf? what should this method do? 
	public ASTNode getAssistNodeParent();

	/*  
	 * Called when element couldn't be found.
	 */
	void handleNotInElement(ASTNode unit, int position);
}
