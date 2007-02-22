package org.eclipse.dltk.typeinference;

import java.lang.ref.SoftReference;

import org.eclipse.dltk.ast.ASTNode;

public abstract class NodeElement extends Element implements INodeElementInternal {

	private SoftReference astNode;

	public ASTNode getASTNode(ASTCaching caching) {
		ASTNode result = null;
		if (astNode != null && caching != ASTCaching.REPARSE)
			result = (ASTNode) astNode.get();
		if (result == null && caching != ASTCaching.CACHED_ONLY) {
			result = obtainASTNode(caching);
			setASTNode(result);
		}
		return result;
	}

	public void setASTNode(ASTNode node) {
		ASTNode oldNode = getASTNode(ASTCaching.CACHED_ONLY);
		if (oldNode != null) 
			getModel().removeNodeMapping(oldNode);
		if (node != null) {
			astNode = new SoftReference(node);
			getModel().putNodeMapping(node, this);
			synchronizeChildren(node, false);
		}
	}

	protected abstract ASTNode obtainASTNode(ASTCaching caching);
	
	protected abstract void synchronizeChildren(ASTNode node, boolean offsetsOnly);
	
	public void provideChildrenNodes(ASTCaching caching, IContainedNodeElement requestor) {
		ASTNode node = getASTNode(caching);
		synchronizeChildren(node, true);
	}
	
}
