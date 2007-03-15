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
package org.eclipse.wst.javascript.ui.internal.common.taginfo;



import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.information.InformationPresenter;

/**
 * Presents tooltip information for javascript code (in javascript editor)
 * @author amywu
 */
public class JavaScriptInformationPresenter extends InformationPresenter {
	IInformationProvider fProvider = null;

	public JavaScriptInformationPresenter(IInformationControlCreator creator) {
		super(creator);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.information.IInformationPresenter#getInformationProvider(java.lang.String)
	 */
	public IInformationProvider getInformationProvider(String contentType) {
		if (fProvider == null) {
			fProvider = new JavaScriptInformationProvider();
		}
		return fProvider;
	}

}
