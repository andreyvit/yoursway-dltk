package org.eclipse.dltk.tcl.internal.core.codeassist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.codeassist.IAssistParser;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.tcl.TclKeywords;
import org.eclipse.dltk.tcl.internal.parser.TclSourceParser;

public abstract class TclAssistParser implements IAssistParser {
	protected static final int MODULE = TclKeywords.MODULE;

	protected static final int NAMESPACE = TclKeywords.NAMESPACE;

	protected static final int FUNCTION = TclKeywords.FUNCTION;

	protected static final int EXEC_EXPRESSION = TclKeywords.EXEC_EXPRESSION;

	protected TclSourceParser parser = new TclSourceParser();

	protected ModuleDeclaration module;

	protected ASTNode assistNodeParent = null;

	public ASTNode getAssistNodeParent() {
		return assistNodeParent;
	}

	protected void findElementsTo(List statements, ASTNode node, List elements) {
		if (statements == null) {
			return;
		}
		Iterator i = statements.iterator();
		while (i.hasNext()) {
			ASTNode n = (ASTNode) i.next();
			if (n.equals(node)) {
				elements.add(n);
				return;
			}
			if (n.sourceStart() <= node.sourceStart()
					&& node.sourceEnd() <= n.sourceEnd()) {
				elements.add(n);
				findElementsTo(TclASTUtil.getStatements(n), node, elements);
				return;
			}
		}

	}

	protected List findLevelsTo(ASTNode astNodeParent) {
		List elements = new ArrayList();
		if (this.module != null || astNodeParent instanceof ModuleDeclaration) {
			if (this.module == null) {
				this.module = (ModuleDeclaration) astNodeParent;
			}
			elements.add(this.module);
			findElementsTo(TclASTUtil.getStatements(this.module),
					astNodeParent, elements);
		}
		return elements;
	}

	public void setSource(ModuleDeclaration unit) {
		this.module = unit;
	}

	public ModuleDeclaration parse(ISourceModule sourceUnit) {
		ModuleDeclaration module = this.parser.parse(sourceUnit
				.getSourceContents());
		module.rebuild();

		TclASTUtil.extendStatements(module, sourceUnit.getSourceContents());

		return module;
	}
}
