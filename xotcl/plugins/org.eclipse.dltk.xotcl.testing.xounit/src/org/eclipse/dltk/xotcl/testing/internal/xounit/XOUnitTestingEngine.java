package org.eclipse.dltk.xotcl.testing.internal.xounit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.testing.ITclTestingEngine;
import org.eclipse.dltk.testing.ITestingProcessor;
import org.eclipse.dltk.utils.DeployHelper;
import org.eclipse.dltk.xotcl.core.IXOTclModifiers;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodDeclaration;

public class XOUnitTestingEngine implements ITclTestingEngine {
	public XOUnitTestingEngine() {
	}

	public String getId() {
		return Activator.PLUGIN_ID + ".testingEngine";
	}

	public String getName() {
		return "XOUnit";
	}

	public ITestingProcessor getProcessor(ILaunch launch) {
		return new XOUnitOutputProcessor(launch);
	}

	public boolean isValidModule(ISourceModule module) {
		// We need to find test or tcltest::test calls in module.
		ModuleDeclaration moduleDeclaration = SourceParserUtil
				.getModuleDeclaration(module, null);
		TypeDeclaration[] findTests = findTests(moduleDeclaration);
		if (findTests.length > 0) {
			return true;
		}
		return false;
	}

	public static TypeDeclaration[] findTests(final ModuleDeclaration decl) {
		final List ndes = new ArrayList();
		if (decl != null) {
			try {
				decl.traverse(new ASTVisitor() {
					public boolean visitGeneral(ASTNode node) throws Exception {
						if (node instanceof TypeDeclaration
								&& (((TypeDeclaration) node).getModifiers() & IXOTclModifiers.AccXOTcl) != 0) {
							TypeDeclaration typeDeclaration = (TypeDeclaration) node;
							
							String fqn = TclParseUtil.getElementFQN(node, "::", decl);
							if( !fqn.endsWith("::test::" + typeDeclaration.getName())) {
								return true;
							}
							List superClassNames = typeDeclaration
									.getSuperClassNames();
							boolean passTestCaseSuper = false;
							for (Iterator iterator = superClassNames.iterator(); iterator
									.hasNext();) {
								String name = (String) iterator.next();
								if (name.endsWith("TestCase")) {
									passTestCaseSuper = true;
									break;
								}
							}
							if (!passTestCaseSuper) {
								return true;
							}
							// lets check what selected class
							// contains tests methods.
							MethodDeclaration[] methods = typeDeclaration
									.getMethods();
							for (int i = 0; i < methods.length; ++i) {
								MethodDeclaration childNode = methods[i];
								if (childNode instanceof XOTclMethodDeclaration) {
									XOTclMethodDeclaration method = (XOTclMethodDeclaration) childNode;
									if (method.getName().startsWith("test")) {
										ndes.add(typeDeclaration);
										break;
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
		}
		return (TypeDeclaration[]) ndes
				.toArray(new TypeDeclaration[ndes.size()]);
	}

	public void correctLaunchConfiguration(InterpreterConfig config,
			ILaunchConfiguration configuration) {
		// We need to extract tcl source module and correct config.
		try {
			IPath runner = DeployHelper.deploy(Activator.getDefault(),
					"scripts/xounitTestingEngine.tcl");
			IPath scriptFilePath = config.getScriptFilePath();
			config.setScriptFile(runner);
			config.addScriptArg(scriptFilePath.toOSString(), 0);

			String projectName = AbstractScriptLaunchConfigurationDelegate
					.getScriptProjectName(configuration);
			String mainScriptName = AbstractScriptLaunchConfigurationDelegate
					.getMainScriptName(configuration);
			IProject project = ResourcesPlugin.getWorkspace().getRoot()
					.getProject(projectName);

			IFile script = project.getFile(mainScriptName);

			ISourceModule module = (ISourceModule) DLTKCore.create(script);

			ModuleDeclaration moduleDeclaration = SourceParserUtil
					.getModuleDeclaration(module, null);
			TypeDeclaration[] findTests = findTests(moduleDeclaration);
			String namespace = "";
			String tests = "";
			for (int i = 0; i < findTests.length; i++) {

				ASTNode parent = TclParseUtil.getPrevParent(moduleDeclaration,
						findTests[i]);
				// lets find namespace
				while (parent != null && parent instanceof TypeDeclaration) {
					if ((((TypeDeclaration) parent).getModifiers() & IXOTclModifiers.AccXOTcl) == 0) {
						break;
					}
					parent = TclParseUtil.getPrevParent(moduleDeclaration,
							parent);
				}
				if (parent != null && parent instanceof TypeDeclaration) {
					String elementFQN = TclParseUtil.getElementFQN(parent,
							"::", moduleDeclaration);
					namespace = elementFQN.substring(0, elementFQN.length()
							- "::test".length());
					config.addEnvVar("XOUNIT_TEST_NAMESPACE", namespace);
				}
				tests += findTests[i].getName()
						+ ((i == findTests.length - 1) ? "" : " ");
				// config.addEnvVar("XOUNIT_TEST_NAME", declName);
			}
			config.addEnvVar("XOUNIT_TEST_NAMES", tests);

		} catch (IOException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	public static TypeDeclaration[] getTests(TypeDeclaration typeDeclaration) {
		return typeDeclaration.getTypes();
	}

	public static MethodDeclaration[] getTestCases(
			TypeDeclaration typeDeclaration) {
		List testCases = new ArrayList();
		MethodDeclaration[] methods = typeDeclaration.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().startsWith("test")
					&& ((methods[i].getModifiers() & IXOTclModifiers.AccXOTcl) != 0)) {
				testCases.add(methods[i]);
			}
		}
		return (MethodDeclaration[]) testCases
				.toArray(new MethodDeclaration[testCases.size()]);
	}
}
