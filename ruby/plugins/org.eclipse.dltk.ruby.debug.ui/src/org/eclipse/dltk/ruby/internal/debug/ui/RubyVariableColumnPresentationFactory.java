package org.eclipse.dltk.ruby.internal.debug.ui;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IColumnPresentation;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IColumnPresentationFactory;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IPresentationContext;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.dltk.debug.core.model.IScriptStackFrame;

public class RubyVariableColumnPresentationFactory implements IColumnPresentationFactory {

	public IColumnPresentation createColumnPresentation(IPresentationContext context, Object element) {
		if (isApplicable(context, element)) {
			return new RubyVariableColumnPresentation();
		}
		return null;
	}

	public String getColumnPresentationId(IPresentationContext context, Object element) {
		if (isApplicable(context, element)) {
			return RubyVariableColumnPresentation.RUBY_VARIABLE_COLUMN_PRESENTATION;
		}
		return null;
	}
	
	private boolean isApplicable(IPresentationContext context, Object element) {
		IScriptStackFrame frame = null;
		if (IDebugUIConstants.ID_VARIABLE_VIEW.equals(context.getId())) {
			if (element instanceof IAdaptable) {
				IAdaptable adaptable = (IAdaptable)element;
				frame = (IScriptStackFrame) adaptable.getAdapter(IScriptStackFrame.class);
			}
		}
		return frame != null;		
	}

}
