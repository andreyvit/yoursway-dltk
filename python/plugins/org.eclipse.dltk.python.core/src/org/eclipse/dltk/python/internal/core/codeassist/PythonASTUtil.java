/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.core.codeassist;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.utils.ASTUtil;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.python.internal.core.parser.PythonParseUtils;

public class PythonASTUtil extends ASTUtil {
	public static void extendStatements(ASTNode node, String content) {
		List statements = getStatements(node);

		if (statements != null) {
			int len = statements.size();
			for (int i = 0; i < len; ++i) {
				ASTNode nde = (ASTNode) statements.get(i);

				extendStatement(nde, content);
				extendStatements(nde, content);
			}
		}
	}

	public static void extendStatement(ASTNode node, String content) {
		int newValueStart = startLineOrSymbol(node, content);
		int newValueEnd = endLineOrSymbol(node, content);
		if (DLTKCore.DEBUG_COMPLETION) {
			if (node.sourceEnd() != newValueEnd
					|| node.sourceStart() != newValueStart) {
				System.out.println("Node Extended from:'"
						+ content.substring(node.sourceStart(), node
								.sourceEnd()) + "'" + "to '"
						+ content.substring(newValueStart, newValueEnd) + "'");
			}
		}
		node.setStart(newValueStart);
		node.setEnd(newValueEnd);
	}

	public static int endLineOrSymbol(ASTNode node, String content) {
		return PythonParseUtils.endLineOrSymbol(node.sourceEnd(), content);
	}

	public static int startLineOrSymbol(ASTNode node, String content) {
		return PythonParseUtils.startLineOrSymbol(node.sourceStart(), content);
	}
}
