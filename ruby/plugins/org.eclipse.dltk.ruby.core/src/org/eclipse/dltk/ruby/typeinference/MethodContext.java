package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.evaluation.types.SimpleType;
import org.eclipse.dltk.ti.IContext;
import org.eclipse.dltk.ti.IInstanceContext;
import org.eclipse.dltk.ti.ISourceModuleContext;
import org.eclipse.dltk.ti.types.ClassType;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class MethodContext implements IContext, IArgumentsContext, IInstanceContext, ISourceModuleContext {

	private final ISourceModule sourceModule;

	private final ModuleDeclaration rootNode;

	private final String[] argNames;

	private final IEvaluatedType[] argTypes;

	private ClassType instanceType;

	public MethodContext(IContext parent, ISourceModule sourceModule, ModuleDeclaration rootNode,
			String[] argNames, IEvaluatedType[] argTypes) {
		this.sourceModule = sourceModule;
		this.rootNode = rootNode;
		this.argNames = argNames;
		this.argTypes = argTypes;
		if (parent instanceof IInstanceContext) {
			instanceType = ((IInstanceContext) parent).getInstanceType();
		}
	}

	public IEvaluatedType getArgumentType(String name) {
		for (int i = 0; i < argNames.length; i++) {
			String argName = argNames[i];
			if (name.equals(argName))
				if (i < argTypes.length)
					return argTypes[i];
				else
					return new SimpleType(SimpleType.TYPE_NONE);
		}
		return null;
	}

	public ClassType getInstanceType() {
		return instanceType;
	}

	public ModuleDeclaration getRootNode() {
		return rootNode;
	}

	public ISourceModule getSourceModule() {
		return sourceModule;
	}
	
	public String getLangNature() {
		if (sourceModule != null) {
			try {
				IDLTKLanguageToolkit languageToolkit = DLTKLanguageManager.getLanguageToolkit(sourceModule);
				if (languageToolkit != null)
					return languageToolkit.getNatureID();
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
