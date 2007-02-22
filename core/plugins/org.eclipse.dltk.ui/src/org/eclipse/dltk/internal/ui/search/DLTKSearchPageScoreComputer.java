/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.search;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.ui.editor.ExternalStorageEditorInput;
import org.eclipse.search.ui.ISearchPageScoreComputer;


public class DLTKSearchPageScoreComputer implements ISearchPageScoreComputer {

	public int computeScore(String id, Object element) {
		if (!DLTKSearchPage.EXTENSION_POINT_ID.equals(id))
			// Can't decide
			return ISearchPageScoreComputer.UNKNOWN;
		
		if (element instanceof IModelElement || element instanceof ExternalStorageEditorInput )
			return 90;
		
		return ISearchPageScoreComputer.LOWEST;
	}
}
