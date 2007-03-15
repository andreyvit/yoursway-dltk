package org.eclipse.dltk.javascript.internal.ui.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.WhitespaceRule;


public class JavascriptStringScanner extends AbstractScriptScanner {

	private static final String[] fgTokenProperties = new String[] {
		JavascriptColorConstants.JS_STRING		
	};

	public JavascriptStringScanner(IColorManager manager, IPreferenceStore store) {
		super(manager, store);

		initialize();
	}

	
	protected String[] getTokenProperties() {
		return fgTokenProperties;
	}

	
	protected List createRules() {
		List/*<IRule>*/ rules = new ArrayList/*<IRule>*/();

		// Add generic whitespace rule.
		rules.add(new WhitespaceRule(new JavascriptWhitespaceDetector()));
		
		//TODO: Add here % and %{name} variables handling.
				
		setDefaultReturnToken(getToken(JavascriptColorConstants.JS_STRING));

		return rules;
	}

}
