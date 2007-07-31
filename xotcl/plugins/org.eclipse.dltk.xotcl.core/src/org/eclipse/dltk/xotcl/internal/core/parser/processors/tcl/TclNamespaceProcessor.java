package org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.xotcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.xotcl.core.ITclParser;

public class TclNamespaceProcessor extends AbstractTclCommandProcessor {

	public ASTNode process(TclCommand command, ITclParser parser, int offset, ASTNode parent) {
		ASTNode namespace = parser.processLocal(command, offset);
		if( !(namespace instanceof TclStatement) ) {
			return null;
		}
		TclStatement statement = (TclStatement) namespace;
		Expression nameSpaceArg = statement.getAt(1);
		if (nameSpaceArg == null || !(nameSpaceArg instanceof SimpleReference)) {
			// TODO: Add error reporting here.
			if (DLTKCore.DEBUG) {
				System.err.println("tcl: namespace argument is null or not simple reference");
			}
			// continue;
		}

		Expression nameSpaceName = statement.getAt(2);
		if (nameSpaceName == null
				|| !(nameSpaceName instanceof SimpleReference)) {
			// TODO: Add error reporting here.
			// continue;
			// by now, just ignore
			return null;
		}

		Expression code = statement.getAt(3);
		if (code == null || !(code instanceof TclBlockExpression)) {
			return null;
			// TODO: Add error reporting here.
			// continue;
		}

		String sNameSpaceArg = ((SimpleReference) nameSpaceArg).getName();
		String sNameSpaceName = ((SimpleReference) nameSpaceName).getName();

		if (sNameSpaceArg.equals("eval")) {
			TypeDeclaration type = new TypeDeclaration(sNameSpaceName, nameSpaceName.sourceStart(), nameSpaceName.sourceEnd(), namespace.sourceStart(), namespace.sourceEnd());
			type.setModifiers(Modifiers.AccNameSpace);
			addToParent(parent, type);
			if( code instanceof TclBlockExpression ) {
				TclBlockExpression block = (TclBlockExpression) code;
				String blockContent = block.getBlock();
				blockContent = blockContent.substring(1, blockContent.length() - 1);
				Block bl = new Block(code.sourceStart(), code.sourceEnd());
				type.setBody(bl);
				parser.parse(blockContent, block.sourceStart() + 1 - parser.getStartPos(), bl );
			}
			return type;
		}
		else {
			System.out.println("Cool");
		}
		return null;
	}
}
