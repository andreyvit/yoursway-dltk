package org.eclipse.dltk.internal.debug.core.eval;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.dltk.dbgp.IDbgpProperty;
import org.eclipse.dltk.dbgp.IDbgpSession;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationEngine;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationListener;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationResult;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptStackFrame;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.core.model.IScriptVariable;
import org.eclipse.dltk.internal.debug.core.model.ScriptVariable;

public class ScriptEvaluationEngine implements IScriptEvaluationEngine {
	private IScriptThread thread;

	public ScriptEvaluationEngine(IScriptThread thread) {
		this.thread = thread;
	}

	public IScriptDebugTarget getScriptDebugTarget() {
		return thread.getScriptDebugTarget();
	}

	public IScriptEvaluationResult syncEvaluate(String snippet,
			IScriptStackFrame frame) {
		IScriptEvaluationResult result = null;

		try {
			IDbgpSession session = thread.getDbgpSession();

			IDbgpProperty property = session.getExtendedCommands().evaluate(
					snippet);

			if (property != null) {
				IScriptVariable variable = new ScriptVariable(
						getScriptDebugTarget(), session, property);

				result = new ScriptEvaluationResult(thread, snippet, variable);
			} else {
				result = new FailedScriptEvaluationResult(snippet,
						new String[] { "Can't evaluate" });
			}

		} catch (DbgpException e) {
			// TODO: improve
			result = new FailedScriptEvaluationResult(snippet, new String[] { e
					.getMessage() });
		}

		return result;
	}

	public void asyncEvaluate(final String snippet,
			final IScriptStackFrame frame,
			final IScriptEvaluationListener listener) {
		Job job = new Job("Evaluation of \"" + snippet + "\"") {
			protected IStatus run(IProgressMonitor monitor) {
				if (getScriptDebugTarget().isTerminated()) {
					return Status.OK_STATUS;
				}

				listener.evaluationComplete(syncEvaluate(snippet, frame));

				return Status.OK_STATUS;
			}
		};

		job.setSystem(true);
		job.setUser(false);
		job.schedule();
	}

	public void dispose() {
		// TODO Auto-generated method stub
	}
}
