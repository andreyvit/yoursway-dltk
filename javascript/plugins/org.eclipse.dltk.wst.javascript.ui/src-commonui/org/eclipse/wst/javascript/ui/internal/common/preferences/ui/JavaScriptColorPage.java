/*******************************************************************************
 * Copyright (c) 2004,2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.common.preferences.ui;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.javascript.ui.internal.common.IHelpContextIds;
import org.eclipse.wst.javascript.ui.internal.common.JSCommonUIMessages;
import org.eclipse.wst.javascript.ui.internal.common.preferences.OverlayPreferenceStore;
import org.eclipse.wst.javascript.ui.internal.common.preferences.OverlayPreferenceStore.OverlayKey;
import org.eclipse.wst.javascript.ui.internal.common.style.IStyleConstantsJavaScript;
import org.eclipse.wst.javascript.ui.internal.editor.JSEditorPlugin;
import org.eclipse.wst.sse.core.internal.ltk.parser.RegionParser;
import org.eclipse.wst.sse.ui.internal.preferences.ui.AbstractColorPage;
import org.eclipse.wst.sse.ui.internal.preferences.ui.StyledTextColorPicker;

/**
 * @deprecated
 */
public class JavaScriptColorPage extends AbstractColorPage {
	private OverlayPreferenceStore fOverlayStore;
	protected RegionParser fParser = new TokenParser();

	/**
	 * Set up all the style preference keys in the overlay store
	 */
	protected OverlayKey[] createOverlayKeys() {
		ArrayList overlayKeys = new ArrayList();
		
		ArrayList styleList = new ArrayList();
		initStyleList(styleList);
		Iterator i = styleList.iterator();
		while (i.hasNext()) {
			overlayKeys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING, (String)i.next()));	
		}

		OverlayPreferenceStore.OverlayKey[] keys = new OverlayPreferenceStore.OverlayKey[overlayKeys.size()];
		overlayKeys.toArray(keys);
		return keys;
	}

	public String getSampleText() {
		return JSCommonUIMessages.sample_javascript_UI_; //$NON-NLS-1$ = "function init() {\n\tfVersions = new Array();\n\tfVersions[1] = 'Version 1';\n\tfVersions[2] = 'Version 2';\n\t// For which version is the user requesting support?\n}\nfunction selectVersion(f) {\n\tvar cnt = 0;\n\tvar i;\n\tfor (i = 0 ; i < f.version.length ; i++) {\n\t\tif (f.version[i].selected) {\n\t\t\tcnt++;\n\t\t}\n\t}\n\tvar doall = (cnt == f.product.length || cnt == 0);\n\tString unfinishedString = \"resource;\n}"
	}

	protected void initContextSytleMap(Dictionary contextStyleMap) {

		contextStyleMap.put(IStyleConstantsJavaScript.DEFAULT, IStyleConstantsJavaScript.DEFAULT);
		contextStyleMap.put(IStyleConstantsJavaScript.KEYWORD, IStyleConstantsJavaScript.KEYWORD);
		contextStyleMap.put(IStyleConstantsJavaScript.LITERAL, IStyleConstantsJavaScript.LITERAL);
		contextStyleMap.put(IStyleConstantsJavaScript.COMMENT, IStyleConstantsJavaScript.COMMENT);
		contextStyleMap.put(IStyleConstantsJavaScript.UNFINISHED_COMMENT, IStyleConstantsJavaScript.UNFINISHED_COMMENT);
	}

	protected void initDescriptions(Dictionary descriptions) {

		descriptions.put(IStyleConstantsJavaScript.DEFAULT, JSCommonUIMessages.Default_Code_UI_); //$NON-NLS-1$ = "Default Code"
		descriptions.put(IStyleConstantsJavaScript.KEYWORD, JSCommonUIMessages.Keywords_UI_); //$NON-NLS-1$ = "Keywords"
		descriptions.put(IStyleConstantsJavaScript.LITERAL, JSCommonUIMessages.Literal_Strings_UI_); //$NON-NLS-1$ = "Literal Strings"
		descriptions.put(IStyleConstantsJavaScript.COMMENT, JSCommonUIMessages.Comments_UI_); //$NON-NLS-1$ = "Comments"
		descriptions.put(IStyleConstantsJavaScript.UNFINISHED_COMMENT, JSCommonUIMessages.Unfinished_Strings_and_Comments_UI_); //$NON-NLS-1$ = "Unfinished Strings and Comments"

	}

	/**
	 * Initializes this preference page for the given workbench.
	 * <p>
	 * This method is called automatically as the preference page is being
	 * created and initialized. Clients must not call this method.
	 * </p>
	 * 
	 * @param workbench
	 *            the workbench
	 */
	public void init(IWorkbench workbench) {
		fOverlayStore = new OverlayPreferenceStore(getPreferenceStore(), createOverlayKeys());
		fOverlayStore.load();
		fOverlayStore.start();
	}

	protected void initStyleList(ArrayList list) {
		list.add(IStyleConstantsJavaScript.DEFAULT);
		list.add(IStyleConstantsJavaScript.KEYWORD);
		list.add(IStyleConstantsJavaScript.LITERAL);
		list.add(IStyleConstantsJavaScript.COMMENT);
		list.add(IStyleConstantsJavaScript.UNFINISHED_COMMENT);
	}

	protected void setupPicker(StyledTextColorPicker picker) {

		picker.setParser(fParser);

		// create descriptions for hilighting types
		Dictionary descriptions = new Hashtable();
		initDescriptions(descriptions);

		// map region types to hilighting types
		Dictionary contextStyleMap = new Hashtable();
		initContextSytleMap(contextStyleMap);

		ArrayList styleList = new ArrayList();
		initStyleList(styleList);

		picker.setContextStyleMap(contextStyleMap);
		picker.setDescriptions(descriptions);
		picker.setStyleList(styleList);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		Control c = super.createContents(parent);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(c, IHelpContextIds.JS_PREFWEBX_STYLES_HELPID);
		return c;
	}
	
	protected void performDefaults() {
		fOverlayStore.loadDefaults();
		fPicker.refresh();
	}

	protected IPreferenceStore doGetPreferenceStore() {
		return JSEditorPlugin.getDefault().getPreferenceStore();
	}
	/**
	 * Creates the StyledTextColorPicker used in createContents This method
	 * can be overwritten to set up StyledTextColorPicker differently
	 */
	protected void createContentsForPicker(Composite parent) {
		// create the color picker
		fPicker = new StyledTextColorPicker(parent, SWT.NULL);
		GridData data = new GridData(GridData.FILL_BOTH);
		fPicker.setLayoutData(data);

		fPicker.setPreferenceStore(fOverlayStore);
		setupPicker(fPicker);

		fPicker.setText(getSampleText());
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.IPreferencePage#performOk()
	 */
	public boolean performOk() {
		fOverlayStore.propagate();
		 savePreferences();
		return true;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.wst.sse.ui.preferences.ui.AbstractColorPage#savePreferences()
	 */
	protected void savePreferences() {
		JSEditorPlugin.getDefault().savePluginPreferences();
	}
}
