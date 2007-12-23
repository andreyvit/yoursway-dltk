package org.eclipse.dltk.ui.preferences;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.core.DLTKContributionExtensionManager;
import org.eclipse.dltk.core.IDLTKContributedExtension;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore.OverlayKey;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public abstract class ContributedExtensionConfigurationBlock extends
		ImprovedAbstractConfigurationBlock {
	private PreferencePage preferencePage;

	private Composite descriptionPlace;
	private ComboViewer contributions;

	private Map contribToDescMap = new HashMap();

	public ContributedExtensionConfigurationBlock(OverlayPreferenceStore store,
			PreferencePage page) {
		super(store, page);

		// need this for later
		this.preferencePage = page;
		
		configurePreferenceStore(store);
	}

	public Control createControl(Composite parent) {
		// Composite
		Composite composite = SWTFactory.createComposite(parent, parent
				.getFont(), 1, 1, GridData.FILL);

		GridData data = new GridData(SWT.FILL, SWT.FILL, true, false);

		addPreferenceLink(composite,
				(IWorkbenchPreferenceContainer) preferencePage.getContainer());

		createSelector(composite, data);

		return composite;
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.ImprovedAbstractConfigurationBlock#initialize()
	 */
	public final void initialize() {
		super.initialize();
		initializeValues();
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.ImprovedAbstractConfigurationBlock#performOk()
	 */
	public void performOk() {
		IDLTKContributedExtension selected = getSelectedContribution();
		if (selected != null) {
			getPreferenceStore().setValue(getContributionPrefKey(), selected.getId());			
		}		
	}
	
	protected void addPreferenceLink(Composite composite,
			IWorkbenchPreferenceContainer container) {
		// default does nothing
	}

	protected void addDescriptionPreferenceLink(Composite composite, String prefPageId,
			IWorkbenchPreferenceContainer container) {
		// default does nothing
	}
	
	protected void createSelector(Composite composite, GridData data) {
		// Group
		Group group = SWTFactory.createGroup(composite,
				getSelectorGroupLabel(), 1, 1, GridData.FILL_HORIZONTAL);

		// Name
		SWTFactory.createLabel(group, getSelectorNameLabel(), 1);

		contributions = new ComboViewer(group);
		contributions.getCombo().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));

		contributions.setLabelProvider(new LabelProvider() {
			public String getText(Object element) {
				return ((IDLTKContributedExtension) element).getName();
			}
		});

		contributions.add(getContributions());
		contributions
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						updateDescription(getSelectedContribution());
					}
				});

		// Description
		descriptionPlace = SWTFactory.createComposite(group, group.getFont(),
				1, 1, GridData.FILL);
		descriptionPlace.setLayout(new StackLayout());
	}

	protected abstract DLTKContributionExtensionManager getExtensionManager();
	
	protected abstract String getSelectorGroupLabel();

	protected abstract String getSelectorNameLabel();
	
	protected abstract String getNatureId();

	/**
	 * Returns the preference key name used to store the selected contribution
	 */
	protected abstract String getContributionPrefKey();
	
	protected void initializeValues() {
		IDLTKContributedExtension[] contributions = getContributions();

		for (int i = 0; i < contributions.length; i++) {
			IDLTKContributedExtension contrib = contributions[i];
			contribToDescMap.put(contrib.getId(), createDescription(
					descriptionPlace, contrib));
		}
		
		String contribId = getPreferenceStore().getString(getContributionPrefKey());

		if ("".equals(contribId)) {
			// no entry exists in the preference store for the pref key
			IDLTKContributedExtension contrib = getDefaultContribution();
			if (contrib != null) {
				contribId = contrib.getId();
			}
		}
		
		setSelectedContribution(getContributionById(contribId));
	}

	protected Composite createDescription(Composite parent,
			IDLTKContributedExtension contrib) {
		Composite composite = SWTFactory.createComposite(parent, parent
				.getFont(), 1, 1, GridData.FILL);

		String desc = contrib.getDescription();
		SWTFactory.createLabel(composite, desc, 1);
		
		String prefPageId = contrib.getPreferencePageId();
		if (prefPageId != null && !"".equals(prefPageId)) {
			addDescriptionPreferenceLink(composite, prefPageId, (IWorkbenchPreferenceContainer) preferencePage
					.getContainer());
		}
		
		return composite;
	}

	protected final IDLTKContributedExtension getSelectedContribution() {
		IStructuredSelection selection = (IStructuredSelection) contributions
				.getSelection();
		if (selection != null) {
			return (IDLTKContributedExtension) selection.getFirstElement();
		}

		return null;
	}

	protected final void setSelectedContribution(IDLTKContributedExtension contrib) {
		if (contrib != null) {
			contributions.setSelection(new StructuredSelection(contrib));
		}
	}

	protected final void updateDescription(IDLTKContributedExtension contrib) {
		StackLayout stackLayout = (StackLayout) descriptionPlace.getLayout();
		Composite composite = (Composite) contribToDescMap.get(contrib.getId());

		stackLayout.topControl = composite;
		descriptionPlace.layout();
	}

	private void configurePreferenceStore(OverlayPreferenceStore store) {
		OverlayKey[] keys = new OverlayKey[] { new OverlayKey(
				OverlayPreferenceStore.STRING, getContributionPrefKey()) };

		store.addKeys(keys);
	}
	
	private IDLTKContributedExtension[] getContributions() {
		return getExtensionManager().getContributions(getNatureId());
	}
	
	private IDLTKContributedExtension getDefaultContribution() {
		return getExtensionManager().getSelectedContribution(null, getNatureId());
	}
	
	private IDLTKContributedExtension getContributionById(String id) {
		return getExtensionManager().getContributionById(id);
	}
}
