package org.eclipse.dltk.ruby.tests.parser;

import java.io.StringWriter;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.utils.CorePrinter;

public class AST2StringVisitor extends ASTVisitor {
	
	

	private StringWriter writer;
	private CorePrinter printer;
	private String indent;

	public AST2StringVisitor() {
		super();
		writer = new StringWriter();
		printer = new CorePrinter(writer);
		indent = "";
	}

	public boolean visitGeneral(ASTNode node) throws Exception {
		String str = node.debugString();
		this.printer.println(indent + "+" + str);
		indent += "  ";
		return super.visitGeneral(node);
	}
	
	private static String simplifyClassName(String name) {
		int pos = name.lastIndexOf('.');
		return name.substring(pos + 1);
	}	
			
	public boolean endvisitGeneral(ASTNode node) throws Exception {
		indent = indent.substring(0, indent.length() - 2);
		this.printer.println(indent + "-" + simplifyClassName(node.getClass().getName()));			
		return super.endvisitGeneral(node);
	}

	public String getResult() {
		printer.flush();
		printer.close();
		return writer.getBuffer().toString();
	}
	
}
