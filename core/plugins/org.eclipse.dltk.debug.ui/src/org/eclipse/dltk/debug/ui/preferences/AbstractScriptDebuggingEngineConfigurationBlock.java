package org.eclipse.dltk.debug.ui.preferences;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.launching.debug.DebuggingEngineManager;
import org.eclipse.dltk.launching.debug.IDebuggingEngine;
import org.eclipse.dltk.ui.preferences.ImprovedAbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore.OverlayKey;
import org.eclipse.dltk.ui.util.SWTFactory;
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.dialogs.PreferenceLinkArea;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public abstract class AbstractScriptDebuggingEngineConfigurationBlock extends
		ImprovedAbstractConfigurationBlock {

	private ComboViewer allEngines;
	private Composite descriptionPlace;
	private PreferencePage preferencePage;

	private Map engineToDescriptionMap;

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

	private void configurePreferenceStore(OverlayPreferenceStore store) {
		OverlayKey[] keys = new OverlayKey[] { new OverlayKey(
				OverlayPreferenceStore.STRING, getDebuggingEngineIdKey()) };

		store.addKeys(keys);
	}

	public AbstractScriptDebuggingEngineConfigurationBlock(
			OverlayPreferenceStore store, PreferencePage preferencePage) {
		super(store, preferencePage);

		configurePreferenceStore(store);

		this.preferencePage = preferencePage;
		this.engineToDescriptionMap = new HashMap();
	}

	public Control createControl(Composite parent) {
		// Composite
		Composite composite = SWTFactory.createComposite(parent, parent
				.getFont(), 1, 1, GridData.FILL);

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

	protected void createEngineSelector(Composite parent, Object data) {

		// Group
		Group group = SWTFactory.createGroup(parent,
				ScriptDebugPreferencesMessages.DebuggingEngine, 1, 1,
				GridData.FILL_HORIZONTAL);

		// Name
		SWTFactory.createLabel(group, ScriptDebugPreferencesMessages.NameLabel,
				1);

		// Engines
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

		// Description
		descriptionPlace = SWTFactory.createComposite(group, group.getFont(),
				1, 1, GridData.FILL);
		descriptionPlace.setLayout(new StackLayout());
	}

	protected Composite createDescription(Composite parent,
			IDebuggingEngine engine) {
		Composite composite = SWTFactory.createComposite(parent, parent
				.getFont(), 1, 1, GridData.FILL);

		SWTFactory.createLabel(composite,
				ScriptDebugPreferencesMessages.DescriptionLabel + " "
						+ engine.getDescription(), 1);

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
					.getSelectedDebuggingEngine(natureId);
			if (engine != null) {
				engineId = engine.getId();
			}
		}

		if (!"".equals(engineId)) {
			IDebuggingEngine engine = manager.getDebuggingEngine(engineId);

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
