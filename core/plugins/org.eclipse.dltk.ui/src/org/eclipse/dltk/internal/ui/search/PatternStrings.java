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
package org.eclipse.dltk.internal.ui.search;

import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.Signature;
import org.eclipse.dltk.ui.ScriptElementLabels;

public class PatternStrings {

	public static String getSignature(IModelElement element) {
		if (element == null)
			return null;
		else
			switch (element.getElementType()) {
				case IModelElement.METHOD:
					return getMethodSignature((IMethod)element);
				case IModelElement.TYPE:
					return getTypeSignature((IType) element);
				case IModelElement.FIELD:
					return getFieldSignature((IField) element);
				default:
					return element.getElementName();
			}
	}
	
	public static String getMethodSignature(IMethod method) {
		StringBuffer buffer= new StringBuffer();
		IType type = method.getDeclaringType();
		if( type != null ) {
			buffer.append(ScriptElementLabels.getDefault().getElementLabel( type, ScriptElementLabels.T_FULLY_QUALIFIED | ScriptElementLabels.USE_RESOLVED));		
			boolean isConstructor= method.getElementName().equals(type.getElementName());
			if (!isConstructor) {
				buffer.append('.');
			}
			buffer.append(getUnqualifiedMethodSignature(method, !isConstructor));
		}				
		return buffer.toString();
	}
	
	private static String getUnqualifiedMethodSignature(IMethod method, boolean includeName) {
		StringBuffer buffer= new StringBuffer();
		if (includeName) {
			buffer.append(method.getElementName());
		}
		buffer.append('(');
		
		String[] types = new String[0];
		try {
			types = method.getParameters();
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i= 0; i < types.length; i++) {
			if (i > 0)
				buffer.append(", "); //$NON-NLS-1$
			String typeSig= Signature.toString(types[i]);
			buffer.append(typeSig);
		}
		buffer.append(')');
		
		return buffer.toString();
	}

	public static String getUnqualifiedMethodSignature(IMethod method) {
		return getUnqualifiedMethodSignature(method, true);
	}

	public static String getTypeSignature(IType field) {
		return ScriptElementLabels.getDefault().getElementLabel(field, 
			ScriptElementLabels.T_FULLY_QUALIFIED | ScriptElementLabels.T_TYPE_PARAMETERS | ScriptElementLabels.USE_RESOLVED);
	}	
	
	public static String getFieldSignature(IField field) {
		return ScriptElementLabels.getDefault().getElementLabel(field, ScriptElementLabels.F_FULLY_QUALIFIED);
	}	
}
