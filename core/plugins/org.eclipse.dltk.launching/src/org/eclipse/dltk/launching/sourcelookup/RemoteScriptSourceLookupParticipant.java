package org.eclipse.dltk.launching.sourcelookup;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant;
import org.eclipse.dltk.internal.debug.core.model.ScriptStackFrame;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;

public class RemoteScriptSourceLookupParticipant extends
		AbstractSourceLookupParticipant {

	public String getSourceName(Object object) throws CoreException {
		ScriptStackFrame frame = (ScriptStackFrame) object;

		String path = frame.getFileName().getPath();
		
		/*
		 * XXX: we may also need to know the remote operating system type
		 * - see win32 check in ScriptSourceLookupParticipant
		 * 
		 * currently there is no real validation of the of this path value -
		 * the RemoteScriptSourceLookupDirector may be able to leverage this
		 * fact - it could check if the remote dir was specified, and if yes
		 * and there is no cooresponding IFile object that matches an entry
		 * in the workspace, we could return 'null' there and follow the 
		 * regular path of letting them specific where to look.
		 * 
		 * this would only apply for being looked for under the workspace -
		 * intrepreter library source would still need to be looked up
		 */
		String remoteDir = getDirector()
				.getLaunchConfiguration()
				.getAttribute(
						ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_REMOTE_WORKING_DIR,
						"");

		if (path.indexOf(remoteDir) != -1) {
			return path.substring(remoteDir.length() + 1);
		}

		return path;
	}

}
