package org.eclipse.dltk.debug.internal.core.model.operations;

import org.eclipse.dltk.dbgp.IDbgpFeature;
import org.eclipse.dltk.dbgp.IDbgpSession;
import org.eclipse.dltk.dbgp.IDbgpStatus;
import org.eclipse.dltk.dbgp.commands.IDbgpCoreCommands;
import org.eclipse.dltk.dbgp.commands.IDbgpExtendedCommands;
import org.eclipse.dltk.dbgp.commands.IDbgpFeatureCommands;
import org.eclipse.dltk.dbgp.commands.IDbgpStreamCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.internal.core.model.IThreadManagement;

public class DbgpDebugger {
	// Operations
	private DbgpStepIntoOperation stepIntoOperation;

	private DbgpStepOverOperation stepOverOperation;

	private DbgpStepReturnOperation stepReturnOperation;

	private DbgpSuspendOperation suspendOperation;

	private DbgpResumeOperation resumeOperation;

	private DbgpTerminateOperation terminateOperation;

	private IDbgpSession session;

	public DbgpDebugger(IDbgpSession session, final IDbgpDebuggerFeedback end) {

		this.session = session;

		stepIntoOperation = new DbgpStepIntoOperation(session,
				new DbgpOperation.IResultHandler() {
					public void finish(IDbgpStatus status, DbgpException e) {
						end.endStepInto(e, status);
					}
				});

		stepOverOperation = new DbgpStepOverOperation(session,
				new DbgpOperation.IResultHandler() {
					public void finish(IDbgpStatus status, DbgpException e) {
						end.endStepOver(e, status);
					}
				});

		stepReturnOperation = new DbgpStepReturnOperation(session,
				new DbgpOperation.IResultHandler() {
					public void finish(IDbgpStatus status, DbgpException e) {
						end.endStepReturn(e, status);
					}
				});

		try {
			suspendOperation = new DbgpSuspendOperation(session,
					new DbgpOperation.IResultHandler() {
						public void finish(IDbgpStatus status, DbgpException e) {
							end.endSuspend(e, status);
						}
					});
		} catch (DbgpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resumeOperation = new DbgpResumeOperation(session,
				new DbgpOperation.IResultHandler() {
					public void finish(IDbgpStatus status, DbgpException e) {
						end.endResume(e, status);
					}
				});

		terminateOperation = new DbgpTerminateOperation(session,
				new DbgpOperation.IResultHandler() {
					public void finish(IDbgpStatus status, DbgpException e) {
						end.endTerminate(e, status);
					}
				});
	}

	public void stepInto() {
		stepIntoOperation.schedule();
	}

	public void stepOver() {
		stepOverOperation.schedule();
	}

	public void stepReturn() {
		stepReturnOperation.schedule();
	}

	public void suspend() {
		suspendOperation.schedule();
	}

	public void resume() {
		resumeOperation.schedule();
	}

	public void terminate() {
		terminateOperation.schedule();
	}

	// Feature helper methods
	protected IDbgpFeature getFeature(String name) throws DbgpException {
		IDbgpCoreCommands core = session.getCoreCommands();
		return core.getFeature(name);
	}

	protected void setFeature(String name, String value) throws DbgpException {
		IDbgpCoreCommands core = session.getCoreCommands();
		core.setFeature(name, value);
	}

	public boolean isFeatureSupported(String name) throws DbgpException {
		return getFeature(name).isSupported();
	}

	public boolean getFeatureBoolean(String name) throws DbgpException {
		return getFeature(name).getValue().equals(IDbgpFeature.ONE_VALUE);
	}

	public void setFeatureBoolean(String name, boolean value)
			throws DbgpException {
		setFeature(name, value ? IDbgpFeature.ONE_VALUE
				: IDbgpFeature.ZERO_VALUE);
	}

	public int getFeatureInteger(String name) throws DbgpException {
		return Integer.parseInt(getFeature(name).getValue());
	}

	public void setFeatureInteger(String name, int value) throws DbgpException {
		setFeature(name, Integer.toString(value));
	}

	// Must available features
	public boolean isSupportsThreads() throws DbgpException {
		return getFeature(IDbgpFeatureCommands.LANGUAGE_SUPPORTS_THREADS)
				.getValue().equals(IDbgpFeature.ONE_VALUE);
	}

	public String getLanguageName() throws DbgpException {
		return getFeature(IDbgpFeatureCommands.LANGUAGE_NAME).getValue();
	}

	public String getLanguageVersion() throws DbgpException {
		return getFeature(IDbgpFeatureCommands.LANGUAGE_VERSION).getValue();
	}

	public String getEncoding() throws DbgpException {
		return getFeature(IDbgpFeatureCommands.ENCODING).getValue();
	}

	public String getDataEncoding() throws DbgpException {
		return getFeature(IDbgpFeatureCommands.DATA_ENCODING).getValue();
	}

	public String getProtocolVersion() throws DbgpException {
		return getFeature(IDbgpFeatureCommands.PROTOCOL_VERSION).getValue();
	}

	public boolean isSupportsAsync() throws DbgpException {
		return getFeature(IDbgpFeatureCommands.SUPPORTS_ASYNC).getValue()
				.equals(IDbgpFeature.ONE_VALUE);
	}

	public String getBreakpointLanguages() throws DbgpException {
		return getFeature(IDbgpFeatureCommands.BREAKPOINT_LANGUAGES).getValue();
	}

	// Multiple sessions
	public boolean getMultipleSessions() throws DbgpException {
		return getFeature(IDbgpFeatureCommands.MULTIPLE_SESSIONS).getValue()
				.equals(IDbgpFeature.ONE_VALUE);
	}

	public void setMultipleSessions(boolean value) throws DbgpException {
		setFeature(IDbgpFeatureCommands.MULTIPLE_SESSIONS,
				value ? IDbgpFeature.ONE_VALUE : IDbgpFeature.ZERO_VALUE);
	}

	// Max children
	public int getMaxChildren() throws DbgpException {
		return getFeatureInteger(IDbgpFeatureCommands.MAX_CHILDREN);
	}

	public void setMaxChildren(int value) throws DbgpException {
		setFeatureInteger(IDbgpFeatureCommands.MAX_CHILDREN, value);
	}

	// Max data
	public int getMaxData() throws DbgpException {
		return getFeatureInteger(IDbgpFeatureCommands.MAX_DATA);
	}

	public void setMaxData(int value) throws DbgpException {
		setFeatureInteger(IDbgpFeatureCommands.MAX_DATA, value);
	}

	// Max depth
	public int getMaxDepth() throws DbgpException {
		return getFeatureInteger(IDbgpFeatureCommands.MAX_DEPTH);
	}

	public void setMaxDepth(int value) throws DbgpException {
		setFeatureInteger(IDbgpFeatureCommands.MAX_DEPTH, value);
	}

	// Optional features

	// Post morten
	public boolean isPostMortenSupported() throws DbgpException {
		return isFeatureSupported(IDbgpFeatureCommands.SUPPORTS_POSTMORTEN);
	}

	public boolean getPostMorten() throws DbgpException {
		return getFeatureBoolean(IDbgpFeatureCommands.SUPPORTS_POSTMORTEN);
	}

	// Show hidden
	public boolean isShowHiddenSupported() throws DbgpException {
		return isFeatureSupported(IDbgpFeatureCommands.SHOW_HIDDEN);
	}

	public boolean getShowHidden() throws DbgpException {
		return getFeatureBoolean(IDbgpFeatureCommands.SHOW_HIDDEN);
	}

	public void setShowHidden(boolean value) throws DbgpException {
		setFeatureBoolean(IDbgpFeatureCommands.SHOW_HIDDEN, value);
	}

	// Notfy ok
	public boolean isNotifyOkSupported() throws DbgpException {
		return isFeatureSupported(IDbgpFeatureCommands.NOTIFY_OK);
	}

	public boolean getNotifyOk() throws DbgpException {
		return getFeatureBoolean(IDbgpFeatureCommands.NOTIFY_OK);
	}

	public void setNotifyOk(boolean value) throws DbgpException {
		setFeatureBoolean(IDbgpFeatureCommands.NOTIFY_OK, value);
	}

	protected void configureStdout(int value) throws DbgpException {
		IDbgpCoreCommands core = session.getCoreCommands();
		// TODO: error handling
		core.configureStdout(value);
	}

	// Stdout
	public void disableStdout() throws DbgpException {
		configureStdout(IDbgpStreamCommands.DISABLE);
	}

	public void copyStdout() throws DbgpException {
		configureStdout(IDbgpStreamCommands.COPY);
	}

	public void redirectStdout() throws DbgpException {
		configureStdout(IDbgpStreamCommands.REDIRECT);
	}

	// Stderr
	protected void configureStderr(int value) throws DbgpException {
		IDbgpCoreCommands core = session.getCoreCommands();
		// TODO: error handling
		core.configureStderr(value);
	}

	public void disableStderr() throws DbgpException {
		configureStderr(IDbgpStreamCommands.DISABLE);
	}

	public void copyStderr() throws DbgpException {
		configureStderr(IDbgpStreamCommands.COPY);
	}

	public void redirectStderr() throws DbgpException {
		configureStderr(IDbgpStreamCommands.REDIRECT);
	}

	// Stdin
	protected void configureStdin(int value) throws DbgpException {
		IDbgpExtendedCommands extended = session.getExtendedCommands();
		// TODO: handling
		extended.configureStdin(value);
	}

	public void disableStdin() throws DbgpException {
		configureStdin(IDbgpExtendedCommands.DISABLE);
	}

	public void redirectStdin() throws DbgpException {
		configureStdin(IDbgpExtendedCommands.REDIRECT);
	}

	public static void printEngineInfo(DbgpDebugger d) throws DbgpException {
		System.out.println(IDbgpFeatureCommands.LANGUAGE_SUPPORTS_THREADS
				+ ": " + d.isSupportsThreads());
		System.out.println(IDbgpFeatureCommands.LANGUAGE_NAME + ": "
				+ d.getLanguageName());
		System.out.println(IDbgpFeatureCommands.LANGUAGE_VERSION + ": "
				+ d.getLanguageVersion());
		System.out.println(IDbgpFeatureCommands.ENCODING + ": "
				+ d.getEncoding());
		System.out.println(IDbgpFeatureCommands.DATA_ENCODING + ": "
				+ d.getDataEncoding());
		System.out.println(IDbgpFeatureCommands.PROTOCOL_VERSION + ": "
				+ d.getProtocolVersion());
		System.out.println(IDbgpFeatureCommands.SUPPORTS_ASYNC + ": "
				+ d.isSupportsAsync());
		System.out.println(IDbgpFeatureCommands.BREAKPOINT_LANGUAGES + ": "
				+ d.getBreakpointLanguages());
		System.out.println(IDbgpFeatureCommands.MULTIPLE_SESSIONS + ": "
				+ d.getMultipleSessions());
		System.out.println(IDbgpFeatureCommands.MAX_CHILDREN + ": "
				+ d.getMaxChildren());
		System.out.println(IDbgpFeatureCommands.MAX_DATA + ": "
				+ d.getMaxData());
		System.out.println(IDbgpFeatureCommands.MAX_DEPTH + ": "
				+ d.getMaxDepth());

		if (d.isPostMortenSupported()) {
			System.out.println("Support of "
					+ IDbgpFeatureCommands.SUPPORTS_POSTMORTEN
					+ ": true, value: " + d.getPostMorten());
		} else {
			System.out.println("Support of "
					+ IDbgpFeatureCommands.SUPPORTS_POSTMORTEN + ": false");
		}

		if (d.isShowHiddenSupported()) {
			System.out.println("Support of " + IDbgpFeatureCommands.SHOW_HIDDEN
					+ ": true, value: " + d.getShowHidden());
		} else {
			System.out.println("Support of " + IDbgpFeatureCommands.SHOW_HIDDEN
					+ ": false");
		}

		if (d.isNotifyOkSupported()) {
			System.out.println("Support of " + IDbgpFeatureCommands.NOTIFY_OK
					+ ": true, value: " + d.getNotifyOk());
		} else {
			System.out.println("Support of " + IDbgpFeatureCommands.NOTIFY_OK
					+ ": false");
		}
	}
}
