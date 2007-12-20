package org.eclipse.dltk.tcl.core;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;

/**
 * Class used to dynamicaly n XOTcl command from tcl command. 
 * @author haiodo
 *
 */

public interface ITclCommandDetector {
	public static class CommandInfo {
		public String commandName;
		public Object parameter;
		public CommandInfo(String name, Object parameter) {
			this.commandName = name;
			this.parameter = parameter;
		}
	}
	CommandInfo detectCommand( TclCommand command, int offset, ModuleDeclaration module, ITclParser parser, ASTNode decl);
}
