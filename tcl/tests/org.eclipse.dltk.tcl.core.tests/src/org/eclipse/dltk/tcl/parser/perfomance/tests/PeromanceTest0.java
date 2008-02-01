/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.tcl.parser.perfomance.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import junit.framework.TestCase;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.parser.ISourceParserConstants;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.core.tests.model.AbstractModelTests;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.core.tests.model.Activator;
import org.eclipse.dltk.tcl.internal.core.search.mixin.TclMixinBuildVisitor;
import org.eclipse.dltk.tcl.internal.core.search.mixin.TclMixinParser;

public class PeromanceTest0 extends TestCase {
	ZipFile scriptsZip = null;
	private final static String[] scripts = new String[] { "append.tcl",
			"appendComp.tcl", "assocd.tcl", "async.tcl", "autoMkindex.tcl",
			"basic.tcl", "binary.tcl", "case.tcl", "clock.tcl", "cmdAH.tcl",
			"cmdIL.tcl", "cmdInfo.tcl", "cmdMZ.tcl", "compExpr.tcl",
			"compExpr-old.tcl", "compile.tcl", "concat.tcl", "dcall.tcl",
			"dstring.tcl", "encoding.tcl", "env.tcl", "error.tcl", "escp.tcl",
			"eval.tcl", "event.tcl", "exec.tcl", "execute.tcl", "expr.tcl",
			"expr-old.tcl", "fCmd.tcl", "fileName.tcl", "fileSystem.tcl",
			"for.tcl", "for-old.tcl", "foreach.tcl", "format.tcl", "get.tcl",
			"history.tcl", "http.tcl", "httpold.tcl", "if.tcl", "if-old.tcl",
			"incr.tcl", "incr-old.tcl", "indexObj.tcl", "info.tcl", "init.tcl",
			"interp.tcl", "io.tcl", "ioCmd.tcl", "iogt.tcl", "ioUtil.tcl",
			"join.tcl", "lindex.tcl", "link.tcl", "linsert.tcl", "list.tcl",
			"listObj.tcl", "llength.tcl", "load.tcl", "lrange.tcl",
			"lreplace.tcl", "lsearch.tcl", "lset.tcl", "lsetComp.tcl",
			"macFCmd.tcl", "main.tcl", "misc.tcl", "msgcat.tcl",
			"namespace.tcl", "namespace-old.tcl", "notify.tcl", "obj.tcl",
			"opt.tcl", "osa.tcl", "package.tcl", "parse.tcl", "parseExpr.tcl",
			"parseOld.tcl", "pid.tcl", "pkg.tcl", "pkgMkIndex.tcl",
			"platform.tcl", "proc.tcl", "proc-old.tcl", "pwd.tcl", "reg.tcl",
			"regexp.tcl", "regexpComp.tcl", "registry.tcl", "rename.tcl",
			"resource.tcl", "result.tcl", "safe.tcl", "scan.tcl",
			"security.tcl", "set.tcl", "set-old.tcl", "socket.tcl",
			"source.tcl", "split.tcl", "stack.tcl", "string.tcl",
			"stringComp.tcl", "stringObj.tcl", "subst.tcl", "switch.tcl",
			"tcl-import.tcl", "tcl-object.tcl", "tcltest.tcl", "test0.tcl",
			"thread.tcl", "timer.tcl", "trace.tcl", "unixFCmd.tcl",
			"unixFile.tcl", "unixInit.tcl", "unixNotfy.tcl", "unknown.tcl",
			"uplevel.tcl", "upvar.tcl", "utf.tcl", "util.tcl", "var.tcl",
			"while.tcl", "while-old.tcl", "winConsole.tcl", "winDde.tcl",
			"winFCmd.tcl", "winFile.tcl", "winNotify.tcl", "winPipe.tcl",
			"winTime.tcl" };

	private String getContents(InputStream stream) throws IOException {

		StringBuffer result = new StringBuffer();
		try {

			InputStreamReader isr = new InputStreamReader(stream);
			BufferedReader input = new BufferedReader(isr);

			while (stream.available() > 0) {
				long size = stream.available();

				char c[] = new char[(int) size];

				input.read(c);

				result.append(c);
			}

		} finally {
			if (stream != null) {
				stream.close();
			}
		}
		return result.toString();
	}

	protected void setUp() throws Exception {
		super.setUp();
		this.scriptsZip = new ZipFile(AbstractModelTests.storeToMetadata(
				Activator.getDefault().getBundle(), "tcl_scripts.zip",
				"/scripts/scripts.zip"));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		if (this.scriptsZip != null) {
			removeIfExist(this.scriptsZip.getName());
		}
	}

	private void removeIfExist(String name) {
		File file = new File(name);
		if (file.exists()) {
			file.delete();
		}
	}

	public void testPerfomance001() throws Exception {
		if (this.scriptsZip == null) {
			throw new IOException("Scripts not pressent...");
		}
		long start = System.currentTimeMillis();
		final List elements = new ArrayList();

		TclMixinParser parser = new TclMixinParser();
		IMixinRequestor requestor = new IMixinRequestor() {
			public void reportElement(ElementInfo info) {
				elements.add(info);
			}
		};
		for (int number = 0; number < scripts.length; number++) {
			String script = scripts[number];
			InputStream input = null;
			try {
				ZipEntry entry = scriptsZip.getEntry(script);
				if (entry == null) {
					throw new IOException("Script:" + script
							+ " not pressent in:" + scriptsZip.getName());
				}

				String content = getContents(scriptsZip.getInputStream(entry));
				parser.setRequirestor(requestor);

				ModuleDeclaration moduleDeclaration = SourceParserUtil
						.getModuleDeclaration("".toCharArray(), content
								.toCharArray(), TclNature.NATURE_ID, null,
								null,
								ISourceParserConstants.RUNTIME_MODEL);

				TclMixinBuildVisitor visitor = new TclMixinBuildVisitor(
						moduleDeclaration, null, false, requestor);
				try {
					moduleDeclaration.traverse(visitor);
				} catch (Exception e) {
					if (DLTKCore.DEBUG) {
						e.printStackTrace();
					}
				}

			} finally {
				if (input != null) {
					input.close();
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("Time:" + Long.toString(end - start));
	}
}
