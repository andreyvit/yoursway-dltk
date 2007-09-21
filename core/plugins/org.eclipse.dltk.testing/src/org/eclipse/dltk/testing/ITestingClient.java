package org.eclipse.dltk.testing;


public interface ITestingClient {

	public void testRunStart(final int count);

	public void testTree(final int testId, final String testName, final boolean issuite, final int testCound);

	public void testTerminated(int elapsed);

	public void testStarted(final int id, final String name);

	public void testEnded(final int id, final String name);

	public void traceMessage(final String message);
	public void traceStart();
	public void traceEnd();

	public void testError(final int id, final String name);
	public void testFailed(int id, String name);
	
	public void testActual(final String actual);
	public void testExpected(final String expected);

}