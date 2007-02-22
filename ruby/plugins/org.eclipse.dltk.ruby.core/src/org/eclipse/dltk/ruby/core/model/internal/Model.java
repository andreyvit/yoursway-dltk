package org.eclipse.dltk.ruby.core.model.internal;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.DLTKProject;
import org.eclipse.dltk.ruby.core.model.IElement;
import org.eclipse.dltk.ruby.core.model.IElementCriteria;
import org.eclipse.dltk.ruby.core.model.IElementKind;
import org.eclipse.dltk.ruby.core.model.IModel;
import org.eclipse.dltk.ruby.internal.parser.Activator;
import org.eclipse.dltk.ruby.internal.parser.JRubySourceParser;
import org.eclipse.dltk.typeinference.ASTCaching;
import org.eclipse.dltk.typeinference.INodeElementInternal;

public class Model implements IModel {
	
	private final IDLTKProject project;
	
	private final Map sourceModulesToASTs = new WeakHashMap();

	public Model(IDLTKProject project) {
		this.project = project;
	}

	public ModuleDeclaration getASTNode(ISourceModule sourceModule, ASTCaching caching) {
		ModuleDeclaration result = null;
		SoftReference astNode = (SoftReference) sourceModulesToASTs.get(sourceModule);
		if (astNode != null && caching != ASTCaching.REPARSE)
			result = (ModuleDeclaration) astNode.get();
		if (result == null && caching != ASTCaching.CACHED_ONLY) {
			JRubySourceParser parser = new JRubySourceParser(null);
			try {
				result = parser.parse(sourceModule.getSource());
			} catch (ModelException e) {
				Activator.log(e);
				result = null;
			}
			if (result != null) {
				astNode = new SoftReference(result);
				sourceModulesToASTs.put(sourceModule, astNode);
			}
		}
		return result;
	}

	public ISourceModule[] search(String name) {
		Collection sourceModules = new ArrayList();
		try {
			IModelElement[] children = project.getChildren();
			addModules(sourceModules, children);
		} catch (ModelException e) {
			e.printStackTrace();
		}
		return (ISourceModule[]) sourceModules.toArray(new ISourceModule[sourceModules.size()]);
	}

	private void addModules(Collection sourceModules, IModelElement[] children) throws ModelException {
		for (int i = 0; i < children.length; i++) {
			IModelElement element = children[i];
			if (element instanceof ISourceModule)
				sourceModules.add(element);
			else if (element instanceof IParent)
				addModules(sourceModules, ((IParent) element).getChildren());
		}
	}

	public IElement[] findChildren(IElementCriteria criteria, String name, IProgressMonitor pm) {
		if (criteria == IElementCriteria.CLASS_OR_MIXIN) {
			
		}
		return null;
	}

	public IElement getAncestor(IElementCriteria criteria) {
		return null;
	}

	public IElementKind getElementKind() {
		return IElementKind.MODEL;
	}
	
}
