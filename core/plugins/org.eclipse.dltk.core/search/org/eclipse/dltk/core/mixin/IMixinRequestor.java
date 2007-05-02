/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core.mixin;

public interface IMixinRequestor {
	public final static String MIXIN_NAME_SEPARATOR = MixinModel.SEPARATOR;
	public static class ElementInfo {
		/**
		 * Could be seperated by MIXIN_NAME_SEPARATOR.
		 * If it is separated, then it added by splitting. 
		 * Then user ask for parent, it will contain this element.
		 */
		public String key;
		/**
		 * All possible user object.
		 */
		public Object object;
	}
	void reportElement( ElementInfo info );
}
