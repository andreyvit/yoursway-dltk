package org.eclipse.dltk.tcl.internal.core.parser.processors.tcl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.internal.parsers.raw.SimpleTclParser;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclParseException;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclScript;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclWord;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.core.ast.BinaryExpression;
import org.eclipse.dltk.tcl.core.ast.TclForeachStatement;
import org.eclipse.dltk.tcl.core.ast.TclVariableDeclaration;

public class TclForeachCommandProcessor extends AbstractTclCommandProcessor {
	
	private static final int MIN_ARG_NUMBER = 3;
	
	private ASTNode parseList(ASTNode listNode, ITclParser parser, boolean variables) {
		if (listNode instanceof TclBlockExpression) {
			try {
				ASTListNode varList = new ASTListNode(listNode.sourceStart(), listNode.sourceEnd());
				TclBlockExpression block = (TclBlockExpression)listNode;
				TclScript script = SimpleTclParser.parse(block.getBlock().substring(1, block.getBlock().length()-1));
				List commands = script.getCommands();
//				Assert.isTrue(commands.size() == 1);
				if( commands.size() == 1 ) {
					report(parser, "Error to parser", listNode, ProblemSeverities.Error);
					return null;
				}
				for (Iterator words = ((TclCommand)commands.get(0)).getWords().iterator(); words.hasNext(); ) {
					TclCommand command = new TclCommand();
					command.addWord((TclWord)words.next());
					ASTNode node = parser.processLocal(command, block.sourceStart()+1, null);
					if (node instanceof TclStatement && ((TclStatement)node).getChilds().size() == 1) { //eliminating redundant level of the AST 
						node = (ASTNode)((TclStatement)node).getChilds().get(0);
					}
					if (variables && node instanceof SimpleReference)
						varList.addNode(new TclVariableDeclaration((SimpleReference)node,null,node.sourceStart(), node.sourceEnd()));
					else varList.addNode(node);
				}
				return varList;
			} catch (TclParseException e) {
				this.report(parser, "Parsing error.", listNode, ProblemSeverities.Error);//TODO appropriate message
				return listNode;
			} 
		}
		else {
			if (listNode instanceof TclStatement && ((TclStatement)listNode).getChilds().size() == 1) { //eliminating redundant level of the AST 
				listNode = (ASTNode)((TclStatement)listNode).getChilds().get(0);
			}
			if (variables && listNode instanceof SimpleReference)
				return new TclVariableDeclaration((SimpleReference)listNode,null,listNode.sourceStart(), listNode.sourceEnd());
			else return listNode;
		}
	}
	
	/**
	 * @param statements IN - statements to be parsed (don't include 'for' and block statement)
	 * @param lists OUT - the list to place (varList, valList)-pair lists in.
	 * */
	private void matchLists(List statements, ITclParser parser, List/*<BinaryExpression>*/ lists) {
		Assert.isTrue(statements.size() % 2 == 0);	
		for(Iterator iter = statements.iterator(); iter.hasNext();) {
			ASTNode nodeVars = (ASTNode)iter.next();
			ASTNode vars = parseList(nodeVars,parser,true);
			ASTNode nodeVals = (ASTNode)iter.next();
			ASTNode vals = parseList(nodeVals, parser, false);
			lists.add(new BinaryExpression(vars,BinaryExpression.E_ASSIGN,vals));
		}
	}
	
	private Block matchBlock(ASTNode statement, ITclParser parser, ASTNode parent) {
		Block block = new Block(statement.sourceStart(), statement.sourceEnd());
		this.addToParent(parent, block);
		if (statement instanceof TclBlockExpression) {
			TclBlockExpression bl = (TclBlockExpression)statement;
			String content = bl.getBlock().substring(1, bl.getBlock().length()-1);
			parser.parse(content,statement.sourceStart()+1,block);
		}
		else block.addStatement(statement);
		return block;
	}

	public ASTNode process(TclCommand command, ITclParser parser, int offset, ASTNode parent) {
		ASTNode node = parser.processLocal(command, offset, parent);
		if (!(node instanceof TclStatement)) {
			return null;
		}
		TclStatement statement = (TclStatement) node;
		TclForeachStatement foreach = new TclForeachStatement(statement.sourceStart(), statement.sourceEnd());
		this.addToParent(parent, foreach);
		
		if (statement.getCount() % 2 != 0 || statement.getCount() < MIN_ARG_NUMBER + 1) {
			this.report(parser, "Syntax error: wrong number of arguments", node, ProblemSeverities.Error);
		}
		else {
			List lists = new ArrayList(); 
			List statements = statement.getChilds();
			matchLists(statements.subList(1, statements.size()-1), parser, lists);
			Block block = matchBlock(statement.getAt(statement.getCount()-1), parser, foreach);
			
			foreach.setArguments(lists);
			foreach.setBlock(block);
		}
		return foreach;
	}
}
