package org.eclipse.dltk.tcl.internal.core.codeassist.completion;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.codeassist.complete.ICompletionOnKeyword;
import org.eclipse.dltk.utils.CorePrinter;

public class CompletionOnKeywordOrFunction extends SimpleReference implements ICompletionOnKeyword {
	
	private String[] possibleKeywords;
	public CompletionOnKeywordOrFunction(String token, ASTNode completionNode, ASTNode node, String[] possibleKeywords) {
		super(completionNode.sourceStart(), completionNode.sourceEnd(), token);
		this.possibleKeywords = possibleKeywords;
	}
	
	public char[] getToken( ) {
		return getName().toCharArray();
	}
	public String[] getPossibleKeywords( ) {
		return this.possibleKeywords;
	}

	public void printNode(CorePrinter output) {
	}

	public void traverse(ASTVisitor pVisitor) throws Exception {
	}

	public boolean canCompleteEmptyToken() {
		return true;
	}
}
