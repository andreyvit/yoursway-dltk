/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.core.model;

import org.eclipse.dltk.ast.ASTCaching;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;

public interface IModel extends IElement {

//	IElement getElementByPosition(ISourceModule module, int offset);
	
	ModuleDeclaration getASTNode(ISourceModule sourceModule, ASTCaching caching);

}
