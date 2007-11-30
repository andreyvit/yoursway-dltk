/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.ui.text.folding;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.python.core.PythonNature;
import org.eclipse.dltk.python.internal.ui.PythonUI;
import org.eclipse.dltk.python.internal.ui.text.PythonPartitionScanner;
import org.eclipse.dltk.python.ui.text.IPythonPartitions;
import org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;

/**
 */
public class PythonFoldingStructureProvider extends
		AbstractASTFoldingStructureProvider {

	private boolean fInitCollapseBlocks = true;
	private boolean fInitCollapseClasses = true;

	/* preferences */
	private boolean fInitCollapseComments = true;

	public String getCommentPartition() {
		return IPythonPartitions.PYTHON_COMMENT;
	}

	protected ILog getLog() {
		return PythonUI.getDefault().getLog();
	}

	protected String getPartition() {
		return IPythonPartitions.PYTHON_PARTITIONING;
	}

	/*
	 * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getPartitionScanner()
	 */
	protected IPartitionTokenScanner getPartitionScanner() {
		return new PythonPartitionScanner();
	}

	/*
	 * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getPartitionTypes()
	 */
	protected String[] getPartitionTypes() {
		return IPythonPartitions.PYTHON_PARITION_TYPES;
	}

	/*
	 * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getNatureId()
	 */
	protected String getNatureId() {
		return PythonNature.NATURE_ID;
	}

	protected void initializePreferences(IPreferenceStore store) {
		super.initializePreferences(store);
		fFoldNewLines = true;
		fInitCollapseBlocks = false;
		fInitCollapseComments = true;
		fInitCollapseClasses = false;
	}

	/*
	 * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#initiallyCollapse(org.eclipse.dltk.ast.statements.Statement,
	 *      org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider.FoldingStructureComputationContext)
	 */
	protected boolean initiallyCollapse(ASTNode s,
			FoldingStructureComputationContext ctx) {
		return false;
	}

	/*
	 * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#initiallyCollapseComments(org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider.FoldingStructureComputationContext)
	 */
	protected boolean initiallyCollapseComments(
			FoldingStructureComputationContext ctx) {
		return ctx.allowCollapsing() && fInitCollapseComments;
	}

	/*
	 * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#mayCollapse(org.eclipse.dltk.ast.statements.Statement,
	 *      org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider.FoldingStructureComputationContext)
	 */
	protected boolean mayCollapse(ASTNode s,
			FoldingStructureComputationContext ctx) {
		return true;
	}

}
