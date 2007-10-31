/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.ui.browsing;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.internal.ui.actions.MultiActionGroup;
import org.eclipse.dltk.internal.ui.filters.LibraryFilter;
import org.eclipse.dltk.internal.ui.filters.NonScriptElementFilter;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.ModelElementSorter;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.dltk.ui.actions.SelectAllAction;
import org.eclipse.dltk.ui.viewsupport.DecoratingModelLabelProvider;
import org.eclipse.dltk.ui.viewsupport.ProblemTableViewer;
import org.eclipse.dltk.ui.viewsupport.ProblemTreeViewer;
import org.eclipse.dltk.ui.viewsupport.ScriptUILabelProvider;
import org.eclipse.dltk.ui.viewsupport.StatusBarUpdater;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;

public class PackagesView extends ScriptBrowsingPart {

	private static final String TAG_VIEW_STATE = ".viewState"; //$NON-NLS-1$
	private static final int LIST_VIEW_STATE = 0;
	private static final int TREE_VIEW_STATE = 1;

	private static class StatusBarUpdater4LogicalPackage extends
			StatusBarUpdater {

		private StatusBarUpdater4LogicalPackage(
				IStatusLineManager statusLineManager) {
			super(statusLineManager);
		}

		protected String formatMessage(ISelection sel) {
			if (sel instanceof IStructuredSelection) {
				IStructuredSelection selection = (IStructuredSelection) sel;
				int nElements = selection.size();
				Object elem = selection.getFirstElement();
				if (nElements == 1 && (elem instanceof LogicalPackage))
					return formatLogicalPackageMessage((LogicalPackage) elem);
			}
			return super.formatMessage(sel);
		}

		private String formatLogicalPackageMessage(LogicalPackage logicalPackage) {
			IScriptFolder[] fragments = logicalPackage.getScriptFolders();
			StringBuffer buf = new StringBuffer(logicalPackage.getElementName());
			buf.append(ScriptElementLabels.CONCAT_STRING);
			String message = ""; //$NON-NLS-1$
			boolean firstTime = true;
			for (int i = 0; i < fragments.length; i++) {
				IScriptFolder fragment = fragments[i];
				IModelElement element = fragment.getParent();
				if (element instanceof IProjectFragment) {
					IProjectFragment root = (IProjectFragment) element;
					String label = ScriptElementLabels
							.getDefault()
							.getElementLabel(
									root,
									ScriptElementLabels.DEFAULT_QUALIFIED
											| ScriptElementLabels.ROOT_QUALIFIED);
					if (firstTime) {
						buf.append(label);
						firstTime = false;
					} else
						message = Messages.format(
								ScriptBrowsingMessages.StatusBar_concat,
								new String[] { message, label });
				}
			}
			buf.append(message);
			return buf.toString();
		}
	}

	private SelectAllAction fSelectAllAction;

	private int fCurrViewState;

	private PackageViewerWrapper fWrappedViewer;

	private MultiActionGroup fSwitchActionGroup;
	private boolean fLastInputWasProject;

	/**
	 * Adds filters the viewer of this part.
	 */
	protected void addFilters() {
		super.addFilters();
		getViewer().addFilter(createNonJavaElementFilter());
		getViewer().addFilter(new LibraryFilter());
	}

	/**
	 * Creates new NonJavaElementFilter and overrides method select to allow for
	 * LogicalPackages.
	 * 
	 * @return NonJavaElementFilter
	 */
	protected NonScriptElementFilter createNonJavaElementFilter() {
		return new NonScriptElementFilter() {
			public boolean select(Viewer viewer, Object parent, Object element) {
				return ((element instanceof IModelElement)
						|| (element instanceof LogicalPackage) || (element instanceof IFolder));
			}
		};
	}

	public void init(IViewSite site, IMemento memento) throws PartInitException {
		super.init(site, memento);
		// this must be created before all actions and filters
		fWrappedViewer = new PackageViewerWrapper();
		restoreLayoutState(memento);
	}

	private void restoreLayoutState(IMemento memento) {
		if (memento == null) {
			// read state from the preference store
			IPreferenceStore store = DLTKUIPlugin.getDefault()
					.getPreferenceStore();
			fCurrViewState = store.getInt(this.getViewSite().getId()
					+ TAG_VIEW_STATE);
		} else {
			// restore from memento
			Integer integer = memento.getInteger(this.getViewSite().getId()
					+ TAG_VIEW_STATE);
			if ((integer == null) || !isValidState(integer.intValue())) {
				fCurrViewState = LIST_VIEW_STATE;
			} else
				fCurrViewState = integer.intValue();
		}
	}

	private boolean isValidState(int state) {
		return (state == LIST_VIEW_STATE) || (state == TREE_VIEW_STATE);
	}

	/*
	 * @see org.eclipse.ui.IViewPart#saveState(org.eclipse.ui.IMemento)
	 */
	public void saveState(IMemento memento) {
		super.saveState(memento);
		memento.putInteger(this.getViewSite().getId() + TAG_VIEW_STATE,
				fCurrViewState);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.internal.ui.browsing.JavaBrowsingPart#createViewer(org.eclipse.swt.widgets.Composite)
	 */
	protected StructuredViewer createViewer(Composite parent) {
		// Creates the viewer of this part dependent on the current layout.
		StructuredViewer viewer;
		if (isInListState())
			viewer = createTableViewer(parent);
		else
			viewer = createTreeViewer(parent);

		fWrappedViewer.setViewer(viewer);
		return fWrappedViewer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.internal.ui.browsing.JavaBrowsingPart#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class key) {
		// if (key == IShowInTargetList.class) {
		// return new IShowInTargetList() {
		// public String[] getShowInTargetIds() {
		// return new String[] { DLTKUIPlugin.ID_PACKAGES,
		// IPageLayout.ID_RES_NAV };
		// }
		// };
		// }
		return super.getAdapter(key);
	}

	protected boolean isInListState() {
		return fCurrViewState == LIST_VIEW_STATE;
	}

	private ProblemTableViewer createTableViewer(Composite parent) {
		return new PackagesViewTableViewer(parent, SWT.MULTI);
	}

	private ProblemTreeViewer createTreeViewer(Composite parent) {
		return new PackagesViewTreeViewer(parent, SWT.MULTI);
	}

	/**
	 * Overrides the createContentProvider from JavaBrowsingPart Creates the
	 * content provider of this part.
	 */
	protected IContentProvider createContentProvider() {
		if (isInListState())
			return new PackagesViewFlatContentProvider(fWrappedViewer
					.getViewer());
		else
			return new PackagesViewHierarchicalContentProvider(fWrappedViewer
					.getViewer());
	}

	protected ScriptUILabelProvider createLabelProvider() {
		if (isInListState())
			return createListLabelProvider();
		else
			return createTreeLabelProvider();
	}

	private ScriptUILabelProvider createTreeLabelProvider() {
		return new PackagesViewLabelProvider(
				PackagesViewLabelProvider.HIERARCHICAL_VIEW_STATE);
	}

	private ScriptUILabelProvider createListLabelProvider() {
		return new PackagesViewLabelProvider(
				PackagesViewLabelProvider.FLAT_VIEW_STATE);
	}

	/**
	 * Returns the context ID for the Help system
	 * 
	 * @return the string used as ID for the Help context
	 */
	protected String getHelpContextId() {
		// return IJavaHelpContextIds.PACKAGES_BROWSING_VIEW;
		return "";
	}

	protected String getLinkToEditorKey() {
		return PreferenceConstants.LINK_BROWSING_PACKAGES_TO_EDITOR;
	}

	/**
	 * Answers if the given <code>element</code> is a valid input for this
	 * part.
	 * 
	 * @param element
	 *            the object to test
	 * @return <true> if the given element is a valid input
	 */
	protected boolean isValidInput(Object element) {
		if (element instanceof IScriptProject
				|| (element instanceof IProjectFragment && ((IModelElement) element)
						.getElementName() != IProjectFragment.DEFAULT_PACKAGE_ROOT)) {
			IScriptProject jProject = ((IModelElement) element)
					.getScriptProject();
			if (jProject != null)
				return DLTKLanguageManager.hasScriptNature(jProject
						.getProject());
		}
		return false;
	}

	/**
	 * Answers if the given <code>element</code> is a valid element for this
	 * part.
	 * 
	 * @param element
	 *            the object to test
	 * @return <true> if the given element is a valid element
	 */
	protected boolean isValidElement(Object element) {
		if (element instanceof IScriptFolder) {
			IModelElement parent = ((IScriptFolder) element).getParent();
			if (parent != null)
				return super.isValidElement(parent)
						|| super.isValidElement(parent.getScriptProject());
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.internal.ui.browsing.JavaBrowsingPart#findElementToSelect(org.eclipse.jdt.core.IJavaElement)
	 */
	protected IModelElement findElementToSelect(IModelElement je) {
		if (je == null)
			return null;

		switch (je.getElementType()) {
		case IModelElement.SCRIPT_FOLDER:
			return je;
		case IModelElement.SOURCE_MODULE:
			return ((ISourceModule) je).getParent();
		case IModelElement.TYPE:
			return ((IType) je).getScriptFolder();
		default:
			return findElementToSelect(je.getParent());
		}
	}

	/*
	 * @see org.eclipse.jdt.internal.ui.browsing.JavaBrowsingPart#setInput(java.lang.Object)
	 */
	protected void setInput(Object input) {
		setViewerWrapperInput(input);
		super.updateTitle();
	}

	private void setViewerWrapperInput(Object input) {
		fWrappedViewer.setViewerInput(input);
	}

	/**
	 * @see org.eclipse.jdt.internal.ui.browsing.ScriptBrowsingPart#fillActionBars(org.eclipse.ui.IActionBars)
	 */
	protected void fillActionBars(IActionBars actionBars) {
		super.fillActionBars(actionBars);
		fSwitchActionGroup.fillActionBars(actionBars);
	}

	private void setUpViewer(StructuredViewer viewer) {
		Assert.isTrue(viewer != null);

		ScriptUILabelProvider labelProvider = createLabelProvider();
		viewer.setLabelProvider(createDecoratingLabelProvider(labelProvider));

		viewer.setComparator(createJavaElementComparator());
		viewer.setUseHashlookup(true);

		createContextMenu();

		// disapears when control disposed
		addKeyListener();

		// this methods only adds listeners to the viewer,
		// these listenters disapear when the viewer is disposed
		hookViewerListeners();

		// Set content provider
		viewer.setContentProvider(createContentProvider());
		// Disposed when viewer's Control is disposed
		initDragAndDrop();

	}

	// alter sorter to include LogicalPackages
	protected ModelElementSorter createJavaElementComparator() {
		return new ModelElementSorter() {
			public int category(Object element) {
				if (element instanceof LogicalPackage) {
					LogicalPackage cp = (LogicalPackage) element;
					return super.category(cp.getScriptFolders()[0]);
				} else
					return super.category(element);
			}

			public int compare(Viewer viewer, Object e1, Object e2) {
				if (e1 instanceof LogicalPackage) {
					LogicalPackage cp = (LogicalPackage) e1;
					e1 = cp.getScriptFolders()[0];
				}
				if (e2 instanceof LogicalPackage) {
					LogicalPackage cp = (LogicalPackage) e2;
					e2 = cp.getScriptFolders()[0];
				}
				return super.compare(viewer, e1, e2);
			}
		};
	}

	protected StatusBarUpdater createStatusBarUpdater(
			IStatusLineManager slManager) {
		return new StatusBarUpdater4LogicalPackage(slManager);
	}

	protected void setSiteSelectionProvider() {
		getSite().setSelectionProvider(fWrappedViewer);
	}

	void adjustInputAndSetSelection(Object o) {
		if (!(o instanceof LogicalPackage)) {
			super.adjustInputAndSetSelection(o);
			return;
		}

		LogicalPackage lp = (LogicalPackage) o;
		if (!lp.getScriptProject().equals(getInput()))
			setInput(lp.getScriptProject());

		setSelection(new StructuredSelection(lp), true);
	}

	// do the same thing as the JavaBrowsingPart but with wrapper
	protected void createActions() {
		super.createActions();

		createSelectAllAction();

		// create the switch action group
		fSwitchActionGroup = createSwitchActionGroup();
	}

	private MultiActionGroup createSwitchActionGroup() {

		LayoutAction switchToFlatViewAction = new LayoutAction(
				ScriptBrowsingMessages.PackagesView_flatLayoutAction_label,
				LIST_VIEW_STATE);
		LayoutAction switchToHierarchicalViewAction = new LayoutAction(
				ScriptBrowsingMessages.PackagesView_HierarchicalLayoutAction_label,
				TREE_VIEW_STATE);
		DLTKPluginImages.setLocalImageDescriptors(switchToFlatViewAction,
				"flatLayout.gif"); //$NON-NLS-1$
		DLTKPluginImages.setLocalImageDescriptors(
				switchToHierarchicalViewAction, "hierarchicalLayout.gif"); //$NON-NLS-1$

		return new LayoutActionGroup(new IAction[] { switchToFlatViewAction,
				switchToHierarchicalViewAction }, fCurrViewState);
	}

	private static class LayoutActionGroup extends MultiActionGroup {

		LayoutActionGroup(IAction[] actions, int index) {
			super(actions, index);
		}

		public void fillActionBars(IActionBars actionBars) {
			// create new layout group
			IMenuManager manager = actionBars.getMenuManager();
			final IContributionItem groupMarker = new GroupMarker("layout"); //$NON-NLS-1$
			manager.add(groupMarker);
			IMenuManager newManager = new MenuManager(
					ScriptBrowsingMessages.PackagesView_LayoutActionGroup_layout_label);
			manager.appendToGroup("layout", newManager); //$NON-NLS-1$
			super.addActions(newManager);
		}
	}

	/**
	 * Switches between flat and hierarchical state.
	 */
	private class LayoutAction extends Action {

		private int fState;

		public LayoutAction(String text, int state) {
			super(text, IAction.AS_RADIO_BUTTON);
			fState = state;
//			if (state == PackagesView.LIST_VIEW_STATE)
//				PlatformUI.getWorkbench().getHelpSystem().setHelp(this,
//						IJavaHelpContextIds.LAYOUT_FLAT_ACTION);
//			else
//				PlatformUI.getWorkbench().getHelpSystem().setHelp(this,
//						IJavaHelpContextIds.LAYOUT_HIERARCHICAL_ACTION);
		}

		public int getState() {
			return fState;
		}

		public void setRunnable(Runnable runnable) {
			Assert.isNotNull(runnable);
		}

		/*
		 * @see org.eclipse.jface.action.IAction#run()
		 */
		public void run() {
			switchViewer(fState);
		}
	}

	private void switchViewer(int state) {
		// Indicate which viewer is to be used
		if (fCurrViewState == state)
			return;
		else {
			fCurrViewState = state;
			IPreferenceStore store = DLTKUIPlugin.getDefault()
					.getPreferenceStore();
			store.setValue(getViewSite().getId() + TAG_VIEW_STATE, state);
		}

		// get the information from the existing viewer
		StructuredViewer viewer = fWrappedViewer.getViewer();
		Object object = viewer.getInput();
		ISelection selection = viewer.getSelection();

		// create and set up the new viewer
		Control control = createViewer(fWrappedViewer.getControl().getParent())
				.getControl();

		setUpViewer(fWrappedViewer);

		createSelectAllAction();

		// add the selection information from old viewer
		fWrappedViewer.setViewerInput(object);
		fWrappedViewer.getControl().setFocus();
		fWrappedViewer.setSelection(selection, true);

		// dispose old viewer
		viewer.getContentProvider().dispose();
		viewer.getControl().dispose();

		// layout the new viewer
		if (control != null && !control.isDisposed()) {
			control.setVisible(true);
			control.getParent().layout(true);
		}
	}

	private void createSelectAllAction() {
		IActionBars actionBars = getViewSite().getActionBars();
		if (isInListState()) {
			fSelectAllAction = new SelectAllAction((TableViewer) fWrappedViewer
					.getViewer());
			actionBars.setGlobalActionHandler(ActionFactory.SELECT_ALL.getId(),
					fSelectAllAction);
		} else {
			actionBars.setGlobalActionHandler(ActionFactory.SELECT_ALL.getId(),
					null);
			fSelectAllAction = null;
		}
		actionBars.updateActionBars();
	}

	protected IModelElement findInputForJavaElement(IModelElement je) {
		// null check has to take place here as well (not only in
		// findInputForJavaElement(IJavaElement, boolean) since we
		// are accessing the Java element
		if (je == null)
			return null;
		if (je.getElementType() == IModelElement.PROJECT_FRAGMENT
				|| je.getElementType() == IModelElement.SCRIPT_PROJECT)
			return findInputForJavaElement(je, true);
		else
			return findInputForJavaElement(je, false);

	}

	protected IModelElement findInputForJavaElement(IModelElement je,
			boolean canChangeInputType) {
		if (je == null || !je.exists())
			return null;

		if (isValidInput(je)) {

			// don't update if input must be project (i.e. project is used as
			// source folder)
			if (canChangeInputType)
				fLastInputWasProject = je.getElementType() == IModelElement.SCRIPT_PROJECT;
			return je;
		} else if (fLastInputWasProject) {
			IProjectFragment packageFragmentRoot = (IProjectFragment) je
					.getAncestor(IModelElement.PROJECT_FRAGMENT);
			if (!packageFragmentRoot.isExternal())
				return je.getScriptProject();
		}

		return findInputForJavaElement(je.getParent(), canChangeInputType);
	}

	/**
	 * Override the getText and getImage methods for the DecoratingLabelProvider
	 * to handel the decoration of logical packages.
	 * 
	 * @see org.eclipse.jdt.internal.ui.browsing.ScriptBrowsingPart#createDecoratingLabelProvider(JavaUILabelProvider)
	 */
	protected DecoratingModelLabelProvider createDecoratingLabelProvider(
			ScriptUILabelProvider provider) {
		return new DecoratingModelLabelProvider(provider, false, isInListState()) {

			public String getText(Object element) {
				if (element instanceof LogicalPackage) {
					LogicalPackage el = (LogicalPackage) element;
					return super.getText(el.getScriptFolders()[0]);
				} else
					return super.getText(element);
			}

			public Image getImage(Object element) {
				if (element instanceof LogicalPackage) {
					LogicalPackage el = (LogicalPackage) element;
					ILabelDecorator decorator = getLabelDecorator();
					IScriptFolder[] fragments = el.getScriptFolders();

					Image image = super.getImage(el);
					for (int i = 0; i < fragments.length; i++) {
						IScriptFolder fragment = fragments[i];
						Image decoratedImage = decorator.decorateImage(image,
								fragment);
						if (decoratedImage != null)
							image = decoratedImage;
					}
					return image;
				} else
					return super.getImage(element);
			}

		};
	}

	/*
	 * Overridden from JavaBrowsingPart to handel LogicalPackages and tree
	 * structure.
	 * 
	 * @see org.eclipse.jdt.internal.ui.browsing.JavaBrowsingPart#adjustInputAndSetSelection(org.eclipse.jdt.core.IJavaElement)
	 */
	void adjustInputAndSetSelection(IModelElement je) {

		IModelElement jElementToSelect = findElementToSelect(je);
		LogicalPackagesProvider p = (LogicalPackagesProvider) fWrappedViewer
				.getContentProvider();

		Object elementToSelect = jElementToSelect;
		if (jElementToSelect != null
				&& jElementToSelect.getElementType() == IModelElement.SCRIPT_FOLDER) {
			IScriptFolder pkgFragment = (IScriptFolder) jElementToSelect;
			elementToSelect = p.findLogicalPackage(pkgFragment);
			if (elementToSelect == null)
				elementToSelect = pkgFragment;
		}

		IModelElement newInput = findInputForJavaElement(je);
		if (elementToSelect == null && !isValidInput(newInput))
			setInput(null);
		else if (elementToSelect == null
				|| getViewer().testFindItem(elementToSelect) == null) {

			// optimization, if you are in the same project but expansion hasn't
			// happened
			Object input = getViewer().getInput();
			if (elementToSelect != null && newInput != null) {
				if (newInput.equals(input)) {
					getViewer().reveal(elementToSelect);
					// Adjust input to selection
				} else {
					setInput(newInput);
					getViewer().reveal(elementToSelect);
				}
			} else
				setInput(newInput);

			if (elementToSelect instanceof IScriptFolder) {
				IScriptFolder pkgFragment = (IScriptFolder) elementToSelect;
				elementToSelect = p.findLogicalPackage(pkgFragment);
				if (elementToSelect == null)
					elementToSelect = pkgFragment;
			}
		}

		ISelection selection;
		if (elementToSelect != null)
			selection = new StructuredSelection(elementToSelect);
		else
			selection = StructuredSelection.EMPTY;
		setSelection(selection, true);
	}

}
