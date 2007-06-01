package org.eclipse.dltk.validators.internal.ui.externalchecker;

import org.eclipse.dltk.validators.internal.core.externalchecker.CustomWildcard;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class WildcardLabelProvider extends LabelProvider implements ITableLabelProvider{

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return ((CustomWildcard)element).getLetter();
		case 1:
			return ((CustomWildcard)element).getSpattern();
		case 2:
			return ((CustomWildcard)element).getDescription();
		default:
			return null;
		}
	}
}
