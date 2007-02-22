package org.eclipse.dltk.core.search;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.search.matching.PossibleMatch;
import org.eclipse.dltk.internal.core.search.matching.MatchingNodeSet;

public interface IMatchLocatorParser {

	void setNodeSet(MatchingNodeSet nodeSet);

	ModuleDeclaration parse(PossibleMatch possibleMatch);

	void parseBodies(ModuleDeclaration unit);
}
