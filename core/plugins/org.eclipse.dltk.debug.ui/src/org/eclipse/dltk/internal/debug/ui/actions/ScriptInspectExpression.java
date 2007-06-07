package org.eclipse.dltk.internal.debug.ui.actions;

import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IErrorReportingExpression;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.dltk.debug.core.model.IScriptValue;

/**
 * An implementation of an expression produced from the
 * inspect action. An inspect expression removes
 * itself from the expression manager when its debug
 * target terminates.
 */
public class ScriptInspectExpression  extends PlatformObject implements IErrorReportingExpression, IDebugEventSetListener {
	
	private IScriptValue fValue;
	
	private String fExpression;
	
	public ScriptInspectExpression(String expression, IScriptValue value) {
		fValue = value;
		fExpression = expression;
		DebugPlugin.getDefault().addDebugEventListener(this);
	}
	
	public String getExpressionText() {
		return fExpression;
	}

	public IValue getValue() {
		return fValue;
	}

	public IDebugTarget getDebugTarget() {
		IValue value= getValue();
		if (value != null) {
			return getValue().getDebugTarget();
		}

		return null;
	}

	public String getModelIdentifier() {
		return getDebugTarget().getModelIdentifier();
	}

	public ILaunch getLaunch() {
		return getDebugTarget().getLaunch();
	}

	public void handleDebugEvents(DebugEvent[] events) {
		for (int i = 0; i < events.length; i++) {
			DebugEvent event = events[i];
			switch (event.getKind()) {
				case DebugEvent.TERMINATE:
					if (event.getSource().equals(getDebugTarget())) {
						DebugPlugin.getDefault().getExpressionManager().removeExpression(this);
					}
					break;
				case DebugEvent.SUSPEND:
					if (event.getDetail() != DebugEvent.EVALUATION_IMPLICIT) {
						if (event.getSource() instanceof IDebugElement) {
							IDebugElement source = (IDebugElement) event.getSource();
							if (source.getDebugTarget().equals(getDebugTarget())) {
								DebugPlugin.getDefault().fireDebugEventSet(new DebugEvent[]{new DebugEvent(this, DebugEvent.CHANGE, DebugEvent.CONTENT)});
							}
						}
					}
					break;
			}
		}
	}

	public void dispose() {
		DebugPlugin.getDefault().removeDebugEventListener(this);		
	}

	public boolean hasErrors() {
		return false;
	}

	public String[] getErrorMessages() {	
		return new String[]{}; //getErrorMessages(fResult);
	}
	
	/*public static String[] getErrorMessages(IEvaluationResult result) {
		if (result == null) {
			return new String[0];
		}
		String messages[]= result.getErrorMessages();
		if (messages.length > 0) {
			return messages;
		}
		DebugException exception= result.getException();
		if (exception != null ) {
			Throwable cause= exception.getStatus().getException();
			if (cause instanceof InvocationException) {
				String  nestedMessage= ((InvocationException) cause).exception().referenceType().name();
				return new String[] { MessageFormat.format(DisplayMessages.JavaInspectExpression_0, new String[] {nestedMessage}) }; 
			}
			return new String[] { exception.getMessage() };
		}
		return new String[0];
	}*/
}

