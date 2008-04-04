package org.eclipse.dltk.examples.python.internal.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class ExamplePythonPartitionScanner extends RuleBasedPartitionScanner {
	public ExamplePythonPartitionScanner() {
		IToken string = new Token(IExamplePythonPartitions.PYTHON_STRING);
		IToken comment = new Token(IExamplePythonPartitions.PYTHON_COMMENT);

		List rules = new ArrayList();

		rules.add(new EndOfLineRule("#", comment));
		rules.add(new MultiLineRule("\"\"\"", "\"\"\"", string, '\\'));
		rules.add(new MultiLineRule("\'\'\'", "\'\'\'", string, '\\'));
		rules.add(new MultiLineRule("\'", "\'", string, '\\'));
		rules.add(new MultiLineRule("\"", "\"", string, '\\'));

		IPredicateRule[] result = new IPredicateRule[rules.size()];
		rules.toArray(result);
		setPredicateRules(result);
	}
}