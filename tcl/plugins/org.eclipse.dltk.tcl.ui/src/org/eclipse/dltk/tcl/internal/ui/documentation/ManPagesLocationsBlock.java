package org.eclipse.dltk.tcl.internal.ui.documentation;


import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.tcl.core.TclPlugin;
import org.eclipse.dltk.tcl.ui.TclPreferenceConstants;
import org.eclipse.dltk.ui.viewsupport.StorageLabelProvider;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;

 
/**
 * Control used to edit the libraries associated with a Interpreter install
 */
public class ManPagesLocationsBlock implements SelectionListener, ISelectionChangedListener {
	
	/**
	 * Attribute name for the last path used to open a file/directory chooser
	 * dialog.
	 */
	protected static final String LAST_PATH_SETTING = "LAST_PATH_SETTING"; //$NON-NLS-1$
	
	/**
	 * the prefix for dialog setting pertaining to this block
	 */
	protected static final String DIALOG_SETTINGS_PREFIX = "ManPagesLocationsBlock"; //$NON-NLS-1$
	
	protected boolean fInCallback = false;
	
	protected File fHome;
	
	//widgets
	protected ListViewer fLocationsViewer;
	private Button fUpButton;
	private Button fDownButton;
	private Button fRemoveButton;
	private Button fAddButton;
	protected Button fDefaultButton;

	private ManLocationsContentProvider fLocationsContentProvider;
	
	private PreferencePage fPage;
	
	private IPreferenceStore fStore;
	
	public ManPagesLocationsBlock(IPreferenceStore store, PreferencePage page) {
		fPage = page;
		fStore = store;
	}
	
	protected IBaseLabelProvider getLabelProvider () {
		return new StorageLabelProvider();
	}
		
	private class ManLocationsContentProvider implements IStructuredContentProvider {
		
		private Viewer fViewer;
		
		private File[] fLocations = new File[0];

		public Object[] getElements(Object inputElement) {
			return fLocations;
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			fViewer = viewer;
		}
		
		public void setLocations (File[] locs) {
			fLocations = new File[locs.length];
			for (int i = 0; i < locs.length; i++) {
				fLocations[i] = new File(locs[i].getAbsolutePath());
			}
			fViewer.refresh();
		}

		public File[] getLocations() {
			return fLocations;
		}
		
		/**
		 * Returns the list of locations in the given selection. 
		 */
		private Set getSelectedLocations(IStructuredSelection selection) {
			Set locs= new HashSet();
			for (Iterator iter= selection.iterator(); iter.hasNext();) {
				Object element= iter.next();
				if (element instanceof File) {
					locs.add(element);
				}
			}
			return locs;
		}
		
		/**
		 * Move the locations of the given selection up.
		 */
		public void up(IStructuredSelection selection) {
			Set locations= getSelectedLocations(selection);
			for (int i= 0; i < fLocations.length - 1; i++) {
				if (locations.contains(fLocations[i + 1])) {
					File temp= fLocations[i];
					fLocations[i]= fLocations[i + 1];
					fLocations[i + 1]= temp;
				}
			}
			fViewer.refresh();
			fViewer.setSelection(selection);
		}

		/**
		 * Move the locations of the given selection down.
		 */
		public void down(IStructuredSelection selection) {
			Set locations= getSelectedLocations(selection);
			for (int i= fLocations.length - 1; i > 0; i--) {
				if (locations.contains(fLocations[i - 1])) {
					File temp= fLocations[i];
					fLocations[i]= fLocations[i - 1];
					fLocations[i - 1]= temp;
				}
			}
			fViewer.refresh();
			fViewer.setSelection(selection);
		}

		/**
		 * Remove the locations contained in the given selection.
		 */
		public void remove(IStructuredSelection selection) {
			List newLocations = new ArrayList();
			for (int i = 0; i < fLocations.length; i++) {
				newLocations.add(fLocations[i]);
			}
			Iterator iterator = selection.iterator();
			while (iterator.hasNext()) {
				Object element = iterator.next();
				if (element instanceof File) {
					newLocations.remove(element);
				} 
			}
			fLocations = (File[]) newLocations.toArray(new File[newLocations.size()]);
			fViewer.refresh();
		}
		
		/**
		 * Add the given locations before the selection, or after the existing locations
		 * if the selection is empty.
		 */
		public void add(File[] locs, IStructuredSelection selection) {
			List newLocations = new ArrayList(fLocations.length + locs.length);
			for (int i = 0; i < fLocations.length; i++) {
				newLocations.add(fLocations[i]);
			}
			List toAdd = new ArrayList(locs.length);
			for (int i = 0; i < locs.length; i++) {
				toAdd.add(new File(locs[i].getAbsolutePath()));
			}
			if (selection.isEmpty()) {
				newLocations.addAll(toAdd);
			} else {
				Object element= selection.getFirstElement();
				File firstLoc = (File) element;			 
				int index = newLocations.indexOf(firstLoc);
				newLocations.addAll(index, toAdd);
			}
			fLocations= (File[]) newLocations.toArray(new File[newLocations.size()]);
			fViewer.refresh();
			fViewer.setSelection(new StructuredSelection(locs), true);
		}
		
	};
	
	/**
	 * Creates and returns the source lookup control.
	 * 
	 * @param parent the parent widget of this control
	 */
	public Control createControl(Composite parent) {
		Font font = parent.getFont();
		
		Composite comp = new Composite(parent, SWT.NONE);
		GridLayout topLayout = new GridLayout();
		topLayout.numColumns = 2;
		topLayout.marginHeight = 0;
		topLayout.marginWidth = 0;
		comp.setLayout(topLayout);		
		GridData gd = new GridData(GridData.FILL_BOTH);
		comp.setLayoutData(gd);
		
		fLocationsViewer= new ListViewer(comp);
		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 6;
		fLocationsViewer.getControl().setLayoutData(gd);
		fLocationsContentProvider= new ManLocationsContentProvider();
		fLocationsViewer.setContentProvider(fLocationsContentProvider);
		fLocationsViewer.setLabelProvider(getLabelProvider());
		fLocationsViewer.setInput(this);
		fLocationsViewer.addSelectionChangedListener(this);
		
		Composite pathButtonComp = new Composite(comp, SWT.NONE);
		GridLayout pathButtonLayout = new GridLayout();
		pathButtonLayout.marginHeight = 0;
		pathButtonLayout.marginWidth = 0;
		pathButtonComp.setLayout(pathButtonLayout);
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_FILL);
		pathButtonComp.setLayoutData(gd);
		pathButtonComp.setFont(font);
		
		fAddButton= createPushButton(pathButtonComp, "Add");
		fAddButton.addSelectionListener(this);
		
		fRemoveButton= createPushButton(pathButtonComp, "Remove");
		fRemoveButton.addSelectionListener(this);
		
		fUpButton= createPushButton(pathButtonComp, "Up");
		fUpButton.addSelectionListener(this);
		
		fDownButton= createPushButton(pathButtonComp, "Down");
		fDownButton.addSelectionListener(this);
		
		return comp;
	}

	
	/**
	 * Creates and returns a button 
	 * 
	 * @param parent parent widget
	 * @param label label
	 * @return Button
	 */
	protected Button createPushButton(Composite parent, String label) {
		Button button = new Button(parent, SWT.PUSH);
		button.setFont(parent.getFont());
		button.setText(label);
		setButtonLayoutData(button);
		return button;	
	}
	
	protected void setButtonLayoutData(Button button) {						
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		int widthHint = 100;
		Point minSize = button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
		data.widthHint = Math.max(widthHint, minSize.x);
		button.setLayoutData(data);
	}
	
	/**
	 * Create some empty space 
	 */
	protected void createVerticalSpacer(Composite comp, int colSpan) {
		Label label = new Label(comp, SWT.NONE);
		GridData gd = new GridData();
		gd.horizontalSpan = colSpan;
		label.setLayoutData(gd);
	}	
	
	/**
	 * Updates buttons and status based on current libraries
	 */
	public void update() {
		updateButtons();
		IStatus status = Status.OK_STATUS;
		
		File[] locs = fLocationsContentProvider.getLocations();
		for (int i = 0; i < locs.length; i++) {
			IStatus st = validateLocation (locs[i]);
			if (!st.isOK()) {
				status = st;
				break;
			}
		}
		
		fLocationsViewer.refresh();
				
		updatePageStatus (status);
	}
	
	public void setDefaults () {
		String res = fStore.getDefaultString(TclPreferenceConstants.DOC_MAN_PAGES_LOCATIONS);
		fStore.setValue(TclPreferenceConstants.DOC_MAN_PAGES_LOCATIONS, res);
		initialize();
	}
	
	protected IStatus validateLocation (File location) {
		if (location.isDirectory())
			return Status.OK_STATUS;
		else
			return new Status(Status.ERROR, TclPlugin.PLUGIN_ID, 0, "Location should be a directory!", null);
	}
	
	protected void updatePageStatus (IStatus status) {
		if (fPage == null)
			return;
		fPage.setValid(status.isOK());
		if (!status.isOK())
			fPage.setErrorMessage(status.getMessage());
		else
			fPage.setErrorMessage(null);
	}
	
	public void initialize () {
		String value = fStore.getString(TclPreferenceConstants.DOC_MAN_PAGES_LOCATIONS);
		String[] names = value.split(">");
		ArrayList files = new ArrayList (names.length);
		for (int i = 0; i < names.length; i++) {			
			if (names[i].trim().length() == 0)
				continue;
			File f = new File (names[i]);
			files.add(f);
 		}
		fLocationsContentProvider.setLocations((File[])files.toArray(new File[files.size()]));
		update();
	}
	
	/**
	 * Saves settings
	 */
	public void performApply() {		
		File[] locs = fLocationsContentProvider.getLocations();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < locs.length; i++) {
			buf.append(locs[i].getAbsolutePath());
			buf.append(">");
		}
		String value = buf.toString();
		fStore.setValue(TclPreferenceConstants.DOC_MAN_PAGES_LOCATIONS, value);
	}	
	
	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetSelected(SelectionEvent e) {
		Object source= e.getSource();
		if (source == fUpButton) {
			fLocationsContentProvider.up((IStructuredSelection) fLocationsViewer.getSelection());
		} else if (source == fDownButton) {
			fLocationsContentProvider.down((IStructuredSelection) fLocationsViewer.getSelection());
		} else if (source == fRemoveButton) {
			fLocationsContentProvider.remove((IStructuredSelection) fLocationsViewer.getSelection());
		} else if (source == fAddButton) {
			add((IStructuredSelection) fLocationsViewer.getSelection());
		} 
		
		update();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetDefaultSelected(SelectionEvent e) {}

	
	private void add(IStructuredSelection selection) { 
		File locs = add();
		if (locs == null)
			return;
		fLocationsContentProvider.add(new File[] {locs}, selection);
		update ();
	}
	
	/**
	 * Open the file selection dialog, and add the return locations.
	 */
	protected File add() {
		DirectoryDialog dialog = new DirectoryDialog(fLocationsViewer.getControl().getShell());
		dialog.setMessage("Select directory directly containing .html files with man pages");
		String result = dialog.open();
		if (result != null) {;
			File file = new File(result);
			if (file != null) {
				if (!validateLocation(file).isOK()) {
					ErrorDialog errDlg = new ErrorDialog(fLocationsViewer.getControl().getShell(), "Error", "It is not correct location", validateLocation(file), 0);
					errDlg.open();
					return null;
				}
				return file;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		updateButtons();
	}

	/**
	 * Refresh the enable/disable state for the buttons.
	 */
	private void updateButtons() {
		IStructuredSelection selection = (IStructuredSelection) fLocationsViewer.getSelection();
		fRemoveButton.setEnabled(!selection.isEmpty());
		boolean enableUp = true, 
				enableDown = true;
		Object[] libraries = fLocationsContentProvider.getElements(null);
		if (selection.isEmpty() || libraries.length == 0) {
			enableUp = false;
			enableDown = false;
		} else {
			Object first = libraries[0];
			Object last = libraries[libraries.length - 1];
			for (Iterator iter= selection.iterator(); iter.hasNext();) {
				Object element= iter.next();
				Object lib;
				lib = element;
				if (lib == first) {
					enableUp = false;
				}
				if (lib == last) {
					enableDown = false;
				}
			}
		}
		fUpButton.setEnabled(enableUp);
		fDownButton.setEnabled(enableDown);		
	}
	
		
}
