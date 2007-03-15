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
package org.eclipse.wst.javascript.ui.internal.views.contentoutline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.wst.javascript.ui.internal.common.ContentElement;
import org.eclipse.wst.javascript.ui.internal.common.JSContentElementConstants;
import org.eclipse.wst.javascript.ui.internal.common.JSContentElementImpl;
import org.eclipse.wst.javascript.ui.internal.common.LexerCacheForJavaScript;
import org.eclipse.wst.javascript.ui.internal.editor.ContentElementProvider;


class JSContentElementProvider implements ContentElementProvider {


	public List getContentElements(Object inputElement) {
		List vec = null;

		//IPreferenceStore preferenceStore = JSEditorPlugin.getInstance().getPreferenceStore();
		//		boolean showVariables = true;//preferenceStore.getBoolean(JSEditorActionConstants.SHOW_VARIABLES);
		//		boolean showHierarchy = true;//preferenceStore.getBoolean(JSEditorActionConstants.SHOW_HIERARCHY);

		LexerCacheForJavaScript lc = LexerCacheForJavaScript.getCache(inputElement, ((IDocument) inputElement).get());
		vec = lc.parseForFunctionsNVariables();

		//		JSContentElementImpl.setSupportChildren(showHierarchy);
		//		JSContentElementImpl.setSupportVariables(showVariables);

		if (!JSContentElementImpl.isSupportVariables()) {
			// Just remove the top level variables.
			// The rest will be hidden from the tree viewer code because
			// we've just set the SupportVariables value.
			List contentList = new ArrayList();
			Iterator it = vec.iterator();
			while (it.hasNext()) {
				ContentElement coe = (ContentElement) it.next();
				if (coe.getType() != JSContentElementConstants.JS_VARIABLE) {
					contentList.add(coe);
				}
			}
			vec = contentList;
		}

		return vec;
	}
}
