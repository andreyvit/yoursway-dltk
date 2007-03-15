/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.common;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.wst.javascript.ui.internal.common.style.IStyleConstantsJavaScript;
import org.eclipse.wst.javascript.ui.internal.editor.JSEditorPlugin;
import org.eclipse.wst.sse.ui.internal.preferences.ui.ColorHelper;
import org.eclipse.wst.sse.ui.internal.util.EditorUtility;

public class JavaScriptColorPreferences {
	private class PropertyChangeListener implements IPropertyChangeListener {
		/* (non-Javadoc)
		 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent event) {
			// have to do it this way so others can override the method
			handlePropertyChange(event);
		}
	}
	
	// defect 215738 - default needs to be set to null to maintain current
	// system color
	// private static final Color defaultForeground =
	// Display.getCurrent().getSystemColor(SWT.COLOR_LIST_FOREGROUND);
	// private static final Color defaultBackground =
	// Display.getCurrent().getSystemColor(SWT.COLOR_LIST_BACKGROUND);
	private static final Color defaultForeground = null;
	private static final Color defaultBackground = null;

	public static TextAttribute taDefault; // = new TextAttribute(
										   // /*fg:*/colorBlack,
										   // /*bg:*/colorWhite, /*bold ?
										   // SWT.BOLD :*/ SWT.NORMAL );
	public static TextAttribute taKeyword; // = new TextAttribute(
										   // /*fg:*/colorGreen,
										   // /*bg:*/colorWhite, /*bold ?*/
										   // SWT.BOLD /*: SWT.NORMAL*/ );
	public static TextAttribute taComment; // = new TextAttribute(
										   // /*fg:*/colorWhite,
										   // /*bg:*/colorGreen, /*bold ?
										   // SWT.BOLD :*/ SWT.NORMAL );
	public static TextAttribute taStringLit; // = new TextAttribute(
											 // /*fg:*/colorPink,
											 // /*bg:*/colorWhite, /*bold ?
											 // SWT.BOLD :*/ SWT.NORMAL );
	public static TextAttribute taUnfComment; // = new TextAttribute(
											  // /*fg:*/colorWhite,
											  // /*bg:*/colorRed, /*bold ?
											  // SWT.BOLD :*/ SWT.NORMAL );

	private PropertyChangeListener fPreferenceListener = new PropertyChangeListener();
	private static final JavaScriptColorPreferences global = new JavaScriptColorPreferences();

	private JavaScriptColorPreferences() {
		IPreferenceStore pref = getColorPreferences();
		if (pref != null) {
			pref.addPropertyChangeListener(fPreferenceListener);
		}
		loadColors();
	}

	/**
	 * Looks up the colorKey in the preference store and creates a
	 * TextAttribute for it.
	 * 
	 * @param colorKey
	 */
	protected TextAttribute createTextAttribute(String colorKey) {
		TextAttribute ta = null;
		
		String prefString = getColorPreferences().getString(colorKey);
		String[] stylePrefs = ColorHelper.unpackStylePreferences(prefString);
		if (stylePrefs != null) {
			RGB foreground = ColorHelper.toRGB(stylePrefs[0]);
			RGB background = ColorHelper.toRGB(stylePrefs[1]);
			boolean bold = Boolean.valueOf(stylePrefs[2]).booleanValue();
			ta = createTextAttribute(foreground, background, bold);
		}
		
		return ta;
	}

	protected void loadColors() {
		clearColors();

		TextAttribute ta = createTextAttribute(IStyleConstantsJavaScript.DEFAULT);
		if (ta != null)
			taDefault = ta;

		ta = createTextAttribute(IStyleConstantsJavaScript.KEYWORD);
		if (ta != null)
			taKeyword = ta;

		ta = createTextAttribute(IStyleConstantsJavaScript.LITERAL);
		if (ta != null)
			taStringLit = ta;

		ta = createTextAttribute(IStyleConstantsJavaScript.COMMENT);
		if (ta != null)
			taComment = ta;
		
		ta = createTextAttribute(IStyleConstantsJavaScript.UNFINISHED_COMMENT);
		if (ta != null)
			taUnfComment = ta;
		
		// we could add code here to check if taOld is different than the new
		// value
		//   and if so invalidate what's currently on the screen.
	}

	protected static void clearColors() {
		taDefault = taKeyword = taStringLit = taComment = taUnfComment = createTextAttribute(null, null, false);
	}

	protected static TextAttribute createTextAttribute(RGB foreground, RGB background, boolean bold) {
		return new TextAttribute((foreground != null) ? EditorUtility.getColor(foreground) : defaultForeground, (background != null) ? EditorUtility.getColor(background) : defaultBackground, bold ? SWT.BOLD : SWT.NORMAL);
	}

	public static void addPropertyChangeListener(IPropertyChangeListener pcl) {
		// todo: we could add a "ColorPreferenceChangeListener" later if we
		// decide the cost of adding an additional interface is justified
		global.getColorPreferences().addPropertyChangeListener(pcl);
	}

	public static void removePropertyChangeListener(IPropertyChangeListener pcl) {
		global.getColorPreferences().removePropertyChangeListener(pcl);
	}

	protected IPreferenceStore getColorPreferences() {
		return JSEditorPlugin.getDefault().getPreferenceStore();
	}

	protected void handlePropertyChange(PropertyChangeEvent event) {
		if (event != null) {
			String prefKey = event.getProperty();
			// check if preference changed is a style preference
			if (IStyleConstantsJavaScript.DEFAULT.equals(prefKey)) {
				taDefault = createTextAttribute(IStyleConstantsJavaScript.DEFAULT);
			}
			else if (IStyleConstantsJavaScript.KEYWORD.equals(prefKey)) {
				taKeyword = createTextAttribute(IStyleConstantsJavaScript.KEYWORD);
			}
			else if (IStyleConstantsJavaScript.LITERAL.equals(prefKey)) {
				taStringLit = createTextAttribute(IStyleConstantsJavaScript.LITERAL);
			}
			else if (IStyleConstantsJavaScript.COMMENT.equals(prefKey)) {
				taComment = createTextAttribute(IStyleConstantsJavaScript.COMMENT);
			}
			else if (IStyleConstantsJavaScript.UNFINISHED_COMMENT.equals(prefKey)) {
				taUnfComment = createTextAttribute(IStyleConstantsJavaScript.UNFINISHED_COMMENT);
			}
		}
	}
	
	/**
	 * Returns true if the given key is a javascript style preference key, false otherwise
	 */
	public static boolean isJavaScriptColorPreference(String prefKey) {
		boolean isPreference = false;
		
		if ((IStyleConstantsJavaScript.DEFAULT.equals(prefKey))
					|| (IStyleConstantsJavaScript.KEYWORD.equals(prefKey))
					|| (IStyleConstantsJavaScript.LITERAL.equals(prefKey))
					|| (IStyleConstantsJavaScript.COMMENT.equals(prefKey))
					|| (IStyleConstantsJavaScript.UNFINISHED_COMMENT.equals(prefKey))) {
					isPreference = true;
		}
		return isPreference;
	}
}
