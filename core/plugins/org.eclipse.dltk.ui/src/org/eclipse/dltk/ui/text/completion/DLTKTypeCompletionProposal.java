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
package org.eclipse.dltk.ui.text.completion;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.Signature;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;


/**
 * If passed compilation unit is not null, the replacement string will be seen as a qualified type name.
  */
public class DLTKTypeCompletionProposal extends ScriptCompletionProposal {

	protected final ISourceModule fSourceModule;

	/** The unqualified type name. */
	private final String fUnqualifiedTypeName;
	/** The fully qualified type name. */
	private final String fFullyQualifiedTypeName;

	public DLTKTypeCompletionProposal(String replacementString, ISourceModule cu, int replacementOffset, int replacementLength, Image image, String displayString, int relevance) {
		this(replacementString, cu, replacementOffset, replacementLength, image, displayString, relevance, null);
	}

	public DLTKTypeCompletionProposal(String replacementString, ISourceModule cu, int replacementOffset, int replacementLength, Image image, String displayString, int relevance,
		String fullyQualifiedTypeName)
	{
		super(replacementString, replacementOffset, replacementLength, image, displayString, relevance);
		fSourceModule= cu;
		fFullyQualifiedTypeName= fullyQualifiedTypeName;
		fUnqualifiedTypeName= fullyQualifiedTypeName != null ? Signature.getSimpleName(fullyQualifiedTypeName) : null;
	}

	protected boolean updateReplacementString(IDocument document, char trigger, int offset) throws CoreException, BadLocationException {
//		// avoid adding imports when inside imports container
//		if (impRewrite != null && fFullyQualifiedTypeName != null) {
//			String replacementString= getReplacementString();
//			String qualifiedType= fFullyQualifiedTypeName;
//			if (qualifiedType.indexOf('.') != -1 && replacementString.startsWith(qualifiedType) && !replacementString.endsWith(String.valueOf(';'))) {
//				IType[] types= impRewrite.getSourceModule().getTypes();
//				if (types.length > 0 && types[0].getSourceRange().getOffset() <= offset) {
//					// ignore positions above type.
//					setReplacementString(impRewrite.addImport(getReplacementString()));
//					return true;
//				}
//			}
//		}
		return false;
	}


	/* (non-Javadoc)
	 * @see ICompletionProposalExtension#apply(IDocument, char, int)
	 */
	public void apply(IDocument document, char trigger, int offset) {
		try {
//			ImportRewrite impRewrite= null;

//			if (fSourceModule != null && allowAddingImports()) {
//				impRewrite= StubUtility.createImportRewrite(fSourceModule, true);
//			}

			boolean importAdded= updateReplacementString(document, trigger, offset);

			if (importAdded)
				setCursorPosition(getReplacementString().length());

			super.apply(document, trigger, offset);

//			if (importAdded && impRewrite != null) {
//				int oldLen= document.getLength();
//				impRewrite.rewriteImports(new NullProgressMonitor()).apply(document, TextEdit.UPDATE_REGIONS);
//				setReplacementOffset(getReplacementOffset() + document.getLength() - oldLen);
//			}
		} catch (CoreException e) {
			DLTKUIPlugin.log(e);
		} catch (BadLocationException e) {
			DLTKUIPlugin.log(e);
		}
	}

	private boolean allowAddingImports() {
		IPreferenceStore preferenceStore= DLTKUIPlugin.getDefault().getPreferenceStore();
		return preferenceStore.getBoolean(PreferenceConstants.CODEASSIST_ADDIMPORT);
	}
	
	protected boolean isValidPrefix(String prefix) {
		return super.isValidPrefix(prefix) || isPrefix(prefix, fUnqualifiedTypeName) || isPrefix(prefix, fFullyQualifiedTypeName);
	}

	public CharSequence getPrefixCompletionText(IDocument document, int completionOffset) {
		return fUnqualifiedTypeName;
	}

	protected boolean isSmartTrigger(char trigger) {		
		return false;
	}
}
