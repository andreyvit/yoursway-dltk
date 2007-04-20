package org.eclipse.dltk.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.core.ClassBasedDLTKExtensionManager;

public class DLTKUILanguageManager extends ClassBasedDLTKExtensionManager {
	private static DLTKUILanguageManager instance = new DLTKUILanguageManager();
	private final static String LANGUAGE_EXTPOINT = DLTKUIPlugin.PLUGIN_ID
			+ ".language";
	
	private DLTKUILanguageManager() {
		super(LANGUAGE_EXTPOINT);
	}

	public static IDLTKUILanguageToolkit getLanguageToolkit(String natureId)
			throws CoreException {
		return (IDLTKUILanguageToolkit) instance.getObject(natureId);
	}

	public static IDLTKUILanguageToolkit getLanguageToolkit(IModelElement element)
			throws CoreException {
		return (IDLTKUILanguageToolkit) instance.getObject(element);
	}
}
