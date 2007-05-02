/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.text;

import org.eclipse.jface.text.rules.IWordDetector;

public class RubyWordDetector implements IWordDetector {

	public boolean isWordPart(char character) {
		return Character.isJavaIdentifierPart(character);
	}

	public boolean isWordStart(char character) {
		return Character.isJavaIdentifierPart(character);
	}
}
