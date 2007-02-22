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


import org.eclipse.jface.text.Assert;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;


public abstract class ScriptCompletionProposal extends AbstractDLTKCompletionProposal {

	/**
	 * Creates a new completion proposal. All fields are initialized based on the provided
	 * information.
	 * 
	 * @param replacementString the actual string to be inserted into the document
	 * @param replacementOffset the offset of the text to be replaced
	 * @param replacementLength the length of the text to be replaced
	 * @param image the image to display for this proposal
	 * @param displayString the string to be displayed for the proposal If set to <code>null</code>,
	 *        the replacement string will be taken as display string.
	 */
	public ScriptCompletionProposal(String replacementString, int replacementOffset, int replacementLength, Image image, String displayString, int relevance) {
		this(replacementString, replacementOffset, replacementLength, image, displayString, relevance, false);
	}
	
	/**
	 * Creates a new completion proposal. All fields are initialized based on the provided
	 * information.
	 * 
	 * @param replacementString the actual string to be inserted into the document
	 * @param replacementOffset the offset of the text to be replaced
	 * @param replacementLength the length of the text to be replaced
	 * @param image the image to display for this proposal
	 * @param displayString the string to be displayed for the proposal If set to <code>null</code>,
	 *        the replacement string will be taken as display string.
	 * @param relevance the relevance
	 * @param indoc <code>true</code> for a javadoc proposal
	 *
	 */
	public ScriptCompletionProposal(String replacementString, int replacementOffset, int replacementLength, Image image, String displayString, int relevance, boolean indoc) {
		Assert.isNotNull(replacementString);
		Assert.isTrue(replacementOffset >= 0);
		Assert.isTrue(replacementLength >= 0);

		setReplacementString(replacementString);
		setReplacementOffset(replacementOffset);
		setReplacementLength(replacementLength);
		setImage(image);
		setDisplayString(displayString == null ? replacementString : displayString);
		setRelevance(relevance);
		setCursorPosition(replacementString.length());
		setInDoc(indoc);
		setSortString(displayString == null ? replacementString : displayString);
	}

	protected boolean isValidPrefix(String prefix) {
		String word= getDisplayString();
		if (isInScriptdoc()) {
			int idx = word.indexOf("{@link "); //$NON-NLS-1$
			if (idx==0) {
				word = word.substring(7);
			} else {
				idx = word.indexOf("{@value "); //$NON-NLS-1$
				if (idx==0) {
					word = word.substring(8);
				}
			}
		}
		return isPrefix(prefix, word);
	}
	
	/*
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposalExtension3#getReplacementText()
	 */
	public CharSequence getPrefixCompletionText(IDocument document, int completionOffset) {
		String string= getReplacementString();
		int pos= string.indexOf('(');
		if (pos > 0)
			return string.subSequence(0, pos);
		else
			return string;
	}

	protected void handleSmartTrigger(IDocument document, char trigger, int referenceOffset) throws BadLocationException {
		// TODO Auto-generated method stub
		
	}	
}
