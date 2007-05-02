/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.text.completion;

import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.CompletionContext;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.javascript.core.JavaScriptNature;
import org.eclipse.dltk.javascript.internal.ui.JavaScriptUI;
import org.eclipse.dltk.ui.text.completion.CompletionProposalLabelProvider;
import org.eclipse.dltk.ui.text.completion.ContentAssistInvocationContext;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationPresenter;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.ui.IEditorPart;

public class JavaScriptCompletionProcessor extends ScriptCompletionProcessor {
	public JavaScriptCompletionProcessor(IEditorPart editor,
			ContentAssistant assistant, String partition) {
		super(editor, assistant, partition);
	}

	public JavaScriptCompletionProcessor(IEditorPart editor,
			ContentAssistant contentAssistant, String defaultContentType,
			IDLTKProject dltkProject, IResource resource) {
		super(editor, contentAssistant, defaultContentType);
		this.project=dltkProject;
		this.resource=resource;
	}

	private String fakeModuleContext;
	private IDLTKProject project;
	private IResource resource;

	protected IPreferenceStore getPreferenceStore() {
		return JavaScriptUI.getDefault().getPreferenceStore();
	}

	protected ContentAssistInvocationContext createContext(ITextViewer viewer,
			int offset) {
		if (this.resource==null)
		{
		return new ScriptContentAssistInvocationContext(viewer, offset,
				fEditor, JavaScriptNature.NATURE_ID) {

			public CompletionContext getCoreContext() {
				return new CompletionContext();
			}

			protected CompletionProposalLabelProvider createLabelProvider() {
				return new JavaScriptCompletionProposalLabelProvider();
			}
		};
		}
		else{
			return new ScriptContentAssistInvocationContext(viewer, offset,
					fEditor, JavaScriptNature.NATURE_ID) {

				public CompletionContext getCoreContext() {
					return new CompletionContext();
				}

				public IDLTKProject getProject() {
					return project;
				}

				public ISourceModule getSourceModule() {
					return new VirialModule(project,resource,fakeModuleContext);
				}

				protected CompletionProposalLabelProvider createLabelProvider() {
					return new JavaScriptCompletionProposalLabelProvider();
				}
			};			
		}
	}

	protected static class Validator implements IContextInformationValidator,
			IContextInformationPresenter {

		private int initialOffset;

		public boolean isContextInformationValid(int offset) {
			return Math.abs(offset - initialOffset) < 5;
		}

		public void install(IContextInformation info, ITextViewer viewer,
				int offset) {
			initialOffset = offset;
		}

		public boolean updatePresentation(int documentPosition,
				TextPresentation presentation) {
			return false;
		}
	}

	private IContextInformationValidator validator;

	public IContextInformationValidator getContextInformationValidator() {
		if (validator == null) {
			validator = new Validator();
		}
		return validator;
	}

	public void setFakeModuleContext(String string) {
		this.fakeModuleContext = string;
	}
}
