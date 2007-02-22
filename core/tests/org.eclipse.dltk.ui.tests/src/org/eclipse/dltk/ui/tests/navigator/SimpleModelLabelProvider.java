package org.eclipse.dltk.ui.tests.navigator;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.viewsupport.ScriptUILabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class SimpleModelLabelProvider implements ILabelProvider {
	ScriptUILabelProvider provider = new ScriptUILabelProvider() {
		protected ImageDescriptor getSourceModuleIcon(IModelElement element,
				int renderFlags) {
			if (DLTKCore.DEBUG) {
				System.err
						.println("label provider returns ghost for source modules...");
			}

			return DLTKPluginImages.DESC_OBJS_GHOST;
		}
	};

	public Image getImage(Object element) {
		return provider.getImage(element);
	}

	public String getText(Object element) {
		String text = provider.getText(element);
		if (text != null) {
			return text;
		}
		return element.toString();
	}

	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
	}

	public void dispose() {
		// TODO Auto-generated method stub
	}

	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
	}
}
