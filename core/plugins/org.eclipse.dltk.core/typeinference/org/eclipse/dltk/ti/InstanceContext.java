/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
package org.eclipse.dltk.ti;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class InstanceContext extends BasicContext implements IInstanceContext {

	private final IEvaluatedType instanceType;

	public InstanceContext(ISourceModule sourceModule,
			ModuleDeclaration rootNode, IEvaluatedType instanceType) {
		super(sourceModule, rootNode);
		this.instanceType = instanceType;
	}

	public InstanceContext(ISourceModuleContext parent,
			IEvaluatedType instanceType) {
		super(parent);
		this.instanceType = instanceType;
	}

	public IEvaluatedType getInstanceType() {
		return instanceType;
	}

}
