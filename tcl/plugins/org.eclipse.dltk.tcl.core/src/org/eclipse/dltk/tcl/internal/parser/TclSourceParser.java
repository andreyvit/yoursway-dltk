package org.eclipse.dltk.tcl.internal.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.declarations.ISourceParser;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.tcl.ast.TclModuleDeclaration;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.ast.expressions.TclExecuteExpression;
import org.eclipse.dltk.tcl.internal.parsers.raw.BracesSubstitution;
import org.eclipse.dltk.tcl.internal.parsers.raw.CommandSubstitution;
import org.eclipse.dltk.tcl.internal.parsers.raw.QuotesSubstitution;
import org.eclipse.dltk.tcl.internal.parsers.raw.SimpleTclParser;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclParseException;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclScript;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclWord;

public class TclSourceParser implements ISourceParser {
	private int currentPosition = 0;

	public ModuleDeclaration parse(char[] content0, IProblemReporter reporter) {
		String content = new String(content0);
		// System.out.println("TclSourceParser.parse() " +
		// System.currentTimeMillis());

		TclScript script;
		try {
			script = SimpleTclParser.parse(content);
		} catch (TclParseException e) {
			if (DLTKCore.DEBUG_PARSER)
				e.printStackTrace();
			return null;
		}

		TclModuleDeclaration moduleDeclaration = new TclModuleDeclaration(
				content.length());

		List statements = new ArrayList();

		List commands = script.getCommands();

		for (Iterator iter = commands.iterator(); iter.hasNext();) {
			TclCommand c = (TclCommand) iter.next();
			List exprs = new ArrayList();
			List words = c.getWords();
			for (Iterator iterator = words.iterator(); iterator.hasNext();) {
				TclWord word = (TclWord) iterator.next();
				String wordText = content.substring(word.getStart(), word
						.getEnd() + 1);
				// wordText = SimpleTclParser.magicSubstitute(wordText);
				Object o = word.getContents().get(0);
				if (o instanceof QuotesSubstitution) {
					QuotesSubstitution qs = (QuotesSubstitution) o;

					exprs.add(new StringLiteral(
							currentPosition + qs.getStart(), currentPosition
									+ qs.getEnd() + 1, wordText)); // TODO:
																	// fixme,
																	// wtf?
				} else if (o instanceof BracesSubstitution) {
					BracesSubstitution bs = (BracesSubstitution) o;

					exprs.add(new TclBlockExpression(currentPosition
							+ bs.getStart(), currentPosition + bs.getEnd() + 1,
							wordText));
				} else if (o instanceof CommandSubstitution
						&& (word.getContents().size() == 1)) {
					CommandSubstitution bs = (CommandSubstitution) o;

					exprs.add(new TclExecuteExpression(currentPosition
							+ bs.getStart(), currentPosition + bs.getEnd() + 1,
							wordText));
				} else {
					exprs.add(new SimpleReference(currentPosition
							+ word.getStart(), currentPosition + word.getEnd()
							+ 1, wordText));
				}
			}
			TclStatement st = new TclStatement(exprs);
			statements.add(st);
		}

		// System.out.println("building ast");

		Iterator i = statements.iterator();
		while (i.hasNext()) {
			moduleDeclaration.addStatement((Statement) i.next());
		}

		return moduleDeclaration;
	}

	public void setCurrentPosition(int startFrom) {
		this.currentPosition = startFrom;
	}
}
