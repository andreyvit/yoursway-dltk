/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.text.blocks;

public abstract class KeywordRole {

	private final String name;

	private final int id;

	private KeywordRole(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public int id() {
		return id;
	}

	public static final int ID_BEGINNING = 0;

	public static final int ID_ENDING = 1;

	public static final int ID_MIDDLE = 2;

	public static final int COUNT = 3;

	public static final KeywordRole BEGINNING = new KeywordRole(ID_BEGINNING, "BEGINNING") {
	};

	public static final KeywordRole ENDING = new KeywordRole(ID_ENDING, "ENDING") {
	};

	public static final KeywordRole MIDDLE = new KeywordRole(ID_MIDDLE, "MIDDLE") {
	};

}
