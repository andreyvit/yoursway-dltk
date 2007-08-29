package org.eclipse.dltk.launching.sourcelookup;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupDirector;
import org.eclipse.debug.core.sourcelookup.ISourceLookupParticipant;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.WorkingCopyOwner;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.internal.core.AbstractExternalSourceModule;
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
			IProject project = LaunchConfigurationUtils.getProject(getLaunchConfiguration());
			ScriptProject scriptProject = new ScriptProject(project, null);
			
			return new RemoteSourceModule(scriptProject, path, DefaultWorkingCopyOwner.PRIMARY, frame);			
			
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public class RemoteSourceModule extends AbstractExternalSourceModule {

		private ScriptStackFrame frame;

		public RemoteSourceModule(ScriptProject parent, String name,
				WorkingCopyOwner owner, ScriptStackFrame frame) {
			super(parent, name, owner);

			this.frame = frame;
		}

		/*
		 * @see org.eclipse.dltk.internal.core.AbstractSourceModule#equals(java.lang.Object)
		 */
		public boolean equals(Object obj) {
			if (!(obj instanceof RemoteSourceModule)) {
				return false;
			}

			return super.equals(obj);
		}
		
		protected IStatus validateSourceModule(IResource resource) {
			/*
			 * XXX: is there a way to validate a remote resource?
			 */			
			return IModelStatus.VERIFIED_OK;
		}

		/*
		 * @see org.eclipse.core.resources.IStorage#getContents()
		 */
		public InputStream getContents() throws CoreException {
			try {
				byte[] contents = lookupSource().getBytes();
				return new ByteArrayInputStream(contents);
			} catch (DbgpException e) {
				throw new CoreException(new Status(IStatus.ERROR,
						DLTKCore.PLUGIN_ID, "remote source lookup", e));
			}
		}

		/*
		 * @see org.eclipse.dltk.compiler.env.IDependent#getFileName()
		 */
		public char[] getFileName() {
			/*
			 * XXX: remote source should not be touched by compiler
			 * 
			 * remove this and just make the other sub-classes implement
			 * org.eclipse.dltk.compiler.env.IDependent directly?
			 */
			return new char[0];
		}

		/*
		 * @see org.eclipse.core.resources.IStorage#getFullPath()
		 */
		public IPath getFullPath() {
			Path path = new Path(frame.getFileName().getPath());
			return path;
		}

		/*
		 * @see org.eclipse.core.resources.IStorage#getName()
		 */
		public String getName() {
			return getFullPath().lastSegment();
		}

		/*
		 * @see org.eclipse.dltk.internal.core.AbstractSourceModule#getBufferContent()
		 */
		protected char[] getBufferContent() throws ModelException {
			try {
				return lookupSource().toCharArray();
			} catch (DbgpException e) {
				throw new ModelException(e, IModelStatus.ERROR);
			}
		}

		private String lookupSource() throws DbgpException {
			/*
			 * XXX: this has problems if the encodings on both hosts don't
			 * match - see getBufferContents/getContents
			 */
			URI uri = frame.getFileName();
			return frame.getScriptThread().getDbgpSession().getCoreCommands()
					.getSource(uri);
		}

		/*
		 * @see org.eclipse.dltk.internal.core.AbstractExternalSourceModule#getModuleType()
		 */
		protected String getModuleType() {
			return "DLTK Remote Source Moule: ";
		}

		/*
		 * @see org.eclipse.dltk.internal.core.AbstractSourceModule#getNatureId()
		 */
		protected String getNatureId() throws CoreException {
			IPath path = getFullPath();
			return lookupLanguageToolkit(path).getNatureId();
		}

		/*
		 * @see org.eclipse.dltk.internal.core.AbstractSourceModule#getOriginalSourceModule()
		 */
		protected ISourceModule getOriginalSourceModule() {
			return new RemoteSourceModule((ScriptProject) getParent(),
					getElementName(), DefaultWorkingCopyOwner.PRIMARY, frame);
		}
	}

}
