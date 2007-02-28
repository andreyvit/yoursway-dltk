package org.eclipse.dltk.ruby.typeinference;

import java.util.ArrayList;
import java.util.LinkedList;
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
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.env.ISourceMethod;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.evaluation.types.IClassType;
import org.eclipse.dltk.evaluation.types.IEvaluatedType;
import org.eclipse.dltk.internal.core.ExternalSourceModule;
import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.internal.core.SourceMethod;
import org.eclipse.dltk.ruby.core.RubyPlugin;
import org.eclipse.dltk.ruby.core.model.FakeMethod;

public class RubyModelUtils {
	
	public static IType getModelTypeByAST(TypeDeclaration astNode, ISourceModule sourceModule) throws ModelException {
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
	
	public static MethodDeclaration getNodeByMethod(ModuleDeclaration rootNode, IMethod method) throws ModelException {

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
				if (modelCutoffEnd < s.sourceStart() || modelCutoffStart >= s.sourceEnd())
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

			public boolean visit(Statement s) throws Exception {
				// XXX workaround for a bug in block offset calculation
				if (s instanceof Block)
					return true;
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
	
	public static IField[] findFields (ISourceModule modelModule, ModuleDeclaration parsedUnit, String prefix, int position) {
		IType[] types = null;
		IClassType type = RubyTypeInferencingUtils.determineSelfClass(modelModule, parsedUnit, position);
		if (type instanceof RubyMetaClassType) {
			type = (IClassType) ((RubyMetaClassType)type).getInstanceType();
		}
		if (type instanceof RubyClassType) {
			RubyClassType rubyClassType = RubyTypeInferencingUtils.resolveMethods(modelModule.getScriptProject(), (RubyClassType) type);
			types = rubyClassType.getTypeDeclarations();
		}		
		if (types == null)
			return new IField[0];
		List resultFields = new ArrayList ();
		for (int i = 0; i < types.length; i++) {
			IField[] fields;
			try {
				fields = types[i].getFields();
				for (int j = 0; j < fields.length; j++) {
					if (fields[j].getElementName().startsWith(prefix)) {
						resultFields.add(fields[j]);
					}
				}
			} catch (ModelException e1) {					
				e1.printStackTrace();
			}				
		}
		return (IField[]) resultFields.toArray(new IField[resultFields.size()]);
	}	 
	
	public static RubyClassType getSuperType(IType type) {
		String[] superClasses;
		try {
			superClasses = type.getSuperClasses();
		} catch (ModelException e) {	
			e.printStackTrace();
			return null;
		}
		if (superClasses != null && superClasses.length == 1) {
			String name = superClasses[0];		
			IType[] types;
			if (name.startsWith("::")) {
				types = DLTKModelUtil.getAllTypes(type.getScriptProject(), name, "::");
			} else {
				String scopeFQN = type.getTypeQualifiedName("::");
				types = DLTKModelUtil.getAllScopedTypes(type.getScriptProject(), name, "::", scopeFQN); 
			}
			if (types != null && types.length > 0) {
				String typeQualifiedName = types[0].getTypeQualifiedName("::").substring(2);
				String[] FQN = typeQualifiedName.split("::");				
				return new RubyClassType(FQN, types, null);
			} else {
				FakeMethod[] fakeMethods = getFakeMethods((ModelElement) type, name);
				if (fakeMethods != null) {
					return new RubyClassType(new String[]{name}, null, fakeMethods);
				}
			}
		}
		FakeMethod[] fakeMethods = getFakeMethods((ModelElement) type, "Object");
		return new RubyClassType(new String[]{"Object"}, null, fakeMethods);
	}

	public static FakeMethod[] getFakeMethods(ModelElement parent, String klass) {
		String[] names = getBuiltinMethodNames(klass);
		if (names == null) 
			return new FakeMethod[0];
		List methods = new ArrayList ();
		for (int i = 0; i < names.length; i++) {
			FakeMethod method = new FakeMethod(parent, names[i]);
			method.setReceiver(klass);
			methods.add(method);
		}
		return (FakeMethod[]) methods.toArray(new FakeMethod[methods.size()]);
	} 
	
	
	public static FakeMethod[] getFakeMetaMethods(ModelElement parent, String klass) {
		String[] names = getBuiltinMetaMethodNames(klass);
		if (names == null) 
			return new FakeMethod[0];
		List methods = new ArrayList ();
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
		} else  if (klass.equals("Fixnum")) {
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
			return BuiltinMetaTypeMethods.objectMethods;
		} 
		return null;
	}
	
	public static IMethod[] findTopLevelMethods (ISourceModule module, String namePrefix) {
		List result = new ArrayList();
		
		try {
			//TODO: add handling of "require"
			IModelElement[] children = module.getChildren();
			for (int i = 0; i < children.length; i++) {
				if (children[i] instanceof IMethod && children[i].getElementName().startsWith(namePrefix))
					result.add(children[i]);
			}
		} catch (ModelException e) {
			e.printStackTrace();
		}

		return (IMethod[]) result.toArray(new IMethod[result.size()]);
	}
	
	public static IType[] findTopLevelTypes (ISourceModule module, String namePrefix) {
		List result = new ArrayList();
		
		try {
			//TODO: add handling of "require"
			IModelElement[] children = module.getChildren();
			for (int i = 0; i < children.length; i++) {
				if (children[i] instanceof IType && children[i].getElementName().startsWith(namePrefix))
					result.add(children[i]);
			}
		} catch (ModelException e) {
			e.printStackTrace();
		}

		return (IType[]) result.toArray(new IType[result.size()]);
	}
	
	public static IMethod[] _bad_findTopLevelMethods (IDLTKProject project, String namePattern) {
		final List result = new ArrayList ();
		SearchRequestor requestor = new SearchRequestor() {

			public void acceptSearchMatch(SearchMatch match) throws CoreException {
				Object element = match.getElement();
				if (element instanceof SourceMethod) {
					SourceMethod meth = (SourceMethod)element;
					if (meth.getParent() instanceof ISourceModule) {
						result.add(meth);
					}
				}
			}
			
		};
		SearchPattern pattern = SearchPattern.createPattern(namePattern, IDLTKSearchConstants.METHOD, 
				IDLTKSearchConstants.DECLARATIONS, SearchPattern.R_PATTERN_MATCH);
		IDLTKSearchScope scope = SearchEngine.createSearchScope(new IModelElement[] {project});		
		try {
			SearchEngine engine = new SearchEngine();
			engine.search(pattern, new SearchParticipant[] { SearchEngine
						.getDefaultSearchParticipant() }, scope, 
						requestor, null);
		} catch (CoreException e) {			
			if (DLTKCore.DEBUG)
				e.printStackTrace();
		}
		
		return (IMethod[]) result.toArray(new IMethod[result.size()]);
	}
	

}
