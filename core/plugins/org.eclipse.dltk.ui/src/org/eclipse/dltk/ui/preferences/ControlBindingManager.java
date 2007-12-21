package org.eclipse.dltk.ui.preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.internal.ui.dialogs.StatusInfo;
import org.eclipse.dltk.internal.ui.dialogs.StatusUtil;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class ControlBindingManager {

	private Map textControls;
	private Map comboControls;
	private Map buttonControls;
	
	private IPreferenceDelegate preferenceDelegate;
	private IStatusChangeListener changeListener;
	
	private DependencyManager dependencyManager;
	private ValidatorManager validatorManager;
	
	public ControlBindingManager(IPreferenceDelegate delegate, IStatusChangeListener listener) {
		this.buttonControls = new HashMap();
		this.comboControls = new HashMap();
		this.textControls = new HashMap();

		this.validatorManager = new ValidatorManager();
		this.dependencyManager = new DependencyManager();
		
		this.changeListener = listener;
		this.preferenceDelegate = delegate;
	}

	public void initialize() {
		Iterator it = textControls.keySet().iterator();
		while (it.hasNext()) {
			final Text text = (Text) it.next();
			final Object key = textControls.get(text);
			text.setText(preferenceDelegate.getString(key));
		}

		it = buttonControls.keySet().iterator();
		while (it.hasNext()) {
			final Button button = (Button) it.next();
			final Object key = buttonControls.get(button);
			button.setSelection(preferenceDelegate.getBoolean(key));
		}

		it = comboControls.keySet().iterator();
		while (it.hasNext()) {
			final Combo combo = (Combo) it.next();
			final Object key = comboControls.get(combo);
			String value = preferenceDelegate.getString(key);
			String[] items = combo.getItems();
			boolean selected = false;
			for (int i = 0; i < items.length; i++) {
				if( items[i].equals(value)) {
					combo.select(i);
					selected = true;
					break;
				}
			}
			if( !selected ) {
				combo.select(0);
			}
		}
		
		dependencyManager.initialize();
	}

	public void bindControl(final Button button, final Object key,
			Control[] slaves) {
		if (key != null) {
			buttonControls.put(button, key);
		}

		if (slaves != null) {
			dependencyManager.createDependency(button, slaves);
		}

		button.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}

			public void widgetSelected(SelectionEvent e) {
				boolean state = button.getSelection();
				preferenceDelegate.setBoolean(key, state);

				updateStatus(new StatusInfo());
			}
		});
	}

	public void bindControl(final Text text, final Object key,
			IFieldValidator validator) {
		if (key != null) {
			textControls.put(text, key);
		}

		if (validator != null) {
			validatorManager.registerValidator(text, validator);
		}

		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				final String value = text.getText();

				IStatus status = validateText(text);

				if (key != null) {
					if (status.getSeverity() != IStatus.ERROR) {
						preferenceDelegate.setString(key, value);
					}
				}

				updateStatus(status);
			}
		});
	}

	public void bindControl(final Combo combo, final String key) {
		if (key != null) {
			comboControls.put(combo, key);
		}
		combo.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}

			public void widgetSelected(SelectionEvent e) {
				int index = combo.getSelectionIndex();
				preferenceDelegate.setString(key, combo.getItem(index));
				
				changeListener.statusChanged(new StatusInfo());
			}
		});
	}
	
	private IStatus validateText(Text text) {
		IStatus status = new StatusInfo();

		IFieldValidator validator = validatorManager.getValidator(text);

		if ((validator != null) && text.isEnabled()) {
			status = validator.validate(text.getText());
		}

		return status;
	}

	protected void updateStatus(IStatus status) {
		if (!status.matches(IStatus.ERROR)) {
			Iterator iter = textControls.keySet().iterator();
			while (iter.hasNext()) {
				IStatus s = validateText((Text) iter.next());
				status = StatusUtil.getMoreSevere(s, status);
			}
		}

		changeListener.statusChanged(status);
	}

	class DependencyManager {
		private List masterSlaveListeners = new ArrayList();

		public void initialize() {
			Iterator it = masterSlaveListeners.iterator();
			while (it.hasNext()) {
				((SelectionListener) it.next()).widgetSelected(null);
			}
		}

		public void createDependency(final Button master, final Control[] slaves) {
			SelectionListener listener = new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					boolean state = master.getSelection();
					for (int i = 0; i < slaves.length; i++) {
						slaves[i].setEnabled(state);

					}

					changeListener.statusChanged(new StatusInfo());
				}

				public void widgetDefaultSelected(SelectionEvent e) {
					// do nothing
				}
			};

			master.addSelectionListener(listener);
			masterSlaveListeners.add(listener);
		}
	}

	class ValidatorManager {
		
		private Map map = new HashMap();

		public IFieldValidator getValidator(Control control) {
			return (IFieldValidator) map.get(control);
		}

		public void registerValidator(Text text, IFieldValidator validator) {
			map.put(text, validator);
		}

		public void unregisterValidator(Text text) {
			map.remove(text);
		}

	}

}
