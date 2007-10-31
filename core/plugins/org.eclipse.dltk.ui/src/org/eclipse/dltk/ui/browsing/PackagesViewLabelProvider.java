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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.navigator.TreeHierarchyLayoutProblemsDecorator;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.ScriptElementImageProvider;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.dltk.ui.viewsupport.AppearanceAwareLabelProvider;
import org.eclipse.dltk.ui.viewsupport.ColoredString;
import org.eclipse.dltk.ui.viewsupport.ImageDescriptorRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider for the Packages view.
 */
class PackagesViewLabelProvider extends AppearanceAwareLabelProvider {

	static final int HIERARCHICAL_VIEW_STATE = 0;
	static final int FLAT_VIEW_STATE = 1;

	private int fViewState;

	private ImageDescriptorRegistry fRegistry;
	private TreeHierarchyLayoutProblemsDecorator fDecorator;

	PackagesViewLabelProvider(int state) {
		this(state, AppearanceAwareLabelProvider.DEFAULT_TEXTFLAGS
				| ScriptElementLabels.P_COMPRESSED,
				AppearanceAwareLabelProvider.DEFAULT_IMAGEFLAGS
						| ScriptElementImageProvider.SMALL_ICONS);
	}

	PackagesViewLabelProvider(int state, long textFlags, int imageFlags) {
		super(textFlags, imageFlags, DLTKUIPlugin.getDefault()
				.getPreferenceStore());

		Assert.isTrue(isValidState(state));
		fViewState = state;
		fRegistry = DLTKUIPlugin.getImageDescriptorRegistry();

		fDecorator = new TreeHierarchyLayoutProblemsDecorator(isFlatView());
		addLabelDecorator(fDecorator);
	}

	private boolean isValidState(int state) {
		return state == FLAT_VIEW_STATE || state == HIERARCHICAL_VIEW_STATE;
	}

	/*
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object element) {
		if (element instanceof LogicalPackage) {
			LogicalPackage cp = (LogicalPackage) element;
			return getLogicalPackageImage(cp);
		}
		return super.getImage(element);
	}

	/*
	 * Decoration is only concerned with error ticks
	 */
	private Image getLogicalPackageImage(LogicalPackage cp) {
		IScriptFolder[] fragments = cp.getScriptFolders();
		for (int i = 0; i < fragments.length; i++) {
			IScriptFolder fragment = fragments[i];
			if (!isEmpty(fragment)) {
				return decorateCompoundElement(
						DLTKPluginImages.DESC_OBJS_LOGICAL_PACKAGE, cp);
			}
		}
		return decorateCompoundElement(
				DLTKPluginImages.DESC_OBJS_EMPTY_LOGICAL_PACKAGE, cp);
	}

	private Image decorateCompoundElement(ImageDescriptor imageDescriptor,
			LogicalPackage cp) {
		Image image = fRegistry.get(imageDescriptor);
		return decorateImage(image, cp);
	}

	private boolean isEmpty(IScriptFolder fragment) {
		try {
			return (fragment.getSourceModules().length == 0);
		} catch (ModelException e) {
			// ignore
		}
		return false;
	}

	/*
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object element) {
		if (element instanceof IScriptFolder)
			return getText((IScriptFolder) element);
		else if (element instanceof LogicalPackage)
			return getText((LogicalPackage) element);
		else
			return super.getText(element);
	}

	private String getText(IScriptFolder fragment) {
		if (isFlatView())
			return getFlatText(fragment);
		else
			return getHierarchicalText(fragment);
	}

	private String getText(LogicalPackage logicalPackage) {
		IScriptFolder[] fragments = logicalPackage.getScriptFolders();
		return getText(fragments[0]);
	}

	private String getFlatText(IScriptFolder fragment) {
		return super.getText(fragment);
	}

	private boolean isFlatView() {
		return fViewState == FLAT_VIEW_STATE;
	}

	private String getHierarchicalText(IScriptFolder fragment) {
		if (fragment.isRootFolder()) {
			return super.getText(fragment);
		}
		IResource res = fragment.getResource();
		if (res != null && !(res.getType() == IResource.FILE))
			return decorateText(res.getName(), fragment);
		else
			return decorateText(calculateName(fragment), fragment);
	}

	private String calculateName(IScriptFolder fragment) {

		String name = fragment.getElementName();
		if (name.indexOf(".") != -1) //$NON-NLS-1$
			name = name.substring(name.lastIndexOf(".") + 1); //$NON-NLS-1$
		return name;

	}
}
