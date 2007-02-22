package org.eclipse.dltk.internal.ui.text;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.reconciler.DirtyRegion;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.ui.texteditor.ITextEditor;

public class ScriptCompositeReconcilingStrategy extends CompositeReconcilingStrategy
{
	private ITextEditor fEditor;
	private ScriptReconcilingStrategy fScriptStrategy;

	/**
	 * Creates a new script reconciling strategy.
	 *
	 * @param editor the editor of the strategy's reconciler
	 * @param documentPartitioning the document partitioning this strategy uses for configuration
	 */
	public ScriptCompositeReconcilingStrategy(ITextEditor editor, String documentPartitioning) {
		fEditor= editor;
		fScriptStrategy= new ScriptReconcilingStrategy(editor);
		setReconcilingStrategies(new IReconcilingStrategy[] {
			fScriptStrategy
		});
	}
	
	/*
	 * @see org.eclipse.jface.text.reconciler.CompositeReconcilingStrategy#reconcile(org.eclipse.jface.text.reconciler.DirtyRegion, org.eclipse.jface.text.IRegion)
	 */
	public void reconcile(DirtyRegion dirtyRegion, IRegion subRegion) {								
		super.reconcile(dirtyRegion, subRegion);		
	}

	/*
	 * @see org.eclipse.jface.text.reconciler.CompositeReconcilingStrategy#reconcile(org.eclipse.jface.text.IRegion)
	 */
	public void reconcile(IRegion partition) {		
		super.reconcile(partition);		
	}

	/**
	 * Tells this strategy whether to inform its listeners.
	 *
	 * @param notify <code>true</code> if listeners should be notified
	 */
	public void notifyListeners(boolean notify) {
		System.out.println( "add notify listeners code here" );
	}

	public void initialReconcile() {		
		super.initialReconcile();		
	}

	/**
	 * Called before reconciling is started.
	 *
	 *
	 */
	public void aboutToBeReconciled() {
		fScriptStrategy.aboutToBeReconciled();
	}
}
