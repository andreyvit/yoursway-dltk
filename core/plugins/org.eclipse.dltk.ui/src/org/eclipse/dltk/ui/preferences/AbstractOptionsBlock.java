package org.eclipse.dltk.ui.preferences;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.internal.ui.dialogs.StatusInfo;
import org.eclipse.dltk.internal.ui.preferences.OptionsConfigurationBlock;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public abstract class AbstractOptionsBlock extends OptionsConfigurationBlock {

	public AbstractOptionsBlock(IStatusChangeListener context,
			IProject project, PreferenceKey[] allKeys,
			IWorkbenchPreferenceContainer container) {
		super(context, project, allKeys, container);
	}

	public Control createContents(Composite parent) {
		setShell(parent.getShell());		
		return createOptionsBlock(parent);
	}

	protected abstract Control createOptionsBlock(Composite parent);
	
	protected final void bindControl(Text textBox, PreferenceKey key) {	
		String value = getValue(key);	
		
		if (value != null) {
			textBox.setText(value);
		}

		textBox.setData(key);
		textBox.addModifyListener(getTextModifyListener());
		
		fTextBoxes.add(textBox);
	}

    protected String[] getFullBuildDialogStrings(boolean workspaceSettings)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    protected final boolean isProjectPreferencePage() {
    	return fProject != null;
    }
	
	/*
     * @see org.eclipse.dltk.ui.preferences.OptionsConfigurationBlock#validateSettings(org.eclipse.dltk.ui.preferences.OptionsConfigurationBlock.Key, java.lang.String, java.lang.String)
     */
    protected final void validateSettings(PreferenceKey changedKey, String oldValue, String newValue)
    {
    	IStatus status = validate(null, newValue, null);
    	fContext.statusChanged(status);
    }
    
    /**
     * Validate any changed settings 
     * 
     * <p>Default implementation returns an <code>OK</code> status.</p>
     * 
     * @param changedKey changed key
     * @param oldValue old value
     * @param newValue new value
     * 
     * @return IStatus object representing if the new value is valid
     */
    protected IStatus validate(PreferenceKey changedKey, String oldValue, String newValue) {
    	return new StatusInfo();
    }
  
}
