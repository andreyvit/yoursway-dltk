package org.eclipse.dltk.debug.ui;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.debug.ui.actions.IWatchExpressionFactoryAdapter;
import org.eclipse.dltk.debug.core.model.IScriptVariable;


public class ScriptDebugElementAdapterFactory implements IAdapterFactory {
	
	//private static final IElementLabelProvider fgLPVariable = new JavaVariableLabelProvider();
	//private static final IElementContentProvider fgCPVariable = new JavaVariableContentProvider();
	//private static final IElementLabelProvider fgLPExpression = new ExpressionLabelProvider();
	//private static final IElementContentProvider fgCPExpression = new JavaExpressionContentProvider();
	private static final IWatchExpressionFactoryAdapter watchExpressionFactory = new ScriptWatchExpressionFilter();

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		/*if (IElementLabelProvider.class.equals(adapterType)) {
			if (adaptableObject instanceof IJavaVariable) {
				return fgLPVariable; 
			}
			if (adaptableObject instanceof JavaInspectExpression) {
				return fgLPExpression;
			}
		}*/
		
		/*if (IElementContentProvider.class.equals(adapterType)) {
			if (adaptableObject instanceof IJavaVariable) {
				return fgCPVariable;
			}
			if (adaptableObject instanceof JavaInspectExpression) {
				return fgCPExpression;
			}
		}*/
		
		if (IWatchExpressionFactoryAdapter.class.equals(adapterType)) {
			if (adaptableObject instanceof IScriptVariable) {
				return watchExpressionFactory;
			}
			/*if (adaptableObject instanceof JavaInspectExpression) {
				return fgCPExpression;
			}*/
		}
		return null;
	}

	public Class[] getAdapterList() {
		return new Class[]{/*IElementLabelProvider.class,IElementContentProvider.class,*/IWatchExpressionFactoryAdapter.class};
	}

}
