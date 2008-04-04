/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.dltk.internal.ui.editor.semantic.highlighting;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.ui.editor.DLTKEditorMessages;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.PositionUpdater.UpdateResult;
import org.eclipse.dltk.internal.ui.text.IScriptReconcilingListener;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextInputListener;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPartSite;

/**
 * Semantic highlighting reconciler - Background thread implementation.
 * 
 * @since 3.0
 */
public class SemanticHighlightingReconciler implements
		IScriptReconcilingListener, ITextInputListener {

	/** The Java editor this semantic highlighting reconciler is installed on */
	private ScriptEditor fEditor;
	/** The source viewer this semantic highlighting reconciler is installed on */
	private ISourceViewer fSourceViewer;
	/** The semantic highlighting presenter */
	private SemanticHighlightingPresenter fPresenter;
	/** Semantic highlightings */
	private SemanticHighlighting[] fSemanticHighlightings;
	/** Highlightings */
	private Highlighting[] fHighlightings;

	/** Background job's added highlighted positions */
	private List fAddedPositions = new ArrayList();
	/** Background job's removed highlighted positions */
	private List fRemovedPositions = new ArrayList();

	/** Background job */
	private Job fJob;
	/** Background job lock */
	private final Object fJobLock = new Object();
	/**
	 * Reconcile operation lock.
	 * 
	 * @since 3.2
	 */
	private final Object fReconcileLock = new Object();
	/**
	 * <code>true</code> if any thread is executing <code>reconcile</code>,
	 * <code>false</code> otherwise.
	 * 
	 * @since 3.2
	 */
	private boolean fIsReconciling = false;

	/**
	 * The semantic highlighting presenter - cache for background thread, only
	 * valid during
	 * {@link #reconciled(ModuleDeclaration, boolean, IProgressMonitor)}
	 */
	private SemanticHighlightingPresenter fJobPresenter;
	/**
	 * Semantic highlightings - cache for background thread, only valid during
	 * {@link #reconciled(ModuleDeclaration, boolean, IProgressMonitor)}
	 */
	private SemanticHighlighting[] fJobSemanticHighlightings;
	/**
	 * Highlightings - cache for background thread, only valid during
	 * {@link #reconciled(ModuleDeclaration, boolean, IProgressMonitor)}
	 */
	private Highlighting[] fJobHighlightings;
	private PositionUpdater positionUpdated;

	/*
	 * @see org.eclipse.jdt.internal.ui.text.Script.IScriptReconcilingListener#aboutToBeReconciled()
	 */
	public void aboutToBeReconciled() {
		// Do nothing
	}

	/*
	 * @see org.eclipse.jdt.internal.ui.text.Script.IScriptReconcilingListener#reconciled(ModuleDeclaration,
	 *      boolean, IProgressMonitor)
	 */
	public void reconciled(ISourceModule ast, boolean forced,
			IProgressMonitor progressMonitor) {
		if (positionUpdated == null)
			return;
		// ensure at most one thread can be reconciling at any time
		synchronized (fReconcileLock) {
			if (fIsReconciling)
				return;
			else
				fIsReconciling = true;
		}
		fJobPresenter = fPresenter;
		fJobSemanticHighlightings = fSemanticHighlightings;
		fJobHighlightings = fHighlightings;

		try {
			long t0 = System.currentTimeMillis();
			if (fJobPresenter == null || fJobSemanticHighlightings == null
					|| fJobHighlightings == null)
				return;

			fJobPresenter.setCanceled(progressMonitor.isCanceled());

			if (ast == null || fJobPresenter.isCanceled())
				return;

			startReconcilingPositions();

			if (!fJobPresenter.isCanceled())
				reconcilePositions(ast, fPresenter);

			TextPresentation textPresentation = null;
			if (!fJobPresenter.isCanceled())
				textPresentation = fJobPresenter.createPresentation(
						fAddedPositions, fRemovedPositions);

			if (!fJobPresenter.isCanceled())
				updatePresentation(textPresentation, fAddedPositions,
						fRemovedPositions);

			stopReconcilingPositions();
			// long t1 = System.currentTimeMillis();
			// System.out.println(t1 - t0);

		} finally {
			fJobPresenter = null;
			fJobSemanticHighlightings = null;
			fJobHighlightings = null;
			synchronized (fReconcileLock) {
				fIsReconciling = false;
			}
		}
	}

	/**
	 * Start reconciling positions.
	 */
	private void startReconcilingPositions() {
		fJobPresenter.addAllPositions(fRemovedPositions);
		// fNOfRemovedPositions = fRemovedPositions.size();
	}

	/**
	 * Reconcile positions based on the AST subtrees
	 * 
	 * @param presenter
	 * @param ast
	 * 
	 * @param subtrees
	 *            the AST subtrees
	 */
	private void reconcilePositions(ISourceModule ast,
			SemanticHighlightingPresenter presenter) {
		// FIXME: remove positions not covered by subtrees
		// for (int i= 0, n= subtrees.length; i < n; i++)
		// subtrees[i].accept(fCollector);

		ArrayList list = new ArrayList();
		presenter.addAllPositions(list);
		UpdateResult reconcile = positionUpdated.reconcile(ast, presenter,
				fHighlightings, list);
		fAddedPositions = reconcile.addedPositions;
		fRemovedPositions = reconcile.removedPositions;
	}

	/**
	 * Update the presentation.
	 * 
	 * @param textPresentation
	 *            the text presentation
	 * @param addedPositions
	 *            the added positions
	 * @param removedPositions
	 *            the removed positions
	 */
	private void updatePresentation(TextPresentation textPresentation,
			List addedPositions, List removedPositions) {
		Runnable runnable = fJobPresenter.createUpdateRunnable(
				textPresentation, addedPositions, removedPositions);
		if (runnable == null)
			return;

		ScriptEditor editor = fEditor;
		if (editor == null)
			return;

		IWorkbenchPartSite site = editor.getSite();
		if (site == null)
			return;

		Shell shell = site.getShell();
		if (shell == null || shell.isDisposed())
			return;

		Display display = shell.getDisplay();
		if (display == null || display.isDisposed())
			return;

		display.asyncExec(runnable);
	}

	/**
	 * Stop reconciling positions.
	 */
	private void stopReconcilingPositions() {
		fRemovedPositions.clear();
		// fNOfRemovedPositions = 0;
		fAddedPositions.clear();
	}

	public void install(ScriptEditor editor, ISourceViewer sourceViewer,
			SemanticHighlightingPresenter presenter,
			SemanticHighlighting[] semanticHighlightings,
			Highlighting[] highlightings) {
		fPresenter = presenter;
		ScriptTextTools textTools = editor.getTextTools();
		if (textTools != null) {
			this.positionUpdated = textTools.getSemanticPositionUpdater();
		}
		fSemanticHighlightings = semanticHighlightings;
		fHighlightings = highlightings;

		fEditor = editor;
		fSourceViewer = sourceViewer;

		if (fEditor instanceof ScriptEditor) {
			((ScriptEditor) fEditor).addReconcileListener(this);
		} else if (fEditor == null) {
			fSourceViewer.addTextInputListener(this);
			scheduleJob();
		}
	}

	/**
	 * Uninstall this reconciler from the editor
	 */
	public void uninstall() {
		if (fPresenter != null)
			fPresenter.setCanceled(true);

		if (fEditor != null) {
			if (fEditor instanceof ScriptEditor)
				((ScriptEditor) fEditor).removeReconcileListener(this);
			else
				fSourceViewer.removeTextInputListener(this);
			fEditor = null;
		}

		fSourceViewer = null;
		fSemanticHighlightings = null;
		fHighlightings = null;
		fPresenter = null;
	}

	/**
	 * Schedule a background job for retrieving the AST and reconciling the
	 * Semantic Highlighting model.
	 */
	private void scheduleJob() {
		final IModelElement element = fEditor.getInputModelElement();

		synchronized (fJobLock) {
			final Job oldJob = fJob;
			if (fJob != null) {
				fJob.cancel();
				fJob = null;
			}

			if (element != null) {
				fJob = new Job(DLTKEditorMessages.SemanticHighlighting_job) {
					protected IStatus run(IProgressMonitor monitor) {
						if (oldJob != null) {
							try {
								oldJob.join();
							} catch (InterruptedException e) {
								DLTKUIPlugin.log(e);
								return Status.CANCEL_STATUS;
							}
						}
						if (monitor.isCanceled())
							return Status.CANCEL_STATUS;
						ISourceModule ast = null;
						// /
						// /DLTKUIPlugin.getDefault().getASTProvider().getAST(element,
						// ASTProvider.WAIT_YES, monitor);
						reconciled(ast, false, monitor);
						synchronized (fJobLock) {
							// allow the job to be gc'ed
							if (fJob == this)
								fJob = null;
						}
						return Status.OK_STATUS;
					}
				};
				fJob.setSystem(true);
				fJob.setPriority(Job.DECORATE);
				fJob.schedule();
			}
		}
	}

	/*
	 * @see org.eclipse.jface.text.ITextInputListener#inputDocumentAboutToBeChanged(org.eclipse.jface.text.IDocument,
	 *      org.eclipse.jface.text.IDocument)
	 */
	public void inputDocumentAboutToBeChanged(IDocument oldInput,
			IDocument newInput) {
		synchronized (fJobLock) {
			if (fJob != null) {
				fJob.cancel();
				fJob = null;
			}
		}
	}

	/*
	 * @see org.eclipse.jface.text.ITextInputListener#inputDocumentChanged(org.eclipse.jface.text.IDocument,
	 *      org.eclipse.jface.text.IDocument)
	 */
	public void inputDocumentChanged(IDocument oldInput, IDocument newInput) {
		if (newInput != null)
			scheduleJob();
	}

	/**
	 * Refreshes the highlighting.
	 * 
	 * @since 3.2
	 */
	public void refresh() {
		scheduleJob();
	}
}
