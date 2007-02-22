package org.eclipse.dltk.ruby.internal.ui.text.folding;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.ruby.internal.parser.JRubySourceParser;
import org.eclipse.dltk.ruby.internal.ui.text.RubyPartitionScanner;
import org.eclipse.dltk.ruby.ui.text.IRubyPartitions;
import org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.rules.FastPartitioner;

public class RubyFoldingStructureProvider extends AbstractASTFoldingStructureProvider {
	
	/* preferences */
	private boolean fInitCollapseComments = true;
	private boolean fInitCollapseBlocks = true;
	private boolean fInitCollapseClasses = true;
	private boolean fFoldNewLines = true;
	
	
	protected void initializePreferences(IPreferenceStore store) {
		super.initializePreferences(store);
		fFoldNewLines = true;
		fInitCollapseBlocks = false;
		fInitCollapseComments = true;
		fInitCollapseClasses = false;
	}
	
	/**
	 * Installs a partitioner with <code>document</code>.
	 * 
	 * @param document
	 *            the document
	 */
	private void installDocumentStuff(Document document) {
		String[] types = new String[] {
				IRubyPartitions.RUBY_STRING, IRubyPartitions.RUBY_DOC, IRubyPartitions.RUBY_COMMENT, IDocument.DEFAULT_CONTENT_TYPE
		};
		FastPartitioner partitioner = new FastPartitioner(new RubyPartitionScanner(), types);
		partitioner.connect(document);
		document.setDocumentPartitioner(IRubyPartitions.RUBY_PARTITIONING, partitioner);
	}

	/**
	 * Removes partitioner with <code>document</code>.
	 * 
	 * @param document
	 *            the document
	 */
	private void removeDocumentStuff(Document document) {
		document.setDocumentPartitioner(IRubyPartitions.RUBY_PARTITIONING, null);
	}

	private ITypedRegion getRegion(IDocument d, int offset) throws BadLocationException {
		return TextUtilities.getPartition(d, IRubyPartitions.RUBY_PARTITIONING, offset, true);
	}
	
	protected final IRegion[] computeCommentsRanges(String contents) {
		try {
			if (contents == null)
				return new IRegion[0];
			List regions = new ArrayList();
			Document d = new Document(contents);
			installDocumentStuff(d);
			List docRegionList = new ArrayList();
			ITypedRegion region = null;
			int offset = 0;
			while (true) {
				try {
					region = getRegion(d, offset);
					docRegionList.add(region);
					offset = region.getLength() + region.getOffset() + 1;
				} catch (BadLocationException e1) {
					break;
				}
			}
			ITypedRegion docRegions[] = new ITypedRegion[docRegionList.size()];
			docRegionList.toArray(docRegions);
			IRegion fullRegion = null;
			int start = -1;
			for (int i = 0; i < docRegions.length; i++) {
				region = docRegions[i];
				boolean multiline = isMultilineRegion(d, region);
				boolean badStart = false;
				if (d.getLineOffset(d.getLineOfOffset(region.getOffset())) != region.getOffset()) {
					int lineStart = d.getLineOffset(d.getLineOfOffset(region.getOffset()));
					String lineStartStr = d.get(lineStart, region.getOffset() - lineStart);
					if (lineStartStr.trim().length() != 0)
						badStart = true;
				}
				if (!badStart
						&& (region.getType().equals(IRubyPartitions.RUBY_COMMENT) || region.getType().equals(IRubyPartitions.RUBY_DOC)
								|| (start != -1 && isEmptyRegion(d, region) && multiline && collapseEmptyLines()) || (start != -1
								&& isEmptyRegion(d, region) && !multiline))) {
					if (start == -1)
						start = i;
				} else {
					if (start != -1) {
						int offset0 = docRegions[start].getOffset();
						int length0 = docRegions[i - 1].getOffset() - offset0 + docRegions[i - 1].getLength() - 1;
						fullRegion = new Region(offset0, length0);
						if (isMultilineRegion(d, fullRegion)) {
							regions.add(fullRegion);
						}
					}
					start = -1;
				}
			}
			if (start != -1) {
				int offset0 = docRegions[start].getOffset();
				int length0 = docRegions[docRegions.length - 1].getOffset() - offset0 + docRegions[docRegions.length - 1].getLength() - 1;
				fullRegion = new Region(offset0, length0);
				if (isMultilineRegion(d, fullRegion)) {
					regions.add(fullRegion);
				}
			}
			removeDocumentStuff(d);
			IRegion[] result = new IRegion[regions.size()];
			regions.toArray(result);
			return result;
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return new IRegion[0];
	}
	
	protected CodeBlock[] getCodeBlocks(String code) {
		return computeBlockRanges(0, code);
	}

	private CodeBlock[] computeBlockRanges(final int offset, String contents) {
		JRubySourceParser pp = new JRubySourceParser(null);
		ModuleDeclaration md = pp.parse(contents);
		final List result = new ArrayList();
		ASTVisitor visitor = new ASTVisitor(){
			public boolean visit(MethodDeclaration s) throws Exception {										
				result.add(new CodeBlock(s,
						new Region(offset + s.sourceStart(), s.sourceEnd() - s.sourceStart())));
				return super.visit(s);
			}

			public boolean visit(TypeDeclaration s) throws Exception {
				result.add(new CodeBlock(s,
						new Region(offset + s.sourceStart(), s.sourceEnd() - s.sourceStart())));
				return super.visit(s);
			}
			
		};
		try {
			md.traverse(visitor);
		} catch (Exception e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
		}
		return (CodeBlock[]) result.toArray(new CodeBlock[result.size()]);
	}

	protected boolean initiallyCollapse(Statement s, FoldingStructureComputationContext ctx) {		
		return false;		
	}

	protected boolean initiallyCollapseComments(FoldingStructureComputationContext ctx) {
		return ctx.allowCollapsing() && fInitCollapseComments;
	}

	protected boolean mayCollapse(Statement s, FoldingStructureComputationContext ctx) {		
		return true;
	}
	
	protected boolean collapseEmptyLines() {
		return fFoldNewLines;
	}
	
}
