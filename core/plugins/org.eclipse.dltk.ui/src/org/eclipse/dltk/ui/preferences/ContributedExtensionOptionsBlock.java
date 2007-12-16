package org.eclipse.dltk.ui.preferences;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.core.DLTKContributionExtensionManager;
import org.eclipse.dltk.core.IDLTKContributedExtension;
import org.eclipse.dltk.ui.dialogs.PropertyLinkArea;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.util.SWTFactory;
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
import org.eclipse.ui.dialogs.PreferenceLinkArea;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

/**
 */
public abstract class ContributedExtensionOptionsBlock extends
		AbstractOptionsBlock {
	private Map contribToDescMap = new HashMap();

	private ComboViewer contributionViewer;
	private Composite descriptionPlace;

	public ContributedExtensionOptionsBlock(IStatusChangeListener context,
			IProject project, PreferenceKey[] allKeys,
			IWorkbenchPreferenceContainer container) {
		super(context, project, allKeys, container);
	}

	// ~ Methods

	public final Control createOptionsBlock(Composite parent) {
		Composite composite = SWTFactory.createComposite(parent, parent
				.getFont(), 1, 1, GridData.FILL);

		createSelectorBlock(composite);
		initializeBlock();

		return composite;
	}

	protected abstract DLTKContributionExtensionManager getExtensionManager();

	protected abstract String getNatureId();

	protected abstract String getPreferenceLinkMessage();

	protected abstract PreferenceKey getSavedContributionKey();

	protected Composite createDescription(Composite parent,
			IDLTKContributedExtension contrib) {
		Composite composite = SWTFactory.createComposite(parent, parent
				.getFont(), 1, 1, GridData.FILL);

		String desc = contrib.getDescription();
		SWTFactory.createLabel(composite, desc, 1);

		String prefPageId = contrib.getPreferencePageId();
		String propPageId = contrib.getPropertyPageId();

		// we're a property page
		if (isProjectPreferencePage() && hasValidId(propPageId)) {
			new PropertyLinkArea(composite, SWT.NONE, propPageId, fProject,
					getPreferenceLinkMessage(), getPreferenceContainer());
		}

		// we're a preference page
		if (!isProjectPreferencePage() && hasValidId(prefPageId)) {
			new PreferenceLinkArea(composite, SWT.NONE, prefPageId,
					getPreferenceLinkMessage(), getPreferenceContainer(), null);
		}

		return composite;
	}

	protected void createSelectorBlock(Composite composite) {
		// Group
		Group group = SWTFactory.createGroup(composite,
				getSelectorGroupLabel(), 1, 1, GridData.FILL_HORIZONTAL);

		// Name
		SWTFactory.createLabel(group, getSelectorNameLabel(), 1);

		contributionViewer = new ComboViewer(group);
		contributionViewer.getCombo().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));

		contributionViewer.setLabelProvider(new LabelProvider() {
			public String getText(Object element) {
				return ((IDLTKContributedExtension) element).getName();
			}
		});

		contributionViewer.add(getContributions());
		contributionViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						updateSelection(getSelectedContribution());
					}
				});

		// Description
		descriptionPlace = SWTFactory.createComposite(group, group.getFont(),
				1, 1, GridData.FILL);
		descriptionPlace.setLayout(new StackLayout());
	}

	protected String[] getFullBuildDialogStrings(boolean workspaceSettings) {
		// TODO Auto-generated method stub
		return null;
	}

	protected final IDLTKContributedExtension getSelectedContribution() {
		IStructuredSelection selection = (IStructuredSelection) contributionViewer
				.getSelection();
		if (selection != null) {
			return (IDLTKContributedExtension) selection.getFirstElement();
		}

		return null;
	}

	protected abstract String getSelectorGroupLabel();

	protected abstract String getSelectorNameLabel();

	protected void initializeBlock() {
		IDLTKContributedExtension[] contributions = getContributions();

		for (int i = 0; i < contributions.length; i++) {
			IDLTKContributedExtension contrib = contributions[i];
			contribToDescMap.put(contrib.getId(), createDescription(
					descriptionPlace, contrib));
		}

		String contribId = getValue(getSavedContributionKey());

		if (contribId == null || "".equals(contribId)) {
			// no entry exists in the preference store for the pref key
			IDLTKContributedExtension contrib = getDefaultContribution();
			if (contrib != null) {
				contribId = contrib.getId();
			}
		}

		setSelectedContribution(getContributionById(contribId));
	}

	protected final void setSelectedContribution(
			IDLTKContributedExtension contrib) {
		if (contrib != null) {
			contributionViewer.setSelection(new StructuredSelection(contrib));
		}
	}

	protected final void updateSelection(IDLTKContributedExtension contrib) {
		String id = contrib.getId();
		setValue(getSavedContributionKey(), id);

		Composite composite = (Composite) contribToDescMap.get(id);

		((StackLayout) descriptionPlace.getLayout()).topControl = composite;
		descriptionPlace.layout();
	}

	private IDLTKContributedExtension getContributionById(String id) {
		return getExtensionManager().getContributionById(id);
	}

	private IDLTKContributedExtension[] getContributions() {
		return getExtensionManager().getContributions(getNatureId());
	}

	private IDLTKContributedExtension getDefaultContribution() {
		return getExtensionManager().getSelectedContribution(getNatureId());
	}

	private boolean hasValidId(String id) {
		return (id != null && !"".equals(id));
	}
}
