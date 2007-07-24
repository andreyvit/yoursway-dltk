/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.ui.text;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.python.ui.text.IPythonPartitions;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class PythonPartitionScanner extends RuleBasedPartitionScanner {

	/**
	 * Creates the partitioner and sets up the appropriate rules.
	 */
	public PythonPartitionScanner() {
		super();
		
		IToken string = new Token( IPythonPartitions.PYTHON_STRING );
		IToken comment = new Token( IPythonPartitions.PYTHON_COMMENT );

		List/*< IPredicateRule >*/ rules= new ArrayList/*<IPredicateRule>*/();

		
		rules.add(new EndOfLineRule("#", comment ));
		
		rules.add(new MultiLineRule("\"\"\"", "\"\"\"", string, '\\'));
		
		rules.add(new MultiLineRule("\'\'\'", "\'\'\'", string, '\\'));
		
		rules.add(new MultiLineRule("\'", "\'", string, '\\'));
		
		rules.add(new MultiLineRule("\"", "\"", string, '\\'));
							
		
		IPredicateRule[] result= new IPredicateRule[rules.size()];
		rules.toArray(result);
		setPredicateRules(result);
	}
}