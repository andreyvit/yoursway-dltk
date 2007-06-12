/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.launchConfigurations;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ScriptCommonTab extends CommonTab {
	
	public void createControl(Composite parent) {
		super.createControl(parent);
		
		/*Composite comp = new Composite(parent, SWT.NONE);
		setControl(comp);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), IDebugHelpContextIds.LAUNCH_CONFIGURATION_DIALOG_COMMON_TAB);
		comp.setLayout(new GridLayout(2, true));
		comp.setFont(parent.getFont());
		
		createSharedConfigComponent(comp);
		createFavoritesComponent(comp);
		createEncodingComponent(comp);
		createOutputCaptureComponent(comp);
		createLaunchInBackgroundComponent(comp);*/
		createUseScriptConsoleComponent((Composite)getControl());
	}
	
	private Button myButtom;
	
	private void createUseScriptConsoleComponent(Composite parent){
		myButtom = createCheckButton(parent, "Use specific DLTK Script console"); 
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		myButtom.setLayoutData(data);
		myButtom.setFont(parent.getFont());
		myButtom.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				updateLaunchConfigurationDialog();
			}
		});	
	}
	
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		super.performApply(configuration);		
		
		if (myButtom.getSelection()){
			configuration.setAttribute(DebugPlugin.ATTR_CAPTURE_OUTPUT, false);
			configuration.setAttribute("script_console_output", true);
		}		
	}	
}
