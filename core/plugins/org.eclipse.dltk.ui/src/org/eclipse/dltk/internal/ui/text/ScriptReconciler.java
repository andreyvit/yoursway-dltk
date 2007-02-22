package org.eclipse.dltk.internal.ui.text;

import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.jface.text.reconciler.MonoReconciler;
import org.eclipse.ui.texteditor.ITextEditor;

public class ScriptReconciler extends MonoReconciler
{
	public ScriptReconciler( ITextEditor editor, IReconcilingStrategy strategy, boolean isIncremental ) {
		super(strategy, isIncremental);
	}
}
