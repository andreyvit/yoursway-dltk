package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.references.Reference;
import org.eclipse.dltk.utils.CorePrinter;

public class SymbolReference extends Reference {
	
	private String name;

	public SymbolReference (int start, int end, String name) {
		super(start, end);
		
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public SymbolReference (DLTKToken token) {
		this.setStart(token.getColumn());
		this.setEnd(this.sourceStart() + 4);
	}

	
	public void printNode(CorePrinter output) {
		output.formatPrintLn("SelfReference" + this.getSourceRange().toString());
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof SymbolReference) {
			SymbolReference sr = (SymbolReference)obj;			
			return name.equals(sr.name) && super.equals(obj);
		}
				
		return false;
	}

	public int hashCode() {
		return super.hashCode() ^ name.hashCode();
	}

	public String getStringRepresentation() {
		return toString();
	}
	
	public String toString() {
		return name;
	}
}
