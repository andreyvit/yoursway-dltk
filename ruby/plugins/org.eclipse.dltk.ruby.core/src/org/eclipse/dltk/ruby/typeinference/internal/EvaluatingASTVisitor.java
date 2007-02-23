/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference.internal;

import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.typeinference.ITypeDescriptor;

public class EvaluatingASTVisitor extends ASTVisitor {
	
	private boolean TRACE = Boolean.valueOf(Platform.getDebugOption("org.eclipse.dltk.ruby.core/typeInferencing/traceEvaluationVisits")).booleanValue();

	private final IContext context;

	private final ASTNode evaluationNode;

	private ITypeDescriptor evaluatedType;

	private final EvaluatorConfiguration evaluatorConfiguration;

	public EvaluatingASTVisitor(IContext context, ASTNode evaluationNode,
			EvaluatorConfiguration evaluatorConfiguration) {
		this.context = context;
		this.evaluationNode = evaluationNode;
		this.evaluatorConfiguration = evaluatorConfiguration;
	}

	public ITypeDescriptor getEvaluatedType() {
		return evaluatedType;
	}

	public boolean visitGeneral(ASTNode node) throws Exception {
//		if (TRACE) 
			System.out.println("Start node " + node.getClass().getName());
		ITypeEvaluator evaluator = evaluatorConfiguration.getTypeEvaluator(node);
		if (evaluator == null) {
			System.out.println("Ruby type inferencing skipping node type: " + node.getClass().getName());
			return true;
		}
		ITypeDescriptor type = evaluator.startTraverse(node, context);
		if (type != null) {
			submit(node, type);
			return false;
		}
		return true;
	}

	private void submit(ASTNode node, ITypeDescriptor type) {
		if (node == evaluationNode)
			evaluatedType = type;
		else
			context.getCurrentScope().setLastCalculatedValueType(type);
	}

	public void endvisitGeneral(ASTNode node) throws Exception {
//		if (TRACE) 
			System.out.println("End node " + node.getClass().getName());
		ITypeEvaluator evaluator = evaluatorConfiguration.getTypeEvaluator(node);
		if (evaluator == null)
			return;
		ITypeDescriptor type = evaluator.endTraverse(node, context);
		if (type != null) {
			submit(node, type);
		}
	}

}