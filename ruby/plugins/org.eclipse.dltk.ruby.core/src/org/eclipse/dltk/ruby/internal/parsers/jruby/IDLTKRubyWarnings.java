/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.parsers.jruby;

import org.jruby.common.IRubyWarnings;
import org.jruby.lexer.yacc.ISourcePosition;

public interface IDLTKRubyWarnings extends IRubyWarnings {

    public abstract void error(ISourcePosition position, String message);
	
}
