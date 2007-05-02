/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.templates;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateVariableResolver;

public final class ScriptTemplateVariables {
	private ScriptTemplateVariables() {
	}

	public static class File extends TemplateVariableResolver {
		public static final String NAME = "file";
		
		public File() {
			super(NAME, TemplateMessages.Variable_File_Description);
		}

		protected String resolve(TemplateContext context) {
			ISourceModule module = ((ScriptTemplateContext) context)
					.getSourceModule();

			return (module == null) ? null : module.getElementName();
		}

		protected boolean isUnambiguous(TemplateContext context) {
			return resolve(context) != null;
		}
	}

	public static class Language extends TemplateVariableResolver {
		public static final String NAME = "language";
		
		public Language() {
			super(NAME, TemplateMessages.Variable_Language_Description);
		}

		protected String resolve(TemplateContext context) {
			try {
				ISourceModule module = ((ScriptTemplateContext) context)
						.getSourceModule();
				IDLTKLanguageToolkit toolkit = DLTKLanguageManager
						.getLanguageToolkit(module);

				return toolkit.getLanguageName();
			} catch (CoreException e) {
				return null;
			}
		}

		protected boolean isUnambiguous(TemplateContext context) {
			return resolve(context) != null;
		}
	}
}
