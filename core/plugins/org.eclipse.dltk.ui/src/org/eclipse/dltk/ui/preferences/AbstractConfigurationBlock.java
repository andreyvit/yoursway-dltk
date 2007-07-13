/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.ui.preferences;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.internal.ui.dialogs.StatusInfo;
import org.eclipse.dltk.internal.ui.dialogs.StatusUtil;
import org.eclipse.dltk.internal.ui.preferences.ScrolledPageContent;
import org.eclipse.dltk.ui.util.PixelConverter;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

/**
 * Configures preferences.
 * 
 */
public abstract class AbstractConfigurationBlock implements
		IPreferenceConfigurationBlock {

	protected static class FilePathValidator implements IInputValidator {
		public String isValid(String newText) {
			IPath path = Path.fromOSString(newText);
			File file = path.toFile();

			String error = null;
			if ("".equals(newText)) {
				error = "Empty path"; //ScriptDebugPreferencesMessages.EmptyPath;
			} else if (!file.exists()) {
				error = "File not exists"; //ScriptDebugPreferencesMessages.FileDoesNotExist;
			} else if (!file.isFile()) {
				error = "Not a file"; //  ScriptDebugPreferencesMessages.InvalidFileType;
			}
			return error;
		}
	}

	/**
	 * Use as follows:
	 * 
	 * <pre>
	 *  SectionManager manager= new SectionManager();
	 *  Composite composite= manager.createSectionComposite(parent);
	 *  
	 *  Composite xSection= manager.createSection(&quot;section X&quot;));
	 *  xSection.setLayout(new FillLayout());
	 *  new Button(xSection, SWT.PUSH); // add controls to section..
	 *  
	 *  [...]
	 *  
	 *  return composite; // return main composite
	 * </pre>
	 */
	protected final class SectionManager {
		/** The preference setting for keeping no section open. */
		private static final String __NONE = "__none"; //$NON-NLS-1$

		private Set fSections = new HashSet();

		private boolean fIsBeingManaged = false;

		private ExpansionAdapter fListener = new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				ExpandableComposite source = (ExpandableComposite) e
						.getSource();
				updateSectionStyle(source);
				if (fIsBeingManaged)
					return;
				if (e.getState()) {
					try {
						fIsBeingManaged = true;
						for (Iterator iter = fSections.iterator(); iter
								.hasNext();) {
							ExpandableComposite composite = (ExpandableComposite) iter
									.next();
							if (composite != source)
								composite.setExpanded(false);
						}
					} finally {
						fIsBeingManaged = false;
					}
					if (fLastOpenKey != null && fDialogSettingsStore != null)
						fDialogSettingsStore.setValue(fLastOpenKey, source
								.getText());
				} else {
					if (!fIsBeingManaged && fLastOpenKey != null
							&& fDialogSettingsStore != null)
						fDialogSettingsStore.setValue(fLastOpenKey, __NONE);
				}
				ExpandableComposite exComp = getParentExpandableComposite(source);
				if (exComp != null)
					exComp.layout(true, true);
				ScrolledPageContent parentScrolledComposite = getParentScrolledComposite(source);
				if (parentScrolledComposite != null) {
					parentScrolledComposite.reflow(true);
				}
			}
		};

		private Composite fBody;

		private final String fLastOpenKey;

		private final IPreferenceStore fDialogSettingsStore;

		private ExpandableComposite fFirstChild = null;

		/**
		 * Creates a new section manager.
		 */
		public SectionManager() {
			this(null, null);
		}

		/**
		 * Creates a new section manager.
		 */
		public SectionManager(IPreferenceStore dialogSettingsStore,
				String lastOpenKey) {
			fDialogSettingsStore = dialogSettingsStore;
			fLastOpenKey = lastOpenKey;
		}

		private void manage(ExpandableComposite section) {
			if (section == null)
				throw new NullPointerException();
			if (fSections.add(section))
				section.addExpansionListener(fListener);
			makeScrollableCompositeAware(section);
		}

		/**
		 * Creates a new composite that can contain a set of expandable
		 * sections. A <code>ScrolledPageComposite</code> is created and a new
		 * composite within that, to ensure that expanding the sections will
		 * always have enough space, unless there already is a
		 * <code>ScrolledComposite</code> along the parent chain of
		 * <code>parent</code>, in which case a normal <code>Composite</code>
		 * is created.
		 * <p>
		 * The receiver keeps a reference to the inner body composite, so that
		 * new sections can be added via <code>createSection</code>.
		 * </p>
		 * 
		 * @param parent
		 *            the parent composite
		 * @return the newly created composite
		 */
		public Composite createSectionComposite(Composite parent) {
			Assert.isTrue(fBody == null);
			boolean isNested = isNestedInScrolledComposite(parent);
			Composite composite;
			if (isNested) {
				composite = new Composite(parent, SWT.NONE);
				fBody = composite;
			} else {
				composite = new ScrolledPageContent(parent);
				fBody = ((ScrolledPageContent) composite).getBody();
			}

			fBody.setLayout(new GridLayout());

			return composite;
		}

		/**
		 * Creates an expandable section within the parent created previously by
		 * calling <code>createSectionComposite</code>. Controls can be added
		 * directly to the returned composite, which has no layout initially.
		 * 
		 * @param label
		 *            the display name of the section
		 * @return a composite within the expandable section
		 */
		public Composite createSection(String label) {
			Assert.isNotNull(fBody);
			final ExpandableComposite excomposite = new ExpandableComposite(
					fBody, SWT.NONE, ExpandableComposite.TWISTIE
							| ExpandableComposite.CLIENT_INDENT
							| ExpandableComposite.COMPACT);
			if (fFirstChild == null)
				fFirstChild = excomposite;
			excomposite.setText(label);
			String last = null;
			if (fLastOpenKey != null && fDialogSettingsStore != null)
				last = fDialogSettingsStore.getString(fLastOpenKey);

			if (fFirstChild == excomposite && !__NONE.equals(last)
					|| label.equals(last)) {
				excomposite.setExpanded(true);
				if (fFirstChild != excomposite)
					fFirstChild.setExpanded(false);
			} else {
				excomposite.setExpanded(false);
			}
			excomposite.setLayoutData(new GridData(GridData.FILL,
					GridData.BEGINNING, true, false));

			updateSectionStyle(excomposite);
			manage(excomposite);

			Composite contents = new Composite(excomposite, SWT.NONE);
			excomposite.setClient(contents);

			return contents;
		}
	}

	protected static final int INDENT = 20;

	private OverlayPreferenceStore fStore;

	private Map fCheckBoxes = new HashMap();

	private ArrayList fRadioButtons = new ArrayList();

	private Map fComboBoxes = new HashMap();

	private SelectionListener fCheckBoxListener = new SelectionListener() {
		public void widgetDefaultSelected(SelectionEvent e) {
		}

		public void widgetSelected(SelectionEvent e) {
			Button button = (Button) e.widget;
			fStore.setValue((String) fCheckBoxes.get(button), button
					.getSelection());
		}
	};

	private SelectionListener fComboBoxListener = new SelectionListener() {
		public void widgetDefaultSelected(SelectionEvent e) {
		}

		public void widgetSelected(SelectionEvent e) {
			Combo combo = (Combo) e.widget;
			Map data = (Map) fComboBoxes.get(combo);
			String key = (String) combo.getData();
			String value = (String) data.get(combo.getText());
			fStore.setValue(key, value);
		}
	};

	private SelectionListener fRadioButtonListener = new SelectionListener() {
		public void widgetDefaultSelected(SelectionEvent e) {
		}

		public void widgetSelected(SelectionEvent e) {
			// Button button= (Button) e.widget;
			for (int i = 0; i < fRadioButtons.size(); i++) {
				Button button = (Button) fRadioButtons.get(i);
				if (button.getSelection()) {
					String[] info = (String[]) button.getData();
					fStore.setValue(info[0], Integer.parseInt(info[1]));
				}
			}
		}
	};

	private Map fTextFields = new HashMap();

	private ModifyListener fTextFieldListener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			Text text = (Text) e.widget;
			fStore.setValue((String) fTextFields.get(text), text.getText());
		}
	};

	private ArrayList fNumberFields = new ArrayList();

	private ModifyListener fNumberFieldListener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			numberFieldChanged((Text) e.widget);
		}
	};

	/**
	 * List of master/slave listeners when there's a dependency.
	 * 
	 * @see #createDependency(Button, Control)
	 * 
	 */
	private ArrayList fMasterSlaveListeners = new ArrayList();

	private org.eclipse.dltk.internal.ui.dialogs.StatusInfo fStatus;

	private final PreferencePage fMainPage;

	public AbstractConfigurationBlock(OverlayPreferenceStore store) {
		Assert.isNotNull(store);
		fStore = store;
		fMainPage = null;
	}

	public AbstractConfigurationBlock(OverlayPreferenceStore store,
			PreferencePage mainPreferencePage) {
		Assert.isNotNull(store);
		Assert.isNotNull(mainPreferencePage);
		fStore = store;
		fMainPage = mainPreferencePage;
	}

	protected final ScrolledPageContent getParentScrolledComposite(
			Control control) {
		Control parent = control.getParent();
		while (!(parent instanceof ScrolledPageContent) && parent != null) {
			parent = parent.getParent();
		}
		if (parent instanceof ScrolledPageContent) {
			return (ScrolledPageContent) parent;
		}
		return null;
	}

	private final ExpandableComposite getParentExpandableComposite(
			Control control) {
		Control parent = control.getParent();
		while (!(parent instanceof ExpandableComposite) && parent != null) {
			parent = parent.getParent();
		}
		if (parent instanceof ExpandableComposite) {
			return (ExpandableComposite) parent;
		}
		return null;
	}

	protected void updateSectionStyle(ExpandableComposite excomposite) {
		excomposite.setFont(JFaceResources.getFontRegistry().getBold(
				JFaceResources.DIALOG_FONT));
	}

	private void makeScrollableCompositeAware(Control control) {
		ScrolledPageContent parentScrolledComposite = getParentScrolledComposite(control);
		if (parentScrolledComposite != null) {
			parentScrolledComposite.adaptChild(control);
		}
	}

	private boolean isNestedInScrolledComposite(Composite parent) {
		return getParentScrolledComposite(parent) != null;
	}

	protected Composite createComposite(Composite parent, Font font,
			int columns, int hspan, int fill, int marginwidth, int marginheight) {
		return SWTFactory.createComposite(parent, font, columns, hspan, fill,
				marginwidth, marginheight);
	}

	protected Group createGroup(Composite parent, String text, int columns,
			int hspan, int fill) {
		return SWTFactory.createGroup(parent, text, columns, hspan, fill);
	}

	protected Label createLabel(Composite parent, String text, int hspan) {
		return SWTFactory.createLabel(parent, text, hspan);
	}

	protected Button addCheckBox(Composite parent, String label, String key,
			int indentation) {
		Button checkBox = new Button(parent, SWT.CHECK);
		checkBox.setText(label);

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalIndent = indentation;
		gd.horizontalSpan = 2;
		checkBox.setLayoutData(gd);
		checkBox.addSelectionListener(fCheckBoxListener);
		makeScrollableCompositeAware(checkBox);

		fCheckBoxes.put(checkBox, key);

		return checkBox;
	}

	protected Combo addComboBox(Composite parent, String label, String key,
			String[] items, String[] values) {
		if (values == null || items == null || label == null
				|| items.length != values.length)
			throw new IllegalArgumentException(
					"values == null || items == null || label == null");

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);

		Label labelControl = new Label(parent, SWT.NONE);
		labelControl.setText(label);
		labelControl.setLayoutData(gd);

		Combo combo = new Combo(parent, SWT.SINGLE | SWT.READ_ONLY);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		combo.setFont(parent.getFont());
		combo.setItems(items);
		combo.setLayoutData(gd);
		combo.setData(key);
		combo.addSelectionListener(fComboBoxListener);
		makeScrollableCompositeAware(combo);

		Map data = new HashMap();
		for (int i = 0; i < items.length; i++) {
			data.put(items[i], values[i]);
		}

		fComboBoxes.put(combo, data);

		return combo;
	}

	protected Button addRadioButton(Composite parent, String label, String key,
			int value) {
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);

		Button button = new Button(parent, SWT.RADIO);
		button.setText(label);
		button.setData(new String[] { key, String.valueOf(value) });
		button.addSelectionListener(fRadioButtonListener);
		button.setLayoutData(gd);

		button.setSelection(value == getPreferenceStore().getInt(key));

		fRadioButtons.add(button);

		return button;
	}

	/**
	 * Returns an array of size 2: - first element is of type <code>Label</code> -
	 * second element is of type <code>Text</code> Use
	 * <code>getLabelControl</code> and <code>getTextControl</code> to get
	 * the 2 controls.
	 * 
	 * @param composite
	 *            the parent composite
	 * @param label
	 *            the text field's label
	 * @param key
	 *            the preference key
	 * @param textLimit
	 *            the text limit
	 * @param indentation
	 *            the field's indentation
	 * @param isNumber
	 *            <code>true</code> iff this text field is used to edit a
	 *            number
	 * @return the controls added
	 */
	protected Control[] addLabelledTextField(Composite composite, String label,
			String key, int textLimit, int indentation, boolean isNumber,
			IInputValidator validator) {

		PixelConverter pixelConverter = new PixelConverter(composite);

		Label labelControl = new Label(composite, SWT.NONE);
		labelControl.setText(label);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalIndent = indentation;
		labelControl.setLayoutData(gd);

		Text textControl = new Text(composite, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.widthHint = pixelConverter
				.convertWidthInCharsToPixels(textLimit + 1);
		textControl.setLayoutData(gd);
		textControl.setTextLimit(textLimit);
		if (validator != null)
			textControl.setData(validator);
		fTextFields.put(textControl, key);
		if (isNumber) {
			fNumberFields.add(textControl);
			textControl.addModifyListener(fNumberFieldListener);
		} else {
			textControl.addModifyListener(fTextFieldListener);
		}

		return new Control[] { labelControl, textControl };
	}

	protected Control[] addLabelledTextField(Composite composite, String label,
			String key, int textLimit, int indentation, boolean isNumber) {
		return addLabelledTextField(composite, label, key, textLimit,
				indentation, isNumber, null);
	}

	protected void createDependency(final Button master, final Control slave) {
		createDependency(master, new Control[] { slave });
	}

	protected void createDependency(final Button master, final Control[] slaves) {
		Assert.isTrue(slaves.length > 0);
		indent(slaves[0]);
		SelectionListener listener = new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				boolean state = master.getSelection();
				for (int i = 0; i < slaves.length; i++) {
					slaves[i].setEnabled(state);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		};
		master.addSelectionListener(listener);
		fMasterSlaveListeners.add(listener);
	}

	protected static void indent(Control control) {
		((GridData) control.getLayoutData()).horizontalIndent += INDENT;
	}

	public void initialize() {
		initializeFields();
	}

	private void initializeFields() {

		Iterator iter = fCheckBoxes.keySet().iterator();
		while (iter.hasNext()) {
			Button b = (Button) iter.next();
			String key = (String) fCheckBoxes.get(b);
			b.setSelection(fStore.getBoolean(key));
		}

		for (int i = 0; i < fRadioButtons.size(); i++) {
			Button button = (Button) fRadioButtons.get(i);
			String[] info = (String[]) button.getData();
			int dflt = fStore.getInt(info[0]);
			String val = info[1];
			button.setSelection(dflt == Integer.parseInt(val));
		}

		Iterator iter2 = fComboBoxes.keySet().iterator();
		while (iter2.hasNext()) {
			Combo b = (Combo) iter2.next();
			String value = fStore.getString((String) b.getData());
			Map data = (Map) fComboBoxes.get(b);
			for (Iterator iterator = data.keySet().iterator(); iterator
					.hasNext();) {
				String title = (String) iterator.next();
				if (data.get(title).equals(value)) {
					b.setText(title);
					break;
				}
			}
		}

		iter = fTextFields.keySet().iterator();
		while (iter.hasNext()) {
			Text t = (Text) iter.next();
			String key = (String) fTextFields.get(t);
			t.setText(fStore.getString(key));
		}

		// Update slaves
		iter = fMasterSlaveListeners.iterator();
		while (iter.hasNext()) {
			SelectionListener listener = (SelectionListener) iter.next();
			listener.widgetSelected(null);
		}

		updateStatus(new StatusInfo());
	}

	public void performOk() {
	}

	public void performDefaults() {
		initializeFields();
	}

	IStatus getStatus() {
		if (fStatus == null)
			fStatus = new StatusInfo();
		return fStatus;
	}

	public void dispose() {
	}

	private void numberFieldChanged(Text textControl) {
		String number = textControl.getText();
		IInputValidator validator = (IInputValidator) textControl.getData();
		if (validator == null) {
			IStatus status = validatePositiveNumber(number);
			if (!status.matches(IStatus.ERROR))
				fStore.setValue((String) fTextFields.get(textControl), number);
			updateStatus(status);
		} else {
			StatusInfo status = new StatusInfo();
			String res = validator.isValid(number);
			if (res != null) {
				status.setError(res);
			} else
				fStore.setValue((String) fTextFields.get(textControl), number);
			updateStatus(status);
		}
	}

	private IStatus validatePositiveNumber(String number) {
		StatusInfo status = new StatusInfo();
		if (number.length() == 0) {
			status
					.setError(PreferencesMessages.DLTKEditorPreferencePage_empty_input);
		} else {
			try {
				int value = Integer.parseInt(number);
				if (value < 0)
					status
							.setError(Messages
									.format(
											PreferencesMessages.DLTKEditorPreferencePage_invalid_input,
											number));
			} catch (NumberFormatException e) {
				status
						.setError(Messages
								.format(
										PreferencesMessages.DLTKEditorPreferencePage_invalid_input,
										number));
			}
		}
		return status;
	}

	protected void updateStatus(IStatus status) {
		if (fMainPage == null)
			return;
		fMainPage.setValid(status.isOK());
		StatusUtil.applyToStatusLine(fMainPage, status);
	}

	protected final OverlayPreferenceStore getPreferenceStore() {
		return fStore;
	}

	protected Composite createSubsection(Composite parent,
			SectionManager manager, String label) {
		if (manager != null) {
			return manager.createSection(label);
		} else {
			Group group = new Group(parent, SWT.SHADOW_NONE);
			group.setText(label);
			GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false);
			group.setLayoutData(data);
			return group;
		}
	}

	private FontMetrics fFontMetrics;

	/**
	 * Initializes the computation of horizontal and vertical dialog units based
	 * on the size of current font.
	 * <p>
	 * This method must be called before any of the dialog unit based conversion
	 * methods are called.
	 * </p>
	 * 
	 * @param testControl
	 *            a control from which to obtain the current font
	 */
	protected void initializeDialogUnits(Control testControl) {
		// Compute and store a font metric
		GC gc = new GC(testControl);
		gc.setFont(JFaceResources.getDialogFont());
		fFontMetrics = gc.getFontMetrics();
		gc.dispose();
	}

	/**
	 * Returns the number of pixels corresponding to the width of the given
	 * number of characters.
	 * <p>
	 * This method may only be called after <code>initializeDialogUnits</code>
	 * has been called.
	 * </p>
	 * <p>
	 * Clients may call this framework method, but should not override it.
	 * </p>
	 * 
	 * @param chars
	 *            the number of characters
	 * @return the number of pixels
	 */
	protected int convertWidthInCharsToPixels(int chars) {
		// test for failure to initialize for backward compatibility
		if (fFontMetrics == null)
			return 0;
		return Dialog.convertWidthInCharsToPixels(fFontMetrics, chars);
	}

	/**
	 * Returns the number of pixels corresponding to the height of the given
	 * number of characters.
	 * <p>
	 * This method may only be called after <code>initializeDialogUnits</code>
	 * has been called.
	 * </p>
	 * <p>
	 * Clients may call this framework method, but should not override it.
	 * </p>
	 * 
	 * @param chars
	 *            the number of characters
	 * @return the number of pixels
	 */
	protected int convertHeightInCharsToPixels(int chars) {
		// test for failure to initialize for backward compatibility
		if (fFontMetrics == null)
			return 0;
		return Dialog.convertHeightInCharsToPixels(fFontMetrics, chars);
	}
}
