package org.eclipse.dltk.itcl.internal.core.parser;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.itcl.internal.core.IIncrTclModifiers;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.ITclCommandDetector;
import org.eclipse.dltk.tcl.core.ITclCommandDetectorExtension;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;

public class IncrTclCommandDetector implements ITclCommandDetector,
		ITclCommandDetectorExtension {
	private final static String[] itclCommands = new String[] { "class",
			"body", "code", "configbody", "delete", "ensemble", "find",
			"local", "scope" };
	private String prefix = "itcl::";
	private boolean runtimeModel = false;

	public static class IncrTclGlobalClassParameter {
		private String name;

		public IncrTclGlobalClassParameter(String name) {
			this.name = name;
		}

		public IModelElement resolveElement() {
			return null;
		}

		public String getClassName() {
			return this.name;
		}
	}

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
			return checkInstanceOperations(module, decl, statement, parser);
		}
		return null;
	}

	private CommandInfo checkInstanceOperations(ModuleDeclaration module,
			ASTNode parent, TclStatement statement, ITclParser parser) {
		if( runtimeModel ) {
			return null;
		}
		Expression commandName = statement.getAt(0);
		if (!(commandName instanceof SimpleReference)) {
			return null;
		}
		String commandNameValue = ((SimpleReference) commandName).getName();

		TypeDeclaration type = TclParseUtil.findXOTclTypeDeclarationFrom(
				module, parent, commandNameValue);
		if (statement.getCount() == 1) {
			return null;
		}
		Expression arg = statement.getAt(1);
		if (type != null) {
			if (arg instanceof SimpleReference) {
				return check(type, (SimpleReference) arg);
			}
		}

		// Lets check possibly this is method call for existing instance
		// variable.
		FieldDeclaration variable = IncrTclParseUtil
				.findXOTclInstanceVariableDeclarationFrom(module, parent,
						commandNameValue);
		if (variable != null) {
			// Add support of procs etc.
			return new CommandInfo("#itcl#$methodCall", variable);
		}

		return null;
	}

	private CommandInfo check(TypeDeclaration type, SimpleReference arg) {
		if ((type.getModifiers() & IIncrTclModifiers.AccIncrTcl) == 0) {
			return null;
		}
		// We need to understand what specified type has't contain method or
		// proc with argument name
		String value = arg.getName();
		MethodDeclaration[] methods = type.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if ((methods[i].getModifiers() & IIncrTclModifiers.AccIncrTclProc) != 0) {
				if (methods[i].getName().equals(value)) {
					return new CommandInfo("#itcl#$methodCall", type);
				}
			}
		}
		// String value = ((SimpleReference) arg).getName();
		return new CommandInfo("#itcl#$newInstance", type);
	}
	public void setBuildRuntimeModelFlag(boolean value) {
		this.runtimeModel = value;
	}
}
