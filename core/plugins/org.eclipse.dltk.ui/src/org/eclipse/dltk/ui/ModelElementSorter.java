/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.ui;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.scriptview.BuildPathContainer;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.ui.model.IWorkbenchAdapter;

import com.ibm.icu.text.Collator;

/**
 * Sorter for Script elements. Ordered by element category, then by element
 * name. Package fragment roots are sorted as ordered on the buildpath.
 * 
 */
public class ModelElementSorter extends ViewerSorter {

	private static final int PROJECTS = 1;
	private static final int PROJECTFRAGMENT = 2;
	private static final int SCRIPTFOLDER = 3;

	private static final int SOURCEMODULES = 4;
	private static final int CLASSFILES = 5;

	private static final int RESOURCEFOLDERS = 7;
	private static final int RESOURCES = 8;
	private static final int STORAGE = 9;

	private static final int PACKAGE_DECL = 10;
	private static final int IMPORT_CONTAINER = 11;
	private static final int IMPORT_DECLARATION = 12;

	// Includes all categories ordered using the OutlineSortOrderPage:
	// types, initializers, methods & fields
	private static final int MEMBERSOFFSET = 15;

	private static final int SCRIPT_ELEMENTS = 50;
	private static final int OTHERS = 51;

	private MembersOrderPreferenceCache fMemberOrderCache;
	private Collator fNewCollator; // collator from ICU

	/**
	 * Constructor.
	 */
	public ModelElementSorter() {
		super(null); // delay initialization of collator
		fMemberOrderCache = DLTKUIPlugin.getDefault()
				.getMemberOrderPreferenceCache();
		fNewCollator = null;
	}

	/*
	 * @see ViewerSorter#category
	 */
	public int category(Object element) {
		if (element instanceof IModelElement) {
			try {
				IModelElement je = (IModelElement) element;

				switch (je.getElementType()) {
				case IModelElement.METHOD: {
					IMethod method = (IMethod) je;
					if (method.isConstructor()) {
						return getMemberCategory(MembersOrderPreferenceCache.CONSTRUCTORS_INDEX);
					}
					return getMemberCategory(MembersOrderPreferenceCache.METHOD_INDEX);
				}
				case IModelElement.FIELD: {
					return getMemberCategory(MembersOrderPreferenceCache.FIELDS_INDEX);
				}
					// case IModelElement.INITIALIZER :
					// {
					// int flags= ((IInitializer) je).getFlags();
					// if (Flags.isStatic(flags))
					// return
					// getMemberCategory(MembersOrderPreferenceCache.STATIC_INIT_INDEX);
					// else
					// return
					// getMemberCategory(MembersOrderPreferenceCache.INIT_INDEX);
					// }
				case IModelElement.TYPE:
					return getMemberCategory(MembersOrderPreferenceCache.TYPE_INDEX);
					// case IModelElement.PACKAGE_DECLARATION :
					// return PACKAGE_DECL;
					// case IModelElement.IMPORT_CONTAINER :
					// return IMPORT_CONTAINER;
					// case IModelElement.IMPORT_DECLARATION :
					// return IMPORT_DECLARATION;
				case IModelElement.SCRIPT_FOLDER:
					return SCRIPTFOLDER;
				case IModelElement.PROJECT_FRAGMENT:
					return PROJECTFRAGMENT;
				case IModelElement.SCRIPT_PROJECT:
					return PROJECTS;
				case IModelElement.SOURCE_MODULE:
					return SOURCEMODULES;
				}

			} catch (ModelException e) {
				if (!e.isDoesNotExist())
					DLTKUIPlugin.log(e);
			}
			return SCRIPT_ELEMENTS;
		} else if (element instanceof IFile) {
			return RESOURCES;
		} else if (element instanceof IProject) {
			return PROJECTS;
		} else if (element instanceof IContainer) {
			return RESOURCEFOLDERS;
		} else if (element instanceof IStorage) {
			return STORAGE;
		} else if (element instanceof BuildPathContainer) {
			return PROJECTFRAGMENT;
		}
		return OTHERS;
	}

	private int getMemberCategory(int kind) {
		int offset = fMemberOrderCache.getCategoryIndex(kind);
		return offset + MEMBERSOFFSET;
	}

	/*
	 * @see ViewerSorter#compare
	 */
	public int compare(Viewer viewer, Object e1, Object e2) {
		int cat1 = category(e1);
		int cat2 = category(e2);

		if (needsBuildpathComparision(e1, cat1, e2, cat2)) {
			IProjectFragment root1 = getProjectFragment(e1);
			IProjectFragment root2 = getProjectFragment(e2);
			if (root1 == null) {
				if (root2 == null) {
					return 0;
				} else {
					return 1;
				}
			} else if (root2 == null) {
				return -1;
			}
			// check if not same to avoid expensive class path access
			if (!root1.getPath().equals(root2.getPath())) {
				int p1 = getBuildpathIndex(root1);
				int p2 = getBuildpathIndex(root2);
				if (p1 != p2) {
					return p1 - p2;
				}
			}
		}

		if (cat1 != cat2)
			return cat1 - cat2;

		if (cat1 == PROJECTS || cat1 == RESOURCES || cat1 == RESOURCEFOLDERS
				|| cat1 == STORAGE || cat1 == OTHERS) {
			String name1 = getNonScriptElementLabel(viewer, e1);
			String name2 = getNonScriptElementLabel(viewer, e2);
			if (name1 != null && name2 != null) {
				return getNewCollator().compare(name1, name2);
			}
			return 0; // can't compare
		}
		// only script elements from this point

		String name1 = getElementName(e1);
		String name2 = getElementName(e2);

		if (e1 instanceof IType) { // handle anonymous types
			if (name1.length() == 0) {
				if (name2.length() == 0) {
					try {
						String sc1[] = ((IType) e1).getSuperClasses();
						String sc2[] = ((IType) e2).getSuperClasses();
						if (sc1 != null && sc2 != null && sc1.length > 0
								&& sc2.length > 0) {
							return getNewCollator().compare(sc1[0], sc2[0]);
						}
						return 0;
					} catch (ModelException e) {
						return 0;
					}
				} else {
					return 1;
				}
			} else if (name2.length() == 0) {
				return -1;
			}
		}

		int cmp = getNewCollator().compare(name1, name2);
		if (cmp != 0) {
			return cmp;
		}

		if (e1 instanceof IMethod) {
			String[] params1 = null;
			try {
				params1 = ((IMethod) e1).getParameters();
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[] params2 = null;
			try {
				params2 = ((IMethod) e2).getParameters();
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (params1 != null && params2 != null) {
				int len = Math.min(params1.length, params2.length);
				for (int i = 0; i < len; i++) {
					cmp = getNewCollator().compare(params1[i], params2[i]);
					if (cmp != 0) {
						return cmp;
					}
				}
			}
			return params1.length - params2.length;
		}
		return 0;
	}

	private IProjectFragment getProjectFragment(Object element) {
		if (element instanceof BuildPathContainer) {
			// return first package fragment root from the container
			BuildPathContainer cp = (BuildPathContainer) element;
			Object[] roots = cp.getProjectFragments();
			if (roots.length > 0)
				return (IProjectFragment) roots[0];
			// non resolvable - return null
			return null;
		}
		return DLTKModelUtil.getProjectFragment((IModelElement) element);
	}

	private String getNonScriptElementLabel(Viewer viewer, Object element) {
		// try to use the workbench adapter for non - script resources or if not
		// available, use the viewers label provider

		if (element instanceof IAdaptable) {
			IWorkbenchAdapter adapter = (IWorkbenchAdapter) ((IAdaptable) element)
					.getAdapter(IWorkbenchAdapter.class);
			if (adapter != null) {
				return adapter.getLabel(element);
			}
		}
		if (viewer instanceof ContentViewer) {
			IBaseLabelProvider prov = ((ContentViewer) viewer)
					.getLabelProvider();
			if (prov instanceof ILabelProvider) {
				return ((ILabelProvider) prov).getText(element);
			}
		}
		return null;
	}

	private int getBuildpathIndex(IProjectFragment root) {
		try {
			IPath rootPath = root.getPath();
			IProjectFragment[] roots = root.getScriptProject()
					.getProjectFragments();
			for (int i = 0; i < roots.length; i++) {
				if (roots[i].getPath().equals(rootPath)) {
					return i;
				}
			}
		} catch (ModelException e) {
		}

		return Integer.MAX_VALUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ViewerSorter#getCollator()
	 */
	public final java.text.Collator getCollator() {
		// kept in for API compatibility
		if (collator == null) {
			collator = java.text.Collator.getInstance();
		}
		return collator;
	}

	private final Collator getNewCollator() {
		if (fNewCollator == null) {
			fNewCollator = Collator.getInstance();
		}
		return fNewCollator;
	}

	private boolean needsBuildpathComparision(Object e1, int cat1, Object e2,
			int cat2) {
		if ((cat1 == PROJECTFRAGMENT && cat2 == PROJECTFRAGMENT)
				|| (cat1 == SCRIPTFOLDER
						&& ((IScriptFolder) e1).getParent().getResource() instanceof IProject && cat2 == PROJECTFRAGMENT)
				|| (cat1 == PROJECTFRAGMENT && cat2 == SCRIPTFOLDER && ((IScriptFolder) e2)
						.getParent().getResource() instanceof IProject)) {
			IDLTKProject p1 = getScriptProject(e1);
			return p1 != null && p1.equals(getScriptProject(e2));
		}
		return false;
	}

	private IDLTKProject getScriptProject(Object element) {
		if (element instanceof IModelElement) {
			return ((IModelElement) element).getScriptProject();
		} else if (element instanceof BuildPathContainer) {
			return ((BuildPathContainer) element).getScriptProject();
		}
		return null;
	}

	protected String getElementName(Object element) {
		if (element instanceof IModelElement) {
			return ((IModelElement) element).getElementName();
		} else if (element instanceof BuildPathContainer) {
			return ((BuildPathContainer) element).getLabel(element);
		} else {
			return element.toString();
		}
	}
}
