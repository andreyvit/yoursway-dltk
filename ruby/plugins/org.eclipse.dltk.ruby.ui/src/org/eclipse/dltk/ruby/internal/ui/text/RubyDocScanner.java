package org.eclipse.dltk.ruby.internal.ui.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ruby.internal.ui.text.rules.BeginOfLineRule;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WhitespaceRule;

public class RubyDocScanner extends AbstractScriptScanner {
	private static final String[] fgTokenProperties = new String[] {
		RubyColorConstants.RUBY_DOC,
		RubyColorConstants.RUBY_DOC_TOPIC		
	};

	public RubyDocScanner(IColorManager manager, IPreferenceStore store) {
		super(manager, store);

		initialize();
	}
	
	protected String[] getTokenProperties() {
		return fgTokenProperties;
	}

	
	protected List createRules() {
		List/*<IRule>*/ rules = new ArrayList/*<IRule>*/();
		
		IToken topic = getToken(RubyColorConstants.RUBY_DOC_TOPIC);
		IToken other = getToken(RubyColorConstants.RUBY_DOC);
		
		// Add generic whitespace rule.
		rules.add(new WhitespaceRule(new RubyWhitespaceDetector()));
		
		rules.add(new BeginOfLineRule(topic, '='));
		setDefaultReturnToken(other);

		return rules;
	}

}
