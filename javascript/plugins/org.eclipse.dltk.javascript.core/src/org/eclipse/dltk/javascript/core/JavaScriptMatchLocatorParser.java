/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.references.Reference;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.IMatchLocatorParser;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.core.search.matching.MatchLocatorParser;
import org.eclipse.dltk.core.search.matching.PatternLocator;
import org.eclipse.dltk.core.search.matching.PossibleMatch;
import org.eclipse.dltk.internal.javascript.parser.JavaScriptModuleDeclaration;
import org.eclipse.dltk.internal.javascript.parser.JavaScriptSourceParser;
import org.eclipse.dltk.internal.javascript.typeinference.ContextReference;
import org.eclipse.dltk.internal.javascript.typeinference.HostCollection;
import org.eclipse.dltk.internal.javascript.typeinference.IReference;
import org.eclipse.dltk.internal.javascript.typeinference.VaribleDeclarationReference;

public class JavaScriptMatchLocatorParser extends MatchLocatorParser implements
		IMatchLocatorParser {
	private JavaScriptSourceParser parser ;

	public JavaScriptMatchLocatorParser(MatchLocator locator) {
		super(locator);
		
		
	}

	IModelElement parent;
	public ModuleDeclaration parse(PossibleMatch possibleMatch) {		
		parent= possibleMatch.getModelElement();
		parser=new JavaScriptSourceParser(parent);
		if (parent instanceof ISourceModule){
			ISourceModule m=(ISourceModule) parent;
			try {
				return parser.parse(m.getPath().toOSString().toCharArray(), m.getSourceAsCharArray(), null);
			} catch (ModelException e) {

			}
		}
		return parser.parse(possibleMatch.getFileName(), possibleMatch.getSourceContents().toCharArray(), null);
	}

	public void parseBodies(ModuleDeclaration unit) {

		JavaScriptModuleDeclaration md = (JavaScriptModuleDeclaration) unit;
		unit.rebuild();
		MethodDeclaration[] methods = unit.getFunctions();
		if (methods != null) {
			PatternLocator locator = getPatternLocator();
			for (int i = 0; i < methods.length; i++) {
				MethodDeclaration method = methods[i];
				if (method instanceof MethodDeclaration) {
					MethodDeclaration methodDeclaration = method;
					locator.match(processMethod(methodDeclaration),
							getNodeSet());
					// parseBodies(methodDeclaration);
				}
			}
		}
		FieldDeclaration[] vars = md.getVariables();
		if (vars != null) {
			PatternLocator locator = getPatternLocator();
			for (int i = 0; i < vars.length; i++) {
				FieldDeclaration var = vars[i];
				locator.match(var, getNodeSet());
			}
		}
		HostCollection collection = md.getCollection();
		Collection sm = (Collection) collection.getReferences().values();
		Iterator i = sm.iterator();
		while (i.hasNext()) {
			Object next = i.next();
			if (next instanceof IReference)
			{
			IReference ref = (IReference) next;
			reportRef(ref, null, 0);
			}
		}
		Map ms = md.getFunctionMap();
		Iterator ia = ms.values().iterator();
		PatternLocator locator = getPatternLocator();
		// contibuting methods 
		while (ia.hasNext()) {
			HostCollection next = (HostCollection) ia.next();
			Reference node = new FunctionDeclarationReference(0, 0, "!!!"+next
					.getName(), next);
			locator.match(node, getNodeSet());
		}
		Collection references = md.getReferences();
		ia=references.iterator();
		while (ia.hasNext()){
			SimpleReference r=(SimpleReference) ia.next();
			
			locator.match(r, getNodeSet());
		}
	}

	private void reportRef(IReference ref, String sma, int level) {		
		
		String key = ref.getName();
		if (sma != null)
			key = sma + '.' + key;
		if (!(ref instanceof ContextReference))
		{
		Set sm = ref.getChilds(false);
		Iterator i = sm.iterator();		
		while (i.hasNext()) {
			Object next = i.next();
			if (next instanceof IReference)
			{
			
			IReference refa = (IReference) next;
			reportRef(refa, key, level + 1);
			}
		}
		}
		PatternLocator locator = getPatternLocator();
		// contibuting field to index
		Reference node = new VaribleDeclarationReference(0, 0, key,ref);
		locator.match(node, getNodeSet());
	}
}
