package org.eclipse.dltk.internal.core.builder;

import java.io.DataOutputStream;

import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.DLTKCore;



public class ScriptBuilder {
	public static final boolean DEBUG = DLTKCore.DEBUG_SCRIPT_BUILDER;	

	/**
	 * Hook allowing to initialize some static state before a complete build iteration.
	 * This hook is invoked during PRE_AUTO_BUILD notification
	 */
	public static void buildStarting() {
		// build is about to start
	}

	/**
	 * Hook allowing to reset some static state after a complete build iteration.
	 * This hook is invoked during POST_AUTO_BUILD notification
	 */
	public static void buildFinished() {
		if (DLTKCore.DEBUG)			
			System.out.println("build finished");
	}
	
	public static void removeProblemsAndTasksFor(IResource resource) {
		if (DLTKCore.DEBUG)
			System.out.println("remove problems and tasks for: " + resource);
	}

	public static void writeState(Object savedState, DataOutputStream out) {
		// TODO Auto-generated method stub		
	}		
}
