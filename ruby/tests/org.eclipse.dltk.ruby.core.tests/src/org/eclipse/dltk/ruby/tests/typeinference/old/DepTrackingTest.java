/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.tests.typeinference.old;

import junit.framework.Test;

import org.eclipse.dltk.ruby.tests.typeinference.TypeInferenceSuite;

public class DepTrackingTest {

	public static Test suite() {
		return new TypeInferenceSuite("/workspace/typeinference/deptracking");
	}

}
