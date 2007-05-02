/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.structure;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringCoreMessages;
import org.eclipse.dltk.internal.corext.refactoring.tagging.IScriptableRefactoring;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.MoveProcessor;
import org.eclipse.ltk.core.refactoring.participants.MoveRefactoring;
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;


/**
 * A move refactoring which can be initialized with refactoring arguments.
 * 
	 *
 */
public class ScriptMoveRefactoring extends MoveRefactoring implements IScriptableRefactoring {

	/**
	 * Creates a new script move refactoring.
	 * 
	 * @param processor
	 *            the move processor to use
	 */
	public ScriptMoveRefactoring(final MoveProcessor processor) {
		super(processor);
	}

	/**
	 * {@inheritDoc}
	 */
	public final RefactoringStatus initialize(final RefactoringArguments arguments) {
		Assert.isNotNull(arguments);
		final RefactoringProcessor processor= getProcessor();
		if (processor instanceof IScriptableRefactoring) {
			return ((IScriptableRefactoring) processor).initialize(arguments);
		}
		return RefactoringStatus.createFatalErrorStatus(Messages.format(RefactoringCoreMessages.ProcessorBasedRefactoring_error_unsupported_initialization, getProcessor().getIdentifier()));
	}
}
