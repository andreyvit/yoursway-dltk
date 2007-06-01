package org.eclipse.dltk.validators.internal.ui.externalchecker;

import org.eclipse.dltk.validators.internal.core.externalchecker.Rule;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.widgets.TableItem;

public class RuleCelllModifier implements ICellModifier {

	private ExternalCheckerConfigurationPage page;
//	private String[] columnNames;
	
	public RuleCelllModifier (ExternalCheckerConfigurationPage page) {
		super();
		this.page = page;
	}

	public boolean canModify(Object element, String property) {
		return true;
	}

	public Object getValue(Object element, String property) {
		int index = page.getColumnNames().indexOf(property);
		
		Object result = null;
		Rule rule = (Rule) element;
		switch (index) {
		case 0:
			result = rule.getDescription(); 
			break;
		case 1:
		
			String stringValue = rule.getType();
			String[] choices = page.getChoices(property);
			int i = choices.length - 1;
			while (!stringValue.equals(choices[i]) && i > 0)
				--i;
			result = new Integer(i);	
		default:
			break;
		}
		
		return result;
	}

	public void modify(Object element, String property, Object value) {
		int index = page.getColumnNames().indexOf(property);	
		TableItem item = (TableItem) element;
		Rule task = (Rule) item.getData();
		String valueString;

		switch(index){
			case 0:
				valueString = ((String) value).trim();
				task.setDescription(valueString);
				page.getRulesList().ruleChanged(task);
				break;
		
			case 1:
				valueString = page.getChoices(property)[((Integer) value).intValue()].trim();
				if (!task.getType().equals(valueString)) {
					task.setType(valueString);
				}
				break;
		}
		page.getRulesList().ruleChanged(task);
	}
}
