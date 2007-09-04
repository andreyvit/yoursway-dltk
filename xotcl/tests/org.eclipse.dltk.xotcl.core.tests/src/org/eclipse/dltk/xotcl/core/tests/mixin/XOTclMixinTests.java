package org.eclipse.dltk.xotcl.core.tests.mixin;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.core.mixin.IMixinRequestor.ElementInfo;
import org.eclipse.dltk.tcl.core.ITclSourceParser;
import org.eclipse.dltk.xotcl.internal.core.parser.XOTclSourceParser;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.XOTclMixinBuildVisitor;

public class XOTclMixinTests extends TestCase {
	class TestMixinRequestorCollector implements IMixinRequestor {
		private List infos = new ArrayList();

		public void reportElement(ElementInfo info) {
			this.infos.add(info);
		}

		public ElementInfo[] getInfo() {
			return (ElementInfo[]) infos.toArray(new ElementInfo[this.infos
					.size()]);
		}
	}

	public void testParseUtil001() throws Throwable {
		String content = 
			"namespace eval c  {\n" +
			"}\n" +
			"namespace eval a {\n" +
			"	namespace eval b {\n" +
			"		proc c::d { } {\n" +
			"		}\n" +
			"	}\n" +
			"}\n";
		String expected = 
			"c : null\n" +
			"a : null\n" +
			"a{b : null\n" +
			"c{d : null\n";
		checkMixin(content, expected);
	}
	public void testParseUtil002() throws Throwable {
		String content = 
			"package require XOTcl\n" +
			"namespace import ::xotcl::*\n" +
			"Class MyModule\n" +
			"# Documentation 1\n" +
			"MyModule instproc myInstanceProc { vars } {\n" +
			"	puts \"MyInstanceProc\"\n" +
			"}\n" +
			"MyModule create myModuleInstance\n" +
			"myModuleInstance myInstanceProc\n" ;
		String expected = 
			"MyModule : null\n" +
			"MyModule{myInstanceProc : null\n" +
			"MyModule{myModuleInstance : null\n" ;
		checkMixin(content, expected);
	}
	

	private void checkMixin(String content, String expected) throws Exception {
		ModuleDeclaration module = this.parser( content );
		TestMixinRequestorCollector collector = new TestMixinRequestorCollector();
		XOTclMixinBuildVisitor visitor = new XOTclMixinBuildVisitor(module, null,false, collector);
		module.traverse(visitor);
		ElementInfo[] info = collector.getInfo();
		String actual = infoToString(info);
		assertEquals(expected, actual);
	}

	private String infoToString(ElementInfo[] info) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < info.length; i++) {
			buffer.append(info[i] + "\n");
		}
		return buffer.toString();
	}

	private ASTNode[] findNodeByName(ModuleDeclaration module, final String name)
			throws Exception {
		final List results = new ArrayList();
		module.traverse(new ASTVisitor() {
			public boolean endvisit(TypeDeclaration s) throws Exception {
				if (s.getName().equals(name)) {
					return results.add(s);
				}
				return super.endvisit(s);
			}

			public boolean visit(MethodDeclaration s) throws Exception {
				if (s.getName().equals(name)) {
					return results.add(s);
				}
				return super.visit(s);
			}
		});
		return (ASTNode[]) results.toArray(new ASTNode[results.size()]);
	}

	private ModuleDeclaration parser(String content) {
		ITclSourceParser parser = new XOTclSourceParser();
		ModuleDeclaration module = parser.parse("file".toCharArray(), content
				.toCharArray(), null);
		assertNotNull(module);
		return module;
	}
}
