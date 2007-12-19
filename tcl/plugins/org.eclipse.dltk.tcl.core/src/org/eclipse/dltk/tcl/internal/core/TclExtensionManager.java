package org.eclipse.dltk.tcl.internal.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.core.PriorityClassDLTKExtensionManager;
import org.eclipse.dltk.core.PriorityDLTKExtensionManager.ElementInfo;
import org.eclipse.dltk.tcl.core.TclPlugin;
import org.eclipse.dltk.tcl.core.extensions.ICompletionExtension;
import org.eclipse.dltk.tcl.core.extensions.IMatchLocatorExtension;
import org.eclipse.dltk.tcl.core.extensions.IMixinBuildVisitorExtension;
import org.eclipse.dltk.tcl.core.extensions.ISelectionExtension;
import org.eclipse.dltk.tcl.core.extensions.ISourceElementRequestVisitorExtension;
import org.eclipse.dltk.tcl.core.extensions.ITclLanguageExtension;

public class TclExtensionManager {
	PriorityClassDLTKExtensionManager manager = new PriorityClassDLTKExtensionManager(
			TclPlugin.PLUGIN_ID + ".tclExtension", "langauge");
	private static TclExtensionManager sInstance;

	public static TclExtensionManager getDefault() {
		if (sInstance == null) {
			sInstance = new TclExtensionManager();
		}
		return sInstance;
	}

	public ITclLanguageExtension[] getExtensions() {
		ElementInfo[] infos = manager.getElementInfos();
		if (infos == null) {
			return new ITclLanguageExtension[0];
		}
		List extensions = new ArrayList();
		for (int i = 0; i < infos.length; i++) {
			Object object = manager.getInitObject(infos[i]);
			if (object instanceof ITclLanguageExtension) {
				extensions.add(object);
			}
		}
		return (ITclLanguageExtension[]) extensions
				.toArray(new ITclLanguageExtension[extensions.size()]);
	}

	public ISourceElementRequestVisitorExtension[] getSourceElementRequestoVisitorExtensions() {
		ITclLanguageExtension[] extensions = getExtensions();
		List result = new ArrayList();
		for (int i = 0; i < extensions.length; i++) {
			ISourceElementRequestVisitorExtension visitorExtension = extensions[i]
					.createSourceElementRequestVisitorExtension();
			if (visitorExtension != null) {
				result.add(visitorExtension);
			}
		}
		return (ISourceElementRequestVisitorExtension[]) result
				.toArray(new ISourceElementRequestVisitorExtension[result
						.size()]);
	}

	public IMixinBuildVisitorExtension[] getMixinVisitorExtensions() {
		ITclLanguageExtension[] extensions = getExtensions();
		List result = new ArrayList();
		for (int i = 0; i < extensions.length; i++) {
			IMixinBuildVisitorExtension visitorExtension = extensions[i]
					.createMixinBuildVisitorExtension();
			if (visitorExtension != null) {
				result.add(visitorExtension);
			}
		}
		return (IMixinBuildVisitorExtension[]) result
				.toArray(new IMixinBuildVisitorExtension[result.size()]);
	}

	public IMatchLocatorExtension[] getMatchLocatorExtensions() {
		ITclLanguageExtension[] extensions = getExtensions();
		List result = new ArrayList();
		for (int i = 0; i < extensions.length; i++) {
			IMatchLocatorExtension visitorExtension = extensions[i]
					.createMatchLocatorExtension();
			if (visitorExtension != null) {
				result.add(visitorExtension);
			}
		}
		return (IMatchLocatorExtension[]) result
				.toArray(new IMatchLocatorExtension[result.size()]);
	}

	public ICompletionExtension[] getCompletionExtensions() {
		ITclLanguageExtension[] extensions = getExtensions();
		List result = new ArrayList();
		for (int i = 0; i < extensions.length; i++) {
			ICompletionExtension visitorExtension = extensions[i]
					.createCompletionExtension();
			if (visitorExtension != null) {
				result.add(visitorExtension);
			}
		}
		return (ICompletionExtension[]) result
				.toArray(new ICompletionExtension[result.size()]);
	}

	public ISelectionExtension[] getSelectionExtensions() {
		ITclLanguageExtension[] extensions = getExtensions();
		List result = new ArrayList();
		for (int i = 0; i < extensions.length; i++) {
			ISelectionExtension visitorExtension = extensions[i]
					.createSelectionExtension();
			if (visitorExtension != null) {
				result.add(visitorExtension);
			}
		}
		return (ISelectionExtension[]) result
				.toArray(new ISelectionExtension[result.size()]);
	}
}
