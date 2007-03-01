/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference.internal.evaluators;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ruby.typeinference.internal.IContext;
import org.eclipse.dltk.ruby.typeinference.internal.ITypeEvaluator;
import org.eclipse.dltk.typeinference.AnyTypeDescriptor;
import org.eclipse.dltk.typeinference.CallDescriptor;
import org.eclipse.dltk.typeinference.IKnownTypeDescriptor;
import org.eclipse.dltk.typeinference.IMethodDescriptor;
import org.eclipse.dltk.typeinference.ITypeDescriptor;
import org.eclipse.dltk.typeinference.RecursiveCallTypeDescriptor;

public class CallTypeEvaluator implements ITypeEvaluator {

	public Class[] getRecognizedNodes() {
		return new Class[] { CallExpression.class };
	}

	public ITypeDescriptor startTraverse(ASTNode node, IContext context) {
		Assert.isTrue(node instanceof CallExpression);
		CallExpression expr = (CallExpression) node;
		Statement receiverNode = expr.getReceiver();
		ITypeDescriptor receiver;
		if (receiverNode == null)
			// XXX handle derivation
			receiver = context.getCurrentScope().getEnclosingType(); 
		else
			receiver = context.evaluateType(receiverNode);
		if (receiver instanceof IKnownTypeDescriptor) {
			IKnownTypeDescriptor receiverType = (IKnownTypeDescriptor) receiver;
			String methodName = expr.getName();
			IMethodDescriptor method = receiverType.getMethodByName(methodName);
			if (method == null) {
				System.out.println("Unknown method " + methodName + " called.");
				return AnyTypeDescriptor.INSTANCE;
			}

			// calculate argument types
			List expressions = expr.getArgs().getExpressions();
			ITypeDescriptor[] argTypes = new ITypeDescriptor[expressions.size()];
			int index = 0;
			for (Iterator iter = expressions.iterator(); iter.hasNext();) {
				Statement statement = (Statement) iter.next();
				argTypes[index++] = context.evaluateType(statement);
			}

			IMethodDescriptor enclosingMethod = context.getCurrentScope().getEnclosingMethod();
			if (enclosingMethod == method)
				return RecursiveCallTypeDescriptor.INSTANCE;
			if (enclosingMethod != null)
				method.addIncomingCall(new CallDescriptor(enclosingMethod, method, argTypes));
			return method.getReturnType();
		}
		return AnyTypeDescriptor.INSTANCE;
	}

	public ITypeDescriptor endTraverse(ASTNode node, IContext context) {
		throw new AssertionError("Never called");
	}

	public void init(IContext globalContext) {
	}

}