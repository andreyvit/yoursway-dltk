package org.eclipse.dltk.console;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

public class ScriptInterpreterManager {
	private static ScriptInterpreterManager instance;
	
	protected IScriptInterpreter findScriptInterpreter(String natureId) throws CoreException {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IExtensionPoint ep = reg
				.getExtensionPoint(ScriptConsoleConstants.SCRIPT_INTERPRETER_EP);
				
		IExtension[] extensions = ep.getExtensions();
		
		for (int i = 0; i < extensions.length; i++) {
			IExtension ext = extensions[i];
			IConfigurationElement[] ce = ext.getConfigurationElements();
			for (int j = 0; j < ce.length; j++) {
				if (natureId
						.equals(ce[j]
								.getAttribute(ScriptConsoleConstants.SCRIPT_INTERPRETER_NATURE_ID))) {
					Object obj = ce[j]
							.createExecutableExtension(ScriptConsoleConstants.SCRIPT_INTERPRETER_CLASS);
					if (obj instanceof IScriptInterpreter) {
						return (IScriptInterpreter) obj;
					} else {
						return null;
					}
				}
			}
		}
		
		return null;
	}

	public static ScriptInterpreterManager getInstance() {
		if (instance == null) {
			instance = new ScriptInterpreterManager();
		}

		return instance;
	}

	public IScriptInterpreter createInterpreter(String natureId) {						
		try {
			return findScriptInterpreter(natureId);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
