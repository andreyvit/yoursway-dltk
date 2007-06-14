/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.declarations.ISourceParser;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.ast.expressions.TclExecuteExpression;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.internal.parser.TclSourceParser;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension4;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.source.ICharacterPairMatcher;

/**
 * Helper class for match pairs of characters.
 */
public final class TclPairMatcher implements ICharacterPairMatcher {

	//private char[] fPairs;

	private IDocument fDocument;

	private int fOffset;

	private int fStartPos;

	private int fEndPos;

	private int fAnchor;

	private class PairBlock {
		public PairBlock(int start, int end, char c) {
			this.start = start;
			this.end = end;
			this.c = c;
		}

		int start;

		int end;

		char c;
	};

	private PairBlock[] cachedPairs;

	private long cachedStamp = -1;

	private long cachedHash = -1;

	public TclPairMatcher(char[] pairs) {
		//if (pairs == null) {
		//	throw new IllegalArgumentException();
		//}

		//fPairs = pairs;
	}

	private PairBlock[] computePairRanges(int offset, String contents) {
		ISourceParser pp = null;
		try {
			pp = DLTKLanguageManager.getSourceParser(TclNature.NATURE_ID);
		} catch (CoreException e1) {
			if(DLTKCore.DEBUG ) {
				e1.printStackTrace();
			}
			return new PairBlock[0];
		}
		ModuleDeclaration md = pp.parse(null, contents.toCharArray(), null);
		if (md == null) {
			return new PairBlock[0];
		}
		List statements = md.getStatements();
		if (statements == null) {
			return new PairBlock[0];
		}
		List result = new ArrayList();
		Iterator i = statements.iterator();
		while (i.hasNext()) {
			Statement sst = (Statement) i.next();
			if (sst instanceof TclStatement) {
				TclStatement statement = (TclStatement) sst;
				/*
				 * result.add(new CodeBlock(statement, new Region(offset +
				 * statement.sourceStart(), statement.sourceEnd() -
				 * statement.sourceStart())));
				 */
				Iterator si = statement.getExpressions().iterator();
				while (si.hasNext()) {
					Expression ex = (Expression) si.next();
					if (ex instanceof TclBlockExpression) {
						TclBlockExpression be = (TclBlockExpression) ex;
						try {
							String newContents = contents.substring(be
									.sourceStart() + 1, be.sourceEnd() - 1);
							result.add(new PairBlock(offset + be.sourceStart(),
									offset + be.sourceEnd() - 1, '{'));
							PairBlock[] cb = computePairRanges(offset
									+ be.sourceStart() + 1, newContents);
							for (int j = 0; j < cb.length; j++) {
								result.add(cb[j]);
							}
						} catch (StringIndexOutOfBoundsException e) {
						}
					} else if (ex instanceof StringLiteral) {
						StringLiteral be = (StringLiteral) ex;
						result.add(new PairBlock(offset + be.sourceStart(),
								offset + be.sourceEnd() - 1, '\"'));
					} else if (ex instanceof TclExecuteExpression) {
						TclExecuteExpression be = (TclExecuteExpression) ex;
						result.add(new PairBlock(offset + be.sourceStart(),
								offset + be.sourceEnd() - 1, '['));
					}
				}
			}
		}
		return (PairBlock[]) result.toArray(new PairBlock[result.size()]);
	}

	/**
	 * Fully recalcs pairs for document
	 * 
	 * @param doc
	 * @throws BadLocationException
	 */
	private void recalc() throws BadLocationException {
		String content = fDocument.get(0, fDocument.getLength());
		cachedPairs = computePairRanges(0, content);

		if (fDocument instanceof IDocumentExtension4) {
			cachedStamp = ((IDocumentExtension4) fDocument)
					.getModificationStamp();
		} else {
			cachedHash = content.hashCode();
		}
	}

	/**
	 * Recalcs pairs for the document, only if it is required
	 */
	private void updatePairs() throws BadLocationException {
		if (fDocument instanceof IDocumentExtension4) {
			IDocumentExtension4 document = (IDocumentExtension4) fDocument;

			if (document.getModificationStamp() == cachedStamp) {
				return;
			}

		} else {
			String content = fDocument.get(0, fDocument.getLength());

			if (content.hashCode() == cachedHash) {
				return;
			}
		}

		recalc();
	}

	private static boolean isBrace(char c) {
		return (c == '{' || c == '}' || c == '\"' || c == '[' || c == ']');
	}

	public IRegion match(IDocument document, int offset) {
		if (document == null || offset < 0) {
			throw new IllegalArgumentException();
		}

		try {
			fOffset = offset;
			fDocument = document;

			if (!isBrace(fDocument.getChar(offset))
					&& (offset == 0 || !isBrace(fDocument.getChar(offset - 1)))) {
				return null;
			}

			updatePairs();

			if (matchPairsAt() && fStartPos != fEndPos)
				return new Region(fStartPos, fEndPos - fStartPos + 1);
		} catch (BadLocationException e) {
			if (DLTKCore.DEBUG_PARSER) 
				e.printStackTrace();
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.source.ICharacterPairMatcher#getAnchor()
	 */
	public int getAnchor() {
		return fAnchor;
	}

	public void dispose() {
		clear();
		fDocument = null;
	}

	public void clear() {
	}

	private boolean matchPairsAt() {

		fStartPos = -1;
		fEndPos = -1;

		for (int i = 0; i < cachedPairs.length; i++) {
			PairBlock block = cachedPairs[i];

			if (fOffset == block.end + 1) {
				fStartPos = block.start - 1;
				fEndPos = block.start;
				fAnchor = LEFT;
				return true;
			}
			if (fOffset == block.start + 1) {
				fStartPos = block.end - 1;
				fEndPos = block.end;
				fAnchor = LEFT;
				return true;
			}

		}

		return false;
	}
}
