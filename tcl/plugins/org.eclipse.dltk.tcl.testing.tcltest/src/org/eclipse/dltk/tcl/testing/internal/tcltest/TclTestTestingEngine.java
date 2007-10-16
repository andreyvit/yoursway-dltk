package org.eclipse.dltk.tcl.testing.internal.tcltest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.testing.ITclTestingEngine;
import org.eclipse.dltk.testing.ITestingProcessor;
import org.eclipse.dltk.utils.DeployHelper;

public class TclTestTestingEngine implements ITclTestingEngine {
	public TclTestTestingEngine() {
	}

	public String getId() {
		return Activator.PLUGIN_ID + ".testingEngine";
	}

	public String getName() {
		return "Tcl Test";
	}

	public ITestingProcessor getProcessor(ILaunch launch) {
		return new TcltestOutputProcessor(launch);
	}

	public boolean isValidModule(ISourceModule module) {
		// We need to find test or tcltest::test calls in module.
		ModuleDeclaration moduleDeclaration = SourceParserUtil
				.getModuleDeclaration(module, null);
		ASTNode[] findTests = findTests(moduleDeclaration);
		if(findTests.length > 0 ) {
			return true;
		}
		return false;
	}

	private ASTNode[] findTests(ModuleDeclaration decl) {
		final List ndes = new ArrayList();
		try {
			decl.traverse(new ASTVisitor() {
				public boolean visitGeneral(ASTNode node) throws Exception {
					if (node instanceof TclStatement
							&& ((TclStatement) node).getCount() > 2) {
						TclStatement st = (TclStatement) node;
						Expression cmd = st.getAt(0);
						if (cmd instanceof SimpleReference) {
							String cmdName = ((SimpleReference) cmd).getName();
							if (cmdName.startsWith("::")) {
								cmdName = cmdName.substring(2);
							}
							if ("test".equals(cmdName)
									|| "tcltest::test".equals(cmdName)) {

								// List findLevelsTo = findLevelsTo(decl, node);
								Expression name = st.getAt(1);
								if (name instanceof SimpleReference) {
									String nameValue = ((SimpleReference) name)
											.getName();
									ndes.add(node);
								}
							}
						}
					}
					return true;
				}
			});
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return (ASTNode[]) ndes.toArray(new ASTNode[ndes.size()]);
	}

	public void correctLaunchConfiguration(InterpreterConfig config) {
		// We need to extract tcl source module and correct config.
		try {
			IPath runner = DeployHelper.deploy(Activator.getDefault(),
					"scripts/tcltestEngine.tcl");
			IPath scriptFilePath = config.getScriptFilePath();
			config.setScriptFile(runner);
			config.addScriptArg(scriptFilePath.toOSString(), 0);
		} catch (IOException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}
}
