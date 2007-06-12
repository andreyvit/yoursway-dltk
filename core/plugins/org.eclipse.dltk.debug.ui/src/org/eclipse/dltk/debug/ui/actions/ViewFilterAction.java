/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.actions;

import org.eclipse.debug.ui.IDebugView;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IActionDelegate2;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public abstract class ViewFilterAction extends ViewerFilter implements
		IViewActionDelegate, IActionDelegate2 {

	private IViewPart fView;
	private IAction fAction;
	private IPropertyChangeListener fListener = new Updater();

	class Updater implements IPropertyChangeListener {

		public void propertyChange(PropertyChangeEvent event) {
			if (event.getProperty().equals(getPreferenceKey())
					|| event.getProperty().equals(getCompositeKey())) {
				fAction.setChecked(getPreferenceValue());
			}
		}
	}

	public ViewFilterAction() {
		super();
	}

	public void init(IViewPart view) {
		fView = view;
		fAction.setChecked(getPreferenceValue());
		run(fAction);
		getPreferenceStore().addPropertyChangeListener(fListener);
	}

	public void init(IAction action) {
		fAction = action;
	}

	public void dispose() {
		getPreferenceStore().removePropertyChangeListener(fListener);
	}

	public void runWithEvent(IAction action, Event event) {
		run(action);
	}

	public void run(IAction action) {
		StructuredViewer viewer = getStructuredViewer();
		ViewerFilter[] filters = viewer.getFilters();
		ViewerFilter filter = null;
		for (int i = 0; i < filters.length; i++) {
			if (filters[i] == this) {
				filter = filters[i];
				break;
			}
		}
		if (filter == null) {
			viewer.addFilter(this);
			viewer.refresh(false);

			viewer.setInput(viewer.getInput());

		} else {
			// only refresh is removing - adding will refresh automatically
			viewer.removeFilter(this);
			viewer.refresh(false);
			viewer.setInput(viewer.getInput());
		}
		IPreferenceStore store = getPreferenceStore();
		String key = getView().getSite().getId() + "." + getPreferenceKey(); //$NON-NLS-1$
		store.setValue(key, action.isChecked());
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

	protected IPreferenceStore getPreferenceStore() {
		return DLTKDebugUIPlugin.getDefault().getPreferenceStore();
	}

	/**
	 * Returns the value of this filters preference (on/off) for the given view.
	 * 
	 * @param part
	 * @return boolean
	 */
	protected boolean getPreferenceValue() {
		String key = getCompositeKey();
		IPreferenceStore store = getPreferenceStore();
		boolean value = false;
		if (store.contains(key)) {
			value = store.getBoolean(key);
		} else {
			value = store.getBoolean(getPreferenceKey());
		}
		return value;
	}

	/**
	 * Returns the key for this action's preference
	 * 
	 * @return String
	 */
	protected abstract String getPreferenceKey();

	/**
	 * Returns the key used by this action to store its preference
	 * value/setting. Based on a base key (suffix) and part id (prefix).
	 * 
	 * @return preference store key
	 */
	protected String getCompositeKey() {
		String baseKey = getPreferenceKey();
		String viewKey = getView().getSite().getId();
		return viewKey + "." + baseKey; //$NON-NLS-1$
	}

	protected IViewPart getView() {
		return fView;
	}

	protected StructuredViewer getStructuredViewer() {
		IDebugView view = (IDebugView) getView().getAdapter(IDebugView.class);
		if (view != null) {
			Viewer viewer = view.getViewer();
			if (viewer instanceof StructuredViewer) {
				return (StructuredViewer) viewer;
			}
		}
		return null;
	}

	/**
	 * Returns whether this action is selected/checked.
	 * 
	 * @return whether this action is selected/checked
	 */
	protected boolean getValue() {
		return fAction.isChecked();
	}
}
