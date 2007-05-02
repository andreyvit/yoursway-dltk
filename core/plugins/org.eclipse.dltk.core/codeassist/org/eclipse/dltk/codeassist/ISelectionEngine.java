/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.codeassist;

import java.util.Map;

import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.core.SearchableEnvironment;

public interface ISelectionEngine {
	IModelElement[] select(ISourceModule module, int offset, int i);

	void setEnvironment(SearchableEnvironment environment);

	void setOptions(Map options);
}
