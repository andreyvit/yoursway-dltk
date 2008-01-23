/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.parser;

import org.eclipse.dltk.compiler.SourceElementRequestVisitor;
import org.eclipse.dltk.core.AbstractSourceElementParser;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.internal.parser.visitors.RubySourceElementRequestor;

public class RubySourceElementParser extends AbstractSourceElementParser {

	/*
	 * @see org.eclipse.dltk.core.AbstractSourceElementParser#createVisitor()
	 */
	protected SourceElementRequestVisitor createVisitor() {
		return new RubySourceElementRequestor(getRequestor());
	}

	/*
	 * @see org.eclipse.dltk.core.AbstractSourceElementParser#getNatureId()
	 */
	protected String getNatureId() {
		return RubyNature.NATURE_ID;
	}
}
