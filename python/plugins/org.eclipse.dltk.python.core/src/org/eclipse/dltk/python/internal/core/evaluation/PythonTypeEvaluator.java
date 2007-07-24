/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.core.evaluation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.evaluation.types.ModelModuleType;
import org.eclipse.dltk.evaluation.types.UnknownType;
import org.eclipse.dltk.python.parser.ast.expressions.Assignment;
import org.eclipse.dltk.ti.types.IEvaluatedType;


public class PythonTypeEvaluator {
	/**
	 * Evaluate last from module import. Evaluate last declaration of element
	 * for correct type detection. Because of that model holds only declaration
	 * of element.
	 * 
	 * @param modelElement
	 * @return
	 */
	public static IEvaluatedType evaluateLast(IModelElement modelElement) {
		return evaluate(modelElement);
	}

	/**
	 * Evaluate type from model.
	 * 
	 * @param modelElement
	 * @return
	 */
	public static IEvaluatedType evaluate(IModelElement modelElement) {

		if (isAppropriateElement(modelElement)) {
			try {

				IModule module = PythonTypeEvaluatorUtils
						.getElementModule(modelElement);

				ModuleDeclaration moduleForElement = PythonTypeEvaluatorUtils
						.parseModuleForElement(modelElement);

				PythonASTFindVisitor findVisitor = PythonTypeEvaluatorUtils
						.findElementsByModelElement(moduleForElement,
								modelElement);

				Map/* < ASTNode, ASTNode > */parents = findVisitor
						.getParents();

				// List< ASTNode > allNodes = findVisitor.getNodes( );

				List/* < ASTNode > */neededDeclarations = filterASTNodes(
						parents, modelElement, findVisitor);
				int elementKind = modelElement.getElementType();

				// IScriptProject project = modelElement.getScriptProject();

				PythonASTTypeEvaluator astEvaluator = new PythonASTTypeEvaluator(
						module, moduleForElement, parents);

				// Only list of possible needed elements.
				if (elementKind == IModelElement.METHOD) {
					// Method should be one. If correct source code.
					int count = 0;
					IEvaluatedType type = null;
					Iterator i = neededDeclarations.iterator();
					while (i.hasNext()) {
						ASTNode node = (ASTNode) i.next();
						if (count > 0) {
							break;
						}
						type = astEvaluator.evaluateASTNode(node, null);
						count++;
					}
					if (count > 1) {
						// TODO: Add error handling here.
					}
					if (type != null) {
						return type;
					}
					return UnknownType.INSTANCE;
				} else if (elementKind == IModelElement.FIELD) {
					// TODO: Add multiple values handling here.
					Iterator i = neededDeclarations.iterator();
					while (i.hasNext()) {
						ASTNode node = (ASTNode) i.next();
						return astEvaluator.evaluateASTNode(node, null);
					}
				}

			} catch (Exception exception) {
				exception.printStackTrace();
				return UnknownType.INSTANCE;
			}
		} else if (modelElement.getElementType() == IModelElement.SOURCE_MODULE) {
			return new ModelModuleType((IModule) modelElement);
		}

		return UnknownType.INSTANCE;
	}

	public static boolean isAppropriateElement(IModelElement modelElement) {

		if (modelElement.getElementType() == IModelElement.FIELD
				|| modelElement.getElementType() == IModelElement.METHOD) {
			return true;
		}
		return false;
	}

	/**
	 * Clear statistics.
	 * 
	 */
	public static void clearStats() {

	}

	private static List/* <ASTNode> */filterASTNodes(
			Map/* <ASTNode, ASTNode> */parents, IModelElement modelElement,
			PythonASTFindVisitor visitor) throws Exception {

		List/* <ASTNode> */elementDeclarations = visitor.getNodes();
		// Needed for this node declarations.
		List/* <ASTNode> */neededDeclarations = new ArrayList/* <ASTNode> */();
		int elementKind = modelElement.getElementType();

		// If this is field then gets parent.
		IModelElement parentElement = modelElement;
		if (elementKind == IModelElement.FIELD) {
			parentElement = modelElement.getParent();
		}
		Iterator i = elementDeclarations.iterator();
		while (i.hasNext()) {
			ASTNode node = (ASTNode) i.next();
			try {
				if (elementKind == IModelElement.METHOD) {
					if (node instanceof MethodDeclaration) {
						if (filterByModel(parents, parentElement,
								PythonTypeEvaluatorUtils.getModelLikeNode(
										parents, node))) {
							neededDeclarations.add(node);
						}

					}
				} else if (elementKind == IModelElement.FIELD) {
					if (node instanceof Assignment) {
						if (filterByModel(parents, parentElement,
								PythonTypeEvaluatorUtils.getModelLikeNode(
										parents, node))) {
							neededDeclarations.add(node);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return neededDeclarations;
	}

	/**
	 * Filters ast element by Model.
	 * 
	 * @param modelElement
	 * @param node
	 * @return
	 */
	public static boolean filterByModel(Map/* <ASTNode, ASTNode> */parents,
			IModelElement modelElement, ASTNode node) throws Exception {

		if (node == null || modelElement == null) {
			return false;
		}
		// Go to parents until module.
		if (modelElement.getElementType() == IModelElement.METHOD) {
			IMethod iMethod = (IMethod) modelElement;
			// Find needed ast method declaration and check to top for equal
			// name and
			// types.
			if (node instanceof MethodDeclaration) {
				MethodDeclaration method = (MethodDeclaration) node;
				if (!method.getName().equals(iMethod.getElementName())) {
					return false;
				}
				if (method.getArguments().size() != iMethod.getParameters().length) {
					return false;
				}
				if (parents.containsKey(node)) {
					return filterByModel(parents, modelElement.getParent(),
							PythonTypeEvaluatorUtils.getModelLikeNode(parents,
									(ASTNode) parents.get(node)));
				}
			}
		} else if (modelElement.getElementType() == IModelElement.TYPE) {
			IType iClass = (IType) modelElement;
			// Find needed ast method declaration and check to top for equal
			// name and
			// types.
			if (node instanceof TypeDeclaration) {
				TypeDeclaration method = (TypeDeclaration) node;
				if (!method.getName().equals(iClass.getElementName())) {
					return false;
				}
				if (parents.containsKey(node)) {
					return filterByModel(parents, modelElement.getParent(),
							PythonTypeEvaluatorUtils.getModelLikeNode(parents,
									(ASTNode) parents.get(node)));
				}
			}
		} else if (modelElement.getElementType() == IModelElement.SOURCE_MODULE) {
			IModule iModule = (IModule) modelElement;
			if (node instanceof ModuleDeclaration) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Evaluate call using argument types. globals must be last from selected
	 * module.
	 * 
	 * @param modelElement
	 * @param callHolder
	 * @return TODO: Add correct implementation here
	 */
	public static IEvaluatedType evaluateCall(IModelElement modelElement,
			List/* <IEvaluatedType> */callHolder) {
		return evaluate(modelElement);
	}
}
