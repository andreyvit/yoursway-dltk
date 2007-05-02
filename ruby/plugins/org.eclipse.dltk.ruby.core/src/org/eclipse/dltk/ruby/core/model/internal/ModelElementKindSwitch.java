/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.core.model.internal;

import org.eclipse.dltk.ast.references.VariableKind;
import org.eclipse.dltk.ruby.core.model.IElement;
import org.eclipse.dltk.ruby.core.model.IElementKind;
import org.eclipse.dltk.ruby.core.model.IMethod;
import org.eclipse.dltk.ruby.core.model.IModel;
import org.eclipse.dltk.ruby.core.model.IType;
import org.eclipse.dltk.ruby.core.model.ITypeFragment;
import org.eclipse.dltk.ruby.core.model.IVariable;

public abstract class ModelElementKindSwitch {

	public void visit(IElement element) {
		int id = element.getElementKind().getId();
		switch(id) {
		case IElementKind.Model.ID:
			visitModel((IModel) element);
			break;
		case IElementKind.Class.ID:
			visitClass((IType) element);
			break;
		case IElementKind.Mixin.ID:
			visitMixin((IType) element);
			break;
		case IElementKind.ClassFragment.ID:
			visitClassFragment((ITypeFragment) element);
			break;
		case IElementKind.MixinFragment.ID:
			visitMixinFragment((ITypeFragment) element);
			break;
		case IElementKind.Method.ID:
			visitMethod((IMethod) element);
			break;
		default:
			if (id >= VariableKind.FIRST_VARIABLE_ID && id <= VariableKind.LAST_VARIABLE_ID)
				visitVariable((IVariable) element);
			else
				visitUnknown(element);
		}
	}
	
	protected void visitGeneral(IElement element) {
	}

	protected void visitUnknown(IElement element) {
		visitGeneral(element);
	}

	protected void visitVariable(IVariable element) {
		visitGeneral(element);
	}

	protected void visitMethod(IMethod element) {
		visitGeneral(element);
	}

	protected void visitMixinFragment(ITypeFragment element) {
		visitGeneral(element);
	}

	protected void visitClassFragment(ITypeFragment element) {
		visitGeneral(element);
	}

	protected void visitMixin(IType element) {
		visitGeneral(element);
	}

	protected void visitClass(IType element) {
		visitGeneral(element);
	}

	protected void visitModel(IModel element) {
		visitGeneral(element);
	}
	
}
