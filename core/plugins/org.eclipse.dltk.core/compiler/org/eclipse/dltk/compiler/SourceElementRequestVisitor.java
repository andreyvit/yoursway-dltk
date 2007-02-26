package org.eclipse.dltk.compiler;

import java.util.List;
import java.util.Stack;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.Literal;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.ExtendedVariableReference;
import org.eclipse.dltk.ast.statements.Statement;

public class SourceElementRequestVisitor extends ASTVisitor {

	protected ISourceElementRequestor fRequestor = null;

	protected boolean fInClass = false; // if we are in class
	protected boolean fInMethod = false; // if we are in method

	protected MethodDeclaration fCurrentMethod = null;

	/**
	 * Used to hold visited nodes in deeph.
	 */
	protected Stack fNodes = new Stack();

	protected SourceElementRequestVisitor(ISourceElementRequestor requesor) {
		this.fRequestor = requesor;
	}

	protected MethodDeclaration getCurrentMethod() {
		return this.fCurrentMethod;

	}

	protected String makeLanguageDependentValue(Expression expr) {
		return "";
	}

	/**
	 * Called then end of methd are visited. Method are added to model before
	 * this call is done.
	 */
	protected void onEndVisitMethod(MethodDeclaration method) {

	}
	
	protected String[] processSuperClasses(TypeDeclaration type ) {
		List names = type.getSuperClassNames();
		if( names.size() == 0 ) {
			return null;
		}
		
		return (String[])names.toArray(new String[names.size()]);
	}

	/**
	 * Called then end of class are visted. Class are added to model before this
	 * call is done.
	 * 
	 */
	protected void onEndVisitClass(TypeDeclaration type) {

	}

	/**
	 * Creates correct string value from expression. For example for
	 * StringLiteral returns "value". And so on.
	 * 
	 * Return "" if it is imposible to make value from expression.
	 * 
	 * @param expr
	 * @return
	 */
	protected String makeValue(Statement stmt) {
		if (!(stmt instanceof Expression))
			return null;
		
		Expression expr = (Expression)stmt;
			
		if (expr == null) {
			return null;
		}
		String value = "";
		if (expr instanceof StringLiteral) {
			value = "\"" + ((StringLiteral) expr).getValue() + "\"";
		} else if (expr instanceof Literal) {
			value = ((Literal) expr).getValue();
		}
		// If it is Dot.
		// Lets make recursive value parsing in this case.
		else if (expr instanceof ExtendedVariableReference) {
			value += this.makeLanguageDependentValue(expr);
		}
		return value;
	}

	public boolean endvisit(MethodDeclaration method) throws Exception {

		this.fRequestor.exitMethod(method.sourceEnd());
		this.fInMethod = false;
		this.fCurrentMethod = null;

		this.onEndVisitMethod(method);

		this.fNodes.pop();
		return true;
	}

	public boolean endvisit(TypeDeclaration typeDeclaration) throws Exception {
		this.fRequestor.exitType(typeDeclaration.sourceEnd());
		this.fInClass = false;
		this.onEndVisitClass(typeDeclaration);
		this.fNodes.pop();
		return true;
	}

	public boolean visit(MethodDeclaration method) throws Exception {
		this.fNodes.push(method);
		List args = method.getArguments();
		String[] parameter = new String[args.size()];
		for (int a = 0; a < args.size(); a++) {
			Argument arg = (Argument) args.get(a);
			parameter[a] = arg.getName();
		}

		ISourceElementRequestor.MethodInfo mi = new ISourceElementRequestor.MethodInfo();
		mi.parameterNames = parameter;
		mi.name = method.getName();
		mi.modifiers = method.getModifiers();
		mi.nameSourceStart = method.getNameStart();
		mi.nameSourceEnd = method.getNameEnd() - 1;
		mi.declarationStart = method.sourceStart();

		this.fRequestor.enterMethod(mi);

		this.fInMethod = true;
		this.fCurrentMethod = method;
		return true;
	}

	public boolean visit(TypeDeclaration s) throws Exception {
		this.fNodes.push(s);
		ISourceElementRequestor.TypeInfo info = new ISourceElementRequestor.TypeInfo();
		info.modifiers = s.getModifiers();
		info.name = s.getName();
		info.nameSourceStart = s.getNameStart();
		info.nameSourceEnd = s.getNameEnd() - 1;
		info.declarationStart = s.sourceStart();
		info.superclasses = processSuperClasses(s);

		this.fRequestor.enterType(info);

		this.fInClass = true;
		return true;
	}

	public boolean endvisit(ModuleDeclaration s) throws Exception {
		this.fRequestor.exitModule(s.sourceEnd());
		this.fNodes.pop();
		return true;
	}

	public boolean visit(ModuleDeclaration declaration) throws Exception {
		this.fNodes.push(declaration);
		this.fRequestor.enterModule();
		return true;
	}

	public boolean endvisit(Expression s) throws Exception {
		this.fNodes.pop();
		return true;
	}

	public boolean endvisit(Statement s) throws Exception {
		this.fNodes.pop();
		return true;
	}

	public boolean visit(Expression expression) throws Exception {
		this.fNodes.push(expression);
		return true;
	}

	public boolean visit(Statement statement) throws Exception {
		this.fNodes.push(statement);
		return true;
	}
}
