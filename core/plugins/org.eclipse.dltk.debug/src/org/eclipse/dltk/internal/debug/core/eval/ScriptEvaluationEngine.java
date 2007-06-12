package org.eclipse.dltk.internal.debug.core.eval;

import java.util.WeakHashMap;

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
import org.eclipse.dltk.internal.debug.core.model.ScriptDebugTarget;
import org.eclipse.dltk.internal.debug.core.model.ScriptVariable;

public class ScriptEvaluationEngine implements IScriptEvaluationEngine {
	private final IScriptThread thread;

	private int count;
	private final WeakHashMap cache;

	protected void putToCache(String snippet, IScriptEvaluationResult result) {
		if (result != null) {
			cache.put(snippet, result);
		}
	}

	protected IScriptEvaluationResult getFromCache(String snippet) {
		int newCount = thread.getSuspendCount();
		if (count != newCount) {
			cache.clear();
			count = newCount;
			return null;
		}

		return (IScriptEvaluationResult) cache.get(snippet);
	}

	private IScriptEvaluationResult evaluate(String snippet,
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
				// TODO: localize
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

	public ScriptEvaluationEngine(IScriptThread thread) {
		this.thread = thread;
		this.count = thread.getSuspendCount();
		this.cache = new WeakHashMap();
	}

	public IScriptDebugTarget getScriptDebugTarget() {
		return (ScriptDebugTarget) thread.getDebugTarget();
	}

	public IScriptEvaluationResult syncEvaluate(String snippet,
			IScriptStackFrame frame) {
		synchronized (cache) {
			IScriptEvaluationResult result = getFromCache(snippet);

			if (result == null) {
				result = evaluate(snippet, frame);
			}

			putToCache(snippet, result);

			return result;
		}
	}

	public void asyncEvaluate(final String snippet,
			final IScriptStackFrame frame,
			final IScriptEvaluationListener listener) {
		// TODO: localize
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
