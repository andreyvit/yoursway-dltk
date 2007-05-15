package org.eclipse.dltk.ruby.internal.debug.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.core.model.IWatchExpressionDelegate;
import org.eclipse.debug.core.model.IWatchExpressionListener;
import org.eclipse.debug.core.model.IWatchExpressionResult;
import org.eclipse.dltk.dbgp.IDbgpProperty;
import org.eclipse.dltk.dbgp.IDbgpSession;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.core.model.IScriptStackFrame;
import org.eclipse.dltk.debug.core.model.IScriptThread;

public class RubyWatchExpressionDelegate implements IWatchExpressionDelegate {

	// IWatchExpressionDelegate
	public void evaluateExpression(final String expression,
			final IDebugElement context, final IWatchExpressionListener listener) {

		// Temporary very basic implemetation
		Job job = new Job("Evaluation of expression") {
			protected IStatus run(IProgressMonitor monitor) {
				if (context.getDebugTarget().isTerminated()) {
					return Status.OK_STATUS;
				}

				IDbgpSession session = null;
				if (context instanceof IScriptThread) {
					session = ((IScriptThread) context).getDbgpSession();
				} else if (context instanceof IScriptStackFrame) {
					session = ((IScriptThread) ((IScriptStackFrame) context)
							.getThread()).getDbgpSession();
				}

				IDbgpProperty property = null;

				try {
					if (session != null) {
						property = session.getExtendedCommands().evaluate(
								expression);
					}

				} catch (DbgpException e) {
					e.printStackTrace();
				}

				final String value = property != null ? property.getValue()
						: "Can't evaluate";

				listener.watchEvaluationFinished(new IWatchExpressionResult() {

					public String[] getErrorMessages() {
						return null;
					}

					public DebugException getException() {
						return null;
					}

					public String getExpressionText() {
						return expression;
					}

					public IValue getValue() {
						return new IValue() {

							public String getReferenceTypeName()
									throws DebugException {
								// TODO Auto-generated method stub
								return null;
							}

							public String getValueString()
									throws DebugException {
								return value;
							}

							public IVariable[] getVariables()
									throws DebugException {
								return new IVariable[0];
							}

							public boolean hasVariables() throws DebugException {
								return false;
							}

							public boolean isAllocated() throws DebugException {
								return true;
							}

							public IDebugTarget getDebugTarget() {
								return context.getDebugTarget();
							}

							public ILaunch getLaunch() {
								return context.getLaunch();
							}

							public String getModelIdentifier() {
								return context.getModelIdentifier();
							}

							public Object getAdapter(Class adapter) {
								if (adapter == IDebugElement.class) {
									return this;
								}
								if (adapter == ILaunch.class) {
									return getLaunch();
								}

								return null;
							}
						};
					}

					public boolean hasErrors() {
						return false;
					}

				});

				return Status.OK_STATUS;
			}
		};
		job.setSystem(true);
		job.setUser(false);
		job.schedule();

	}
}
