package org.eclipse.dltk.internal.javascript.reference.resolvers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.internal.javascript.typeinference.AbstractCallResultReference;
import org.eclipse.dltk.internal.javascript.typeinference.HostCollection;
import org.eclipse.dltk.internal.javascript.typeinference.IReference;
import org.eclipse.dltk.internal.javascript.typeinference.VaribleDeclarationReference;
import org.eclipse.dltk.javascript.core.FunctionDeclarationReference;
import org.eclipse.dltk.javascript.core.JavaScriptNature;

public class SourceBasedResolver implements IReferenceResolver,
		IExecutableExtension {

	public boolean canResolve(ISourceModule module) {
		return true;
	}

	public Set getChilds(IResolvableReference ref) {
		if (ref instanceof AbstractCallResultReference) {
			AbstractCallResultReference cm = (AbstractCallResultReference) ref;
			String id = cm.getId();
			
			List result = searchMethods(id);
			HashSet hashSet = new HashSet();

			for (int a = 0; a < result.size(); a++) {
				FunctionDeclarationReference fr = (FunctionDeclarationReference) result
						.get(a);
				HostCollection ms = fr.getCollection();
				IReference reference = ms.getReference(cm.getResultId());
				if (reference != null) {
					hashSet.addAll(reference.getChilds(true));
				}
			}
			result = searchRefs(id);
			for (int a = 0; a < result.size(); a++) {
				VaribleDeclarationReference fr = (VaribleDeclarationReference) result
						.get(a);
				IReference reference = fr.getReference().getChild(
						cm.getResultId(), true);
				if (reference != null)
					hashSet.addAll(reference.getChilds(true));
			}
			if (hashSet.isEmpty())
				return null;
			return hashSet;
		}
		return null;
	}

	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {

	}

	protected static IDLTKLanguageToolkit toolkit;
	protected static IDLTKSearchScope scope;

	static {
		try {
			toolkit = DLTKLanguageManager
					.getLanguageToolkit(JavaScriptNature.NATURE_ID);
			scope = SearchEngine.createWorkspaceScope(toolkit);
		} catch (CoreException e) {
			throw new LinkageError();
		}
	}
	protected static int EXACT_RULE = SearchPattern.R_EXACT_MATCH
			| SearchPattern.R_CASE_SENSITIVE;

	protected List searchMethods(String name) {
		final List result = new ArrayList(2);
		return result;
	}

	protected List searchRefs(String name) {
		final List result = new ArrayList(2);
		return result;
	}

	public Set resolveGlobals(String id) {
		return new HashSet();
	}

	public void processCall(String call, String objId) {
	}

	public void init() {
	}

	public void init(ReferenceResolverContext owner) {
	}

}
