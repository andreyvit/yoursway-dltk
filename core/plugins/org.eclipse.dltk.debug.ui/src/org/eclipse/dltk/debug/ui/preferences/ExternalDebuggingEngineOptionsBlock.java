package org.eclipse.dltk.debug.ui.preferences;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.FieldValidators;
import org.eclipse.dltk.ui.preferences.IFieldValidator;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

/**
 * Options block for external debugging engine that require the user to specify
 * their location on disk.
 */
public abstract class ExternalDebuggingEngineOptionsBlock extends
		AbstractOptionsBlock {

	Text enginePath;

	public ExternalDebuggingEngineOptionsBlock(IStatusChangeListener context,
			IProject project, PreferenceKey[] allKeys,
			IWorkbenchPreferenceContainer container) {
		super(context, project, allKeys, container);
	}

	/**
	 * Add a link to an external site where the debugging engine can be
	 * downloaded from
	 * 
	 * @param parent parent composite
	 * @param text link text
	 * @param url link url
	 */
	protected void addDownloadLink(Composite parent, String text,
			final String url) {
		Link link = new Link(parent, SWT.NONE);
		link.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				openExternalUrl(url);
			}
		});

		link.setText(text);
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.AbstractOptionsBlock#createOptionsBlock(org.eclipse.swt.widgets.Composite)
	 */
	protected final Control createOptionsBlock(Composite parent) {
		final Composite composite = SWTFactory.createComposite(parent, parent
				.getFont(), 1, 1, GridData.FILL);
		createEngineBlock(composite);

		return composite;
	}

	/**
	 * Returns the debugging engine path preference key.
	 */
	protected abstract PreferenceKey getDebuggingEnginePathKey();

	/**
	 * Creates the engine path block.
	 * 
	 * <p>
	 * Sub-classes are free to override if they wish to make additional
	 * contributions to the parent composite to provide additional options for
	 * their specific engine.
	 * </p>
	 * 
	 * @param parent
	 *            parent composite
	 */
	protected void createEngineBlock(final Composite parent) {
		final Group group = SWTFactory.createGroup(parent,
				ScriptDebugPreferencesMessages.ExternalEngineGroup, 3, 1,
				GridData.FILL_HORIZONTAL);

		// Engine path
		SWTFactory.createLabel(group, ScriptDebugPreferencesMessages.PathLabel,
				1);

		enginePath = SWTFactory.createText(group, SWT.BORDER, 1, "");
		bindControl(enginePath, getDebuggingEnginePathKey());

		// Browse
		final Button button = SWTFactory.createPushButton(group,
				ScriptDebugPreferencesMessages.BrowseEnginePath, null);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(parent.getShell(), SWT.OPEN);
				String file = dialog.open();
				if (file != null) {
					enginePath.setText(file);
				}
			}
		});
	}

	protected void openExternalUrl(String url) {
		try {
			final IWebBrowser browser = PlatformUI.getWorkbench()
					.getBrowserSupport().getExternalBrowser();
			browser.openURL(new URL(url));
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected IStatus validate(PreferenceKey changedKey, String oldValue,
			String newValue) {
		IFieldValidator validator = FieldValidators.PATH_VALIDATOR;
		return validator.validate(oldValue);
	}

}
