package org.eclipse.dltk.tcl.testing.internal.tcltest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementVisitor;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceModuleInfoCache;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.ISourceModuleInfoCache.ISourceModuleInfo;
import org.eclipse.dltk.internal.core.ModelManager;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclASTUtil;
import org.eclipse.dltk.tcl.internal.parser.TclSourceElementParser;
import org.eclipse.dltk.testing.ITestingElementResolver;

public class TcltestMemberResolver implements ITestingElementResolver {

	public ISourceRange resolveRange(IScriptProject project,
			ILaunchConfiguration config, String name, ISourceModule module,
			IModelElement element) {
		ModuleDeclaration decl;
		try {
			decl = parseModule(module);
		} catch (ModelException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
			return null;
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
			return null;
		}
		final ASTNode node = findNode(module, name, decl);
		if( node != null ) {
			return new ISourceRange() {

				public int getLength() {
					return node.sourceEnd() - node.sourceStart();
				}

				public int getOffset() {
					return node.sourceStart();
				}
			};
		}
		return null;
	}

	public static ASTNode getScopeParent(ModuleDeclaration module, ASTNode node) {
		List levels = findLevelsTo(module, node);
		for (int i = 0; i < levels.size(); i++) {
			ASTNode nde = (ASTNode) levels.get(levels.size() - i - 1);
			if (nde instanceof TypeDeclaration
					|| nde instanceof MethodDeclaration
					|| nde instanceof ModuleDeclaration && nde instanceof Block) {
				return nde;
			}
		}
		return module;
	}

	public static String getElementFQN(List nodes, ModuleDeclaration module) {
		StringBuffer prefix = new StringBuffer();
		for (Iterator iterator = nodes.iterator(); iterator.hasNext();) {
			ASTNode ns = (ASTNode) iterator.next();
			String name = null;
			if (ns instanceof ModuleDeclaration) {
				name = "";
				// module = (ModuleDeclaration) ns;
			} else if (ns instanceof TypeDeclaration) {
				name = ((TypeDeclaration) ns).getName();
			} else if (ns instanceof MethodDeclaration) {
				name = ((MethodDeclaration) ns).getName();
			}
			if (name != null) {
				if (name.startsWith("::")) {
					prefix.delete(0, prefix.length());
					name = name.substring(2);
				}
				if (name.length() > 0) {
					prefix.append(name + "::");
				}
			}
		}

		String result = prefix.toString();
		if (result.endsWith("::")) {
			return result.substring(0, result.length() - 2);
		}
		return result;
	}

	public static String getFQNFromModelElement(IModelElement member,
			String separator) {
		String buffer = new String();
		IModelElement m = member;
		while (m.getElementType() != IModelElement.SOURCE_MODULE) {
			buffer = separator + m.getElementName() + buffer;
			m = m.getParent();
		}
		return buffer;
	}

	public IModelElement resolveElement(IScriptProject project,
			ILaunchConfiguration config, ISourceModule module, String name) {
		try {
			ModuleDeclaration decl = parseModule(module);
			ASTNode nde = findNode(module, name, decl);
			final IModelElement result[] = new IModelElement[] { null };
			if (nde != null) {
				ASTNode parent = getScopeParent(decl, nde);
				if (parent != null) {
					List l = findLevelsTo(decl, parent);
					final String fqn = getElementFQN(l, decl);
					module.accept(new IModelElementVisitor() {
						public boolean visit(IModelElement element) {
							String efqn = getFQNFromModelElement(element, "::");
							if (efqn.equals(fqn)) {
								result[0] = element;
								return false;
							}
							return true;
						}
					});
				}
			}
			return result[0];
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}

	private ModuleDeclaration parseModule(ISourceModule module)
			throws CoreException, ModelException {

		ISourceModuleInfoCache sourceModuleInfoCache = ModelManager
				.getModelManager().getSourceModuleInfoCache();
		// sourceModuleInfoCache.remove(this);
		ISourceModuleInfo mifo = sourceModuleInfoCache.get(module);

		ModuleDeclaration decl = TclSourceElementParser.parseModule(mifo,
				module.getSource().toCharArray(), null, module.getPath()
						.toOSString().toCharArray());
		return decl;
	}

	public static List findLevelsTo(ModuleDeclaration module,
			ASTNode astNodeParent) {
		List elements = new ArrayList();
		if (module != null || astNodeParent instanceof ModuleDeclaration) {
			if (module == null) {
				module = (ModuleDeclaration) astNodeParent;
			}
			elements.add(module);
			findElementsTo(TclASTUtil.getStatements(module), astNodeParent,
					elements);
		}
		return elements;
	}

	public static void findElementsTo(List statements, ASTNode node,
			List elements) {
		if (statements == null) {
			return;
		}
		Iterator i = statements.iterator();
		while (i.hasNext()) {
			ASTNode n = (ASTNode) i.next();
			if (n.equals(node)) {
				elements.add(n);
				return;
			}
			if (n.sourceStart() <= node.sourceStart()
					&& node.sourceEnd() <= n.sourceEnd()) {
				elements.add(n);
				findElementsTo(TclASTUtil.getStatements(n), node, elements);
				return;
			}
		}
	}

	private ASTNode findNode(ISourceModule module, final String testName,
			ModuleDeclaration decl) {
		final ASTNode[] nde = new ASTNode[] { null };
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
									if (testName.equals(nameValue)) {
										nde[0] = st;
									}
								}
							}
						}
					}
					return true;
				}
			});
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nde[0];
	}
}
