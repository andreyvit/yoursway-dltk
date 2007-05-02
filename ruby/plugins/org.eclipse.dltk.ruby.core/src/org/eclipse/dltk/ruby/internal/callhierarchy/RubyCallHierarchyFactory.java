/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.callhierarchy;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.ICallHierarchyFactory;
import org.eclipse.dltk.core.ICallProcessor;
import org.eclipse.dltk.core.ICalleeProcessor;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.search.IDLTKSearchScope;

public class RubyCallHierarchyFactory implements ICallHierarchyFactory {

	public ICalleeProcessor createCalleeProcessor(IMethod method,
			IProgressMonitor monitor, IDLTKSearchScope scope) {
		return new RubyCalleeProcessor( method, monitor, scope );
	}

	public ICallProcessor createCallProcessor() {
		return new RubyCallProcessor();
	}
}
