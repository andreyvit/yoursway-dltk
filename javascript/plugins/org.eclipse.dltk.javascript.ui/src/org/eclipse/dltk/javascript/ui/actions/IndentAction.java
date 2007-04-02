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
package org.eclipse.dltk.javascript.ui.actions;

import java.util.ResourceBundle;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.javascript.core.JavaScriptPlugin;
import org.eclipse.dltk.javascript.internal.ui.formatting.DefaultCodeFormatterConstants;
import org.eclipse.dltk.javascript.scriptdoc.JavaHeuristicScanner;
import org.eclipse.dltk.javascript.scriptdoc.JavaIndenter;
import org.eclipse.dltk.javascript.ui.text.IJavaScriptPartitions;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.IRewriteTarget;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorExtension3;
import org.eclipse.ui.texteditor.TextEditorAction;


/**
 * Indents a line or range of lines in a Java document to its correct position. No complete
 * AST must be present, the indentation is computed using heuristics. The algorithm used is fast for
 * single lines, but does not store any information and therefore not so efficient for large line
 * ranges.
 * 
 * @see org.eclipse.jdt.internal.ui.text.JavaHeuristicScanner
 * @see org.eclipse.jdt.internal.ui.text.JavaIndenter
 * @since 3.0
 */
public class IndentAction extends TextEditorAction {
	
	/** The caret offset after an indent operation. */
	private int fCaretOffset;
	
	/** 
	 * Whether this is the action invoked by TAB. When <code>true</code>, indentation behaves 
	 * differently to accommodate normal TAB operation.
	 */
	private final boolean fIsTabAction;
	
	/**
	 * Creates a new instance.
	 * 
	 * @param bundle the resource bundle
	 * @param prefix the prefix to use for keys in <code>bundle</code>
	 * @param editor the text editor
	 * @param isTabAction whether the action should insert tabs if over the indentation
	 */
	public IndentAction(ResourceBundle bundle, String prefix, ITextEditor editor, boolean isTabAction) {
		super(bundle, prefix, editor);
		fIsTabAction= isTabAction;
	}
	
	/*
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		// update has been called by the framework
		if (!isEnabled() || !validateEditorInputState())
			return;
		
		ITextSelection selection= getSelection();
		final IDocument document= getDocument();
		
		if (document != null) {
			
			final int offset= selection.getOffset();
			final int length= selection.getLength();
			final Position end= new Position(offset + length);
			final int firstLine, nLines;
			fCaretOffset= -1;
			
			try {
				document.addPosition(end);
				firstLine= document.getLineOfOffset(offset);
				// check for marginal (zero-length) lines
				int minusOne= length == 0 ? 0 : 1;
				nLines= document.getLineOfOffset(offset + length - minusOne) - firstLine + 1;
			} catch (BadLocationException e) {
				// will only happen on concurrent modification
				//DLTKCore.log(new Status(IStatus.ERROR, JavaPlugin.getPluginId(), IStatus.OK, "", e)); //$NON-NLS-1$
				return;
			}
			
			Runnable runnable= new Runnable() {
				public void run() {
					IRewriteTarget target= (IRewriteTarget)getTextEditor().getAdapter(IRewriteTarget.class);
					if (target != null)
						target.beginCompoundChange();
					
					try {
						JavaHeuristicScanner scanner= new JavaHeuristicScanner(document);
						JavaIndenter indenter= new JavaIndenter(document, scanner, getJavaProject());
						final boolean multiLine= nLines > 1;
						boolean hasChanged= false;
						for (int i= 0; i < nLines; i++) {
							hasChanged |= indentLine(document, firstLine + i, offset, indenter, scanner, multiLine);
						}
						
						// update caret position: move to new position when indenting just one line
						// keep selection when indenting multiple
						int newOffset, newLength;
						if (!fIsTabAction && multiLine) {
							newOffset= offset;
							newLength= end.getOffset() - offset;
						} else {
							newOffset= fCaretOffset;
							newLength= 0;
						}
						
						// always reset the selection if anything was replaced
						// but not when we had a single line non-tab invocation
						if (newOffset != -1 && (hasChanged || newOffset != offset || newLength != length))
							selectAndReveal(newOffset, newLength);
						
						document.removePosition(end);
					} catch (BadLocationException e) {
						// will only happen on concurrent modification
						//JavaPlugin.log(new Status(IStatus.ERROR, JavaPlugin.getPluginId(), IStatus.OK, "ConcurrentModification in IndentAction", e)); //$NON-NLS-1$
						
					} finally {
						if (target != null)
							target.endCompoundChange();
					}
				}
			};
			
			if (nLines > 50) {
				Display display= getTextEditor().getEditorSite().getWorkbenchWindow().getShell().getDisplay();
				BusyIndicator.showWhile(display, runnable);
			} else
				runnable.run();
			
		}
	}
	
	/**
	 * Selects the given range on the editor.
	 * 
	 * @param newOffset the selection offset
	 * @param newLength the selection range
	 */
	private void selectAndReveal(int newOffset, int newLength) {
		Assert.isTrue(newOffset >= 0); 
		Assert.isTrue(newLength >= 0); 
		ITextEditor editor= getTextEditor();
		if (editor instanceof ScriptEditor) {
			ISourceViewer viewer= ((ScriptEditor)editor).getViewer();
			if (viewer != null)
				viewer.setSelectedRange(newOffset, newLength);
		} else
			// this is too intrusive, but will never get called anyway
			getTextEditor().selectAndReveal(newOffset, newLength);
			
	}

	/**
	 * Indents a single line using the java heuristic scanner. Javadoc and multiline comments are 
	 * indented as specified by the <code>JavaDocAutoIndentStrategy</code>.
	 * 
	 * @param document the document
	 * @param line the line to be indented
	 * @param caret the caret position
	 * @param indenter the java indenter
	 * @param scanner the heuristic scanner
	 * @param multiLine <code>true</code> if more than one line is being indented 
	 * @return <code>true</code> if <code>document</code> was modified, <code>false</code> otherwise
	 * @throws BadLocationException if the document got changed concurrently 
	 */
	private boolean indentLine(IDocument document, int line, int caret, JavaIndenter indenter, JavaHeuristicScanner scanner, boolean multiLine) throws BadLocationException {
		IRegion currentLine= document.getLineInformation(line);
		int offset= currentLine.getOffset();
		int wsStart= offset; // where we start searching for non-WS; after the "//" in single line comments
		
		String indent= null;
		if (offset < document.getLength()) {
			ITypedRegion partition= TextUtilities.getPartition(document, IJavaScriptPartitions.JS_PARTITIONING, offset, true);
			ITypedRegion startingPartition= TextUtilities.getPartition(document, IJavaScriptPartitions.JS_PARTITIONING, offset, false);
			String type= partition.getType();
			if (type.equals(IJavaScriptPartitions.JS_DOC) || type.equals(IJavaScriptPartitions.JS_COMMENT)) {
				indent= computeJavadocIndent(document, line, scanner, startingPartition);
			} else if (!fIsTabAction && startingPartition.getOffset() == offset && startingPartition.getType().equals(IJavaScriptPartitions.JS_COMMENT)) {
				
				// line comment starting at position 0 -> indent inside
				int max= document.getLength() - offset;
				int slashes= 2;
				while (slashes < max - 1 && document.get(offset + slashes, 2).equals("//")) //$NON-NLS-1$
					slashes+= 2;
				
				wsStart= offset + slashes;
				
				StringBuffer computed= indenter.computeIndentation(offset);
				if (computed == null)
					computed= new StringBuffer(0);
				int tabSize= getTabSize();
				while (slashes > 0 && computed.length() > 0) {
					char c= computed.charAt(0);
					if (c == '\t')
						if (slashes > tabSize)
							slashes-= tabSize;
						else
							break;
					else if (c == ' ')
						slashes--;
					else break;
					
					computed.deleteCharAt(0);
				}
				
				indent= document.get(offset, wsStart - offset) + computed;
				
			}
		} 
		
		// standard java indentation
		if (indent == null) {
			StringBuffer computed= indenter.computeIndentation(offset);
			if (computed != null)
				indent= computed.toString();
			else
				indent= ""; //$NON-NLS-1$
		}
		
		// change document:
		// get current white space
		int lineLength= currentLine.getLength();
		int end= scanner.findNonWhitespaceForwardInAnyPartition(wsStart, offset + lineLength);
		if (end == JavaHeuristicScanner.NOT_FOUND) {
			// an empty line
			end= offset + lineLength;
			if (multiLine && !indentEmptyLines())
				indent= ""; //$NON-NLS-1$
		}
		int length= end - offset;
		String currentIndent= document.get(offset, length);
		
		// if we are right before the text start / line end, and already after the insertion point
		// then just insert a tab.
		if (fIsTabAction && caret == end && whiteSpaceLength(currentIndent) >= whiteSpaceLength(indent)) {
			String tab= getTabEquivalent();
			document.replace(caret, 0, tab);
			fCaretOffset= caret + tab.length();
			return true;
		}
		
		// set the caret offset so it can be used when setting the selection
		if (caret >= offset && caret <= end)
			fCaretOffset= offset + indent.length();
		else
			fCaretOffset= -1;
		
		// only change the document if it is a real change
		if (!indent.equals(currentIndent)) {
			document.replace(offset, length, indent);
			return true;
		} else
			return false;
	}

	/**
	 * Computes and returns the indentation for a javadoc line. The line
	 * must be inside a javadoc comment.
	 * 
	 * @param document the document
	 * @param line the line in document
	 * @param scanner the scanner
	 * @param partition the javadoc partition
	 * @return the indent, or <code>null</code> if not computable
	 * @throws BadLocationException
	 * @since 3.1
	 */
	private String computeJavadocIndent(IDocument document, int line, JavaHeuristicScanner scanner, ITypedRegion partition) throws BadLocationException {
		if (line == 0) // impossible - the first line is never inside a javadoc comment
			return null;
		
		// don't make any assumptions if the line does not start with \s*\* - it might be
		// commented out code, for which we don't want to change the indent
		final IRegion lineInfo= document.getLineInformation(line);
		final int lineStart= lineInfo.getOffset();
		final int lineLength= lineInfo.getLength();
		final int lineEnd= lineStart + lineLength;
		int nonWS= scanner.findNonWhitespaceForwardInAnyPartition(lineStart, lineEnd);
		if (nonWS == JavaHeuristicScanner.NOT_FOUND || document.getChar(nonWS) != '*') {
			if (nonWS == JavaHeuristicScanner.NOT_FOUND)
				return document.get(lineStart, lineLength);
			return document.get(lineStart, nonWS - lineStart);
		}
		
		// take the indent from the previous line and reuse
		IRegion previousLine= document.getLineInformation(line - 1);
		int previousLineStart= previousLine.getOffset();
		int previousLineLength= previousLine.getLength();
		int previousLineEnd= previousLineStart + previousLineLength;
		
		StringBuffer buf= new StringBuffer();
		int previousLineNonWS= scanner.findNonWhitespaceForwardInAnyPartition(previousLineStart, previousLineEnd);
		if (previousLineNonWS == JavaHeuristicScanner.NOT_FOUND || document.getChar(previousLineNonWS) != '*') {
			// align with the comment start if the previous line is not an asterisked line
			previousLine= document.getLineInformationOfOffset(partition.getOffset());
			previousLineStart= previousLine.getOffset();
			previousLineLength= previousLine.getLength();
			previousLineEnd= previousLineStart + previousLineLength;
			previousLineNonWS= scanner.findNonWhitespaceForwardInAnyPartition(previousLineStart, previousLineEnd);
			if (previousLineNonWS == JavaHeuristicScanner.NOT_FOUND)
				previousLineNonWS= previousLineEnd;
			
			// add the initial space 
			// TODO this may be controlled by a formatter preference in the future
			buf.append(' ');
		}
		
		String indentation= document.get(previousLineStart, previousLineNonWS - previousLineStart);
		buf.insert(0, indentation);
		return buf.toString();
	}
	
	/**
	 * Returns the size in characters of a string. All characters count one, tabs count the editor's
	 * preference for the tab display 
	 * 
	 * @param indent the string to be measured.
	 * @return the size in characters of a string
	 */
	private int whiteSpaceLength(String indent) {
		if (indent == null)
			return 0;
		else {
			int size= 0;
			int l= indent.length();
			int tabSize= getTabSize();
			
			for (int i= 0; i < l; i++)
				size += indent.charAt(i) == '\t' ? tabSize : 1;
			return size;
		}
	}

	/**
	 * Returns a tab equivalent, either as a tab character or as spaces, depending on the editor and
	 * formatter preferences.
	 * 
	 * @return a string representing one tab in the editor, never <code>null</code>
	 */
	private String getTabEquivalent() {
		String tab;
		if (" ".equals(getCoreFormatterOption(DefaultCodeFormatterConstants.FORMATTER_TAB_CHAR))) {
			int size= getTabSize();
			StringBuffer buf= new StringBuffer();
			for (int i= 0; i< size; i++)
				buf.append(' ');
			tab= buf.toString();
		} else
			tab= "\t"; //$NON-NLS-1$
	
		return tab;
	}
	
	/**
	 * Returns the tab size used by the java editor, which is deduced from the
	 * formatter preferences.
	 * 
	 * @return the tab size as defined in the current formatter preferences
	 */
	private int getTabSize() {
		return getCoreFormatterOption(DefaultCodeFormatterConstants.FORMATTER_TAB_SIZE, 4);
	}

	/**
	 * Returns <code>true</code> if empty lines should be indented, false otherwise.
	 * 
	 * @return <code>true</code> if empty lines should be indented, false otherwise
	 * @since 3.2
	 */
	private boolean indentEmptyLines() {
		return DefaultCodeFormatterConstants.TRUE.equals(getCoreFormatterOption(DefaultCodeFormatterConstants.FORMATTER_INDENT_EMPTY_LINES));
	}
	
	/**
	 * Returns the possibly project-specific core preference defined under <code>key</code>.
	 * 
	 * @param key the key of the preference
	 * @return the value of the preference
	 * @since 3.1
	 */
	private String getCoreFormatterOption(String key) {
		IDLTKProject project= getJavaProject();
		if (project == null)
			return DLTKCore.getOption(key);
		return project.getOption(key, true);
	}

	/**
	 * Returns the possibly project-specific core preference defined under <code>key</code>, or
	 * <code>def</code> if the value is not a integer.
	 * 
	 * @param key the key of the preference
	 * @param def the default value
	 * @return the value of the preference
	 * @since 3.1
	 */
	private int getCoreFormatterOption(String key, int def) {
		try {
			return Integer.parseInt(getCoreFormatterOption(key));
		} catch (NumberFormatException e) {
			return def;
		}
	}

	/**
	 * Returns the <code>IJavaProject</code> of the current editor input, or
	 * <code>null</code> if it cannot be found.
	 * 
	 * @return the <code>IJavaProject</code> of the current editor input, or
	 *         <code>null</code> if it cannot be found
	 * @since 3.1
	 */
	private IDLTKProject getJavaProject() {
		ITextEditor editor= getTextEditor();
		if (editor == null)
			return null;
		
		ISourceModule cu= DLTKUIPlugin.getDefault().getWorkingCopyManager().getWorkingCopy(editor.getEditorInput());
		if (cu == null)
			return null;
		return cu.getScriptProject();
	}

	/**
	 * Returns the editor's selection provider.
	 * 
	 * @return the editor's selection provider or <code>null</code>
	 */
	private ISelectionProvider getSelectionProvider() {
		ITextEditor editor= getTextEditor();
		if (editor != null) {
			return editor.getSelectionProvider();
		}
		return null;
	}
	
	/*
	 * @see org.eclipse.ui.texteditor.IUpdate#update()
	 */
	public void update() {
		super.update();
		
		if (isEnabled())
			if (fIsTabAction)
				setEnabled(canModifyEditor() && isSmartMode() && isValidSelection());
			else
				setEnabled(canModifyEditor() && !getSelection().isEmpty());
	}
	
	/**
	 * Returns if the current selection is valid, i.e. whether it is empty and the caret in the 
	 * whitespace at the start of a line, or covers multiple lines.
	 * 
	 * @return <code>true</code> if the selection is valid for an indent operation
	 */
	private boolean isValidSelection() {
		ITextSelection selection= getSelection();
		if (selection.isEmpty())
			return false;
		
		int offset= selection.getOffset();
		int length= selection.getLength();
		
		IDocument document= getDocument();
		if (document == null)
			return false;
		
		try {
			IRegion firstLine= document.getLineInformationOfOffset(offset);
			int lineOffset= firstLine.getOffset();
			
			// either the selection has to be empty and the caret in the WS at the line start
			// or the selection has to extend over multiple lines
			if (length == 0)
				return document.get(lineOffset, offset - lineOffset).trim().length() == 0;
			else
//				return lineOffset + firstLine.getLength() < offset + length;
				return false; // only enable for empty selections for now
			
		} catch (BadLocationException e) {
		}
		
		return false;
	}
	
	/**
	 * Returns the smart preference state.
	 * 
	 * @return <code>true</code> if smart mode is on, <code>false</code> otherwise
	 */
	private boolean isSmartMode() {
		ITextEditor editor= getTextEditor();
		
		if (editor instanceof ITextEditorExtension3)
			return ((ITextEditorExtension3) editor).getInsertMode() == ITextEditorExtension3.SMART_INSERT;
		
		return false;
	}
	
	/**
	 * Returns the document currently displayed in the editor, or <code>null</code> if none can be 
	 * obtained.
	 * 
	 * @return the current document or <code>null</code>
	 */
	private IDocument getDocument() {
		
		ITextEditor editor= getTextEditor();
		if (editor != null) {
			
			IDocumentProvider provider= editor.getDocumentProvider();
			IEditorInput input= editor.getEditorInput();
			if (provider != null && input != null)
				return provider.getDocument(input);
			
		}
		return null;
	}
	
	/**
	 * Returns the selection on the editor or an invalid selection if none can be obtained. Returns
	 * never <code>null</code>.
	 * 
	 * @return the current selection, never <code>null</code>
	 */
	private ITextSelection getSelection() {
		ISelectionProvider provider= getSelectionProvider();
		if (provider != null) {
			
			ISelection selection= provider.getSelection();
			if (selection instanceof ITextSelection)
				return (ITextSelection) selection;
		}
		
		// null object
		return TextSelection.emptySelection();
	}
	
}
