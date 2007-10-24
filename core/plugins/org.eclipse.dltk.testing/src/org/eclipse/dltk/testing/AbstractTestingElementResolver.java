package org.eclipse.dltk.testing;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceParserUtil;

public abstract class AbstractTestingElementResolver implements ITestingElementResolver {

	public AbstractTestingElementResolver() {
		super();
	}

	public ISourceRange resolveRange(IScriptProject project,
			ILaunchConfiguration config, String name, ISourceModule module,
			IModelElement element, String method) {
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
		final ASTNode node = findNode(name, decl, method);
		if (node != null) {
			return new ISourceRange() {

				public int getLength() {
					if (node instanceof Declaration) {
						Declaration decl = (Declaration) node;
						return decl.getNameEnd() - decl.getNameStart();
					}
					return node.sourceEnd() - node.sourceStart();
				}

				public int getOffset() {
					if (node instanceof Declaration) {
						return ((Declaration) node).getNameStart();
					}
					return node.sourceStart();
				}
			};
		}
		return null;
	}

	protected abstract ASTNode findNode(String name, ModuleDeclaration decl, String method);

	public IModelElement resolveElement(IScriptProject project,
			ILaunchConfiguration config, ISourceModule module, String name) {
		return module;
	}

	private ModuleDeclaration parseModule(ISourceModule module)
			throws CoreException, ModelException {
		return SourceParserUtil.getModuleDeclaration(module, null);
	}
	

}