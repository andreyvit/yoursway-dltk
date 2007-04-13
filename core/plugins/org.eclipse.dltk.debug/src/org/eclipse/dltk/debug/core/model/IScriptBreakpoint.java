package org.eclipse.dltk.debug.core.model;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.dltk.dbgp.breakpoints.IDbgpBreakpoint;

public interface IScriptBreakpoint extends IBreakpoint {
	final int HIT_CONDITION_GREATER = IDbgpBreakpoint.HIT_CONDITION_GREATER;

	final int HIT_CONDITION_EQUAL = IDbgpBreakpoint.HIT_CONDITION_EQUAL;

	final int HIT_CONDITION_MULTIPLE = IDbgpBreakpoint.HIT_CONDITION_MULTIPLE;

	// Identifier
	String getIdentifier();

	void setIdentifier(String id) throws CoreException;

	// Hit count (-1 if not available)
	int getHitCount();

	// Hit value
	int getHitValue();

	void setHitValue(int count) throws CoreException;

	// Hit count
	int getHitCondition();

	void setHitCondition(int condition) throws CoreException;

	// Resource name
	String getResourceName();
	
	String getConditionalExpression();
	
	public void setConditionalExpression(String id) throws CoreException ;
		
	
	boolean isConditionalExpressionEnabled();
	
	void    setConditionalExpressionEnabled(boolean enabled) throws CoreException;
	
}
