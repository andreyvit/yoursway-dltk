package org.eclipse.dltk.tcl.internal.ui.text;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.tcl.internal.ui.rules.TclCommentRule;
import org.eclipse.dltk.tcl.internal.ui.rules.TclEscapedCharRule;
import org.eclipse.dltk.tcl.ui.text.TclPartitions;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;


public class TclPartitionScanner extends RuleBasedPartitionScanner {

	/**
	 * Creates the partitioner and sets up the appropriate rules.
	 */
	public TclPartitionScanner() {
		super();
		
		IToken string = new Token( TclPartitions.TCL_STRING );
		IToken comment = new Token( TclPartitions.TCL_COMMENT );
		IToken stuff = new Token("dummy");

		List/*< IPredicateRule >*/ rules= new ArrayList/*<IPredicateRule>*/();

		//rules.add(new EndOfLineRule("#", comment ));
		rules.add(new TclCommentRule(comment));
		
		rules.add(new TclEscapedCharRule (stuff, '\\'));
		
		rules.add(new MultiLineRule("\"", "\"", string, '\\'));
		
		IPredicateRule[] result= new IPredicateRule[rules.size()];
		rules.toArray(result);
		setPredicateRules(result);
	}
}
