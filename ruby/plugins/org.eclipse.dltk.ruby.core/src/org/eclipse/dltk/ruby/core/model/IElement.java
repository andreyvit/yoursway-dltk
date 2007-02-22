package org.eclipse.dltk.ruby.core.model;

import org.eclipse.core.runtime.IProgressMonitor;

public interface IElement {
	
	public final static IElement[] EMPTY_ARRAY = new IElement[0];
	
	IElementKind getElementKind();
	
	IElement getAncestor(IElementCriteria criteria);
	
	IElement[] findChildren(IElementCriteria criteria, String name, IProgressMonitor pm);

}
