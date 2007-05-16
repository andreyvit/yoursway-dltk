package org.eclipse.dltk.ruby.internal.debug.ui;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IColumnPresentationFactory;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IElementLabelProvider;
import org.eclipse.dltk.debug.core.model.IScriptStackFrame;
import org.eclipse.dltk.debug.core.model.IScriptVariable;

public class RubyAdapterFactory implements IAdapterFactory {

	private static final IColumnPresentationFactory columnPresentation = new RubyVariableColumnPresentationFactory();

	private static final IElementLabelProvider labelProvider = new RubyVariableLabelProvider();

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof IScriptStackFrame) {
			if (IColumnPresentationFactory.class.equals(adapterType)) {
				return columnPresentation;
			}
		}

		if (adaptableObject instanceof IScriptVariable) {
			if (IElementLabelProvider.class.equals(adapterType)) {
				return labelProvider;
			}
		}

		return null;
	}

	public Class[] getAdapterList() {
		return new Class[] { IColumnPresentationFactory.class };
	}
}
