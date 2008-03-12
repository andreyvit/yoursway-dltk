package org.eclipse.dltk.validators.ui;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.validators.internal.core.ValidatorUtils;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IOConsoleOutputStream;
import org.eclipse.ui.console.IPatternMatchListener;

public abstract class AbstractValidateSelectionWithConsole implements
		IObjectActionDelegate {

	public static final String DLTK_VALIDATORS_CONSOLE = Messages.AbstractValidateSelectionWithConsole_dltkValidatorOutput;

	protected abstract void invoceValidationFor(final OutputStream out,
			final List elements, final List resources, IProgressMonitor monitor);

	ISelection selection;

	public AbstractValidateSelectionWithConsole() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		if (this.selection == null) {
			return;
		}
		processSelectionToElements(selection);
	}

	protected void processSelectionToElements(ISelection selection) {
		final Set elements = new HashSet();
		final Set resources = new HashSet();
		if (this.selection != null
				&& this.selection instanceof IStructuredSelection) {
			IStructuredSelection sel = (IStructuredSelection) this.selection;
			Iterator iterator = sel.iterator();
			for (; iterator.hasNext();) {
				Object o = iterator.next();
				ValidatorUtils.processResourcesToElements(o, elements,
						resources);
			}
		}

		Job job = new Job(getJobName()) {
			protected IStatus run(IProgressMonitor monitor) {
				IOConsoleOutputStream newOutputStream = null;
				try {
					if (isConsoleRequired()) {
						IConsoleManager consoleManager = ConsolePlugin
								.getDefault().getConsoleManager();
						IOConsole ioConsole = new IOConsole(
								DLTK_VALIDATORS_CONSOLE + getJobName(), null);
						IPatternMatchListener[] listeners = ValidatorConsoleTrackerManager
								.getListeners();
						for (int i = 0; i < listeners.length; i++) {
							ioConsole.addPatternMatchListener(listeners[i]);
						}
						consoleManager
								.addConsoles(new IConsole[] { ioConsole });
						consoleManager.showConsoleView(ioConsole);
						newOutputStream = ioConsole.newOutputStream();
					}
					List els = new ArrayList();
					els.addAll(elements);
					List res = new ArrayList();
					res.addAll(resources);
					invoceValidationFor(newOutputStream, els, res, monitor);
				} finally {
					try {
						if (newOutputStream != null) {
							newOutputStream.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return Status.OK_STATUS;
			}
		};
		Set resourcesOnly = new HashSet();
		resourcesOnly.addAll(resources);
		for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
			ISourceModule module = (ISourceModule) iterator.next();
			resourcesOnly.add(module.getResource());
		}
		ISchedulingRule[] rules = (ISchedulingRule[]) resourcesOnly
				.toArray(new ISchedulingRule[resourcesOnly.size()]);
		job.setRule(MultiRule.combine(rules));
		job.setUser(true);
		job.schedule();
		// ProgressMonitorDialog dialog = new ProgressMonitorDialog(PlatformUI
		// .getWorkbench().getDisplay().getActiveShell());
		// try {
		// dialog.run(true, true, new IRunnableWithProgress() {
		// public void run(IProgressMonitor monitor)
		// throws InvocationTargetException, InterruptedException {

		// }
		// });
		// } catch (InvocationTargetException e) {
		// if (DLTKCore.DEBUG) {
		// e.printStackTrace();
		// }
		// } catch (InterruptedException e) {
		// if (DLTKCore.DEBUG) {
		// e.printStackTrace();
		// }
		// }
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	protected abstract String getJobName();

	protected boolean isConsoleRequired() {
		return true;
	}
}