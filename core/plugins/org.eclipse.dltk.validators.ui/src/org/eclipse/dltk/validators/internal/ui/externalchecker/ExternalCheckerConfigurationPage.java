package org.eclipse.dltk.validators.internal.ui.externalchecker;

import java.util.Arrays;
import java.util.List;

import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringDialogField;
import org.eclipse.dltk.validators.internal.core.externalchecker.ExternalChecker;
import org.eclipse.dltk.validators.internal.core.externalchecker.Rule;
import org.eclipse.dltk.validators.ui.ValidatorConfigurationPage;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class ExternalCheckerConfigurationPage extends
		ValidatorConfigurationPage {

	private StringDialogField fArguments;
	private StringDialogField fPath;
	private StringDialogField fExtensions;

	private Table fTable;
	private TableViewer tableViewer;
	private Button addRule;
	private Button delRule;
	private RulesList rulesList = new RulesList();

	private final String TYPES = "TYPES"; //$NON-NLS-1$

	public RulesList getRulesList() {
		return rulesList;
	}

	private String[] columnNames = new String[] { "RULES", "TYPES" }; //$NON-NLS-1$ //$NON-NLS-2$

	public ExternalCheckerConfigurationPage() {
	}

	public void applyChanges() {
		ExternalChecker externalChecker = getExtrenalChecker();
		externalChecker.setArguments(this.fArguments.getText());
		externalChecker.setCommand(this.fPath.getText());
		externalChecker.setRules(rulesList.getRules());
		externalChecker.setExtensions(this.fExtensions.getText());
	}

	private void createPathBrowse(final Composite parent, int columns) {
		this.fPath.doFillIntoGrid(parent, columns - 1);
		Text path = this.fPath.getTextControl(parent);
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = columns - 2;
		path.setLayoutData(gd);
		// Browse
		Button browse = new Button(parent, SWT.PUSH);
		browse.setText(Messages.ExternalCheckerConfigurationPage_browse);
		gd = new GridData(GridData.END);
		gd.horizontalSpan = 1;
		browse.setLayoutData(gd);

		browse.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(parent.getShell(), SWT.OPEN);
				String file = dialog.open();
				if (file != null) {
					fPath.setText(file);
				}
			}
		});
	}

	public void createControl(final Composite ancestor, int columns) {
		createFields();

		this.createPathBrowse(ancestor, columns);
		this.fArguments.doFillIntoGrid(ancestor, columns);
		this.fExtensions.doFillIntoGrid(ancestor, columns);
		Label label = new Label(ancestor, SWT.WRAP);
		label.setText(Messages.ExternalCheckerConfigurationPage_commaSeparatedListOfExtensions);
		GridData data = new GridData(SWT.FILL, SWT.FILL, false, false);
		data.horizontalSpan = columns;
		data.minimumWidth = 100;
		data.widthHint = 100;
		label.setLayoutData(data);
		this.rulesList.getRules().clear();

// GridLayout layout = (GridLayout)ancestor.getLayout();

		Group group = new Group(ancestor, SWT.NONE);
		group.setText(Messages.ExternalCheckerConfigurationPage_patternRules);
		data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.horizontalSpan = columns;
		group.setLayoutData(data);
		GridLayout layout = new GridLayout(2, false);
		group.setLayout(layout);

		label = new Label(ancestor, SWT.WRAP);
		label
				.setText(Messages.ExternalCheckerConfigurationPage_patternIsARegularExpression);
		data = new GridData(SWT.FILL, SWT.FILL, false, false);
		data.horizontalSpan = columns;
		data.minimumWidth = 100;
		data.widthHint = 100;
		label.setLayoutData(data);
// label.
// label.setSize(label.computeSize(100, SWT.DEFAULT));

		fTable = new Table(group, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
		data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.widthHint = 300;
		data.heightHint = 100;
		fTable.setLayoutData(data);

// fTable.setLayout(layout);
		fTable.setLinesVisible(true);
		fTable.setHeaderVisible(true);
// fTable.setSize(500, 500);

		TableColumn col1 = new TableColumn(fTable, SWT.LEFT, 0);
		col1.setWidth(200);
		col1.setText(Messages.ExternalCheckerConfigurationPage_outputRule);

		TableColumn col2 = new TableColumn(fTable, SWT.LEFT, 1);
		col2.setWidth(100);
		col2.setText(Messages.ExternalCheckerConfigurationPage_type);

		tableViewer = new TableViewer(fTable);
		tableViewer.setColumnProperties(columnNames);
		CellEditor[] editors = new CellEditor[columnNames.length];

		TextCellEditor textEditor = new TextCellEditor(fTable);
		((Text) textEditor.getControl()).setTextLimit(60);
		editors[0] = textEditor;

		ComboBoxCellEditor comboEditor = new ComboBoxCellEditor(fTable,
				rulesList.getTypes(), SWT.READ_ONLY);
		editors[1] = comboEditor;

		tableViewer.setCellEditors(editors);

		tableViewer.setCellModifier(new RuleCelllModifier(this));

		tableViewer.setContentProvider(new RulesContentProvider());
		tableViewer.setLabelProvider(new RulesLabelProvider());
		tableViewer.setInput(rulesList);

		Composite buttons = new Composite(group, SWT.NONE);
		buttons.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		buttons.setLayout(layout);
		data = new GridData(SWT.FILL, SWT.NONE, false, false);
		data.verticalAlignment = SWT.TOP;
		addRule = new Button(buttons, SWT.PUSH);
		addRule.setLayoutData(data);
		addRule.setText(Messages.ExternalCheckerConfigurationPage_addRule);
		addRule.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent ev) {
				rulesList.addRule();
			}
		});

		delRule = new Button(buttons, SWT.PUSH);
		delRule.setLayoutData(data);
		delRule.setText(Messages.ExternalCheckerConfigurationPage_deleteRule);
		delRule.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent ev) {
				Rule rule = (Rule) ((IStructuredSelection) tableViewer
						.getSelection()).getFirstElement();
				if (rule != null)
					rulesList.removeRule(rule);
			}
		});

		updateValuesFrom();
	}

	private ExternalChecker getExtrenalChecker() {
		return (ExternalChecker) getValidator();

	}

	private void updateValuesFrom() {
		ExternalChecker externalChecker = getExtrenalChecker();
		this.fArguments.setText(externalChecker.getArguments());

		this.fPath.setText(externalChecker.getCommand().toOSString());
		this.fExtensions.setText(externalChecker.getExtensions());

		this.rulesList.getRules().clear();
		for (int i = 0; i < externalChecker.getNRules(); i++) {
			Rule r = (Rule) externalChecker.getRule(i);
			rulesList.addRule(r);
		}
	}

	private void createFields() {
		this.fPath = new StringDialogField();
		this.fPath.setLabelText(Messages.ExternalCheckerConfigurationPage_commandToRunChecker);
		this.fArguments = new StringDialogField();
		this.fArguments.setLabelText(Messages.ExternalCheckerConfigurationPage_CheckerArguments);
		this.fExtensions = new StringDialogField();
		this.fExtensions.setLabelText(Messages.ExternalCheckerConfigurationPage_filenameExtensions);
	}

	public class RulesContentProvider implements IStructuredContentProvider,
			IRulesListViewer {

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
			tableViewer.editElement(r, 0);
		}

		public void removeRule(Rule r) {
			tableViewer.remove(r);
		}

		public void updateRule(Rule r) {
			tableViewer.update(r, null);
		}

	}

	public List getColumnNames() {
		return Arrays.asList(columnNames);
	}

	public String[] getChoices(String property) {
		if (TYPES.equals(property))
			return rulesList.getTypes(); // The ExampleTaskList knows about
		// the choice of owners
		else
			return new String[] {};
	}
}
