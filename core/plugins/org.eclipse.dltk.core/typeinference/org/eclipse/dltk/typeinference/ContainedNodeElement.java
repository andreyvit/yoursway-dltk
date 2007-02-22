package org.eclipse.dltk.typeinference;

import org.eclipse.dltk.ast.ASTNode;

public abstract class ContainedNodeElement extends NodeElement implements IContainedNodeElementInternal {

	private int startOffset, endOffset;
	
	private INodeElement enclosingElement;
	
	public ContainedNodeElement(INodeElement enclosingElement) {
		super();
		this.enclosingElement = enclosingElement;
	}

	protected ASTNode obtainASTNode(ASTCaching caching) {
		((INodeElementInternal) getEnclosingElement()).provideChildrenNodes(caching, this);
		return getASTNode(ASTCaching.CACHED_ONLY);
	}
	
	public void setASTNode(ASTNode node) {
		super.setASTNode(node);
		if (node != null) {
			startOffset = node.sourceStart();
			endOffset = node.sourceEnd();
		}
	}
	
	public ITypeModel getModel() {
		return getUnit().getModel();
	}

	public int compareOffset(int start, int end) {
		if (endOffset <= start)
			return LOCATED_AFTER;
		if (startOffset >= end)
			return LOCATED_BEFORE;
		if (startOffset == start && endOffset == end)
			return LOCATED_EQUAL;
		if (startOffset <= start && endOffset >= end)
			return LOCATED_INSIDE;
		if (startOffset >= start && endOffset <= end)
			return LOCATED_OUTSIDE;
		return LOCATED_INTERSECTS;
	}

	public INodeElement getEnclosingElement() {
		return enclosingElement;
	}

	public IUnit getUnit() {
		return enclosingElement.getUnit();
	}

	public INodeElement getInnermostModelElement(ASTNode node) {
		int res = compareOffset(node.sourceStart(), node.sourceEnd());
		if (res == LOCATED_INSIDE || res == LOCATED_EQUAL)
			return this;
		return null;
	}

}
