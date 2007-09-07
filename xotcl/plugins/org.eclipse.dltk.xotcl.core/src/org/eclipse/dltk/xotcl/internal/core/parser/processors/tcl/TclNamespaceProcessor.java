package org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
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
import org.eclipse.dltk.xotcl.core.TclParseUtil;

public class TclNamespaceProcessor extends AbstractTclCommandProcessor {
	private ASTNode findRealParent(ASTNode node) {
		List levels = TclParseUtil.findLevelsTo(this.getModuleDeclaration(),
				node);
		for (int i = levels.size() - 1; i >= 0; --i) {
			ASTNode n = (ASTNode) levels.get(i);
			if (n instanceof MethodDeclaration || n instanceof TypeDeclaration
					|| n instanceof ModuleDeclaration) {
				return n;
			}
		}
		return null;
	}

	public ASTNode process(TclCommand command, ITclParser parser, int offset,
			ASTNode parent) {
		ASTNode namespace = parser.processLocal(command, offset, parent);
		if (!(namespace instanceof TclStatement)) {
			return null;
		}
		TclStatement statement = (TclStatement) namespace;
		Expression nameSpaceArg = statement.getAt(1);
		if (nameSpaceArg == null || !(nameSpaceArg instanceof SimpleReference)) {
			// TODO: Add error reporting here.
			if (DLTKCore.DEBUG) {
				System.err
						.println("tcl: namespace argument is null or not simple reference");
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

		String sNameSpaceArg = ((SimpleReference) nameSpaceArg).getName();
		String sNameSpaceName = ((SimpleReference) nameSpaceName).getName();

		if (sNameSpaceArg.equals("eval")) {
			final int FIRST_ARGUMENT_POSITION = 3;

			// List statements = new ArrayList(statement.getCount() -
			// FIRST_ARGUMENT_POSITION);
			int start = statement.getAt(FIRST_ARGUMENT_POSITION).sourceStart();
			int end = statement.getAt(statement.getCount() - 1).sourceEnd();
			Block code = new Block(start, end);
			TypeDeclaration type = new TypeDeclaration(sNameSpaceName,
					nameSpaceName.sourceStart(), nameSpaceName.sourceEnd(),
					namespace.sourceStart(), namespace.sourceEnd());
			type.setModifiers(Modifiers.AccNameSpace);
			ASTNode realParent = findRealParent(parent);
			if (realParent instanceof TypeDeclaration) {
				TypeDeclaration t = ((TypeDeclaration) realParent);
				type.setEnclosingTypeName(t.getEnclosingTypeName() + "$"
						+ t.getName());
			}
			addToParent(parent, type);
			type.setBody((Block) code);
			for (int i = FIRST_ARGUMENT_POSITION; i < statement.getCount(); i++) {
				Expression expr = statement.getAt(i);
				if (expr == null) {
					return null;
					// TODO: Add error reporting here.
					// continue;
				}
				if (expr instanceof TclBlockExpression) {
					TclBlockExpression block = (TclBlockExpression) expr;
					String blockContent = block.getBlock();
					blockContent = blockContent.substring(1, blockContent
							.length() - 1);
					parser.parse(blockContent, block.sourceStart() + 1
							- parser.getStartPos(), code);
//					code.getStatements().addAll(bl.getStatements());
				} else {
					code.getStatements().add(expr);
				}
			}

			return type;
		} else {
			// System.out.println("Cool");
		}
		return null;
	}
}
