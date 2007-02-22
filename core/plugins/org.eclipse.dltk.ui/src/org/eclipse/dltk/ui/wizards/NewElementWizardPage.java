/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.ui.wizards;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.internal.ui.dialogs.StatusInfo;
import org.eclipse.dltk.internal.ui.dialogs.StatusUtil;
import org.eclipse.jface.wizard.WizardPage;


/**
 * Base class for wizard page responsible to create model elements. The class
 * provides API to update the wizard's status line and OK button according to
 * the value of a <code>IStatus</code> object.
 * 
 * <p>
 * Clients may subclass.
 * </p>
 * 
 *
 */
public abstract class NewElementWizardPage extends WizardPage {

	private IStatus currStatus;

	private boolean pageVisible;

	/**
	 * Creates a <code>NewElementWizardPage</code>.
	 * 
	 * @param name
	 *            the wizard page's name
	 */
	public NewElementWizardPage(String name) {
		super(name);
		pageVisible = false;
		currStatus = new StatusInfo();
	}

	public void setVisible(boolean visible) {
		super.setVisible(visible);
		pageVisible = visible;
		// policy: wizards are not allowed to come up with an error message
		if (visible && currStatus.matches(IStatus.ERROR)) {
			StatusInfo status = new StatusInfo();
			status.setError(""); //$NON-NLS-1$
			currStatus = status;
		}
		updateStatus(currStatus);
	}

	/**
	 * Updates the status line and the OK button according to the given status
	 * 
	 * @param status
	 *            status to apply
	 */
	protected void updateStatus(IStatus status) {
		currStatus = status;
		
		setPageComplete(!status.matches(IStatus.ERROR));
		
		if (pageVisible) {
			StatusUtil.applyToStatusLine(this, status);
		}
	}

	/**
	 * Updates the status line and the OK button according to the status
	 * evaluate from an array of status. The most severe error is taken. In case
	 * that two status with the same severity exists, the status with lower
	 * index is taken.
	 * 
	 * @param status
	 *            the array of status
	 */
	protected void updateStatus(IStatus[] status) {
		updateStatus(StatusUtil.getMostSevere(status));
	}

}
