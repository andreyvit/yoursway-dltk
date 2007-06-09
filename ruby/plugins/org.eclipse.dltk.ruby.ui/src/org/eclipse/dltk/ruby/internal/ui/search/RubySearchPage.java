/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.search;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.internal.ui.search.ScriptSearchPage;
import org.eclipse.dltk.ruby.core.RubyLanguageToolkit;

public class RubySearchPage extends ScriptSearchPage {
	protected IDLTKLanguageToolkit getLanguageToolkit() {
		return RubyLanguageToolkit.getDefault();
	}
}
