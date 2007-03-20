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
package org.eclipse.dltk.internal.ui.text.hover;


import org.eclipse.dltk.internal.ui.text.DLTKWordFinder;
import org.eclipse.dltk.internal.ui.text.HTMLTextPresenter;
import org.eclipse.dltk.ui.text.hover.IScriptEditorTextHover;
import org.eclipse.jface.internal.text.html.BrowserInformationControl;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.information.IInformationProviderExtension2;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;



public class ScriptInformationProvider implements IInformationProvider, IInformationProviderExtension2 {

	class EditorWatcher implements IPartListener {

		public void partOpened(IWorkbenchPart part) {
		}

		public void partDeactivated(IWorkbenchPart part) {
		}

		public void partClosed(IWorkbenchPart part) {
			if (part == fEditor) {
				fEditor.getSite().getWorkbenchWindow().getPartService().removePartListener(fPartListener);
				fPartListener= null;
			}
		}

		public void partActivated(IWorkbenchPart part) {
			update();
		}

		public void partBroughtToTop(IWorkbenchPart part) {
			update();
		}
	}

	protected IEditorPart fEditor;
	protected IPartListener fPartListener;

	protected String fCurrentPerspective;
	protected IScriptEditorTextHover fImplementation;

	/**
	 * The presentation control creator.
	 */
	private IInformationControlCreator fPresenterControlCreator;

	public ScriptInformationProvider(IEditorPart editor) {

		fEditor= editor;

		if (fEditor != null) {

			fPartListener= new EditorWatcher();
			IWorkbenchWindow window= fEditor.getSite().getWorkbenchWindow();
			window.getPartService().addPartListener(fPartListener);

			update();
		}
	}

	protected void update() {

		IWorkbenchWindow window= fEditor.getSite().getWorkbenchWindow();
		IWorkbenchPage page= window.getActivePage();
		if (page != null) {

			IPerspectiveDescriptor perspective= page.getPerspective();
			if (perspective != null)  {
				String perspectiveId= perspective.getId();

				if (fCurrentPerspective == null || fCurrentPerspective != perspectiveId) {
					fCurrentPerspective= perspectiveId;

					fImplementation= new DLTKTypeHover();
					fImplementation.setEditor(fEditor);
				}
			}
		}
	}
	
	public IRegion getSubject(ITextViewer textViewer, int offset) {

		if (textViewer != null)
			return DLTKWordFinder.findWord(textViewer.getDocument(), offset);

		return null;
	}

	public String getInformation(ITextViewer textViewer, IRegion subject) {
		if (fImplementation != null) {
			String s= fImplementation.getHoverInfo(textViewer, subject);
			if (s != null && s.trim().length() > 0) {
				return s;
			}
		}

		return null;
	}
	
	public IInformationControlCreator getInformationPresenterControlCreator() {
		if (fPresenterControlCreator == null) {
			fPresenterControlCreator= new AbstractReusableInformationControlCreator() {
				
				public IInformationControl doCreateInformationControl(Shell parent) {
					int shellStyle= SWT.RESIZE | SWT.TOOL;
					int style= SWT.V_SCROLL | SWT.H_SCROLL;
					if (BrowserInformationControl.isAvailable(parent))
						return new BrowserInformationControl(parent, shellStyle, style);
					else
						return new DefaultInformationControl(parent, shellStyle, style, new HTMLTextPresenter(false));
				}
			};
		}
		return fPresenterControlCreator;
	}
}
