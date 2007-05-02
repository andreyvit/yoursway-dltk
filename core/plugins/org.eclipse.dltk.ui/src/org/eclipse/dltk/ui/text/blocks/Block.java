/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.dltk.ui.text.blocks;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.Assert;

/**
 * Represents a language construct that consists of a single beginning word, a single ending word and
 * zero or more middle words. Examples of such constructions are braces, <code>if</code> .. 
 * <code>else</code> .. <code>endif</code>, <code>loop</code> .. <code>end loop</code>.
 * 
 * @author Andrey Tarantsov
 */
public class Block {
	
	private final Keyword beginning;

	private final Keyword ending;

	private final Keyword[] middles;
	
	public Block(Keyword beginning, Keyword ending) {
		this(beginning, ending, new Keyword[0]);
	}
	
	public Block(Keyword beginning, Keyword ending, Keyword[] middles) {
		Assert.isLegal(beginning.getRole() == KeywordRole.BEGINNING);
		Assert.isLegal(ending.getRole() == KeywordRole.ENDING);
		// TODO: check more
		this.beginning = beginning;
		this.ending = ending;
		this.middles = middles;
	}
	
	public String getBeginningRegularExpression() {
		return beginning.getPattern();
	}
	
	public Collection getMiddleRegularExpressions() {
		Collection parts = new ArrayList(middles.length);
		for (int i = 0; i < middles.length; i++) {
			Keyword item = middles[i];
			parts.add(item.getPattern());
		}
		return parts;
	}
	
	public String getEndingRegularExpression() {
		return ending.getPattern();
	}
	
	public Keyword getBeginning() {
		return beginning;
	}
	
	public Keyword getEnding() {
		return ending;
	}
	
	public Keyword[] getMiddleKeywords() {
		return middles;
	}
	
	public String toString() {
		return beginning.toString();
	}

	public void install(MultiMap allNames) {
		allNames.put(beginning, this);
		allNames.put(ending, this);
		for (int i = 0; i < middles.length; i++)
			allNames.put(middles[i], this);
	}
	
}