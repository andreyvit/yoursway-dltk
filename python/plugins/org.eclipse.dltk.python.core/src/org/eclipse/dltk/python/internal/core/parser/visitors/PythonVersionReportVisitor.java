/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.core.parser.visitors;

import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.ExpressionConstants;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.python.parser.ast.PythonYieldStatement;
import org.eclipse.dltk.python.parser.ast.expressions.BinaryExpression;
import org.eclipse.dltk.python.parser.ast.expressions.NotStrictAssignment;
import org.eclipse.dltk.python.parser.ast.expressions.PythonForListExpression;
import org.eclipse.dltk.python.parser.ast.expressions.PythonImportAsExpression;

/**
 * Used to detect used python version.
 * 
 * @author haiodo
 * 
 */
public class PythonVersionReportVisitor extends ASTVisitor {

	/**
	 * 1.5.x and all before it.
	 */
	public static final int VERSION_15x = 0x0105;

	/**
	 * Added strict assignments. +=, -=, *=, /=, %=, &= |=, ^=, <<=, >>=, **=
	 */
	public static final int VERSION_20x = 0x0200;

	/**
	 * Added import as expressions.
	 */
	public static final int VERSION_21x = 0x0201;

	/**
	 * Removed ^= Added //= In term added //.
	 * 
	 */
	public static final int VERSION_22x = 0x0202;

	public static final int VERSION_23x = 0x0203;

	/**
	 * Decorators Added.
	 */
	public static final int VERSION_24x = 0x0204;

	/**
	 * Version of python needed by this code is.
	 */
	private int fCurrentVersion = VERSION_15x;

	private int fProjectVersion = 0;

	/**
	 * Construct version detect visitor.
	 * 
	 * TODO: Add reporting handler here.
	 */
	public PythonVersionReportVisitor(int version) {
		this.fProjectVersion = version;
	}

	/**
	 * Return requred Python version by selected AST None.
	 * 
	 * @return
	 */
	public int getRequredVersion() {
		return this.fCurrentVersion;
	}

	public String getRequestedVersionStr() {
		return this.getVersionStr(this.fCurrentVersion);
	}

	public String getVersionStr(int version) {
		if (version < VERSION_15x) {
			return "Python <= 1.5.x";
		} else if (version >= VERSION_15x && version < VERSION_20x) {
			return "Python >=1.5.x and < 2.0.0 ";
		} else if (version >= VERSION_20x && version < VERSION_21x) {
			return "Python 2.0.x ";
		} else if (version >= VERSION_21x && version < VERSION_22x) {
			return "Python >=2.1.x and < 2.2.0 ";
		} else if (version >= VERSION_22x && version < VERSION_23x) {
			return "Python >=2.2.x and < 2.3.0 ";
		} else if (version >= VERSION_23x && version < VERSION_24x) {
			return "Python >=2.3.x and < 2.4.0 ";
		} else if (version >= VERSION_24x) {
			return "Python >=2.4.x";
		}
		return "Python version not determined.";
	}

	public boolean visit(Expression expression) throws Exception {
		if (expression instanceof NotStrictAssignment) {
			switch (expression.getKind()) {
			case ExpressionConstants.E_DIV_ASSIGN:
			case ExpressionConstants.E_PLUS_ASSIGN:
			case ExpressionConstants.E_MINUS_ASSIGN:
			case ExpressionConstants.E_MULT_ASSIGN:
			case ExpressionConstants.E_MOD_ASSIGN:
			case ExpressionConstants.E_SR_ASSIGN:
			case ExpressionConstants.E_SL_ASSIGN:
			case ExpressionConstants.E_BAND_ASSIGN:
			case ExpressionConstants.E_BOR_ASSIGN:
			case ExpressionConstants.E_DOUBLESTAR_ASSIGN:
				this.requreVersion(VERSION_20x, expression);
				break;
			case ExpressionConstants.E_DOUBLEDIV_ASSIGN:
			case ExpressionConstants.E_BXOR_ASSIGN:
				this.requreVersion(VERSION_22x, expression);
				break;
			}
		}
		if (expression instanceof BinaryExpression) {
			BinaryExpression binaryExpression = (BinaryExpression) expression;
			if (binaryExpression.getKind() == ExpressionConstants.E_MOD) {
				this.requreVersion(VERSION_22x, expression);
			}
		}
		if (expression instanceof PythonForListExpression
				|| expression instanceof PythonForListExpression) {
			this.requreVersion(VERSION_20x, expression);
		}
		if (expression instanceof PythonImportAsExpression) {
			this.requreVersion(VERSION_21x, expression);

		}
		return true;
	}

	public boolean visit(MethodDeclaration s) throws Exception {
		List decorators = s.getDecorators();
		if (decorators != null && decorators.size() > 0) {
			this.requreVersion(VERSION_24x, s);
		}
		return true;
	}

	private void requreVersion(int version, Statement statement) {

		if (this.fCurrentVersion < version) {
			this.fCurrentVersion = version;
		}
		if (this.fCurrentVersion > this.fProjectVersion) {
			System.out.println("Project requred version:"
					+ statement.toString());
			// TODO:: Add version error setting here.
		}
	}

	public boolean visit(Statement statement) throws Exception {

		if (statement instanceof PythonYieldStatement) {
			this.requreVersion(VERSION_22x, statement);
		}
		return true;
	}
}
