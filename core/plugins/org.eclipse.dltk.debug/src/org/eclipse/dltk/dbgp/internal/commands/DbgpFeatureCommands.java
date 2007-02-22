package org.eclipse.dltk.dbgp.internal.commands;

import org.eclipse.dltk.dbgp.IDbgpFeature;
import org.eclipse.dltk.dbgp.commands.IDbgpFeatureCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.DbgpRequest;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlEntityParser;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlParser;

public class DbgpFeatureCommands extends DbgpBaseCommands implements
		IDbgpFeatureCommands {

	private static final String FEATURE_SET_COMMAND = "feature_set";

	private static final String FEATURE_GET_COMMAND = "feature_get";

	public DbgpFeatureCommands(IDbgpCommunicator communicator) {
		super(communicator);
	}

	public IDbgpFeature getFeature(String featureName) throws DbgpException {
		DbgpRequest request = createRequest(FEATURE_GET_COMMAND);
		request.addOption("-n", featureName);
		return DbgpXmlEntityParser.parseFeature(communicate(request));
	}

	public boolean setFeature(String featureName, String featureValue)
			throws DbgpException {
		DbgpRequest request = createRequest(FEATURE_SET_COMMAND);
		request.addOption("-n", featureName);
		request.addOption("-v", featureValue);
		return DbgpXmlParser.parseSuccess(communicate(request));
	}
}
