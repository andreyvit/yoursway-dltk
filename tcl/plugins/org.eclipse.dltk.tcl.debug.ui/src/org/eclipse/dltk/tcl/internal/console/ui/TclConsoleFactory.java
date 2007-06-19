/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.console.ui;

import org.eclipse.dltk.console.IScriptInterpreter;
import org.eclipse.dltk.console.ScriptConsolePrompt;
import org.eclipse.dltk.console.ui.IScriptConsoleFactory;
import org.eclipse.dltk.console.ui.ScriptConsole;
import org.eclipse.dltk.console.ui.ScriptConsoleFactoryBase;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.launching.LaunchingMessages;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.tcl.console.TclConsoleConstants;
import org.eclipse.dltk.tcl.console.TclConsoleUtil;
import org.eclipse.dltk.tcl.console.TclInterpreter;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.internal.debug.ui.TclDebugUIPlugin;
import org.eclipse.dltk.ui.DLTKUILanguageManager;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.ui.dialogs.PreferencesUtil;


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
	protected void showInterpreterPreferencePage(String natureId) {
		String preferencePageId = null;
		IDLTKUILanguageToolkit languageToolkit = null;
		languageToolkit = DLTKUILanguageManager.getLanguageToolkit(natureId);
		if( languageToolkit == null ) {
			return;
		}
		preferencePageId = languageToolkit.getInterpreterPreferencePage();

		if (preferencePageId != null) {
			PreferenceDialog dialog = PreferencesUtil.createPreferenceDialogOn(
					null, preferencePageId, null, null);
			dialog.open();
		}
	}
	private void showQuestion() {
		final boolean result[] = new boolean[] { false };
		DLTKDebugUIPlugin.getStandardDisplay().syncExec(new Runnable() {
			public void run() {
				String title = LaunchingMessages.NoDefaultInterpreterStatusHandler_title;
				String message = LaunchingMessages.NoDefaultInterpreterStatusHandler_message;
				result[0] = (MessageDialog.openQuestion(DLTKDebugUIPlugin
						.getActiveWorkbenchShell(), title, message));
				if (result[0]) {
					showInterpreterPreferencePage(TclNature.NATURE_ID);
				}
			}
		});
	}
	private TclConsole createConsoleInstance(IScriptInterpreter interpreter, String id) {
		if (interpreter == null) {
			try {
				id = "default";
				interpreter = new TclInterpreter();
				
				if( ScriptRuntime.getDefaultInterpreterInstall(TclNature.NATURE_ID) == null ) {
					showQuestion();
					if( ScriptRuntime.getDefaultInterpreterInstall(TclNature.NATURE_ID) == null ) {
						return null;
					}
				}
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
