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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.NumericLiteral;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModule;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.evaluation.types.ClassInstanceType;
import org.eclipse.dltk.evaluation.types.FunctionType;
import org.eclipse.dltk.evaluation.types.IClassType;
import org.eclipse.dltk.evaluation.types.ModelClassType;
import org.eclipse.dltk.evaluation.types.ModelFunctionType;
import org.eclipse.dltk.evaluation.types.ModelModuleType;
import org.eclipse.dltk.evaluation.types.MultiTypeType;
import org.eclipse.dltk.evaluation.types.OldClassType;
import org.eclipse.dltk.evaluation.types.SimpleType;
import org.eclipse.dltk.evaluation.types.UnknownType;
import org.eclipse.dltk.python.internal.core.evaluation.types.ImportedCallType;
import org.eclipse.dltk.python.internal.core.evaluation.types.ImportedType;
import org.eclipse.dltk.python.parser.ast.PythonImportFromStatement;
import org.eclipse.dltk.python.parser.ast.PythonImportStatement;
import org.eclipse.dltk.python.parser.ast.expressions.Assignment;
import org.eclipse.dltk.python.parser.ast.expressions.BinaryExpression;
import org.eclipse.dltk.python.parser.ast.expressions.CallHolder;
import org.eclipse.dltk.python.parser.ast.expressions.ExtendedVariableReference;
import org.eclipse.dltk.python.parser.ast.expressions.PythonDictExpression;
import org.eclipse.dltk.python.parser.ast.expressions.PythonImportAsExpression;
import org.eclipse.dltk.python.parser.ast.expressions.PythonImportExpression;
import org.eclipse.dltk.python.parser.ast.expressions.PythonListExpression;
import org.eclipse.dltk.python.parser.ast.expressions.PythonTestListExpression;
import org.eclipse.dltk.python.parser.ast.expressions.PythonTupleExpression;
import org.eclipse.dltk.python.parser.ast.statements.ReturnStatement;
import org.eclipse.dltk.ti.types.IEvaluatedType;
import org.eclipse.dltk.ti.types.RecursionTypeCall;


public class PythonASTTypeEvaluator {
	private static final String SELF_CONSTANT = "self";

	private ModuleDeclaration fModule = null;

	private Map fParents = null;

	private IModule fModelModule = null;

	private List fEvaluationList = new ArrayList();

	private Map fLocalIndex = null;

	/*
	 * way to remove recursion.
	 */

	public PythonASTTypeEvaluator(IModule modelModule,
			ModuleDeclaration module, Map/*<ASTNode, ASTNode>*/ parents) {

		this.fModule = module;
		this.fParents = parents;
		this.fModelModule = modelModule;

		this.fLocalIndex = makeLocalIndex(this.fModelModule);
	}

	// Statistic information of unknown detections.

	public static long totalSimpleReferenceDetections = 0;

	public static long unknownSimpleReferenceBecauseOfArgument = 0;

	public static long unknownSimpleReferenceNotInAST = 0;

	public static long unknownSimpleReferenceNotFoundAtEnd = 0;

	public static long unknownReturnBecauseOfArgument = 0;

	public static long unknownSimpleReferenceEmptyParent = 0;

	public static long unknownSimpleReferenceNotFoundAtEndBuiltin = 0;

	public static long unknownBinaryExpressionCount = 0;

	public static long unknownBinaryExpressionUnDetected = 0;

	public static long unknownBinaryExpressionNotSimpleTypes = 0;

	public static long unknownExtendedCount = 0;

	public static long unknownExtendedUnDetectedIndex = 0;

	/**
	 * Used to evaluate binaty expressiont in AST.
	 * 
	 * @param node
	 * @return
	 */
	public IEvaluatedType evaluateASTBinaryExpression(ASTNode node) {

		BinaryExpression expression = (BinaryExpression) node;

		IEvaluatedType leftType = this.evaluateASTNode(expression.getLeft(),
				null);
		IEvaluatedType rightType = this.evaluateASTNode(expression.getRight(),
				null);

		unknownBinaryExpressionCount += 1;

		// TODO: Add correct other types binary expression handling here. May be
		// operator calls' or other stuff.
		if (leftType instanceof UnknownType || rightType instanceof UnknownType) {
			return UnknownType.INSTANCE;
		}

		if (!(leftType instanceof SimpleType && rightType instanceof SimpleType)) {
			unknownBinaryExpressionNotSimpleTypes += 1;
			return UnknownType.INSTANCE;
		}
		int left = ((SimpleType) leftType).getType();
		int right = ((SimpleType) rightType).getType();

		int kind = expression.getKind();

		IEvaluatedType type = PythonBinaryExpressionOperations.makeType(left,
				kind, right);

		if (type instanceof UnknownType) {
			unknownBinaryExpressionUnDetected += 1;
		}
		return type;
		// binding.
	}

	/**
	 * Used to evaluate type of any AST node.
	 * 
	 * @param module
	 * @param parents
	 * @param node
	 * @return
	 */
	public IEvaluatedType evaluateASTNode(ASTNode node, ASTNode scope) {

		if (this.fEvaluationList.contains(node)) {
			// CorePrinter printer = new CorePrinter( System.out );
			// printer.formatPrintLn( "Recursion on field:" );
			// node.printNode( printer );
			// printer.flush( );
			return RecursionTypeCall.INSTANCE;
			// throw new RuntimeException("Recursion detected");
		}
		this.fEvaluationList.add(node);
		if (node == null) {
			return UnknownType.INSTANCE;
		}
		if (node instanceof Assignment) {
			Assignment assign = (Assignment) node;
			return evaluateASTNode(assign.getRight(), scope);
		} else if (node instanceof MethodDeclaration) {
			// return evaluateFunctionReturnValue( node, scope );
			return new FunctionType(this.fModule, (MethodDeclaration) node);
		} else if (node instanceof BinaryExpression) {
			return evaluateASTBinaryExpression(node);
		} else if (node instanceof NumericLiteral) {
			return new SimpleType(SimpleType.TYPE_NUMBER);
		} else if (node instanceof StringLiteral) {
			return new SimpleType(SimpleType.TYPE_STRING);
		} else if (node instanceof PythonTupleExpression) {
			return new SimpleType(SimpleType.TYPE_TUPLE);
		} else if (node instanceof PythonListExpression) {
			return new SimpleType(SimpleType.TYPE_LIST);
		} else if (node instanceof PythonDictExpression) {
			return new SimpleType(SimpleType.TYPE_DICT);
		} else if (node instanceof SimpleReference) { // Simple reference
			return evaluateSimpleReferenceType((SimpleReference) node, scope);
		} else if (node instanceof ExtendedVariableReference) {
			return evaluateExtendedReferenceType(
					(ExtendedVariableReference) node, node);
		} else if (node instanceof TypeDeclaration) {
			return new OldClassType(this.fModule, (TypeDeclaration) node);
		}
		return UnknownType.INSTANCE;
	}

	/**
	 * Evaluate call node. This may be only be functions or lambda expressions.
	 * Or call what return functions or lambda expressions.
	 */
	public IEvaluatedType evaluateCallASTNode(ASTNode node,
			CallHolder callHolder, boolean isMethod, ASTNode scope) {

		if (node == null) {
			return UnknownType.INSTANCE;
		} else if (node instanceof SimpleReference) { // Simple reference
			IEvaluatedType type = evaluateSimpleReferenceType(
					(SimpleReference) node, scope);
			if (type instanceof FunctionType) {
				ASTNode parent = (ASTNode)this.fParents.get(((FunctionType) type)
						.getFunction());
				if (isMethod && parent instanceof TypeDeclaration) {
					return type;
				} else if (!isMethod && !(parent instanceof TypeDeclaration)) {
					return type;
				} else {
					// Not approppriate type.
					return UnknownType.INSTANCE;
				}
			} else if (type instanceof OldClassType) {
				return type;
			}
			// TODO: Add class methods here to check.
		} else if (node instanceof ExtendedVariableReference) {
			return evaluateExtendedReferenceType(
					(ExtendedVariableReference) node, scope);
		}
		return UnknownType.INSTANCE;
	}

	private IEvaluatedType evaluateExtendedReferenceType(
			ExtendedVariableReference reference, ASTNode scope) {

		unknownExtendedCount += 1;
		// TODO: Add correct multi extended variables handling here.
		// Tonay only simple variables are handled.
		List/*<Expression>*/ expressions = reference.getExpressions();
		IEvaluatedType type = null;
		Expression expr = null;
		int index = 0;
		while (index < expressions.size()) {
			expr = (Expression)expressions.get(index);
			// This is method call.
			if (reference.isCall(index)) {
				CallHolder callHolder = (CallHolder) expressions.get(index + 1);
				if (type == null) { // This is first
					type = evaluateFunctionCall(expr, callHolder, scope);
				}
				// Not first
				else {
					if (type instanceof IClassType
							&& expr instanceof SimpleReference) {
						type = evaluateMethodCall(type, (SimpleReference) expr,
								callHolder, scope);
					}
				}
				if (type == null) {
					return UnknownType.INSTANCE;
				}
				// Need to evaluate in the selected class or variable.
				index += 2;
			} else if (reference.isDot(index) || reference.isLast(index)) {
				if (type == null) { // first
					// lets firstly check for dotted import names and then check
					// for simple name.
					type = checkDottedImports(expressions, expr, scope);
					if (type == null) {
						type = evaluateIdentifier(expr, scope);
					}

					if (type instanceof ImportedType) {
						type = ((ImportedType) type).getType();
					}
				} else { // second and other.
					if (type instanceof ModelModuleType) {
						type = evaluateModuleSubIdentifier(
								((ModelModuleType) type).getModule(), expr);
					} else if (type instanceof ClassInstanceType) {
						type = evaluateASTClassSubIdentifier(
								((ClassInstanceType) type).getTypeDeclaration(),
								expr, false);
					} else if (type instanceof OldClassType) {
						type = evaluateASTClassSubIdentifier(((OldClassType) type)
								.getTypeDeclaration(), expr, true);
					} else if (type instanceof ModelClassType) {
						/*
						 * Model class evaluation.
						 */
						type = evaluateModelClassSubIdentifier(
								(ModelClassType) type, expr);
					}
					if (type instanceof ImportedType) {
						type = ((ImportedType) type).getType();
					}
				}
				if (type == null) {
					return UnknownType.INSTANCE;
				} else if (type instanceof ModelModuleType) {
					ModelModuleType modelModuleType = (ModelModuleType) type;
					index += modelModuleType.getStepCount() - 1;
				}
			} else if (reference.isIndex(index)) {
				unknownExtendedUnDetectedIndex += 1;
				return UnknownType.INSTANCE;
			}

			index++;
		}
		return type;
	}

	private IEvaluatedType evaluateASTClassSubIdentifier(
			TypeDeclaration typeDeclaration, Expression expr,
			boolean bStaticsOnly) {

		if (expr instanceof SimpleReference) {

			SimpleReference reference = (SimpleReference) expr;
			SimpleReference ref = (SimpleReference) expr;
			PythonASTFindVisitor visitor = new PythonASTFindVisitor(ref
					.getName(), bStaticsOnly);
			try {
				typeDeclaration.traverse(visitor);
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
			List/*<ASTNode>*/ nodes = visitor.getNodes();
			IEvaluatedType type = this.checkListForReference(reference
					.getName(), nodes, typeDeclaration);
			if (type != null) {
				return type;
			}

			IEvaluatedType imported = checkReferenceImports(
					reference.getName(), nodes);
			if (imported != null) {
				return imported;
			}

			IEvaluatedType btype = evaluateReferenceInBaseClasses(
					typeDeclaration, (SimpleReference) expr, bStaticsOnly);
			if (btype != null) {
				return btype;
			}
		}
		return null;
	}

	private IEvaluatedType evaluateReferenceInBaseClasses(
			TypeDeclaration typeDeclaration, SimpleReference expr,
			boolean staticOnly) {

		List/*<String>*/ baseClasses = typeDeclaration.getSuperClassNames();
		Iterator i = baseClasses.iterator();
		while( i.hasNext() ) {
			String className = (String)i.next();
			IEvaluatedType type = evaluateName(className, typeDeclaration);
			if (type instanceof ImportedType) {
				type = ((ImportedType) type).getType();
			}
			if (type instanceof OldClassType) {
				OldClassType ctype = (OldClassType) type;
				IEvaluatedType subIdentifier = this
						.evaluateASTClassSubIdentifier(ctype
								.getTypeDeclaration(), expr, staticOnly);
				if (subIdentifier != null) {
					return subIdentifier;
				}
			} else if (type instanceof ModelClassType) {
				ModelClassType ctype = (ModelClassType) type;
				IEvaluatedType subIdentifier = this
						.evaluateModelClassSubIdentifier(ctype, expr);
				if (subIdentifier != null) {
					return subIdentifier;
				}
			}
		}
		return null;
	}

	/**
	 * Check for
	 * 
	 * @param className
	 * @param scope
	 * @return
	 */
	private IEvaluatedType evaluateName(String name, ASTNode scope) {

		VariableReference ref = new VariableReference(-2, -3, name);
		this.fParents.put(ref, scope);
		IEvaluatedType type = this.evaluateSimpleReferenceType(ref, null);
		this.fParents.remove(ref);
		return type;
	}

	private IEvaluatedType evaluateModelClassSubIdentifier(
			ModelClassType modelType, Expression expr) {

		if (expr instanceof SimpleReference) {
			SimpleReference ref = (SimpleReference) expr;
			IType classElement = modelType.getTypeDeclaration();

			if (classElement != null) {
				IModelElement[] elements = null;
				try {
					elements = classElement.getChildren();
				} catch (ModelException ex) {
					ex.printStackTrace();
					return null;
				}
				for (int i = 0; i < elements.length; ++i ) {
					IModelElement element = elements[i];
					if (element instanceof IField) {
						if (((IField) element).getElementName().equals(
								ref.getName())) {
							IEvaluatedType type = PythonTypeEvaluator
									.evaluate(element);
							if (type != null) {
								return type;
							}
						} else if (element instanceof IMethod) {
							if (((IMethod) element).getElementName().equals(
									ref.getName())) {
								return new ModelFunctionType((IMethod) element);
							}
						}
					}
				}
				// check for base classes here.
				try {
					String[] superClassNames = classElement.getSuperClasses();
					for ( int i = 0; i < superClassNames.length; ++i ) {
						String superClass = superClassNames[i];
						IModelElement baseElement = this
								.findBaseClassForElement(classElement,
										superClass);
						if (baseElement instanceof IType) {
							IEvaluatedType type = evaluateModelClassSubIdentifier(
									new ModelClassType((IType) baseElement),
									expr);
							if (type != null) {
								return type;
							}
						} else if (baseElement instanceof IModule) {
							IEvaluatedType type = evaluateModuleSubIdentifier(
									(IModule) baseElement, expr);
							if (type != null) {
								return type;
							}
						}
					}
				} catch (ModelException ex) {
					ex.printStackTrace();
					return UnknownType.INSTANCE;
				}
			}
		}

		return null;
	}

	private IModelElement findBaseClassForElement(IType classElement,
			String baseClassName) {

		// TODO:Need to be reimplemented on new model.
		// IModelElement parent = classElement.getParent( );
		// Collection< IImportStatement > imports = null;
		// if( parent instanceof IType ) {
		// IType classElement0 = ( IType )parent;
		// try {
		// imports = classElement0.getImports( );
		// }
		// catch( ModelException ex ) {
		// ex.printStackTrace( );
		// return null;
		// }
		// }
		// else if( parent instanceof IMethod ) {
		// IMethod methodElement = ( IMethod )parent;
		// try {
		// imports = methodElement.getImports( );
		// }
		// catch( ModelException ex ) {
		// ex.printStackTrace( );
		// return null;
		// }
		// }
		// else if( parent instanceof IModule ) {
		// IModule moduleElement = ( IModule )parent;
		// try {
		// imports = moduleElement.getImports( );
		// }
		// catch( ModelException ex ) {
		// ex.printStackTrace( );
		// return null;
		// }
		// }
		// // lest filter all imports below our element.
		// List< IImportStatement > neededImports = new ArrayList<
		// IImportStatement >( );
		//
		// PositionInformation elementPosition = classElement.getPosition( );
		//
		// if( elementPosition != null ) {
		// for( IImportStatement statement : imports ) {
		// PositionInformation position = statement.getPosition( );
		// if( position != null ) {
		// if( position.isLess( elementPosition ) ) {
		// neededImports.add( statement );
		// }
		// }
		// }
		// }
		// // we can't check anything then position is null.
		// for( IImportStatement statement : neededImports ) {
		// IModelElement sElement = null;
		// try {
		// sElement = statement.isImported( baseClassName );
		// }
		// catch( ModelException ex ) {
		// ex.printStackTrace( );
		// continue;
		// }
		// if( sElement != null ) {
		// return sElement;
		// }
		// }
		return null;
	}

	private IEvaluatedType evaluateModuleSubIdentifier(IModule module,
			Expression expr) {

		if (expr instanceof SimpleReference) {
			IModelElement[] childrens = null;
			try {
				childrens = module.getChildren();
			} catch (ModelException ex) {
				ex.printStackTrace();
			}
			// TODO: Add order corrections here.
			if (childrens != null) {
				for( int i = 0; i < childrens.length; ++i ) {
					IModelElement element  = childrens[i];
					if (element instanceof IField) {
						if (((IField) element).getElementName().equals(
								((SimpleReference) expr).getName())) {
							return PythonTypeEvaluator.evaluate(element);
						}
					} else if (element instanceof IMethod) {
						if (((IMethod) element).getElementName().equals(
								((SimpleReference) expr).getName())) {
							return new ModelFunctionType((IMethod) element);
						}
					} else if (element instanceof IType) {
						if (((IType) element).getElementName().equals(
								((SimpleReference) expr).getName())) {
							return new ModelClassType((IType) element);
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Returns last possible dotted name module.
	 * 
	 * @param expressions
	 * @param expr
	 * @param scope
	 * @return
	 */
	private IEvaluatedType checkDottedImports(List/*<Expression>*/ expressions,
			Expression expr, ASTNode scope) {

		/*
		 * lets check for possible element in module like a.b.c and has import
		 * such as a.b.c
		 */
		// check for imports with dots.
		String pName = "";
		boolean firstI = true;
		int index = 1;
		IEvaluatedType lastType = null;
		Iterator i = expressions.iterator();
		while( i.hasNext() ) {
			Expression exp = (Expression)i.next();
			if (exp instanceof SimpleReference) {
				if (firstI) {
					pName += ((SimpleReference) exp).getName();
					firstI = false;
					continue;
				} else {
					pName += "." + ((SimpleReference) exp).getName();
				}
				index += 1;
				IEvaluatedType type = this.evaluateName(pName, scope);
				if (type != null) {
					if (type instanceof ModelModuleType) {
						lastType = new ModelModuleType(((ModelModuleType) type)
								.getModule(), index);
					} else if (type instanceof UnknownType) {
						continue;
					}
				}
			}
		}
		return lastType;
	}

	private IEvaluatedType evaluateIdentifier(Expression expr, ASTNode scope) {

		IEvaluatedType ctype = evaluateASTNode(expr, scope);
		if (ctype instanceof ModelClassType) {
			return ctype;
		} else if (ctype instanceof OldClassType) {
			return ctype;
		} else if (ctype instanceof ClassInstanceType) {
			return ctype;
		} else if (ctype instanceof FunctionType) {
			return ctype;
		} else if (ctype instanceof ModelFunctionType) {
			return ctype;
		} else if (ctype instanceof ModelModuleType) {
			return ctype;
		}
		return null;
	}

	private IEvaluatedType evaluateMethodCall(IEvaluatedType type,
			SimpleReference reference, CallHolder callHolder, ASTNode scope) {

		if (type instanceof OldClassType) {

			// TODO: Implement static call
			OldClassType classType = (OldClassType) type;
			IEvaluatedType rtype = this.evaluateASTClassSubIdentifier(classType
					.getTypeDeclaration(), reference, true);
			if (rtype instanceof ImportedType) {
				rtype = ((ImportedType) rtype).getType();
			}
			if (rtype instanceof FunctionType) {
				IEvaluatedType ftype = evaluateMethodReturnValue(
						((FunctionType) rtype).getFunction(), callHolder, scope);
				if (ftype != null) {
					return ftype;
				}
			}
			// TODO: Add imported function for base function check.
			return UnknownType.INSTANCE;
		} else if (type instanceof ClassInstanceType) {

			// TODO: Evaluate non static call.
			IEvaluatedType rtype = this.evaluateASTClassSubIdentifier(
					((ClassInstanceType) type).getTypeDeclaration(), reference,
					false);
			if (rtype instanceof FunctionType) {
				IEvaluatedType ftype = evaluateMethodReturnValue(
						((FunctionType) rtype).getFunction(), callHolder, scope);
				if (ftype != null) {
					return ftype;
				}
			}
			// TODO: Add imported function for base function check.

			return UnknownType.INSTANCE;
		} else if (type instanceof ModelClassType) {
			IType classElement = ((ModelClassType) type).getTypeDeclaration();
			if (classElement != null) {
				// if( this.fClassIndex != null ) {
				// List< IModelElement > elements =
				// this.fClassIndex.getElements( reference.getName( ) );
				//
				// for( IModelElement element : elements ) {
				// if( element.getParent( ).equals( classElement ) ) {
				// if( element instanceof IField ) {
				// return TypeEvaluator.evaluate( element );
				// }
				// else if( element instanceof IMethod ) {
				//
				// /*
				// * TODO: Add appropriate arguments here,
				// */
				// return TypeEvaluator.evaluateCall( element, null );
				// }
				// }
				// }
				// }
			}
		}
		return null;
	}

	private IEvaluatedType evaluateFunctionCall(Expression expr,
			CallHolder callHolder, ASTNode scope) {

		IEvaluatedType ctype = evaluateCallASTNode(expr, callHolder, false,
				scope);
		if (ctype instanceof OldClassType) {
			return new ClassInstanceType(this.fModule, ((OldClassType) ctype)
					.getTypeDeclaration());
		} else if (ctype instanceof FunctionType) {
			return evaluateFunctionReturnValue(((FunctionType) ctype)
					.getFunction(), callHolder, scope);
		} else if (ctype instanceof ImportedCallType) {
			ImportedCallType icType = (ImportedCallType) ctype;
			IModelElement modelElement = icType.getElement();
			if (modelElement != null) {
				if (modelElement instanceof IType) {
					/*
					 * this is class.
					 */
					return new ModelClassType((IType) modelElement);
				} else if (modelElement instanceof IMethod) {
					/*
					 * TODO: Add argumens type evaluation here before of call
					 * evaluation for correct type evaluation of method
					 * declaration.
					 */
					return PythonTypeEvaluator.evaluateCall(modelElement, null);
				}
			}
		}
		return null;
	}

	private List/*<ASTNode>*/ getUpperNodes(ASTNode parent, ASTNode nodeParent) {

		List/*<ASTNode>*/ nodes = PythonTypeEvaluatorUtils
				.getAllASTLevelChilds(parent);

		// Here must be all childs of selected node.

		if (nodes.contains(nodeParent)) {
			// We are in first context. So remove current and
			// all above.
			List/*<ASTNode>*/ nnodes = new ArrayList/*<ASTNode>*/();
			Iterator i = nodes.iterator();
			while( i.hasNext() ) {
				ASTNode nnde = (ASTNode)i.next();
				if (nnde.equals(nodeParent)) {
					break;
				} else {
					nnodes.add(nnde);
				}
			}
			nodes = nnodes;
		}
		// lets find declaration of this reference from end.
		Collections.reverse(nodes);
		return nodes;
	}

	private List/*<ReturnStatement>*/ findReturns(ASTNode node) {

		List/*<ASTNode>*/ nodes = PythonTypeEvaluatorUtils
				.getAllASTLevelChilds(node);
		// Filter return statements.
		List/*<ReturnStatement>*/ returns = new ArrayList/*<ReturnStatement>*/();
		if (nodes == null) {
			return returns;
		}
		Iterator i = nodes.iterator();
		while( i.hasNext() ) {
			ASTNode nde = (ASTNode)i.next();
			if (nde instanceof ReturnStatement) {
				returns.add(nde);
			}
		}
		return returns;
	}

	private IEvaluatedType makeMultiType(List/*<IEvaluatedType>*/ types) {

		int size = types.size();
		if (size == 1) {
			return (IEvaluatedType)types.get(0);
		} else if (size > 1) {
			MultiTypeType multi = new MultiTypeType();
			Iterator i = types.iterator();
			while( i.hasNext()) {
				IEvaluatedType type = (IEvaluatedType)i.next();
				multi.addType(type);
			}
			if (multi.size() == 1) {
				return multi.get(0);
			}
			return multi;
		}
		return new SimpleType(SimpleType.TYPE_NONE);
	}

	/**
	 * Used to evaluate method return value from correct ASTNode.
	 */
	private IEvaluatedType evaluateFunctionReturnValue(ASTNode node,
			ASTNode scope) {

		List/*<ReturnStatement>*/ returns = findReturns(node);
		if (returns == null) {
			return new SimpleType(SimpleType.TYPE_NONE);
		}
		// TODO: Add correct multiple types check.
		int count = 0;
		List/*<IEvaluatedType>*/ types = new ArrayList/*<IEvaluatedType>*/();

		Iterator i = returns.iterator();
		while( i.hasNext() ) {
			ReturnStatement returnS = (ReturnStatement)i.next();

			if (returnS.getExpression() != null) {
				IEvaluatedType type = evaluateASTNode(returnS.getExpression(),
						scope);
				if (!(type instanceof RecursionTypeCall)) {
					types.add(type);
				}
			} else {
				types.add(new SimpleType(SimpleType.TYPE_NONE));
			}
		}
		return makeMultiType(types);
	}

	private IEvaluatedType evaluateFunctionReturnValue(ASTNode node,
			CallHolder arguments, ASTNode scope) {

		// TODO: Add call dependent method return value implementation.
		MethodDeclaration method = (MethodDeclaration) node;
		List originalArgumetns = method.getArguments();
		List/*<Expression>*/ argValues = PythonTypeEvaluatorUtils
				.parseCallHolder(arguments);
		// TODO: Add argument type checking. *args is array of all posible
		// arguments.
		// and **kwrargs is map with names and arguments.
		List/*<Expression>*/ oldInitialValues = new ArrayList/*<Expression>*/();
		int index = 0;
		if (originalArgumetns.size() == argValues.size()) {
			Iterator i = originalArgumetns.iterator();
			while( i.hasNext()) {
				Argument arg = (Argument)i.next();
				oldInitialValues.add(arg.getInitialization());
				arg.setInitializationExpression((Expression)argValues.get(index));
				index += 1;
			}
		} else {
			return evaluateFunctionReturnValue(node, scope);
		}
		IEvaluatedType type = evaluateFunctionReturnValue(node, scope);
		index = 0;
		Iterator i = originalArgumetns.iterator();
		while( i.hasNext()) {
			Argument arg = (Argument)i.next();
			arg.setInitializationExpression((Expression)oldInitialValues.get(index));
			index += 1;
		}
		return type;
	}

	private IEvaluatedType evaluateMethodReturnValue(ASTNode node,
			CallHolder arguments, ASTNode scope) {

		// TODO: Add call dependent method return value implementation.
		MethodDeclaration method = (MethodDeclaration) node;
		List originalArgumetns = method.getArguments();
		List/*<Expression>*/ argValues = PythonTypeEvaluatorUtils
				.parseCallHolder(arguments);
		// TODO: Add argument type checking. *args is array of all posible
		// arguments.
		// and **kwrargs is map with names and arguments.
		List/*<Expression>*/ oldInitialValues = new ArrayList/*<Expression>*/();
		int index = 0;
		if (originalArgumetns.size() - 1 == argValues.size()) {
			boolean skipFirst = true;
			Iterator i = originalArgumetns.iterator();
			while( i.hasNext()) {
				Argument arg = (Argument)i.next();
				if (skipFirst) {
					skipFirst = false;
					continue;
				}
				oldInitialValues.add(arg.getInitialization());
				arg.setInitializationExpression((Expression)argValues.get(index));
				index += 1;
			}
		} else {
			return evaluateFunctionReturnValue(node, scope);
		}
		IEvaluatedType type = evaluateFunctionReturnValue(node, scope);
		index = 0;
		boolean skipFirst = true;
		Iterator i = originalArgumetns.iterator();
		while( i.hasNext()) {
			Argument arg = (Argument)i.next();
			if (skipFirst) {
				skipFirst = false;
				continue;
			}
			arg.setInitializationExpression((Expression)oldInitialValues.get(index));
			index += 1;
		}
		return type;
	}

	private IEvaluatedType checkArguments(MethodDeclaration method,
			String reference) {

		// / Checks for parameters and may be it has types.
		List arguments = method.getArguments();
		boolean first = true;
		Iterator i = arguments.iterator();
		while( i.hasNext()) {
			Argument arg = (Argument)i.next();
			if (arg.getName().equals(reference)) {
				/*
				 * TODO: Add staticmethod and classmethods check here.
				 */
				if (first) {
					// should return itselt if this is correct and upper if it
					// is block possible.
					ASTNode parent = (ASTNode)this.fParents.get(method);
					ASTNode parentParent = PythonTypeEvaluatorUtils
							.getModelLikeNode(fParents, parent);
					if (parentParent instanceof TypeDeclaration) {
						/*
						 * suppose this is self. If is call check may be this is
						 * call to __call__ method.
						 */
						// TODO: Add check of staticmethod and classmethod to
						// return needed type here.
						return new ClassInstanceType(this.fModule,
								(TypeDeclaration) parentParent);
					}
				}
				if (arg.getInitialization() != null) {
					IEvaluatedType type = evaluateASTNode(arg
							.getInitialization(), null);
					if (type instanceof UnknownType) {
						unknownSimpleReferenceBecauseOfArgument += 1;
					}
					return type;
				} else {
					unknownSimpleReferenceBecauseOfArgument += 1;
					return UnknownType.INSTANCE;
				}
			}
			first = false;
		}
		return null;
	}

	private IEvaluatedType checkMethodNode(MethodDeclaration method,
			CallHolder callHolder, SimpleReference reference) {

		if (method.getName().equals(reference.getName())) {
			List arguments = method.getArguments();
			List/*<Expression>*/ callHolderArguments = PythonTypeEvaluatorUtils
					.parseCallHolder(callHolder);
			// TODO: Add correct check for arguments.
			if (callHolderArguments.size() == arguments.size()) {
				return new FunctionType(this.fModule, method);
			} else {
				return new FunctionType(this.fModule, method, true);
			}
		}
		return null;
	}

	/**
	 * Used to filter list of in one module model elements by call holder and
	 * some other information.
	 * 
	 * @param types
	 * @param callHolder
	 * @return one desired element or null if it can't be found.
	 */
	private IModelElement filterCallType(List/*<IModelElement>*/ types,
			CallHolder callHolder) {

		// TODO: Add correct code here.
		// Now return first with appropriate count of arguments if function and
		// first class if class.
		List/*<IModelElement>*/ fTypes = new ArrayList/*<IModelElement>*/();
		fTypes.addAll(types);
		Collections.reverse(fTypes);
		Iterator i = fTypes.iterator();
		while( i.hasNext() ) {
			IModelElement element = (IModelElement)i.next();
			if (element instanceof IMethod) {
				IMethod method = (IMethod) element;
				String[] args = null;
				try {
					args = method.getParameters();
					List/*<Expression>*/ callHolderArgs = PythonTypeEvaluatorUtils
							.parseCallHolder(callHolder);
					if (args.length == callHolderArgs.size()) {
						return element;
					}
				} catch (ModelException ex) {
					ex.printStackTrace();
				}
			} else if (element instanceof IType) { // This is class
				// instanciation possible
				// action.
				return element;
			}
		}

		return null;
	}

	private IEvaluatedType evaluateSimpleReferenceType(
			SimpleReference reference, ASTNode scope) {

		// adding only for simple reference detections.
		if (reference.getName().indexOf(".")==-1) {
			totalSimpleReferenceDetections += 1;
		}

		ASTNode parent = null;
		if (!this.fParents.containsKey(reference)) {
			// throw new RuntimeException("node not in AST.");
			if (reference.getName().indexOf(".")==-1) {
				unknownSimpleReferenceNotInAST += 1;
			}
			return UnknownType.INSTANCE;
		}
		parent = PythonTypeEvaluatorUtils.getParentNode(this.fParents,
				reference);
		if (parent == null) {
			if (reference.getName().indexOf(".")==-1) {
				totalSimpleReferenceDetections -= 1;
				/*
				 * this is not detection. FIXME: Possible this is some kind of
				 * not in ast nodes? Need to check.
				 */
			}
			return UnknownType.INSTANCE;
		}
		/*
		 * This is parent that can' be found in this context.
		 */
		ASTNode nodeParent = PythonTypeEvaluatorUtils.getNodeParentBeforeModel(
				this.fParents, reference);
		if (nodeParent == null) {
			if (reference.getName().indexOf(".")==-1) {
				unknownSimpleReferenceEmptyParent += 1;
			}
			return UnknownType.INSTANCE;
		}
		ASTNode pnode = nodeParent;

		/*
		 * Previous parent. node parent first not module, method or type will be
		 * fine. This is used to remove under declarations.
		 */

		boolean parentFromScope = false;
		while (parent != null) {
			// find from begining this node declaration.
			// May be only assignments or methods.
			List/*<ASTNode>*/ nodes = null;

			try {
				if (parent instanceof ModuleDeclaration && scope != null
						&& !parentFromScope) {
					pnode = PythonTypeEvaluatorUtils.getNodeParentBeforeModel(
							this.fParents, scope);
					nodeParent = pnode;
					parent = PythonTypeEvaluatorUtils.getParentNode(
							this.fParents, scope);
					parentFromScope = true;
				}
				// This is for correcting filtration.
				if (scope != null) {
					if (parent instanceof ModuleDeclaration) {
						ASTNode par = (ASTNode)this.fParents.get(nodeParent);
						if (par != null) {
							while (!par.equals(parent) && par != null) {
								nodeParent = par;
								par = (ASTNode)this.fParents.get(nodeParent);
							}
						}
					}
				}

				nodes = getUpperNodes(parent, pnode);
				// if this is parent then lets go to parent parent for correct
				// checks.
				if (parent instanceof Block) {

					IEvaluatedType stype = checkListForReference(reference
							.getName(), nodes, scope);
					if (stype != null) {
						return stype;
					}

					/*
					 * If we can't found here. Then check for this scope has
					 * contexts. and check may be we import needed element from
					 * this context.
					 */

					IEvaluatedType imported = checkReferenceImports(reference
							.getName(), nodes);
					if (imported != null) {
						return imported;
					}
					if (!(parent instanceof ModuleDeclaration)) {
						if (!this.fParents.containsKey(parent)) {
							if (reference.getName().indexOf(".")==-1) {
								unknownSimpleReferenceNotInAST += 1;
							}
							return UnknownType.INSTANCE;
						}
					} else {
						parent = null;
					}
				} else if (parent instanceof MethodDeclaration) {
					IEvaluatedType type = checkArguments(
							(MethodDeclaration) parent, reference.getName());
					if (type != null) {
						return type;
					}
				} else if (parent instanceof TypeDeclaration) { // we can search
																// for class or
																// method name
																// here also.
					Iterator i = nodes.iterator();
					while( i.hasNext() ) {
						ASTNode node = (ASTNode)i.next();
						if (node instanceof MethodDeclaration) {
							MethodDeclaration method = (MethodDeclaration) node;
							if (method.getName().equals(reference.getName())) {
								return new FunctionType(this.fModule,
										(MethodDeclaration) node);
							}
						}
						if (node instanceof TypeDeclaration) {
							TypeDeclaration method = (TypeDeclaration) node;
							if (method.getName().equals(reference.getName())) {
								return new OldClassType(this.fModule,
										(TypeDeclaration) node);
							}
						}
					}
				}

				pnode = parent;
				parent = (ASTNode)this.fParents.get(parent);
			} catch (Exception e) {
				e.printStackTrace();
				return UnknownType.INSTANCE;
			}
		}
		// check in ClassIndex.

		// check for buildin name.
		IEvaluatedType buildinType = evaluateBuildin(reference.getName());
		if (buildinType != null) {
			return buildinType;
		}
		if (!PythonBuiltIns.isBuiltin(reference.getName())) {
			if (reference.getName().indexOf(".")==-1) {
				unknownSimpleReferenceNotFoundAtEnd += 1;
			}
		}
		return UnknownType.INSTANCE;
	}

	private IEvaluatedType checkReferenceImports(String reference,
			List/*<ASTNode>*/ nodes) {

		Iterator i = nodes.iterator();
		while( i.hasNext() ) {
			ASTNode nde = (ASTNode)i.next();
			if (nde instanceof PythonImportFromStatement) {
				IModelElement element = null;// this.findImport( (
												// PythonImportFromStatement
												// )nde, reference );
				if (element != null) {
					if (element instanceof IField) {
						return new ImportedType(PythonTypeEvaluator
								.evaluateLast(element),
								PythonTypeEvaluatorUtils
										.getElementModule(element));
					} else if (element instanceof IMethod) {
						return new ImportedType(new ModelFunctionType(
								(IMethod) element), PythonTypeEvaluatorUtils
								.getElementModule(element));
					} else if (element instanceof IType) {
						return new ImportedType(new ModelClassType(
								(IType) element), PythonTypeEvaluatorUtils
								.getElementModule(element));
					} else if (element instanceof IModule) {
						return new ImportedType(new ModelModuleType(
								(IModule) element), PythonTypeEvaluatorUtils
								.getElementModule(element));
					}
				}
			}
			// This check for module by name returning.
			else if (nde instanceof PythonImportStatement) {
				PythonImportStatement importStatement = (PythonImportStatement) nde;
				Expression expr = importStatement.getExpression();
				List/*<Expression>*/ imports = new ArrayList/*<Expression>*/();
				if (expr instanceof PythonTestListExpression) {
					PythonTestListExpression testList = (PythonTestListExpression) expr;
					imports = testList.getExpressions();
				} else {
					imports.add(expr);
				}
				Iterator j = imports.iterator();
				while( j.hasNext() ) {
					Expression imp = (Expression)j.next();
					if (imp instanceof PythonImportExpression) {
						PythonImportExpression impex = (PythonImportExpression) imp;
						String impexName = impex.getName();
						if (impexName.equals(reference)) {
							IModule module = null;// this.findImport( impex
													// );//findModuleFromImport(
													// importStatement,
													// impexName );
							if (module != null) {
								return new ModelModuleType(module);
							}
						}
						// if it contains dots then check for sub name is
						// equals.
						if (impexName.indexOf(".")!=-1) {
							if (impexName.startsWith(reference)) {
								// cool lets return appropriate module.
								IModule module = null;// findModuleFromImport(
														// importStatement,
														// reference );
								if (module != null) {
									return new ModelModuleType(module);
								}
							}
						}
					} else if (imp instanceof PythonImportAsExpression) {
						IModule module = null;// findModuleFromImport(
												// importStatement, ( (
												// PythonImportAsExpression )imp
												// ).getAsName( ) );
						if (module != null) {
							return new ModelModuleType(module);
						}
					}
				}
			}
		}
		return null;
	}

	// TODO: Need to be reimplemented.
	// private Collection< IImportStatement > getImportsFromNode( ASTNode nde )
	// {
	//
	// ASTNode parent = PythonTypeEvaluatorUtils.getModelLikeNode( fParents, nde
	// );
	// if( parent == null ) {
	// return null;
	// }
	// IModelElement element = null;
	// if( parent instanceof MethodDeclaration ) {
	// MethodDeclaration methodParent = ( MethodDeclaration )parent;
	// element = this.filerElementForAST( this.fLocalIndex.get(
	// methodParent.getName( ) ) );
	// }
	// else if( parent instanceof TypeDeclaration ) {
	// TypeDeclaration classParent = ( TypeDeclaration )parent;
	// element = this.filerElementForAST( this.fLocalIndex.get(
	// classParent.getName( ) ) );
	// }
	// else if( parent instanceof ModuleDeclaration ) {
	// element = this.fModelModule;
	// }
	// if( element != null ) {
	// Collection< IImportStatement > imports = null;
	// if( element.getElementType( ) == IModelElement.Kind.Class ) {
	// IType classElement = ( IType )element;
	// try {
	// imports = classElement.getImports( );
	// }
	// catch( ModelException ex ) {
	// ex.printStackTrace( );
	// return null;
	// }
	// }
	// else if( element.getElementType( ) == IModelElement.Kind.Method ) {
	// IMethod method = ( IMethod )element;
	// try {
	// imports = method.getImports( );
	// }
	// catch( ModelException ex ) {
	// ex.printStackTrace( );
	// return null;
	// }
	// }
	// else if( element.getElementType( ) == IModelElement.SOURCE_MODULE ) {
	// IModule module = ( IModule )element;
	// try {
	// imports = module.getImports( );
	// }
	// catch( ModelException ex ) {
	// ex.printStackTrace( );
	// return null;
	// }
	// }
	// return imports;
	// }
	// return null;
	// }

	// TODO: Need tot be reimplemented.
	// private IModule findModuleFromImport( PythonImportStatement statement,
	// String name ) {
	//
	// Collection< IImportStatement > imports = getImportsFromNode( statement );
	// if( imports != null ) {
	// for( IImportStatement importStatement : imports ) {
	// if( ! ( importStatement instanceof PythonModelImportStatement ) )
	// continue;
	//
	// PythonModelImportStatement pythonModelImportStatement = (
	// PythonModelImportStatement )importStatement;
	//
	// if( pythonModelImportStatement.getKind( ) ==
	// PythonModelImportStatement.Kind.ModuleAs ) {
	//
	// if( !pythonModelImportStatement.getModuleName( ).equals( name ) ) {
	// continue;
	// }
	//
	// IModule module = null;
	// try {
	// module = pythonModelImportStatement.getModule( );
	// }
	// catch( ModelException ex ) {
	// ex.printStackTrace( );
	// }
	// if( module != null ) {
	// return module;
	// }
	// }
	// }
	// }
	// return null;
	// }

	Map/*<String, List<IModelElement>>*/ makeLocalIndex(IModule module) {

		Map/*<String, List<IModelElement>>*/ index = new HashMap/*<String, List<IModelElement>>*/();
		List/*<IModelElement>*/ queue = new ArrayList/*<IModelElement>*/();
		queue.add(module);

		while (queue.size() > 0) {
			IModelElement e = (IModelElement)queue.get(0);
			queue.remove(0);

			if (!index.containsKey(e.getElementName())) {
				List/*<IModelElement>*/ list = new ArrayList/*<IModelElement>*/();
				list.add(e);
				index.put(e.getElementName(), list);
			} else {
				List/*<IModelElement>*/ list = (List)index.get(e.getElementName());
				list.add(e);
			}

			// adding childs.
			IModelElement[] childs = null;
			try {
				if (e instanceof IParent) {
					childs = ((IParent) e).getChildren();
				}
			} catch (ModelException ex) {
				ex.printStackTrace();
			}
			if (childs != null) {
				// queue.addAll( childs. );
				for( int i = 0; i < childs.length; ++i ) {
					IModelElement element = childs[i];
					queue.add(element);
				}
			}
		}
		return index;
	}

	/**
	 * Used to find model import for selected ast import.
	 * 
	 * @param statement
	 * @return
	 */
	// TODO: Need to be reimplemented.
	// private IModelElement findFromImport( PythonImportFromStatement
	// statement, String name ) {
	//
	// Collection< IImportStatement > imports = getImportsFromNode( statement );
	//
	// if( imports != null ) {
	// for( IImportStatement imp : imports ) {
	//
	// if( ! ( imp instanceof PythonModelImportStatement ) ) continue;
	// PythonModelImportStatement importStatement = ( PythonModelImportStatement
	// )imp;
	//
	// if( importStatement.getKind( ) ==
	// PythonModelImportStatement.Kind.ElementsFrom || importStatement.getKind(
	// ) == PythonModelImportStatement.Kind.WholeModule ) {
	//
	// PositionInformation position = statement.getPosition( );
	// if( !statement.isAllImport( ) && ( importStatement.getKind( ) ==
	// PythonModelImportStatement.Kind.WholeModule ) ) {
	// continue;
	// }
	//
	// if( !importStatement.getModuleName( ).equals(
	// statement.getImportModuleName( ) ) ) {
	// continue;
	// }
	// if( !importStatement.getPosition( ).equals( position ) ) {
	// continue;
	// }
	//
	// IModelElement modelElement = null;
	// try {
	// modelElement = importStatement.isImported( name );
	// }
	// catch( ModelException ex ) {
	// ex.printStackTrace( );
	// }
	// if( modelElement != null ) {
	// return modelElement;
	// }
	// }
	// }
	// }
	//
	// return null;
	// }
	/*
	 * private IModule findImport(PythonImportStatement statement ) { //we need
	 * to find selected module name in project fragment roots. IDylanProject
	 * project = this.fModelModule.getScriptProject(); IProjectFragment[]
	 * fragments; try { fragments = project.getProjectFragments(); for(
	 * IProjectFragment pf:fragments ) { IScriptFolder sf = pf.getScriptFolder(
	 * new Path( "" ) ); if( sf != null && sf.exists() ) { ISourceModule sm =
	 * sf.getSourceModule( statement.get + ".py" ); if( sm != null &&
	 * sm.exists() ) { //lets find element with selected name. IModelElement[]
	 * elements = sm.getChildren(); for( IModelElement element: elements ) { if(
	 * element.getElementName().equals( reference ) ) { return element; } } } } } }
	 * catch( ModelException me ) { me.printStackTrace(); }
	 * 
	 * IResource ur; try { ur = this.fModelModule.getUnderlyingResource(); if(
	 * ur.getType() == IResource.FOLDER ) { IScriptFolder sf =
	 * (IScriptFolder)ur; ISourceModule sm = sf.getSourceModule(
	 * statement.getImportModuleName() + ".py" ); if( sm != null && sm.exists() ) { //
	 * lets find element with selected name. IModelElement[] elements =
	 * sm.getChildren(); for( IModelElement element: elements ) { if(
	 * element.getElementName().equals( reference ) ) { return element; } } } }
	 *  } catch( ModelException me ) { me.printStackTrace(); }
	 * 
	 *  // Secondly lets search for module in current folder. return null; }
	 */

	private IModelElement filerElementForAST(List/*<IModelElement>*/ elements) {

		if (elements.size() == 1) {
			return (IModelElement)elements.get(0);
		} else if (elements.size() > 0) { // TODO: Add filtration here.
			return (IModelElement)elements.get(0);
		}
		return null;
	}

	/**
	 * TODO: Add building here.
	 * 
	 * @param reference
	 * @return
	 */
	private IEvaluatedType evaluateBuildin(String reference) {

		if (PythonBuiltIns.isBuiltin(reference)) {
			unknownSimpleReferenceNotFoundAtEndBuiltin += 1;
		}
		return null;
	}

	private IEvaluatedType checkListForReference(String reference,
			List/*<ASTNode>*/ nodes, ASTNode scope) {

		Iterator i = nodes.iterator();
		while( i.hasNext()) {
			ASTNode node = (ASTNode)i.next();
			if (node instanceof Assignment) {
				Assignment assignment = (Assignment) node;
				Statement left = assignment.getLeft();
				Statement right = assignment.getRight();
				if (left instanceof SimpleReference) {
					if (((SimpleReference) left).getName().equals(reference)) {
						if (right != null) {
							return evaluateASTNode(right, scope);
						} else {
							return new SimpleType(SimpleType.TYPE_NONE);
						}
					}
				} else if (left instanceof ExtendedVariableReference) {
					ExtendedVariableReference ref = (ExtendedVariableReference) left;
					if (ref.isDot(0) && ref.getExpressionCount() == 2) {
						Expression first = ref.getExpression(0);
						Expression second = ref.getExpression(1);
						// TODO: Replace self with appropriate first argument of
						// function.
						if (first instanceof VariableReference
								&& ((VariableReference) first).getName()
										.equals(SELF_CONSTANT)) {
							if (second instanceof VariableReference
									&& ((VariableReference) second).getName()
											.equals(reference)) {
								if (right != null) {
									return evaluateASTNode(right, scope);
								} else {
									return new SimpleType(SimpleType.TYPE_NONE);
								}
							}
						}
					}
				}
			}
			if (node instanceof MethodDeclaration) {
				MethodDeclaration method = (MethodDeclaration) node;
				if (method.getName().equals(reference)) {
					return new FunctionType(this.fModule,
							(MethodDeclaration) node);
				}
			}
			if (node instanceof TypeDeclaration) {
				TypeDeclaration method = (TypeDeclaration) node;
				if (method.getName().equals(reference)) {
					return new OldClassType(this.fModule, (TypeDeclaration) node);
				}
			}
		}
		return null;
	}
}
