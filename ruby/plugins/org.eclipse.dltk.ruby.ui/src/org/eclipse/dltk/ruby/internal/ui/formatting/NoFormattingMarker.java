/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.formatting;

import java.util.Map;

public class NoFormattingMarker  extends AbstractBlockMarker {


	public NoFormattingMarker(String aKeyword, int aPos) {
		super(aKeyword, aPos);
		// TODO Auto-generated constructor stub
	}

	public boolean isFormatting() 
	{
		return false ;	
	}

	protected void indentAfterPrint(IndentationState state) {


	}


	protected void indentBeforePrint(IndentationState state) {


	}
	
	public void appendIndentedLine(StringBuffer sb, IndentationState state, String originalLine, String strippedLine, Map options) {
			sb.append(originalLine) ;
	}	

}
