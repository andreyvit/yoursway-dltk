package org.eclipse.dltk.internal.javascript.parser;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;

public class Test {

	public static void main(String[] args) {		
		JavaScriptSourceParser sp=new JavaScriptSourceParser();
		ModuleDeclaration parse = sp.parse("function main(){}".toCharArray(), null);
		System.out.println("Dpone");
	}
}
