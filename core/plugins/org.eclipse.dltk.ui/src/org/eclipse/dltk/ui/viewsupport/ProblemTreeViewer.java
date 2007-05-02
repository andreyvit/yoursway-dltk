/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.ui.viewsupport;

import java.util.ArrayList;

import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.internal.ui.IWorkingCopyProvider;
import org.eclipse.dltk.ui.ProblemsLabelDecorator.ProblemsLabelChangedEvent;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Widget;


/**
 * Extends a  TreeViewer to allow more performance when showing error ticks.
 * A <code>ProblemItemMapper</code> is contained that maps all items in
 * the tree to underlying resource
 */
public class ProblemTreeViewer extends TreeViewer implements ResourceToItemsMapper.IContentViewerAccessor {

	protected ResourceToItemsMapper fResourceToItemsMapper;

	/*
	 * @see TreeViewer#TreeViewer(Composite)
	 */
	public ProblemTreeViewer(Composite parent) {
		super(parent);
		initMapper();
	}

	/*
	 * @see TreeViewer#TreeViewer(Composite, int)
	 */
	public ProblemTreeViewer(Composite parent, int style) {
		super(parent, style);
		initMapper();
	}

	/*
	 * @see TreeViewer#TreeViewer(Tree)
	 */
	public ProblemTreeViewer(Tree tree) {
		super(tree);
		initMapper();
	}

	public void doUpdateItem(Widget item) {
		doUpdateItem(item, item.getData(), true);
	}
	
	private void initMapper() {
		fResourceToItemsMapper= new ResourceToItemsMapper(this);
	}
	
	
	/*
	 * @see StructuredViewer#mapElement(Object, Widget)
	 */
	protected void mapElement(Object element, Widget item) {
		super.mapElement(element, item);
		if (item instanceof Item) {
			fResourceToItemsMapper.addToMap(element, (Item) item);
		}
	}

	/*
	 * @see StructuredViewer#unmapElement(Object, Widget)
	 */
	protected void unmapElement(Object element, Widget item) {
		if (item instanceof Item) {
			fResourceToItemsMapper.removeFromMap(element, (Item) item);
		}		
		super.unmapElement(element, item);
	}

	/*
	 * @see StructuredViewer#unmapAllElements()
	 */
	protected void unmapAllElements() {
		fResourceToItemsMapper.clearMap();
		super.unmapAllElements();
	}

	/*
	 * @see ContentViewer#handleLabelProviderChanged(LabelProviderChangedEvent)
	 */
	protected void handleLabelProviderChanged(LabelProviderChangedEvent event) {
		if (event instanceof ProblemsLabelChangedEvent) {
			ProblemsLabelChangedEvent e= (ProblemsLabelChangedEvent) event;
			if (!e.isMarkerChange() && canIgnoreChangesFromAnnotionModel()) {
				return;
			}
		}
		Object[] changed= addAditionalProblemParents(event.getElements());
		
		if (changed != null && !fResourceToItemsMapper.isEmpty()) {
			ArrayList others= new ArrayList();
			for (int i= 0; i < changed.length; i++) {
				Object curr= changed[i];
				if (curr instanceof IResource) {
					fResourceToItemsMapper.resourceChanged((IResource) curr);
				} else {
					others.add(curr);
				}
			}
			if (others.isEmpty()) {
				return;
			}
			event= new LabelProviderChangedEvent((IBaseLabelProvider) event.getSource(), others.toArray());
		} else {
			// we have modified the list of changed elements via add additional parents.
			if (event.getElements() != changed)
				event= new LabelProviderChangedEvent((IBaseLabelProvider) event.getSource(), changed);
		}
		super.handleLabelProviderChanged(event);
	}
	
	/**
	 * Answers whether this viewer can ignore label provider changes resulting from
	 * marker changes in annotation models
	 */
	private boolean canIgnoreChangesFromAnnotionModel() {
		Object contentProvider= getContentProvider();
		return contentProvider instanceof IWorkingCopyProvider && !((IWorkingCopyProvider)contentProvider).providesWorkingCopies();
	}
	
		
	/**
	 * Decides if {@link #isExpandable(Object)} should also test filters. The default behaviour is to
	 * do this only for IMembers. Implementors can replace this behaviour.
	 * @param parent the given element
	 * @return returns if if {@link #isExpandable(Object)} should also test filters for the given element.
	 */
	protected boolean evaluateExpandableWithFilters(Object parent) {
		return parent instanceof IMember;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.AbstractTreeViewer#isExpandable(java.lang.Object)
	 */
	public boolean isExpandable(Object parent) {
		if (hasFilters() && evaluateExpandableWithFilters(parent)) {
			// workaround for 65762
			Object[] children= getRawChildren(parent);
			if (children.length > 0) {
				ViewerFilter[] filters= getFilters();
				for (int i = 0; i < children.length; i++) {
					if (!isFiltered(children[i], parent, filters)) {
						return true;
					}
				}
			}
			return false;
		}
		return super.isExpandable(parent);
	}
	
    protected final boolean hasFilteredChildren(Object parent) {
		Object[] children = getRawChildren(parent);
		if (children.length == 0) {
			return false;
		}
		if (!hasFilters()) {
			return children.length > 0;
		}
		ViewerFilter[] filters = getFilters();

		for (int i = 0; i < children.length; i++) {
			Object object = children[i];
			if (!isFiltered(object, parent, filters)) {
				return true;
			}
		}
		return false;
    }
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.StructuredViewer#filter(java.lang.Object[])
	 */
	protected final Object[] filter(Object[] elements) {
		ViewerFilter[] filters= getFilters();
		if (filters == null || filters.length == 0)
			return elements;
		
		ArrayList filtered= new ArrayList(elements.length);
		Object root= getRoot();
		for (int i= 0; i < elements.length; i++) {
			Object curr= elements[i];
			if (!isFiltered(curr, root, filters))
				filtered.add(curr);
		}
		return filtered.toArray();
	}
	
	protected boolean isFiltered(Object object, Object parent, ViewerFilter[] filters) {
		for (int i = 0; i < filters.length; i++) {
			ViewerFilter filter = filters[i];
			if (!filter.select(this, parent, object))
				return true;
		}
		return false;
	}
	
	protected Object[] addAditionalProblemParents(Object[] elements) {
		return elements;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.StructuredViewer#handleInvalidSelection(org.eclipse.jface.viewers.ISelection, org.eclipse.jface.viewers.ISelection)
	 */
	protected void handleInvalidSelection(ISelection invalidSelection, ISelection newSelection) {
		// workaround for bug 125708: TODO: Remove when bug 125708 is fixed
		if (!invalidSelection.isEmpty() && newSelection.isEmpty() && invalidSelection instanceof ITreeSelection) {
			newSelection= new StructuredSelection(((IStructuredSelection) invalidSelection).toArray());
			setSelection(newSelection);
		}
		super.handleInvalidSelection(invalidSelection, newSelection);
	}
}

