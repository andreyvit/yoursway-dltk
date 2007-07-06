package org.eclipse.dltk.debug.ui.preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.launching.debug.DebuggingEngineManager;
import org.eclipse.dltk.launching.debug.IDebuggingEngine;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore.OverlayKey;
import org.eclipse.jface.preference.IPreferenceStore;
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
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.PreferenceLinkArea;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public abstract class AbstractScriptDebuggingEngineConfigurationBlock extends
		AbstractConfigurationBlock {

	private ComboViewer allEngines;
	// private Label description;
	private Composite descriptionPlace;
	private PreferencePage preferencePage;

	private Map engineToDescriptionMap;

	public AbstractScriptDebuggingEngineConfigurationBlock(
			OverlayPreferenceStore store, PreferencePage preferencePage) {
		super(store, preferencePage);

		this.preferencePage = preferencePage;

		this.engineToDescriptionMap = new HashMap();

		store.addKeys(createKeys());
	}

	public Control createControl(Composite parent) {
		Composite composite = createComposite(parent, parent.getFont(), 1, 1,
				0, 0, GridData.FILL);

		// Link
		PreferenceLinkArea area = new PreferenceLinkArea(composite, SWT.NONE,
				ScriptDebugPreferencePage.PAGE_ID,
				ScriptDebugPreferencesMessages.LinkToGeneralPreferenses,
				(IWorkbenchPreferenceContainer) preferencePage.getContainer(),
				null);

		area.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, false, false));

		// Debugging engine
		createEngineSelector(composite, new GridData(SWT.FILL, SWT.FILL, true,
				false));

		return composite;
	}

	public void initialize() {
		super.initialize();

		initializeValues();
	}

	private OverlayKey[] createKeys() {
		ArrayList keys = new ArrayList();

		keys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.STRING, getDebuggingEngineIdKey()));

		return (OverlayKey[]) keys
				.toArray(new OverlayPreferenceStore.OverlayKey[keys.size()]);
	}

	protected IDebuggingEngine getSelectedDebuggineEngine() {
		IStructuredSelection selection = (IStructuredSelection) allEngines
				.getSelection();
		if (selection != null) {
			return (IDebuggingEngine) selection.getFirstElement();
		}

		return null;
	}

	protected void setSelectedDebuggingEngine(IDebuggingEngine engine) {
		if (engine != null) {
			allEngines.setSelection(new StructuredSelection(engine));
		}
	}

	protected void updateDescription(IDebuggingEngine engine) {
		StackLayout stackLayout = (StackLayout) descriptionPlace.getLayout();
		Composite composite = (Composite) engineToDescriptionMap.get(engine
				.getId());
		stackLayout.topControl = composite;
		descriptionPlace.layout();
	}

	protected void createEngineSelector(Composite parent, Object data) {
		Group group = new Group(parent, SWT.NONE);
		group.setFont(parent.getFont());
		group.setLayoutData(data);
		group.setText(ScriptDebugPreferencesMessages.DebuggingEngine);

		GridLayout layout = new GridLayout(1, false);
		group.setLayout(layout);

		// Name
		Label nameLabel = new Label(group, SWT.NONE);
		nameLabel.setText(ScriptDebugPreferencesMessages.NameLabel);

		allEngines = new ComboViewer(group);
		allEngines.getCombo().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));
		allEngines.setLabelProvider(new LabelProvider() {
			public String getText(Object element) {
				return ((IDebuggingEngine) element).getName();
			}
		});

		allEngines.add(DebuggingEngineManager.getInstance()
				.getDebuggingEngines(getNatureId()));

		allEngines.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateDescription(getSelectedDebuggineEngine());
			}
		});

		descriptionPlace = createComposite(group, group.getFont(), 1, 1, 0, 0,
				GridData.FILL);
		descriptionPlace.setLayout(new StackLayout());
	}

	private Composite createDescription(Composite parent,
			IDebuggingEngine engine) {
		Composite composite = createComposite(parent, parent.getFont(), 1, 1,
				0, 0, GridData.FILL);
		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		label.setText(ScriptDebugPreferencesMessages.DescriptionLabel + " "
				+ engine.getDescription());

		final String preferencePageId = engine.getPreferencePageId();
		if (preferencePageId != null && preferencePageId.length() > 0) {
			PreferenceLinkArea area = new PreferenceLinkArea(
					composite,
					SWT.NONE,
					preferencePageId,
					ScriptDebugPreferencesMessages.LingToDebuggingEnginePreferences,
					(IWorkbenchPreferenceContainer) preferencePage
							.getContainer(), null);

			area.getControl().setLayoutData(
					new GridData(SWT.FILL, SWT.FILL, false, false));
		}

		return composite;
	}

	protected void initializeValues() {
		DebuggingEngineManager manager = DebuggingEngineManager.getInstance();

		String natureId = getNatureId();
		String engineId = getPreferenceStore().getString(
				getDebuggingEngineIdKey());

		IDebuggingEngine[] engines = manager.getDebuggingEngines(natureId);
		for (int i = 0; i < engines.length; ++i) {
			IDebuggingEngine engine = engines[i];
			engineToDescriptionMap.put(engine.getId(), createDescription(
					descriptionPlace, engine));
		}

		if ("".equals(engineId)) {
			// Engine not selected (for example, first launch)
			IDebuggingEngine engine = manager
					.getSelectedDebuggineEngine(natureId);
			if (engine != null) {
				engineId = engine.getId();
			}
		}

		if (!"".equals(engineId)) {
			IDebuggingEngine engine = manager.getDebuggingEngine(natureId,
					engineId);

			setSelectedDebuggingEngine(engine);
		}
	}

	public void performOk() {
		IPreferenceStore prefs = getPreferenceStore();

		IDebuggingEngine engine = getSelectedDebuggineEngine();

		if (engine != null) {
			prefs.setValue(getDebuggingEngineIdKey(), engine.getId());
		}
	}

	protected abstract String getDebuggingEngineIdKey();

	protected abstract String getNatureId();
}
