package org.eclipse.dltk.itcl.internal.core.parser;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.ITclCommandDetector;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;

public class IncrTclCommandDetector implements ITclCommandDetector {
	private final static String[] itclCommands = new String[] { "class",
			"body", "code", "configbody", "delete", "ensemble", "find",
			"local", "scope" };
	private String prefix = "itcl::";

	public IncrTclCommandDetector() {
	}

	/**
	 * 1) Detect of core itcl commands 2) Detect itcl class access 3) Detect
	 * itcl object creations 4) Detect itcl instances method access, etc.
	 * 
	 */
	public CommandInfo detectCommand(TclCommand command, int offset,
			ModuleDeclaration module, ITclParser parser, ASTNode decl) {
		TclStatement statement = (TclStatement) parser.processLocal(command,
				offset, decl);
		if (statement.getCount() == 0) {
			return null;
		}
		Expression commandName = statement.getAt(0);
		if (commandName instanceof SimpleReference) {
			String value = ((SimpleReference) commandName).getName();
			for (int i = 0; i < itclCommands.length; i++) {
				if (itclCommands[i].equals(value)
						|| (prefix + itclCommands[i]).equals(value)
						|| ("::" + prefix + itclCommands[i]).equals(value)) {
					return new CommandInfo("#itcl#" + itclCommands[i], null);
				}
			}
		}
		return null;
	}
}
