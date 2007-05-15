package org.eclipse.dltk.ruby.internal.debug.ui;

import org.eclipse.debug.internal.ui.elements.adapters.VariableColumnPresentation;

public class RubyVariableColumnPresentation extends VariableColumnPresentation {
	/**
	 * Constant identifier for the Java variable column presentation.
	 */
	public final static String RUBY_VARIABLE_COLUMN_PRESENTATION = "Ruby" + ".VARIALBE_COLUMN_PRESENTATION"; //$NON-NLS-1$
	/**
	 * Default column identifiers
	 */
	public final static String COLUMN_INSTANCE_ID = RUBY_VARIABLE_COLUMN_PRESENTATION
			+ ".COL_INSTANCE_ID"; //$NON-NLS-1$

	/**
	 * Column ids
	 */
	private static String[] fgAllColumns = null;

	public String[] getAvailableColumns() {
		if (fgAllColumns == null) {
			String[] basic = super.getAvailableColumns();
			fgAllColumns = new String[basic.length + 1];
			System.arraycopy(basic, 0, fgAllColumns, 0, basic.length);
			fgAllColumns[basic.length] = COLUMN_INSTANCE_ID;
		}
		return fgAllColumns;
	}

	public String getHeader(String id) {
		if (COLUMN_INSTANCE_ID.equals(id)) {
			return "Object ID";
		}

		return super.getHeader(id);
	}

	public String getId() {
		return RUBY_VARIABLE_COLUMN_PRESENTATION;
	}
}