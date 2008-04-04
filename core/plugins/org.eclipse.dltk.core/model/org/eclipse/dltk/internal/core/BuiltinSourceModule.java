/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.dltk.internal.core;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.WorkingCopyOwner;

/**
 * Represents a builtin source module.
 */
public class BuiltinSourceModule extends AbstractExternalSourceModule implements
		org.eclipse.dltk.compiler.env.ISourceModule {

	public BuiltinSourceModule(BuiltinScriptFolder parent, String name,
			WorkingCopyOwner owner) {
		super(parent, name, owner, true);
	}

	/*
	 * @see org.eclipse.dltk.internal.core.AbstractSourceModule#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof BuiltinSourceModule)) {
			return false;
		}

		return super.equals(obj);
	}

	/*
	 * @see org.eclipse.core.resources.IStorage#getContents()
	 */
	public InputStream getContents() {
		String builtinModuleContent = getSourceModuleContent();
		if (builtinModuleContent == null) {
			return new ByteArrayInputStream(new byte[0]);
		}

		ByteArrayInputStream input = new ByteArrayInputStream(
				builtinModuleContent.getBytes());
		return input;
	}

	/*
	 * @see org.eclipse.dltk.compiler.env.IDependent#getFileName()
	 */
	public char[] getFileName() {
		return this.getPath().toOSString().toCharArray();
	}

	/*
	 * @see org.eclipse.core.resources.IStorage#getFullPath()
	 */
	public IPath getFullPath() {
		return new Path(getName());
	}

	/*
	 * @see org.eclipse.core.resources.IStorage#getName()
	 */
	public String getName() {
		return getElementName();
	}

	/*
	 * @see org.eclipse.dltk.core.ISourceModule#isBuiltin()
	 */
	public boolean isBuiltin() {
		return true;
	}

	/*
	 * @see org.eclipse.dltk.internal.core.AbstractSourceModule#getBufferContent()
	 */
	protected char[] getBufferContent() {
		String content = getSourceModuleContent();
		if (content != null) {
			return content.toCharArray();
		}

		return new char[0];
	}

	/*
	 * @see org.eclipse.dltk.internal.core.AbstractExternalSourceModule#getModuleType()
	 */
	protected String getModuleType() {
		return "DLTK Builtin Source Module: "; //$NON-NLS-1$
	}

	/*
	 * @see org.eclipse.dltk.internal.core.AbstractSourceModule#getNatureId()
	 */
	protected String getNatureId() throws CoreException {
		IScriptProject project = getScriptProject();
		return lookupLanguageToolkit(project).getNatureId();
	}

	/*
	 * @see org.eclipse.dltk.internal.core.AbstractSourceModule#getOriginalSourceModule()
	 */
	protected ISourceModule getOriginalSourceModule() {
		return new BuiltinSourceModule((BuiltinScriptFolder) getParent(),
				getElementName(), DefaultWorkingCopyOwner.PRIMARY);
	}

	private String getSourceModuleContent() {
		BuiltinProjectFragment fragment = (BuiltinProjectFragment) getProjectFragment();
		String builtinModuleContent = fragment.builtinProvider
				.getBuiltinModuleContent(getName());
		return builtinModuleContent;
	}
}
