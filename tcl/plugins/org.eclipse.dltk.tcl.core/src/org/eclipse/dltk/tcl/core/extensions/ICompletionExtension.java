package org.eclipse.dltk.tcl.core.extensions;

import java.util.Set;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.CompletionRequestor;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclCompletionEngine;
import org.eclipse.dltk.tcl.internal.core.codeassist.completion.CompletionOnKeywordArgumentOrFunctionArgument;
import org.eclipse.dltk.tcl.internal.core.codeassist.completion.CompletionOnKeywordOrFunction;
import org.eclipse.dltk.tcl.internal.core.codeassist.completion.TclCompletionParser;

public interface ICompletionExtension {
	// This is compltion parser features extension
	boolean visit(Expression s, TclCompletionParser parser, int position);

	boolean visit(Statement s, TclCompletionParser parser, int position);

	// this is completion features extension
	void completeOnKeywordOrFunction(CompletionOnKeywordOrFunction key,
			ASTNode astNodeParent, TclCompletionEngine engine);

	void completeOnKeywordArgumentsOne(String name,
			CompletionOnKeywordArgumentOrFunctionArgument compl,
			Set methodNames, TclStatement st,
			TclCompletionEngine tclCompletionEngine);

	void setRequestor(CompletionRequestor requestor);
}
