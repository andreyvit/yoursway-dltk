/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.typeinference;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.mixin.MixinModel;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.evaluation.types.AmbiguousType;
import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.internal.core.SourceMethod;
import org.eclipse.dltk.ruby.core.RubyLanguageToolkit;
import org.eclipse.dltk.ruby.core.RubyPlugin;
import org.eclipse.dltk.ruby.core.model.FakeMethod;
import org.eclipse.dltk.ruby.internal.parser.mixin.IRubyMixinElement;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixin;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixinClass;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixinMethod;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixinModel;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixinVariable;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyObjectMixinClass;
import org.eclipse.dltk.ruby.typeinference.BuiltinMethodsDatabase.ClassMetaclass;
import org.eclipse.dltk.ruby.typeinference.BuiltinMethodsDatabase.Metaclass;
import org.eclipse.dltk.ruby.typeinference.BuiltinMethodsDatabase.MethodInfo;
import org.eclipse.dltk.ruby.typeinference.BuiltinMethodsDatabase.ModuleMetaclass;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class RubyModelUtils {

	public static IType getModelTypeByAST(TypeDeclaration astNode,
			ISourceModule sourceModule) throws ModelException {
		IType[] types = sourceModule.getAllTypes();
		int astStart = astNode.sourceStart();
		int astEnd = astNode.sourceEnd();
		int bestScore = Integer.MAX_VALUE;
		IType bestType = null;
		for (int i = 0; i < types.length; i++) {
			IType type = types[i];
			ISourceRange sourceRange = type.getSourceRange();
			int modelStart = sourceRange.getOffset();
			int modelEnd = modelStart + sourceRange.getLength();
			if (modelStart == astStart && modelEnd == astEnd)
				// XXX modelEnd == astEnd + 1 currently, so this never happens
				return type;
			if (type.getElementName().equals(astNode.getName())) {
				int diff1 = modelStart - astStart;
				int diff2 = modelEnd - astEnd;
				int score = diff1 * diff1 + diff2 * diff2;
				if (score < bestScore) {
					bestScore = score;
					bestType = type;
				}
			}
		}
		return bestType;
	}

	public static MethodDeclaration getNodeByMethod(ModuleDeclaration rootNode,
			IMethod method) throws ModelException {

		ISourceRange sourceRange = method.getSourceRange();
		final int modelStart = sourceRange.getOffset();
		final int modelEnd = modelStart + sourceRange.getLength();
		final int modelCutoffStart = modelStart - 100;
		final int modelCutoffEnd = modelEnd + 100;
		final String methodName = method.getElementName();

		final MethodDeclaration[] bestResult = new MethodDeclaration[1];

		ASTVisitor visitor = new ASTVisitor() {

			int bestScore = Integer.MAX_VALUE;

			private boolean interesting(ASTNode s) {
				if (s.sourceStart() < 0 || s.sourceEnd() < s.sourceStart())
					return true;
				if (modelCutoffEnd < s.sourceStart()
						|| modelCutoffStart >= s.sourceEnd())
					return false;
				return true;
			}

			public boolean visit(Expression s) throws Exception {
				if (!interesting(s))
					return false;
				return true;
			}

			public boolean visit(MethodDeclaration s) throws Exception {
				if (!interesting(s))
					return false;
				if (s.getName().equals(methodName)) {
					int astStart = s.sourceStart();
					int astEnd = s.sourceEnd();
					int diff1 = modelStart - astStart;
					int diff2 = modelEnd - astEnd;
					int score = diff1 * diff1 + diff2 * diff2;
					if (score < bestScore) {
						bestScore = score;
						bestResult[0] = s;
					}

				}
				return true;
			}

			public boolean visit(ModuleDeclaration s) throws Exception {
				if (!interesting(s))
					return false;
				return true;
			}			

			public boolean visit(TypeDeclaration s) throws Exception {
				if (!interesting(s))
					return false;
				return true;
			}

			public boolean endvisit(TypeDeclaration s) throws Exception {
				if (!interesting(s))
					return false;
				return false /* dummy */;
			}

			public boolean visitGeneral(ASTNode s) throws Exception {
				if (s instanceof Block)
					return true;
				if (!interesting(s))
					return false;
				return true;
			}

		};

		try {
			rootNode.traverse(visitor);
		} catch (Exception e) {
			RubyPlugin.log(e);
		}

		return bestResult[0];

	}

	public static IField[] findFields(ISourceModule modelModule,
			ModuleDeclaration parsedUnit, String prefix, int position) {
		List result = new ArrayList();

		RubyMixinModel rubyModel = RubyMixinModel.getInstance();
		String[] keys = RubyTypeInferencingUtils.getModelStaticScopesKeys(
				rubyModel.getRawModel(), parsedUnit, position);

		IRubyMixinElement innerElement = null;
		RubyMixinClass selfClass = new RubyObjectMixinClass(rubyModel, true);

		if (keys != null && keys.length > 0) {
			String inner = keys[keys.length - 1];
			if (prefix.length() > 0 && !prefix.startsWith("@")) { // locals &
				// constants
				String varkey = inner + MixinModel.SEPARATOR + prefix;
				String[] keys2 = rubyModel.getRawModel().findKeys(varkey + "*");
				for (int i = 0; i < keys2.length; i++) {
					IRubyMixinElement element = rubyModel
							.createRubyElement(keys2[i]);
					if (element instanceof RubyMixinVariable) {
						RubyMixinVariable variable = (RubyMixinVariable) element;
						result.add(variable.getSourceFields()[0]);
					}
				}
			} else {
				innerElement = rubyModel.createRubyElement(inner);
				if (innerElement instanceof RubyMixinMethod) {
					RubyMixinMethod method = (RubyMixinMethod) innerElement;
					selfClass = method.getSelfType();
					RubyMixinVariable[] fields2 = method.getVariables();
					addVariablesFrom(fields2, prefix, result);
				} else if (innerElement instanceof RubyMixinClass) {
					selfClass = (RubyMixinClass) innerElement;
				}
			}
		}

		if (selfClass != null) {
			RubyMixinVariable[] fields2 = selfClass.getFields();
			addVariablesFrom(fields2, prefix, result);
			if (selfClass.getKey().equals("Object")) {
				// variables
				try {
					IModelElement[] children = modelModule.getChildren();
					for (int i = 0; i < children.length; i++) {
						if (children[i] instanceof IField) {
							IField field = (IField) children[i];
							if (field.getElementName().startsWith(prefix))
								result.add(field);
						}
					}
				} catch (ModelException e) {
					e.printStackTrace();
				}
			}
		}

		return (IField[]) result.toArray(new IField[result.size()]);
	}
	
	
	private static List handleSpecialMethod(RubyMixinMethod method, RubyMixinClass selfKlass) {
		if (method.getKey().equals("Class%{new")) {
			RubyMixinMethod init = selfKlass.getInstanceClass()
					.getMethod("initialize");
			if (init != null) {
				IMethod[] initMethods = init.getSourceMethods();
				List result = new ArrayList ();
				for (int i = 0; i < initMethods.length; i++) {					
					try {
						String[] parameters = initMethods[i].getParameters();
						String[] parameterInitializers = initMethods[i]
						                                             .getParameterInitializers();
						int flags = initMethods[i].getFlags();
						ISourceRange sourceRange = initMethods[i].getSourceRange();
						ISourceRange nameRange = initMethods[i].getNameRange();
						IModelElement parent = initMethods[i].getParent();
						FakeMethod newMethod = new FakeMethod(
								(ModelElement) parent, "new",
								sourceRange.getOffset(), sourceRange.getLength(),
								nameRange.getOffset(), nameRange.getLength());
						newMethod.setParameters(parameters);
						newMethod.setParameterInitializers(parameterInitializers);
						newMethod.setFlags(flags);
						String receiver = "";
						if (parent instanceof IType) {
							IType type = (IType) parent;
							receiver = type.getTypeQualifiedName("::");
						}
						newMethod.setReceiver(receiver);
						result.add(newMethod);
					} catch (ModelException e) {
						e.printStackTrace();						
					}
				}
				return result;
			}
		}
		return null;
	}
	
	public static List getAllSourceMethods(RubyMixinMethod[] methods, RubyMixinClass selfKlass) {
		List result = new ArrayList ();
		for (int i = 0; i < methods.length; i++) {
			if (selfKlass != null) {
				List m = handleSpecialMethod(methods[i], selfKlass);
				if (m != null) {
					result.addAll(m);
					continue;
				}
			}
			IMethod[] sourceMethods = methods[i].getSourceMethods();			
			for (int j = 0; j < sourceMethods.length; j++) {
				if (sourceMethods[j] != null/*
						&& sourceMethods[j].getElementName()
								.startsWith(prefix)*/) {				
					result.add(sourceMethods[j]);
				}
			}			
		}
		return result;
	}

	public static IMethod[] searchClassMethods(
			org.eclipse.dltk.core.ISourceModule modelModule,
			ModuleDeclaration moduleDeclaration, IEvaluatedType type,
			String prefix) {
		List result = new ArrayList();
		if (type instanceof RubyClassType) {
			RubyClassType rubyClassType = (RubyClassType) type;
			RubyMixinClass rubyClass = RubyMixinModel.getInstance()
					.createRubyClass(rubyClassType);
			if (rubyClass != null) {
				RubyMixinMethod[] methods = rubyClass.findMethods(prefix,
						(prefix.length() > 0));
				result.addAll(getAllSourceMethods(methods, rubyClass));
			}

		} else if (type instanceof AmbiguousType) {
			AmbiguousType type2 = (AmbiguousType) type;
			IEvaluatedType[] possibleTypes = type2.getPossibleTypes();
			for (int i = 0; i < possibleTypes.length; i++) {
				IMethod[] m = searchClassMethods(modelModule,
						moduleDeclaration, possibleTypes[i], prefix);
				for (int j = 0; j < m.length; j++) {
					result.add(m[j]);
				}
			}
		}
		return (IMethod[]) result.toArray(new IMethod[result.size()]);
	}

	private static void addVariablesFrom(RubyMixinVariable[] fields2,
			String prefix, List resultList) {
		for (int i = 0; i < fields2.length; i++) {
			IField[] sourceFields = fields2[i].getSourceFields();
			if (sourceFields != null) {
				for (int j = 0; j < sourceFields.length; j++) {
					if (sourceFields[j] != null) {
						if (prefix == null
								|| sourceFields[j].getElementName().startsWith(
										prefix)) {
							resultList.add(sourceFields[j]);
							break;
						}

					}

				}
			}
		}
	}

	public static IMethod[] getVirtualMethods(VariableReference receiver,
			ModuleDeclaration parsedUnit, ISourceModule modelModule,
			String methodName) {
		IMethod[] res = null;
		// if (receiver instanceof VariableReference) {
		VariableReference ref = (VariableReference) receiver;
		String[] scopesKeys = RubyTypeInferencingUtils
				.getModelStaticScopesKeys(RubyMixinModel.getRawInstance(),
						parsedUnit, ref.sourceStart());
		if (scopesKeys != null && scopesKeys.length > 0) {
			String possibleName;
			if (scopesKeys.length == 1) {
				possibleName = ref.getName() + RubyMixin.VIRTUAL_SUFFIX;
			} else {
				String last = scopesKeys[scopesKeys.length - 1];
				possibleName = last + MixinModel.SEPARATOR + ref.getName()
						+ RubyMixin.VIRTUAL_SUFFIX;
			}
			IRubyMixinElement element = RubyMixinModel.getInstance()
					.createRubyElement(possibleName);
			if (element instanceof RubyMixinClass) {
				RubyMixinClass rubyMixinClass = (RubyMixinClass) element;
				res = RubyModelUtils.searchClassMethods(modelModule,
						parsedUnit, new RubyClassType(rubyMixinClass.getKey()),
						methodName);
			}
		}
		// }
		return res;
	}

	// public static RubyClassType getSuperType(IType type) {
	// String[] superClasses;
	// try {
	// superClasses = type.getSuperClasses();
	// } catch (ModelException e) {
	// e.printStackTrace();
	// return null;
	// }
	// if (superClasses != null && superClasses.length == 1) {
	// String name = superClasses[0];
	// IType[] types;
	// if (name.startsWith("::")) {
	// types = DLTKModelUtil.getAllTypes(type.getScriptProject(), name, "::");
	// } else {
	// String scopeFQN = type.getTypeQualifiedName("::");
	// types = DLTKModelUtil.getAllScopedTypes(type.getScriptProject(), name,
	// "::", scopeFQN);
	// }
	// if (types != null && types.length > 0) {
	// String typeQualifiedName =
	// types[0].getTypeQualifiedName("::").substring(2);
	// String[] FQN = typeQualifiedName.split("::");
	// return new RubyClassType(FQN, types, null);
	// } else {
	// FakeMethod[] fakeMethods = getFakeMethods((ModelElement) type, name);
	// if (fakeMethods != null) {
	// return new RubyClassType(new String[]{name}, null, fakeMethods);
	// }
	// }
	// }
	// FakeMethod[] fakeMethods = getFakeMethods((ModelElement) type, "Object");
	// return new RubyClassType(new String[]{"Object"}, null, fakeMethods);
	// }

	public static FakeMethod[] getFakeMethods(ModelElement parent, String klass) {
		Metaclass metaclass = BuiltinMethodsDatabase.get(klass);
		if (metaclass != null) {
			List fakeMethods = new ArrayList();
			addFakeMethods(parent, metaclass, fakeMethods);
			return (FakeMethod[]) fakeMethods
					.toArray(new FakeMethod[fakeMethods.size()]);
		}
		// XXX the following code is legacy
		String[] names = getBuiltinMethodNames(klass);
		if (names == null)
			return new FakeMethod[0];
		List methods = new ArrayList();
		for (int i = 0; i < names.length; i++) {
			FakeMethod method = new FakeMethod(parent, names[i]);
			method.setReceiver(klass);
			methods.add(method);
		}
		return (FakeMethod[]) methods.toArray(new FakeMethod[methods.size()]);
	}

	private static void addFakeMethods(ModelElement parent,
			Metaclass metaclass, List fakeMethods) {
		// process included modules first, to allow the class to override
		// some of the methods
		ModuleMetaclass[] includedModules = metaclass.getIncludedModules();
		for (int i = 0; i < includedModules.length; i++) {
			ModuleMetaclass module = includedModules[i];
			addFakeMethods(parent, module, fakeMethods);
		}
		MethodInfo[] methods = metaclass.getMethods();
		for (int i = 0; i < methods.length; i++) {
			MethodInfo info = methods[i];
			FakeMethod method = createFakeMethod(parent, metaclass, info);
			fakeMethods.add(method);
		}
		if (metaclass instanceof BuiltinMethodsDatabase.ClassMetaclass) {
			BuiltinMethodsDatabase.ClassMetaclass classMeta = (BuiltinMethodsDatabase.ClassMetaclass) metaclass;
			ClassMetaclass superClass = classMeta.getSuperClass();
			if (superClass != null)
				addFakeMethods(parent, superClass, fakeMethods);
		}
	}

	private static FakeMethod createFakeMethod(ModelElement parent,
			Metaclass metaclass, MethodInfo info) {
		FakeMethod method = new FakeMethod(parent, info.getName());
		int arity = info.getArity();
		String parameters[] = new String[0];
		if (arity > 0) {
			parameters = new String[arity];
			for (int i = 0; i < arity; i++)
				parameters[i] = "arg" + (i + 1);
		} else if (arity < 0) {
			parameters = new String[-arity];
			for (int i = 0; i < -arity - 1; i++)
				parameters[i] = "arg" + (i + 1);
			parameters[-arity - 1] = "...";
		}
		method.setParameters(parameters);
		method.setReceiver(metaclass.getName());
		return method;
	}

	public static FakeMethod[] getFakeMetaMethods(ModelElement parent,
			String klass) {
		Metaclass metaclass = BuiltinMethodsDatabase.get(klass);
		if (metaclass != null) {
			ClassMetaclass metaMetaclass = metaclass.getMetaClass();
			List fakeMethods = new ArrayList();
			addFakeMethods(parent, metaMetaclass, fakeMethods);
			return (FakeMethod[]) fakeMethods
					.toArray(new FakeMethod[fakeMethods.size()]);
		}
		String[] names = getBuiltinMetaMethodNames(klass);
		if (names == null)
			return new FakeMethod[0];
		List methods = new ArrayList();
		for (int i = 0; i < names.length; i++) {
			FakeMethod method = new FakeMethod(parent, names[i]);
			method.setReceiver(klass);
			methods.add(method);
		}
		return (FakeMethod[]) methods.toArray(new FakeMethod[methods.size()]);
	}

	public static String[] getBuiltinMethodNames(String klass) {
		if (klass.equals("Object")) {
			return BuiltinTypeMethods.objectMethods;
		} else if (klass.equals("String")) {
			return BuiltinTypeMethods.stringMethods;
		} else if (klass.equals("Fixnum")) {
			return BuiltinTypeMethods.fixnumMethods;
		} else if (klass.equals("Float")) {
			return BuiltinTypeMethods.floatMethods;
		} else if (klass.equals("Regexp")) {
			return BuiltinTypeMethods.regexpMethods;
		} else if (klass.equals("Array")) {
			return BuiltinTypeMethods.arrayMethods;
		}
		return null;
	}

	public static String[] getBuiltinMetaMethodNames(String klass) {
		if (klass.equals("Object")) {
			return BuiltinMethodsDatabase.objectMethods;
		}
		return null;
	}

	public static IType[] findTopLevelTypes(ISourceModule module,
			String namePrefix) {
		List result = new ArrayList();

		try {
			// TODO: add handling of "require"
			IModelElement[] children = module.getChildren();
			for (int i = 0; i < children.length; i++) {
				if (children[i] instanceof IType
						&& children[i].getElementName().startsWith(namePrefix))
					result.add(children[i]);
			}
		} catch (ModelException e) {
			e.printStackTrace();
		}

		return (IType[]) result.toArray(new IType[result.size()]);
	}

	public static IField[] findTopLevelFields(ISourceModule module,
			String namePrefix) {
		List result = new ArrayList();

		try {
			IModelElement[] children = module.getChildren();
			for (int i = 0; i < children.length; i++) {
				if (children[i] instanceof IField
						&& children[i].getElementName().startsWith(namePrefix))
					result.add(children[i]);
			}
		} catch (ModelException e) {
			e.printStackTrace();
		}

		return (IField[]) result.toArray(new IField[result.size()]);
	}

	public static IMethod[] findTopLevelMethods(IDLTKProject project,
			String namePattern) {
		final List result = new ArrayList();
		SearchRequestor requestor = new SearchRequestor() {

			public void acceptSearchMatch(SearchMatch match)
					throws CoreException {
				Object element = match.getElement();
				if (element instanceof SourceMethod) {
					SourceMethod meth = (SourceMethod) element;
					if (meth.getParent() instanceof ISourceModule) {
						result.add(meth);
					}
				}
			}

		};
		SearchPattern pattern = SearchPattern.createPattern(namePattern,
				IDLTKSearchConstants.METHOD, IDLTKSearchConstants.DECLARATIONS,
				SearchPattern.R_PATTERN_MATCH | SearchPattern.R_EXACT_MATCH);
		IDLTKSearchScope scope;
		if (project != null)
			scope = SearchEngine
					.createSearchScope(new IModelElement[] { project });
		else
			scope = SearchEngine.createWorkspaceScope(RubyLanguageToolkit
					.getDefault());
		try {
			SearchEngine engine = new SearchEngine();
			engine.search(pattern, new SearchParticipant[] { SearchEngine
					.getDefaultSearchParticipant() }, scope, requestor, null);
		} catch (CoreException e) {
			if (DLTKCore.DEBUG)
				e.printStackTrace();
		}

		return (IMethod[]) result.toArray(new IMethod[result.size()]);
	}

	/**
	 * Should return mixin-key of superclass
	 * 
	 * @param type
	 * @return
	 */
	public static String evaluateSuperClass(IType type) {
		String superclass = null;
		String[] superClasses;
		try {
			superClasses = type.getSuperClasses();
		} catch (ModelException e) {
			e.printStackTrace();
			return null;
		}
		if (superClasses != null && superClasses.length > 0) {
			superclass = superClasses[0];
			if (superclass.startsWith("::"))
				superclass = superclass.substring(2);
			superclass = superclass.replaceAll("::", "{");
		}

		// TODO: add appropriate evaluation here

		return superclass;
	}

}
