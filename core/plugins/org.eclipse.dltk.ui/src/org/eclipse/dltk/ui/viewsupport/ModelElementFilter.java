package org.eclipse.dltk.ui.viewsupport;

import org.eclipse.dltk.core.IMember;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class ModelElementFilter extends ViewerFilter {
	private int fElementType;

	public ModelElementFilter(int type) {
		fElementType = type;
	}

	public int getFilteringType() {
		return fElementType;
	}

	public boolean isFilterProperty(Object element, Object property) {
		return false;
	}

	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof IMember) {
			IMember member = (IMember) element;
			if (member.getElementType() == fElementType) {
				return false;
			}
		}

		return true;
	}
}
