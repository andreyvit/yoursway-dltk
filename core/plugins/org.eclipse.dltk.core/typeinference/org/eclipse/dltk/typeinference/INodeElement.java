package org.eclipse.dltk.typeinference;

import org.eclipse.dltk.ast.ASTNode;

public interface INodeElement extends IElement {

	IUnit getUnit();

	ASTNode getASTNode(ASTCaching caching);
	
	INodeElement getInnermostModelElement(ASTNode node);

}
