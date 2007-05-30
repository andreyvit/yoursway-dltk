package org.eclipse.dltk.validators.internal.ui;

import java.util.Arrays;
import java.util.List;

import org.eclipse.dltk.validators.internal.core.externalchecker.CustomWildcard;
import org.eclipse.dltk.validators.internal.ui.eternalchecker.CustomWildcardsList;
import org.eclipse.dltk.validators.internal.ui.eternalchecker.IWildcardListViewer;
import org.eclipse.dltk.validators.internal.ui.eternalchecker.WildcardCellModifier;
import org.eclipse.dltk.validators.internal.ui.eternalchecker.WildcardLabelProvider;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class ConfigureWildcardsDialog extends StatusDialog {
	
	Table fTable;
	TableViewer tViewer;
	private String[] columnNames = new String[] {"CHARACTER", "PATTERN"};
	private CustomWildcardsList wlist = new CustomWildcardsList();
	private Button addWCard;
	
	public ConfigureWildcardsDialog(Shell shelll){
		super(shelll);
	}
	
	protected Control createDialogArea(Composite ancestor) {
        
		GridLayout layout = (GridLayout)ancestor.getLayout();    		
		
		  
	    fTable =new Table(ancestor, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | 
				SWT.FULL_SELECTION | SWT.HIDE_SELECTION);    		
				
		
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
        tViewer.setInput(wlist);
        
        
        addWCard= new Button(ancestor, SWT.PUSH);
        addWCard.setText("Add Rule");
        addWCard.addSelectionListener(new SelectionAdapter(){
        	public void widgetSelected(SelectionEvent ev){
        		wlist.addWcard();
        	}
        });
	
		
		
		
		return ancestor;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
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
	
	class WildcardContentProvider implements IStructuredContentProvider, IWildcardListViewer {
		public Object[] getElements(Object inputElement) {
			return wlist.getWcards().toArray(); 
		}

		public void dispose() {
			wlist.removeChangeListener(this);
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			  if (newInput != null)
	              ((CustomWildcardsList) newInput).addChangeListener(this);
	          if (oldInput != null)
	              ((CustomWildcardsList)oldInput).removeChangeListener(this);
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

}
