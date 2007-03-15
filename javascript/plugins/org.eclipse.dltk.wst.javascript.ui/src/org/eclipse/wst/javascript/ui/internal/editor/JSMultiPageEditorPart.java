/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.MultiPageEditorSite;
import org.eclipse.wst.javascript.core.internal.contenttype.ContentTypeIdForJavaScript;
import org.eclipse.wst.javascript.ui.internal.actions.MultiPageEditorActionBarContributorJS;

public class JSMultiPageEditorPart extends MultiPageEditorPart {

	/**
	 * Internal part activation listener, copied from AbstractTextEditor
	 */
	private class ActivationListener implements IPartListener, IWindowListener {

		/** Cache of the active workbench part. */
		private IWorkbenchPart fActivePart;
		/** Indicates whether activation handling is currently be done. */
		private boolean fIsHandlingActivation = false;
		/**
		 * The part service.
		 * 
		 * @since 3.1
		 */
		private IPartService fPartService;

		/**
		 * Creates this activation listener.
		 * 
		 * @param partService
		 *            the part service on which to add the part listener
		 * @since 3.1
		 */
		public ActivationListener(IPartService partService) {
			fPartService = partService;
			fPartService.addPartListener(this);
			PlatformUI.getWorkbench().addWindowListener(this);
		}

		/**
		 * Disposes this activation listener.
		 * 
		 * @since 3.1
		 */
		public void dispose() {
			fPartService.removePartListener(this);
			PlatformUI.getWorkbench().removeWindowListener(this);
			fPartService = null;
		}

		/**
		 * Handles the activation triggering a element state check in the
		 * editor.
		 */
		void handleActivation() {
			if (fIsHandlingActivation || fEditor == null)
				return;

			if (fActivePart == JSMultiPageEditorPart.this) {
				fIsHandlingActivation = true;
				try {
					fEditor.safelySanityCheckState(getEditorInput());
				}
				finally {
					fIsHandlingActivation = false;
				}
			}
		}

		/*
		 * @see IPartListener#partActivated(org.eclipse.ui.IWorkbenchPart)
		 */
		public void partActivated(IWorkbenchPart part) {
			fActivePart = part;
			handleActivation();
		}

		/*
		 * @see IPartListener#partBroughtToTop(org.eclipse.ui.IWorkbenchPart)
		 */
		public void partBroughtToTop(IWorkbenchPart part) {
			// do nothing
		}

		/*
		 * @see IPartListener#partClosed(org.eclipse.ui.IWorkbenchPart)
		 */
		public void partClosed(IWorkbenchPart part) {
			// do nothing
		}

		/*
		 * @see IPartListener#partDeactivated(org.eclipse.ui.IWorkbenchPart)
		 */
		public void partDeactivated(IWorkbenchPart part) {
			fActivePart = null;
		}

		/*
		 * @see IPartListener#partOpened(org.eclipse.ui.IWorkbenchPart)
		 */
		public void partOpened(IWorkbenchPart part) {
			// do nothing
		}

		/*
		 * @see org.eclipse.ui.IWindowListener#windowActivated(org.eclipse.ui.IWorkbenchWindow)
		 * @since 3.1
		 */
		public void windowActivated(IWorkbenchWindow window) {
			if (window == getEditorSite().getWorkbenchWindow()) {
				/*
				 * Workaround for problem described in
				 * http://dev.eclipse.org/bugs/show_bug.cgi?id=11731 Will be
				 * removed when SWT has solved the problem.
				 */
				window.getShell().getDisplay().asyncExec(new Runnable() {
					public void run() {
						handleActivation();
					}
				});
			}
		}

		/*
		 * @see org.eclipse.ui.IWindowListener#windowClosed(org.eclipse.ui.IWorkbenchWindow)
		 * @since 3.1
		 */
		public void windowClosed(IWorkbenchWindow window) {
			// do nothing
		}

		/*
		 * @see org.eclipse.ui.IWindowListener#windowDeactivated(org.eclipse.ui.IWorkbenchWindow)
		 * @since 3.1
		 */
		public void windowDeactivated(IWorkbenchWindow window) {
			// do nothing
		}

		/*
		 * @see org.eclipse.ui.IWindowListener#windowOpened(org.eclipse.ui.IWorkbenchWindow)
		 * @since 3.1
		 */
		public void windowOpened(IWorkbenchWindow window) {
			// do nothing
		}
	}

	private class SourcePagePropertyListener implements IPropertyListener {
		public void propertyChanged(Object source, int propId) {
			switch (propId) {
				case IEditorPart.PROP_INPUT : {
					if (source == fEditor) {
						if (fEditor.getEditorInput() != getEditorInput()) {
							setInput(fEditor.getEditorInput());
							setPartName(fEditor.getEditorInput().getName());
						}
					}
					break;
				}
				case IWorkbenchPart.PROP_TITLE : {
					if (source == fEditor) {
						if (fEditor.getEditorInput() != getEditorInput()) {
							setInput(fEditor.getEditorInput());
						}
					}
					break;
				}
				default : {
					if (source == fEditor) {
						firePropertyChange(propId);
					}
					break;
				}
			}
		}
	}

	private ActivationListener fActivationListener;
	JSEditor fEditor = null;
	private JSPreviewPage fPreviewPage = null;
	private int fPreviewPageIndex = 1;
	private int fSourcePageIndex = 0;
	private IPropertyListener fSourcePagePropertyListener;
	private Image fTitleImage = null;

	/**
	 * Adds the preview page of the multi-page editor.
	 */
	private void addPreviewPage() {
		fPreviewPageIndex = addPage(fPreviewPage.getControl());
		setPageText(fPreviewPageIndex, JavaScriptUIMessages.Preview);
	}

	/**
	 * Adds the source page of the multi-page editor.
	 */
	private void addSourcePage() {
		try {
			fEditor = new JSEditor();
			fEditor.setEditorPart(this);
			fSourcePagePropertyListener = new SourcePagePropertyListener();
			fEditor.addPropertyListener(fSourcePagePropertyListener);
			fSourcePageIndex = addPage(fEditor, getEditorInput());
			setPageText(fSourcePageIndex, JavaScriptUIMessages.Source);
		}
		catch (PartInitException exception) {
			// dispose editor
			dispose();

			throw new RuntimeException(exception);
		}
	}

	protected void createPages() {
		addSourcePage();

		/*
		 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=87657 used this check
		 * for SWTError in attempt to work around "blocker" mentioned in 87657
		 */
		try {
			fPreviewPage = createPreviewPage();
			addPreviewPage();
		}
		catch (SWTError e) {
			Logger.logException(e);
		}

		// use the javascript icon with beige box instead of the
		// javascript icon for the navigator view so the letter J is
		// visible
		fTitleImage = JSEditorPluginImageHelper.getInstance().getImageDescriptor(JSEditorPluginImages.IMG_OBJ_JAVASCRIPT_VIEW).createImage();
		setTitleImage(fTitleImage);
	}

	/**
	 * Adds the preview page of the multi-page editor.
	 */
	private JSPreviewPage createPreviewPage() {
		return new JSPreviewPage(getContainer(), fEditor);
	}

	protected IEditorSite createSite(IEditorPart editor) {
		IEditorSite site = null;
		if (editor == fEditor) {
			site = new MultiPageEditorSite(this, editor) {
				/**
				 * @see org.eclipse.ui.part.MultiPageEditorSite#getActionBarContributor()
				 */
				public IEditorActionBarContributor getActionBarContributor() {
					IEditorActionBarContributor contributor = super.getActionBarContributor();
					IEditorActionBarContributor multiContributor = JSMultiPageEditorPart.this.getEditorSite().getActionBarContributor();
					if (multiContributor instanceof MultiPageEditorActionBarContributorJS) {
						contributor = ((MultiPageEditorActionBarContributorJS) multiContributor).getSourceViewerActionContributor();
					}
					return contributor;
				}

				public String getId() {
					// sets this id so nested editor is considered js source
					// page
					return ContentTypeIdForJavaScript.ContentTypeID_JAVASCRIPT + ".source"; //$NON-NLS-1$;
				}
			};
		}
		else {
			site = super.createSite(editor);
		}
		return site;
	}

	public void dispose() {
		if (fSourcePagePropertyListener != null) {
			fEditor.removePropertyListener(fSourcePagePropertyListener);
			fSourcePagePropertyListener = null;
		}

		if (fActivationListener != null) {
			fActivationListener.dispose();
			fActivationListener = null;
		}

		// super.dispose() will take care of properly disposing nested editors, including fEditor
//		if (fEditor != null)
//			fEditor.dispose();

		if (fTitleImage != null)
			fTitleImage.dispose();
		
		// BUG144666 - instance of JavaScript editor leaks every time opened/closed
		super.dispose();
	}

	public void doSave(IProgressMonitor monitor) {
		fEditor.doSave(monitor);
	}

	public void doSaveAs() {
		fEditor.doSaveAs();
	}

	public Object getAdapter(Class required) {
		Object o = super.getAdapter(required);
		if (o == null) {
			o = fEditor.getAdapter(required);
		}
		return o;
	}

	public String getTitle() {
		if (getEditorInput() != null) {
			return getEditorInput().getName();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.MultiPageEditorPart#init(org.eclipse.ui.IEditorSite,
	 *      org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
		setPartName(input.getName());

		// we want to listen for our own activation
		fActivationListener = new ActivationListener(site.getWorkbenchWindow().getPartService());
	}

	public boolean isSaveAsAllowed() {
		return fEditor.isSaveAsAllowed();
	}

	protected void pageChange(int newPageIndex) {
		if (newPageIndex == fPreviewPageIndex)
			fPreviewPage.refresh();

		super.pageChange(newPageIndex);
	}
}
