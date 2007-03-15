/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.editor;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.wst.javascript.ui.internal.common.JSSourceViewerConfiguration;


public class JSEditorSourceViewerConfiguration extends JSSourceViewerConfiguration {
	private IAnnotationHover fAnnotationHover = null;
	
	JSEditorSourceViewerConfiguration(IPreferenceStore store) {
		super(store);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getAnnotationHover(org.eclipse.jface.text.source.ISourceViewer)
	 */
	public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer) {
		if(fAnnotationHover == null) {
			fAnnotationHover = new JSAnnotationHover();
		}
		return fAnnotationHover;
	}
}
