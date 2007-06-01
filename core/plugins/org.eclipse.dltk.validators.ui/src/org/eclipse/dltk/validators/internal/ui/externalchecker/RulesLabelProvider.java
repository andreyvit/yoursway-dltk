package org.eclipse.dltk.validators.internal.ui.externalchecker;

import org.eclipse.dltk.validators.internal.core.externalchecker.Rule;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class RulesLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return ((Rule)element).getDescription();
		case 1:
			return ((Rule)element).getType();
		default:
			return null;
		}
	}
}
