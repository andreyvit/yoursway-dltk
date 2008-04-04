package org.eclipse.dltk.debug.ui.actions;

import java.util.Arrays;
import java.util.LinkedList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ITypeHierarchy;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.ui.dialogs.TypeSelectionExtension;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

/**
 * Provides a type dialog extension for the JDT type selection dialog
 * 
 * @since 3.4
 */
public class AddExceptionTypeDialogExtension extends TypeSelectionExtension {
	
	/**
	  * widgets
	  */
	 private Button fCaughtButton;
	 private Button fUncaughtButton;
	 private boolean fCaught = false;
	 private boolean fUncaught = false;
	protected Object fExceptionBaseClassName;
	
	 /**
	 * Constructor
	 * @param caught
	 * @param uncaught
	 */
	public AddExceptionTypeDialogExtension(String fExceptionBaseClassName, boolean caught, boolean uncaught) {
		 fCaught = caught;
		 fUncaught = uncaught;
	 }
	 
	/* (non-Javadoc)
	 * @see org.eclipse.jdt.ui.dialogs.TypeSelectionExtension#createContentArea(org.eclipse.swt.widgets.Composite)
	 */
	public Control createContentArea(Composite parent) {
		Composite comp = SWTFactory.createComposite(parent, parent.getFont(), 1, 1, GridData.FILL_HORIZONTAL);
		fCaughtButton = SWTFactory.createCheckButton(comp, Messages.AddExceptionTypeDialogExtension_suspendOnCaught, null, fCaught, 1);
		fCaughtButton.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {}
			public void widgetSelected(SelectionEvent e) {
				fCaught = fCaughtButton.getSelection();
			}
		});
		fUncaughtButton = SWTFactory.createCheckButton(comp, Messages.AddExceptionTypeDialogExtension_SuspendOnUncaught, null, fUncaught, 1);
		fUncaughtButton.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {}
			public void widgetSelected(SelectionEvent e) {
				fUncaught = fUncaughtButton.getSelection();
			}
		});
		return comp;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jdt.ui.dialogs.TypeSelectionExtension#getSelectionValidator()
	 */
	public ISelectionStatusValidator getSelectionValidator() {
		return new ISelectionStatusValidator() {
			public IStatus validate(Object[] selection) {
				if(selection.length == 1) {
					// if any class can be thrown return OK
					if (fExceptionBaseClassName == null) {
						return Status.OK_STATUS;
					}

					// else check that selected class was enherited from exception base class
					try {
		 	    		LinkedList queue = new LinkedList();
						IType type = (IType) selection[0];
			            ITypeHierarchy hierarchy = type.newSupertypeHierarchy(new NullProgressMonitor());
			            IType curr = type;
			            while (curr != null) {
			                if (fExceptionBaseClassName.equals(curr.getFullyQualifiedName("."))) { //$NON-NLS-1$
			                    return Status.OK_STATUS;
			                }
		 	                IType[] superclasses = hierarchy.getSuperclass(curr);
		 	                if (superclasses != null) 	                	
		 	                	queue.addAll(Arrays.asList(superclasses));
		 	                
		 	                if (queue.size() > 0)
		 	                	curr = (IType) queue.removeFirst();
		 	                else 
		 	                	curr = null;
			            }
			        } 
			        catch (ModelException e) {
			        	DLTKDebugUIPlugin.log(e);
			        	return Status.CANCEL_STATUS;
			        }
				}
				return new Status(IStatus.ERROR, DLTKDebugUIPlugin.getUniqueIdentifier(), Messages.AddExceptionTypeDialogExtension_selectedItemIsNotAnException);
			}
			
		};
	}
	
	/**
	 * Returns if the breakpoint should be set to suspend when the associated exception is thrown, but caught
	 * @return if the breakpoint should be set to suspend when the associated exception is thrown, but caught
	 */
	public boolean shouldHandleCaughtExceptions() {
		return fCaught;
	}
	
	/**Returns if the breakpoint should be set to suspend when the associated exception is thrown, but not caught
	 * @return if the breakpoint should be set to suspend when the associated exception is thrown, but not caught
	 */
	public boolean shouldHandleUncaughtExceptions() {
		return fUncaught;
	}
}
