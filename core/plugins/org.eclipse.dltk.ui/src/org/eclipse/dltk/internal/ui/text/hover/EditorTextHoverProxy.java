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
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHoverExtension;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.information.IInformationProviderExtension2;
import org.eclipse.ui.IEditorPart;



/**
 * Proxy for JavaEditorTextHovers.
 *
	 *
 */
public class EditorTextHoverProxy extends AbstractScriptEditorTextHover implements ITextHoverExtension, IInformationProviderExtension2 {

	private EditorTextHoverDescriptor fHoverDescriptor;
	private IScriptEditorTextHover fHover;

	public EditorTextHoverProxy(EditorTextHoverDescriptor descriptor, IEditorPart editor, IPreferenceStore store) {
		fHoverDescriptor= descriptor;
		setEditor(editor);
		setPreferenceStore(store);
	}
	
	public void setPreferenceStore(IPreferenceStore store) {
		super.setPreferenceStore(store);
		
		if (fHover != null)
			fHover.setPreferenceStore(getPreferenceStore());
	}

	/*
	 * @see IJavaEditorTextHover#setEditor(IEditorPart)
	 */
	public void setEditor(IEditorPart editor) {
		super.setEditor(editor);

		if (fHover != null)
			fHover.setEditor(getEditor());
	}

	public boolean isEnabled() {
		return true;
	}

	/*
	 * @see ITextHover#getHoverRegion(ITextViewer, int)
	 */
	public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
		if (ensureHoverCreated())
			return fHover.getHoverRegion(textViewer, offset);

		return null;
	}

	/*
	 * @see ITextHover#getHoverInfo(ITextViewer, IRegion)
	 */
	public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
		if (ensureHoverCreated())
			return fHover.getHoverInfo(textViewer, hoverRegion);

		return null;
	}

	private boolean ensureHoverCreated() {
		if (!isEnabled() || fHoverDescriptor == null)
			return false;
		return isCreated() || createHover();
	}

	private boolean isCreated() {
		return fHover != null;
	}

	private boolean createHover() {
		fHover= fHoverDescriptor.createTextHover();
		if (fHover != null) {
			fHover.setEditor(getEditor());
			fHover.setPreferenceStore(getPreferenceStore());
		}
		return isCreated();
	}

	/*
	 * @see org.eclipse.jface.text.ITextHoverExtension#getHoverControlCreator()
	 *
	 */
	public IInformationControlCreator getHoverControlCreator() {
		if (ensureHoverCreated() && (fHover instanceof ITextHoverExtension))
			return ((ITextHoverExtension)fHover).getHoverControlCreator();

		return null;
	}

	/*
	 * @see org.eclipse.jface.text.information.IInformationProviderExtension2#getInformationPresenterControlCreator()
	 */
	public IInformationControlCreator getInformationPresenterControlCreator() {
		if (ensureHoverCreated() && (fHover instanceof IInformationProviderExtension2))
			return ((IInformationProviderExtension2)fHover).getInformationPresenterControlCreator();

		return null;
	}

	
}
