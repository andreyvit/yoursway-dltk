/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.text.folding;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.internal.ui.RubyUI;
import org.eclipse.dltk.ruby.internal.ui.text.RubyPartitionScanner;
import org.eclipse.dltk.ruby.internal.ui.text.RubyPartitions;
import org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;

public class RubyFoldingStructureProvider extends
		AbstractASTFoldingStructureProvider {

	/* preferences */
	private boolean fInitCollapseComments = true;
	private boolean fInitCollapseBlocks = true;
	private boolean fInitCollapseClasses = true;

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

	/*
	 * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getCommentPartitionType()
	 */
	protected String getCommentPartition() {
		return RubyPartitions.RUBY_COMMENT;
	}

	/*
	 * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getPartition()
	 */
	protected String getPartition() {
		return RubyPartitions.RUBY_PARTITIONING;
	}

	/*
	 * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getPartitionScanner()
	 */
	protected IPartitionTokenScanner getPartitionScanner() {
		return new RubyPartitionScanner();
	}

	/*
	 * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getPartitionTypes()
	 */
	protected String[] getPartitionTypes() {
		return RubyPartitions.RUBY_PARTITION_TYPES;
	}

	/*
	 * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getNatureId()
	 */
	protected String getNatureId() {
		return RubyNature.NATURE_ID;
	}

	/*
	 * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getLog()
	 */
	protected ILog getLog() {
		return RubyUI.getDefault().getLog();
	}

}
