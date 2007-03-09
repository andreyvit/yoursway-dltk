/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference.internal.evaluators;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ruby.typeinference.RubyTypeUtils;
import org.eclipse.dltk.ruby.typeinference.internal.IContext;
import org.eclipse.dltk.ruby.typeinference.internal.ITypeEvaluator;
import org.eclipse.dltk.typeinference.IKnownTypeDescriptor;
import org.eclipse.dltk.typeinference.ITypeDescriptor;
import org.eclipse.dltk.typeinference.MethodScope;
import org.eclipse.dltk.typeinference.UserMethodDescriptor;

public class MethodContextTypeEvaluator implements ITypeEvaluator {

	private IKnownTypeDescriptor nilClass;

	public Class[] getRecognizedNodes() {
		return new Class[] { MethodDeclaration.class };
	}

	public void init(IContext globalContext) {
		nilClass = RubyTypeUtils.lookupType(globalContext, "NilClass");
	}

	public ITypeDescriptor startTraverse(ASTNode node, IContext context) {
		UserMethodDescriptor method = (UserMethodDescriptor) context.getNodeMapping(node);
		Assert.isNotNull(method);
		context.enterScope(method.getScope());
		method.reset();
		return null;
	}

	public ITypeDescriptor endTraverse(ASTNode node, IContext context) {
		UserMethodDescriptor method = (UserMethodDescriptor) context.getNodeMapping(node);
		Assert.isNotNull(method);
		MethodScope scope = (MethodScope) context.leaveScope();
		ITypeDescriptor lastCalculatedValueType = scope.getLastCalculatedValueType();
		if (lastCalculatedValueType != null)
			scope.addReturnedType(lastCalculatedValueType);
		else
			scope.addReturnedType(nilClass);
		ITypeDescriptor newReturnType = context.getModelMaster()
				.makeTypeDescriptorFromListOfTypeDescriptors(scope.getReturnedTypes());
		method.setReturnType(newReturnType);
		return nilClass;
	}
}