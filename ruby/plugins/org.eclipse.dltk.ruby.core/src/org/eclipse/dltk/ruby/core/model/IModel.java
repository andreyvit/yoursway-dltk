package org.eclipse.dltk.ruby.core.model;

import org.eclipse.dltk.ast.ASTCaching;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;

public interface IModel extends IElement {

//	IElement getElementByPosition(ISourceModule module, int offset);
	
	ModuleDeclaration getASTNode(ISourceModule sourceModule, ASTCaching caching);

}
