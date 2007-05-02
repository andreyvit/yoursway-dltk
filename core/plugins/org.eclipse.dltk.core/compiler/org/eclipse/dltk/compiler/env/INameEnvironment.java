/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.compiler.env;


/**
 * The name environment provides a callback API that the compiler can use to
 * look up types, compilation units, and packages in the current environment.
 * The name environment is passed to the compiler on creation.
 */
public interface INameEnvironment {	
	/**
	 * This method cleans the environment uo. It is responsible for releasing
	 * the memory and freeing resources. Passed that point, the name environment
	 * is no longer usable.
	 * 
	 * A name environment can have a long life cycle, therefore it is the
	 * responsibility of the code which created it to decide when it is a good
	 * time to clean it up.
	 */
	void cleanup();
}
