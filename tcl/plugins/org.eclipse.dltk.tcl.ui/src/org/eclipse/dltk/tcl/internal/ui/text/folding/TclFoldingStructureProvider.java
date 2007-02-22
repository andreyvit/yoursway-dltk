package org.eclipse.dltk.tcl.internal.ui.text.folding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.internal.parser.TclSourceParser;
import org.eclipse.dltk.tcl.internal.ui.text.TclPartitionScanner;
import org.eclipse.dltk.tcl.ui.TclPreferenceConstants;
import org.eclipse.dltk.tcl.ui.text.TclPartitions;
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


public class TclFoldingStructureProvider extends AbstractASTFoldingStructureProvider {
	
	/* preferences */
	private int fBlockFolding = 0;
	private List fBlockIncludeList = new ArrayList();
	private List fBlockExcludeList = new ArrayList();
	private boolean fInitCollapseComments = true;
	private boolean fInitCollapseBlocks = true;
	private boolean fInitCollapseNamespaces = true;
	private boolean fFoldNewLines = true;
	
	
	protected void initializePreferences(IPreferenceStore store) {
		super.initializePreferences(store);
		fBlockFolding = store.getInt(TclPreferenceConstants.EDITOR_FOLDING_BLOCKS);
		String t = store.getString(TclPreferenceConstants.EDITOR_FOLDING_EXCLUDE_LIST);
		String items[] = t.split(",");
		fBlockExcludeList.clear();
		for (int i = 0; i < items.length; i++) {
			if (items[i].trim().length() > 0)
				fBlockExcludeList.add(items[i]);
		}
		t = store.getString(TclPreferenceConstants.EDITOR_FOLDING_INCLUDE_LIST);
		items = t.split(",");
		fBlockIncludeList.clear();
		for (int i = 0; i < items.length; i++) {
			if (items[i].trim().length() > 0)
				fBlockIncludeList.add(items[i]);
		}
		fFoldNewLines = store.getBoolean(TclPreferenceConstants.EDITOR_FOLDING_COMMENTS_WITH_NEWLINES);
		fInitCollapseBlocks = store.getBoolean(TclPreferenceConstants.EDITOR_FOLDING_INIT_BLOCKS);
		fInitCollapseComments = store.getBoolean(TclPreferenceConstants.EDITOR_FOLDING_INIT_COMMENTS);
		fInitCollapseNamespaces = store.getBoolean(TclPreferenceConstants.EDITOR_FOLDING_INIT_NAMESPACES);
	}
	
	/**
	 * Installs a partitioner with <code>document</code>.
	 * 
	 * @param document
	 *            the document
	 */
	private void installDocumentStuff(Document document) {
		String[] types = new String[] {
				TclPartitions.TCL_STRING, TclPartitions.TCL_COMMENT, IDocument.DEFAULT_CONTENT_TYPE
		};
		FastPartitioner partitioner = new FastPartitioner(new TclPartitionScanner(), types);
		partitioner.connect(document);
		document.setDocumentPartitioner(TclPartitions.TCL_PARTITIONING, partitioner);
	}

	/**
	 * Removes partitioner with <code>document</code>.
	 * 
	 * @param document
	 *            the document
	 */
	private void removeDocumentStuff(Document document) {
		document.setDocumentPartitioner(TclPartitions.TCL_PARTITIONING, null);
	}

	private ITypedRegion getRegion(IDocument d, int offset) throws BadLocationException {
		return TextUtilities.getPartition(d, TclPartitions.TCL_PARTITIONING, offset, true);
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
						&& (region.getType().equals(TclPartitions.TCL_COMMENT)
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

	private CodeBlock[] computeBlockRanges(int offset, String contents) {
		TclSourceParser pp = new TclSourceParser();
		ModuleDeclaration md = pp.parse(contents);		
		List statements = md.getStatements();
		if (statements == null)
			return new CodeBlock[0];
		List result = new ArrayList();
		Iterator i = statements.iterator();
		while (i.hasNext()) {
			Statement sst = (Statement) i.next();
			if (sst instanceof TclStatement) {
				TclStatement statement = (TclStatement) sst;							
				result.add(new CodeBlock(statement,
						new Region(offset + statement.sourceStart(), statement.sourceEnd() - statement.sourceStart())));
				Iterator si = statement.getExpressions().iterator();
				while (si.hasNext()) {
					Expression ex = (Expression) si.next();
					if (ex instanceof TclBlockExpression) {						
						TclBlockExpression be = (TclBlockExpression) ex;
						try {
							String newContents = contents.substring(be.sourceStart() + 1,
								be.sourceEnd() - 1);
							CodeBlock[] cb = computeBlockRanges(offset + be.sourceStart() + 1, 
									newContents);
							for (int j = 0; j < cb.length; j++) {
								result.add(cb[j]);
							}
						} catch (StringIndexOutOfBoundsException e) {							
						}
					}
				}
			}
		}
		return (CodeBlock[]) result.toArray(new CodeBlock[result.size()]);
	}

	protected boolean initiallyCollapse(Statement s, FoldingStructureComputationContext ctx) {
		if (s instanceof TclStatement) {
			TclStatement statement = (TclStatement) s;
			if (!(statement.getAt(0) instanceof SimpleReference))
				return false;
			String name = null;
			name = ((SimpleReference) statement.getAt(0)).getName();
			if (name.equals("namespace")) {
				return ctx.allowCollapsing() && fInitCollapseNamespaces;
			}
			return ctx.allowCollapsing() && fInitCollapseBlocks;
		}
		return false;
		
	}

	protected boolean initiallyCollapseComments(FoldingStructureComputationContext ctx) {
		return ctx.allowCollapsing() && fInitCollapseComments;
	}

	protected boolean mayCollapse(Statement s, FoldingStructureComputationContext ctx) {
		if (s instanceof TclStatement) {
			TclStatement statement = (TclStatement) s;
			if (!(statement.getAt(0) instanceof SimpleReference))
				return false;
			String name = null;
			name = ((SimpleReference) statement.getAt(0)).getName();
			switch (fBlockFolding) {
				case TclPreferenceConstants.EDITOR_FOLDING_BLOCKS_OFF:
					if (name.equals("proc") || name.equals("namespace"))
						return true;
					return false;
				case TclPreferenceConstants.EDITOR_FOLDING_BLOCKS_INCLUDE:
					if (fBlockIncludeList.contains(name))
						return true;
					else
						return false;
				case TclPreferenceConstants.EDITOR_FOLDING_BLOCKS_EXCLUDE:
					if (fBlockExcludeList.contains(name))
						return false;
					else
						return true;
			}
			return false;
		}
		return false;
	}
	
	protected boolean collapseEmptyLines() {
		return fFoldNewLines;
	}
	
}
