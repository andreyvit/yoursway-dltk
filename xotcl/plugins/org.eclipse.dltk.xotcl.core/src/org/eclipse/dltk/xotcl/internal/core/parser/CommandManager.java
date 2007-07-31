package org.eclipse.dltk.xotcl.internal.core.parser;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.PriorityDLTKExtensionManager;
import org.eclipse.dltk.core.SimpleClassDLTKExtensionManager;
import org.eclipse.dltk.xotcl.core.ITclCommandDetector;
import org.eclipse.dltk.xotcl.core.ITclCommandProcessor;
import org.eclipse.dltk.xotcl.internal.core.XOTclPlugin;

public class CommandManager {
	private static final String ID_ATTR = "id";
	private static final String EXTENSION_PROCESSOR = XOTclPlugin.PLUGIN_ID + ".tclCommandProcessor";
	private static final String EXTENSION_DETECTOR = XOTclPlugin.PLUGIN_ID + ".tclCommandDetector";
	private static final String CLASS_ATTR = "class";
	
	private static class SimpleExtensionManager extends PriorityDLTKExtensionManager {
		public SimpleExtensionManager(String extensionPoint) {
			super(extensionPoint, ID_ATTR);
		}

		public Object getInitObject(ElementInfo ext) {
			try {
				if (ext != null) {
					IConfigurationElement cfg = (IConfigurationElement) ext.config;
					return createObject(cfg);
				}
			} catch (CoreException e) {
				if( DLTKCore.DEBUG ) {
					e.printStackTrace();
				}
			}
			return null;
		}

		protected Object createObject(IConfigurationElement cfg)
				throws CoreException {
			return cfg.createExecutableExtension(CLASS_ATTR);
		}

		public Object get(String name) {
			return getInitObject(getElementInfo(name));
		}

	}
	
	private SimpleExtensionManager commands = new SimpleExtensionManager(EXTENSION_PROCESSOR);
	private SimpleClassDLTKExtensionManager detectors = new SimpleClassDLTKExtensionManager(EXTENSION_DETECTOR);
	public ITclCommandProcessor getProcessor(String name) {
		return (ITclCommandProcessor) commands.get(name);
	}
	public ITclCommandDetector[] getDetectors() {
		Object[] objects = detectors.getObjects();
		ITclCommandDetector[] detectors = new ITclCommandDetector[objects.length];
		for (int i = 0; i < objects.length; i++) {
			detectors[i] = (ITclCommandDetector) objects[i];
		}
		return detectors;
	}
	
	private static CommandManager sInstance = null;
	public static CommandManager getInstance() {
		if( sInstance == null ) {
			sInstance = new CommandManager();
		}
		return sInstance;
	}
}
