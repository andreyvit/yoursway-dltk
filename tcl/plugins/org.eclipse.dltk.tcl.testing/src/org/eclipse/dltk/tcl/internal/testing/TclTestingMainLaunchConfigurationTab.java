package org.eclipse.dltk.tcl.internal.testing;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.dltk.debug.ui.messages.DLTKLaunchConfigurationsMessages;
import org.eclipse.dltk.tcl.internal.debug.ui.launchConfigurations.TclMainLaunchConfigurationTab;
import org.eclipse.dltk.tcl.testing.ITclTestingEngine;
import org.eclipse.dltk.testing.IDLTKTestingConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class TclTestingMainLaunchConfigurationTab extends
		TclMainLaunchConfigurationTab {
	private Button detect;
	private Combo engineType;
	private Map nameToId = new HashMap();

	protected void doCreateControl(Composite composite) {
		createMainModuleEditor(composite,
				DLTKLaunchConfigurationsMessages.mainTab_mainModule);
		createVerticalSpacer(composite, 1);
		createTestEngineEditor(composite, "Tcl Testing engine");

	}

	protected void createTestEngineEditor(Composite parent, String text) {
		Font font = parent.getFont();
		Group mainGroup = new Group(parent, SWT.NONE);
		mainGroup.setText(text);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		mainGroup.setLayoutData(gd);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		mainGroup.setLayout(layout);
		mainGroup.setFont(font);
		engineType = new Combo(mainGroup, SWT.SINGLE | SWT.BORDER
				| SWT.DROP_DOWN);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		engineType.setLayoutData(gd);
		engineType.setFont(font);
		engineType.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateLaunchConfigurationDialog();
			}
		});
		detect = createPushButton(mainGroup, "Detect", null);

		ITclTestingEngine[] engines = TclTestingEngineManager.getEngines();
		for (int i = 0; i < engines.length; i++) {
			String name = engines[i].getName();
			this.engineType.add(name);
			nameToId.put(name, engines[i].getId());
		}
		detect.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleDetectButtonSelected();
			}
		});
		handleDetectButtonSelected();
	}

	private void handleDetectButtonSelected() {
		ITclTestingEngine[] engines = TclTestingEngineManager.getEngines();
		this.engineType.select(0);
		for (int i = 0; i < engines.length; i++) {
			// if( engines.i)
		}
	}

	protected void doPerformApply(ILaunchConfigurationWorkingCopy config) {
		super.doPerformApply(config);
		config.setAttribute(IDLTKTestingConstants.ENGINE_ID_ATR,
				(String) this.nameToId.get(this.engineType.getText()));
	}

	protected void doInitializeForm(ILaunchConfiguration config) {
		super.doInitializeForm(config);
		ITclTestingEngine[] engines = TclTestingEngineManager.getEngines();
		String id = null;
		try {
			id = config.getAttribute(IDLTKTestingConstants.ENGINE_ID_ATR, "");
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (id == null && id.length() == 0) {
			handleDetectButtonSelected();
		} else {
//			this.engineType.select(0);
			for (int i = 0; i < engines.length; i++) {
				if (engines[i].getId().equals(id)) {
					this.engineType.select(i);
				}
			}
		}
	}
}
