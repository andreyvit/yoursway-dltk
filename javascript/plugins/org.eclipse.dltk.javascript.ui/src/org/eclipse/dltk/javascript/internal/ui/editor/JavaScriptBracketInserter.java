/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.editor;

import org.eclipse.dltk.internal.ui.editor.BracketInserter;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor.BracketLevel;
import org.eclipse.dltk.javascript.internal.ui.text.Symbols;
import org.eclipse.dltk.javascript.scriptdoc.JavaHeuristicScanner;
import org.eclipse.dltk.javascript.ui.text.IJavaScriptPartitions;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.link.LinkedModeModel;
import org.eclipse.jface.text.link.LinkedModeUI;
import org.eclipse.jface.text.link.LinkedPosition;
import org.eclipse.jface.text.link.LinkedPositionGroup;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.texteditor.link.EditorLinkedModeUI;

public class JavaScriptBracketInserter extends BracketInserter {

	JavaScriptBracketInserter(ScriptEditor scriptEditor) {
		super(scriptEditor);
	}

	public void verifyKey(VerifyEvent event) {

		// early pruning to slow down normal typing as little as possible
		if (!event.doit
				|| this.editor.getInsertMode() != ScriptEditor.SMART_INSERT)
			return;
		switch (event.character) {
		case '(':
		case '<':
		case '[':
		case '\'':
		case '\"':
			break;
		default:
			return;
		}

		final ISourceViewer sourceViewer = this.editor
				.getScriptSourceViewer();
		IDocument document = sourceViewer.getDocument();

		final Point selection = sourceViewer.getSelectedRange();
		final int offset = selection.x;
		final int length = selection.y;

		try {
			IRegion startLine = document.getLineInformationOfOffset(offset);
			IRegion endLine = document.getLineInformationOfOffset(offset
					+ length);

			JavaHeuristicScanner scanner = new JavaHeuristicScanner(document);
			int nextToken = scanner.nextToken(offset + length, endLine
					.getOffset()
					+ endLine.getLength());
			String next = nextToken == Symbols.TokenEOF ? null : document.get(
					offset, scanner.getPosition() - offset).trim();
			int prevToken = scanner.previousToken(offset - 1, startLine
					.getOffset());
			int prevTokenOffset = scanner.getPosition() + 1;
			String previous = prevToken == Symbols.TokenEOF ? null : document
					.get(prevTokenOffset, offset - prevTokenOffset).trim();

			switch (event.character) {
			case '(':
				if (!fCloseBrackets || nextToken == Symbols.TokenLPAREN
						|| nextToken == Symbols.TokenIDENT || next != null
						&& next.length() > 1)
					return;
				break;

			case '<':
				if (!(fCloseAngularBrackets && fCloseBrackets)
						|| nextToken == Symbols.TokenLESSTHAN
						|| prevToken != Symbols.TokenLBRACE
						&& prevToken != Symbols.TokenRBRACE
						&& prevToken != Symbols.TokenSEMICOLON
						&& prevToken != Symbols.TokenSYNCHRONIZED
						&& prevToken != Symbols.TokenSTATIC
						&& (prevToken != Symbols.TokenIDENT || !isAngularIntroducer(previous))
						&& prevToken != Symbols.TokenEOF)
					return;
				break;

			case '[':
				if (!fCloseBrackets || nextToken == Symbols.TokenIDENT
						|| next != null && next.length() > 1)
					return;
				break;

			case '\'':
			case '"':
				if (!fCloseStrings || nextToken == Symbols.TokenIDENT
						|| prevToken == Symbols.TokenIDENT || next != null
						&& next.length() > 1 || previous != null
						&& previous.length() > 1)
					return;
				break;

			default:
				return;
			}

			ITypedRegion partition = TextUtilities.getPartition(document,
					IJavaScriptPartitions.JS_PARTITIONING, offset, true);
			if (!IDocument.DEFAULT_CONTENT_TYPE.equals(partition.getType()))
				return;

			if (!this.editor.validateEditorInputState())
				return;

			final char character = event.character;
			final char closingCharacter = getPeerCharacter(character);
			final StringBuffer buffer = new StringBuffer();
			buffer.append(character);
			buffer.append(closingCharacter);

			document.replace(offset, length, buffer.toString());

			BracketLevel level = new ScriptEditor.BracketLevel();
			fBracketLevelStack.push(level);

			LinkedPositionGroup group = new LinkedPositionGroup();
			group.addPosition(new LinkedPosition(document, offset + 1, 0,
					LinkedPositionGroup.NO_STOP));

			LinkedModeModel model = new LinkedModeModel();
			model.addLinkingListener(this);
			model.addGroup(group);
			model.forceInstall();

			level.fOffset = offset;
			level.fLength = 2;

			// set up position tracking for our magic peers
			if (fBracketLevelStack.size() == 1) {
				document.addPositionCategory(CATEGORY);
				document.addPositionUpdater(fUpdater);
			}
			level.fFirstPosition = new Position(offset, 1);
			level.fSecondPosition = new Position(offset + 1, 1);
			document.addPosition(CATEGORY, level.fFirstPosition);
			document.addPosition(CATEGORY, level.fSecondPosition);

			level.fUI = new EditorLinkedModeUI(model, sourceViewer);
			level.fUI.setSimpleMode(true);
			level.fUI.setExitPolicy(this.editor.new ExitPolicy(
					closingCharacter, getEscapeCharacter(closingCharacter),
					fBracketLevelStack));
			level.fUI.setExitPosition(sourceViewer, offset + 2, 0,
					Integer.MAX_VALUE);
			level.fUI.setCyclingMode(LinkedModeUI.CYCLE_NEVER);
			level.fUI.enter();

			IRegion newSelection = level.fUI.getSelectedRegion();
			sourceViewer.setSelectedRange(newSelection.getOffset(),
					newSelection.getLength());

			event.doit = false;

		} catch (BadLocationException e) {
			DLTKUIPlugin.log(e);
		} catch (BadPositionCategoryException e) {
			DLTKUIPlugin.log(e);
		}
	}

}
