package org.eclipse.dltk.ast.parser;

import java.util.List;

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

	/*
	 * @see org.eclipse.dltk.core.DLTKContributionExtensionManager#isValidSelector(java.lang.Object)
	 */
	protected boolean isValidSelector(Object object) {
		return (object instanceof ISourceParserSelector);
	}

	/*
	 * @see org.eclipse.dltk.core.DLTKContributionExtensionManager#isValidContribution(java.lang.Object)
	 */
	protected boolean isValidContribution(Object object) {
		return (object instanceof ISourceParser);
	}

	/**
	 * @deprecated
	 */
	public ISourceParser getSourceParser(String natureId) {
		List parsers = getContributions(natureId);

		if (parsers.isEmpty()) {
			return null;
		}

		// mimic current functionality
		return (ISourceParser) parsers.get(0);
	}

}
