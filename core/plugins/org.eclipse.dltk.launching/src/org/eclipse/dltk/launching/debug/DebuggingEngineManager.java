package org.eclipse.dltk.launching.debug;

import org.eclipse.core.runtime.IConfigurationElement;

import org.eclipse.dltk.core.DLTKContributionExtensionManager;
import org.eclipse.dltk.internal.launching.DLTKLaunchingPlugin;
import org.eclipse.dltk.internal.launching.debug.DebuggingEngine;
import org.eclipse.dltk.launching.IInterpreterRunnerFactory;

public class DebuggingEngineManager extends DLTKContributionExtensionManager {
	private static final String DEBUGGING_ENGINE_EXT_POINT = DLTKLaunchingPlugin.PLUGIN_ID
			+ ".debuggingEngine";

	private static final String ENGINE_TAG = "engine";

	private static DebuggingEngineManager instance;

	public static DebuggingEngineManager getInstance() {
		if (instance == null) {
			instance = new DebuggingEngineManager();
		}

		return instance;
	}

	public IDebuggingEngine getDebuggingEngine(String id) {
		return (IDebuggingEngine) getContributionById(id);
	}

	/**
	 * Returns selected debugging engine for script language with natureId. Uses
	 * default debugging engine selector (priority based) if custom selector is
	 * not contributed.
	 * 
	 * @param natureId
	 * 
	 * @return Selected debugging engine or null (if there are no debugging
	 *         engines at all or there are no selected engines)
	 */
	public IDebuggingEngine getSelectedDebuggingEngine(String natureId) {
		return (IDebuggingEngine) getSelectedContribution(natureId);
	}

	/**
	 * Returns if script language with nature natureId has selected debugging
	 * engine. If this method returns false then getSelectedDebuggingEngine
	 * returns null.
	 * 
	 * @param natureId
	 *            nature id
	 * 
	 * @return true if the nature has a selected debugging engine, false
	 *         otherwise
	 */
	public boolean hasSelectedDebuggingEngine(String natureId) {
		return getSelectedDebuggingEngine(natureId) != null;
	}

	/*
	 * @see org.eclipse.dltk.core.DLTKContributionExtensionManager#configureContribution(java.lang.Object,
	 *      org.eclipse.core.runtime.IConfigurationElement)
	 */
	protected Object configureContribution(Object object,
			IConfigurationElement config) {
		return new DebuggingEngine((IInterpreterRunnerFactory) object, config);
	}

	/*
	 * @see org.eclipse.dltk.core.DLTKContributionExtensionManager#getContributionElementName()
	 */
	protected String getContributionElementName() {
		return ENGINE_TAG;
	}

	/*
	 * @see org.eclipse.dltk.core.DLTKContributionExtensionManager#getExtensionPoint()
	 */
	protected String getExtensionPoint() {
		return DEBUGGING_ENGINE_EXT_POINT;
	}

	/*
	 * @see org.eclipse.dltk.core.DLTKContributionExtensionManager#isValidContribution(java.lang.Object)
	 */
	protected boolean isValidContribution(Object object) {
		return (object instanceof IInterpreterRunnerFactory);
	}
}
