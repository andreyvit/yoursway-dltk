package org.eclipse.dltk.dbgp.internal.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.dltk.dbgp.commands.IDbgpContextCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.DbgpRequest;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlEntityParser;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DbgpContextCommands extends DbgpBaseCommands implements
		IDbgpContextCommands {
	private static final String CONTEXT_NAMES_COMMAND = "context_names";

	private static final String CONTEXT_GET = "context_get";

	private static final String TAG_CONTEXT = "context";

	private static final String TAG_PROPERTY = "property";

	private static final String ATTR_NAME = "name";

	private static final String ATTR_ID = "id";

	public DbgpContextCommands(IDbgpCommunicator communicator) {
		super(communicator);
	}

	protected Map parseContextNamesResponse(Element response)
			throws DbgpException {
		Map map = new HashMap();

		NodeList contexts = response.getElementsByTagName(TAG_CONTEXT);
		for (int i = 0; i < contexts.getLength(); ++i) {
			Element context = (Element) contexts.item(i);
			String name = context.getAttribute(ATTR_NAME);
			Integer id = new Integer(context.getAttribute(ATTR_ID));
			map.put(id, name);
		}

		return map;
	}

	protected List parseContextPropertiesResponse(Element response)
			throws DbgpException {
		NodeList properties = response.getElementsByTagName(TAG_PROPERTY);

		List list = new ArrayList();
		for (int i = 0; i < properties.getLength(); ++i) {
			list.add(DbgpXmlEntityParser.parseProperty((Element) properties
					.item(i)));
		}

		return list;
	}

	public Map getContextNames(int stackDepth) throws DbgpException {
		DbgpRequest request = createRequest(CONTEXT_NAMES_COMMAND);
		request.addOption("-d", stackDepth);
		return parseContextNamesResponse(communicate(request));
	}

	public List getContextProperties(int stackDepth) throws DbgpException {
		DbgpRequest request = createRequest(CONTEXT_GET);
		request.addOption("-d", stackDepth);
		return parseContextPropertiesResponse(communicate(request));
	}

	public List getContextProperties(int stackDepth, int contextId)
			throws DbgpException {
		DbgpRequest request = createRequest(CONTEXT_GET);
		request.addOption("-d", stackDepth);
		request.addOption("-c", contextId);
		return parseContextPropertiesResponse(communicate(request));
	}
}
