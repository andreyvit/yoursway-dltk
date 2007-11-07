package org.eclipse.dltk.ui;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;

public class DLTKExecuteExtensionHelper {
	public static IDLTKLanguageToolkit getLanguageToolkit(IConfigurationElement config,
			String propertyName, Object data) {
		String nature = null;
		if (data instanceof String) {
			nature = (String) data;

		} else if (data instanceof Map) {
			nature = (String) ((Map) data).get("nature");
		}
		if (nature != null) {
			try {
				IDLTKLanguageToolkit toolkit = DLTKLanguageManager.getLanguageToolkit(nature);
				if( toolkit == null ) {
					throw new RuntimeException(
							"Nature attribute should be specified and correct");
				}
				return toolkit;
			} catch (CoreException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
				throw new RuntimeException(
						"Nature attribute should be specified and correct", e);
			}
		} else {
			throw new RuntimeException(
					"Nature attribute should be specified and correct");
		}
	}
}
