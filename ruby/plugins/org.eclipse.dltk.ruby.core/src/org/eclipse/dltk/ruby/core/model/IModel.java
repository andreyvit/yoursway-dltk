package org.eclipse.dltk.ruby.core.model;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.typeinference.ASTCaching;

public interface IModel extends IElement {

//	IElement getElementByPosition(ISourceModule module, int offset);
	
	ModuleDeclaration getASTNode(ISourceModule sourceModule, ASTCaching caching);

}
