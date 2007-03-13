package org.eclipse.dltk.ruby.internal.parsers.jruby;

import org.jruby.common.IRubyWarnings;
import org.jruby.lexer.yacc.ISourcePosition;

public interface IDLTKRubyWarnings extends IRubyWarnings {

    public abstract void error(ISourcePosition position, String message);
	
}
