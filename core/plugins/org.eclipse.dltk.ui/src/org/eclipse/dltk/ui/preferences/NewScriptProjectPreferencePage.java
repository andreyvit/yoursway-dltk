/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
// AW
package org.eclipse.dltk.ui.preferences;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.internal.core.BuildpathEntry;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.internal.ui.dialogs.StatusInfo;
import org.eclipse.dltk.internal.ui.dialogs.StatusUtil;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

	
/*
 * The page for defaults for classpath entries in newscriptprojects.
 * See PreferenceConstants to access or change these values through public API.
 */
public abstract class NewScriptProjectPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	public static final String ID= "com.xore.dltk.ui.preferences.BuildPathPreferencePage"; //$NON-NLS-1$
	
	private static final String SRCBIN_FOLDERS_IN_NEWPROJ= PreferenceConstants.SRCBIN_FOLDERS_IN_NEWPROJ;
	private static final String SRC_SRCNAME= PreferenceConstants.SRC_SRCNAME;	
	
	private static String fgDefaultEncoding= System.getProperty("file.encoding"); //$NON-NLS-1$

	public abstract IBuildpathEntry[] getDefaultLanguageLibrary();			
	
	// InterpreterEnvironment Entry
	
	public static String decodeInterpreterEnvironmentLibraryDescription(String encoded) {
		int end= encoded.indexOf(' ');
		if (end != -1) {
			return decode(encoded.substring(0, end));
		}
		return ""; //$NON-NLS-1$
	}
	
	private static String decode(String str) {
		try {
			return URLDecoder.decode(str, fgDefaultEncoding);
		} catch (UnsupportedEncodingException e) {
			DLTKUIPlugin.log(e);
		}
		return ""; //$NON-NLS-1$
	}
	
	private static String encode(String str) {
		try {
			return URLEncoder.encode(str, fgDefaultEncoding);
		} catch (UnsupportedEncodingException e) {
			DLTKUIPlugin.log(e);
		}
		return ""; //$NON-NLS-1$
	}	
	
	public static IBuildpathEntry[] decodeInterpreterEnvironmentLibraryBuildpathEntries(String encoded) {
		StringTokenizer tok= new StringTokenizer(encoded, " "); //$NON-NLS-1$
		ArrayList res= new ArrayList();
		while (tok.hasMoreTokens()) {
			try {
				tok.nextToken(); // desc: ignore
				int kind= Integer.parseInt(tok.nextToken());
				IPath path= decodePath(tok.nextToken());				
				boolean isExported= Boolean.valueOf(tok.nextToken()).booleanValue();
				switch (kind) {
					case IBuildpathEntry.BPE_SOURCE:
						res.add(DLTKCore.newSourceEntry(path));
						break;
					case IBuildpathEntry.BPE_LIBRARY:
						res.add(DLTKCore.newLibraryEntry(path, isExported));
						break;
					case IBuildpathEntry.BPE_PROJECT:
						res.add(DLTKCore.newProjectEntry(path, isExported));
						break;
					case IBuildpathEntry.BPE_CONTAINER:
						res.add(DLTKCore.newContainerEntry(path, isExported));
						break;
				}								
			} catch (NumberFormatException e) {
				String message= PreferencesMessages.NewScriptProjectPreferencePage_error_decode; 
				DLTKUIPlugin.log(new Status(IStatus.ERROR, DLTKUIPlugin.PLUGIN_ID, IStatus.ERROR, message, e));
			} catch (NoSuchElementException e) {
				String message= PreferencesMessages.NewScriptProjectPreferencePage_error_decode; 
				DLTKUIPlugin.log(new Status(IStatus.ERROR, DLTKUIPlugin.PLUGIN_ID, IStatus.ERROR, message, e));
			}
		}
		return (IBuildpathEntry[]) res.toArray(new IBuildpathEntry[res.size()]);	
	}
	
	
	public static String encodeInterpreterEnvironmentLibrary(String desc, IBuildpathEntry[] cpentries) {
		StringBuffer buf= new StringBuffer();
		for (int i= 0; i < cpentries.length; i++) {
			IBuildpathEntry entry= cpentries[i];
			buf.append(encode(desc));
			buf.append(' ');
			buf.append(entry.getEntryKind());
			buf.append(' ');
			buf.append(encodePath(entry.getPath()));
			buf.append(' ');			
			buf.append(entry.isExported());
			buf.append(' ');
		}
		return buf.toString();
	}
	
	private static String encodePath(IPath path) {
		if (path == null) {
			return "#"; //$NON-NLS-1$
		} else if (path.isEmpty()) {
			return "&"; //$NON-NLS-1$
		} else {
			return encode(path.toPortableString());
		}
	}
	
	private static IPath decodePath(String str) {
		if ("#".equals(str)) { //$NON-NLS-1$
			return null;
		} else if ("&".equals(str)) { //$NON-NLS-1$
			return Path.EMPTY;
		} else {
			return Path.fromPortableString(decode(str));
		}
	}
	
	
	private ArrayList fCheckBoxes;
	private ArrayList fRadioButtons;
	private ArrayList fTextControls;
	
	private SelectionListener fSelectionListener;
	private ModifyListener fModifyListener;
		
	private Text fSrcFolderNameText;

	//private Combo fInterpreterEnvironmentCombo;

	private Button fProjectAsSourceFolder;
	private Button fFoldersAsSourceFolder;

	private Label fSrcFolderNameLabel;

	public NewScriptProjectPreferencePage() {
		super();
		setPreferenceStore(DLTKUIPlugin.getDefault().getPreferenceStore());
		setDescription(PreferencesMessages.NewScriptProjectPreferencePage_description); 
	
		// title used when opened programatically
		setTitle(PreferencesMessages.NewScriptProjectPreferencePage_title); 
		
		fRadioButtons= new ArrayList();
		fCheckBoxes= new ArrayList();
		fTextControls= new ArrayList();
		
		fSelectionListener= new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {}

			public void widgetSelected(SelectionEvent e) {
				controlChanged(e.widget);
			}
		};
		
		fModifyListener= new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				controlModified(e.widget);
			}
		};
		
	}

	public static void initDefaults(IPreferenceStore store) {
		store.setDefault(SRCBIN_FOLDERS_IN_NEWPROJ, false);
		store.setDefault(SRC_SRCNAME, "src"); //$NON-NLS-1$		
		
//		store.setDefault(CLASSPATH_InterpreterEnvironmentLIBRARY_LIST, getDefaultInterpreterEnvironmentLibraries());
//		store.setDefault(CLASSPATH_InterpreterEnvironmentLIBRARY_INDEX, 0); 
	}
	
	/*
	 * @see IWorkbenchPreferencePage#init(IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}		
	
	/**
	 * @see PreferencePage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		super.createControl(parent);
		if (DLTKCore.DEBUG) {
			System.err.println("Add help support here...");
		}		
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), IDLTKHelpContextIds.NEW_JAVA_PROJECT_PREFERENCE_PAGE);
	}	


	private Button addRadioButton(Composite parent, String label, String key, String value, int indent) { 
		GridData gd= new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan= 2;
		gd.horizontalIndent= indent;
		
		Button button= new Button(parent, SWT.RADIO);
		button.setText(label);
		button.setData(new String[] { key, value });
		button.setLayoutData(gd);

		button.setSelection(value.equals(getPreferenceStore().getString(key)));
		
		fRadioButtons.add(button);
		return button;
	}
	
	private Text addTextControl(Composite parent, Label labelControl, String key, int indent) {
		GridData gd= new GridData();
		gd.horizontalIndent= indent;
		
		labelControl.setLayoutData(gd);
		
		gd= new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint= convertWidthInCharsToPixels(30);
		
		Text text= new Text(parent, SWT.SINGLE | SWT.BORDER);
		text.setText(getPreferenceStore().getString(key));
		text.setData(key);
		text.setLayoutData(gd);
		
		fTextControls.add(text);
		return text;
	}	
	
	
	protected Control createContents(Composite parent) {
		initializeDialogUnits(parent);
		
		Composite result= new Composite(parent, SWT.NONE);
		GridLayout layout= new GridLayout();
		layout.marginHeight= convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.marginWidth= 0;
		layout.verticalSpacing= convertVerticalDLUsToPixels(10);
		layout.horizontalSpacing= convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		layout.numColumns= 2;
		result.setLayout(layout);
		
		GridData gd= new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan= 2;
		
		Group sourceFolderGroup= new Group(result, SWT.NONE);
		layout= new GridLayout();
		layout.numColumns= 2;
		sourceFolderGroup.setLayout(layout);
		sourceFolderGroup.setLayoutData(gd);
		sourceFolderGroup.setText(PreferencesMessages.NewScriptProjectPreferencePage_sourcefolder_label); 
		
		int indent= 0;
		
		fProjectAsSourceFolder= addRadioButton(sourceFolderGroup, PreferencesMessages.NewScriptProjectPreferencePage_sourcefolder_project, SRCBIN_FOLDERS_IN_NEWPROJ, IPreferenceStore.FALSE, indent); 
		fProjectAsSourceFolder.addSelectionListener(fSelectionListener);

		fFoldersAsSourceFolder= addRadioButton(sourceFolderGroup, PreferencesMessages.NewScriptProjectPreferencePage_sourcefolder_folder, SRCBIN_FOLDERS_IN_NEWPROJ, IPreferenceStore.TRUE, indent); 
		fFoldersAsSourceFolder.addSelectionListener(fSelectionListener);
		
		indent= convertWidthInCharsToPixels(4);

		fSrcFolderNameLabel= new Label(sourceFolderGroup, SWT.NONE);
		fSrcFolderNameLabel.setText(PreferencesMessages.NewScriptProjectPreferencePage_folders_src); 
		fSrcFolderNameText= addTextControl(sourceFolderGroup, fSrcFolderNameLabel, SRC_SRCNAME, indent); 
		fSrcFolderNameText.addModifyListener(fModifyListener);

//		String[] InterpreterEnvironmentNames= getInterpreterEnvironmentNames();
//		if (InterpreterEnvironmentNames.length > 0) {
//			Label InterpreterEnvironmentSelectionLabel= new Label(result, SWT.NONE);
//			InterpreterEnvironmentSelectionLabel.setText(PreferencesMessages.NewScriptProjectPreferencePage_InterpreterEnvironmentlibrary_label); 
//			InterpreterEnvironmentSelectionLabel.setLayoutData(new GridData());
//		
//			int index= getPreferenceStore().getInt(CLASSPATH_InterpreterEnvironmentLIBRARY_INDEX);
//			fInterpreterEnvironmentCombo= new Combo(result, SWT.READ_ONLY);
//			fInterpreterEnvironmentCombo.setItems(InterpreterEnvironmentNames);
//			fInterpreterEnvironmentCombo.select(index);
//			fInterpreterEnvironmentCombo.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
//		}
					
		validateFolders();
	
		Dialog.applyDialogFont(result);
		return result;
	}
	
	private void validateFolders() {
		boolean useFolders= fFoldersAsSourceFolder.getSelection();
		
		fSrcFolderNameText.setEnabled(useFolders);		
		fSrcFolderNameLabel.setEnabled(useFolders);			
		if (useFolders) {
			String srcName= fSrcFolderNameText.getText();			
			if (srcName.length() == 0) {
				updateStatus(new StatusInfo(IStatus.ERROR,  PreferencesMessages.NewScriptProjectPreferencePage_folders_error_namesempty)); 
				return;
			}
			IWorkspace workspace= DLTKUIPlugin.getWorkspace();
			IProject dmy= workspace.getRoot().getProject("project"); //$NON-NLS-1$
			
			IStatus status;
			IPath srcPath= dmy.getFullPath().append(srcName);
			if (srcName.length() != 0) {
				status= workspace.validatePath(srcPath.toString(), IResource.FOLDER);
				if (!status.isOK()) {
					String message= Messages.format(PreferencesMessages.NewScriptProjectPreferencePage_folders_error_invalidsrcname, status.getMessage()); 
					updateStatus(new StatusInfo(IStatus.ERROR, message));
					return;
				}
			}			
			IBuildpathEntry entry= DLTKCore.newSourceEntry(srcPath);
			status= BuildpathEntry.validateBuildpath(DLTKCore.create(dmy), new IBuildpathEntry[] { entry });
			if (!status.isOK()) {
				String message= PreferencesMessages.NewScriptProjectPreferencePage_folders_error_invalidcp; 
				updateStatus(new StatusInfo(IStatus.ERROR, message));
				return;
			}
		}
		updateStatus(new StatusInfo()); // set to OK
	}
		
	private void updateStatus(IStatus status) {
		setValid(!status.matches(IStatus.ERROR));
		StatusUtil.applyToStatusLine(this, status);
	}		
	
	private void controlChanged(Widget widget) {
		if (widget == fFoldersAsSourceFolder || widget == fProjectAsSourceFolder) {
			validateFolders();
		}
	}
	
	private void controlModified(Widget widget) {
		if (widget == fSrcFolderNameText) {
			validateFolders();
		}
	}	
	
	/*
	 * @see PreferencePage#performDefaults()
	 */
	protected void performDefaults() {
		IPreferenceStore store= getPreferenceStore();
		for (int i= 0; i < fCheckBoxes.size(); i++) {
			Button button= (Button) fCheckBoxes.get(i);
			String key= (String) button.getData();
			button.setSelection(store.getDefaultBoolean(key));
		}
		for (int i= 0; i < fRadioButtons.size(); i++) {
			Button button= (Button) fRadioButtons.get(i);
			String[] info= (String[]) button.getData();
			button.setSelection(info[1].equals(store.getDefaultString(info[0])));
		}
		for (int i= 0; i < fTextControls.size(); i++) {
			Text text= (Text) fTextControls.get(i);
			String key= (String) text.getData();
			text.setText(store.getDefaultString(key));
		}
//		if (fInterpreterEnvironmentCombo != null) {
//			fInterpreterEnvironmentCombo.select(store.getDefaultInt(CLASSPATH_InterpreterEnvironmentLIBRARY_INDEX));
//		}
		
		validateFolders();
		super.performDefaults();
	}

	/*
	 * @see IPreferencePage#performOk()
	 */
	public boolean performOk() {
		IPreferenceStore store= getPreferenceStore();
		for (int i= 0; i < fCheckBoxes.size(); i++) {
			Button button= (Button) fCheckBoxes.get(i);
			String key= (String) button.getData();
			store.setValue(key, button.getSelection());
		}
		for (int i= 0; i < fRadioButtons.size(); i++) {
			Button button= (Button) fRadioButtons.get(i);
			if (button.getSelection()) {
				String[] info= (String[]) button.getData();
				store.setValue(info[0], info[1]);
			}
		}
		for (int i= 0; i < fTextControls.size(); i++) {
			Text text= (Text) fTextControls.get(i);
			String key= (String) text.getData();
			store.setValue(key, text.getText());
		}
		
//		if (fInterpreterEnvironmentCombo != null) {
//			store.setValue(CLASSPATH_InterpreterEnvironmentLIBRARY_INDEX, fInterpreterEnvironmentCombo.getSelectionIndex());
//		}
		
		DLTKUIPlugin.getDefault().savePluginPreferences();
		return super.performOk();
	}
	
//	private String[] getInterpreterEnvironmentNames() {
//		String prefString= getPreferenceStore().getString(CLASSPATH_InterpreterEnvironmentLIBRARY_LIST);
//		ArrayList list= new ArrayList();
//		StringTokenizer tok= new StringTokenizer(prefString, ";"); //$NON-NLS-1$
//		while (tok.hasMoreTokens()) {
//			list.add(decodeInterpreterEnvironmentLibraryDescription(tok.nextToken()));
//		}
//		return (String[]) list.toArray(new String[list.size()]);
//	}

}


