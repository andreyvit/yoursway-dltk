package org.eclipse.dltk.dbgp.internal;

import java.io.IOException;

import org.eclipse.dltk.dbgp.IDbgpNotificationManager;
import org.eclipse.dltk.dbgp.IDbgpSession;
import org.eclipse.dltk.dbgp.IDbgpSessionInfo;
import org.eclipse.dltk.dbgp.commands.IDbgpCoreCommands;
import org.eclipse.dltk.dbgp.commands.IDbgpExtendedCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.commands.DbgpCoreCommands;
import org.eclipse.dltk.dbgp.internal.commands.DbgpDebuggingEngineCommunicator;
import org.eclipse.dltk.dbgp.internal.commands.DbgpExtendedCommands;
import org.eclipse.dltk.dbgp.internal.managers.DbgpNotificationManager;
import org.eclipse.dltk.dbgp.internal.managers.DbgpStreamManager;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlEntityParser;

public class DbgpSession extends DbgpTermination implements IDbgpSession,
		IDbgpTerminationListener {
	private IDbgpDebugingEngine engine;

	private IDbgpCoreCommands coreCommands;

	private IDbgpExtendedCommands extendedCommands;

	private DbgpNotificationManager notificationManager;

	private DbgpStreamManager streamManager;

	private IDbgpSessionInfo info;

	private Object terminatingLock = new Object();
	private boolean terminating = false;

	private Object terminatedLock = new Object();
	private boolean terminated = false;

	private void requestTerminateImpl(Object object) {
		if (object != engine) {
			engine.requestTermination();
		}

		if (object != streamManager) {
			streamManager.requestTermination();
		}

		if (object != notificationManager) {
			notificationManager.requestTermination();
		}
	}

	private void waitTerminatedImpl(Object object) throws InterruptedException {
		// System.out.println("-- Waiting engine");
		if (object != engine) {
			engine.waitTerminated();
		}

		// System.out.println("-- Waiting streamManager");
		if (object != streamManager) {
			streamManager.waitTerminated();
		}

		// System.out.println("-- Waiting notificationManager");
		if (object != notificationManager) {
			notificationManager.waitTerminated();
		}
	}

	public DbgpSession(IDbgpDebugingEngine engine) throws DbgpException,
			IOException {
		if (engine == null) {
			throw new IllegalArgumentException();
		}

		this.engine = engine;

		try {
			info = DbgpXmlEntityParser.parseSession(engine.getResponsePacket(
					-1).getContent());
		} catch (InterruptedException e) {
		}

		// Engine
		this.engine.addTerminationListener(this);

		// Notification manager
		this.notificationManager = new DbgpNotificationManager(engine);
		this.notificationManager.addTerminationListener(this);

		// Stream manager
		this.streamManager = new DbgpStreamManager(engine);
		this.streamManager.addTerminationListener(this);

		// Commands
		DbgpDebuggingEngineCommunicator communicator = new DbgpDebuggingEngineCommunicator(
				engine);

		this.coreCommands = new DbgpCoreCommands(communicator, streamManager);
		this.extendedCommands = new DbgpExtendedCommands(communicator);

		// Starting all
		this.notificationManager.start();
		this.streamManager.start();
	}

	public IDbgpSessionInfo getInfo() {
		return info;
	}

	public String toString() {
		return "Session. appid: " + info.getApplicationId();
	}

	public IDbgpCoreCommands getCoreCommands() {
		return coreCommands;
	}

	public IDbgpExtendedCommands getExtendedCommands() {
		return extendedCommands;
	}

	public IDbgpNotificationManager getNotificationManager() {
		return notificationManager;
	}

	// IDbgpTermination
	public void requestTermination() {
		synchronized (terminatedLock) {
			if (terminated) {
				return;
			}

			requestTerminateImpl(null);
		}
	}

	public void waitTerminated() throws InterruptedException {
		synchronized (terminatedLock) {
			if (terminated) {
				return;
			}

			waitTerminatedImpl(null);
		}
	}

	// IDbgpTerminationListener
	public void objectTerminated(Object object, Exception e) {
		// Allows to unblock all terminating threads
		synchronized (terminatingLock) {
			if (terminating) {
				return;
			} else {
				terminating = true;
			}
		}

		// Should be executed only once
		synchronized (terminatedLock) {
			engine.removeTerminationListener(this);
			streamManager.removeTerminationListener(this);
			notificationManager.removeTerminationListener(this);

			// Request terminate
			requestTerminateImpl(object);

			try {
				waitTerminatedImpl(object);
			} catch (InterruptedException ex) {
				// OK, interrrputed
			}

			terminated = true;
		}

		fireObjectTerminated(e);
	}
}
