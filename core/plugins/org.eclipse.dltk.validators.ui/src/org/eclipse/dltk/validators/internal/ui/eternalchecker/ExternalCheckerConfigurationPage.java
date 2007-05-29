package org.eclipse.dltk.validators.internal.ui.eternalchecker;

import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringDialogField;
import org.eclipse.dltk.validators.ValidatorConfigurationPage;
import org.eclipse.dltk.validators.internal.core.externalchecker.ExternalChecker;
import org.eclipse.dltk.validators.internal.core.externalchecker.Rule;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class ExternalCheckerConfigurationPage extends
		ValidatorConfigurationPage {

	private StringDialogField fArguments;
	private StringDialogField fCommannd;
	private Table fTable;
	private TableViewer tableViewer;
	private Button addRule;
	private Button delRule;
	private RulesList rulesList = new RulesList();
	
	public RulesList getRulesList(){
		return rulesList;
	}
	
	private String[] columnNames = new String[] {"RULES"};
	
	public ExternalCheckerConfigurationPage() {
	}

	public void applyChanges() {
		ExternalChecker externalChecker = getExtrenalChecker();
    	externalChecker.setArguments(this.fArguments.getText());
		externalChecker.setCommand(this.fCommannd.getText());	
		externalChecker.setRules(rulesList.getRules());
	
	}

	public void createControl(final Composite ancestor, int columns ) {
		createFields();
		
		Composite parent = new Composite(ancestor, SWT.NULL);
		GridLayout layout = (GridLayout)ancestor.getLayout();
		
		this.fCommannd.doFillIntoGrid(ancestor,columns);
		this.fArguments.doFillIntoGrid(ancestor, columns);
		fTable = new Table(ancestor, SWT.BORDER|SWT.MULTI);
		fTable.setLayout(layout);
    	fTable.setLinesVisible(true);
    	TableColumn col = new TableColumn(fTable, SWT.LEFT);
		col.setWidth(200);
	
		tableViewer = new TableViewer(fTable);
		tableViewer.setColumnProperties(columnNames);
		CellEditor[] editors = new CellEditor[columnNames.length];
		
		TextCellEditor textEditor = new TextCellEditor(fTable);
        ((Text) textEditor.getControl()).setTextLimit(60);
        editors[0] = textEditor;
        tableViewer.setCellEditors(editors);
        tableViewer.setCellModifier(new RuleCelllModifier(this));
        
        tableViewer.setContentProvider(new RulesContentProvider());
        tableViewer.setLabelProvider(new RulesLabelProvider());
        tableViewer.setInput(rulesList);
        
        addRule = new Button(ancestor, SWT.PUSH);
        addRule.setText("Add Rule");
        addRule.addSelectionListener(new SelectionAdapter(){
        	public void widgetSelected(SelectionEvent ev){
        		rulesList.addRule();
        	}
        });
        
        delRule = new Button(ancestor, SWT.PUSH);
        delRule.setText("Delete Rule");
        delRule.addSelectionListener(new SelectionAdapter(){
        	public void widgetSelected(SelectionEvent ev){
        		Rule rule = (Rule)((IStructuredSelection)tableViewer.getSelection()).getFirstElement();
        		if(rule!=null)
        			rulesList.removeRule(rule);
        	}
        });
        
        updateValuesFrom();
	}
	private ExternalChecker getExtrenalChecker() {
		return (ExternalChecker)getValidator();
	
	}
	private void updateValuesFrom() {
		ExternalChecker externalChecker = getExtrenalChecker();
			this.fArguments.setText(externalChecker.getArguments());
			this.fCommannd.setText(externalChecker.getCommand());
	//		this.rulesList = new RulesList();
	//		for(int i=0; i<externalChecker.getNRules(); i++){
	//			rulesList.addRule(externalChecker.getRule(i));;
	//		}
			
	}

//	private void createLabel(Composite parent, String content, int columns) {
//		Label l = new Label( parent, SWT.None );
//		l.setText(content);
//		GridData gd= new GridData();
//		gd.horizontalAlignment= GridData.FILL;
//		gd.grabExcessHorizontalSpace= true;
//		gd.horizontalSpan= columns;
//		l.setLayoutData(gd);
//	}

//	private void createBrowseButton(final Composite parent, int columns ) {/
//		GridData gd= new GridData();
//		gd.horizontalAlignment= GridData.FILL;
//		gd.grabExcessHorizontalSpace= true;
//		gd.horizontalSpan= columns - 2;
		
//		Button browse = new Button(parent, SWT.PUSH);
//		browse.setText("Browse...");
//		gd= new GridData(GridData.END);
//		gd.horizontalSpan = 1;
//		browse.setLayoutData(gd);

//		browse.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent e) {
//				FileDialog dialog = new FileDialog(parent.getShell(), SWT.OPEN);
//				String file = dialog.open();
//				if (file != null) {
////					fPath.setText(file);
//				}
//			}
//		});
//	}
	private void createFields() {
		this.fCommannd = new StringDialogField();
		this.fCommannd.setLabelText("command to run checker");
		this.fArguments = new StringDialogField();
		this.fArguments.setLabelText("Arguments");	
	
	
	}
	
	public class RulesContentProvider implements IStructuredContentProvider, IRulesListViewer {

		public Object[] getElements(Object inputElement) {
			return rulesList.getRules().toArray(); 
		}

		public void dispose() {
			rulesList.removeChangeListener(this);
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			  if (newInput != null)
	              ((RulesList) newInput).addChangeListener(this);
	          if (oldInput != null)
	              ((RulesList) oldInput).removeChangeListener(this);
		}

		public void addRule(Rule r) {
			tableViewer.add(r);
		}

		public void removeRule(Rule r) {
			tableViewer.remove(r);
		}

		public void updateRule(Rule r) {
			tableViewer.update(r, null);
		}
	}
}
