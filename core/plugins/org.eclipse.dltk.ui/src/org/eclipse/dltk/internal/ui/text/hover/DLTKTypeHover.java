/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.text.hover;

import org.eclipse.dltk.ui.text.hover.IScriptEditorTextHover;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IEditorPart;



public class DLTKTypeHover implements IScriptEditorTextHover {

	//private IEditorTextHover fProblemHover;
	private IScriptEditorTextHover fDocumentationHover;

	public DLTKTypeHover() {
		//fProblemHover= new ProblemHover();
		fDocumentationHover= new DocumentationHover();
	}

	/*
	 * @see IJavaEditorTextHover#setEditor(IEditorPart)
	 */
	public void setEditor(IEditorPart editor) {
		//fProblemHover.setEditor(editor);
		fDocumentationHover.setEditor(editor);
	}
	
	public void setPreferenceStore(IPreferenceStore store) {
		fDocumentationHover.setPreferenceStore(store);
	}

	/*
	 * @see ITextHover#getHoverRegion(ITextViewer, int)
	 */
	public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
		return fDocumentationHover.getHoverRegion(textViewer, offset);
	}

	/*
	 * @see ITextHover#getHoverInfo(ITextViewer, IRegion)
	 */
	public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
		/*String hoverInfo= fProblemHover.getHoverInfo(textViewer, hoverRegion);
		if (hoverInfo != null)
			return hoverInfo;*/

		return fDocumentationHover.getHoverInfo(textViewer, hoverRegion);
	}

	
}
