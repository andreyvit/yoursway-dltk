package org.eclipse.dltk.ruby.internal.ui.text.folding;

import org.eclipse.core.runtime.ILog;

import org.eclipse.dltk.ast.declarations.ISourceParser;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ruby.internal.parser.JRubySourceParser;
import org.eclipse.dltk.ruby.internal.ui.RubyUI;
import org.eclipse.dltk.ruby.internal.ui.text.RubyPartitionScanner;
import org.eclipse.dltk.ruby.ui.text.IRubyPartitions;
import org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;


public class RubyFoldingStructureProvider extends AbstractASTFoldingStructureProvider {

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
    * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#initiallyCollapse(org.eclipse.dltk.ast.statements.Statement, org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider.FoldingStructureComputationContext)
     */
    protected boolean initiallyCollapse(Statement s,
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
     * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#mayCollapse(org.eclipse.dltk.ast.statements.Statement, org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider.FoldingStructureComputationContext)
     */
    protected boolean mayCollapse(Statement s,
        FoldingStructureComputationContext ctx) {
        return true;
    }

    /*
     * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getCommentPartitionType()
     */
    protected String getCommentPartition() {
        return IRubyPartitions.RUBY_COMMENT;
    }

    /*
     * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getPartition()
     */
    protected String getPartition() {
        return IRubyPartitions.RUBY_PARTITIONING;
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
        return IRubyPartitions.RUBY_PARTITION_TYPES;
    }

    /*
     * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getSourceParser()
     */
    protected ISourceParser getSourceParser() {
        return new JRubySourceParser(null);
    }

    /*
    * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getLog()
     */
    protected ILog getLog() {
        return RubyUI.getDefault().getLog();
    }

}
