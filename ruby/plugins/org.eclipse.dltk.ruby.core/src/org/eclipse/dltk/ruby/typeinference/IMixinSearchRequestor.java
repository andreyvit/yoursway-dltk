package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ruby.internal.parser.mixin.IRubyMixinElement;

public interface IMixinSearchRequestor {
	
	void acceptResult (IRubyMixinElement element);
	
}
