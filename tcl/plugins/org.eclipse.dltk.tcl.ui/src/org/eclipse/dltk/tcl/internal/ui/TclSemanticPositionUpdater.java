package org.eclipse.dltk.tcl.internal.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.internal.ui.editor.SemanticHighlightingManager.HighlightedPosition;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.Highlighting;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.PositionUpdater;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.SemanticHighlightingPresenter;
import org.eclipse.dltk.tcl.ui.semantilhighlighting.ISemanticHighlightingExtension;

public class TclSemanticPositionUpdater extends PositionUpdater {
	private HashSet currentPositions = new HashSet();
	private ISemanticHighlightingExtension[] extensions;

	public TclSemanticPositionUpdater(
			ISemanticHighlightingExtension[] extensions) {
		this.extensions = extensions;
	}

	private List calculateNewPositions(ISourceModule ast,
			final SemanticHighlightingPresenter presenter,
			final Highlighting[] highlightings) {
		final ArrayList result = new ArrayList();
		try {
			ModuleDeclaration module = SourceParserUtil.getModuleDeclaration(
					ast, null);
			module.traverse(new ASTVisitor() {

				public boolean visitGeneral(ASTNode node) throws Exception {
					for (int i = 0; i < extensions.length; i++) {
						HighlightedPosition[] poss = extensions[i]
								.calculatePositions(node, presenter,
										highlightings);
						if (poss != null) {
							for (int j = 0; j < poss.length; j++) {
								result.add(poss[j]);
							}
						}

					}
					return true;
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
