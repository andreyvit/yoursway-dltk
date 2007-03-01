/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference.internal.evaluators;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ruby.typeinference.RubyTypeUtils;
import org.eclipse.dltk.ruby.typeinference.internal.IContext;
import org.eclipse.dltk.ruby.typeinference.internal.ITypeEvaluator;
import org.eclipse.dltk.typeinference.IClassLikeFragment;
import org.eclipse.dltk.typeinference.IKnownTypeDescriptor;
import org.eclipse.dltk.typeinference.IScope;
import org.eclipse.dltk.typeinference.ITypeDescriptor;

public class ClassContextTypeEvaluator implements ITypeEvaluator {

	private IKnownTypeDescriptor objectType;

	public Class[] getRecognizedNodes() {
		return new Class[] { TypeDeclaration.class };
	}

	public void init(IContext globalContext) {
		objectType = RubyTypeUtils.lookupType(globalContext, "Object");
	}

	public ITypeDescriptor startTraverse(ASTNode node, IContext context) {
		IClassLikeFragment fragment = (IClassLikeFragment) context.getNodeMapping(node);
		Assert.isNotNull(fragment);
		context.enterScope(fragment.getScope());
		return null;
	}

	public ITypeDescriptor endTraverse(ASTNode node, IContext context) {
		IScope scope = context.leaveScope();
		return scope.getLastCalculatedValueType();
	}

}