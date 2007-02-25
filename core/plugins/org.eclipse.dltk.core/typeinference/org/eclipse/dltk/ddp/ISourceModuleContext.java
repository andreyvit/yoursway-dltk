package org.eclipse.dltk.ddp;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;

public interface ISourceModuleContext {

	public abstract ModuleDeclaration getRootNode();

	public abstract ISourceModule getSourceModule();

}
