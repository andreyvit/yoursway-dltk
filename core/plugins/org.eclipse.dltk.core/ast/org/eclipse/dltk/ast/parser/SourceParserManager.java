package org.eclipse.dltk.ast.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKContributionExtensionManager;
import org.eclipse.dltk.core.DLTKCore;

/**
 * Manager responsible for all contributed <code>ISourceParser</code>
 * extension implementations.
 */
public class SourceParserManager extends DLTKContributionExtensionManager {

	private static final String SOURCE_PARSER_EXT_POINT = DLTKCore.PLUGIN_ID
			+ ".sourceParsers";

	private static final String PARSER_TAG = "parser";

	private static SourceParserManager instance;

	public static SourceParserManager getInstance() {
		if (instance == null) {
			instance = new SourceParserManager();
		}

		return instance;
	}

	/*
	 * @see org.eclipse.dltk.core.DLTKContributionExtensionManager#getContributionElementName()
	 */
	protected String getContributionElementName() {
		return PARSER_TAG;
	}

	/*
	 * @see org.eclipse.dltk.core.DLTKContributionExtensionManager#getExtensionPoint()
	 */
	protected String getExtensionPoint() {
		return SOURCE_PARSER_EXT_POINT;
	}

	public ISourceParser getSourceParser(String natureId) {
		List parsers = getPriorityContributions(natureId);

		if (parsers.isEmpty()) {
			return null;
		}
		try {
			return (ISourceParser) ((ContributionElement) parsers.get(0))
					.createExecutableClass();
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private List getPriorityContributions(String natureId) {
		List contributions = new ArrayList();
		contributions.addAll(this.getContributions(natureId));
		// Lets sort contributions by priority.
		Collections.sort(contributions, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				if (arg0 instanceof ContributionElement
						&& arg1 instanceof ContributionElement) {
					ContributionElement e1 = (ContributionElement) arg0;
					ContributionElement e2 = (ContributionElement) arg1;
					if (e1.getPriority() == e2.getPriority()) {
						return 0;
					}
					if (e1.getPriority() < e2.getPriority()) {
						return -1;
					}
					return 1;
				}
				return 0;
			}
		});
		return contributions;
	}
}
