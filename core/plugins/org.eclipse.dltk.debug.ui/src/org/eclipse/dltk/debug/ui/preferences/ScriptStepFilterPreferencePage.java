/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.preferences;

import java.util.ArrayList;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementVisitor;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptModel;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.debug.ui.IDLTKDebugUIPreferenceConstants;
import org.eclipse.dltk.internal.debug.ui.ScriptDebugOptionsManager;
import org.eclipse.dltk.ui.DLTKExecuteExtensionHelper;
import org.eclipse.dltk.ui.DLTKUILanguageManager;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.dialogs.FilteredTypesSelectionDialog;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

/**
 * The preference page for Java step filtering, located at the node Java > Debug >
 * Step Filtering
 */
public class ScriptStepFilterPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage, IExecutableExtension {

	public static final String PAGE_ID = "org.eclipse.dltk.debug.ui.ScriptStepFilterPreferencePage"; //$NON-NLS-1$

	/**
	 * Content provider for the table. Content consists of instances of
	 * StepFilter.
	 * 
	 */
	class StepFilterContentProvider implements IStructuredContentProvider {
		public StepFilterContentProvider() {
			initTableState(false);
		}

		public Object[] getElements(Object inputElement) {
			return getAllFiltersFromTable();
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}
	}

	// widgets
	private CheckboxTableViewer fTableViewer;
	private Button fUseStepFiltersButton;
	private Button fAddAllButton;
	private Button fAddTypeButton;
	private Button fRemoveFilterButton;
//	private Button fAddFilterButton;
	private Button fSelectAllButton;
	private Button fDeselectAllButton;
	private IDLTKLanguageToolkit fToolkit;

	/**
	 * Constructor
	 */
	public ScriptStepFilterPreferencePage() {
		super();
		setPreferenceStore(DLTKDebugUIPlugin.getDefault().getPreferenceStore());
		setTitle(ScriptDebugPreferencesMessages.ScriptStepFilterPreferencePage_title);
		setDescription(ScriptDebugPreferencesMessages.ScriptStepFilterPreferencePage_description);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		// PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(),
		// IJavaDebugHelpContextIds.JAVA_STEP_FILTER_PREFERENCE_PAGE);
		// The main composite
		Composite composite = SWTFactory.createComposite(parent, parent
				.getFont(), 1, 1, GridData.FILL_BOTH, 0, 0);
		createStepFilterPreferences(composite);
		return composite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	/**
	 * handles the filter button being clicked
	 * 
	 * @param event
	 *            the clicked event
	 */
	private void handleFilterViewerKeyPress(KeyEvent event) {
		if (event.character == SWT.DEL && event.stateMask == 0) {
			removeFilters();
		}
	}

	/**
	 * Create a group to contain the step filter related widgetry
	 */
	private void createStepFilterPreferences(Composite parent) {
		Composite container = SWTFactory.createComposite(parent, parent
				.getFont(), 2, 1, GridData.FILL_BOTH, 0, 0);
		fUseStepFiltersButton = SWTFactory
				.createCheckButton(
						container,
						ScriptDebugPreferencesMessages.ScriptStepFilterPreferencePage__Use_step_filters,
						null, StepFilterManager
								.isUseStepFilters(getPreferenceStore()), 2);
		fUseStepFiltersButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				setPageEnablement(fUseStepFiltersButton.getSelection());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		SWTFactory
				.createLabel(
						container,
						ScriptDebugPreferencesMessages.ScriptStepFilterPreferencePage_Defined_step_fi_lters__8,
						2);
		fTableViewer = CheckboxTableViewer.newCheckList(container, SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
		fTableViewer.getTable().setFont(container.getFont());
		fTableViewer.setLabelProvider(new FilterLabelProvider());
		fTableViewer.setComparator(new FilterViewerComparator());
		fTableViewer.setContentProvider(new StepFilterContentProvider());
		fTableViewer.setInput(getAllStoredFilters(false));
		fTableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		fTableViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				((Filter) event.getElement()).setChecked(event.getChecked());
			}
		});
		fTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						ISelection selection = event.getSelection();
						if (selection.isEmpty()) {
							fRemoveFilterButton.setEnabled(false);
						} else {
							fRemoveFilterButton.setEnabled(true);
						}
					}
				});
		fTableViewer.getControl().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
				handleFilterViewerKeyPress(event);
			}
		});

		createStepFilterButtons(container);

		setPageEnablement(fUseStepFiltersButton.getSelection());
	}

	/**
	 * initializes the checked state of the filters when the dialog opens
	 * 
	 * @since 3.2
	 */
	private void initTableState(boolean defaults) {
		Filter[] filters = getAllStoredFilters(defaults);
		for (int i = 0; i < filters.length; i++) {
			fTableViewer.add(filters[i]);
			fTableViewer.setChecked(filters[i], filters[i].isChecked());
		}
	}

	/**
	 * Enables or disables the widgets on the page, with the exception of
	 * <code>fUseStepFiltersButton</code> according to the passed boolean
	 * 
	 * @param enabled
	 *            the new enablement status of the page's widgets
	 * @since 3.2
	 */
	protected void setPageEnablement(boolean enabled) {
//		fAddFilterButton.setEnabled(enabled);
		fAddAllButton.setEnabled(enabled);
		fAddTypeButton.setEnabled(enabled);
		fDeselectAllButton.setEnabled(enabled);
		fSelectAllButton.setEnabled(enabled);
		fTableViewer.getTable().setEnabled(enabled);
		fRemoveFilterButton.setEnabled(enabled
				& !fTableViewer.getSelection().isEmpty());
	}

	/**
	 * Creates the button for the step filter options
	 * 
	 * @param container
	 *            the parent container
	 */
	private void createStepFilterButtons(Composite container) {
		initializeDialogUnits(container);
		// button container
		Composite buttonContainer = new Composite(container, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_VERTICAL);
		buttonContainer.setLayoutData(gd);
		GridLayout buttonLayout = new GridLayout();
		buttonLayout.numColumns = 1;
		buttonLayout.marginHeight = 0;
		buttonLayout.marginWidth = 0;
		buttonContainer.setLayout(buttonLayout);
		// Add filter button
//		fAddFilterButton = SWTFactory
//				.createPushButton(
//						buttonContainer,
//						ScriptDebugPreferencesMessages.ScriptStepFilterPreferencePage_Add__Filter_9,
//						ScriptDebugPreferencesMessages.ScriptStepFilterPreferencePage_Key_in_the_name_of_a_new_step_filter_10,
//						null);
//		fAddFilterButton.addListener(SWT.Selection, new Listener() {
//			public void handleEvent(Event e) {
//				addFilter();
//			}
//		});
		// Add type button
		fAddTypeButton = SWTFactory
				.createPushButton(
						buttonContainer,
						ScriptDebugPreferencesMessages.ScriptStepFilterPreferencePage_Add__Type____11,
						ScriptDebugPreferencesMessages.ScriptStepFilterPreferencePage_Choose_a_Java_type_and_add_it_to_step_filters_12,
						null);
		fAddTypeButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				addType();
			}
		});
		// Add package button
		fAddAllButton = SWTFactory
				.createPushButton(
						buttonContainer,
						ScriptDebugPreferencesMessages.ScriptStepFilterPreferencePage_Add__All____13,
						ScriptDebugPreferencesMessages.ScriptStepFilterPreferencePage_Choose_a_package_and_add_it_to_step_filters_14,
						null);
		fAddAllButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				addAll();
			}
		});
		// Remove button
		fRemoveFilterButton = SWTFactory
				.createPushButton(
						buttonContainer,
						ScriptDebugPreferencesMessages.ScriptStepFilterPreferencePage__Remove_15,
						ScriptDebugPreferencesMessages.ScriptStepFilterPreferencePage_Remove_all_selected_step_filters_16,
						null);
		fRemoveFilterButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				removeFilters();
			}
		});
		fRemoveFilterButton.setEnabled(false);

		Label separator = new Label(buttonContainer, SWT.NONE);
		separator.setVisible(false);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.heightHint = 4;
		separator.setLayoutData(gd);
		// Select All button
		fSelectAllButton = SWTFactory
				.createPushButton(
						buttonContainer,
						ScriptDebugPreferencesMessages.ScriptStepFilterPreferencePage__Select_All_1,
						ScriptDebugPreferencesMessages.ScriptStepFilterPreferencePage_Selects_all_step_filters_2,
						null);
		fSelectAllButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				fTableViewer.setAllChecked(true);
			}
		});
		// De-Select All button
		fDeselectAllButton = SWTFactory
				.createPushButton(
						buttonContainer,
						ScriptDebugPreferencesMessages.ScriptStepFilterPreferencePage_Deselect_All_3,
						ScriptDebugPreferencesMessages.ScriptStepFilterPreferencePage_Deselects_all_step_filters_4,
						null);
		fDeselectAllButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				fTableViewer.setAllChecked(false);
			}
		});

	}

	/**
	 * add a new type to the listing of available filters
	 */
	private void addType() {
		// SelectionDialog dialog = JavaUI.createTypeDialog(getShell(),
		// PlatformUI.getWorkbench().getProgressService(),
		// SearchEngine.createWorkspaceScope(),
		// IJavaElementSearchConstants.CONSIDER_CLASSES, false);
		FilteredTypesSelectionDialog dialog = new FilteredTypesSelectionDialog(
				getShell(), false, PlatformUI.getWorkbench()
						.getProgressService(), SearchEngine
						.createWorkspaceScope(fToolkit),
				IDLTKSearchConstants.TYPE, fToolkit);
		dialog.setMessage(Messages.ScriptStepFilterPreferencePage_search);
		dialog.setInitialPattern(""); //$NON-NLS-1$
		dialog
				.setTitle(ScriptDebugPreferencesMessages.ScriptStepFilterPreferencePage_Add_type_to_step_filters_20);
		dialog
				.setMessage(ScriptDebugPreferencesMessages.ScriptStepFilterPreferencePage_Select_a_type_to_filter_when_stepping_23);
		if (dialog.open() == IDialogConstants.OK_ID) {
			Object[] types = dialog.getResult();
			if (types != null && types.length > 0) {
				IType type = (IType) types[0];
				try {
					addFilter(type.getTypeQualifiedName("."), true, type //$NON-NLS-1$
							.getFlags());
				} catch (ModelException e) {
					if (DLTKCore.DEBUG) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * add a new package to the list of all available package filters
	 */
	private void addAll() {
		IScriptModel model = DLTKCore.create(ResourcesPlugin.getWorkspace()
				.getRoot());
		try {
			model.accept(new IModelElementVisitor() {
				public boolean visit(IModelElement element) {
					if (element.getElementType() == IModelElement.SCRIPT_PROJECT ) {
						IDLTKLanguageToolkit languageToolkit;
						try {
							languageToolkit = DLTKLanguageManager.getLanguageToolkit(element);
							if( !fToolkit.getNatureId().equals(languageToolkit.getNatureId()) ) {
								return false;
							}
						} catch (CoreException e) {
							return false;
						}
					}
					if (element.getElementType() == IModelElement.PROJECT_FRAGMENT) {
						IProjectFragment fragment = (IProjectFragment) element;
						if (fragment.isExternal()) {
							return false;
						}
					}
					if (element.getElementType() == IModelElement.TYPE) {
						IType type = (IType) element;
						Filter filter;
						try {
							filter = new Filter(type.getTypeQualifiedName("."), //$NON-NLS-1$
									true, type.getFlags());
							addFilter(filter);
						} catch (ModelException e) {
							if (DLTKCore.DEBUG) {
								e.printStackTrace();
							}
						}
					}
					return true;
				}
			});
		} catch (ModelException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Removes the currently selected filters.
	 */
	protected void removeFilters() {
		fTableViewer
				.remove(((IStructuredSelection) fTableViewer.getSelection())
						.toArray());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	public boolean performOk() {
		StepFilterManager.setUseStepFilters(fUseStepFiltersButton.getSelection(), getPreferenceStore());
		IPreferenceStore store = getPreferenceStore();
		ArrayList active = new ArrayList();
		ArrayList inactive = new ArrayList();
		String name = ""; //$NON-NLS-1$
		Filter[] filters = getAllFiltersFromTable();
		for (int i = 0; i < filters.length; i++) {
			name = filters[i].getName();
			String modifiers = ":" //$NON-NLS-1$
					+ Integer.toString(filters[i].getModifiers());
			if (filters[i].isChecked()) {
				active.add(name + modifiers);
			} else {
				inactive.add(name + modifiers);
			}
		}
		String pref = ScriptDebugOptionsManager.serializeList((String[]) active
				.toArray(new String[active.size()]));
		store.setValue(IDLTKDebugUIPreferenceConstants.PREF_ACTIVE_FILTERS_LIST,
				pref);
		pref = ScriptDebugOptionsManager.serializeList((String[]) inactive
				.toArray(new String[inactive.size()]));
		store.setValue(
				IDLTKDebugUIPreferenceConstants.PREF_INACTIVE_FILTERS_LIST, pref);
		return super.performOk();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	protected void performDefaults() {
		boolean stepenabled = DebugUITools.isUseStepFilters();
		fUseStepFiltersButton.setSelection(stepenabled);
		setPageEnablement(stepenabled);
		fTableViewer.getTable().removeAll();
		initTableState(true);
		super.performDefaults();
	}

	/**
	 * adds a single filter to the viewer
	 * 
	 * @param filter
	 *            the new filter to add
	 * @param checked
	 *            the checked state of the new filter
	 * @since 3.2
	 */
	protected void addFilter(String filter, boolean checked, int modifiers) {
		if (filter != null) {
			Filter f = new Filter(filter, checked, modifiers);
			fTableViewer.add(f);
			fTableViewer.setChecked(f, checked);
		}
	}

	protected void addFilter(Filter filter) {
		if (filter != null) {
			Filter[] allFiltersFromTable = getAllFiltersFromTable();
			for (int i = 0; i < allFiltersFromTable.length; i++) {
				if (filter.equals(allFiltersFromTable[i])) {
					return;
				}
			}
			fTableViewer.add(filter);
			fTableViewer.setChecked(filter, filter.isChecked());
		}
	}

	/**
	 * returns all of the filters from the table, this includes ones that have
	 * not yet been saved
	 * 
	 * @return a possibly empty lits of filters fron the table
	 * @since 3.2
	 */
	protected Filter[] getAllFiltersFromTable() {
		TableItem[] items = fTableViewer.getTable().getItems();
		Filter[] filters = new Filter[items.length];
		for (int i = 0; i < items.length; i++) {
			filters[i] = (Filter) items[i].getData();
			filters[i].setChecked(items[i].getChecked());
		}
		return filters;
	}

	/**
	 * Returns all of the committed filters
	 * 
	 * @return an array of committed filters
	 * @since 3.2
	 */
	protected Filter[] getAllStoredFilters(boolean defaults) {
		Filter[] filters = null;
		String[] activefilters, inactivefilters;
		IPreferenceStore store = getPreferenceStore();
		if (defaults) {
			activefilters = ScriptDebugOptionsManager
					.parseList(store
							.getDefaultString(IDLTKDebugUIPreferenceConstants.PREF_ACTIVE_FILTERS_LIST));
			inactivefilters = ScriptDebugOptionsManager
					.parseList(store
							.getDefaultString(IDLTKDebugUIPreferenceConstants.PREF_INACTIVE_FILTERS_LIST));
		} else {
			activefilters = ScriptDebugOptionsManager
					.parseList(store
							.getString(IDLTKDebugUIPreferenceConstants.PREF_ACTIVE_FILTERS_LIST));
			inactivefilters = ScriptDebugOptionsManager
					.parseList(store
							.getString(IDLTKDebugUIPreferenceConstants.PREF_INACTIVE_FILTERS_LIST));
		}
		filters = new Filter[activefilters.length + inactivefilters.length];
		for (int i = 0; i < activefilters.length; i++) {
			String[] split = activefilters[i].split(":"); //$NON-NLS-1$
			if (split.length == 1) {
				filters[i] = new Filter(split[0], true, 0);
			} else {
				filters[i] = new Filter(split[0], true, (new Integer(split[1]))
						.intValue());
			}
		}
		for (int i = 0; i < inactivefilters.length; i++) {
			String[] split = inactivefilters[i].split(":"); //$NON-NLS-1$
			if (split.length == 1) {
				filters[i + activefilters.length] = new Filter(split[0], false,
						0);
			} else {
				filters[i + activefilters.length] = new Filter(split[0], false,
						(new Integer(split[1])).intValue());
			}
		}
		return filters;
	}

	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		fToolkit = DLTKExecuteExtensionHelper.getLanguageToolkit(config,
				propertyName, data);
		IDLTKUILanguageToolkit uiToolkit = DLTKUILanguageManager
				.getLanguageToolkit(fToolkit.getNatureId());
		IPreferenceStore preferenceStore = uiToolkit.getPreferenceStore();
		Assert.isNotNull(preferenceStore);
		setPreferenceStore(preferenceStore);
	}
}
