package org.eclipse.dltk.ruby.typeinference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Assignment;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.evaluation.types.AmbiguousType;
import org.eclipse.dltk.evaluation.types.IEvaluatedType;
import org.eclipse.dltk.evaluation.types.RecursionTypeCall;
import org.eclipse.dltk.evaluation.types.UnknownType;
import org.eclipse.dltk.ruby.internal.parser.Activator;
import org.eclipse.dltk.ruby.internal.parser.JRubySourceParser;

public class RubyTypeInferencingUtils {

	public static ASTNode[] getAllStaticScopes(ModuleDeclaration rootNode, final int requestedOffset) {
		final Collection scopes = new ArrayList();
		ASTVisitor visitor = new OffsetTargetedASTVisitor(requestedOffset) {

			public boolean visitInteresting(MethodDeclaration s) {
				scopes.add(s);
				return true;
			}

			public boolean visitInteresting(ModuleDeclaration s) {
				scopes.add(s);
				return true;
			}

			public boolean visitInteresting(TypeDeclaration s) {
				scopes.add(s);
				return true;
			}

			// TODO: handle Ruby blocks here
			
		};
		try {
			rootNode.traverse(visitor);
		} catch (Exception e) {
			Activator.log(e);
		}
		return (ASTNode[]) scopes.toArray(new ASTNode[scopes.size()]);
	}
	
	public static LocalVariableInfo findLocalVariable(ModuleDeclaration rootNode, final int requestedOffset, String varName) {
		ASTNode[] staticScopes = getAllStaticScopes(rootNode, requestedOffset);
		int end = staticScopes.length;
		for (int start = end - 1; start >= 0; start--) {
			ASTNode currentScope = staticScopes[start];
			if (!isRootLocalScope(currentScope))
				continue;
			ASTNode nextScope = (end < staticScopes.length ? staticScopes[end] : null);
			Assignment[] assignments = findLocalVariableAssignments(currentScope, nextScope, varName);
			if (assignments.length > 0) {
				return new LocalVariableInfo(currentScope, assignments);
			}
		}
		return null;
	}
	
	public static Assignment[] findLocalVariableAssignments(final ASTNode scope, final ASTNode nextScope, final String varName) {
		final Collection assignments = new ArrayList();
		ASTVisitor visitor = new ASTVisitor() {

			public boolean visit(Expression s) throws Exception {
				if (s instanceof Assignment) {
					Assignment assignment = (Assignment) s;
					Expression lhs = assignment.getLeft();
					if (lhs instanceof VariableReference) {
						VariableReference varRef = (VariableReference) lhs;
						if (varName.equals(varRef.getName())) {
							assignments.add(assignment);
						}
					}
				}
				return true;
			}

			public boolean visitGeneral(ASTNode node) throws Exception {
				if (node == nextScope)
					return false;
				return true;
			}

		};
		try {
			scope.traverse(visitor);
		} catch (Exception e) {
			Activator.log(e);
		}
		return (Assignment[]) assignments.toArray(new Assignment[assignments.size()]);
	}
	
	public static boolean isRootLocalScope(ASTNode node) {
		return node instanceof ModuleDeclaration || node instanceof TypeDeclaration
				|| node instanceof MethodDeclaration;
	}
	
	public static IEvaluatedType combineTypes(Collection evaluaedTypes) {
		Set types = new HashSet(evaluaedTypes);
		types.remove(null);
		if (types.size() > 1 && types.contains(RecursionTypeCall.INSTANCE))
			types.remove(RecursionTypeCall.INSTANCE);
		return combineUniqueTypes((IEvaluatedType[]) types.toArray(new IEvaluatedType[types.size()]));
	}
	
	public static IEvaluatedType combineUniqueTypes(IEvaluatedType[] types) {
		if (types.length == 0)
			return UnknownType.INSTANCE;
		if (types.length == 1)
			return types[0];
		return new AmbiguousType(types);
	}

	public static IEvaluatedType combineTypes(IEvaluatedType[] evaluaedTypes) {
		return combineTypes(Arrays.asList(evaluaedTypes));
	}
	
	public static ModuleDeclaration parseSource(ISourceModule module) {
		JRubySourceParser parser = new JRubySourceParser(null);
		try {
			return parser.parse(module.getSource());
		} catch (ModelException e) {
			Activator.log(e);
			return null;
		}
	}
	
}
