/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.ui.browsing;

import java.util.List;

import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.viewsupport.ProblemTreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Widget;

/**
 * Special problem tree viewer to handle logical packages.
 */
public class PackagesViewTreeViewer extends ProblemTreeViewer implements IPackagesViewViewer{

	public PackagesViewTreeViewer(Composite parent, int style) {
		super(parent, style);
//		ColoredViewersManager.install(this);
	}

	/*
	 * @see org.eclipse.jface.viewers.StructuredViewer#mapElement(java.lang.Object, org.eclipse.swt.widgets.Widget)
	 */
	public void mapElement(Object element, Widget item) {
		if (element instanceof LogicalPackage && item instanceof Item) {
			LogicalPackage cp= (LogicalPackage) element;
			IScriptFolder[] fragments= cp.getScriptFolders();
			for (int i= 0; i < fragments.length; i++) {
				IScriptFolder fragment= fragments[i];
				fResourceToItemsMapper.addToMap(fragment, (Item) item);
			}
		}
		super.mapElement(element, item);
	}

	/*
	 * @see org.eclipse.jface.viewers.StructuredViewer#unmapElement(java.lang.Object, org.eclipse.swt.widgets.Widget)
	 */
	public void unmapElement(Object element, Widget item) {

		if (element instanceof LogicalPackage && item instanceof Item) {
			LogicalPackage cp= (LogicalPackage) element;
			IScriptFolder[] fragments= cp.getScriptFolders();
			for (int i= 0; i < fragments.length; i++) {
				IScriptFolder fragment= fragments[i];
				fResourceToItemsMapper.removeFromMap((Object)fragment, (Item)item);
			}
		}
		super.unmapElement(element, item);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jdt.internal.ui.viewsupport.ProblemTreeViewer#isFiltered(java.lang.Object, java.lang.Object, org.eclipse.jface.viewers.ViewerFilter[])
	 */
	protected boolean isFiltered(Object object, Object parent, ViewerFilter[] filters) {
		boolean res= super.isFiltered(object, parent, filters);
		if (res && isEssential(object)) {
			return false;
		}
		return res;
	}

	private boolean isEssential(Object object) {
		try {
			if (object instanceof IScriptFolder) {
				IScriptFolder fragment = (IScriptFolder) object;
				if (!fragment.isRootFolder() && fragment.hasSubfolders()) {
					return hasFilteredChildren(fragment);
				}
			} else if (object instanceof LogicalPackage) {
				LogicalPackage logicalPackage= (LogicalPackage) object;
				if (!logicalPackage.isDefaultPackage() && logicalPackage.hasSubpackages()) {
					return !hasFilteredChildren(object);
				}
			}
		} catch (ModelException e) {
			DLTKUIPlugin.log(e);
		}
		return false;
	}

	// --------- see: IPackagesViewViewer ----------

	public Widget doFindItem(Object element) {
		return super.doFindItem(element);
	}

	public Widget doFindInputItem(Object element) {
		return super.doFindInputItem(element);
	}

	public List getSelectionFromWidget() {
		return super.getSelectionFromWidget();
	}

	public void doUpdateItem(Widget item, Object element, boolean fullMap){
		super.doUpdateItem(item, element, fullMap);
	}

	public void internalRefresh(Object element){
		super.internalRefresh(element);
	}

	public void setSelectionToWidget(List l, boolean reveal){
		super.setSelectionToWidget(l, reveal);
	}
}
