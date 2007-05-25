package org.eclipse.dltk.ruby.internal.debug.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Preferences.IPropertyChangeListener;
import org.eclipse.core.runtime.Preferences.PropertyChangeEvent;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.internal.ui.model.elements.VariableLabelProvider;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IPresentationContext;
import org.eclipse.dltk.debug.core.model.IScriptVariable;

public class RubyVariableLabelProvider extends VariableLabelProvider implements
		IPropertyChangeListener {

	/**
	 * Map of view id to qualified name setting
	 */
	// private Map fQualifiedNameSettings = new HashMap();
	// private boolean fQualifiedNames = false;
	public RubyVariableLabelProvider() {
		// JDIDebugUIPlugin.getDefault().getPluginPreferences().addPropertyChangeListener(this);
	}

	protected String getValueText(IVariable variable, IValue value,
			IPresentationContext context) throws CoreException {
		// if (value instanceof IJavaValue) {
		// return
		// escapeSpecialChars(fLabelProvider.getFormattedValueText((IJavaValue)
		// value));
		// }
		return super.getValueText(variable, value, context);
	}

	protected String getValueTypeName(IVariable variable, IValue value,
			IPresentationContext context) throws CoreException {
		// String typeName=
		// DebugUIMessages.JDIModelPresentation_unknown_type__2;
		// try {
		// typeName = value.getReferenceTypeName();
		// if (!fQualifiedNames) {
		// return fLabelProvider.removeQualifierFromGenericName(typeName);
		// }
		// } catch (DebugException e) {}
		// return typeName;
		return super.getVariableTypeName(variable, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.internal.ui.elements.adapters.VariableLabelAdapter#getVariableTypeName(org.eclipse.debug.core.model.IVariable)
	 */
	protected String getVariableTypeName(IVariable variable,
			IPresentationContext context) throws CoreException {
		// String typeName=
		// DebugUIMessages.JDIModelPresentation_unknown_type__2;
		// try {
		// typeName = variable.getReferenceTypeName();
		// if (!fQualifiedNames) {
		// return fLabelProvider.removeQualifierFromGenericName(typeName);
		// }
		// } catch (DebugException e) {}
		// return typeName;
		return super.getVariableTypeName(variable, context);
	}

	/**
	 * Returns if the the specified presentation context is showing qualified
	 * names or not
	 * 
	 * @param context
	 * @return true if the presentation context is showing qualified names,
	 *         false otherwise
	 */
// private Boolean isShowQualfiiedNames(IPresentationContext context) {
// Boolean qualified = (Boolean) fQualifiedNameSettings.get(context.getId());
// if (qualified == null) {
// qualified =
// Boolean.valueOf(JDIDebugUIPlugin.getDefault().getPluginPreferences().getBoolean(context.getId()
// + '.' + IJDIPreferencesConstants.PREF_SHOW_QUALIFIED_NAMES));
// fQualifiedNameSettings.put(context.getId(), qualified);
// }
// return qualified;
// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.internal.ui.elements.adapters.VariableLabelAdapter#getColumnText(org.eclipse.debug.core.model.IVariable,
	 *      org.eclipse.debug.core.model.IValue, java.lang.String,
	 *      org.eclipse.debug.internal.ui.viewers.provisional.IPresentationContext)
	 */
	protected String getColumnText(IVariable variable, IValue value,
			IPresentationContext context, String columnId) throws CoreException {
		if (RubyVariableColumnPresentation.COLUMN_INSTANCE_ID.equals(columnId)) {
			String key = ((IScriptVariable)variable).getId();
			return key;
		}
		return super.getColumnText(variable, value, context, columnId);
	}

	public void propertyChange(PropertyChangeEvent event) {
		// TODO Auto-generated method stub

	}

	/**
	 * Sets qualified name setting before building label
	 */
// protected void retrieveLabel(ILabelUpdate update) throws CoreException {
// Boolean showQ = isShowQualfiiedNames(update.getPresentationContext());
// fQualifiedNames = showQ.booleanValue();
// fLabelProvider.setAttribute(JDIModelPresentation.DISPLAY_QUALIFIED_NAMES,
// showQ);
// super.retrieveLabel(update);
// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.Preferences$IPropertyChangeListener#propertyChange(org.eclipse.core.runtime.Preferences.PropertyChangeEvent)
	 */
// public void propertyChange(PropertyChangeEvent event) {
// if
// (event.getProperty().endsWith(IJDIPreferencesConstants.PREF_SHOW_QUALIFIED_NAMES))
// {
// fQualifiedNameSettings.clear();
// }
// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.internal.ui.model.elements.ElementLabelProvider#requiresUIJob(org.eclipse.debug.internal.ui.viewers.model.provisional.ILabelUpdate[])
	 */
// protected boolean requiresUIJob(ILabelUpdate[] updates) {
// return !JDIModelPresentation.isInitialized();
// }

}
