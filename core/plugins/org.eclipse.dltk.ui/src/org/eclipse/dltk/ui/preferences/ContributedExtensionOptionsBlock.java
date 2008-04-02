package org.eclipse.dltk.ui.preferences;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.core.DLTKContributionExtensionManager;
import org.eclipse.dltk.core.IDLTKContributedExtension;
import org.eclipse.dltk.ui.dialogs.PropertyLinkArea;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.util.SWTFactory;
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

	private ComboViewerBlock viewer;

	// private ComboViewer contributionViewer;
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

		return composite;
	}

	/**
	 * Returns the extension manager for the contributed extension.
	 */
	protected abstract DLTKContributionExtensionManager getExtensionManager();

	/**
	 * Returns the language's nature id.
	 */
	protected abstract String getNatureId();

	/**
	 * Returns the message that will be used to create the link to the
	 * preference or property page.
	 */
	protected abstract String getPreferenceLinkMessage();

	/**
	 * Returns the preference key that will be used to store the contribution
	 * preference.
	 */
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

		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		viewer = new ComboViewerBlock(group, gd) {
			protected String getObjectName(Object element) {
				return ((IDLTKContributedExtension) element).getName();
			}

			protected void selectedObjectChanged(Object element) {
				updateSelection((IDLTKContributedExtension) element);
			}

			protected String getObjectId(Object element) {
				return ((IDLTKContributedExtension) element).getId();
			}

			protected Object getDefaultObject() {
				return getExtensionManager().getSelectedContribution(
						getProject(), getNatureId());
			}

			protected String getSavedObjectId() {
				return getValue(getSavedContributionKey());
			}

			protected Object getObjectById(String id) {
				return getExtensionManager().getContributionById(id);
			}
		};

		// Description
		descriptionPlace = SWTFactory.createComposite(group, group.getFont(),
				1, 1, GridData.FILL);
		descriptionPlace.setLayout(new StackLayout());
	}

	protected String[] getFullBuildDialogStrings(boolean workspaceSettings) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the label that will be used for the selector group.
	 */
	protected abstract String getSelectorGroupLabel();

	/**
	 * Returns the label that will be used for the selector name.
	 */
	protected abstract String getSelectorNameLabel();

	protected void initialize() {
		super.initialize();

		IDLTKContributedExtension[] contributions = getExtensionManager()
				.getContributions(getNatureId());

		for (int i = 0; i < contributions.length; i++) {
			IDLTKContributedExtension contrib = contributions[i];
			contribToDescMap.put(contrib.getId(), createDescription(
					descriptionPlace, contrib));
		}

		viewer.initialize(contributions);
	}

	protected final void updateSelection(IDLTKContributedExtension contrib) {
		String id = contrib.getId();
		setValue(getSavedContributionKey(), id);

		Composite composite = (Composite) contribToDescMap.get(id);

		((StackLayout) descriptionPlace.getLayout()).topControl = composite;
		descriptionPlace.layout();
	}

	private boolean hasValidId(String id) {
		return (id != null && !"".equals(id)); //$NON-NLS-1$
	}
}
