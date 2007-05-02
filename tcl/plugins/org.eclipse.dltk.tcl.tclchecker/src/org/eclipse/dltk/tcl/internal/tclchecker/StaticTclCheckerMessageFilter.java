/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.tclchecker;

import java.util.ArrayList;
import java.util.List;

public class StaticTclCheckerMessageFilter implements TclCheckerMessageFilter {
		
	private static List badIds;
	
	static{
		badIds = new ArrayList(4);
		badIds.add("warnUndefinedUpvar");
		badIds.add("warnUndefinedVar");
		badIds.add("warnUndefFunc");
		badIds.add("warnUndefProc");
	};
	 	
	public StaticTclCheckerMessageFilter(){	

	}
	
	public boolean accept(String messageId) {		
		if (badIds.contains(messageId)){
			return false;
		}
		
		return true;
	}
}
