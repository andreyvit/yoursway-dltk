package org.eclipse.dltk.debug.ui.handlers;

import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;

/**
 * Abstract handler implementation that can be used to toggle the display of
 * debugging variables.
 */
abstract class AbstractToggleVariableHandler extends AbstractHandler
		implements IElementUpdater {

	/*
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public final Object execute(ExecutionEvent event) throws ExecutionException {
		toggleVariableDisplay();
		updateDebugTargets();
		
		// refresh the ui elements
		IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);
		ICommandService service = (ICommandService) window
				.getService(ICommandService.class);
		service.refreshElements(event.getCommand().getId(), null);

		return null;
	}

	/*
	 * @see org.eclipse.ui.commands.IElementUpdater#updateElement(org.eclipse.ui.menus.UIElement,
	 *      java.util.Map)
	 */
	public void updateElement(UIElement element, Map parameters) {
		element.setChecked(isVariableDisplayEnabled());
	}

	/**
	 * Returns the debug model id for the given script language
	 */
	protected abstract String getModelId();

	/**
	 * Returns the preference store to store the variable display settings
	 * under.
	 */
	protected abstract IPreferenceStore getPreferenceStore();

	/**
	 * Returns the preference key used to store the variable display setting.
	 */
	protected abstract String getVariableDisplayPreferenceKey();

	/**
	 * Toggle the variable display value the subclass is responsible for
	 * handling.
	 * 
	 * @param target
	 *            script debug target
	 * @param enabled
	 *            <code>true</code> if the variable should be displayed,
	 *            <code>false</code> otherwise.
	 */
	protected abstract void toggleVariableDisplay(IScriptDebugTarget target,
			boolean enabled);

	private boolean isVariableDisplayEnabled() {
		return getPreferenceStore().getBoolean(
				getVariableDisplayPreferenceKey());
	}

	private boolean matchesModelId(IScriptDebugTarget target) {
		return getModelId().equals(target.getModelIdentifier());
	}
	
	private void toggleVariableDisplay() {
		IPreferenceStore store = getPreferenceStore();
		String key = getVariableDisplayPreferenceKey();

		boolean value = store.getBoolean(key);
		store.setValue(key, !value);
	}

	private void updateDebugTargets() {
		IDebugTarget[] targets = DebugPlugin.getDefault().getLaunchManager()
				.getDebugTargets();

		for (int i = 0; i < targets.length; i++) {
			if (!(targets[i] instanceof IScriptDebugTarget)) {
				continue;
			}

			IScriptDebugTarget target = (IScriptDebugTarget) targets[i];
			if (target.isTerminated() || !matchesModelId(target)) {
				continue;
			}

			toggleVariableDisplay(target, isVariableDisplayEnabled());
		}
	}
}
