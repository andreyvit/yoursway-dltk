/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.editor;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;


/**
 * Model element hyperlink.
 *
	 *
 */
public class ModelElementHyperlink implements IHyperlink {

	private final IRegion fRegion;
	private final IAction fOpenAction;


	/**
	 * Creates a new Script element hyperlink.
	 */
	public ModelElementHyperlink(IRegion region, IAction openAction) {
		Assert.isNotNull(openAction);
		Assert.isNotNull(region);

		fRegion= region;
		fOpenAction= openAction;
	}
	
	public IRegion getHyperlinkRegion() {
		return fRegion;
	}
	
	public void open() {
		fOpenAction.run();
	}

	public String getTypeLabel() {
		return null;
	}
	
	public String getHyperlinkText() {
		return null;
	}
}
