/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.dltk.internal.ui.editor.semantic.highlighting;



/**
 * Semantic highlighting
 */
public abstract class SemanticHighlighting {

	/**
	 * @return the preference key, will be augmented by a prefix and a suffix for each preference
	 */
	public abstract String getPreferenceKey();

	
		/**
	 * @return <code>true</code> if the text attribute italic is enabled by default
	 */
	public  boolean isEnabledByDefault(){return true;};

	/**
	 * @return the display name
	 */
	public  String getDisplayName(){return "";};

	

	public String getBackgroundPreferenceKey() {	
		return null;
	}
	
}
