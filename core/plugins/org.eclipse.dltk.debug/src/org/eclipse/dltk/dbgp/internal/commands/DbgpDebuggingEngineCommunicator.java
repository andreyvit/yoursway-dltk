package org.eclipse.dltk.dbgp.internal.commands;

import java.io.IOException;

import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.exceptions.DbgpIOException;
import org.eclipse.dltk.dbgp.internal.DbgpRequest;
import org.eclipse.dltk.dbgp.internal.IDbgpDebugingEngine;
import org.eclipse.dltk.dbgp.internal.packets.DbgpResponsePacket;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlParser;
import org.w3c.dom.Element;

public class DbgpDebuggingEngineCommunicator implements IDbgpCommunicator {

	private IDbgpDebugingEngine engine;

	private void sendRequest(String command) throws IOException {
		engine.sendCommand(command);
	}

	private DbgpResponsePacket receiveResponse(int transactionId)
			throws IOException, InterruptedException {
		return engine.getResponsePacket(transactionId);
	}

	public DbgpDebuggingEngineCommunicator(IDbgpDebugingEngine engine) {
		if (engine == null) {
			throw new IllegalArgumentException();
		}

		this.engine = engine;
	}

	public Element communicate(DbgpRequest request) throws DbgpException {
		try {
			sendRequest(request.toString());
			Element response = receiveResponse(
					Integer.parseInt(request.getOption("-i"))).getContent();

			DbgpException e = DbgpXmlParser.checkError(response);
			if (e != null) {
				throw e;
			}

			return response;
		} catch (InterruptedException e) {
			return null;
		} catch (IOException e) {
			throw new DbgpIOException(e);
		}
	}
}
