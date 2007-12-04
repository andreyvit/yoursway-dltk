package org.eclipse.dltk.ast.parser;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.dltk.core.DLTKContributedExtension;
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

	public ISourceParser getSourceParserById(String id) {
		return ((SourceParserContribution) getContributionById(id)).getSourceParser();
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
	 * @see org.eclipse.dltk.core.DLTKContributionExtensionManager#isValidContribution(java.lang.Object)
	 */
	protected boolean isValidContribution(Object object) {
		return (object instanceof ISourceParserFactory);
	}
	
	/*
	 * @see org.eclipse.dltk.core.DLTKContributionExtensionManager#configureContribution(java.lang.Object, org.eclipse.core.runtime.IConfigurationElement)
	 */
	protected Object configureContribution(Object object,
			IConfigurationElement config) {
		/*
		 * using the following delegate class allows for integration with the
		 * generic managed contribution preference page.
		 * 
		 * not all source parsers are thread safe, so the factory allows us
		 * to create a new instance each time one is requested
		 */		
		return new SourceParserContribution((ISourceParserFactory) object, config);
	}

	public ISourceParser getSourceParser(String natureId) {
		return ((SourceParserContribution) getSelectedContribution(natureId)).getSourceParser();
	}
	
	class SourceParserContribution extends DLTKContributedExtension {		

		private ISourceParserFactory factory;
		private IConfigurationElement config;
		
		SourceParserContribution(ISourceParserFactory factory, IConfigurationElement config) {
			this.factory = factory;
			this.config = config;
			
			/*
			 * this is a cheat - this class contains all the attributes of the 
			 * configured extension, so leverage the code DLTKContributedExtension
			 * already provides
			 */
			setInitializationData(config, null, null);
		}

		ISourceParser getSourceParser() {
			ISourceParser parser = factory.createSourceParser();
			/*
			 * another cheat - not all source parsers are thread safe, so
			 * we need to create a new instance each time one is requested (hence
			 * the factory). 
			 *
			 * the parser instance should be initialized with all it's attribute
			 * data
			 */
			((AbstractSourceParser) parser).setInitializationData(config, null, null);
			
			return parser;
		}
	}
}
