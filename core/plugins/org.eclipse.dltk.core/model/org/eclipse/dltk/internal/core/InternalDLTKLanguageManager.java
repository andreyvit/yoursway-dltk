/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.PriorityClassDLTKExtensionManager;

public class InternalDLTKLanguageManager {
	private final static String LANGUAGE_EXTPOINT = DLTKCore.PLUGIN_ID
			+ ".language";
	private final static String SOURCE_ELEMENT_PARSERS_EXTPOINT = DLTKCore.PLUGIN_ID
			+ ".sourceElementParsers";
	private final static String SOURCE_PARSERS_EXTPOINT = DLTKCore.PLUGIN_ID
			+ ".sourceParsers";
	private final static String PROBLEM_FACTORY_EXTPOINT = DLTKCore.PLUGIN_ID
			+ ".problemFactory";
	private final static String COMPLETION_ENGINE_EXTPOINT = DLTKCore.PLUGIN_ID
			+ ".completionEngine";
	private final static String SELECTION_ENGINE_EXTPOINT = DLTKCore.PLUGIN_ID
			+ ".selectionEngine";
	private final static String SEARCH_EXTPOINT = DLTKCore.PLUGIN_ID
			+ ".search";
	private final static String CALLHIERARCHY_EXTPOINT = DLTKCore.PLUGIN_ID
			+ ".callHierarchy";
	
	private static PriorityClassDLTKExtensionManager languageToolkitsManager = new PriorityClassDLTKExtensionManager(
			LANGUAGE_EXTPOINT);

	// Inner managers
	private static PriorityClassDLTKExtensionManager sourceElementParsersManager = new NewInstanceClassBasedDLTKExtensionManager(
			SOURCE_ELEMENT_PARSERS_EXTPOINT);
	private static PriorityClassDLTKExtensionManager problemFactoryManager = new PriorityClassDLTKExtensionManager(
			PROBLEM_FACTORY_EXTPOINT);

	private static PriorityClassDLTKExtensionManager selectionEngineManager = new NewInstanceClassBasedDLTKExtensionManager(
			SELECTION_ENGINE_EXTPOINT);
	private static PriorityClassDLTKExtensionManager completionEngineManager = new NewInstanceClassBasedDLTKExtensionManager(
			COMPLETION_ENGINE_EXTPOINT);
	private static PriorityClassDLTKExtensionManager sourceParsersManager = new NewInstanceClassBasedDLTKExtensionManager(
			SOURCE_PARSERS_EXTPOINT);

	private static PriorityClassDLTKExtensionManager searchManager = new PriorityClassDLTKExtensionManager(
			SEARCH_EXTPOINT);
	private static PriorityClassDLTKExtensionManager callHierarchyManager = new PriorityClassDLTKExtensionManager(
			CALLHIERARCHY_EXTPOINT);
	public static PriorityClassDLTKExtensionManager getSourceElementParsersManager() {
		return sourceElementParsersManager;
	}
	public static PriorityClassDLTKExtensionManager getProblemFactoryManager() {
		return problemFactoryManager;
	}
	public static PriorityClassDLTKExtensionManager getSelectionEngineManager() {
		return selectionEngineManager;
	}
	public static PriorityClassDLTKExtensionManager getCompletionEngineManager() {
		return completionEngineManager;
	}
	public static PriorityClassDLTKExtensionManager getSourceParsersManager() {
		return sourceParsersManager;
	}
	public static PriorityClassDLTKExtensionManager getSearchManager() {
		return searchManager;
	}
	public static PriorityClassDLTKExtensionManager getCallHierarchyManager() {
		return callHierarchyManager;
	}
	public static PriorityClassDLTKExtensionManager getLanguageToolkitsManager() {
		return languageToolkitsManager;
	}
	/**
	 * For testing purpose only. Could be used to test language without extensions.
	 * If level is -1 then bigger value will be used.
	 */
	public static void setPrefferedPriority(String id, int level) {
		languageToolkitsManager.setPreffetedLevel(id, level);
		sourceElementParsersManager.setPreffetedLevel(id, level);
		problemFactoryManager.setPreffetedLevel(id, level);
		selectionEngineManager.setPreffetedLevel(id, level);
		completionEngineManager.setPreffetedLevel(id, level);
		sourceParsersManager.setPreffetedLevel(id, level);
		searchManager.setPreffetedLevel(id, level);
		callHierarchyManager.setPreffetedLevel(id, level);
	}
}
