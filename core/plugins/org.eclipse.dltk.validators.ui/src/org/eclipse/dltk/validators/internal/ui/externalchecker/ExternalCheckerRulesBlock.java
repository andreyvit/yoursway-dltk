/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
package org.eclipse.dltk.validators.internal.ui.externalchecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.dltk.internal.ui.util.SWTUtil;
import org.eclipse.dltk.internal.ui.util.TableLayoutComposite;
import org.eclipse.dltk.ui.util.PixelConverter;
import org.eclipse.dltk.validators.internal.core.externalchecker.CustomWildcard;
import org.eclipse.dltk.validators.internal.core.externalchecker.ExternalCheckerWildcardManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class ExternalCheckerRulesBlock  {

	private Composite fControl;

	Table fTable;
	TableViewer tViewer;
	private String[] columnNames = new String[] { "CHARACTER", "PATTERN",
			"DESCRIPTION" };
	private CustomWildcardsList wlist = new CustomWildcardsList();
	private Button addWCard;
	private Button removeWCard;

	public ExternalCheckerRulesBlock() {
	}

	public void refresh() {
		tViewer.refresh();
	}

	public void loadWildcards() {
		// String preference =
		// ValidatorsUI.getDefault().getPreferenceStore().getString("wildcards");
		wlist.getWcards().clear();
		List wildcards = new ArrayList();
		
		wildcards = ExternalCheckerWildcardManager.loadCustomWildcards();
		for (int i = 0; i < wildcards.size(); i++) {
			wlist.addWcard((CustomWildcard)wildcards.get(i));
		}
	}

	public void createControl(Composite ancestor) {
		loadWildcards();

		Composite parent = new Composite(ancestor, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginHeight = 0;
		layout.marginWidth = 0;

		Font font = ancestor.getFont();
		parent.setFont(font);
		parent.setLayout(layout);

		fControl = parent;

		GridData data;

		PixelConverter conv = new PixelConverter(parent);
		data = new GridData(GridData.FILL_BOTH);
		data.widthHint = conv.convertWidthInCharsToPixels(50);
		TableLayoutComposite tblComposite = new TableLayoutComposite(parent,
				SWT.NONE);
		tblComposite.setLayoutData(data);
		fTable = new Table(tblComposite, SWT.BORDER | SWT.MULTI
				| SWT.FULL_SELECTION);

		data = new GridData(GridData.FILL_BOTH);
		data.widthHint = 450;
		fTable.setLayoutData(data);
		fTable.setFont(font);

		fTable.setHeaderVisible(true);
		fTable.setLinesVisible(true);

		TableColumn col1 = new TableColumn(fTable, SWT.LEFT, 0);
		col1.setWidth(70);
		col1.setText("Char");

		TableColumn col2 = new TableColumn(fTable, SWT.LEFT, 1);
		col2.setWidth(130);
		col2.setText("Pattern");

		TableColumn col3 = new TableColumn(fTable, SWT.LEFT, 2);
		col3.setWidth(100);
		col3.setText("Description");

		tViewer = new TableViewer(fTable);
		tViewer.setColumnProperties(columnNames);
		CellEditor[] editors = new CellEditor[columnNames.length];

		TextCellEditor textEditor = new TextCellEditor(fTable);
		((Text) textEditor.getControl()).setTextLimit(60);
		editors[0] = textEditor;

		TextCellEditor textEditor2 = new TextCellEditor(fTable);
		editors[1] = textEditor2;

		TextCellEditor textEditor3 = new TextCellEditor(fTable);
		editors[2] = textEditor3;

		tViewer.setCellEditors(editors);

		tViewer.setCellModifier(new WildcardCellModifier(this));
		tViewer.setContentProvider(new WildcardContentProvider());
		tViewer.setLabelProvider(new WildcardLabelProvider());
		tViewer.setInput(getWlist());

		Composite buttons = new Composite(parent, SWT.NULL);
		buttons.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		buttons.setLayout(layout);
		buttons.setFont(font);

		addWCard = SWTUtil.createPushButton(buttons, "Add", null);
		addWCard.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent ev) {
				getWlist().addWcard();
			}
		});

		removeWCard = SWTUtil.createPushButton(buttons, "Remove", null);
		removeWCard.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent ev) {
				CustomWildcard rule = (CustomWildcard) ((IStructuredSelection) tViewer
						.getSelection()).getFirstElement();
				if (rule != null)
					wlist.removeWcard(rule);
			}
		});
	}

	public void createControl2(Composite ancestor) {
		loadWildcards();
		Composite parent = new Composite(ancestor, SWT.NULL);
		// GridLayout layout = new GridLayout();
		// layout.numColumns = 2;
		// layout.marginHeight = 0;
		// layout.marginWidth = 0;

		Font font = ancestor.getFont();
		parent.setFont(font);
		// parent.setLayout(layout);

		fControl = parent;

		// GridData data;
		GridLayout layout = (GridLayout) ancestor.getLayout();

		fTable = new Table(ancestor, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.HIDE_SELECTION);

		fTable.setLayout(layout);
		fTable.setLinesVisible(true);
		fTable.setHeaderVisible(true);
		fTable.setSize(500, 500);

		TableColumn col1 = new TableColumn(fTable, SWT.LEFT, 0);
		col1.setWidth(200);
		col1.setText("Char");

		TableColumn col2 = new TableColumn(fTable, SWT.LEFT, 1);
		col2.setWidth(100);
		col2.setText("Pattern");

		tViewer = new TableViewer(fTable);
		tViewer.setColumnProperties(columnNames);
		CellEditor[] editors = new CellEditor[columnNames.length];

		TextCellEditor textEditor = new TextCellEditor(fTable);
		((Text) textEditor.getControl()).setTextLimit(60);
		editors[0] = textEditor;

		TextCellEditor textEditor2 = new TextCellEditor(fTable);
		editors[1] = textEditor2;

		tViewer.setCellEditors(editors);

		tViewer.setCellModifier(new WildcardCellModifier(this));
		tViewer.setContentProvider(new WildcardContentProvider());
		tViewer.setLabelProvider(new WildcardLabelProvider());
		tViewer.setInput(getWlist());

		addWCard = new Button(ancestor, SWT.PUSH);
		addWCard.setText("Add Rule");
		addWCard.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent ev) {
				getWlist().addWcard();
			}
		});

	}

	public Control getControl() {
		return fControl;
	}

	protected Shell getShell() {
		return getControl().getShell();
	}

	public List getColumnNames() {
		return Arrays.asList(columnNames);
	}

	public void setWlist(CustomWildcardsList wlist) {
		this.wlist = wlist;
	}

	public CustomWildcardsList getWlist() {
		return wlist;
	}

	class WildcardContentProvider implements IStructuredContentProvider,
			IWildcardListViewer {
		public Object[] getElements(Object inputElement) {
			return getWlist().getWcards().toArray();
		}

		public void dispose() {
			getWlist().removeChangeListener(this);
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			if (newInput != null)
				((CustomWildcardsList) newInput).addChangeListener(this);
			if (oldInput != null)
				((CustomWildcardsList) oldInput).removeChangeListener(this);
		}

		public void addWildcard(CustomWildcard r) {
			tViewer.add(r);
		}

		public void removeWildcard(CustomWildcard r) {
			tViewer.remove(r);
		}

		public void updateWildcard(CustomWildcard r) {
			tViewer.update(r, null);
		}

	}

	public void removeAll() {
		getWlist().removeAll();
	}
}
