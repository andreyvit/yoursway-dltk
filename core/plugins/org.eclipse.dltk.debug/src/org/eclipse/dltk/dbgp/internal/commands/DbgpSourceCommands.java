package org.eclipse.dltk.dbgp.internal.commands;

import java.net.URI;

import org.eclipse.dltk.dbgp.commands.IDbgpSourceCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.DbgpRequest;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlParser;
import org.w3c.dom.Element;

public class DbgpSourceCommands extends DbgpBaseCommands implements
		IDbgpSourceCommands {

	private static final String SOURCE_COMMAND = "source";

	public DbgpSourceCommands(IDbgpCommunicator communicator) {
		super(communicator);
	}

	protected String parseResponseXml(Element response) throws DbgpException {
		boolean success = DbgpXmlParser.parseSuccess(response);

		if (success) {
			DbgpXmlParser.parseBase64Content(response);
		}

		return null;
	}

	protected String getSource(URI uri, Integer beginLine, Integer endLine)
			throws DbgpException {
		DbgpRequest request = createRequest(SOURCE_COMMAND);

		if (beginLine != null) {
			request.addOption("-b", beginLine);
		}
		if (endLine != null) {
			request.addOption("-e", endLine);
		}

		request.addOption("-f", uri.toString());

		return parseResponseXml(communicate(request));
	}

	public String getSource(URI uri) throws DbgpException {
		return getSource(uri, null, null);
	}

	public String getSource(URI uri, int beginLine) throws DbgpException {
		return getSource(uri, new Integer(beginLine), null);
	}

	public String getSource(URI uri, int beginLine, int endLine)
			throws DbgpException {
		return getSource(uri, new Integer(beginLine), new Integer(endLine));
	}
}
