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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IPackageDeclaration;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.actions.BuildActionGroup;
import org.eclipse.dltk.internal.ui.actions.CCPActionGroup;
import org.eclipse.dltk.internal.ui.actions.CompositeActionGroup;
import org.eclipse.dltk.internal.ui.actions.ImportActionGroup;
import org.eclipse.dltk.internal.ui.actions.NewWizardsActionGroup;
import org.eclipse.dltk.internal.ui.actions.refactoring.RefactorActionGroup;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.dltk.internal.ui.search.SearchUtil;
import org.eclipse.dltk.internal.ui.workingsets.WorkingSetFilterActionGroup;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.IWorkingCopyManager;
import org.eclipse.dltk.ui.ModelElementLabelProvider;
import org.eclipse.dltk.ui.ModelElementSorter;
import org.eclipse.dltk.ui.ScriptElementImageProvider;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.dltk.ui.actions.CustomFiltersActionGroup;
import org.eclipse.dltk.ui.actions.OpenEditorActionGroup;
import org.eclipse.dltk.ui.actions.OpenViewActionGroup;
import org.eclipse.dltk.ui.actions.SearchActionGroup;
import org.eclipse.dltk.ui.infoviews.AbstractInfoView;
import org.eclipse.dltk.ui.viewsupport.AppearanceAwareLabelProvider;
import org.eclipse.dltk.ui.viewsupport.DecoratingModelLabelProvider;
import org.eclipse.dltk.ui.viewsupport.IViewPartInputProvider;
import org.eclipse.dltk.ui.viewsupport.ProblemTableViewer;
import org.eclipse.dltk.ui.viewsupport.ScriptUILabelProvider;
import org.eclipse.dltk.ui.viewsupport.StatusBarUpdater;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.search.internal.ui.Messages;
import org.eclipse.search.ui.ISearchResultViewPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.part.IShowInSource;
import org.eclipse.ui.part.ShowInContext;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.ITextEditor;

public abstract class ScriptBrowsingPart extends ViewPart implements
		IMenuListener, ISelectionListener, IViewPartInputProvider,
		IExecutableExtension {

	private static final String TAG_SELECTED_ELEMENTS = "selectedElements"; //$NON-NLS-1$
	private static final String TAG_SELECTED_ELEMENT = "selectedElement"; //$NON-NLS-1$
	private static final String TAG_LOGICAL_PACKAGE = "logicalPackage"; //$NON-NLS-1$
	private static final String TAG_SELECTED_ELEMENT_PATH = "selectedElementPath"; //$NON-NLS-1$

	private ScriptUILabelProvider fLabelProvider;
	private ILabelProvider fTitleProvider;
	private StructuredViewer fViewer;
	private IMemento fMemento;
	private ScriptElementTypeComparator fTypeComparator;

	// Actions
	private WorkingSetFilterActionGroup fWorkingSetFilterActionGroup;
	private boolean fHasWorkingSetFilter = true;
	private boolean fHasCustomFilter = true;
	private OpenEditorActionGroup fOpenEditorGroup;
	private CCPActionGroup fCCPActionGroup;
	private BuildActionGroup fBuildActionGroup;
	private ToggleLinkingAction fToggleLinkingAction;
	protected CompositeActionGroup fActionGroups;

	// Filters
	private CustomFiltersActionGroup fCustomFiltersActionGroup;

	protected IWorkbenchPart fPreviousSelectionProvider;
	protected Object fPreviousSelectedElement;

	// Linking
	private boolean fLinkingEnabled;

	/*
	 * Ensure selection changed events being processed only if initiated by user
	 * interaction with this part.
	 */
	private boolean fProcessSelectionEvents = true;

	private IPartListener2 fPartListener = new IPartListener2() {
		public void partActivated(IWorkbenchPartReference ref) {
		}

		public void partBroughtToTop(IWorkbenchPartReference ref) {
		}

		public void partInputChanged(IWorkbenchPartReference ref) {
		}

		public void partClosed(IWorkbenchPartReference ref) {
		}

		public void partDeactivated(IWorkbenchPartReference ref) {
		}

		public void partOpened(IWorkbenchPartReference ref) {
		}

		public void partVisible(IWorkbenchPartReference ref) {
			if (ref != null && ref.getId() == getSite().getId()) {
				fProcessSelectionEvents = true;
				IWorkbenchPage page = getSite().getWorkbenchWindow()
						.getActivePage();
				if (page != null)
					selectionChanged(page.getActivePart(), page.getSelection());
			}
		}

		public void partHidden(IWorkbenchPartReference ref) {
			if (ref != null && ref.getId() == getSite().getId())
				fProcessSelectionEvents = false;
		}
	};
	private IDLTKLanguageToolkit fToolkit;

	public ScriptBrowsingPart() {
		super();
		initLinkingEnabled();
	}

	/*
	 * Implements method from IViewPart.
	 */
	public void init(IViewSite site, IMemento memento) throws PartInitException {
		super.init(site, memento);
		fMemento = memento;
	}

	/*
	 * Implements method from IViewPart.
	 */
	public void saveState(IMemento memento) {
		if (fViewer == null) {
			// part has not been created
			if (fMemento != null) // Keep the old state;
				memento.putMemento(fMemento);
			return;
		}
		if (fHasWorkingSetFilter)
			fWorkingSetFilterActionGroup.saveState(memento);
		if (fHasCustomFilter)
			fCustomFiltersActionGroup.saveState(memento);
		saveSelectionState(memento);
		saveLinkingEnabled(memento);
	}

	private void saveLinkingEnabled(IMemento memento) {
		memento.putInteger(getLinkToEditorKey(), fLinkingEnabled ? 1 : 0);
	}

	private void saveSelectionState(IMemento memento) {
		Object elements[] = ((IStructuredSelection) fViewer.getSelection())
				.toArray();
		if (elements.length > 0) {
			IMemento selectionMem = memento.createChild(TAG_SELECTED_ELEMENTS);
			for (int i = 0; i < elements.length; i++) {
				IMemento elementMem = selectionMem
						.createChild(TAG_SELECTED_ELEMENT);
				Object o = elements[i];
				if (o instanceof IModelElement)
					elementMem
							.putString(TAG_SELECTED_ELEMENT_PATH,
									((IModelElement) elements[i])
											.getHandleIdentifier());
				else if (o instanceof LogicalPackage) {
					IScriptFolder[] packages = ((LogicalPackage) o)
							.getScriptFolders();
					for (int j = 0; j < packages.length; j++) {
						IMemento packageMem = elementMem
								.createChild(TAG_LOGICAL_PACKAGE);
						packageMem.putString(TAG_SELECTED_ELEMENT_PATH,
								packages[j].getHandleIdentifier());
					}
				}
			}
		}
	}

	protected void restoreState(IMemento memento) {
		if (fHasWorkingSetFilter)
			fWorkingSetFilterActionGroup.restoreState(memento);
		if (fHasCustomFilter)
			fCustomFiltersActionGroup.restoreState(memento);

		if (fHasCustomFilter || fHasWorkingSetFilter) {
			fViewer.getControl().setRedraw(false);
			fViewer.refresh();
			fViewer.getControl().setRedraw(true);
		}
	}

	private ISelection restoreSelectionState(IMemento memento) {
		if (memento == null)
			return null;

		IMemento childMem;
		childMem = memento.getChild(TAG_SELECTED_ELEMENTS);
		if (childMem != null) {
			ArrayList list = new ArrayList();
			IMemento[] elementMem = childMem.getChildren(TAG_SELECTED_ELEMENT);
			for (int i = 0; i < elementMem.length; i++) {
				String javaElementHandle = elementMem[i]
						.getString(TAG_SELECTED_ELEMENT_PATH);
				if (javaElementHandle == null) {
					// logical package
					IMemento[] packagesMem = elementMem[i]
							.getChildren(TAG_LOGICAL_PACKAGE);
					LogicalPackage lp = null;
					for (int j = 0; j < packagesMem.length; j++) {
						javaElementHandle = packagesMem[j]
								.getString(TAG_SELECTED_ELEMENT_PATH);
						Object pack = DLTKCore.create(javaElementHandle);
						if (pack instanceof IScriptFolder
								&& ((IScriptFolder) pack).exists()) {
							if (lp == null)
								lp = new LogicalPackage((IScriptFolder) pack);
							else
								lp.add((IScriptFolder) pack);
						}
					}
					if (lp != null)
						list.add(lp);
				} else {
					IModelElement element = DLTKCore.create(javaElementHandle);
					if (element != null && element.exists())
						list.add(element);
				}
			}
			return new StructuredSelection(list);
		}
		return null;
	}

	private void restoreLinkingEnabled(IMemento memento) {
		Integer val = memento.getInteger(getLinkToEditorKey());
		if (val != null) {
			fLinkingEnabled = val.intValue() != 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPartControl(Composite parent) {
		Assert.isTrue(fViewer == null);

		fTypeComparator = new ScriptElementTypeComparator();

		// Setup viewer
		fViewer = createViewer(parent);

		initDragAndDrop();

		fLabelProvider = createLabelProvider();
		fViewer.setLabelProvider(createDecoratingLabelProvider(fLabelProvider));

		fViewer.setComparator(createModelElementComparator());
		fViewer.setUseHashlookup(true);
		fTitleProvider = createTitleProvider();

		createContextMenu();
		getSite().setSelectionProvider(fViewer);

		if (fMemento != null) { // initialize linking state before creating the
			// actions
			restoreLinkingEnabled(fMemento);
		}

		createActions(); // call before registering for selection changes
		addKeyListener();

		if (fMemento != null)
			restoreState(fMemento);

		getSite().setSelectionProvider(fViewer);

		// Status line
		IStatusLineManager slManager = getViewSite().getActionBars()
				.getStatusLineManager();
		fViewer.addSelectionChangedListener(createStatusBarUpdater(slManager));

		hookViewerListeners();

		// Filters
		addFilters();

		// Initialize viewer input
		fViewer.setContentProvider(createContentProvider());
		setInitialInput();

		// Initialize selection
		setInitialSelection();
		fMemento = null;

		// Listen to page changes
		getViewSite().getPage().addPostSelectionListener(this);
		getViewSite().getPage().addPartListener(fPartListener);

		fillActionBars(getViewSite().getActionBars());

		setHelp();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class key) {
		if (key == IShowInSource.class) {
			return getShowInSource();
		}
		// if (key == IContextProvider.class)
		// return JavaUIHelp.getHelpContextProvider(this, getHelpContextId());

		return super.getAdapter(key);
	}

	/**
	 * Returns the <code>IShowInSource</code> for this view.
	 * 
	 * @return returns the <code>IShowInSource</code>
	 */
	protected IShowInSource getShowInSource() {
		return new IShowInSource() {
			public ShowInContext getShowInContext() {
				return new ShowInContext(null, getSite().getSelectionProvider()
						.getSelection());
			}
		};
	}

	protected DecoratingModelLabelProvider createDecoratingLabelProvider(
			ScriptUILabelProvider provider) {
		// XXX: Work in progress for problem decorator being a workbench
		// decorator//
		// return new ExcludingDecoratingLabelProvider(provider, decorationMgr,
		// "org.eclipse.jdt.ui.problem.decorator"); //$NON-NLS-1$
		return new DecoratingModelLabelProvider(provider);
	}

	protected ModelElementSorter createModelElementComparator() {
		return new ModelElementSorter();
	}

	protected StatusBarUpdater createStatusBarUpdater(
			IStatusLineManager slManager) {
		return new StatusBarUpdater(slManager);
	}

	protected void createContextMenu() {
		MenuManager menuManager = new MenuManager("#PopupMenu"); //$NON-NLS-1$
		menuManager.setRemoveAllWhenShown(true);
		menuManager.addMenuListener(this);
		Menu contextMenu = menuManager.createContextMenu(fViewer.getControl());
		fViewer.getControl().setMenu(contextMenu);
		getSite().registerContextMenu(menuManager, fViewer);
	}

	protected void initDragAndDrop() {
		// int ops = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
		// // drop
		// Transfer[] dropTransfers = new Transfer[] { LocalSelectionTransfer
		// .getInstance() };
		// TransferDropTargetListener[] dropListeners = new
		// TransferDropTargetListener[] { new SelectionTransferDropAdapter(
		// fViewer) };
		// fViewer.addDropSupport(ops | DND.DROP_DEFAULT, dropTransfers,
		// new DelegatingDropAdapter(dropListeners));
		//
		// // Drag
		// Transfer[] dragTransfers = new Transfer[] {
		// LocalSelectionTransfer.getInstance(),
		// ResourceTransfer.getInstance() };
		// TransferDragSourceListener[] dragListeners = new
		// TransferDragSourceListener[] {
		// new SelectionTransferDragAdapter(fViewer),
		// new ResourceTransferDragAdapter(fViewer) };
		// fViewer.addDragSupport(ops, dragTransfers, new DLTKViewerDragAdapter(
		// fViewer, dragListeners));
	}

	protected void fillActionBars(IActionBars actionBars) {
		IToolBarManager toolBar = actionBars.getToolBarManager();
		fillToolBar(toolBar);

		if (fHasWorkingSetFilter)
			fWorkingSetFilterActionGroup.fillActionBars(getViewSite()
					.getActionBars());

		actionBars.updateActionBars();

		fActionGroups.fillActionBars(actionBars);

		if (fHasCustomFilter)
			fCustomFiltersActionGroup.fillActionBars(actionBars);

		IMenuManager menu = actionBars.getMenuManager();
		menu.add(fToggleLinkingAction);
	}

	// ---- IWorkbenchPart
	// ------------------------------------------------------

	public void setFocus() {
		fViewer.getControl().setFocus();
	}

	public void dispose() {
		if (fViewer != null) {
			getViewSite().getPage().removePostSelectionListener(this);
			getViewSite().getPage().removePartListener(fPartListener);
			fViewer = null;
		}
		if (fActionGroups != null)
			fActionGroups.dispose();

		if (fWorkingSetFilterActionGroup != null) {
			fWorkingSetFilterActionGroup.dispose();
		}

		super.dispose();
	}

	/**
	 * Adds the KeyListener
	 */
	protected void addKeyListener() {
		fViewer.getControl().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event) {
				handleKeyReleased(event);
			}
		});
	}

	protected void handleKeyReleased(KeyEvent event) {
		if (event.stateMask != 0)
			return;

		int key = event.keyCode;
		if (key == SWT.F5) {
			IAction action = fBuildActionGroup.getRefreshAction();
			if (action.isEnabled())
				action.run();
		}
	}

	// ---- Adding Action to Toolbar -------------------------------------------

	protected void fillToolBar(IToolBarManager tbm) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
	 */
	public void menuAboutToShow(IMenuManager menu) {
		DLTKUIPlugin.createStandardGroups(menu);

		IStructuredSelection selection = (IStructuredSelection) fViewer
				.getSelection();
		int size = selection.size();
		Object element = selection.getFirstElement();

		if (size == 1)
			addOpenNewWindowAction(menu, element);
		fActionGroups.setContext(new ActionContext(selection));
		fActionGroups.fillContextMenu(menu);
		fActionGroups.setContext(null);
	}

	private void addOpenNewWindowAction(IMenuManager menu, Object element) {
		if (element instanceof IModelElement) {
			element = ((IModelElement) element).getResource();
		}
		if (!(element instanceof IContainer))
			return;
		// menu.appendToGroup(IContextMenuConstants.GROUP_OPEN,
		// new PatchedOpenInNewWindowAction(
		// getSite().getWorkbenchWindow(), (IContainer) element));
	}

	protected IDLTKLanguageToolkit getToolkit() {
		return this.fToolkit;
	}

	protected void createActions() {
		fActionGroups = new CompositeActionGroup(new ActionGroup[] {
				new NewWizardsActionGroup(this.getSite()),
				fOpenEditorGroup = new OpenEditorActionGroup(this),
				new OpenViewActionGroup(this),
				fCCPActionGroup = new CCPActionGroup(this),
				// new GenerateActionGroup(this),
				new RefactorActionGroup(this), new ImportActionGroup(this),
				fBuildActionGroup = new BuildActionGroup(this),
				new SearchActionGroup(this, this.getToolkit()) });

		if (fHasWorkingSetFilter) {
			String viewId = getConfigurationElement().getAttribute("id"); //$NON-NLS-1$
			Assert.isNotNull(viewId);
			IPropertyChangeListener workingSetListener = new IPropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent event) {
					doWorkingSetChanged(event);
				}
			};
			fWorkingSetFilterActionGroup = new WorkingSetFilterActionGroup(
					getSite(), workingSetListener);
			fViewer.addFilter(fWorkingSetFilterActionGroup
					.getWorkingSetFilter());
		}

		// Custom filter group
		if (fHasCustomFilter)
			fCustomFiltersActionGroup = new CustomFiltersActionGroup(this,
					fViewer);

		fToggleLinkingAction = new ToggleLinkingAction(this);
	}

	private void doWorkingSetChanged(PropertyChangeEvent event) {
		String property = event.getProperty();
		if (IWorkingSetManager.CHANGE_WORKING_SET_NAME_CHANGE.equals(property))
			updateTitle();
		else if (IWorkingSetManager.CHANGE_WORKING_SET_CONTENT_CHANGE
				.equals(property)) {
			updateTitle();
			fViewer.getControl().setRedraw(false);
			fViewer.refresh();
			fViewer.getControl().setRedraw(true);
		}

	}

	/**
	 * Returns the shell to use for opening dialogs. Used in this class, and in
	 * the actions.
	 * 
	 * @return returns the shell
	 */
	Shell getShell() {
		return fViewer.getControl().getShell();
	}

	protected final Display getDisplay() {
		return fViewer.getControl().getDisplay();
	}

	/**
	 * Returns the selection provider.
	 * 
	 * @return the selection provider
	 */
	ISelectionProvider getSelectionProvider() {
		return fViewer;
	}

	/**
	 * Answers if the given <code>element</code> is a valid input for this
	 * part.
	 * 
	 * @param element
	 *            the object to test
	 * @return <code>true</code> if the given element is a valid input
	 */
	abstract protected boolean isValidInput(Object element);

	/**
	 * Answers if the given <code>element</code> is a valid element for this
	 * part.
	 * 
	 * @param element
	 *            the object to test
	 * @return <code>true</code> if the given element is a valid element
	 */
	protected boolean isValidElement(Object element) {
		if (!(element instanceof IModelElement)) {
			return false;
		}
		Object input = getViewer().getInput();
		if (input == null)
			return false;
		if (input instanceof Collection)
			return ((Collection) input).contains(element);
		else
			return input.equals(element);

	}

	private boolean isInputResetBy(Object newInput, Object input,
			IWorkbenchPart part) {
		if (newInput == null)
			return part == fPreviousSelectionProvider;

		if (input instanceof IModelElement && newInput instanceof IModelElement)
			return getTypeComparator().compare(newInput, input) > 0;

		if ((newInput instanceof List) && (part instanceof PackagesView))
			return true;

		else
			return false;
	}

	private boolean isInputResetBy(IWorkbenchPart part) {
		if (!(part instanceof ScriptBrowsingPart))
			return true;
		Object thisInput = getViewer().getInput();
		Object partInput = ((ScriptBrowsingPart) part).getViewer().getInput();

		if (thisInput instanceof Collection)
			thisInput = ((Collection) thisInput).iterator().next();

		if (partInput instanceof Collection)
			partInput = ((Collection) partInput).iterator().next();

		if (thisInput instanceof IModelElement
				&& partInput instanceof IModelElement)
			return getTypeComparator().compare(partInput, thisInput) > 0;
		else
			return true;
	}

	protected boolean isAncestorOf(Object ancestor, Object element) {
		if (element instanceof IModelElement
				&& ancestor instanceof IModelElement)
			return !element.equals(ancestor)
					&& internalIsAncestorOf((IModelElement) ancestor,
							(IModelElement) element);
		if (element instanceof IModelElement && ancestor instanceof List) {
			List l = (List) ancestor;
			for (Iterator iterator = l.iterator(); iterator.hasNext();) {
				IModelElement anc = (IModelElement) iterator.next();
				if (element.equals(anc)
						&& internalIsAncestorOf(anc, (IModelElement) element)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean internalIsAncestorOf(IModelElement ancestor,
			IModelElement element) {
		if (element != null)
			return element.equals(ancestor)
					|| internalIsAncestorOf(ancestor, element.getParent());
		else
			return false;
	}

	private boolean isSearchResultView(IWorkbenchPart part) {
		return SearchUtil.isSearchPlugInActivated()
				&& part instanceof ISearchResultViewPart;
	}

	protected boolean needsToProcessSelectionChanged(IWorkbenchPart part,
			ISelection selection) {
		if (!fProcessSelectionEvents || part == this
				|| isSearchResultView(part) || part instanceof AbstractInfoView) {
			if (part == this)
				fPreviousSelectionProvider = part;
			return false;
		}
		return true;
	}

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (!needsToProcessSelectionChanged(part, selection))
			return;

		if (fToggleLinkingAction.isChecked() && (part instanceof ITextEditor)) {
			setSelectionFromEditor(part, selection);
			return;
		}

		if (!(selection instanceof IStructuredSelection))
			return;

		// Set selection
		Object selectedElement = getSingleElementFromSelection(selection);

		if (selectedElement != null
				&& (part == null || part.equals(fPreviousSelectionProvider))
				&& selectedElement.equals(fPreviousSelectedElement))
			return;

		fPreviousSelectedElement = selectedElement;

		Object currentInput = getViewer().getInput();
		if (selectedElement != null && selectedElement.equals(currentInput)) {
			Object elementToSelect = findElementToSelect(selectedElement);
			if (elementToSelect != null
					&& getTypeComparator().compare(selectedElement,
							elementToSelect) < 0)
				setSelection(new StructuredSelection(elementToSelect), true);
			else if (elementToSelect == null && (this instanceof MembersView)) {
				setSelection(StructuredSelection.EMPTY, true);
				fPreviousSelectedElement = StructuredSelection.EMPTY;
			}
			fPreviousSelectionProvider = part;
			return;
		}

		// Clear input if needed
		if (part != fPreviousSelectionProvider && selectedElement != null
				&& !selectedElement.equals(currentInput)
				&& isInputResetBy(selectedElement, currentInput, part)) {
			if (!isAncestorOf(selectedElement, currentInput))
				setInput(null);
			fPreviousSelectionProvider = part;
			return;
		} else if (selection.isEmpty() && !isInputResetBy(part)) {
			fPreviousSelectionProvider = part;
			return;
		} else if (selectedElement == null
				&& part == fPreviousSelectionProvider) {
			setInput(null);
			fPreviousSelectionProvider = part;
			return;
		}
		fPreviousSelectionProvider = part;

		// Adjust input and set selection and
		adjustInputAndSetSelection(selectedElement);
	}

	void setHasWorkingSetFilter(boolean state) {
		fHasWorkingSetFilter = state;
	}

	void setHasCustomSetFilter(boolean state) {
		fHasCustomFilter = state;
	}

	protected Object getInput() {
		return fViewer.getInput();
	}

	protected void setInput(Object input) {
		setViewerInput(input);
		updateTitle();
	}

	boolean isLinkingEnabled() {
		return fLinkingEnabled;
	}

	private void initLinkingEnabled() {
		fLinkingEnabled = DLTKUIPlugin.getDefault().getPreferenceStore()
				.getBoolean(getLinkToEditorKey());
	}

	private void setViewerInput(Object input) {
		fProcessSelectionEvents = false;
		fViewer.setInput(input);
		fProcessSelectionEvents = true;
	}

	void updateTitle() {
		setTitleToolTip(getToolTipText(fViewer.getInput()));
	}

	/**
	 * Returns the tool tip text for the given element.
	 * 
	 * @param element
	 *            the element
	 * @return the tooltip for the element
	 */
	String getToolTipText(Object element) {
		String result;
		if (!(element instanceof IResource)) {
			result = ScriptElementLabels.getDefault().getTextLabel(element,
					AppearanceAwareLabelProvider.DEFAULT_TEXTFLAGS);
		} else {
			IPath path = ((IResource) element).getFullPath();
			if (path.isRoot()) {
				result = getConfigurationElement().getAttribute("name"); //$NON-NLS-1$
			} else {
				result = path.makeRelative().toString();
			}
		}

		if (fWorkingSetFilterActionGroup == null
				|| fWorkingSetFilterActionGroup.getWorkingSet() == null)
			return result;

		IWorkingSet ws = fWorkingSetFilterActionGroup.getWorkingSet();
		String wsstr = Messages.format(
				ScriptBrowsingMessages.JavaBrowsingPart_toolTip,
				new String[] { ws.getLabel() });
		if (result.length() == 0)
			return wsstr;
		return Messages.format(
				ScriptBrowsingMessages.JavaBrowsingPart_toolTip2, new String[] {
						result, ws.getLabel() });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#getTitleToolTip()
	 */
	public String getTitleToolTip() {
		if (fViewer == null)
			return super.getTitleToolTip();
		return getToolTipText(fViewer.getInput());
	}

	protected final StructuredViewer getViewer() {
		return fViewer;
	}

	protected final void setViewer(StructuredViewer viewer) {
		fViewer = viewer;
	}

	protected ScriptUILabelProvider createLabelProvider() {
		return new AppearanceAwareLabelProvider(
				AppearanceAwareLabelProvider.DEFAULT_TEXTFLAGS,
				AppearanceAwareLabelProvider.DEFAULT_IMAGEFLAGS
						| ScriptElementImageProvider.SMALL_ICONS, DLTKUIPlugin
						.getDefault().getPreferenceStore());
	}

	protected ILabelProvider createTitleProvider() {
		return new ModelElementLabelProvider(
				ModelElementLabelProvider.SHOW_BASICS
						| ModelElementLabelProvider.SHOW_SMALL_ICONS);
	}

	protected final ILabelProvider getLabelProvider() {
		return fLabelProvider;
	}

	protected final ILabelProvider getTitleProvider() {
		return fTitleProvider;
	}

	/**
	 * Creates the viewer of this part.
	 * 
	 * @param parent
	 *            the parent for the viewer
	 * @return the created viewer
	 */
	protected StructuredViewer createViewer(Composite parent) {
		return new ProblemTableViewer(parent, SWT.MULTI);
	}

	protected int getLabelProviderFlags() {
		return ModelElementLabelProvider.SHOW_BASICS
				| ModelElementLabelProvider.SHOW_OVERLAY_ICONS
				| ModelElementLabelProvider.SHOW_SMALL_ICONS
				| ModelElementLabelProvider.SHOW_VARIABLE
				| ModelElementLabelProvider.SHOW_PARAMETERS;
	}

	/**
	 * Adds filters the viewer of this part.
	 */
	protected void addFilters() {
		// default is to have no filters
	}

	/**
	 * Creates the content provider of this part.
	 * 
	 * @return the content provider
	 */
	protected IContentProvider createContentProvider() {
		return new ScriptBrowsingContentProvider(true, this, this.getToolkit());
	}

	protected void setInitialInput() {
		// Use the selection, if any
		ISelection selection = getSite().getPage().getSelection();
		Object input = getSingleElementFromSelection(selection);
		if (!(input instanceof IModelElement)) {
			// Use the input of the page
			input = getSite().getPage().getInput();
			if (!(input instanceof IModelElement)
					&& input instanceof IAdaptable)
				input = ((IAdaptable) input).getAdapter(IModelElement.class);
		}
		setInput(findInputForJavaElement((IModelElement) input));
	}

	protected void setInitialSelection() {
		// Use the selection, if any
		Object input;
		IWorkbenchPage page = getSite().getPage();
		ISelection selection = null;
		if (page != null)
			selection = page.getSelection();
		if (selection instanceof ITextSelection) {
			Object part = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().getActivePart();
			if (part instanceof IEditorPart) {
				setSelectionFromEditor((IEditorPart) part);
				if (fViewer.getSelection() != null)
					return;
			}
		}

		// Use saved selection from memento
		if (selection == null || selection.isEmpty())
			selection = restoreSelectionState(fMemento);

		if (selection == null || selection.isEmpty()) {
			// Use the input of the page
			input = getSite().getPage().getInput();
			if (!(input instanceof IModelElement)) {
				if (input instanceof IAdaptable)
					input = ((IAdaptable) input)
							.getAdapter(IModelElement.class);
				else
					return;
			}
			selection = new StructuredSelection(input);
		}
		selectionChanged(null, selection);
	}

	protected final void setHelp() {
		// JavaUIHelp.setHelp(fViewer, getHelpContextId());
	}

	/**
	 * Returns the context ID for the Help system
	 * 
	 * @return the string used as ID for the Help context
	 */
	abstract protected String getHelpContextId();

	/**
	 * Returns the preference key for the link to editor setting.
	 * 
	 * @return the string used as key into the preference store
	 */
	abstract protected String getLinkToEditorKey();

	/**
	 * Adds additional listeners to this view. This method can be overridden but
	 * should call super.
	 */
	protected void hookViewerListeners() {
		fViewer
				.addPostSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						if (!fProcessSelectionEvents)
							return;

						fPreviousSelectedElement = getSingleElementFromSelection(event
								.getSelection());

						IWorkbenchPage page = getSite().getPage();
						if (page == null)
							return;

						if (page.equals(DLTKUIPlugin.getActivePage())
								&& ScriptBrowsingPart.this.equals(page
										.getActivePart())) {
							linkToEditor((IStructuredSelection) event
									.getSelection());
						}
					}
				});

		fViewer.addOpenListener(new IOpenListener() {
			public void open(OpenEvent event) {
				IAction open = fOpenEditorGroup.getOpenAction();
				if (open.isEnabled()) {
					open.run();
					restoreSelection();
				}
			}
		});
	}

	void restoreSelection() {
		// Default is to do nothing
	}

	void adjustInputAndSetSelection(Object o) {
		if (!(o instanceof IModelElement || o instanceof List)) {
			if (o == null)
				setInput(null);
			setSelection(StructuredSelection.EMPTY, true);
			return;
		}
		if (o instanceof IModelElement) {
			IModelElement je = (IModelElement) o;
			IModelElement elementToSelect = findElementToSelect(je);
			IModelElement newInput = findInputForJavaElement(je);
			IModelElement oldInput = null;
			if (getInput() instanceof IModelElement)
				oldInput = (IModelElement) getInput();

			if (elementToSelect == null && !isValidInput(newInput)
					&& (newInput == null && !isAncestorOf(je, oldInput)))
				// Clear input
				setInput(null);
			else if (mustSetNewInput(elementToSelect, oldInput, newInput)) {
				// Adjust input to selection
				setInput(newInput);
			}

			if (elementToSelect != null && elementToSelect.exists())
				setSelection(new StructuredSelection(elementToSelect), true);
			else
				setSelection(StructuredSelection.EMPTY, true);
		}
		else {
			setSelection(new StructuredSelection(o), true);
			setInput(o);
		}
	}

	/**
	 * Compute if a new input must be set.
	 * 
	 * @param elementToSelect
	 *            the element to select
	 * @param oldInput
	 *            old input
	 * @param newInput
	 *            new input
	 * @return <code>true</code> if the input has to be set
	 * @since 3.0
	 */
	private boolean mustSetNewInput(IModelElement elementToSelect,
			IModelElement oldInput, IModelElement newInput) {
		return (newInput == null || !newInput.equals(oldInput))
				&& (elementToSelect == null || oldInput == null || (!((elementToSelect instanceof IPackageDeclaration)
						&& (elementToSelect.getParent().equals(oldInput
								.getParent())) && (!isAncestorOf(
						getViewPartInput(), elementToSelect)))));
	}

	/**
	 * Finds the closest Java element which can be used as input for this part
	 * and has the given Java element as child
	 * 
	 * @param je
	 *            the Java element for which to search the closest input
	 * @return the closest Java element used as input for this part
	 */
	protected IModelElement findInputForJavaElement(IModelElement je) {
		if (je == null || !je.exists())
			return null;
		if (isValidInput(je))
			return je;
		return findInputForJavaElement(je.getParent());
	}

	protected final Object findElementToSelect(Object obj) {
		if (obj instanceof IModelElement)
			return findElementToSelect((IModelElement) obj);
		else if (obj instanceof List) {
			return obj;
		}
		return null;
	}

	/**
	 * Finds the element which has to be selected in this part.
	 * 
	 * @param je
	 *            the Java element which has the focus
	 * @return returns the element to select
	 */
	abstract protected IModelElement findElementToSelect(IModelElement je);

	protected final Object getSingleElementFromSelection(ISelection selection) {
		if (!(selection instanceof IStructuredSelection) || selection.isEmpty())
			return null;

		Iterator iter = ((IStructuredSelection) selection).iterator();
		Object firstElement = iter.next();
		if (!(firstElement instanceof IModelElement)) {
			if (firstElement instanceof IMarker)
				firstElement = ((IMarker) firstElement).getResource();
			if (firstElement instanceof IAdaptable) {
				IModelElement je = (IModelElement) ((IAdaptable) firstElement)
						.getAdapter(IModelElement.class);
				if (je == null && firstElement instanceof IFile) {
					IContainer parent = ((IFile) firstElement).getParent();
					if (parent != null)
						return parent.getAdapter(IModelElement.class);
					else
						return null;
				} else
					return je;

			} else
				return firstElement;
		}
		Object currentInput = getViewer().getInput();
		List elements = new ArrayList();
		if (currentInput == null
				|| !currentInput
						.equals(findInputForJavaElement((IModelElement) firstElement)))
			if (iter.hasNext() && selection instanceof StructuredSelection) {
				// multi-selection and view is empty
				return ((StructuredSelection) selection).toList();
			} else
				// OK: single selection and view is empty
				return firstElement;

		// be nice to multi-selection
		while (iter.hasNext()) {
			Object element = iter.next();
			if (!(element instanceof IModelElement))
				return null;
			if (!currentInput
					.equals(findInputForJavaElement((IModelElement) element)))
				return null;
		}
		return firstElement;
	}

	/**
	 * Gets the typeComparator.
	 * 
	 * @return Returns a JavaElementTypeComparator
	 */
	protected Comparator getTypeComparator() {
		return fTypeComparator;
	}

	/**
	 * Links to editor (if option enabled)
	 * 
	 * @param selection
	 *            the selection
	 */
	private void linkToEditor(IStructuredSelection selection) {
		Object obj = selection.getFirstElement();

		if (selection.size() == 1) {
			IEditorPart part = EditorUtility.isOpenInEditor(obj);
			if (part != null) {
				IWorkbenchPage page = getSite().getPage();
				page.bringToTop(part);
				if (obj instanceof IModelElement)
					EditorUtility.revealInEditor(part, (IModelElement) obj);
			}
		}
	}

	void setSelectionFromEditor(IWorkbenchPart part) {
		if (!fProcessSelectionEvents || !linkBrowsingViewSelectionToEditor()
				|| !(part instanceof IEditorPart))
			return;

		IWorkbenchPartSite site = part.getSite();
		if (site == null)
			return;
		ISelectionProvider provider = site.getSelectionProvider();
		if (provider != null)
			setSelectionFromEditor(part, provider.getSelection());
	}

	private void setSelectionFromEditor(IWorkbenchPart part,
			ISelection selection) {
		if (part instanceof IEditorPart) {
			IModelElement element = null;
			if (selection instanceof IStructuredSelection) {
				Object obj = getSingleElementFromSelection(selection);
				if (obj instanceof IModelElement)
					element = (IModelElement) obj;
			}
			IEditorInput ei = ((IEditorPart) part).getEditorInput();
			if (selection instanceof ITextSelection) {
				int offset = ((ITextSelection) selection).getOffset();
				element = getElementAt(ei, offset);
			}
			if (element != null) {
				adjustInputAndSetSelection(element);
				return;
			}
			if (ei instanceof IFileEditorInput) {
				IFile file = ((IFileEditorInput) ei).getFile();
				IModelElement je = (IModelElement) file
						.getAdapter(IModelElement.class);
				if (je == null) {
					IContainer container = ((IFileEditorInput) ei).getFile()
							.getParent();
					if (container != null)
						je = (IModelElement) container
								.getAdapter(IModelElement.class);
				}
				if (je == null) {
					setSelection(null, false);
					return;
				}
				adjustInputAndSetSelection(je);
			} /*
				 * else if (ei instanceof IClassFileEditorInput) { IClassFile cf =
				 * ((IClassFileEditorInput) ei).getClassFile();
				 * adjustInputAndSetSelection(cf); }
				 */
		}
	}

	/**
	 * Returns the element contained in the EditorInput
	 * 
	 * @param input
	 *            the editor input
	 * @return the input element
	 */
	Object getElementOfInput(IEditorInput input) {
		if (input instanceof IFileEditorInput)
			return ((IFileEditorInput) input).getFile();
		if (input != null)
			return DLTKUIPlugin.getEditorInputModelElement(input);
		return null;
	}

	void setSelection(ISelection selection, boolean reveal) {
		if (selection != null && selection.equals(fViewer.getSelection()))
			return;
		fProcessSelectionEvents = false;
		fViewer.setSelection(selection, reveal);
		fProcessSelectionEvents = true;
	}

	/**
	 * @param input
	 *            the editor input
	 * @param offset
	 *            the offset in the file
	 * @return the element at the given offset
	 */
	protected IModelElement getElementAt(IEditorInput input, int offset) {

		IWorkingCopyManager manager = DLTKUIPlugin.getDefault()
				.getWorkingCopyManager();
		ISourceModule unit = manager.getWorkingCopy(input);
		if (unit != null)
			try {
				if (unit.isConsistent())
					return unit.getElementAt(offset);
				else {
					/*
					 * XXX: We should set the selection later when the CU is
					 * reconciled. see
					 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=51290
					 */
				}
			} catch (ModelException ex) {
				// fall through
			}
		return null;
	}

	// protected IType getTypeForCU(ISourceModule cu) {
	// // Use primary type if possible
	// IType primaryType = cu.findPrimaryType();
	// if (primaryType != null)
	// return primaryType;
	//
	// // Use first top-level type
	// try {
	// IType[] types = cu.getTypes();
	// if (types.length > 0)
	// return types[0];
	// else
	// return null;
	// } catch (ModelException ex) {
	// return null;
	// }
	// }

	void setProcessSelectionEvents(boolean state) {
		fProcessSelectionEvents = state;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.internal.ui.viewsupport.IViewPartInputProvider#getViewPartInput()
	 */
	public Object getViewPartInput() {
		if (fViewer != null) {
			return fViewer.getInput();
		}
		return null;
	}

	protected void setActionGroups(CompositeActionGroup actionGroups) {
		fActionGroups = actionGroups;
	}

	protected void setBuildActionGroup(BuildActionGroup actionGroup) {
		fBuildActionGroup = actionGroup;
	}

	protected void setCCPActionGroup(CCPActionGroup actionGroup) {
		fCCPActionGroup = actionGroup;
	}

	protected void setCustomFiltersActionGroup(
			CustomFiltersActionGroup customFiltersActionGroup) {
		fCustomFiltersActionGroup = customFiltersActionGroup;
	}

	protected boolean hasCustomFilter() {
		return fHasCustomFilter;
	}

	protected boolean hasWorkingSetFilter() {
		return fHasWorkingSetFilter;
	}

	protected void setOpenEditorGroup(OpenEditorActionGroup openEditorGroup) {
		fOpenEditorGroup = openEditorGroup;
	}

	protected OpenEditorActionGroup getOpenEditorGroup() {
		return fOpenEditorGroup;
	}

	protected BuildActionGroup getBuildActionGroup() {
		return fBuildActionGroup;
	}

	protected CCPActionGroup getCCPActionGroup() {
		return fCCPActionGroup;
	}

	private boolean linkBrowsingViewSelectionToEditor() {
		return isLinkingEnabled();
	}

	public void setLinkingEnabled(boolean enabled) {
		fLinkingEnabled = enabled;
		DLTKUIPlugin.getDefault().getPreferenceStore().setValue(
				getLinkToEditorKey(), enabled);
		if (enabled) {
			IEditorPart editor = getSite().getPage().getActiveEditor();
			if (editor != null) {
				setSelectionFromEditor(editor);
			}
		}
	}

	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) {
		super.setInitializationData(config, propertyName, data);
		String nature = null;
		if (data instanceof String) {
			nature = (String) data;

		} else if (data instanceof Map) {
			nature = (String) ((Map) data).get("nature"); //$NON-NLS-1$
		}
		if (nature != null) {
			try {
				this.fToolkit = DLTKLanguageManager.getLanguageToolkit(nature);
			} catch (CoreException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
				throw new RuntimeException(
						ScriptBrowsingMessages.ScriptBrowsingPart_natureAttributeMustBeSpecifiedAndCorrect, e);
			}
		} else {
			throw new RuntimeException(
					ScriptBrowsingMessages.ScriptBrowsingPart_natureAttributeMustBeSpecifiedAndCorrect);
		}
	}
}
