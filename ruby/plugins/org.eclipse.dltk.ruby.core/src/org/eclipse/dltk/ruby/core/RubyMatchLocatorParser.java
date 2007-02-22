package org.eclipse.dltk.ruby.core;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.search.IMatchLocatorParser;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.core.search.matching.MatchLocatorParser;
import org.eclipse.dltk.core.search.matching.PossibleMatch;
import org.eclipse.dltk.ruby.internal.parser.RubySourceParser;

public class RubyMatchLocatorParser extends MatchLocatorParser implements IMatchLocatorParser {
	private RubySourceParser parser = new RubySourceParser();
	public RubyMatchLocatorParser(MatchLocator locator) {
		super(locator);
	}

	public ModuleDeclaration parse(PossibleMatch possibleMatch) {
		return parser.parse(possibleMatch.getSourceContents());
	}

	public void parseBodies(ModuleDeclaration unit) {
		// TODO Auto-generated method stub		
	}
}
