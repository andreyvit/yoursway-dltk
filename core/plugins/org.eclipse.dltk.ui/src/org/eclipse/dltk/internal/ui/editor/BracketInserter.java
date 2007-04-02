/**
 * 
 */
package org.eclipse.dltk.internal.ui.editor;

import java.util.Stack;

import org.eclipse.dltk.internal.ui.editor.ScriptEditor.BracketLevel;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor.ExclusivePositionUpdater;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor.ExitPolicy;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.IPositionUpdater;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.link.ILinkedModeListener;
import org.eclipse.jface.text.link.LinkedModeModel;
import org.eclipse.jface.text.link.LinkedModeUI;
import org.eclipse.jface.text.link.LinkedPosition;
import org.eclipse.jface.text.link.LinkedPositionGroup;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.texteditor.link.EditorLinkedModeUI;

public abstract class BracketInserter implements VerifyKeyListener, ILinkedModeListener {

	/**
	 * 
	 */
	protected final ScriptEditor scriptEditor;

	/**
	 * @param scriptEditor
	 */
	protected BracketInserter(ScriptEditor scriptEditor) {
		this.scriptEditor = scriptEditor;
		CATEGORY= this.scriptEditor.toString();
		fUpdater = new ScriptEditor.ExclusivePositionUpdater(CATEGORY);
	}

	protected boolean fCloseBrackets = true;
	protected boolean fCloseStrings = true;
	protected boolean fCloseAngularBrackets = true;
	protected final String CATEGORY ;
	protected IPositionUpdater fUpdater;
	protected Stack fBracketLevelStack = new Stack();

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

	/*
	 * @see org.eclipse.swt.custom.VerifyKeyListener#verifyKey(org.eclipse.swt.events.VerifyEvent)
	 */
	public abstract void verifyKey(VerifyEvent event) ;

	/*
	 * @see org.eclipse.jface.text.link.ILinkedModeListener#left(org.eclipse.jface.text.link.LinkedModeModel,
	 *      int)
	 */
	public void left(LinkedModeModel environment, int flags) {

		final BracketLevel level = (BracketLevel) fBracketLevelStack.pop();

		if (flags != ILinkedModeListener.EXTERNAL_MODIFICATION)
			return;

		// remove brackets
		final ISourceViewer sourceViewer = this.scriptEditor
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

	/*
	 * @see org.eclipse.jface.text.link.ILinkedModeListener#suspend(org.eclipse.jface.text.link.LinkedModeModel)
	 */
	public void suspend(LinkedModeModel environment) {
	}

	/*
	 * @see org.eclipse.jface.text.link.ILinkedModeListener#resume(org.eclipse.jface.text.link.LinkedModeModel,
	 *      int)
	 */
	public void resume(LinkedModeModel environment, int flags) {
	}
}
