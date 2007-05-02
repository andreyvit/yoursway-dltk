/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.navigator;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.ui.navigator.IExtensionStateConstants.Values;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.ScriptElementImageProvider;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.dltk.ui.viewsupport.AppearanceAwareLabelProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;
import org.eclipse.ui.navigator.IExtensionStateModel;


/**
 * Provides the labels for the Project Explorer.
 * <p>
 * It provides labels for the packages in hierarchical layout and in all other
 * cases delegates it to its super class.
 * </p>
 * 
	 *
 */
public class ScriptNavigatorLabelProvider implements ICommonLabelProvider {

	private final long LABEL_FLAGS = ScriptElementLabels.DEFAULT_QUALIFIED
			| ScriptElementLabels.ROOT_POST_QUALIFIED
			| ScriptElementLabels.APPEND_ROOT_PATH
			| ScriptElementLabels.M_PARAMETER_TYPES
			| ScriptElementLabels.M_PARAMETER_NAMES
			| ScriptElementLabels.M_APP_RETURNTYPE
			| ScriptElementLabels.M_EXCEPTIONS
			| ScriptElementLabels.F_APP_TYPE_SIGNATURE
			| ScriptElementLabels.T_TYPE_PARAMETERS;

	private ScriptExplorerLabelProvider delegeteLabelProvider;

	protected ScriptExplorerContentProvider fContentProvider;

	private IExtensionStateModel fStateModel;

	public ScriptNavigatorLabelProvider() {

	}
	public void init(ICommonContentExtensionSite commonContentExtensionSite) {
		fStateModel = commonContentExtensionSite.getExtensionStateModel();
		fContentProvider = (ScriptExplorerContentProvider) commonContentExtensionSite.getExtension().getContentProvider();
		delegeteLabelProvider = createLabelProvider();

		delegeteLabelProvider.setIsFlatLayout(fStateModel
				.getBooleanProperty(Values.IS_LAYOUT_FLAT));
		fStateModel.addPropertyChangeListener(new IPropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				if (Values.IS_LAYOUT_FLAT.equals(event.getProperty())) {
					if (event.getNewValue() != null) {
						boolean newValue = ((Boolean) event.getNewValue())
								.booleanValue() ? true : false;
						delegeteLabelProvider.setIsFlatLayout(newValue);
					}
				}

			}
		});
	}

	public String getDescription(Object element) {
		return formatMessage(element);
	}

	//protected abstract PackageExplorerLabelProvider createLabelProvider();
//	{
//
//		return new PackageExplorerLabelProvider(
//				AppearanceAwareLabelProvider.DEFAULT_TEXTFLAGS
//						| ScriptElementLabels.P_COMPRESSED,
//				AppearanceAwareLabelProvider.DEFAULT_IMAGEFLAGS
//						| ScriptElementImageProvider.SMALL_ICONS,
//				fContentProvider, getPreferenceStare());
//
//	}
	protected ScriptExplorerLabelProvider createLabelProvider() {
		{
			return new ScriptExplorerLabelProvider(
					AppearanceAwareLabelProvider.DEFAULT_TEXTFLAGS | 
					ScriptElementLabels.P_COMPRESSED,
					AppearanceAwareLabelProvider.DEFAULT_IMAGEFLAGS | 
					ScriptElementImageProvider.SMALL_ICONS, 
					fContentProvider,
					getPreferenceStare()) {				
			};
		}
	}

	protected IPreferenceStore getPreferenceStare() {
		return DLTKUIPlugin.getDefault().getPreferenceStore();
	}
	
	public void dispose() {
		delegeteLabelProvider.dispose();
	}

	public void propertyChange(PropertyChangeEvent event) {
		delegeteLabelProvider.propertyChange(event);
	}

	public void addLabelDecorator(ILabelDecorator decorator) {
		delegeteLabelProvider.addLabelDecorator(decorator);
	}

	public void addListener(ILabelProviderListener listener) {
		delegeteLabelProvider.addListener(listener);
	}

	public Color getBackground(Object element) {
		return delegeteLabelProvider.getBackground(element);
	}

	public Color getForeground(Object element) {
		return delegeteLabelProvider.getForeground(element);
	}

	public Image getImage(Object element) {
		return delegeteLabelProvider.getImage(element);
	}

	public boolean isLabelProperty(Object element, String property) {
		return delegeteLabelProvider.isLabelProperty(element, property);
	}

	public void removeListener(ILabelProviderListener listener) {
		delegeteLabelProvider.removeListener(listener);
	}

	public boolean equals(Object obj) {
		return delegeteLabelProvider.equals(obj);
	}

	public int hashCode() {
		return delegeteLabelProvider.hashCode();
	}

	public String toString() {
		return delegeteLabelProvider.toString();
	}

	public String getText(Object element) {
		return delegeteLabelProvider.getText(element);		
	}

	public void setIsFlatLayout(boolean state) {
		delegeteLabelProvider.setIsFlatLayout(state);
	}

	// Taken from StatusBarUpdater

	protected String formatMessage(Object element) {
		if (element instanceof IModelElement) {
			return formatModelElementMessage((IModelElement) element);
		} else if (element instanceof IResource) {
			return formatResourceMessage((IResource) element);
		}
		return ""; //$NON-NLS-1$
	}

	private String formatModelElementMessage(IModelElement element) {
		return ScriptElementLabels.getDefault().getElementLabel(element, LABEL_FLAGS);
	}

	private String formatResourceMessage(IResource element) {
		IContainer parent = element.getParent();
		if (parent != null && parent.getType() != IResource.ROOT)
			return element.getName() + ScriptElementLabels.CONCAT_STRING
					+ parent.getFullPath().makeRelative().toString();
		else
			return element.getName();
	}

	public void restoreState(IMemento memento) {

	}

	public void saveState(IMemento memento) {

	}
}
