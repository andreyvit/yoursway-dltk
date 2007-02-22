package org.eclipse.dltk.tcl.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.tcl.internal.parser.TclParseUtils;

public class TclNamespaceDeclaration extends TypeDeclaration {

	public TclNamespaceDeclaration(DLTKToken name) {
		super(name);
	}

	public TclNamespaceDeclaration(String name, int nameStart, int nameEnd, int start, int end) {
		super(name, nameStart, nameEnd, start, end);
	}

	public FieldDeclaration[] getVariables() {
		List variableNames = new ArrayList();
		List variableDeclarations = new ArrayList();
		List statements = this.getStatements();
		Iterator i = statements.iterator();
		while( i.hasNext() ) {
			Object o = i.next();
			if( o instanceof TclStatement ) {
				TclStatement statement = (TclStatement)o;
				FieldDeclaration[] decls = TclParseUtils.returnVariableDeclarations(statement);
				if( decls != null ) {
					for( int j = 0; j < decls.length; ++j ) {
						String name2 = decls[j].getName();
						if( !variableNames.contains(name2)) {
							variableNames.add(name2);
							variableDeclarations.add(decls[j]);
						}
					}
				}
			}
		}
		return (FieldDeclaration[])variableDeclarations.toArray(new FieldDeclaration[variableDeclarations.size()]);
	}
}
