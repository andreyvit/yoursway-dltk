package org.eclipse.dltk.dbgp.internal.packets;

import java.io.IOException;
import java.io.OutputStream;

public class DbgpPacketSender {
	private OutputStream output;

	private IDbgpLogger logger;

	public DbgpPacketSender(OutputStream output) {
		if (output == null) {
			throw new IllegalArgumentException();
		}

		this.output = output;
	}

	public void sendCommand(String command) throws IOException {
		if (command == null) {
			throw new IllegalArgumentException();
		}

		if (logger != null) {
			logger.logOutput(command);
		}

		output.write(command.getBytes("ASCII"));
		output.write(0);
		output.flush();
	}

	public void setLogger(IDbgpLogger logger) {
		this.logger = logger;
	}
}
