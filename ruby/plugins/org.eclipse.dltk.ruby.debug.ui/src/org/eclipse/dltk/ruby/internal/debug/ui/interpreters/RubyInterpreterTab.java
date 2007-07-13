/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.debug.ui.interpreters;

import org.eclipse.dltk.debug.ui.launchConfigurations.InterpreterTab;
import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterComboBlock;
import org.eclipse.dltk.ruby.core.RubyNature;

public class RubyInterpreterTab extends InterpreterTab {
	protected AbstractInterpreterComboBlock getInterpreterBlock() {
		return new RubyInterpreterComboBlock();
	}

	protected String getNature() {
		return RubyNature.NATURE_ID;
	}
}
