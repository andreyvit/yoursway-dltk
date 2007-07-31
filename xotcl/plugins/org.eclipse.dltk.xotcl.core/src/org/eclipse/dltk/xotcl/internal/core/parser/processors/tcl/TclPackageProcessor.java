package org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.xotcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.xotcl.core.ITclCommandProcessor;
import org.eclipse.dltk.xotcl.core.ITclParser;
import org.eclipse.dltk.xotcl.core.ast.TclPackageDeclaration;

public class TclPackageProcessor extends AbstractTclCommandProcessor implements
		ITclCommandProcessor {

	public TclPackageProcessor() {
	}

	public ASTNode process(TclCommand command, ITclParser parser, int offset,ASTNode parent) {
		TclStatement statement = (TclStatement) parser.processLocal(command, offset);
		if (statement.getCount() < 3) {
			// TODO: Add error reporting here.
			if (DLTKCore.DEBUG) {
				System.err.println("tcl: package argument could incorrect...");
			}
			return null;
		}
		Expression nameSpaceArg = statement.getAt(1);
		if (nameSpaceArg == null || !(nameSpaceArg instanceof SimpleReference)) {
			// TODO: Add error reporting here.
			if (DLTKCore.DEBUG) {
				System.err
						.println("tcl: package argument is null or not simple reference");
			}
			return null;
		}
		String arg = ((SimpleReference) nameSpaceArg).getName();
		if (arg.equals("provide") ) {
			boolean exact = false;
			int i = 2;
			Expression pkg = statement.getAt(i);
			if( pkg instanceof SimpleReference && "-exact".equals(((SimpleReference)pkg).getName())) {
				i = 3;
				pkg = statement.getAt(i);
			}
			Expression pkgVer = null;
			if (statement.getCount() > i + 1 &&  statement.getAt(i + 1) instanceof SimpleReference) {
				pkgVer = statement.getAt(i + 1);
			}
			if (pkg != null && pkg instanceof SimpleReference) {
				TclPackageDeclaration st = new TclPackageDeclaration((SimpleReference)pkg, pkgVer, TclPackageDeclaration.STYLE_PROVIDE, command.getStart() + offset, command.getEnd() + offset + 1);
				st.setExact(exact);
				this.addToParent(parent, st);
				return st;
			}
		}
		else if( arg.equals("ifneeded") ) {
			Expression pkg = statement.getAt(2);
			Expression pkgVer = null;
			Expression script = null;
			if (statement.getCount() > 3 &&  statement.getAt(3) instanceof SimpleReference) {
				pkgVer = statement.getAt(3);
			}
			else if(statement.getAt(3) instanceof TclBlockExpression) {
				script = statement.getAt(3);
			}
			if (script != null && statement.getCount() > 4) {
				pkgVer = statement.getAt(4);
			}
			if (pkg != null && pkg instanceof SimpleReference) {
				TclPackageDeclaration st = new TclPackageDeclaration((SimpleReference)pkg, pkgVer, TclPackageDeclaration.STYLE_IFNEEDED, command.getStart() + offset, command.getEnd() + offset + 1);
				st.setScript(script);
				this.addToParent(parent, st);
				return st;
			}
		}
		else if( arg.equals("forget") ) {

		}
		return null;
	}

}
