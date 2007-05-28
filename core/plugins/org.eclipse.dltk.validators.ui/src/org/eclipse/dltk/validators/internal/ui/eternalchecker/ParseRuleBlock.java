package org.eclipse.dltk.validators.internal.ui.eternalchecker;


import org.eclipse.dltk.internal.ui.util.TableLayoutComposite;
import org.eclipse.dltk.ui.util.PixelConverter;
import org.eclipse.dltk.validators.core.IValidator;
import org.eclipse.dltk.validators.internal.ui.ValidatorMessages;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class ParseRuleBlock implements ISelectionProvider {

	protected CheckboxTableViewer fRulesList;
	private Composite fControl;
	private Table fTable;
//	private Button fAddButton;
//	private Button fRemoveButton;
//	private Button fEditButton;
	
	public ParseRuleBlock(){
		
	}
	
	public void addSelectionChangedListener(ISelectionChangedListener listener) {

	}

	public ISelection getSelection() {
		return null;
	}

	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {

	}

	public void setSelection(ISelection selection) {

	}
	
	public void createControl(Composite ancestor) {

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

		Label tableLabel = new Label(parent, SWT.NONE);
		tableLabel.setText(ValidatorMessages.InstalledValidatorBlock_15);
		data = new GridData();
		data.horizontalSpan = 2;
		tableLabel.setLayoutData(data);
		tableLabel.setFont(font);

		PixelConverter conv = new PixelConverter(parent);
		data = new GridData(GridData.FILL_BOTH);
		data.widthHint = conv.convertWidthInCharsToPixels(50);
		TableLayoutComposite tblComposite = new TableLayoutComposite(parent,
				SWT.NONE);
		tblComposite.setLayoutData(data);
		fTable = new Table(tblComposite, SWT.CHECK | SWT.BORDER | SWT.MULTI
				| SWT.FULL_SELECTION);

		data = new GridData(GridData.FILL_BOTH);
		data.widthHint = 450;
		fTable.setLayoutData(data);
		fTable.setFont(font);

		fTable.setHeaderVisible(true);
		fTable.setLinesVisible(true);

		TableColumn column1 = new TableColumn(fTable, SWT.NULL);
		column1.setText(ValidatorMessages.InstalledValidatorBlock_0);
		column1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
			}
		});

		TableColumn column2 = new TableColumn(fTable, SWT.NULL);
		column2.setText(ValidatorMessages.InstalledValidatorBlock_2);
		column2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
			}
		});

		TableColumn column3 = new TableColumn(fTable, SWT.NULL);
		column3.setText(ValidatorMessages.InstalledValidatorBlock_1);
		// column3.addSelectionListener(new SelectionAdapter() {
		// public void widgetSelected(SelectionEvent e) {
		// sortByLocation();
		// }
		// });

		fRulesList = new CheckboxTableViewer(fTable);
		//fRulesList.setLabelProvider(new ValidatorLabelProvider());
		//fRulesList.setContentProvider(new ValidatorContentProvider());
		fRulesList.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				IValidator validator = (IValidator)event.getElement();
				validator.setActive(event.getChecked());
			}
		});
		
		// by default, sort by name
	
		fRulesList
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent evt) {
					}
				});

		// fValidatorList.addCheckStateListener(new ICheckStateListener() {
		// public void checkStateChanged(CheckStateChangedEvent event) {
		// if (event.getChecked()) {
		// setCheckedInterpreter((IValidator)event.getElement());
		// } else {
		// setCheckedInterpreter(null);
		// }
		// }
		// });

		fTable.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
				if (event.character == SWT.DEL && event.stateMask == 0) {
				}
			}
		});

		Composite buttons = new Composite(parent, SWT.NULL);
		buttons.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		buttons.setLayout(layout);
		buttons.setFont(font);



		// copied from ListDialogField.CreateSeparator()
		Label separator = new Label(buttons, SWT.NONE);
		separator.setVisible(false);
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.heightHint = 4;
		separator.setLayoutData(gd);

	}
	
	public Control getControl() {
		return fControl;
	}

}
