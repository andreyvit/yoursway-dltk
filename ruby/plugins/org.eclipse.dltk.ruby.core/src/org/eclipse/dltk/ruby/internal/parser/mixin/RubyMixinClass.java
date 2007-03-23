package org.eclipse.dltk.ruby.internal.parser.mixin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.MixinModel;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.internal.core.search.matching.MethodPattern;
import org.eclipse.dltk.ruby.core.RubyLanguageToolkit;
import org.eclipse.dltk.ruby.typeinference.RubyModelUtils;

public class RubyMixinClass implements IRubyMixinElement {

	private final String key;
	private final RubyMixinModel model;

	public RubyMixinClass(RubyMixinModel model, String key) {
		super();
		this.model = model;
		this.key = key;
	}

	public String getKey() {
		return key;
	}
	
	public RubyMixinClass getMetaclass () {
		if (isMeta())
			return this;
		String metakey = key.substring(0, key.indexOf(RubyMixin.INSTANCE_SUFFIX));
		IRubyMixinElement r = model.createRubyElement(metakey);
		if (r instanceof RubyMixinClass)
			return (RubyMixinClass) r;
		return null;
	}
	
	public boolean isMeta () {
		return (!key.endsWith(RubyMixin.INSTANCE_SUFFIX))&&(!key.endsWith(RubyMixin.VIRTUAL_SUFFIX));
	}
	
	public String getName () {
		String name = key.substring(key.lastIndexOf(MixinModel.SEPARATOR));
		int pos;
		if ((pos = name.indexOf(RubyMixin.INSTANCE_SUFFIX)) != -1)
			name = name.substring(0, pos);
		return name;
	}
	
	public IType[] getSourceTypes () {
		List result = new ArrayList ();
		IMixinElement mixinElement = model.getRawModel().get(key);
		Object[] allObjects = mixinElement.getAllObjects();
		for (int i = 0; i < allObjects.length; i++) {
			if (allObjects[i] instanceof IType) {
				result.add(allObjects[i]);
			}
		}
		return (IType[]) result.toArray(new IType[result.size()]);
	}
	
	public RubyMixinClass getSuperclass () {
		IMixinElement mixinElement = model.getRawModel().get(key);
		Object[] allObjects = mixinElement.getAllObjects();
		if (allObjects == null || allObjects.length == 0)
			return null;
		IType type = (IType) allObjects[0];
		if (type != null) { 
			String key = RubyModelUtils.evaluateSuperClass (type);
			if (key != null) {
				IRubyMixinElement rubyElement = model.createRubyElement(key);
				return (RubyMixinClass) rubyElement;
			}
		}
		return null;
	}
	
	public RubyMixinMethod[] getMethods () {
		final List result = new ArrayList ();
		if (key.equals("Object")) {
			// search top-level methods
			SearchRequestor requestor = new SearchRequestor() {

				public void acceptSearchMatch(SearchMatch match) throws CoreException {
					if (match.getElement() instanceof IMethod) {
						IMethod method = (IMethod) match.getElement();
						if (method.getParent() instanceof ISourceModule) {
							String possibleKey = method.getElementName();
							IRubyMixinElement element = RubyMixinModel.getInstance().createRubyElement(possibleKey);
							if (element instanceof RubyMixinMethod)
								result.add(element);
						}
					}
				}
				
			};
			IDLTKSearchScope scope = SearchEngine.createWorkspaceScope(RubyLanguageToolkit.getDefault());

//			try {
//				SearchEngine engine = new SearchEngine();
//				SearchPattern pattern = SearchPattern.createPattern("*",
//						IDLTKSearchConstants.METHOD,
//						IDLTKSearchConstants.DECLARATIONS,
//						SearchPattern.R_PATTERN_MATCH | SearchPattern.R_EXACT_MATCH);
//				engine.search(pattern, new SearchParticipant[] { SearchEngine
//						.getDefaultSearchParticipant() }, scope, requestor, null);
//			} catch (CoreException e) {
//				if (DLTKCore.DEBUG)
//					e.printStackTrace();
//			}
//			
		}		
		IMixinElement mixinElement = model.getRawModel().get(key);
		IMixinElement[] children = mixinElement.getChildren();
		for (int i = 0; i < children.length; i++) {
			IRubyMixinElement element = model.createRubyElement(children[i]);
			if (element instanceof RubyMixinMethod)
				result.add(element);
		}
		RubyMixinClass superclass = getSuperclass();
		if (superclass != null) {
			RubyMixinMethod[] methods = superclass.getMethods();
			result.addAll(Arrays.asList(methods));
		}		
		return (RubyMixinMethod[]) result.toArray(new RubyMixinMethod[result.size()]);
	}
	
	public RubyMixinMethod getMethod (String name) {
		String possibleKey = key + MixinModel.SEPARATOR + name;
		IMixinElement mixinElement = model.getRawModel().get(possibleKey);
		if (mixinElement != null) {
			return (RubyMixinMethod) model.createRubyElement(mixinElement);
		}
		return null;
	}
	
	public RubyMixinClass[] getClasses () {
		List result = new ArrayList ();
		IMixinElement mixinElement = model.getRawModel().get(key);
		IMixinElement[] children = mixinElement.getChildren();
		for (int i = 0; i < children.length; i++) {
			IRubyMixinElement element = model.createRubyElement(children[i]);
			if (element instanceof RubyMixinClass)
				result.add(element);
		}
		return (RubyMixinClass[]) result.toArray(new RubyMixinClass[result.size()]);
	}
	
	public RubyMixinVariable[] getFields () {
		List result = new ArrayList ();
		IMixinElement mixinElement = model.getRawModel().get(key);
		IMixinElement[] children = mixinElement.getChildren();
		for (int i = 0; i < children.length; i++) {
			IRubyMixinElement element = model.createRubyElement(children[i]);
			if (element instanceof RubyMixinVariable)
				result.add(element);
		}
		return (RubyMixinVariable[]) result.toArray(new RubyMixinVariable[result.size()]);
	}
	
}
