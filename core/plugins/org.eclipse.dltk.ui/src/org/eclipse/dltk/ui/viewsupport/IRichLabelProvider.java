package org.eclipse.dltk.ui.viewsupport;

import org.eclipse.jface.viewers.ILabelProvider;

public interface IRichLabelProvider extends ILabelProvider {
	
	ColoredString getRichTextLabel(Object object);

}