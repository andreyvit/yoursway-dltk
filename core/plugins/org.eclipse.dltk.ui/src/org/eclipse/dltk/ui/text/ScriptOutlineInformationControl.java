/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ITypeHierarchy;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.internal.ui.StandardModelElementContentProvider;
import org.eclipse.dltk.internal.ui.text.AbstractInformationControl;
import org.eclipse.dltk.internal.ui.text.TextMessages;
import org.eclipse.dltk.internal.ui.util.StringMatcher;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.ProblemsLabelDecorator;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.dltk.ui.viewsupport.AppearanceAwareLabelProvider;
import org.eclipse.dltk.ui.viewsupport.MemberFilter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.keys.KeySequence;
import org.eclipse.ui.keys.SWTKeySupport;

/**
 * Show outline in light-weight control.
 */
public abstract class ScriptOutlineInformationControl extends
		AbstractInformationControl {

	private KeyAdapter fKeyAdapter;
	private OutlineContentProvider fOutlineContentProvider;
	private IModelElement fInput = null;

	private OutlineSorter fOutlineSorter;

	private OutlineLabelProvider fInnerLabelProvider;
	protected Color fForegroundColor;

	private boolean fShowOnlyMainType;
	private LexicalSortingAction fLexicalSortingAction;
	private SortByDefiningTypeAction fSortByDefiningTypeAction;
	private ShowOnlyMainTypeAction fShowOnlyMainTypeAction;
	private Map fTypeHierarchies = new HashMap();

	/**
	 * Category filter action group.
	 * 
	 */
	private String fPattern;

	protected abstract IPreferenceStore getPreferenceStore();

	private class OutlineLabelProvider extends AppearanceAwareLabelProvider {

		private boolean fShowDefiningType;

		private OutlineLabelProvider() {
			super(AppearanceAwareLabelProvider.DEFAULT_TEXTFLAGS
					| ScriptElementLabels.F_APP_TYPE_SIGNATURE
					| ScriptElementLabels.ALL_CATEGORY,
					AppearanceAwareLabelProvider.DEFAULT_IMAGEFLAGS,
					getPreferenceStore());
		}

		/*
		 * @see ILabelProvider#getText
		 */
		public String getText(Object element) {
			String text = super.getText(element);
			if (fShowDefiningType) {
				try {
					IType type = getDefiningType(element);
					if (type != null) {
						StringBuffer buf = new StringBuffer(super.getText(type));
						buf.append(ScriptElementLabels.CONCAT_STRING);
						buf.append(text);
						return buf.toString();
					}
				} catch (ModelException e) {
				}
			}
			return text;
		}

		public Color getForeground(Object element) {
			if (fOutlineContentProvider.isShowingInheritedMembers()) {
				if (element instanceof IModelElement) {
					IModelElement je = (IModelElement) element;
					je = je.getAncestor(IModelElement.SOURCE_MODULE);
					if (fInput.equals(je)) {
						return null;
					}
				}
				return fForegroundColor;
			}
			return null;
		}

		public void setShowDefiningType(boolean showDefiningType) {
			fShowDefiningType = showDefiningType;
		}

		public boolean isShowDefiningType() {
			return fShowDefiningType;
		}

		private IType getDefiningType(Object element) throws ModelException {
			int kind = ((IModelElement) element).getElementType();

			if (kind != IModelElement.METHOD && kind != IModelElement.FIELD) {
				return null;
			}
			IType declaringType = ((IMember) element).getDeclaringType();
			if (kind != IModelElement.METHOD) {
				return declaringType;
			}
			ITypeHierarchy hierarchy = getSuperTypeHierarchy(declaringType);
			if (hierarchy == null) {
				return declaringType;
			}
			IMethod method = (IMethod) element;
			// MethodOverrideTester tester= new
			// MethodOverrideTester(declaringType, hierarchy);
			// IMethod res= tester.findDeclaringMethod(method, true);
			// if (res == null || method.equals(res)) {
			// return declaringType;
			// }
			// return res.getDeclaringType();
			return method.getDeclaringType();
		}
	}

	private class OutlineTreeViewer extends TreeViewer {

		private boolean fIsFiltering = false;

		private OutlineTreeViewer(Tree tree) {
			super(tree);
		}

		/**
		 * {@inheritDoc}
		 */
		protected Object[] getFilteredChildren(Object parent) {
			Object[] result = getRawChildren(parent);
			int unfilteredChildren = result.length;
			ViewerFilter[] filters = getFilters();
			if (filters != null) {
				for (int i = 0; i < filters.length; i++)
					result = filters[i].filter(this, parent, result);
			}
			fIsFiltering = unfilteredChildren != result.length;
			return result;
		}

		/**
		 * {@inheritDoc}
		 */
		protected void internalExpandToLevel(Widget node, int level) {
			if (!fIsFiltering && node instanceof Item) {
				Item i = (Item) node;
				if (i.getData() instanceof IModelElement) {
					IModelElement je = (IModelElement) i.getData();
					if (/* je.getElementType() == IModelElement.IMPORT_CONTAINER || */isInnerType(je)) {
						setExpanded(i, false);
						return;
					}
				}
			}
			super.internalExpandToLevel(node, level);
		}

		private boolean isInnerType(IModelElement element) {
			if (element != null
					&& element.getElementType() == IModelElement.TYPE) {
				IType type = (IType) element;
				// try {
				return type.getDeclaringType() != null;
				/*
				 * } catch (ModelException e) { IModelElement parent=
				 * type.getParent(); if (parent != null) { int
				 * parentElementType= parent.getElementType(); return
				 * (parentElementType != IModelElement.SOURCE_MODULE); } }
				 */
			}
			return false;
		}
	}

	private class OutlineContentProvider extends
			StandardModelElementContentProvider {

		private boolean fShowInheritedMembers;

		/**
		 * Creates a new Outline content provider.
		 * 
		 * @param showInheritedMembers
		 *            <code>true</code> iff inherited members are shown
		 */
		private OutlineContentProvider(boolean showInheritedMembers) {
			super(true);
			fShowInheritedMembers = showInheritedMembers;
		}

		public boolean isShowingInheritedMembers() {
			return fShowInheritedMembers;
		}

		public void toggleShowInheritedMembers() {
			Tree tree = getTreeViewer().getTree();

			tree.setRedraw(false);
			fShowInheritedMembers = !fShowInheritedMembers;
			getTreeViewer().refresh();
			getTreeViewer().expandToLevel(2);

			// reveal selection
			Object selectedElement = getSelectedElement();
			if (selectedElement != null)
				getTreeViewer().reveal(selectedElement);

			tree.setRedraw(true);
		}

		/**
		 * {@inheritDoc}
		 */
		public Object[] getChildren(Object element) {

			if (fShowInheritedMembers && element instanceof IType) {
				IType type = (IType) element;
				if (type.getDeclaringType() == null) {
					ITypeHierarchy th = getSuperTypeHierarchy(type);
					if (th != null) {
						List children = new ArrayList();
						IType[] superClasses = th.getAllSupertypes(type);
						children.addAll(Arrays.asList(super.getChildren(type)));
						for (int i = 0, scLength = superClasses.length; i < scLength; i++)
							children.addAll(Arrays.asList(super
									.getChildren(superClasses[i])));
						return children.toArray();
					}
				}
			}
			return super.getChildren(element);
		}

		/**
		 * {@inheritDoc}
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			super.inputChanged(viewer, oldInput, newInput);
			fTypeHierarchies.clear();
		}

		/**
		 * {@inheritDoc}
		 */
		public void dispose() {
			super.dispose();
			fTypeHierarchies.clear();
		}
	}

	private class ShowOnlyMainTypeAction extends Action {

		private static final String STORE_GO_INTO_TOP_LEVEL_TYPE_CHECKED = "GoIntoTopLevelTypeAction.isChecked"; //$NON-NLS-1$

		private TreeViewer fOutlineViewer;

		private ShowOnlyMainTypeAction(TreeViewer outlineViewer) {
			super(
					TextMessages.ScriptOutlineInformationControl_GoIntoTopLevelType_label,
					IAction.AS_CHECK_BOX);
			setToolTipText(TextMessages.ScriptOutlineInformationControl_GoIntoTopLevelType_tooltip);
			setDescription(TextMessages.ScriptOutlineInformationControl_GoIntoTopLevelType_description);

			DLTKPluginImages.setLocalImageDescriptors(this,
					"gointo_toplevel_type.gif"); //$NON-NLS-1$

			// PlatformUI.getWorkbench().getHelpSystem().setHelp(this,
			// IJavaHelpContextIds.GO_INTO_TOP_LEVEL_TYPE_ACTION);
			System.err.println("TODO: add help support here");

			fOutlineViewer = outlineViewer;

			boolean showclass = getDialogSettings().getBoolean(
					STORE_GO_INTO_TOP_LEVEL_TYPE_CHECKED);
			setTopLevelTypeOnly(showclass);
		}

		/*
		 * @see org.eclipse.jface.action.Action#run()
		 */
		public void run() {
			setTopLevelTypeOnly(!fShowOnlyMainType);
		}

		private void setTopLevelTypeOnly(boolean show) {
			fShowOnlyMainType = show;
			setChecked(show);

			Tree tree = fOutlineViewer.getTree();
			tree.setRedraw(false);

			fOutlineViewer.refresh(false);
			if (!fShowOnlyMainType)
				fOutlineViewer.expandToLevel(2);

			// reveal selection
			Object selectedElement = getSelectedElement();
			if (selectedElement != null)
				fOutlineViewer.reveal(selectedElement);

			tree.setRedraw(true);

			getDialogSettings().put(STORE_GO_INTO_TOP_LEVEL_TYPE_CHECKED, show);
		}
	}

	private class OutlineSorter extends ViewerSorter {

		protected ITypeHierarchy getHierarchy(IType type) {
			return getSuperTypeHierarchy(type);
		}

		public boolean isSortByDefiningType() {
			return fSortByDefiningTypeAction.isChecked();
		}

		public boolean isSortAlphabetically() {
			return fLexicalSortingAction.isChecked();
		}
	}

	private class LexicalSortingAction extends Action {

		private static final String STORE_LEXICAL_SORTING_CHECKED = "LexicalSortingAction.isChecked"; //$NON-NLS-1$

		private TreeViewer fOutlineViewer;

		private LexicalSortingAction(TreeViewer outlineViewer) {
			super(
					TextMessages.ScriptOutlineInformationControl_LexicalSortingAction_label,
					IAction.AS_CHECK_BOX);
			setToolTipText(TextMessages.ScriptOutlineInformationControl_LexicalSortingAction_tooltip);
			setDescription(TextMessages.ScriptOutlineInformationControl_LexicalSortingAction_description);

			DLTKPluginImages.setLocalImageDescriptors(this,
					"alphab_sort_co.gif"); //$NON-NLS-1$

			fOutlineViewer = outlineViewer;

			boolean checked = getDialogSettings().getBoolean(
					STORE_LEXICAL_SORTING_CHECKED);
			setChecked(checked);
			// PlatformUI.getWorkbench().getHelpSystem().setHelp(this,
			// IJavaHelpContextIds.LEXICAL_SORTING_BROWSING_ACTION);
			System.err.println("TODO: add help support here");
		}

		public void run() {
			valueChanged(isChecked(), true);
		}

		private void valueChanged(final boolean on, boolean store) {
			setChecked(on);
			BusyIndicator.showWhile(fOutlineViewer.getControl().getDisplay(),
					new Runnable() {
						public void run() {
							fOutlineViewer.refresh(false);
						}
					});

			if (store)
				getDialogSettings().put(STORE_LEXICAL_SORTING_CHECKED, on);
		}
	}

	private class SortByDefiningTypeAction extends Action {

		private static final String STORE_SORT_BY_DEFINING_TYPE_CHECKED = "SortByDefiningType.isChecked"; //$NON-NLS-1$

		private TreeViewer fOutlineViewer;

		/**
		 * Creates the action.
		 * 
		 * @param outlineViewer
		 *            the outline viewer
		 */
		private SortByDefiningTypeAction(TreeViewer outlineViewer) {
			super(
					TextMessages.ScriptOutlineInformationControl_SortByDefiningTypeAction_label);
			setDescription(TextMessages.ScriptOutlineInformationControl_SortByDefiningTypeAction_description);
			setToolTipText(TextMessages.ScriptOutlineInformationControl_SortByDefiningTypeAction_tooltip);

			DLTKPluginImages.setLocalImageDescriptors(this,
					"definingtype_sort_co.gif"); //$NON-NLS-1$

			fOutlineViewer = outlineViewer;

			// PlatformUI.getWorkbench().getHelpSystem().setHelp(this,
			// IJavaHelpContextIds.SORT_BY_DEFINING_TYPE_ACTION);
			System.err.println("TODO: add help support here");

			boolean state = getDialogSettings().getBoolean(
					STORE_SORT_BY_DEFINING_TYPE_CHECKED);
			setChecked(state);
			fInnerLabelProvider.setShowDefiningType(state);
		}

		/*
		 * @see Action#actionPerformed
		 */
		public void run() {
			BusyIndicator.showWhile(fOutlineViewer.getControl().getDisplay(),
					new Runnable() {
						public void run() {
							fInnerLabelProvider
									.setShowDefiningType(isChecked());
							getDialogSettings().put(
									STORE_SORT_BY_DEFINING_TYPE_CHECKED,
									isChecked());

							setMatcherString(fPattern, false);
							fOutlineViewer.refresh(true);

							// reveal selection
							Object selectedElement = getSelectedElement();
							if (selectedElement != null)
								fOutlineViewer.reveal(selectedElement);
						}
					});
		}
	}

	/**
	 * String matcher that can match two patterns.
	 * 
	 * 
	 */
	private static class OrStringMatcher extends StringMatcher {

		private StringMatcher fMatcher1;
		private StringMatcher fMatcher2;

		private OrStringMatcher(String pattern1, String pattern2,
				boolean ignoreCase, boolean foo) {
			super("", false, false); //$NON-NLS-1$
			fMatcher1 = new StringMatcher(pattern1, ignoreCase, false);
			fMatcher2 = new StringMatcher(pattern2, ignoreCase, false);
		}

		public boolean match(String text) {
			return fMatcher2.match(text) || fMatcher1.match(text);
		}

	}

	/**
	 * Creates a new Script outline information control.
	 * 
	 * @param parent
	 * @param shellStyle
	 * @param treeStyle
	 * @param commandId
	 */
	public ScriptOutlineInformationControl(Shell parent, int shellStyle,
			int treeStyle, String commandId) {
		super(parent, shellStyle, treeStyle, commandId, true);
	}

	/**
	 * {@inheritDoc}
	 */
	protected Text createFilterText(Composite parent) {
		Text text = super.createFilterText(parent);
		text.addKeyListener(getKeyAdapter());
		return text;
	}

	/**
	 * {@inheritDoc}
	 */
	protected TreeViewer createTreeViewer(Composite parent, int style) {
		Tree tree = new Tree(parent, SWT.SINGLE | (style & ~SWT.MULTI));
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = tree.getItemHeight() * 12;
		tree.setLayoutData(gd);

		final TreeViewer treeViewer = new OutlineTreeViewer(tree);

		// Hard-coded filters
		treeViewer.addFilter(new NamePatternFilter());
		treeViewer.addFilter(new MemberFilter());

		fForegroundColor = parent.getDisplay().getSystemColor(
				SWT.COLOR_DARK_GRAY);

		fInnerLabelProvider = new OutlineLabelProvider();
		fInnerLabelProvider.addLabelDecorator(new ProblemsLabelDecorator(null));
		// IDecoratorManager decoratorMgr=
		// PlatformUI.getWorkbench().getDecoratorManager();
		/*
		 * if
		 * (decoratorMgr.getEnabled("org.eclipse.dltk.ui.override.decorator"))
		 * //$NON-NLS-1$ fInnerLabelProvider.addLabelDecorator(new
		 * OverrideIndicatorLabelDecorator(null));
		 */

		treeViewer.setLabelProvider(fInnerLabelProvider);

		fLexicalSortingAction = new LexicalSortingAction(treeViewer);
		fSortByDefiningTypeAction = new SortByDefiningTypeAction(treeViewer);
		// fShowOnlyMainTypeAction= new ShowOnlyMainTypeAction(treeViewer);
		// fCategoryFilterActionGroup= new CategoryFilterActionGroup(treeViewer,
		// getId(), getInputForCategories());

		fOutlineContentProvider = new OutlineContentProvider(false);
		treeViewer.setContentProvider(fOutlineContentProvider);
		fOutlineSorter = new OutlineSorter();
		treeViewer.setSorter(fOutlineSorter);
		treeViewer.setAutoExpandLevel(AbstractTreeViewer.ALL_LEVELS);

		treeViewer.getTree().addKeyListener(getKeyAdapter());

		return treeViewer;
	}

	/**
	 * {@inheritDoc}
	 */
	protected String getStatusFieldText() {
		KeySequence[] sequences = getInvokingCommandKeySequences();
		if (sequences == null || sequences.length == 0)
			return ""; //$NON-NLS-1$

		String keySequence = sequences[0].format();

		if (fOutlineContentProvider.isShowingInheritedMembers())
			return Messages.format("Press ''{0}'' to hide inherited members",
					keySequence);
		else
			return Messages.format("Press ''{0}'' to show inherited members",
					keySequence);
	}

	/*
	 * @see org.eclipse.dltk.internal.ui.text.AbstractInformationControl#getId()
	 * 
	 */
	protected String getId() {
		return "org.eclipse.dltk.internal.ui.text.QuickOutline"; //$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
	 */
	public void setInput(Object information) {
		if (information == null || information instanceof String) {
			inputChanged(null, null);
			return;
		}
		IModelElement je = (IModelElement) information;
		ISourceModule cu = (ISourceModule) je
				.getAncestor(IModelElement.SOURCE_MODULE);
		if (cu != null)
			fInput = cu;

		inputChanged(fInput, information);

		// fCategoryFilterActionGroup.setInput(getInputForCategories());
	}

	private KeyAdapter getKeyAdapter() {
		if (fKeyAdapter == null) {
			fKeyAdapter = new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					int accelerator = SWTKeySupport
							.convertEventToUnmodifiedAccelerator(e);
					KeySequence keySequence = KeySequence
							.getInstance(SWTKeySupport
									.convertAcceleratorToKeyStroke(accelerator));
					KeySequence[] sequences = getInvokingCommandKeySequences();
					if (sequences == null)
						return;
					for (int i = 0; i < sequences.length; i++) {
						if (sequences[i].equals(keySequence)) {
							e.doit = false;
							toggleShowInheritedMembers();
							return;
						}
					}
				}
			};
		}
		return fKeyAdapter;
	}

	/**
	 * {@inheritDoc}
	 */
	protected void handleStatusFieldClicked() {
		toggleShowInheritedMembers();
	}

	protected void toggleShowInheritedMembers() {
		long flags = fInnerLabelProvider.getTextFlags();
		flags ^= ScriptElementLabels.ALL_POST_QUALIFIED;
		fInnerLabelProvider.setTextFlags(flags);
		fOutlineContentProvider.toggleShowInheritedMembers();
		updateStatusFieldText();
		// fCategoryFilterActionGroup.setInput(getInputForCategories());
	}

	/*
	 * @see org.eclipse.dltk.internal.ui.text.AbstractInformationControl#fillViewMenu(org.eclipse.jface.action.IMenuManager)
	 */
	protected void fillViewMenu(IMenuManager viewMenu) {
		super.fillViewMenu(viewMenu);
		// viewMenu.add(fShowOnlyMainTypeAction);

		// viewMenu.add(new Separator("Sorters")); //$NON-NLS-1$
		// viewMenu.add(fLexicalSortingAction);

		// viewMenu.add(fSortByDefiningTypeAction);

		// fCategoryFilterActionGroup.setInput(getInputForCategories());
		// fCategoryFilterActionGroup.contributeToViewMenu(viewMenu);
	}

	/*
	 * @see org.eclipse.dltk.internal.ui.text.AbstractInformationControl#setMatcherString(java.lang.String,
	 *      boolean)
	 * 
	 */
	protected void setMatcherString(String pattern, boolean update) {
		fPattern = pattern;
		if (pattern.length() == 0 || !fSortByDefiningTypeAction.isChecked()) {
			super.setMatcherString(pattern, update);
			return;
		}

		boolean ignoreCase = pattern.toLowerCase().equals(pattern);
		String pattern2 = "*" + ScriptElementLabels.CONCAT_STRING + pattern; //$NON-NLS-1$
		fStringMatcher = new OrStringMatcher(pattern, pattern2, ignoreCase,
				false);

		if (update)
			stringMatcherUpdated();

	}

	private ITypeHierarchy getSuperTypeHierarchy(IType type) {
		/*
		 * ITypeHierarchy th= (ITypeHierarchy)fTypeHierarchies.get(type); if (th ==
		 * null) { try { th= SuperTypeHierarchyCache.getTypeHierarchy(type,
		 * getProgressMonitor()); } catch (ModelException e) { return null; }
		 * catch (OperationCanceledException e) { return null; }
		 * fTypeHierarchies.put(type, th); } return th;
		 */
		return null;
	}

	private IProgressMonitor getProgressMonitor() {
		IWorkbenchPage wbPage = DLTKUIPlugin.getActivePage();
		if (wbPage == null)
			return null;

		IEditorPart editor = wbPage.getActiveEditor();
		if (editor == null)
			return null;

		return editor.getEditorSite().getActionBars().getStatusLineManager()
				.getProgressMonitor();
	}
}
