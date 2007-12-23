package org.eclipse.dltk.ui.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.internal.ui.preferences.OptionsConfigurationBlock;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public abstract class AbstractOptionsBlock extends OptionsConfigurationBlock
		implements IPreferenceDelegate {

	private ControlBindingManager bindManager;

	public AbstractOptionsBlock(IStatusChangeListener context,
			IProject project, PreferenceKey[] allKeys,
			IWorkbenchPreferenceContainer container) {
		super(context, project, allKeys, container);

		this.bindManager = new ControlBindingManager(this, context);
	}

	public Control createContents(Composite parent) {
		setShell(parent.getShell());
		Control control = createOptionsBlock(parent);
		initialize();

		return control;
	}

	protected void initialize() {
		bindManager.initialize();
	}
	
	protected abstract Control createOptionsBlock(Composite parent);

	protected final void bindControl(Button button, PreferenceKey key,
			Control[] dependencies) {
		bindManager.bindControl(button, key, dependencies);
	}

	protected final void bindControl(Text textBox, PreferenceKey key,
			IFieldValidator validator) {
		bindManager.bindControl(textBox, key, validator);
	}

	protected String[] getFullBuildDialogStrings(boolean workspaceSettings) {
		// TODO Auto-generated method stub
		return null;
	}

	protected final boolean isProjectPreferencePage() {
		return fProject != null;
	}

	/*
	 * @see org.eclipse.dltk.internal.ui.preferences.OptionsConfigurationBlock#performDefaults()
	 */
	public void performDefaults() {
		super.performDefaults();
		bindManager.initialize();
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.IPreferenceDelegate#getBoolean(java.lang.Object)
	 */
	public final boolean getBoolean(Object key) {
		return getBooleanValue((PreferenceKey) key);
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.IPreferenceDelegate#getString(java.lang.Object)
	 */
	public final String getString(Object key) {
		return getValue((PreferenceKey) key);
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.IPreferenceDelegate#setBoolean(java.lang.Object, boolean)
	 */
	public final void setBoolean(Object key, boolean value) {
		super.setValue((PreferenceKey) key, value);
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.IPreferenceDelegate#setString(java.lang.Object, java.lang.String)
	 */
	public final void setString(Object key, String value) {
		setValue((PreferenceKey) key, value);
	}
	
	protected final IProject getProject() {
		return fProject;
	}
	
	protected final void updateStatus(IStatus status) {
		bindManager.updateStatus(status);
	}
}
