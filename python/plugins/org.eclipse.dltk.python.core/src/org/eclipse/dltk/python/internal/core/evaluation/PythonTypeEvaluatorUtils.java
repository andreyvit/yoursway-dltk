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
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ISourceParser;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.ExpressionList;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModule;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.python.core.PythonNature;
import org.eclipse.dltk.python.parser.ast.expressions.Assignment;
import org.eclipse.dltk.python.parser.ast.expressions.CallHolder;
import org.eclipse.dltk.python.parser.ast.expressions.ExtendedVariableReference;
import org.eclipse.dltk.python.parser.ast.statements.IfStatement;
import org.eclipse.dltk.python.parser.ast.statements.SwitchStatement;
import org.eclipse.dltk.python.parser.ast.statements.TryStatement;
import org.eclipse.dltk.python.parser.ast.statements.WhileStatement;


public class PythonTypeEvaluatorUtils {

	// Return all AST node childs.
	public static List/* <ASTNode> */getAllASTLevelChilds(ASTNode parent) {

		PythonASTLevelVisitor visitor = new PythonASTLevelVisitor();
		List/* <ASTNode> */nodes = new ArrayList/* <ASTNode> */(1);
		try {
			parent.traverse(visitor);
			nodes = visitor.getNodes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodes;
	}

	public static String makeASTNodeName(Map/*<ASTNode, ASTNode>*/parents,
			ASTNode node) {

		String name = null;
		ASTNode nde = node;
		while (nde != null) {
			if (nde instanceof SimpleReference) {
				if (name != null) {
					name = ((SimpleReference) nde).getName() + "." + name;
				} else {
					name = ((SimpleReference) nde).getName();
				}
			} else if (nde instanceof ExtendedVariableReference) {
				if (name != null) {
					name = ((ExtendedVariableReference) nde)
							.getStringRepresentation()
							+ "." + name;
				} else {
					name = ((ExtendedVariableReference) nde)
							.getStringRepresentation();
				}
			} else if (nde instanceof MethodDeclaration) {
				if (name != null) {
					name = ((MethodDeclaration) nde).getName() + "." + name;
				} else {
					name = ((MethodDeclaration) nde).getName();
				}
			} else if (nde instanceof TypeDeclaration) {
				if (name != null) {
					name = ((TypeDeclaration) nde).getName() + "." + name;
				} else {
					name = ((TypeDeclaration) nde).getName();
				}
			}
			if (parents.containsKey(nde)) {
				nde = (ASTNode)parents.get(nde);
			} else {
				break;
			}
		}
		return name;
	}

	public static String makePathByModule(IModule module) {

		String name = module.getElementName();
		IModelElement element = module.getParent();
		while (element != null
				&& element.getElementType() != IModelElement.SCRIPT_MODEL) {
			if (element.getElementType() == IModelElement.PROJECT_FRAGMENT) {
				name = element.getElementName() + name;
			}
			if (element.getElementType() == IModelElement.SCRIPT_FOLDER) {
				name = element.getElementName() + "/" + name;
			}
			element = element.getParent();
		}
		return name;
	}

	/**
	 * Returns ModelDeclaration for selected modelElement.
	 */
	public static ModuleDeclaration parseModuleForElement(
			IModelElement modelElement) throws Exception {

		IModule module = getElementModule(modelElement);
		if (module == null) {
			return null;
		}
		ISourceModule sourceUnit = (ISourceModule) module;

		String contents = new String(sourceUnit.getSource());

		ISourceParser sourceParser;
		try {
			sourceParser = DLTKLanguageManager.getSourceParser(PythonNature.NATURE_ID);
		} catch (CoreException e) {
			throw new RuntimeException("Failed to create python source parser",e);
		}

		ModuleDeclaration moduleDeclaration = sourceParser.parse(module.getPath().toString().toCharArray(), contents.toCharArray(), null);

		return moduleDeclaration;
	}

	public static List/*<Expression>*/ parseCallHolder(CallHolder callHolder) {
		List/*<Expression>*/ arguments = new ArrayList/*<Expression>*/();

		Expression refArgumentsList = callHolder.getArguments();
		if (refArgumentsList instanceof ExpressionList) {
			ExpressionList list = (ExpressionList) refArgumentsList;
			List/*<Expression>*/ refArguments = list.getExpressions();
			return refArguments;
		}
		return arguments;
	}

	public static IModule getElementModule(IModelElement element) {

		if (element == null) {
			return null;
		}
		if (element.getElementType() == IModelElement.SCRIPT_MODEL) {
			return null;
		}
		if (element.getElementType() == IModelElement.SOURCE_MODULE) {
			IModule module = (IModule) element;
			return module;
		}
		IModelElement parent = element.getParent();
		if (parent != null) {
			return getElementModule(parent);
		}
		return null;
	}

	/**
	 * Used to return first model like node. For example if AST tree is Module ->
	 * Class -> Method -> Assignment -> Left -> Right then to left or right, or
	 * even assignment Method are returned. And for Method or class itselt are
	 * returned.
	 * 
	 */
	public static ASTNode getModelLikeNode(Map/*<ASTNode, ASTNode>*/ parents,
			ASTNode node) {

		if (node == null) {
			return null;
		}
		// If this is assignment. Then it is field, and it can't be only in
		// Class or Module so skip methods.
		if (node instanceof Assignment) {
			ASTNode lnode = node;
			while (!(lnode instanceof TypeDeclaration)
					&& !(lnode instanceof ModuleDeclaration)) {
				if (parents.containsKey(lnode)) {
					lnode = (ASTNode)parents.get(lnode);
				} else {
					throw new RuntimeException("node not in AST.");
				}
			}
			return lnode;
		} else {
			ASTNode lnode = node;
			while (!(lnode instanceof MethodDeclaration)
					&& !(lnode instanceof TypeDeclaration)
					&& !(lnode instanceof ModuleDeclaration)) {
				if (parents.containsKey(lnode)) {
					lnode = (ASTNode)parents.get(lnode);
				} else {
					throw new RuntimeException("node not in AST.");
				}
			}
			return lnode;
		}
	}

	public static ASTNode getParentNode(Map/*<ASTNode, ASTNode>*/parents,
			ASTNode node) {

		if (node == null) {
			return null;
		}
		ASTNode lnode = node;
		while (!(lnode instanceof MethodDeclaration)
				&& !(lnode instanceof TypeDeclaration)
				&& !(lnode instanceof ModuleDeclaration)
				&& !(lnode instanceof Block) && !(lnode instanceof IfStatement)
				&& !(lnode instanceof WhileStatement)
				&& !(lnode instanceof SwitchStatement)
				&& !(lnode instanceof TryStatement)) {
			if (parents.containsKey(lnode)) {
				lnode = (ASTNode)parents.get(lnode);
			} else {
				// throw new RuntimeException("node not in AST.");
				return null;
			}
		}
		return lnode;
	}

	public static ASTNode getNodeParentBeforeModel(
			Map/*<ASTNode, ASTNode>*/ parents, ASTNode node) {

		ASTNode nodeParent = node;
		while (true) {
			if (parents.containsKey(nodeParent)) {
				ASTNode mparent = (ASTNode)parents.get(nodeParent);
				if (mparent == null) {
					break;
				}
				if (mparent instanceof ModuleDeclaration
						|| mparent instanceof MethodDeclaration
						|| mparent instanceof TypeDeclaration
						|| mparent instanceof Block
						|| mparent instanceof IfStatement
						|| mparent instanceof WhileStatement
						|| mparent instanceof SwitchStatement
						|| mparent instanceof TryStatement) {
					break;
				}
				nodeParent = mparent;
			} else {
				// throw new RuntimeException("node not in AST.");
				return null;
			}
		}
		return nodeParent;
	}

	public static String findElementPath(IModelElement element) {

		IModule module = getElementModule(element);
		if (module != null) {
			return makePathByModule(module);
		}
		return null;
	}

	/**
	 * Find all possible model declarations for selected element.
	 */
	public static PythonASTFindVisitor findElementsByModelElement(
			ModuleDeclaration module, IModelElement modelElement) {

		// At first find all possible declarations with modelElement name.
		String name = modelElement.getElementName();
		PythonASTFindVisitor visitor = new PythonASTFindVisitor(name);
		try {
			module.traverse(visitor);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return visitor;
	}
}
