package org.eclipse.dltk.dbgp.internal;

import java.util.Map;
import java.util.TreeMap;

import org.eclipse.dltk.dbgp.IDbgpStatus;

public class DbgpStatus implements IDbgpStatus {
	// Reasons
	private static final Integer REASON_OK = new Integer(0);

	private static final Integer REASON_ERROR = new Integer(1);

	private static final Integer REASON_ABORTED = new Integer(2);

	private static final Integer REASON_EXCEPTION = new Integer(3);

	// Status
	private static final Integer STATUS_STARTING = new Integer(0);

	private static final Integer STATUS_STOPPING = new Integer(1);

	private static final Integer STATUS_STOPPED = new Integer(2);

	private static final Integer STATUS_RUNNING = new Integer(3);

	private static final Integer STATUS_BREAK = new Integer(4);

	private static Map statusParser = new TreeMap(String.CASE_INSENSITIVE_ORDER);

	private static Map reasonParser = new TreeMap(String.CASE_INSENSITIVE_ORDER);

	static {
		statusParser.put("starting", STATUS_STARTING);
		statusParser.put("stopping", STATUS_STOPPING);
		statusParser.put("stopped", STATUS_STOPPED);
		statusParser.put("running", STATUS_RUNNING);
		statusParser.put("break", STATUS_BREAK);

		reasonParser.put("ok", REASON_OK);
		reasonParser.put("error", REASON_ERROR);
		reasonParser.put("aborted", REASON_ABORTED);
		reasonParser.put("exception", REASON_EXCEPTION);
	}

	private Integer status;

	private Integer reason;

	public DbgpStatus(String status, String reason) {
		this.status = (Integer) statusParser.get(status);

		if (this.status == null) {
			throw new IllegalArgumentException();
		}

		this.reason = (Integer) reasonParser.get(reason);

		if (this.reason == null) {
			throw new IllegalArgumentException();
		}
	}

	public boolean reasonAborred() {
		return REASON_ABORTED == reason;
	}

	public boolean reasonError() {
		return REASON_ERROR == reason;
	}

	public boolean reasonException() {
		return REASON_EXCEPTION == reason;
	}

	public boolean reasonOk() {
		return REASON_OK == reason;
	}

	public boolean isRunning() {
		return STATUS_RUNNING == status;
	}

	public boolean isStarting() {
		return STATUS_STARTING == status;
	}

	public boolean isStopped() {
		return STATUS_STOPPED == status;
	}

	public boolean isStopping() {
		return STATUS_STOPPING == status;
	}

	public boolean isBreak() {
		return STATUS_BREAK == status;
	}

	public boolean equals(Object obj) {
		if (obj instanceof DbgpStatus) {
			DbgpStatus s = (DbgpStatus) obj;
			return this.status == s.status || this.reason == s.reason;
		}

		return false;
	}

	public int hashCode() {
		return (status.hashCode() << 8) | reason.hashCode();
	}
}
