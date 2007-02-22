/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.typehierarchy;

import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ITypeHierarchy;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.ScriptElementImageDescriptor;
import org.eclipse.dltk.ui.ScriptElementImageProvider;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.dltk.ui.viewsupport.AppearanceAwareLabelProvider;
import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

/**
 * Label provider for the hierarchy viewers. Types in the hierarchy that are not belonging to the
 * input scope are rendered differntly.
  */
public class HierarchyLabelProvider extends AppearanceAwareLabelProvider {

	private static class FocusDescriptor extends CompositeImageDescriptor {
		private ImageDescriptor fBase;
		public FocusDescriptor(ImageDescriptor base) {
			fBase= base;
		}
		protected void drawCompositeImage(int width, int height) {
			drawImage(getImageData(fBase), 0, 0);
			drawImage(getImageData(DLTKPluginImages.DESC_OVR_FOCUS), 0, 0);
		}
		
		private ImageData getImageData(ImageDescriptor descriptor) {
			ImageData data= descriptor.getImageData(); // see bug 51965: getImageData can return null
			if (data == null) {
				data= DEFAULT_IMAGE_DATA;
				DLTKUIPlugin.logErrorMessage("Image data not available: " + descriptor.toString()); //$NON-NLS-1$
			}
			return data;
		}
		
		protected Point getSize() {
			return ScriptElementImageProvider.BIG_SIZE;
		}
		public int hashCode() {
			return fBase.hashCode();
		}
		public boolean equals(Object object) {
			return object != null && FocusDescriptor.class.equals(object.getClass()) && ((FocusDescriptor)object).fBase.equals(fBase);
		}		
	}

	private Color fGrayedColor;
	private Color fSpecialColor;

	private ViewerFilter fFilter;
	
	private TypeHierarchyLifeCycle fHierarchy;
	
	public HierarchyLabelProvider(TypeHierarchyLifeCycle lifeCycle) {
		super(DEFAULT_TEXTFLAGS | ScriptElementLabels.USE_RESOLVED, DEFAULT_IMAGEFLAGS, null); //XXX
		fHierarchy= lifeCycle;
		fFilter= null;
	}

	/**
	 * @return Returns the filter.
	 */
	public ViewerFilter getFilter() {
		return fFilter;
	}

	/**
	 * @param filter The filter to set.
	 */
	public void setFilter(ViewerFilter filter) {
		fFilter= filter;
	}

	protected boolean isDifferentScope(IType type) {
		if (fFilter != null && !fFilter.select(null, null, type)) {
			return true;
		}
		
		IModelElement input= fHierarchy.getInputElement();
		if (input == null || input.getElementType() == IModelElement.TYPE) {
			return false;
		}
			
		IModelElement parent= type.getAncestor(input.getElementType());
		if (input.getElementType() == IModelElement.PROJECT_FRAGMENT) {
			if (parent == null || parent.getElementName().equals(input.getElementName())) {
				return false;
			}
		} else if (input.equals(parent)) {
			return false;
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see ILabelProvider#getText
	 */ 	
	public String getText(Object element) {
		String text= super.getText(element);
		return decorateText(text, element);
	}	
	
	
	/* (non-Javadoc)
	 * @see ILabelProvider#getImage
	 */ 
	public Image getImage(Object element) {
		Image result= null;
		if (element instanceof IType) {
			ImageDescriptor desc= getTypeImageDescriptor((IType) element);
			if (desc != null) {
				if (element.equals(fHierarchy.getInputElement())) {
					desc= new FocusDescriptor(desc);
				}
				result= DLTKUIPlugin.getImageDescriptorRegistry().get(desc);
			}
		} else {
			result= fImageLabelProvider.getImageLabel(element, evaluateImageFlags(element));
		}
		return decorateImage(result, element);
	}

	private ImageDescriptor getTypeImageDescriptor(IType type) {
		ITypeHierarchy hierarchy= fHierarchy.getHierarchy();
		if (hierarchy == null) {
			return new ScriptElementImageDescriptor(DLTKPluginImages.DESC_OBJS_CLASS, 0, ScriptElementImageProvider.BIG_SIZE);
		}
		
		int flags= hierarchy.getCachedFlags(type);
		if (flags == -1) {
			return new ScriptElementImageDescriptor(DLTKPluginImages.DESC_OBJS_CLASS, 0, ScriptElementImageProvider.BIG_SIZE);
		}
		
		boolean isInner= (type.getDeclaringType() != null);
		
		ImageDescriptor desc= ScriptElementImageProvider.getTypeImageDescriptor(isInner, false, flags, isDifferentScope(type));

		int adornmentFlags= 0;
//		if (Flags.isFinal(flags)) {
//			adornmentFlags |= ScriptElementImageDescriptor.FINAL;
//		}
//		if (Flags.isAbstract(flags) && !isInterface) {
//			adornmentFlags |= ScriptElementImageDescriptor.ABSTRACT;
//		}
//		if (Flags.isStatic(flags)) {
//			adornmentFlags |= ScriptElementImageDescriptor.STATIC;
//		}
		
		return new ScriptElementImageDescriptor(desc, adornmentFlags, ScriptElementImageProvider.BIG_SIZE);
	}
		
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
	 */
	public Color getForeground(Object element) {
		if (element instanceof IMethod) {
			if (fSpecialColor == null) {
				fSpecialColor= Display.getCurrent().getSystemColor(SWT.COLOR_DARK_BLUE);
			}
			return fSpecialColor;
		} else if (element instanceof IType && isDifferentScope((IType) element)) {
			if (fGrayedColor == null) {
				fGrayedColor= Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY);
			}
			return fGrayedColor;
		}
		return null;
	}	
	
	

}
