/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.launchConfigurations;

 
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.StringVariableSelectionDialog;
import org.eclipse.dltk.debug.ui.actions.ControlAccessibleListener;
import org.eclipse.dltk.debug.ui.messages.DLTKLaunchMessages;
import org.eclipse.dltk.internal.debug.ui.ScriptDebugImages;
import org.eclipse.dltk.internal.debug.ui.launcher.InterpreterArgumentsBlock;
import org.eclipse.dltk.internal.debug.ui.launcher.WorkingDirectoryBlock;
import org.eclipse.dltk.internal.launching.DLTKLaunchingPlugin;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


/**
 * A launch configuration tab that displays and edits program arguments,
 * Interpreter arguments, and working directory launch configuration attributes.
 * <p>
 * This class may be instantiated. This class may be subclassed by overloading createInterpreterArgsBlock and createWorkingDirBlock.
 * </p>
	 *
 */
public class ScriptArgumentsTab extends CommonScriptLaunchTab {
		
	// Program arguments widgets
	protected Label fPrgmArgumentsLabel;
	protected Text fPrgmArgumentsText;
	
	// Interpreter arguments widgets
	protected InterpreterArgumentsBlock fInterpreterArgumentsBlock;

	// Working directory
	protected WorkingDirectoryBlock fWorkingDirectoryBlock;
		
	protected static final String EMPTY_STRING = ""; //$NON-NLS-1$
		
	public ScriptArgumentsTab() {
		fInterpreterArgumentsBlock = createInterpreterArgsBlock();
		fWorkingDirectoryBlock = createWorkingDirBlock();
	}
	
	protected InterpreterArgumentsBlock createInterpreterArgsBlock() {
		return new InterpreterArgumentsBlock();
	}
	
	protected WorkingDirectoryBlock createWorkingDirBlock() {
		return new WorkingDirectoryBlock();
	}
	
	/**
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Font font = parent.getFont();
		Composite comp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, true);
		comp.setLayout(layout);
		comp.setFont(font);
		
		GridData gd = new GridData(GridData.FILL_BOTH);
		comp.setLayoutData(gd);
		setControl(comp);
		setHelpContextId();
		
		Group group = new Group(comp, SWT.NONE);
		group.setFont(font);
		layout = new GridLayout();
		group.setLayout(layout);
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		String controlName = (DLTKLaunchMessages.ArgumentsTab__Program_arguments__5); 
		group.setText(controlName);
		
		fPrgmArgumentsText = new Text(group, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL);
		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 40;
		gd.widthHint = 100;
		fPrgmArgumentsText.setLayoutData(gd);
		fPrgmArgumentsText.setFont(font);
		fPrgmArgumentsText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent evt) {
				updateLaunchConfigurationDialog();
			}
		});
		ControlAccessibleListener.addListener(fPrgmArgumentsText, group.getText());
		
		String buttonLabel = DLTKLaunchMessages.ArgumentsTab_5;  
		Button pgrmArgVariableButton = createPushButton(group, buttonLabel, null); 
		pgrmArgVariableButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		pgrmArgVariableButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				StringVariableSelectionDialog dialog = new StringVariableSelectionDialog(getShell());
				dialog.open();
				String variable = dialog.getVariableExpression();
				if (variable != null) {
                    fPrgmArgumentsText.insert(variable);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			
		});
		
		if (fInterpreterArgumentsBlock != null)
			fInterpreterArgumentsBlock.createControl(comp);
	
		fWorkingDirectoryBlock.createControl(comp);		
	}
	
	/**
	 * Set the help context id for this launch config tab.  Subclasses may
	 * override this method.
	 */
	protected void setHelpContextId() {
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), IScriptDebugHelpContextIds.LAUNCH_CONFIGURATION_DIALOG_ARGUMENTS_TAB);		
	}
			
	/**
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#dispose()
	 */
	public void dispose() {
	}
		
	/**
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#isValid(ILaunchConfiguration)
	 */
	public boolean isValid(ILaunchConfiguration config) {
		return fWorkingDirectoryBlock.isValid(config);
	}

	/**
	 * Defaults are empty.
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(ILaunchConfigurationWorkingCopy)
	 */
	public void setDefaults(ILaunchConfigurationWorkingCopy config) {
		config.setAttribute(ScriptLaunchConfigurationConstants.ATTR_SCRIPT_ARGUMENTS, (String)null);
		if (fInterpreterArgumentsBlock != null)
			fInterpreterArgumentsBlock.setDefaults(config);
		fWorkingDirectoryBlock.setDefaults(config);
	}

	/**
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(ILaunchConfiguration)
	 */
	public void initializeFrom(ILaunchConfiguration configuration) {
		try {
			fPrgmArgumentsText.setText(configuration.getAttribute(ScriptLaunchConfigurationConstants.ATTR_SCRIPT_ARGUMENTS, "")); //$NON-NLS-1$
			if (fInterpreterArgumentsBlock != null)
				fInterpreterArgumentsBlock.initializeFrom(configuration);
			fWorkingDirectoryBlock.initializeFrom(configuration);
		} catch (CoreException e) {
			setErrorMessage(DLTKLaunchMessages.ArgumentsTab_Exception_occurred_reading_configuration___15 + e.getStatus().getMessage()); 
			DLTKLaunchingPlugin.log(e);
		}
	}

	/**
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(ILaunchConfigurationWorkingCopy)
	 */
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(ScriptLaunchConfigurationConstants.ATTR_SCRIPT_ARGUMENTS, 
				getAttributeValueFrom(fPrgmArgumentsText));
		if (fInterpreterArgumentsBlock != null)
			fInterpreterArgumentsBlock.performApply(configuration);
		fWorkingDirectoryBlock.performApply(configuration);
	}

	/**
	 * Returns the string in the text widget, or <code>null</code> if empty.
	 * 
	 * @return text or <code>null</code>
	 */
	protected String getAttributeValueFrom(Text text) {
		String content = text.getText().trim();
		if (content.length() > 0) {
			return content;
		}
		return null;
	}
	
	/**
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
	 */
	public String getName() {
		return DLTKLaunchMessages.ArgumentsTab__Arguments_16; 
	}	
	
	/**
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setLaunchConfigurationDialog(ILaunchConfigurationDialog)
	 */
	public void setLaunchConfigurationDialog(ILaunchConfigurationDialog dialog) {
		super.setLaunchConfigurationDialog(dialog);
		fWorkingDirectoryBlock.setLaunchConfigurationDialog(dialog);
		if (fInterpreterArgumentsBlock != null)
			fInterpreterArgumentsBlock.setLaunchConfigurationDialog(dialog);
	}	
	/**
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getErrorMessage()
	 */
	public String getErrorMessage() {
		String m = super.getErrorMessage();
		if (m == null) {
			return fWorkingDirectoryBlock.getErrorMessage();
		}
		return m;
	}

	/**
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getMessage()
	 */
	public String getMessage() {
		String m = super.getMessage();
		if (m == null) {
			return fWorkingDirectoryBlock.getMessage();
		}
		return m;
	}
	
	/**
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getImage()
	 */
	public Image getImage() {
		return ScriptDebugImages.get(ScriptDebugImages.IMG_VIEW_ARGUMENTS_TAB);
	}	

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#activated(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
	 */
	public void activated(ILaunchConfigurationWorkingCopy workingCopy) {
		fWorkingDirectoryBlock.initializeFrom(workingCopy);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#deactivated(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
	 */
	public void deactivated(ILaunchConfigurationWorkingCopy workingCopy) {
		// do nothing when deactivated
	}
}

