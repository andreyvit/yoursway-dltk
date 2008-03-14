package org.eclipse.dltk.examples.python.internal.search;

import org.eclipse.dltk.core.search.AbstractSearchFactory;
import org.eclipse.dltk.core.search.IMatchLocatorParser;
import org.eclipse.dltk.core.search.matching.MatchLocator;

public class ExamplePythonSearchFactory extends AbstractSearchFactory {
	public IMatchLocatorParser createMatchParser(MatchLocator locator) {
		return new ExamplePythonMatchLocationParser(locator);
	}
}
