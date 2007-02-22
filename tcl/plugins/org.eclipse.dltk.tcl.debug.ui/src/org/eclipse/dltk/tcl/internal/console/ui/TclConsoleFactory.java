package org.eclipse.dltk.tcl.internal.console.ui;

import org.eclipse.dltk.console.IScriptInterpreter;
import org.eclipse.dltk.console.ScriptConsolePrompt;
import org.eclipse.dltk.console.ui.IScriptConsoleFactory;
import org.eclipse.dltk.console.ui.ScriptConsole;
import org.eclipse.dltk.console.ui.ScriptConsoleFactoryBase;
import org.eclipse.dltk.tcl.console.TclConsoleConstants;
import org.eclipse.dltk.tcl.console.TclConsoleUtil;
import org.eclipse.dltk.tcl.console.TclInterpreter;
import org.eclipse.dltk.tcl.internal.debug.ui.TclDebugUIPlugin;
import org.eclipse.jface.preference.IPreferenceStore;


public class TclConsoleFactory extends ScriptConsoleFactoryBase implements
		IScriptConsoleFactory {
	protected IPreferenceStore getPreferenceStore() {
		return TclDebugUIPlugin.getDefault().getPreferenceStore();
	}

	protected ScriptConsolePrompt makeInvitation() {
		IPreferenceStore store = getPreferenceStore();
		return new ScriptConsolePrompt(store
				.getString(TclConsoleConstants.PREF_NEW_PROMPT), store
				.getString(TclConsoleConstants.PREF_CONTINUE_PROMPT));
	}

	protected TclConsole makeConsole(TclInterpreter interpreter, String id) {
		TclConsole console = new TclConsole(interpreter, id);
		console.setPrompt(makeInvitation());
		return console;
	}

	private TclConsole createConsoleInstance(IScriptInterpreter interpreter, String id) {
		if (interpreter == null) {
			try {
				id = "default";
				interpreter = new TclInterpreter();
				TclConsoleUtil
						.runDefaultTclInterpreter((TclInterpreter) interpreter);
			} catch (Exception e) {
				return null;
			}
		}

		return makeConsole((TclInterpreter) interpreter, id);
	}

	protected ScriptConsole createConsoleInstance() {
		return createConsoleInstance(null, null);
	}

	public TclConsoleFactory() {
	}

	public void openConsole(IScriptInterpreter interpreter, String id) {
		registerAndOpenConsole(createConsoleInstance(interpreter, id));
	}
}
