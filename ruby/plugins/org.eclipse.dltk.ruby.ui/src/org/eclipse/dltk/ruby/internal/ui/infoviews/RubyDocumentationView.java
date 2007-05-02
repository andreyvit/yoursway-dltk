/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.infoviews;

import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.internal.ui.RubyUI;
import org.eclipse.dltk.ui.infoviews.AbstractDocumentationView;
import org.eclipse.jface.preference.IPreferenceStore;


public class RubyDocumentationView extends AbstractDocumentationView {
	protected IPreferenceStore getPreferenceStore() {
		return RubyUI.getDefault().getPreferenceStore();
	}

	protected String getNature() {
		return RubyNature.NATURE_ID;
	}
}
