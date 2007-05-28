package org.eclipse.dltk.validators.internal.ui.eternalchecker;

import org.eclipse.dltk.internal.ui.util.SWTUtil;
import org.eclipse.dltk.internal.ui.util.TableLayoutComposite;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringDialogField;
import org.eclipse.dltk.ui.util.PixelConverter;
import org.eclipse.dltk.validators.ValidatorConfigurationPage;
import org.eclipse.dltk.validators.internal.core.externalchecker.ExternalChecker;
import org.eclipse.dltk.validators.internal.ui.ValidatorMessages;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class ExternalCheckerConfigurationPage extends
		ValidatorConfigurationPage {

	private StringDialogField fArguments;
	private StringDialogField fCommannd;
	protected CheckboxTableViewer fValidatorList;
	private Table fTable;
	private Button addRuleButtton;
	private StringDialogField fSyntax;
	private StringDialogField fSyntax2;
	
	
	public class RuleContentProvider{
		
	}
	
	public ExternalCheckerConfigurationPage() {
	}

	public void applyChanges() {
		ExternalChecker externalChecker = getExtrenalChecker();
    	externalChecker.setArguments(this.fArguments.getText());
		externalChecker.setCommand(this.fCommannd.getText());
	
	}

	public void createControl(final Composite ancestor, int columns ) {
		createFields();
	
		Composite parent = new Composite(ancestor, SWT.NULL);
		GridLayout layout = (GridLayout)ancestor.getLayout();
	//	GridLayout layout = new GridLayout();
	//	layout.numColumns = columns;
	//	layout.marginHeight = 0;
	//	layout.marginWidth = 0;

		Font font = ancestor.getFont();
//		parent.setFont(font);
		parent.setLayout(layout);

		GridData data =new GridData();

		Label tableLabel = new Label(parent, SWT.NONE);
		tableLabel.setText(ValidatorMessages.InstalledValidatorBlock_15);
		data.horizontalSpan = 3;
		
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
		column1.setText("Hello");
		column1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
			}
		});

	
		fValidatorList = new CheckboxTableViewer(fTable);
	//	fValidatorList.setLabelProvider(new ValidatorLabelProvider());
	//	fValidatorList.setContentProvider(new ValidatorContentProvider());
		fValidatorList.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
			}
		});
		
	
		fValidatorList
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent evt) {
					}
				});
		fValidatorList.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent e) {
			}
		});
		fTable.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
			}
		});
		
		addRuleButtton = SWTUtil.createPushButton(ancestor,"AddRule" ,null);
		addRuleButtton.addListener(SWT.Selection, new Listener(){
			public void handleEvent(Event ev){
				addRule(new Composite(ancestor, SWT.NULL));
			}
			
		});
		
	//	this.fCommannd.doFillIntoGrid(parent, columns);
///		this.fArguments.doFillIntoGrid(parent, columns);
	
		this.fSyntax.doFillIntoGrid(parent, columns);
		this.fSyntax2.doFillIntoGrid(parent, columns);
		
//		updateValuesFrom();
	}
	private ExternalChecker getExtrenalChecker() {
		return (ExternalChecker)getValidator();
	
	}
	private void updateValuesFrom() {
		ExternalChecker externalChecker = getExtrenalChecker();
			this.fArguments.setText(externalChecker.getArguments());
			this.fCommannd.setText(externalChecker.getCommand());
		
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
		this.fSyntax = new StringDialogField();
		this.fSyntax.setLabelText("Syntaz");
		
		this.fSyntax2 = new StringDialogField();
		this.fSyntax2.setLabelText("Syntaz");
		
		this.fCommannd = new StringDialogField();
		this.fCommannd.setLabelText("command to run checker");
		this.fArguments = new StringDialogField();
		this.fArguments.setLabelText("Arguments");
	}
	
	public  void addRule(Control control){
		
		AddRuleDialog addDialog = new AddRuleDialog(control.getShell());
	}
}
