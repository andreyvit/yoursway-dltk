package org.eclipse.dltk.ruby.ast;

import java.util.ArrayList;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;


public class MultipleAssignmentStatement extends Statement {
	
	private ArrayList mlhs_ = new ArrayList();
	private ArrayList mrhs_ = new ArrayList();
	private VariableReference asterisk_lhs_ = null;
	private Expression asterisk_rhs_ = null;
	private boolean handle_special_case_ = false;
	private boolean has_extra_comma_ = false; // e.g. "a, = 1, 2"

	public MultipleAssignmentStatement(boolean has_extra_comma) {
		has_extra_comma_ = has_extra_comma;
	}
	
/*	public void addLhs(Expression e)  {
		if (handle_special_case_) {
			mrhs_.add(e);
			return;
		}
		
		if (e instanceof VariableReference) {
			mlhs_.add((VariableReference)e);
		} else if (e instanceof AssignmentOperatorExpression) {
			// For inputs like 'a = 1, 2, 3', tree parser will recognize them,
			// but in wrong struture
			AssignmentOperatorExpression a = (AssignmentOperatorExpression)e;
			mlhs_.add(a.getLhs());
			mrhs_.add(a.getRhs());
			handle_special_case_ = true;
		} else if (e instanceof MethodCallExpression) {
			// For inputs like 'a, b = 1', a will be recognized as MethodCall
			MethodCallExpression m = (MethodCallExpression)e;
			if (m.getArguments() != null &&
					m.getArguments().size() > 0) {// TODO more erro checking?
													// e.g. a(), b = 1
				throw new RecognitionException("Only variable can be parallel assigned");
			}
			mlhs_.add(new LocalVariableExpression(m.getName(), false));
		} else {
			throw new RecognitionException("Only variable can be parallel assigned");
		}
	}
	
	public void addRhs(Expression e) {
		mrhs_.add(e);
	}
	
	public void setAsteriskRhs(Expression e) {
		assert(null == asterisk_rhs_);
		asterisk_rhs_ = e;
	}

	public void setAsteriskLhs(Expression e)throws RecognitionException {
		assert(null == asterisk_lhs_);
		if (e instanceof VariableExpression) {
			asterisk_lhs_ = (VariableExpression)e;
		} else {
			throw new RecognitionException("Only variable can be parallel assigned");
		}
	}
*/
	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void printNode(CorePrinter output) {
		// TODO Auto-generated method stub

	}

	public void traverse(ASTVisitor visitor) throws Exception {
		// TODO Auto-generated method stub

	}

}
