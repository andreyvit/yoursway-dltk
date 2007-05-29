package org.eclipse.dltk.validators.internal.ui.eternalchecker;

import org.eclipse.dltk.validators.internal.core.externalchecker.Rule;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.widgets.TableItem;

public class RuleCelllModifier implements ICellModifier {

	private ExternalCheckerConfigurationPage page;
	
	public RuleCelllModifier (ExternalCheckerConfigurationPage page) {
		super();
		this.page = page;
	}

	public boolean canModify(Object element, String property) {
		return true;
	}

	public Object getValue(Object element, String property) {
		Object result = null;
		Rule rule = (Rule) element;
		result = rule.getDescription(); 
		return result;
	}

	public void modify(Object element, String property, Object value) {
			
		TableItem item = (TableItem) element;
		Rule task = (Rule) item.getData();
		String valueString;

		valueString = ((String) value).trim();
		task.setDescription(valueString);
		
		page.getRulesList().ruleChanged(task);
	
	}
}
