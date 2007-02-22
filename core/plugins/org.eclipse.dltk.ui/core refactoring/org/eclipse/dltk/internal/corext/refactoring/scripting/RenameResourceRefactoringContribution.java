/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.scripting;

import org.eclipse.dltk.internal.corext.refactoring.ScriptRefactoringContribution;
import org.eclipse.dltk.internal.corext.refactoring.rename.RenameResourceProcessor;
import org.eclipse.dltk.internal.corext.refactoring.rename.ScriptRenameRefactoring;
import org.eclipse.ltk.core.refactoring.Refactoring;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;


/**
 * Refactoring contribution for the rename resource refactoring.
 * 
	 *
 */
public final class RenameResourceRefactoringContribution extends ScriptRefactoringContribution {

	/**
	 * {@inheritDoc}
	 */
	public Refactoring createRefactoring(final RefactoringDescriptor descriptor) {
		return new ScriptRenameRefactoring(new RenameResourceProcessor(null));
	}
}
