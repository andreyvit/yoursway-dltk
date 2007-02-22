/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.compiler.lookup;

// TODO should rename into TypeNames (once extracted last non name constants)
public interface TypeConstants {
	
	// Constants used to perform bound checks
	int OK = 0;
	int UNCHECKED = 1;
	int MISMATCH = 2;
	
	char[] INIT = "<init>".toCharArray(); //$NON-NLS-1$
	
}
