package org.eclipse.dltk.ruby.internal.ui;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.dltk.ruby.core.RubyPlugin;
import org.eclipse.ui.progress.UIJob;

public class InitializeAfterLoadJob extends UIJob {
	
	
	
	private final class RealJob extends Job {
		public RealJob(String name) {
			super(name);
		}
		public void waitForAutoBuild() {
			boolean wasInterrupted = false;
			do {
				try {
					Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);
					wasInterrupted = false;
				} catch (OperationCanceledException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					wasInterrupted = true;
				}
			} while (wasInterrupted);
		}
		protected IStatus run(IProgressMonitor monitor) {
			monitor.beginTask("", 10); //$NON-NLS-1$
//			try {
//				waitForAutoBuild();
//				RubyPlugin.initializeAfterLoad(new SubProgressMonitor(monitor, 6));
//				RubyUI.initializeAfterLoad(new SubProgressMonitor(monitor, 4));
//			} catch (CoreException e) {
//				RubyPlugin.log(e);
//				RubyPlugin.initialized = true;
//				return e.getStatus();
//			}
			RubyPlugin.initialized = true;
			return new Status(IStatus.OK, RubyPlugin.PLUGIN_ID, IStatus.OK, "", null); //$NON-NLS-1$
		}
		public boolean belongsTo(Object family) {
			return RubyUI.PLUGIN_ID.equals(family);
		}
	}
	
	public InitializeAfterLoadJob() {
		super("Starting DLTK Ruby initialization");
		setSystem(true);
	}
	public IStatus runInUIThread(IProgressMonitor monitor) {
		RubyPlugin.initialized = false;
		Job job = new RealJob("Initializing DLTK Ruby");
		job.setPriority(Job.SHORT);
		job.schedule();
		return new Status(IStatus.OK, RubyUI.PLUGIN_ID, IStatus.OK, "", null); //$NON-NLS-1$
	}

}