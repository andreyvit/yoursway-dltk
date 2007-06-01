package org.eclipse.dltk.validators.internal.ui.externalchecker;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.validators.core.IValidator;
import org.eclipse.dltk.validators.core.ValidatorRuntime;
import org.eclipse.dltk.validators.internal.core.externalchecker.ExternalChecker;
import org.eclipse.dltk.validators.internal.core.externalchecker.ExternalCheckerProblem;
import org.eclipse.dltk.validators.internal.core.externalchecker.ExternalCheckerWildcardManager;
import org.eclipse.dltk.validators.internal.core.externalchecker.Rule;
import org.eclipse.dltk.validators.internal.core.externalchecker.WildcardException;
import org.eclipse.dltk.validators.internal.core.externalchecker.WildcardMatcher;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IPatternMatchListener;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;

public class ExternalCheckerConsoleTracker implements IPatternMatchListener {

	protected TextConsole console;
	private List rules = new ArrayList();

	public ExternalCheckerConsoleTracker() {
		super();

		IValidator[] validators = ValidatorRuntime.getActiveValidators();
		for (int i = 0; i < validators.length; i++) {
			if (validators[i] instanceof ExternalChecker) {
				ExternalChecker checker = (ExternalChecker) validators[i];
				for (int j = 0; j < checker.getNRules(); j++) {
					rules.add(checker.getRule(j));
				}
			}
		}
	}

	public void connect(TextConsole console) {
		this.console = console;
	}

	public void disconnect() {
		console = null;
	}

	protected TextConsole getConsole() {
		return console;
	}

	public int getCompilerFlags() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getLineQualifier() {
		return null;
	}

	public void matchFound(PatternMatchEvent event) {
		try {
			IOConsole cons = (IOConsole) event.getSource();
			IDocument doc = (IDocument) cons.getDocument();
			int offset = event.getOffset();
			int length = event.getLength();
			String text = doc.get(offset, length);
			
			List wlist = ExternalCheckerWildcardManager.loadCustomWildcards();
			WildcardMatcher wmatcher = new WildcardMatcher(wlist);

				for (int i = 0; i < rules.size(); i++) {
					Rule rule = (Rule) rules.get(i);
					try {
						ExternalCheckerProblem problem = wmatcher.match(rule,
								text);
						if (problem != null) {
							IHyperlink link = new ExternalCheckerSyntaxHyperlink(
									console, problem);
							console.addHyperlink(link, offset, text
									.length());
							break;
						}
					} catch (WildcardException x) {
					}
				}
//				offset = offset + text.length() + 1;
		} catch (BadLocationException e) {
		}
	}

	public String getPattern() {
		return ".+";
	}
}
