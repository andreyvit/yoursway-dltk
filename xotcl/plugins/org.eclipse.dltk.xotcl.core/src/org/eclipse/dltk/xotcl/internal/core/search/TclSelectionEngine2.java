/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.xotcl.internal.core.search;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.codeassist.IAssistParser;
import org.eclipse.dltk.codeassist.ScriptSelectionEngine;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.internal.codeassist.select.SelectionNodeFound;
import org.eclipse.dltk.tcl.core.TclLanguageToolkit;
import org.eclipse.dltk.tcl.internal.parser.TclParseUtils;

public class TclSelectionEngine2 extends ScriptSelectionEngine {
	public static boolean DEBUG = DLTKCore.DEBUG_SELECTION;

	private int actualSelectionStart;

	private int actualSelectionEnd;

	protected List selectionElements = new ArrayList();

	private TclSelectionParser2 parser = new TclSelectionParser2();

	private org.eclipse.dltk.core.ISourceModule sourceModule;

	private IDLTKLanguageToolkit toolkit;

	public TclSelectionEngine2() {
		this.toolkit = TclLanguageToolkit.getDefault();
	}

	public IModelElement[] select(ISourceModule sourceUnit,
			int selectionSourceStart, int selectionSourceEnd) {
		sourceModule = (org.eclipse.dltk.core.ISourceModule) sourceUnit
				.getModelElement();
		String content = sourceUnit.getSourceContents();

		if (DEBUG) {
			System.out.print("SELECTION IN "); //$NON-NLS-1$
			System.out.print(sourceUnit.getFileName());
			System.out.print(" FROM "); //$NON-NLS-1$
			System.out.print(selectionSourceStart);
			System.out.print(" TO "); //$NON-NLS-1$
			System.out.println(selectionSourceEnd);
			System.out.println("SELECTION - Source :"); //$NON-NLS-1$
			System.out.println(content);
		}

		if (!checkSelection(content, selectionSourceStart, selectionSourceEnd)) {
			return new IModelElement[0];
		}

		if (DEBUG) {
			System.out.print("SELECTION - Checked : \""); //$NON-NLS-1$
			System.out.print(content.substring(actualSelectionStart,
					actualSelectionEnd));
			System.out.println('"');
		}

		try {
			ModuleDeclaration parsedUnit = (ModuleDeclaration) this.parser
					.parse(sourceUnit);
			if (parsedUnit != null) {
				try {
					parseBlockStatements(parsedUnit, this.actualSelectionStart);
					if (DEBUG) {
						System.out.println("COMPLETION - AST :"); //$NON-NLS-1$
						System.out.println(parsedUnit.toString());
					}
					// parsedUnit.resolve();
				} catch (SelectionNodeFound e) {
					// completionNodeFound = true;
					if (e.getNode() != null) {
						if (DEBUG) {
							System.out.print("COMPLETION - Completion node : "); //$NON-NLS-1$
							System.out.println(e.getNode().toString());
							if (this.parser.getAssistNodeParent() != null) {
								System.out.print("COMPLETION - Parent Node : "); //$NON-NLS-1$
								System.out.println(this.parser
										.getAssistNodeParent());
							}
						}
						// if null then we found a problem in the completion
						// node
						select(e.getNode(), this.parser.getAssistNodeParent());
					}
				}
			}
		} catch (IndexOutOfBoundsException e) { // work-around internal failure
			// - 1GEMF6D
			if (DEBUG) {
				System.out.println("Exception caught by SelectionEngine:"); //$NON-NLS-1$
				e.printStackTrace(System.out);
			}
		}

		return (IModelElement[]) selectionElements
				.toArray(new IModelElement[selectionElements.size()]);
	}

	protected void select(ASTNode astNode, ASTNode astNodeParent) {
	}

	public IAssistParser getParser() {
		return parser;
	}

	protected IParent getSourceModule() {
		return this.sourceModule;
	}

	private boolean checkSelection(String source, int selectionSourceStart,
			int selectionSourceEnd) {

		boolean cheat = false;
		if (selectionSourceEnd < selectionSourceStart) {
			selectionSourceEnd = selectionSourceStart;
			cheat = true;
		}

		int start = TclParseUtils.startLineOrNoSymbol(selectionSourceStart,
				source);
		int end = TclParseUtils.endLineOrNoSymbol(selectionSourceEnd, source);
		if (end <= start) {
			if (cheat)
				return checkSelection(source, selectionSourceEnd - 1,
						selectionSourceEnd - 1);
			return false;
		}
		if (start > source.length() || end > source.length()) {
			if (cheat)
				return checkSelection(source, selectionSourceEnd - 1,
						selectionSourceEnd - 1);
			return false;
		}

		boolean isVariable = false;
		if (source.charAt(start) == '$') {
			isVariable = true;
		} else {
			if (start > 0) { // check if it is variable
				if (source.charAt(start - 1) == '{') {
					if (start - 1 > 0 && source.charAt(start - 2) == '$') {
						start -= 2;
						isVariable = true;
						while (end < source.length()
								&& source.charAt(end) != '}')
							end++;
						end++;
					}
				}
			}
		}
		if (isVariable && end < source.length() && source.charAt(end) == '(') {// it
			// is
			// array
			while (end < source.length() && source.charAt(end) != ')')
				end++;
			end++;
		}
		String sub = source.substring(start, end);
		// If contain tabs or spaces, then from start.
		if (!isVariable
				&& (sub.indexOf(' ') != -1 || sub.indexOf('\t') != -1 || sub
						.indexOf('\n') != -1)) {
			if (cheat)
				return checkSelection(source, selectionSourceEnd - 1,
						selectionSourceEnd - 1);
			return false;
		}
		this.actualSelectionStart = start;
		this.actualSelectionEnd = end;
		return true;
	}
}
