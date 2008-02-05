package org.eclipse.dltk.ruby.internal.ui.text;

import java.util.Arrays;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.internal.ui.RubyUI;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.text.util.AutoEditUtils;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextUtilities;

public class RubyAutoEditStrategy extends DefaultIndentLineAutoEditStrategy {
	
	private static final int[] INDENT_TO_BLOCK_TOKENS = {
			IRubySymbols.TokenELSE, IRubySymbols.TokenELSIF,
			IRubySymbols.TokenEND, IRubySymbols.TokenENSURE,
			IRubySymbols.TokenRESCUE, IRubySymbols.TokenWHEN,
			IRubySymbols.TokenRBRACE };

	private static final int[] CONTINUATION_TOKENS = {
			IRubySymbols.TokenBACKSLASH, IRubySymbols.TokenCOMMA,
			IRubySymbols.TokenSLASH, IRubySymbols.TokenPLUS,
			IRubySymbols.TokenMINUS, IRubySymbols.TokenSTAR };

	private static final int[] REMOVE_IDENTATION_TOKENS = {
			IRubySymbols.TokenRDOCBEGIN, IRubySymbols.TokenRDOCEND };

	static {
		Arrays.sort(INDENT_TO_BLOCK_TOKENS);
		Arrays.sort(CONTINUATION_TOKENS);
		Arrays.sort(REMOVE_IDENTATION_TOKENS);
	}

	private boolean fIsSmartMode;
	private boolean fCloseBlocks = true;
	private RubyPreferenceInterpreter fPreferences;

	public RubyAutoEditStrategy(String partitioning) {
		this(partitioning, RubyUI.getDefault().getPreferenceStore());
	}

	public RubyAutoEditStrategy(String partitioning, IPreferenceStore store) {
		fPreferences = new RubyPreferenceInterpreter(store);
	}

	private void clearCachedValues() {
		fCloseBlocks = fPreferences.closeBlocks();
		fIsSmartMode = fPreferences.isSmartMode();
	}

	private void closeBlock(IDocument d, DocumentCommand c, String indent,
			String afterCursor, RubyHeuristicScanner scanner) throws BadLocationException {
		c.caretOffset = c.offset + c.text.length();
		c.length = afterCursor.length();
		c.shiftsCaret = false;
		String delimiter = TextUtilities.getDefaultLineDelimiter(d);		
		c.text += afterCursor.trim() + delimiter + indent + getApropriateBlockEnding(d, scanner, c.offset);
	}

	private String getApropriateBlockEnding(IDocument d,
			RubyHeuristicScanner scanner, int offset) throws BadLocationException {
		int beginning = scanner.findBlockBeginningOffset(offset);
		IRegion line = d.getLineInformationOfOffset(beginning);
		int ending = Math.min(line.getOffset() + line.getLength(), offset);
		int token = scanner.previousToken(ending, beginning);
		if (token == IRubySymbols.TokenLBRACE) {
			return "}";
		} else {
			return "end";
		}
	}

	private boolean isSmartMode() {
		return fIsSmartMode;
	}

	public void customizeDocumentCommand(IDocument d, DocumentCommand c) {
		if (c.doit == false)
			return;

		clearCachedValues();
		if (!isSmartMode()) {
			super.customizeDocumentCommand(d, c);
			return;
		}

		try {
			if (c.length == 0 && c.text != null && isLineDelimiter(d, c.text))
				smartIndentAfterNewLine(d, c);
			else if (c.text.length() == 1)
				smartIndentOnKeypress(d, c);
			else if (c.text.length() > 1 && fPreferences.isSmartPaste())
				smartPaste(d, c); // no smart backspace for paste
			else
				super.customizeDocumentCommand(d, c);
		} catch (BadLocationException e) {
			DLTKUIPlugin.log(e);
		}
	}

	private boolean isLineDelimiter(IDocument document, String text) {
		String[] delimiters = document.getLegalLineDelimiters();
		if (delimiters != null)
			return TextUtilities.equals(delimiters, text) > -1;
		return false;
	}

	private void smartIndentOnKeypress(IDocument d, DocumentCommand c)
			throws BadLocationException {
		RubyHeuristicScanner scanner = new RubyHeuristicScanner(d);
		IRegion info = d.getLineInformationOfOffset(c.offset);
		int token = scanner.previousTokenAfterInput(c.offset, c.text);

		if (Arrays.binarySearch(INDENT_TO_BLOCK_TOKENS, token) >= 0) {
			String indent = "";
			try {
				indent = getBlockIndent(d, c, scanner);
			}
			catch (BadLocationException e) {
				// there is no enclosing block
			}	
			
			int pos = scanner.findNonWhitespaceForwardInAnyPartition(info
					.getOffset(), c.offset);
			String line = "";
			if (pos != RubyHeuristicScanner.NOT_FOUND) {
				line = d.get(pos, c.offset - pos);
			}
			
			c.text = indent + line + c.text;
			c.length = c.offset - info.getOffset();
			c.offset = info.getOffset();

		} else if (Arrays.binarySearch(REMOVE_IDENTATION_TOKENS, token) >= 0) {
			int start = scanner.findNonWhitespaceForward(info.getOffset(),
					c.offset);
			c.text = d.get(start, c.offset - start) + c.text;
			c.length = c.offset - info.getOffset();
			c.offset = info.getOffset();
		}
	}

	private String getBlockIndent(IDocument d, DocumentCommand c,
			RubyHeuristicScanner scanner) throws BadLocationException {

		int blockOffset = scanner.findBlockBeginningOffset(c.offset);
		if (blockOffset == RubyHeuristicScanner.NOT_FOUND)
			throw new BadLocationException("Block not found");
			
		String indent = AutoEditUtils.getLineIndent(d, d
				.getLineOfOffset(blockOffset));
		return indent;
	}

	private void smartIndentAfterNewLine(IDocument d, DocumentCommand c)
			throws BadLocationException {
		IRegion line = d.getLineInformationOfOffset(c.offset);
		int lineEnd = line.getOffset() + line.getLength();
		RubyHeuristicScanner scanner = new RubyHeuristicScanner(d);

		// eat pending whitespace
		int nonWsPos = scanner.findNonWhitespaceForwardInAnyPartition(c.offset,
				lineEnd);
		if (nonWsPos != RubyHeuristicScanner.NOT_FOUND) {
			c.length = nonWsPos - c.offset;
		}

		// if pending statement is end, else etc. then indent it to block
		// beginning
		if (nextIsIdentToBlockToken(scanner, c.offset, lineEnd)) {
			try {
				c.text += getBlockIndent(d, c, scanner);
			}
			catch (BadLocationException e) {
				// there is no enclosing block
			}			
			return;
		}

		// else
		String indent = getPreviousLineIndent(d, c, scanner);
		c.text += indent;

		if (previousIsBlockBeginning(d, scanner, c.offset)) {
			// if this line was beginning of the block
			c.text += fPreferences.getIndent();

			// Auto close blocks
			if (fCloseBlocks && !isBlockClosed(d, c.offset)) {
				closeBlock(d, c, indent, d.get(c.offset, lineEnd - c.offset), scanner);
			}
		} else if (previousIsFirstContinuation(d, scanner, c.offset, line
				.getOffset())) {
			// or if this line was the first line ending with one of
			// continuation symbols
			c.text += fPreferences.getIndent();

		} else if (hasUnclosedParen(scanner, c.offset, line.getOffset())) {
			// or if this line contains unclosed paren
			c.text += fPreferences.getIndent();
		}
	}

	private boolean hasUnclosedParen(RubyHeuristicScanner scanner, int offset,
			int bound) {
		int pos = scanner.findOpeningPeer(offset, bound, '(', ')');
		return pos != RubyHeuristicScanner.NOT_FOUND;
	}

	private boolean previousIsFirstContinuation(IDocument d,
			RubyHeuristicScanner scanner, int offset, int bound)
			throws BadLocationException {

		IRegion previousLine = null;
		int line = d.getLineOfOffset(offset);
		if (line > 0) {
			previousLine = d.getLineInformation(line - 1);
		}

		return previousIsContinuation(scanner, offset, bound)
				&& (previousLine == null || !previousIsContinuation(scanner,
						previousLine.getOffset() + previousLine.getLength(),
						previousLine.getOffset()));

	}

	private boolean previousIsContinuation(RubyHeuristicScanner scanner,
			int offset, int bound) {
		int token = scanner.previousToken(offset, bound);
		return Arrays.binarySearch(CONTINUATION_TOKENS, token) >= 0;
	}

	private boolean previousIsBlockBeginning(IDocument d,
			RubyHeuristicScanner scanner, int offset)
			throws BadLocationException {
		int previousLineOffset = scanner.findPrecedingNotEmptyLine(offset);
		IRegion previousLine = d.getLineInformationOfOffset(previousLineOffset);
		int previousLineEnd = Math.min(previousLine.getOffset()
				+ previousLine.getLength(), offset);

		boolean previousIsBlockBeginning = scanner.isBlockBeginning(
				previousLine.getOffset(), previousLineEnd)
				|| scanner.isBlockMiddle(previousLine.getOffset(),
						previousLineEnd);
		return previousIsBlockBeginning;
	}

	private boolean nextIsIdentToBlockToken(RubyHeuristicScanner scanner,
			int offset, int bound) {
		int token = scanner.nextToken(offset, bound);
		return Arrays.binarySearch(INDENT_TO_BLOCK_TOKENS, token) >= 0;
	}

	private void smartPaste(IDocument d, DocumentCommand c)
			throws BadLocationException {
		// fix first line whitespace
		IRegion info = d.getLineInformationOfOffset(c.offset);
		String line = d.get(info.getOffset(), c.offset - info.getOffset());
		if (line.trim().length() == 0) {
			c.length += line.length();
			c.offset -= line.length();
		}

		RubyHeuristicScanner scanner = new RubyHeuristicScanner(d);
		String indent = "";
		try {
			indent = getBlockIndent(d, c, scanner) + fPreferences.getIndent();
		}
		catch (BadLocationException e) {
			// there is no enclosing block
		}	

		String delimiter = TextUtilities.getDefaultLineDelimiter(d);
		boolean addLastDelimiter = c.text.endsWith(delimiter);
		String[] lines = c.text.split(delimiter);
		if (lines.length > 0) {
			String currentIndent = "";
			for (int i = 0; i < lines.length; i++) {
				if (lines[i].trim().length() != 0) {
					currentIndent = AutoEditUtils.getLineIndent(lines[i]);
					break;
				}
			}

			int shift = computeVisualLength(indent)
					- computeVisualLength(currentIndent);
			StringBuffer result = new StringBuffer();
			for (int i = 0; i < lines.length - 1; i++) {
				result.append(shiftIdentation(lines[i], shift)).append(
						delimiter);
			}
			result.append(shiftIdentation(lines[lines.length - 1], shift));
			if (addLastDelimiter) {
				result.append(delimiter);
			}

			c.text = result.toString();
		}
	}

	private String shiftIdentation(String line, int shift) {
		if (shift > 0) {
			return fPreferences.getIndentByVirtualSize(shift) + line;
		} else {
			int pos = 0;
			shift *= -1;
			while (shift > 0 && Character.isWhitespace(line.charAt(pos))) {
				String ws = Character.toString(line.charAt(pos));
				shift -= computeVisualLength(ws);
				pos++;
			}
			return line.substring(pos);
		}
	}

	/**
	 * Computes the length of a <code>CharacterSequence</code>, counting a
	 * tab character as the size until the next tab stop and every other
	 * character as one.
	 * 
	 * @param indent
	 *            the string to measure
	 * @return the visual length in characters
	 */
	private int computeVisualLength(CharSequence indent) {
		final int tabSize = fPreferences.getTabSize();
		int length = 0;
		for (int i = 0; i < indent.length(); i++) {
			char ch = indent.charAt(i);
			switch (ch) {
			case '\t':
				if (tabSize > 0) {
					int reminder = length % tabSize;
					length += tabSize - reminder;
				}
				break;
			case ' ':
				length++;
				break;
			}
		}
		return length;
	}

	/**
	 * Computes the indentation at <code>offset</code>.
	 * 
	 * @param scanner
	 * 
	 * @param offset
	 *            the offset in the document
	 * @return a String which reflects the correct indentation for the line in
	 *         which offset resides, or <code>null</code> if it cannot be
	 *         determined
	 * @throws BadLocationException
	 */
	private String getPreviousLineIndent(IDocument d, DocumentCommand c,
			RubyHeuristicScanner scanner) throws BadLocationException {
		StringBuffer result = new StringBuffer();

		if (c.offset == -1 || d.getLength() == 0)
			return result.toString();

		// find start of line
		int start = scanner.findPrecedingNotEmptyLine(c.offset);
		IRegion info = d.getLineInformationOfOffset(start);
		int end = scanner.findNonWhitespaceForwardInAnyPartition(start, start
				+ info.getLength());

		if (end > start) {
			// append to input
			result.append(d.get(start, end - start));
		}
		return result.toString();
	}

	private boolean isBlockClosed(IDocument document, int offset)
			throws BadLocationException {
		RubyHeuristicScanner scanner = new RubyHeuristicScanner(document);
		IRegion sourceRange = scanner.findSurroundingBlock(offset);
		if (sourceRange != null) {
			String source = document.get(sourceRange.getOffset(), sourceRange
					.getLength());
			char[] buffer = source.toCharArray();

			SyntaxErrorListener listener = new SyntaxErrorListener();
			ISourceParser parser;
			try {
				parser = DLTKLanguageManager
						.getSourceParser(RubyNature.NATURE_ID);
				parser.parse(null, buffer, listener);
				if (listener.errorOccured())
					return false;
			} catch (CoreException e) {
				DLTKUIPlugin.log(e);
			}
		}
		return true;
	}

	private static class SyntaxErrorListener implements IProblemReporter {
		private boolean fError = false;

		public void clearMarkers() {
		}

		public IMarker reportProblem(IProblem problem) throws CoreException {
			int id = problem.getID();
			if ((id & IProblem.Syntax) != 0 || id == IProblem.Unclassified) {
				fError = true;
			}
			return null;
		}

		public boolean errorOccured() {
			return fError;
		}
	}
}
