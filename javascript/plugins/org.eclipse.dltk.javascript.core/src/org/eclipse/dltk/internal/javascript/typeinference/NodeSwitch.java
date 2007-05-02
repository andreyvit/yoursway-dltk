/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.javascript.typeinference;

import com.xored.org.mozilla.javascript.Node;
import com.xored.org.mozilla.javascript.ScriptOrFnNode;
import com.xored.org.mozilla.javascript.Token;
import com.xored.org.mozilla.javascript.Node.Jump;

public class NodeSwitch {

	protected int position;
	protected ScriptOrFnNode context;

	public NodeSwitch(ScriptOrFnNode module, int position) {
		super();
		this.context = module;
		this.position = position;
	}

	public Object doAction(Node node, Object arg) {
		if (node.getPosition() > position
				&& node.getType() != Token.CATCH_SCOPE
				&& node.getType() != Token.LEAVEWITH)
			throw new PositionReachedException(context);
		switch (node.getType()) {
		case Token.SCRIPT:
			return processScriptNode(node, arg);

		case Token.SET_REF:
			return processSetRefNode(node, arg);
		case Token.SET_REF_OP:
			return processSetRefOpNode(node, arg);
		case Token.SETVAR:
			return processSetVarNode(node, arg);
		case Token.FUNCTION:
			return processFunction(node, arg);
		case Token.BLOCK:
			return processBlock(node, arg);
		case Token.VAR:
			return processVarDeclaration(node, arg);
		case Token.LABEL:
			return processLabelNode(node, arg);
		case Token.EXPR_VOID:
			return processVoidExprNode(node, arg);
		case Token.EXPR_RESULT:
			return processExprResultNode(node, arg);
		case Token.EMPTY:
			return processEmptyNode(node, arg);
		case Token.TYPEOFNAME:
			return processTypeOfName(node, arg);
		case Token.TYPEOF:
			return processTypeOf(node, arg);
		case Token.SETELEM_OP:
			return processSetElemOpNode(node, arg);
		case Token.LOOP:
			return processLoop(node, arg);
		case Token.FALSE:
			return processFalse(node, arg);
		case Token.TRUE:
			return processTrue(node, arg);
		case Token.CALL:
			return processCall(node, arg);
		case Token.GETPROP:
			return processGetPropNode(node, arg);
		case Token.SETPROP:
			return processSetPropNode(node, arg);
		case Token.NAME:
			return processNameNode(node, arg);
		case Token.STRING:
			return processStringNode(node, arg);
		case Token.ARRAYLIT:
			return processArrayLitNode(node, arg);
		case Token.OBJECTLIT:
			return processObjectLitNode(node, arg);
		case Token.NUMBER:
			return processNumberNode(node, arg);
		case Token.NULL:
			return processNullNode(node, arg);
		case Token.LOCAL_BLOCK:
			return processLabekBlock(node, arg);
		case Token.TRY:
			return processTryNode(node, arg);
		case Token.WHILE:
			return processWhileNode(node, arg);
		case Token.DO:
			return processDoNode(node, arg);
		case Token.FOR:
			return processForNode(node, arg);
		case Token.GOTO:
			return processGotoNode(node, arg);
		case Token.TARGET:
			return processTargetNode(node, arg);
		case Token.CATCH_SCOPE:
			return processCatchScopeNode(node, arg);
		case Token.LOCAL_LOAD:
			return processLocalLoad(node, arg);
		case Token.HOOK:
			return processHookNode(node, arg);
		case Token.NOT:
			return processNotNode(node, arg);
		case Token.NEW:
			return processNewNode(node, arg);
		case Token.GETELEM:
			return processGetElemNode(node, arg);
		case Token.SETELEM:
			return processSetElemNode(node, arg);
		case Token.REGEXP:
			return processRegExp(node, arg);
		case Token.BINDNAME:
			return processBindNameNode(node, arg);
		case Token.SETNAME:
			return processSetNameNode(node, arg);
		case Token.BITOR:
			return processBitOrNode(node, arg);
		case Token.BITXOR:
			return processBitXorNode(node, arg);
		case Token.BITAND:
			return processBitAndNode(node, arg);
		case Token.ENUM_INIT_KEYS:
			return processEnumInitKeys(node, arg);
		case Token.ENUM_INIT_VALUES:
			return processEnumInitValues(node, arg);
		case Token.ENUM_NEXT:
			return processEnumNext(node, arg);
		case Token.EQ:
			return processEqNode(node, arg);
		case Token.IFEQ:
			return processIfEqNode(node, arg);
		case Token.NE:
			return processNE(node, arg);
		case Token.GE:
			return processGE(node, arg);
		case Token.LSH:
			return processLSH(node, arg);
		case Token.RSH:
			return processRSH(node, arg);
		case Token.DIV:
			return processDiv(node, arg);
		case Token.MUL:
			return processMul(node, arg);
		case Token.DELPROP:
			return processDelProp(node, arg);
		case Token.INC:
			return processINC(node, arg);
		case Token.DEC:
			return processGE(node, arg);
		case Token.LT:
			return processLT(node, arg);
		case Token.ADD:
			return processAdd(node, arg);
		case Token.VOID:
			return processVoid(node, arg);
		case Token.AND:
			return processAnd(node, arg);
		case Token.THIS:
			return processThis(node, arg);
		case Token.OR:
			return processOr(node, arg);
		case Token.ASSIGN:
			return processAssign(node, arg);
		case Token.ASSIGN_ADD:
			return processAdd(node, arg);
		case Token.ASSIGN_BITAND:
			return processBitAnd(node, arg);
		case Token.ASSIGN_BITOR:
			return processBitOr(node, arg);
		case Token.ASSIGN_BITXOR:
			return processBitXor(node, arg);
		case Token.ASSIGN_DIV:
			return processDiv(node, arg);
		case Token.ASSIGN_LSH:
			return processAssign_LSH(node, arg);
		case Token.ASSIGN_RSH:
			return processAssign_RSH(node, arg);
		case Token.ASSIGN_SUB:
			return processAssign_SUB(node, arg);
		case Token.ASSIGN_URSH:
			return processAssign_URSH(node, arg);
		case Token.BITNOT:
			return processBitNot(node, arg);
		case Token.BREAK:
			return processBreak(node, arg);
		case Token.CASE:
			return processCase(node, arg);
		case Token.COLON:
			return processColon(node, arg);
		case Token.COMMA:
			return processComma(node, arg);
		case Token.COLONCOLON:
			return processColonColon(node, arg);
		case Token.CONTINUE:
			return processContinue(node, arg);
		case Token.DEFAULT:
			return processDefault(node, arg);
		case Token.DEFAULTNAMESPACE:
			return processDefaultNameSpace(node, arg);
		case Token.DEL_REF:
			return processDelRef(node, arg);
		case Token.ELSE:
			return processElse(node, arg);
		case Token.ENTERWITH:
			return processEnterWidth(node, arg);
		case Token.LEAVEWITH:
			return processLeaveWidth(node, arg);
		case Token.WITH:
			return processWidth(node, arg);
		case Token.IFNE:
			return processWidth(node, arg);
		case Token.RETURN:
			return processReturn(node, arg);
		case Token.RETURN_RESULT:
			return processWidth(node, arg);
		case Token.GET_REF:
			return processWidth(node, arg);
		case Token.SWITCH:
			return processSwitch(node, arg);
		default:
			return processScriptNode(node, arg);
		}
	}

	public Object processReturn(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processSwitch(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processLeaveWidth(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processEnterWidth(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	private Object processSetVarNode(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	private Object processSetRefOpNode(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	private Object processSetRefNode(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processNE(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processLSH(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processRSH(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processMul(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processDelProp(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processINC(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processGE(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processLT(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processVoid(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processAnd(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processThis(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processOr(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processAssign(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processAdd(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processBitAnd(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processBitOr(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processBitXor(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processDiv(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processAssign_LSH(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processAssign_RSH(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processAssign_SUB(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processAssign_URSH(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processBitNot(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processBreak(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processCase(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processColon(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processComma(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processColonColon(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processContinue(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processWidth(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processElse(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processDelRef(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processDefaultNameSpace(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processDefault(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processIfEqNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processEqNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processEnumNext(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processEnumInitValues(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processEnumInitKeys(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processBitOrNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processBitXorNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processBitAndNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processSetNameNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processBindNameNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processRegExp(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processSetElemNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processGetElemNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processNewNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processNotNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processHookNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processLocalLoad(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processCatchScopeNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processTargetNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processGotoNode(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processForNode(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processDoNode(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processWhileNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processTryNode(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processLabekBlock(Node node, Object arg) {
		Node nm = node.getFirstChild();
		return processScriptNode(node, arg);
	}

	public Object processNullNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processNumberNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processObjectLitNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processArrayLitNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processStringNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processNameNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processSetPropNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processGetPropNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processCall(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processTrue(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processFalse(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processLoop(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processSetElemOpNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processTypeOf(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processTypeOfName(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processEmptyNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processExprResultNode(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processVoidExprNode(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processLabelNode(Node node, Object arg) {

		return processScriptNode(node, arg);
	}

	public Object processBlock(Node node, Object arg) {
		Node nm = node.getFirstChild();
		if (nm instanceof Jump) {
			Jump jm = (Jump) nm;
			int type = nm.getType();
			if (type == Token.IFNE) {
				int q = jm.getFirstChild().getType();
			}
			System.out.println(jm);
		}
		return processScriptNode(node, arg);
	}

	public Object processFunction(Node node, Object arg) {
		return arg;
	}

	public Object processVarDeclaration(Node node, Object arg) {
		return processScriptNode(node, arg);
	}

	public Object processScriptNode(Node node, Object arg) {
		Node no = node.getFirstChild();
		while (no != null) {
			doAction(no, arg);
			no = no.getNext();
		}
		return null;
	}
}
