/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.ast;

import java.util.regex.Pattern;

import org.eclipse.dltk.ast.expressions.StringLiteral;

public class RubyRegexpExpression extends StringLiteral {

	private Pattern pattern;
	
	public RubyRegexpExpression(int start, int end, String value) {
		super(start, end, value);
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

}
