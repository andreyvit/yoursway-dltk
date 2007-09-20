package org.eclipse.dltk.testing;

import org.eclipse.debug.core.ILaunch;

import org.eclipse.dltk.testing.model.ITestElement.FailureTrace;


public interface ITestSession {

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.junit.model.ITestElement#getFailureTrace()
	 */
	public abstract FailureTrace getFailureTrace();

	/**
	 * @return the launch, or <code>null</code> iff this session was run externally
	 */
	public abstract ILaunch getLaunch();

	public abstract int getErrorCount();

	public abstract int getFailureCount();

	public abstract int getStartedCount();

	public abstract int getIgnoredCount();

	public abstract void setTotalCount(int count);

	public abstract long getStartTime();

	/**
	 * @return <code>true</code> iff the session has been stopped or terminated
	 */
	public abstract boolean isStopped();

	public abstract boolean isStarting();

	/**
	 * @return <code>true</code> iff this session has been started, but not ended nor stopped nor terminated
	 */
	public abstract boolean isRunning();

}