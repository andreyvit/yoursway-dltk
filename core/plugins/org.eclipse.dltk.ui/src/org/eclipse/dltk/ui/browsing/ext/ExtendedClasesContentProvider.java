/**
 * 
 */
package org.eclipse.dltk.ui.browsing.ext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptModel;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.TypeNameMatch;
import org.eclipse.dltk.core.search.TypeNameMatchRequestor;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

class ExtendedClasesContentProvider implements ITreeContentProvider {
	/**
	 * 
	 */
	private final ExtendedClassesView contentProvider;
	Object input;
	private IDLTKSearchScope scope;

	public ExtendedClasesContentProvider(ExtendedClassesView extendedClassesView, IDLTKSearchScope scope) {
		contentProvider = extendedClassesView;
		this.scope = scope;
	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IScriptModel) {
			return getElements(parentElement);
		} else if (parentElement instanceof MixedClass) {
			List els = new ArrayList();
			MixedClass cl = (MixedClass) parentElement;
			List elements = cl.getElements();
			for (Iterator iterator = elements.iterator(); iterator
					.hasNext();) {
				Object element = iterator.next();
				if (element instanceof IType) {
					IType type = (IType) element;
					try {
						IType[] types = type.getTypes();
						for (int i = 0; i < types.length; i++) {
							els.add(types[i]);
						}
					} catch (ModelException e) {
						e.printStackTrace();
					}

				}
			}
			return convert(els);
		}
		return new Object[0];
	}

	public Object getParent(Object element) {
		return null;
	}

	public boolean hasChildren(Object element) {
		return false;
	}

	public Object[] getElements(Object inputElement) {
		final List elements = new ArrayList();
		try {
			(new SearchEngine()).searchAllTypeNames(
					null,
					SearchPattern.R_EXACT_MATCH,
					"*".toCharArray(), //$NON-NLS-1$
					SearchPattern.R_PATTERN_MATCH
							| SearchPattern.R_CASE_SENSITIVE,
					IDLTKSearchConstants.TYPE,
					scope,
					new TypeNameMatchRequestor() {
						public void acceptTypeNameMatch(TypeNameMatch match) {
							try {
								IType type = match.getType();
								if (type.exists()
										&& type.getParent()
												.getElementType() != IModelElement.TYPE) {
									elements.add(type);
								}
							} catch (Exception e) {

							}
						}
					},

					IDLTKSearchConstants.WAIT_UNTIL_READY_TO_SEARCH,
					new NullProgressMonitor());
		} catch (ModelException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return convert(elements);
	}

	private Object[] convert(final List elements) {
		// Contains name to list classes map.
		Map els = new HashMap();
		for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
			IType type = (IType) iterator.next();
			String name = type.getElementName();
			if (els.containsKey(name)) {
				MixedClass cl = (MixedClass) els.get(name);
				cl.getElements().add(type);
			} else {
				MixedClass cl = new MixedClass();
				cl.getElements().add(type);
				cl.setName(name);
				els.put(name, cl);
			}
		}

		return els.values().toArray();
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.input = newInput;
		updateInput();
	}

	private void updateInput() {
		if (this.input instanceof IModelElement) {
			IModelElement element = (IModelElement) this.input;
			this.scope = SearchEngine
					.createSearchScope(new IModelElement[] { element });
		} else if (this.input instanceof IModelElement[]) {
			this.scope = SearchEngine
					.createSearchScope((IModelElement[]) this.input);
		}
	}
}