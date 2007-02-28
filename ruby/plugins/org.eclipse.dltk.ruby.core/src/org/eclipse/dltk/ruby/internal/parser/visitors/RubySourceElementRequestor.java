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
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.ruby.ast.ColonExpression;
import org.eclipse.dltk.ruby.ast.ConstantDeclaration;
import org.eclipse.dltk.ruby.ast.SymbolReference;
import org.eclipse.dltk.utils.CorePrinter;

public class RubySourceElementRequestor extends SourceElementRequestVisitor {

	private static class TypeField {
		private String fName;

		private String fInitValue;

		private PositionInformation fPos;

		private Expression fExpression;

		private ASTNode fToNode;

		public TypeField(String name, String initValue,
				PositionInformation pos, Expression expression, ASTNode toNode) {

			this.fName = name;
			this.fInitValue = initValue;
			this.fPos = pos;
			this.fExpression = expression;
			this.fToNode = toNode;
		}

		public String getName() {
			return fName;
		}

		public String getInitValue() {
			return fInitValue;
		}

		public PositionInformation getPosition() {
			return fPos;
		}

		public Expression getExpression() {
			return fExpression;
		}

		public ASTNode getASTNode() {
			return fToNode;
		}

		public boolean equals(Object obj) {
			if (obj instanceof TypeField) {
				TypeField typeFileld = (TypeField) obj;
				return typeFileld.fName.equals(fName)
						&& typeFileld.fToNode.equals(fToNode);
			}

			return false;
		}

		public String toString() {
			return fName;
		}
	}

	private List fNotAddedFields = new ArrayList(); // Used to prehold fields if
	// adding in methods.
	private Map fTypeVariables = new HashMap(); // Used to depermine duplicate

	// names, ASTNode -> List of
	// variables

	private boolean canAddVariables(ASTNode type, String name) {
		if (fTypeVariables.containsKey(type)) {
			List variables = (List) fTypeVariables.get(type);
			if (variables.contains(name)) {
				return false;
			}
			variables.add(name);
			return true;
		} else {
			List variables = new ArrayList();
			variables.add(name);
			fTypeVariables.put(type, variables);
			return true;
		}
	}

	/**
	 * Parsers Expresssion and extract correct variable reference.
	 */
	private void addVariableReference(Expression left, Statement right,
			boolean inClass, boolean inMethod) {

		if (left == null) {
			throw new IllegalArgumentException(
					"addVariable expression can't be null");
		}

		if (left instanceof VariableReference) {
			VariableReference var = (VariableReference) left;

			if (!inMethod) {
				// For module static of class static variables.
				if (canAddVariables((ASTNode) fNodes.peek(), var.getName())) {
					ISourceElementRequestor.FieldInfo info = new ISourceElementRequestor.FieldInfo();
					info.modifiers = Modifiers.AccStatic;
					info.name = var.getName();
					info.nameSourceEnd = var.sourceEnd() - 1;
					info.nameSourceStart = var.sourceStart();
					info.declarationStart = var.sourceStart();
					fRequestor.enterField(info);
					if (right != null) {
						fRequestor.exitField(right.sourceEnd() - 1);
					} else {
						fRequestor.exitField(var.sourceEnd() - 1);
					}
				}
			}

		} else if (left instanceof ExtendedVariableReference) {
			if (inClass && inMethod) {
				// This is for in class and in method.
				ExtendedVariableReference extendedVariable = ((ExtendedVariableReference) left);

				List varParts = extendedVariable.getExpressions();

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
						List arguments = currentMethod.getArguments();
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

								String initialString = makeValue(right);
								ASTNode method = (ASTNode) fNodes.pop();
								ASTNode toClass = (ASTNode) fNodes.peek();
								fNodes.push(method);

								TypeField field = new TypeField(var.getName(),
										initialString, pos, left, toClass);
								fNotAddedFields.add(field);
							}
						}
					}
				}
			} else if (left instanceof ExpressionList) {
				// Multiple
				// TODO: Add list of variables reporting.
			} else {
				// TODO: dynamic variable handling not yet supported.
			}
		}
	}

	protected String[] processSuperClasses(TypeDeclaration type) {
		ExpressionList list = type.getSuperClasses();

		if (list == null) {
			return new String[0];
		}

		List expressions = list.getExpressions();
		List names = new ArrayList();
		for (Iterator iter = expressions.iterator(); iter.hasNext();) {
			Expression expr = (Expression) iter.next();
			if (expr instanceof SimpleReference) {
				names.add(((SimpleReference) expr).getName());
			} else if (expr instanceof ColonExpression) { // FIXME
				String name = "";

				while (expr instanceof ColonExpression) {
					ColonExpression colonExpression = (ColonExpression) expr;
					name = "::" + colonExpression.getName();
					Expression left = colonExpression.getLeft();
					if (!colonExpression.isFull() && left == null) {
						name = name.substring(2);
					}
					expr = left;
				}

				if (expr instanceof ConstantReference) {
					ConstantReference constant = (ConstantReference) expr;
					name = constant.getName() + name;
				}
				names.add(name);
			}
		}

		return (String[]) names.toArray(new String[names.size()]);
	}

	protected String makeLanguageDependentValue(Expression value) {
		String outValue = "";
		if (value instanceof ExtendedVariableReference) {
			StringWriter stringWriter = new StringWriter();
			CorePrinter printer = new CorePrinter(stringWriter);
			value.printNode(printer);
			printer.flush();
			return stringWriter.getBuffer().toString();
		}
		return outValue;
	}

	public RubySourceElementRequestor(ISourceElementRequestor requestor) {
		super(requestor);
	}

	// Visiting methods
	protected void onEndVisitMethod(MethodDeclaration method) {
		if (DLTKCore.DEBUG) {
			System.out.println("==> Method: " + method.getName());
		}

		Iterator it = fNotAddedFields.iterator();

		while (it.hasNext()) {
			TypeField field = (TypeField) it.next();

			if (canAddVariables(field.getASTNode(), field.getName())) {
				PositionInformation pos = field.getPosition();

				ISourceElementRequestor.FieldInfo info = new ISourceElementRequestor.FieldInfo();
				info.modifiers = Modifiers.AccStatic;
				info.name = field.getName();
				info.nameSourceEnd = pos.nameEnd - 1;
				info.nameSourceStart = pos.nameStart;
				info.declarationStart = pos.sourceStart;

				fRequestor.enterField(info);
				fRequestor.exitField(pos.sourceEnd);
			}
		}

		fNotAddedFields.clear();
	}
	
	protected static boolean isAttrLike(String name){
		return name.equals("attr_reader") || name.equals("attr_writer") || name.equals("attr_accessor"); 
	}

	// Visiting expressions
	public boolean visit(Expression expression) throws Exception {
		if (DLTKCore.DEBUG) {
			System.out.println("==> Expression: " + expression.toString());
		}

		if (expression instanceof Assignment) {
			// Assignment handling (this is static variable assignment.)

			Assignment assignment = (Assignment) expression;
			Expression left = assignment.getLeft();
			Statement right = assignment.getRight();

			// Handle static variables
			addVariableReference(left, right, fInClass, fInMethod);
		} else if (expression instanceof CallExpression) {
			// CallExpression handling
			CallExpression callExpression = (CallExpression) expression;

			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			// Only for expermintal purposes!!!
			String name = callExpression.getName();

			if (isAttrLike(name)) {
				CallArgumentsList list = callExpression.getArgs();
				List expr = list.getExpressions();
				Iterator it = expr.iterator();

				List args = new ArrayList();
				while (it.hasNext()) {
					SymbolReference sr = (SymbolReference) it.next();
					args.add(sr.getName());
				}

				ISourceElementRequestor.MethodInfo mi = new ISourceElementRequestor.MethodInfo();
				mi.parameterNames = (String[]) args.toArray(new String[args
						.size()]);
				mi.name = name;
				mi.modifiers = 0;
				mi.nameSourceStart = callExpression.sourceStart();
				mi.nameSourceEnd = callExpression.sourceEnd();
				mi.declarationStart = callExpression.sourceStart();

				fRequestor.enterMethod(mi);
				fRequestor.exitMethod(callExpression.sourceEnd());

				return true;
			}
			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

			// Arguments
			int argsCount = 0;
			CallArgumentsList args = callExpression.getArgs();
			if (args != null && args.getExpressions() != null) {
				argsCount = args.getExpressions().size();
			}

			// Start
			int start = callExpression.sourceStart();
			if (start < 0) {
				start = 0;
			}

			// End
			int end = callExpression.sourceEnd();
			if (end < 0) {
				end = 1;
			}

			// Accept
			fRequestor.acceptMethodReference(callExpression.getName()
					.toCharArray(), argsCount, start, end);
		} else if (expression instanceof VariableReference) {
			// VariableReference handling
			VariableReference variableReference = (VariableReference) expression;

			int pos = variableReference.sourceStart();
			if (pos < 0) {
				pos = 0;
			}

			// Accept
			fRequestor.acceptFieldReference(variableReference.getName()
					.toCharArray(), pos);
		}

		return true;
	}

	public boolean endvisit(Expression expression) throws Exception {
		return true;
	}

	// Visiting statements
	public boolean visit(Statement statement) throws Exception {
		if (DLTKCore.DEBUG) {
			System.out.println("==> Statement: " + statement.toString());
		}

		if (statement instanceof ConstantDeclaration) {
			ConstantDeclaration constant = (ConstantDeclaration) statement;
			SimpleReference constName = constant.getName();

			ISourceElementRequestor.FieldInfo info = new ISourceElementRequestor.FieldInfo();
			info.modifiers = Modifiers.AccConstant;
			info.name = constName.getName();
			info.nameSourceEnd = constName.sourceEnd() - 1;
			info.nameSourceStart = constName.sourceStart();
			info.declarationStart = constName.sourceStart();

			fRequestor.enterField(info);
			fRequestor.exitField(constName.sourceEnd() - 1);
		}

		return true;
	}

	public boolean endvisit(Statement s) throws Exception {
		return true;
	}
}
