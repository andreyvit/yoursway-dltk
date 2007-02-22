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
