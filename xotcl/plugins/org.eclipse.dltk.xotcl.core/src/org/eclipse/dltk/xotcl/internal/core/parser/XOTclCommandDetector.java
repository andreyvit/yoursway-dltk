package org.eclipse.dltk.xotcl.internal.core.parser;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.ITclCommandDetector;
import org.eclipse.dltk.tcl.core.ITclCommandDetectorExtension;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.xotcl.core.IXOTclModifiers;
import org.eclipse.dltk.xotcl.core.XOTclParseUtil;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclObjectDeclaration;
import org.eclipse.dltk.xotcl.internal.core.XOTclKeywords;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclClass;

public class XOTclCommandDetector implements ITclCommandDetector,
		ITclCommandDetectorExtension {
	// Options
	public static boolean INTERPRET_CLASS_UNKNOWN_AS_CREATE = true;
	public static boolean INTERPRET_OBJECT_UNKNOWN_AS_CREATE = true;
	private boolean runtimeModel = false;

	public static class XOTclGlobalClassParameter {
		private String name;

		public XOTclGlobalClassParameter(String name) {
			this.name = name;
		}

		public IModelElement resolveElement() {
			XOTclClass e = XOTclMixinUtils.findMixinElement(name);
			if (e != null) {
				return e.getModelElement();
			}
			return null;
		}

		public String getClassName() {
			return this.name;
		}
	}

	public XOTclCommandDetector() {
	}

	public CommandInfo detectCommand(TclStatement statement, 
			ModuleDeclaration module, ITclParser parser, ASTNode parent) {
		if (statement.getCount() == 0) {
			return null;
		}
		Expression commandName = statement.getAt(0);
		if (commandName instanceof SimpleReference) {
			String value = ((SimpleReference) commandName).getName();
			if (value.equals("Class") || value.equals("::xotcl::Class")
					|| value.equals("xotcl::Class")) {
				return checkClass(statement, module, parser, parent);
			} else if (value.equals("Object")
					|| value.equals("::xotcl::Object")
					|| value.equals("xotcl::Object")) {
				return checkObject(statement, module, parser, parent);
			} else {
				return checkInstanceOperations(module, parent, statement,
						parser);
			}
		}
		return null;
	}

	private CommandInfo checkInstanceOperations(ModuleDeclaration module,
			ASTNode parent, TclStatement statement, ITclParser parser) {
		if (runtimeModel) {
			return null;
		}
		Expression commandName = statement.getAt(0);
		if (!(commandName instanceof SimpleReference)) {
			// TODO: Add handling of this.
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

		// Find Object instance
		XOTclObjectDeclaration decl = XOTclParseUtil
				.findXOTclObjectInstanceFrom(module, parent, commandNameValue);
		if (decl != null) {
			if (arg instanceof SimpleReference) {
				String value = ((SimpleReference) arg).getName();
				if (value.equals("proc")) {
					return new CommandInfo("#Class#proc", decl);
				}
			}
			// Method call
			return new CommandInfo("#Class#$ProcCall", type);
		}
		// Lets check possibly this is method call for existing instance
		// variable.
		FieldDeclaration variable = XOTclParseUtil
				.findXOTclInstanceVariableDeclarationFrom(module, parent,
						commandNameValue);
		if (variable != null) {
			// Add support of procs etc.
			return new CommandInfo("#Class#$MethodCall", variable);
		}

		// Class instance field declaration
		if (statement.getCount() < 3) {
			return null;
		}
		if (!(arg instanceof SimpleReference)) {
			return null;
		}
		String argumentValue = ((SimpleReference) arg).getName();
		if (commandNameValue.length() >= 3) {
			if (commandNameValue.startsWith("::")) {
				commandNameValue = commandNameValue.substring(2);
			}
			boolean isUpper = Character.isUpperCase(commandNameValue.charAt(0));
			if (commandNameValue.indexOf("::") > 0 || isUpper) {
				if (argumentValue.equals("create")) {
					XOTclGlobalClassParameter param = new XOTclGlobalClassParameter(
							commandNameValue);
					return new CommandInfo("#Class#$newInstance", param);
				}
			}
		}
		return null;
	}

	private CommandInfo check(TypeDeclaration type, SimpleReference arg) {
		if ((type.getModifiers() & IXOTclModifiers.AccXOTcl) == 0) {
			return null;
		}
		String value = arg.getName();
		if (!value.equals("create")) {
			CommandInfo info = checkCommands(value, type);
			if (info != null) {
				return info;
			}
		} else {
			return new CommandInfo("#Class#$newInstance", type);
		}

		return new CommandInfo("#Class#$ProcCall", type);
	}

	private CommandInfo checkCommands(String value, Object decl) {
		CommandInfo info = checkClassOperator(decl, value,
				XOTclKeywords.XOTclCommandClassArgs, "#Class#");
		if (info != null) {
			return info;
		}
		info = checkClassOperator(decl, value,
				XOTclKeywords.XOTclCommandObjectArgs, "#Object#");
		if (info != null) {
			return info;
		}
		return null;
	}

	private CommandInfo checkClassOperator(Object type, String value,
			String[] commands, String prefix) {
		for (int q = 0; q < commands.length; q++) {
			if (value.equals(commands[q])) {
				return new CommandInfo(prefix + value, type);
			}
		}
		return null;
	}

	private CommandInfo checkClass(TclStatement statement,
			ModuleDeclaration module, ITclParser parser, ASTNode parent) {

		Expression arg = statement.getAt(1);
		if (arg instanceof SimpleReference) {
			String value = ((SimpleReference) arg).getName();
			if (!runtimeModel) {
				TypeDeclaration type = TclParseUtil
						.findXOTclTypeDeclarationFrom(module, parent, "Class");
				// if (type != null) {
				for (int i = 0; i < XOTclKeywords.XOTclCommandClassArgs.length; i++) {
					if (value.equals(XOTclKeywords.XOTclCommandClassArgs[i])) {
						return new CommandInfo("#Class#" + value, type);
					}
				}
				// }
			}
			CommandInfo info = checkCreateType(statement, parent, arg, value);
			if (info != null) {
				return info;
			}
			// Else unknown command or create command.
			if (INTERPRET_CLASS_UNKNOWN_AS_CREATE) {
				return new CommandInfo("#Class#create", null);
			}

			return null;
		}
		return null;
	}

	private CommandInfo checkObject(TclStatement statement,
			ModuleDeclaration module, ITclParser parser, ASTNode parent) {

		Expression arg = statement.getAt(1);
		if (arg instanceof SimpleReference) {
			String value = ((SimpleReference) arg).getName();
			TypeDeclaration type = TclParseUtil.findXOTclTypeDeclarationFrom(
					module, parent, "Object");
			// if (type != null) {
			for (int i = 0; i < XOTclKeywords.XOTclCommandObjectArgs.length; i++) {
				if (value.equals(XOTclKeywords.XOTclCommandObjectArgs[i])) {
					return new CommandInfo("#Object#" + value, type);
				}
			}
			// }
			CommandInfo info = checkCreateType(statement, parent, arg, value);
			if (info != null) {
				return info;
			}
			// // Else unknown command or create command.
			if (INTERPRET_OBJECT_UNKNOWN_AS_CREATE) {
				return new CommandInfo("#Object#create", null);
			}

			return null;
		}
		return null;
	}

	private CommandInfo checkCreateType(TclStatement statement, ASTNode parent,
			Expression arg, String value) {
		if (value.equals("instproc") || value.equals("proc")
				|| value.equals("set")) {
			String name = TclParseUtil.getNameFromNode(statement.getAt(0));
			TypeDeclaration decl = createTypeAdd(statement, parent, statement
					.getAt(0), name);
			CommandInfo info = checkCommands(value, decl);
			if (info != null) {
				return info;
			}
		}
		return null;
	}

	private TypeDeclaration createTypeAdd(TclStatement statement,
			ASTNode parent, Expression arg, String value) {
		TypeDeclaration decl = new TypeDeclaration(value, arg.sourceStart(),
				arg.sourceEnd(), arg.sourceStart(), arg.sourceEnd());
		TclParseUtil.addToDeclaration(parent, decl);
		return decl;
	}

	public void setBuildRuntimeModelFlag(boolean value) {
		this.runtimeModel = value;
	}
}
