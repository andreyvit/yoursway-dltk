/*******************************************************************************
 * Copyright (c) 2004, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.codeassist;

import org.eclipse.dltk.codeassist.ICompletionEngine;
import org.eclipse.dltk.core.IAccessRule;
import org.eclipse.dltk.internal.core.NameLookup;

public class InternalCompletionProposal {	
	public ICompletionEngine completionEngine;
	
	public NameLookup nameLookup;
	
	protected char[] declarationPackageName;
	protected char[] declarationTypeName;
	protected char[] packageName;
	protected char[] typeName;
	protected char[][] parameterPackageNames;
	protected char[][] parameterTypeNames;
		
	protected int accessibility = IAccessRule.K_ACCESSIBLE;
	
	protected boolean isConstructor = false;
}
