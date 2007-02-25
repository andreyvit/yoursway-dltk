package org.eclipse.dltk.ruby.internal.parser.visitors;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.PositionInformation;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Assignment;
import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.ExpressionList;
import org.eclipse.dltk.ast.references.ConstantReference;
import org.eclipse.dltk.ast.references.ExtendedVariableReference;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.SourceElementRequestVisitor;
import org.eclipse.dltk.ruby.ast.ConstantDeclaration;
import org.eclipse.dltk.utils.CorePrinter;

public class RubySourceElementRequestor extends SourceElementRequestVisitor {
	
	private static class TypeField {
		private String fName;

		private String fInitValue;

		private PositionInformation fPos;

		private Expression fExpression;

		private ASTNode fToNode;

		TypeField(String name, String initValue, PositionInformation pos,
				Expression expression, ASTNode toNode) {

			this.fName = name;
			this.fInitValue = initValue;
			this.fPos = pos;
			this.fExpression = expression;
			this.fToNode = toNode;
		}

		String getName() {

			return this.fName;
		}

		String getInitValue() {

			return this.fInitValue;
		}

		PositionInformation getPos() {

			return this.fPos;
		}

		Expression getExpression() {

			return this.fExpression;
		}

		ASTNode getToNode() {

			return this.fToNode;
		}

		public boolean equals(Object obj) {

			if (obj instanceof TypeField) {
				TypeField second = (TypeField) obj;
				return second.fName.equals(this.fName)
						&& second.fToNode.equals(this.fToNode);
			}
			return super.equals(obj);
		}

		public String toString() {

			return this.fName;
		}

	}

	// Used to prehold fields if adding in methods.
	private List/* <TypeField> */fNotAddedFields = new ArrayList/* <TypeField> */();

	/**
	 * Used to depermine duplicate names.
	 */
	private Map/* <TypeDeclaration, String> */fTypeVariables = new HashMap();

	public RubySourceElementRequestor(ISourceElementRequestor requestor) {

		super(requestor);
	}

	/**
	 * Used to create Call value in python syntax.
	 */
	protected String makeLanguageDependentValue(Expression value) {

		String outValue = "";
		if (value instanceof ExtendedVariableReference) {
			// Lets use AST Printer to print extended variable in python like
			// syntax.
			StringWriter stringWriter = new StringWriter();
			CorePrinter printer = new CorePrinter(stringWriter);
			value.printNode(printer);
			printer.flush();
			return stringWriter.getBuffer().toString();
		}
		return outValue;
	}

	/**
	 * Parsers Expresssion and extract correct variable reference.
	 * 
	 * @param left
	 */
	private void addVariableReference( Expression left, Statement right, boolean inClass, boolean inMethod ) {

		if (left == null) {
			throw new RuntimeException("addVariable expression can't be null");
		}
		if (left instanceof VariableReference) {
			VariableReference var = (VariableReference) left;

			if (!inMethod) { // for module static of class static variables.

				if (canAddVariables((ASTNode) this.fNodes.peek(), var.getName())) {
					ISourceElementRequestor.FieldInfo info = new ISourceElementRequestor.FieldInfo();
					info.modifiers = Modifiers.AccStatic;
					info.name = var.getName();
					info.nameSourceEnd = var.sourceEnd() - 1;
					info.nameSourceStart = var.sourceStart();
					info.declarationStart = var.sourceStart();
					this.fRequestor.enterField(info);
					if (right != null) {
						this.fRequestor.exitField(right.sourceEnd() - 1);
					} else {
						this.fRequestor.exitField(var.sourceEnd() - 1);
					}
				}
			}			

		} else if (left instanceof ExtendedVariableReference) {
			// This is for in class and in method.
			if (inClass && inMethod) {
				ExtendedVariableReference extendedVariable = ((ExtendedVariableReference) left);

				List/* <Expression> */varParts = extendedVariable
						.getExpressions();
				if (extendedVariable.isDot(0)) {
					Expression first = (Expression) varParts.get(0);
					// support only local variable addition.
					// TODO: Add more complex variable addition.
					Expression second = (Expression) varParts.get(1);

					if (first instanceof VariableReference
							&& second instanceof VariableReference) {
						String varName = ((VariableReference) first).getName();
						MethodDeclaration currentMethod = this
								.getCurrentMethod();
						List/* <Argument> */arguments = currentMethod
								.getArguments();
						if (arguments != null && arguments.size() > 0) {
							Argument firstArgument = (Argument) arguments
									.get(0);
							String argumentName = firstArgument.getName();
							if (argumentName.equals(varName)) {
								VariableReference var = (VariableReference) second;
								int initialValueStart = 0;
								int initialValueEnd = 0;
								if (right != null) {
									initialValueStart = right.sourceStart();
									initialValueEnd = right.sourceEnd();
								}
								PositionInformation pos = new PositionInformation(
										var.sourceStart(), var.sourceEnd(),
										initialValueStart, initialValueEnd);
								String initialString = this.makeValue(right);
								ASTNode method = (ASTNode) this.fNodes.pop();
								ASTNode toClass = (ASTNode) this.fNodes.peek();
								this.fNodes.push(method);

								TypeField field = new TypeField(var.getName(),
										initialString, pos, left, toClass);
								this.fNotAddedFields.add(field);
							}
						}
					}
				}
			} else if (left instanceof ExpressionList) { // Multiple
				// TODO: Add list of variables reporting.
				/*
				 * // assignment. ExpressionList list = (ExpressionList) left;
				 * List<Expression> exprs = list.getExpressions(); for
				 * (Expression expr : exprs) { }
				 */
			} else {// TODO: dynamic variable handling not yet supported.

			}
		} 
	}

	public boolean visit(Expression expression) throws Exception {

		if (expression instanceof Assignment) {
			// this is static variable assignment.
			Assignment assignment = ( Assignment )expression;
			Expression left = assignment.getLeft( );
			Statement right = assignment.getRight( );

			// Handle static variables
			this.addVariableReference(left, right, this.fInClass,
					this.fInMethod);
		} 
		if(expression instanceof CallExpression ) {
			CallExpression callE = (CallExpression)expression;
			
			CallArgumentsList args = callE.getArgs();
			int argsCount = 0;
			if( args != null && args.getExpressions() != null ) {
				argsCount = args.getExpressions().size();
			}
			int start = callE.sourceStart();
			int end = callE.sourceEnd();
			if( start < 0 ) {
				start = 0;
			}
			if( end < 0 ) {
				end = 1;
			}
			this.fRequestor.acceptMethodReference(callE.getName().toCharArray(), argsCount, start, end );
		}
		if( expression instanceof VariableReference ) {
			VariableReference variableReference = (VariableReference) expression;
			int pos = variableReference.sourceStart();
			if( pos < 0 ) {
				pos = 0;
			}
			this.fRequestor.acceptFieldReference(variableReference.getName().toCharArray(), pos);
			
		}
		return true;
	}

	public boolean endvisit(Expression expression) throws Exception {
		return true;
	}

	protected void onEndVisitMethod(MethodDeclaration method) {

		Iterator i = this.fNotAddedFields.iterator();
		while (i.hasNext()) {
			TypeField field = (TypeField) i.next();
			if (canAddVariables(field.getToNode(), field.getName())) {

				PositionInformation pos = field.getPos();

				ISourceElementRequestor.FieldInfo info = new ISourceElementRequestor.FieldInfo();
				info.modifiers = Modifiers.AccStatic;
				info.name = field.getName();
				info.nameSourceEnd = pos.nameEnd - 1;
				info.nameSourceStart = pos.nameStart;
				info.declarationStart = pos.sourceStart;
				this.fRequestor.enterField(info);
				this.fRequestor.exitField(pos.sourceEnd);

			}
		}
		this.fNotAddedFields.clear();
	}

	public boolean visit(Statement statement) throws Exception {
		if (statement instanceof ConstantDeclaration) {
			ConstantDeclaration constant = (ConstantDeclaration) statement;
			SimpleReference constName = constant.getName();
			ISourceElementRequestor.FieldInfo info = new ISourceElementRequestor.FieldInfo();
			info.modifiers = Modifiers.AccConstant;
			info.name = constName.getName();
			info.nameSourceEnd = constName.sourceEnd() - 1;
			info.nameSourceStart = constName.sourceStart();
			info.declarationStart = constName.sourceStart();
			this.fRequestor.enterField(info);
			this.fRequestor.exitField(constName.sourceEnd() - 1);			
		}
		return true;
	}

	private boolean canAddVariables(ASTNode type, String name) {

		if (this.fTypeVariables.containsKey(type)) {
			List variables = (List) this.fTypeVariables.get(type);
			if (variables.contains(name)) {
				return false;
			}
			variables.add(name);
			return true;
		} else {
			List variables = new ArrayList();
			variables.add(name);
			this.fTypeVariables.put(type, variables);
			return true;
		}
	}

	public boolean endvisit(Statement s) throws Exception {
		return true;
	}
}
