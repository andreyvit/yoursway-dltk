package org.eclipse.dltk.ruby.internal.ui.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ruby.core.text.RubyContext;
import org.eclipse.dltk.ruby.internal.ui.text.syntax.RubyContextUtils;
import org.eclipse.dltk.ruby.ui.text.IRubyPartitions;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class RubyPartitionScanner extends RuleBasedPartitionScanner {

	private Token string;
	private Token comment;
	private Token rubyDoc;
	
	/**
	 * Creates the partitioner and sets up the appropriate rules.
	 */
	public RubyPartitionScanner() {
		super();

		string = new Token(IRubyPartitions.RUBY_STRING);

		comment = new Token(IRubyPartitions.RUBY_COMMENT);

		rubyDoc = new Token(IRubyPartitions.RUBY_DOC);

		List/* < IPredicateRule > */rules = new ArrayList/* <IPredicateRule> */();

		rules.add(new MultiLineRule("=begin", "=end", rubyDoc));

		rules.add(new EndOfLineRule("#", comment));

		rules.add(new MultiLineRule("\'", "\'", string, '\\'));

		rules.add(new MultiLineRule("\"", "\"", string, '\\'));

		rules.add(new RubyPercentStringRule(string, false));

		rules.add(new RubySlashRegexpRule(string));

		IPredicateRule[] result = new IPredicateRule[rules.size()];
		rules.toArray(result);
		setPredicateRules(result);
	}

	public int getOffsetForLaterContextLookup() {
		return fOffset;
	}

	public RubyContext getCurrentContext() {
		return RubyContextUtils.determineContext(fDocument, fOffset, RubyContext.MODE_FULL);
	}

	public RubyContext getContext(int offset) {
		return RubyContextUtils.determineContext(fDocument, offset, RubyContext.MODE_FULL);
	}

	protected Token getToken(String key) {
		if (IRubyPartitions.RUBY_STRING.equals(key))
			return string;
		if (IRubyPartitions.RUBY_COMMENT.equals(key))
			return comment;
		if (IRubyPartitions.RUBY_DOC.equals(key))
			return rubyDoc;
		return null;
	}
}