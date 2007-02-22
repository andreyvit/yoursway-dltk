package org.eclipse.dltk.debug.dbgp.tests;

import junit.framework.TestCase;

import org.eclipse.dltk.dbgp.internal.DbgpRequest;

public class DbgpRequestTests extends TestCase {

	private DbgpRequest request;

	protected void setUp() throws Exception {
		super.setUp();

		request = new DbgpRequest("test_command");
	}

	public void testOptions() {
		request.addOption("-t", 324);

		assertTrue(request.hasOption("-t"));
		assertEquals(Integer.toString(324), request.getOption("-t"));

		assertEquals("test_command", request.getCommand());
	}

	public void testData() {
		request.setData("my_data");
		assertEquals("my_data", request.getData());
	}

	public void testStringRepresentation() {
		request.addOption("-i", 324);
		request.setData("my_data");

		assertEquals("test_command -i 324 -- bXlfZGF0YQ==", request.toString());
	}

	public void testEquals() {
		DbgpRequest r1 = new DbgpRequest("step_command_xxx");
		r1.addOption("-a", 32);
		r1.addOption("-b", 12);
		r1.setData("my_data");

		DbgpRequest r2 = new DbgpRequest("step_command_xxx");
		r2.setData("my_data");
		r2.addOption("-b", 12);
		r2.addOption("-a", 32);

		assertEquals(r1, r2);
		assertEquals(r2, r1);
	}
}
