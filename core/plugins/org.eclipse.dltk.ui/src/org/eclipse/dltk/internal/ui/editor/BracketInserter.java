/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.dltk.internal.ui.editor;

import java.util.Stack;

import org.eclipse.dltk.internal.ui.editor.ScriptEditor.BracketLevel;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.IPositionUpdater;
import org.eclipse.jface.text.link.ILinkedModeListener;
import org.eclipse.jface.text.link.LinkedModeModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;

public abstract class BracketInserter implements VerifyKeyListener, ILinkedModeListener {

	protected final ScriptEditor editor;
	protected boolean fCloseBrackets = true;
	protected boolean fCloseStrings = true;
	protected boolean fCloseAngularBrackets = true;
	protected final String CATEGORY ;
	protected IPositionUpdater fUpdater;
	protected Stack fBracketLevelStack = new Stack();

	protected BracketInserter(ScriptEditor editor) {
		this.editor = editor;
		CATEGORY= this.editor.toString();
		fUpdater = new ScriptEditor.ExclusivePositionUpdater(CATEGORY);
	}

	public void setCloseBracketsEnabled(boolean enabled) {
		fCloseBrackets = enabled;
	}

	public void setCloseStringsEnabled(boolean enabled) {
		fCloseStrings = enabled;
	}

	public void setCloseAngularBracketsEnabled(boolean enabled) {
		fCloseAngularBrackets = enabled;
	}

	protected boolean isAngularIntroducer(String identifier) {
		return false;
	}
	
	protected static char getEscapeCharacter(char character) {
		switch (character) {
			case '"':
			case '\'':
				return '\\';
			default:
				return 0;
		}
	}
	protected static char getPeerCharacter(char character) {
		switch (character) {
			case '(':
				return ')';

			case ')':
				return '(';

			case '<':
				return '>';

			case '>':
				return '<';

			case '[':
				return ']';

			case ']':
				return '[';

			case '"':
				return character;

			case '\'':
				return character;

			default:
				throw new IllegalArgumentException();
		}
	}

	public abstract void verifyKey(VerifyEvent event) ;

	public void left(LinkedModeModel environment, int flags) {

		final BracketLevel level = (BracketLevel) fBracketLevelStack.pop();

		if (flags != ILinkedModeListener.EXTERNAL_MODIFICATION) {
			return;
		}

		// remove brackets
		final ISourceViewer sourceViewer = this.editor
				.getScriptSourceViewer();
		final IDocument document = sourceViewer.getDocument();
		if (document instanceof IDocumentExtension) {
			IDocumentExtension extension = (IDocumentExtension) document;
			extension.registerPostNotificationReplace(null,
					new IDocumentExtension.IReplace() {

						public void perform(IDocument d, IDocumentListener owner) {
							if ((level.fFirstPosition.isDeleted || level.fFirstPosition.length == 0)
									&& !level.fSecondPosition.isDeleted
									&& level.fSecondPosition.offset == level.fFirstPosition.offset) {
								try {
									document.replace(
											level.fSecondPosition.offset,
											level.fSecondPosition.length, ""); //$NON-NLS-1$
								} catch (BadLocationException e) {
									DLTKUIPlugin.log(e);
								}
							}

							if (fBracketLevelStack.size() == 0) {
								document.removePositionUpdater(fUpdater);
								try {
									document.removePositionCategory(CATEGORY);
								} catch (BadPositionCategoryException e) {
									DLTKUIPlugin.log(e);
								}
							}
						}
					});
		}
	}

	public void suspend(LinkedModeModel environment) {
	}

	public void resume(LinkedModeModel environment, int flags) {
	}
}
