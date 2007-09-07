package org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.xotcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.xotcl.core.ITclParser;
import org.eclipse.dltk.xotcl.core.ast.BinaryExpression;
import org.eclipse.dltk.xotcl.core.ast.TclSwitchStatement;

public class TclSwitchCommandProcessor extends AbstractTclCommandProcessor {

	private static class MismatchException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public MismatchException() {}
		public MismatchException(String str) {
			super(str);
		}
	}
	
	private static final int OPTIONS_START_OR_STRING_POS = 1; //position in statements list
	
	/**
	 * @return 'string' argument position
	 * @throws nothing specific
	 * */
	private int matchOptions(TclStatement statement) {
		int pos = OPTIONS_START_OR_STRING_POS;
		while (pos < statement.getCount() && statement.getAt(pos) instanceof SimpleReference)
		{
			SimpleReference argument = (SimpleReference) statement.getAt(pos);
			String argumentRepresentation = argument.getName();
			if (TclSwitchStatement.options.contains(argumentRepresentation))
				++pos;
			else break;
			if (TclSwitchStatement.isOptionsEndMarker(argumentRepresentation))
				break;
		}
		return pos;
	}
	
	/**
	 * @throws MismatchException 
	 * */
	private Expression matchString(TclStatement statement, int pos) {
		if (pos >= statement.getCount()) {
			//TODO report an error
			throw new MismatchException();
		}
		return statement.getAt(pos);		//XXX: can any Expression be returned?  
	}
	
	/**
	 * @throws MismatchException 
	 * */
	private List /*<BinaryExpression>*/ matchAlternatives(TclStatement statement, int pos, ITclParser parser) {
		List /*<BinaryExpression>*/ list;
		List statements;
		if (statement.getAt(pos) instanceof TclBlockExpression) {
			TclBlockExpression block = (TclBlockExpression)statement.getAt(pos);
			String blockContent = block.getBlock();
			blockContent = blockContent.substring(1, blockContent.length() - 1);
			Block bl = new Block(block.sourceStart(), block.sourceEnd());
			parser.parse(blockContent, block.sourceStart() + 1 - parser.getStartPos(), bl);
			List statements2 = bl.getStatements();
			Object stmt = statements2.get(0);
			if( stmt instanceof TclStatement ) {
				statements = ((TclStatement)stmt).getExpressions();
			}
			else {
//				System.out.println("C");
				statements = new ArrayList();
			}
		}
		else { 
			statements = statement.getExpressions();
			statements = statements.subList(pos, statements.size());
		}
		if (statements.size()%2 != 0 || statements.size() < 2) {
			//TODO report an error
			throw new MismatchException();
		}
		list = new ArrayList(statements.size()/2);
		for (Iterator i = statements.iterator(); i.hasNext();) {
			Expression caseExpression = (Expression)i.next();
			Expression doExpression = (Expression)i.next();
			if (doExpression instanceof TclBlockExpression) {
				TclBlockExpression block = (TclBlockExpression)doExpression;
				String blockContent = block.getBlock();
				blockContent = blockContent.substring(1, blockContent.length() - 1);
				Block bl = new Block(block.sourceStart(), block.sourceEnd());
				parser.parse(blockContent, block.sourceStart() + 1 - parser.getStartPos(), bl);
				doExpression = block;
			}
			list.add(new BinaryExpression(caseExpression, Expression.E_CONDITIONAL,doExpression));
		}
		return list;
	}
	
	public ASTNode process(TclCommand command, ITclParser parser, int offset, ASTNode parent) {
		ASTNode node = parser.processLocal(command, offset, parent);
		if (!(node instanceof TclStatement)) {
			return null;
		}
		try{
			TclStatement statement = (TclStatement) node;
			int startIndex = matchOptions(statement);
			Expression string = matchString(statement, startIndex);
			List /*<BinaryExpression>*/ alternatives = matchAlternatives(statement, startIndex + 1, parser);
			int start = statement.getAt(0).sourceStart();
			int end = statement.getAt(statement.getCount() - 1).sourceEnd();
			return new TclSwitchStatement(string, alternatives, start, end);
		} catch (MismatchException e) {
			//TODO report error
			return null;
		}
	}
}
