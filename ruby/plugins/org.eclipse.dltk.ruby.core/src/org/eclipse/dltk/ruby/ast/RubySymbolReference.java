/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.references.Reference;
import org.eclipse.dltk.utils.CorePrinter;

public class RubySymbolReference extends Reference {
	
	private String name;

	public RubySymbolReference (int start, int end, String name) {
		super(start, end);
		
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public RubySymbolReference (DLTKToken token) {
		this.setStart(token.getColumn());
		this.setEnd(this.sourceStart() + 4);
	}

	
	public void printNode(CorePrinter output) {
		output.formatPrintLn("RubySymbolReference" + this.getSourceRange().toString()); //$NON-NLS-1$
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof RubySymbolReference) {
			RubySymbolReference sr = (RubySymbolReference)obj;			
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
