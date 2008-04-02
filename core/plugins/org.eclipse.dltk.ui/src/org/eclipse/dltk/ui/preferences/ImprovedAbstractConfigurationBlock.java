/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.ui.preferences;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.internal.ui.dialogs.StatusInfo;
import org.eclipse.dltk.internal.ui.dialogs.StatusUtil;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore.OverlayKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * Configures preferences.
 */
public abstract class ImprovedAbstractConfigurationBlock implements
		IPreferenceConfigurationBlock, IPreferenceDelegate {

	private PreferencePage page;
	private OverlayPreferenceStore store;

	private ControlBindingManager bindManager;

	public ImprovedAbstractConfigurationBlock(OverlayPreferenceStore store,
			final PreferencePage page) {
		this.page = page;
		this.store = store;

		bindManager = new ControlBindingManager(this, getStatusListener());
		addOverlayKeys();
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock#initialize()
	 */
	public void initialize() {
		initializeFields();
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock#performOk()
	 */
	public void performOk() {
		// do nothing
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock#performDefaults()
	 */
	public void performDefaults() {
		initializeFields();
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock#dispose()
	 */
	public void dispose() {
		// do nothing
	}

	public boolean getBoolean(Object key) {
		return store.getBoolean((String) key);
	}

	public String getString(Object key) {
		return store.getString((String) key);
	}

	public void setBoolean(Object key, boolean value) {
		store.setValue((String) key, value);
	}

	public void setString(Object key, String value) {
		store.setValue((String) key, value);
	}

	/**
	 * Create the {@link OverlayPreferenceStore.OverlayKey} keys for the
	 * preference page.
	 * 
	 * <p>
	 * Subclasses may return <code>null</code> in then event they are not
	 * storing any preference values.
	 * </p>
	 */
	protected abstract List createOverlayKeys();

	// Binding
	protected void bindControl(final Button button, final String key,
			final Control[] dependencies) {
		bindManager.bindControl(button, key, dependencies);
	}

	protected void bindControl(final Button button, final String key) {
		bindControl(button, key, null);
	}

	protected void bindControl(final Text text, final String key,
			IFieldValidator validator) {
		bindManager.bindControl(text, key, validator);
	}

	protected void bindControl(final Text text, final String key) {
		bindControl(text, key, null);
	}

	protected void bindControl(final Text text, IFieldValidator validator) {
		bindManager.bindControl(text, null, validator);
	}

	protected void bindControl(final Combo combo, final String key) {
		bindManager.bindControl(combo, key);
	}

	protected void initializeFields() {
		bindManager.initialize();
	}

	protected IPreferenceStore getPreferenceStore() {
		return store;
	}

	private IStatusChangeListener getStatusListener() {
		return new IStatusChangeListener() {

			public void statusChanged(IStatus status) {
				if (status == null) {
					status = new StatusInfo();
				}

				page.setValid(status.getSeverity() != IStatus.ERROR);
				StatusUtil.applyToStatusLine(page, status);
			}
		};
	}

	private void addOverlayKeys() {
		List overlayKeys = createOverlayKeys();
		if (overlayKeys != null) {
			OverlayKey[] keys = (OverlayKey[]) overlayKeys
					.toArray(new OverlayKey[overlayKeys.size()]);
			store.addKeys(keys);
		}
	}
}
