/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ruby.internal.ui.text.rules.RubyGlobalVarRule;
import org.eclipse.dltk.ruby.internal.ui.text.rules.RubyPercentStringRule;
import org.eclipse.dltk.ruby.internal.ui.text.rules.RubySlashRegexpRule;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class RubyPartitionScanner extends RuleBasedPartitionScanner {

	private Token string;
	private Token comment;
	private Token rubyDoc;
	private Token defaultToken;
	private Token singleQuoteString;

	/**
	 * Creates the partitioner and sets up the appropriate rules.
	 */
	public RubyPartitionScanner() {
		super();

		defaultToken = new Token(IDocument.DEFAULT_CONTENT_TYPE);

		string = new Token(IRubyPartitions.RUBY_STRING);

		singleQuoteString = new Token(IRubyPartitions.RUBY_SINGLE_QUOTE_STRING);

		comment = new Token(IRubyPartitions.RUBY_COMMENT);

		rubyDoc = new Token(IRubyPartitions.RUBY_DOC);

		List rules = new ArrayList();

		rules.add(new MultiLineRule("=begin", "=end", rubyDoc)); //$NON-NLS-1$ //$NON-NLS-2$

		rules.add(new EndOfLineRule("#", comment)); //$NON-NLS-1$

		rules.add(new MultiLineRule("\"", "\"", string, '\\', true)); //$NON-NLS-1$ //$NON-NLS-2$
		
		rules.add(new MultiLineRule("'", "'", singleQuoteString, '\\', true)); //$NON-NLS-1$ //$NON-NLS-2$

		rules.add(new RubyPercentStringRule(string, false));

		rules.add(new RubySlashRegexpRule(string));

		rules.add(new RubyGlobalVarRule(defaultToken));

		IPredicateRule[] result = new IPredicateRule[rules.size()];
		rules.toArray(result);
		setPredicateRules(result);
	}
}