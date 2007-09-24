package org.eclipse.dltk.launching.sourcelookup;

import java.net.URI;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupDirector;
import org.eclipse.debug.core.sourcelookup.ISourceLookupParticipant;
import org.eclipse.dltk.internal.core.DefaultWorkingCopyOwner;
import org.eclipse.dltk.internal.core.ScriptProject;
import org.eclipse.dltk.internal.debug.core.model.ScriptStackFrame;
import org.eclipse.dltk.internal.launching.LaunchConfigurationUtils;

public class RemoteScriptSourceLookupDirector extends
		AbstractSourceLookupDirector {

	public void initializeParticipants() {
		addParticipants(new ISourceLookupParticipant[] { new RemoteScriptSourceLookupParticipant() });
	}

	public Object getSourceElement(Object element) {
		// source element was found inside the project
		Object o = super.getSourceElement(element);
		if (o instanceof IFile) {
			return o;
		}

		// time to ask for it remotely
		ScriptStackFrame frame = (ScriptStackFrame) element;

		URI uri = frame.getFileName();
		String path = uri.getPath();

		try {
			IProject project = LaunchConfigurationUtils
					.getProject(getLaunchConfiguration());

			// XXX: May be we use here DLTKCore.create(), to support correct
			// selection, etc in opened remote source module.
			ScriptProject scriptProject = new ScriptProject(project, null);

			/*
			 * XXX: this should probably use some kind of IStorable
			 * implementation instead of directly relying on the stack frame -
			 * that allows for re-use of the ExternalStorageEditorInput object
			 */

			return new DBGPSourceModule(scriptProject, path,
					DefaultWorkingCopyOwner.PRIMARY, frame);

		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
