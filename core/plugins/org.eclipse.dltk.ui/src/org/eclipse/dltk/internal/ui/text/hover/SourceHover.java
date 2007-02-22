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

package org.eclipse.dltk.internal.ui.text.hover;

import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.ITextHoverExtension;
import org.eclipse.jface.text.information.IInformationProviderExtension2;

/**
 * Provides source as hover info for Script elements.
 */
public class SourceHover extends AbstractScriptEditorTextHover implements ITextHoverExtension, IInformationProviderExtension2 {

	public IInformationControlCreator getInformationPresenterControlCreator() {
		// TODO Auto-generated method stub
		return null;
	}
/*
	
	 * @see ModelElementHover
	 
	protected String getHoverInfo(IModelElement[] result) {
		int nResults= result.length;

		if (nResults > 1)
			return null;

		IModelElement curr= result[0];
		if ((curr instanceof IMember ||
				curr instanceof ILocalVariable ||
				curr instanceof ITypeParameter) && curr instanceof ISourceReference) {
			try {
				String source= ((ISourceReference) curr).getSource();
				if (source == null)
					return null;

				source= removeLeadingComments(source);
				String delim= StubUtility.getLineDelimiterUsed(result[0]);

				String[] sourceLines= Strings.convertIntoLines(source);
				String firstLine= sourceLines[0];
				if (!Character.isWhitespace(firstLine.charAt(0)))
					sourceLines[0]= ""; //$NON-NLS-1$
				Strings.trimIndentation(sourceLines, curr.getScriptProject());

				if (!Character.isWhitespace(firstLine.charAt(0)))
					sourceLines[0]= firstLine;

				source= Strings.concatenate(sourceLines, delim);

				return source;

			} catch (ModelException ex) {
			}
		}

		return null;
	}

	private String removeLeadingComments(String source) {
		JavaCodeReader reader= new JavaCodeReader();
		IDocument document= new Document(source);
		int i;
		try {
			reader.configureForwardReader(document, 0, document.getLength(), true, false);
			int c= reader.read();
			while (c != -1 && (c == '\r' || c == '\n')) {
				c= reader.read();
			}
			i= reader.getOffset();
			reader.close();
		} catch (IOException ex) {
			i= 0;
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException ex) {
				JavaPlugin.log(ex);
			}
		}

		if (i < 0)
			return source;
		return source.substring(i);
	}

	
	 * @see org.eclipse.jface.text.ITextHoverExtension#getHoverControlCreator()
	 *
	 
	public IInformationControlCreator getHoverControlCreator() {
		return new IInformationControlCreator() {
			public IInformationControl createInformationControl(Shell parent) {
				IEditorPart editor= getEditor(); 
				int shellStyle= SWT.TOOL | SWT.NO_TRIM;
				if (editor instanceof IWorkbenchPartOrientation)
					shellStyle |= ((IWorkbenchPartOrientation)editor).getOrientation();
				return new SourceViewerInformationControl(parent, shellStyle, SWT.NONE, getTooltipAffordanceString());
			}
		};
	}

	
	 * @see IInformationProviderExtension2#getInformationPresenterControlCreator()
	 *
	 
	public IInformationControlCreator getInformationPresenterControlCreator() {
		return new IInformationControlCreator() {
			public IInformationControl createInformationControl(Shell parent) {
				int style= SWT.V_SCROLL | SWT.H_SCROLL;
				int shellStyle= SWT.RESIZE | SWT.TOOL;
				IEditorPart editor= getEditor(); 
				if (editor instanceof IWorkbenchPartOrientation)
					shellStyle |= ((IWorkbenchPartOrientation)editor).getOrientation();
				return new SourceViewerInformationControl(parent, shellStyle, style);
			}
		};
	}*/
}
