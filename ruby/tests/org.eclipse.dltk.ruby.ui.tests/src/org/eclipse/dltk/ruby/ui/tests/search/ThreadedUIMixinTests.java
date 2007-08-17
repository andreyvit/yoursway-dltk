/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.ui.tests.search;

import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.core.tests.model.AbstractDLTKSearchTests;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixinModel;
import org.eclipse.dltk.ruby.ui.tests.internal.RubyUITestsPlugin;

public class ThreadedUIMixinTests extends AbstractDLTKSearchTests {
	private static final String PROJECT_NAME = "mixins";

	public ThreadedUIMixinTests(String name) {
		super(RubyUITestsPlugin.PLUGIN_ID, name);
	}

	public static Suite suite() {
		return new Suite(ThreadedUIMixinTests.class);
	}

	public void setUpSuite() throws Exception {
		super.setUpSuite();
		up();
		waitUntilIndexesReady();
		buildAll();
	}

	private void buildAll() throws CoreException {
		ResourcesPlugin.getWorkspace()
				.build(IncrementalProjectBuilder.FULL_BUILD,
						new NullProgressMonitor());
		// waitForAutoBuild();
	}

	public void tearDownSuite() throws Exception {
		deleteProject(PROJECT_NAME);
		super.tearDownSuite();
	}

	private static String fgLastUsedID;

	protected String createUniqueId(IInterpreterInstallType InterpreterType) {
		String id = null;
		do {
			id = String.valueOf(System.currentTimeMillis());
		} while (InterpreterType.findInterpreterInstall(id) != null
				|| id.equals(fgLastUsedID));
		fgLastUsedID = id;
		return id;
	}

	private void up() throws Exception {
		if (SCRIPT_PROJECT != null) {
			deleteProject(SCRIPT_PROJECT.getElementName());
		}
		SCRIPT_PROJECT = setUpScriptProject(PROJECT_NAME);
	}

	class Access implements Runnable {
		private int start = 0;
		private int stop = 0;
		private int cycles = 0;
		private String[] keys;
		public boolean finish = false;

		public Access(String keys[], int start, int stop, int cycles) {
			this.start = start;
			this.stop = stop;
			this.cycles = cycles;
			this.keys = keys;
		}

		public void run() {
			RubyMixinModel instance = RubyMixinModel.getInstance();
			for (int i = 0; i < this.cycles; i++) {
				for (int j = 0; j < this.stop - this.start; j++) {
					instance.createRubyElement(this.keys[this.start + j]);
				//	System.out.println("#:" + this.start + ":" + this.stop);
				}
			}
			this.finish = true;
			System.out.println("Finished");
		}
	};
	class AccessUI extends Access {

		public AccessUI(String[] keys, int start, int stop, int cycles) {
			super(keys, start, stop, cycles);
			// TODO Auto-generated constructor stub
		}

		public void run() {
			// TODO Auto-generated method stub
//			this.notifyAll();
			super.run();
		}
		
	}
	public void testMultiAccess() throws Exception {
		int count = 10;
		String[] findKeys = RubyMixinModel.getRawInstance().findKeys("*");
		Thread[] threads = new Thread[count-1];
		Access[] access = new Access[count];
		int d = findKeys.length / count;
		for (int i = 0; i < count; i++) {
			if (i != count - 1) {
				Access a = new Access(findKeys, d * (i), d
						* (i+1), 1);
				access[i] = a;
				threads[i] = new Thread(a);
			}
			else {
				Access a = new AccessUI(findKeys, d * (i), findKeys.length, 10);
				access[i] = a;
//				threads[i] = new Thread(a);
			}
		}
		long s = System.currentTimeMillis();
//		RubyUITestsPlugin.getDefault().getWorkbench().getDisplay().syncExec(access[count -1]);
//		access[count -1].wait();
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {			
			threads[i].join(100000);
			assertTrue(access[i].finish);
		}
		access[count - 1].run();
		assertTrue(access[count-1].finish);
//		assertTrue(access[count-1].finish);
		long e = System.currentTimeMillis();
		System.out.println("time:" + Long.toString(e - s));
	}
}
