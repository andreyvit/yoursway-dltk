package org.eclipse.dltk.xotcl.internal.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.Highlighting;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.PositionUpdater;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.SemanticHighlightingPresenter;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclDocumentationNode;

public class XOTclSemanticPositionUpdater extends PositionUpdater {
	private HashSet currentPositions = new HashSet();

	private List calculateNewPositions(ISourceModule ast,
			final SemanticHighlightingPresenter presenter,
			final Highlighting[] highlightings) {
		final ArrayList result = new ArrayList();
		try {
			ModuleDeclaration module = SourceParserUtil.getModuleDeclaration(
					ast, null);
			module.traverse(new ASTVisitor() {

				public boolean visit(Statement s) throws Exception {
					if (s instanceof TclStatement) {
						TclStatement st = (TclStatement) s;
						if (st.getAt(0) instanceof SimpleReference
								&& ((SimpleReference) st.getAt(0)).getName()
										.equals("@")) {
							result.add(presenter.createHighlightedPosition(st
									.sourceStart(), st.sourceEnd()
									- st.sourceStart(), highlightings[0]));
						}
					} else if (s instanceof XOTclDocumentationNode) {
						result.add(presenter.createHighlightedPosition(s
								.sourceStart(),
								s.sourceEnd() - s.sourceStart(),
								highlightings[0]));
					}
					return super.visit(s);
				}
			});
		} catch (ModelException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public UpdateResult reconcile(ISourceModule ast,
			SemanticHighlightingPresenter presenter,
			Highlighting[] highlightings, List currentPositions) {
		this.currentPositions = new HashSet(currentPositions);
		// TODO Auto-generated method stub
		List calculateNewPositions = calculateNewPositions(ast, presenter,
				highlightings);
		Iterator it = calculateNewPositions.iterator();
		ArrayList addedPositions = new ArrayList();

		HashSet removed = new HashSet(currentPositions);
		while (it.hasNext()) {
			Object o = it.next();
			if (currentPositions.contains(o)) {
				removed.remove(o);
			} else {
				addedPositions.add(o);
			}
		}
		ArrayList removedPositions = new ArrayList(removed);
		this.currentPositions = new HashSet(calculateNewPositions);
		return new UpdateResult(addedPositions, removedPositions);
	}
}
