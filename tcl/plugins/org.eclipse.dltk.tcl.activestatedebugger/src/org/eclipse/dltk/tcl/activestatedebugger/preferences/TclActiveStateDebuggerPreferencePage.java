/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.tcl.activestatedebugger.preferences;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.dltk.debug.ui.preferences.ExternalDebuggingEngineConfigurationBlock;
import org.eclipse.dltk.tcl.activestatedebugger.TclActiveStateDebuggerConstants;
import org.eclipse.dltk.tcl.activestatedebugger.TclActiveStateDebuggerPlugin;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;

public class TclActiveStateDebuggerPreferencePage extends
		AbstractConfigurationBlockPreferencePage {
	public static final String PAGE_ID = "org.eclipse.dltk.tcl.preferences.debug.activestatedebugger";

	private static class TclDebuggingEngineConfigurationBlock extends
			ExternalDebuggingEngineConfigurationBlock {

		protected void openExternalUrl(String url) {
			try {
				final IWebBrowser browser = PlatformUI.getWorkbench()
						.getBrowserSupport().getExternalBrowser();
				browser.openURL(new URL(url));
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public TclDebuggingEngineConfigurationBlock(
				OverlayPreferenceStore store, PreferencePage preferencePage) {
			super(store, preferencePage);
		}

		protected void createInfo(final Composite parent) {
			final Link link = new Link(parent, SWT.NONE);
			link.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					openExternalUrl(PreferenceMessages.DebuggingEngineDownloadPageLink);
				}
			});

			link.setText(PreferenceMessages.DebuggingEngineDownloadPage);
		}

		protected String getDebuggingEnginePathKey() {
			return TclActiveStateDebuggerConstants.DEBUGGING_ENGINE_PATH_KEY;
		}

		public Control createControl(Composite parent) {
			final Composite composite = SWTFactory.createComposite(parent,
					parent.getFont(), 1, 1, GridData.FILL);

			createEnginePath(composite);
			createInfo(composite);

			return composite;
		}
	}

	protected IPreferenceConfigurationBlock createConfigurationBlock(
			OverlayPreferenceStore store) {
		return new TclDebuggingEngineConfigurationBlock(store, this);
	}

	protected String getHelpId() {
		return null;
	}

	protected void setDescription() {
		setDescription(PreferenceMessages.PreferencesDescription);
	}

	protected void setPreferenceStore() {
		setPreferenceStore(TclActiveStateDebuggerPlugin.getDefault()
				.getPreferenceStore());
	}

	public boolean performOk() {
		super.performOk();
		TclActiveStateDebuggerPlugin.getDefault().savePluginPreferences();
		return true;
	}
}
