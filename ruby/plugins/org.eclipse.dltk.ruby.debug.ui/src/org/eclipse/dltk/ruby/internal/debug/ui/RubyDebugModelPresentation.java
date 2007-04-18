package org.eclipse.dltk.ruby.internal.debug.ui;

import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.ui.ScriptDebugModelPresentation;
import org.eclipse.ui.IEditorInput;

public class RubyDebugModelPresentation extends ScriptDebugModelPresentation {
	private static final String RUBY_EDITOR_ID = "org.eclipse.dltk.ruby.ui.editor.RubyEditor";
	
	private static final String MAIN_THREAD_NAME = "Main thread";
	
	protected String getThreadText(IScriptThread thread) {
		return MAIN_THREAD_NAME;
	}
	
	public String getEditorId(IEditorInput input, Object element) {		
		return RUBY_EDITOR_ID;
	}
}
