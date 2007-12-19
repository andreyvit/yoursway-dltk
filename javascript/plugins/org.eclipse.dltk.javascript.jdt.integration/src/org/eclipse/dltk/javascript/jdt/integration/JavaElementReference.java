/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.jdt.integration;

import java.util.Collection;
import java.util.Set;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.internal.javascript.typeinference.FakeField;
import org.eclipse.dltk.internal.javascript.typeinference.IReference;
import org.eclipse.dltk.internal.javascript.typeinference.UncknownReference;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.JavaModelException;

public class JavaElementReference extends UncknownReference{

	public Set getChilds(boolean resolveLocals) {
		if (element instanceof IMethod){
			IMethod m=(IMethod) element;
			try {
				String rType=m.getReturnType();
//				System.out.println(rType);
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		return super.getChilds(resolveLocals);
	}

	public IReference getChild(String key, boolean resolveLocals) {
		// TODO Auto-generated method stub
		return super.getChild(key, resolveLocals);
	}

	public void addModelElements(Collection toAdd) {
		if (element instanceof IMember){
			IMember m=(IMember) element;
			ISourceRange sourceRange;
			try {
				sourceRange = m.getSourceRange();
				
				FakeField fakeField = new JavaReferenceFakeField((ModelElement) owner, element.getElementName(), sourceRange.getOffset(),
						sourceRange.getLength(),this.element);
				
				toAdd.add(fakeField);
			} catch (JavaModelException e) {
			}			
		}
	}

	protected IJavaElement element;
	protected IModelElement owner;
	
	public JavaElementReference(IJavaElement element,IModelElement owner) {
		super(element.getElementName(),true);
		this.element=element;
		this.owner=owner;
	}	
}
