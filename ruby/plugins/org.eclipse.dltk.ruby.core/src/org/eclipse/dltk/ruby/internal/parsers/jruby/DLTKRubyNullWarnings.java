package org.eclipse.dltk.ruby.internal.parsers.jruby;

import org.jruby.common.NullWarnings;
import org.jruby.lexer.yacc.ISourcePosition;

public class DLTKRubyNullWarnings extends NullWarnings implements IDLTKRubyWarnings {

	public void error(ISourcePosition position, String message) {}

	
}
