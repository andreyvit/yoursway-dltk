package org.eclipse.dltk.typeinference;

import org.eclipse.dltk.ast.ASTNode;

public interface INodeElementInternal extends INodeElement {
	
	void provideChildrenNodes(ASTCaching caching, IContainedNodeElement requestor);

	void setASTNode(ASTNode node);
	
}
