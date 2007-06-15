/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
 package org.eclipse.dltk.internal.corext.refactoring;

import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.core.resources.mapping.ResourceMappingContext;
import org.eclipse.core.runtime.IProgressMonitor;

public interface IInternalRefactoringProcessorIds {
	
	/**
	 * Processor ID of the copy processor (value <code>"org.eclipse.dltk.ui.CopyProcessor"</code>).
	 * 
	 * The copy processor is used when copying elements via drag and drop or when pasting
	 * elements from the clipboard. The copy processor loads the following participants,
	 * depending on the type of the element that gets copied:
	 * <ul>
	 *   <li><code>IDLTKProject</code>: no participants are loaded.</li>
	 *   <li><code>IProjectFragment</code>: participants registered for copying 
	 *       <code>IProjectFragment</code> and <code>ResourceMapping</code>.</li>
	 *   <li><code>IScriptFolder</code>: participants registered for copying 
	 *       <code>IScriptFolder</code> and <code>ResourceMapping</code>.</li>
	 *   <li><code>ISourceModule</code>: participants registered for copying 
	 *       <code>ISourceModule</code> and <code>ResourceMapping</code>.</li>
	 *   <li><code>IType</code>: like ISourceModule if the primary top level type is copied.
	 *       Otherwise no participants are loaded.</li>
	 *   <li><code>IMember</code>: no participants are loaded.</li>
	 *   <li><code>IFolder</code>: participants registered for copying folders.</li>
	 *   <li><code>IFile</code>: participants registered for copying files.</li>
	 * </ul>
	 * <p>
	 * Use the method {@link ResourceMapping#accept(ResourceMappingContext context, IResourceVisitor visitor, IProgressMonitor monitor)} 
	 * to enumerate the resources which form the Script element. <code>ResourceMappingContext.LOCAL_CONTEXT</code> 
	 * should be use as the <code>ResourceMappingContext</code> passed to the accept method.
	 * </p>
	 * @see org.eclipse.core.resources.mapping.ResourceMapping
	 *
	 */
	public static String COPY_PROCESSOR= "org.eclipse.dltk.ui.CopyProcessor";  //$NON-NLS-1$
	
}
