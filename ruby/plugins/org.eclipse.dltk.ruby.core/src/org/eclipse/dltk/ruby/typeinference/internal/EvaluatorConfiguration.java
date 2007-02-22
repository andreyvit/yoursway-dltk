/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.ast.ASTNode;

public class EvaluatorConfiguration {

	private Map nodeTypesToEvaluators = new HashMap();

	private Map cache = new HashMap();

	public void addTypeEvaluator(ITypeEvaluator evaluator) {
		Class[] recognizedNodes = evaluator.getRecognizedNodes();
		for (int i = 0; i < recognizedNodes.length; i++) {
			Class node = recognizedNodes[i];
			nodeTypesToEvaluators.put(node, evaluator);
		}
	}

	public ITypeEvaluator getTypeEvaluator(ASTNode node) {
		Class nodeType = node.getClass();
		ITypeEvaluator evaluator = (ITypeEvaluator) cache.get(nodeType);
		if (evaluator == null) {
			for (Class type = nodeType; type != null; type = type.getSuperclass()) {
				evaluator = (ITypeEvaluator) nodeTypesToEvaluators.get(type);
				if (evaluator != null) {
					cache.put(nodeType, evaluator);
					break;
				}
			}
		}
		return evaluator;
	}

}