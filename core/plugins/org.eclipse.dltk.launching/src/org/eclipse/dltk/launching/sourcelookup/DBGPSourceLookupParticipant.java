package org.eclipse.dltk.launching.sourcelookup;

import java.net.URI;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.internal.core.DefaultWorkingCopyOwner;
import org.eclipse.dltk.internal.core.ScriptProject;
import org.eclipse.dltk.internal.debug.core.model.ScriptStackFrame;
import org.eclipse.dltk.internal.launching.LaunchConfigurationUtils;

/**
 * This class used to get source from DBGP remote debuger, if path starts with
 * dbgp scheme.
 * 
 * @author haiodo
 * 
 */
public class DBGPSourceLookupParticipant extends
		AbstractSourceLookupParticipant {

	public String getSourceName(Object object) throws CoreException {
		if (!(object instanceof ScriptStackFrame)) {
			return null;
		}
		ScriptStackFrame frame = (ScriptStackFrame) object;

		URI uri = frame.getFileName();
		if ("dbgp".equals(uri.getScheme())) {
			return "Debug resource:" + uri.getPath();
		}
		return uri.toString();
	}

	public Object[] findSourceElements(Object object) throws CoreException {
		if (object instanceof ScriptStackFrame) {
			ScriptStackFrame frame = (ScriptStackFrame) object;
			ILaunchConfiguration launchConfiguration = this.getDirector()
					.getLaunchConfiguration();

			IProject project = LaunchConfigurationUtils
					.getProject(launchConfiguration);
			ScriptProject scriptProject = (ScriptProject) DLTKCore
					.create(project);

			URI uri = frame.getFileName();
			
			if (!("dbgp".equals(uri.getScheme()))) {
				return null;
			}
			return new Object[] { new DBGPSourceModule(scriptProject, frame
					.getFileName().getPath(), DefaultWorkingCopyOwner.PRIMARY,
					frame) };
		}
		return new Object[0];
	}
}
