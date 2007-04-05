package org.eclipse.dltk.ast.declarations;

import org.eclipse.dltk.compiler.problem.IProblemReporter;

public interface ISourceParser {
	ModuleDeclaration parse(char[] source, IProblemReporter reporter);
}
