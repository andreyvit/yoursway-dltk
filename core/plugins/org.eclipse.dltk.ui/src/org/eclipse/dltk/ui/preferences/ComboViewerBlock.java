package org.eclipse.dltk.ui.preferences;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public abstract class ComboViewerBlock {

	private ComboViewer viewer;

	public ComboViewerBlock(Composite composite, GridData layout) {
		viewer = new ComboViewer(composite);
		viewer.getCombo().setLayoutData(layout);

		viewer.setLabelProvider(new LabelProvider() {
			public String getText(Object element) {
				return getObjectName(element);
			}
		});

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				selectedObjectChanged(getSelectedObject());
			}
		});
	}

	/**
	 * Initializes the ComboViewer
	 * 
	 * @param elements
	 *            viewer elements
	 */
	public void initialize(Object[] elements) {
		viewer.add(elements);

		String id = getSavedObjectId();
		Object selected = null;

		if (id == null || "".equals(id)) { //$NON-NLS-1$
			// no entry exists in the preference store for the pref key
			selected = getDefaultObject();
		} else {
			selected = getObjectById(id);

		}

		setSelectedObject(selected);
	}

	/**
	 * Returns the unique identifier for the saved object from the preference
	 * store.
	 */
	protected abstract String getSavedObjectId();

	/**
	 * Returns the name of the object that will be displayed in the drop-down
	 * seleector.
	 */
	protected abstract String getObjectName(Object element);

	/**
	 * Handle an object selection change.
	 * 
	 * <p>
	 * Subclasses should use this method to store the changed preference value.
	 * </p>
	 * 
	 * @param element newly selected element
	 */
	protected abstract void selectedObjectChanged(Object element);

	/**
	 * Returns an unique identifier for the given object.
	 */
	protected abstract String getObjectId(Object element);

	/**
	 * Returns the default object that will be automatically selected when no
	 * saved value in the preference store exists.
	 * 
	 * <p>
	 * Subclasses may return <code>null</code> if they do not wish to have an
	 * object initially selected.
	 * </p>
	 */
	protected abstract Object getDefaultObject();

	/**
	 * Returns the object represented by the specified id.
	 */
	protected abstract Object getObjectById(String id);

	private void setSelectedObject(Object element) {
		if (element != null) {
			viewer.setSelection(new StructuredSelection(element));
		}
	}

	private Object getSelectedObject() {
		IStructuredSelection selection = (IStructuredSelection) viewer
				.getSelection();
		if (selection != null) {
			return selection.getFirstElement();
		}

		return null;
	}
}
