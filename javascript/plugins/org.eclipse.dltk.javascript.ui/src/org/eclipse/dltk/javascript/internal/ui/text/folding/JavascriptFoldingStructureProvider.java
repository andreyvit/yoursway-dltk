package org.eclipse.dltk.javascript.internal.ui.text.folding;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.ILog;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.ISourceParser;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.internal.javascript.parser.JavaScriptSourceParser;
import org.eclipse.dltk.javascript.internal.ui.JavaScriptUI;
import org.eclipse.dltk.javascript.internal.ui.text.JavascriptPartitionScanner;
import org.eclipse.dltk.javascript.ui.text.IJavaScriptPartitions;
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
import org.eclipse.jface.text.rules.IPartitionTokenScanner;

public class JavascriptFoldingStructureProvider extends AbstractASTFoldingStructureProvider {
	
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
				IJavaScriptPartitions.JS_STRING, IJavaScriptPartitions.JS_COMMENT, IDocument.DEFAULT_CONTENT_TYPE
		};
		FastPartitioner partitioner = new FastPartitioner(new JavascriptPartitionScanner(), types);
		partitioner.connect(document);
		document.setDocumentPartitioner(IJavaScriptPartitions.JS_PARTITIONING, partitioner);
	}

	/**
	 * Removes partitioner with <code>document</code>.
	 * 
	 * @param document
	 *            the document
	 */
	private void removeDocumentStuff(Document document) {
		document.setDocumentPartitioner(IJavaScriptPartitions.JS_PARTITIONING, null);
	}

	private ITypedRegion getRegion(IDocument d, int offset) throws BadLocationException {
		return TextUtilities.getPartition(d, IJavaScriptPartitions.JS_PARTITIONING, offset, true);
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
						&& (region.getType().equals(IJavaScriptPartitions.JS_COMMENT) 
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
		JavaScriptSourceParser pp = new JavaScriptSourceParser();
		ModuleDeclaration md = pp.parse(contents.toCharArray(), null);
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

	protected boolean initiallyCollapse(ASTNode s, FoldingStructureComputationContext ctx) {		
		return false;		
	}

	protected boolean initiallyCollapseComments(FoldingStructureComputationContext ctx) {
		return ctx.allowCollapsing() && fInitCollapseComments;
	}

	protected boolean mayCollapse(ASTNode s, FoldingStructureComputationContext ctx) {		
		return true;
	}
	
	protected boolean collapseEmptyLines() {
		return fFoldNewLines;
	}

	protected String getCommentPartition() {
		return IJavaScriptPartitions.JS_COMMENT;
	}

	protected ILog getLog() {
		return JavaScriptUI.getDefault().getLog();
	}

	protected String getPartition() {
		return IJavaScriptPartitions.JS_PARTITIONING;
	}

	protected IPartitionTokenScanner getPartitionScanner() {
		return new JavascriptPartitionScanner();
	}

	protected String[] getPartitionTypes() {
		return IJavaScriptPartitions.JS_PARTITION_TYPES;
	}

	protected ISourceParser getSourceParser() {
		return new JavaScriptSourceParser();
	}

	protected boolean initiallyCollapse(Statement s,
			FoldingStructureComputationContext ctx) {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean mayCollapse(Statement s,
			FoldingStructureComputationContext ctx) {
		return false;
	}
}
