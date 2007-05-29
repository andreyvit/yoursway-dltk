/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.scriptchecker.internal.ui;

import java.util.Iterator;

import org.eclipse.dltk.internal.ui.util.SWTUtil;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.ComboDialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.SelectionButtonDialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringDialogField;
import org.eclipse.dltk.scriptchecker.internal.core.ScriptChecker;
import org.eclipse.dltk.scriptchecker.internal.ui.ATSGuideManager.GuideNode;
import org.eclipse.dltk.validators.ValidatorConfigurationPage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class ScriptCheckerConfigurationPage extends ValidatorConfigurationPage {

	private static final String[] SEVERITY = ScriptChecker.SEVERITY_VALUES;
	private StringDialogField fPath;
	private StringDialogField fArguments;
	private SelectionButtonDialogField fStyle;
	private SelectionButtonDialogField fSyntax;
	private ComboDialogField fSeverity;

	private Table fTable;
	private TableViewer fTableViewer;

	private Button fAddButton;
	private Button fRemoveButton;

	private Composite fControl;

	public ScriptCheckerConfigurationPage() {
	}

	public void applyChanges() {
		ScriptChecker scriptChecker = getScriptChecker();
		scriptChecker.setPath(this.fPath.getText());
		scriptChecker.setArguments(this.fArguments.getText());
		scriptChecker.setSeverity(this.fSeverity.getText());
		scriptChecker.setStyle(this.fStyle.isSelected());
		scriptChecker.setSyntax(this.fSyntax.isSelected());
	}

	class ATSStyleContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object input) {
			return ATSGuideManager.getInstance().getGuides();
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}
	}

	class ATSStyleLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof GuideNode) {
				GuideNode node = (GuideNode) element;
				switch (columnIndex) {
				case 0:
					return node.getPattern();
				case 1:
					return node.getUri();
				}
			}
			return element.toString();
		}

		/**
		 * @see ITableLabelProvider#getColumnImage(Object, int)
		 */
		public Image getColumnImage(Object element, int columnIndex) {
			if (columnIndex == 0) {
			}
			return null;
		}

	}

	protected Button createPushButton(Composite parent, String label) {
		return SWTUtil.createPushButton(parent, label, null);
	}

	public void createControl(final Composite parent, int columns) {
		fControl = parent;
		createFields();

		createBrowseButton(parent, columns);
		this.fArguments.doFillIntoGrid(parent, columns);
		createLabel(
				parent,
				"(%f will be replaced to filename, if no -- are specified, then it added before all arguments)",
				columns);

		// Creating ASTSTyle preferences
		createOptions(parent, 1);
		createATSStyle(parent, columns - 1);

		updateValuesFrom();
	}

	private void createOptions(final Composite parent, int columns) {
		Group options = new Group(parent, SWT.NONE);
		GridData data = new GridData(SWT.FILL, SWT.FILL, false, true);
		data.horizontalSpan = columns;
// data.heightHint = 100;
		data.widthHint = 150;
		data.minimumWidth = 150;
		options.setLayoutData(data);
		options.setText("Options");
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		options.setLayout(layout);

		this.fSeverity.doFillIntoGrid(options, 2);
		this.fStyle.doFillIntoGrid(options, 2);
		this.fSyntax.doFillIntoGrid(options, 2);
	}

	private void createATSStyle(final Composite parent, int columns) {
		Group atsStyle = new Group(parent, SWT.NONE);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.horizontalSpan = columns;
		data.heightHint = 200;
		data.widthHint = 200;
		data.minimumWidth = 100;
		atsStyle.setLayoutData(data);
		atsStyle.setText("Guidelines");
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		atsStyle.setLayout(layout);

		Font font = parent.getFont();

		fTable = new Table(atsStyle, SWT.BORDER | SWT.MULTI
				| SWT.FULL_SELECTION);

		data = new GridData(GridData.FILL_BOTH);
		data.widthHint = 450;
		fTable.setLayoutData(data);
		fTable.setFont(font);

		fTable.setHeaderVisible(true);
		fTable.setLinesVisible(true);

		TableColumn column1 = new TableColumn(fTable, SWT.NULL);
		column1.setText("ID");
		column1.setWidth(50);

		TableColumn column2 = new TableColumn(fTable, SWT.NULL);
		column2.setText("Page source");
		column2.setWidth(100);

		fTableViewer = new TableViewer(fTable);

		fTableViewer.setContentProvider(new ATSStyleContentProvider());
		fTableViewer.setLabelProvider(new ATSStyleLabelProvider());

		Composite buttons = new Composite(atsStyle, SWT.NULL);
		buttons.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		buttons.setLayout(layout);
		buttons.setFont(font);

		fAddButton = createPushButton(buttons, "Add");
		fAddButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event evt) {
				addGuide();
			}
		});

		fRemoveButton = createPushButton(buttons, "Remove");
		fRemoveButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event evt) {
				removeGuide();
			}
		});
		fTableViewer.setInput(new Integer(0));
		fTableViewer.refresh();
	}

	protected void removeGuide() {
		ISelection selection = this.fTableViewer.getSelection();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection sel = (IStructuredSelection) selection;
			Iterator iterator = sel.iterator();
			while (iterator.hasNext()) {
				Object element = iterator.next();
				if (element instanceof GuideNode) {
					ATSGuideManager.getInstance().removeGuide(
							(GuideNode) element);
					this.fTableViewer.refresh();
				}
			}
		}
	}

	protected void addGuide() {
		AddGuideDialog dialog = new AddGuideDialog(fControl.getShell(), null);

		dialog.setTitle("Add ats style guide");
		if (dialog.open() != Window.OK) {
			return;
		}
		ATSGuideManager.getInstance().load();
// ATSGuideManager.getInstance().addGuide("S9.1", "http://guide0");
		this.fTableViewer.refresh();
	}

	private ScriptChecker getScriptChecker() {
		return (ScriptChecker) getValidator();
	}

	private void updateValuesFrom() {
		ScriptChecker scriptChecker = getScriptChecker();
		this.fPath.setText(scriptChecker.getPath());
		this.fArguments.setText(scriptChecker.getArguments());
		this.fStyle.setSelection(scriptChecker.isStyle());
		this.fSyntax.setSelection(scriptChecker.isSyntax());
		this.fSeverity.selectItem(scriptChecker.getSeverity());
	}

	private void createLabel(Composite parent, String content, int columns) {
		Label l = new Label(parent, SWT.None);
		l.setText(content);
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = columns;
		l.setLayoutData(gd);
	}

	private void createBrowseButton(final Composite parent, int columns) {
		this.fPath.doFillIntoGrid(parent, columns - 1);
		Text path = this.fPath.getTextControl(parent);
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = columns - 2;
		path.setLayoutData(gd);
		// Browse
		Button browse = new Button(parent, SWT.PUSH);
		browse.setText("Browse...");
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

	private void createFields() {
		this.fPath = new StringDialogField();
		this.fPath.setLabelText("Script Checker Path:");

		this.fPath.setDialogFieldListener(new IDialogFieldListener() {
			public void dialogFieldChanged(DialogField field) {
				validateScriptCheckerPath();
			}
		});

		this.fArguments = new StringDialogField();
		this.fArguments.setLabelText("Arguments");

		this.fStyle = new SelectionButtonDialogField(SWT.CHECK);
		this.fStyle.setLabelText("Check Style Guidelines");

		this.fSyntax = new SelectionButtonDialogField(SWT.CHECK);
		this.fSyntax.setLabelText("Check Syntax");

		this.fSeverity = new ComboDialogField(SWT.DROP_DOWN | SWT.READ_ONLY);
		this.fSeverity.setLabelText("Severity");
		this.fSeverity.setItems(SEVERITY);
	}

	protected void validateScriptCheckerPath() {
	}

	public void dispose() {
		super.dispose();
	}
	
}
