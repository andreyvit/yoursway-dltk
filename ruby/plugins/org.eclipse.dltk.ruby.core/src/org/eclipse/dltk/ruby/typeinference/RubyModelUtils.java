package org.eclipse.dltk.ruby.typeinference;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.evaluation.types.IClassType;
import org.eclipse.dltk.ruby.core.RubyPlugin;

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
	
	public static IField[] findFields (ISourceModule modelModule, ModuleDeclaration parsedUnit, VariableReference ref) {
		IType[] types = null;
		IClassType type = RubyTypeInferencingUtils.determineSelfClass(modelModule, parsedUnit, ref.sourceStart());
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
					if (fields[i].getElementName().equals(ref.getName())) {
						resultFields.add(fields[i]);
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
			IType[] types;
			String name = superClasses[0];			
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
			}
		}
		return null;
	}

	

}
