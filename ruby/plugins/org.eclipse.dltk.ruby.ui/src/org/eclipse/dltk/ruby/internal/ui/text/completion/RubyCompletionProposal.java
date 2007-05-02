/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.text.completion;

import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposal;
import org.eclipse.swt.graphics.Image;

public class RubyCompletionProposal extends ScriptCompletionProposal {

	public RubyCompletionProposal(String replacementString,
			int replacementOffset, int replacementLength, Image image,
			String displayString, int relevance) {
		super(replacementString, replacementOffset, replacementLength, image,
				displayString, relevance);
	}

	public RubyCompletionProposal(String replacementString,
			int replacementOffset, int replacementLength, Image image,
			String displayString, int relevance, boolean isInDoc) {
		super(replacementString, replacementOffset, replacementLength, image,
				displayString, relevance, isInDoc);
	}

	protected boolean isSmartTrigger(char trigger) {
		if (trigger == '$') {
			return true;
		}

		return false;
	}
	
	
	
}
