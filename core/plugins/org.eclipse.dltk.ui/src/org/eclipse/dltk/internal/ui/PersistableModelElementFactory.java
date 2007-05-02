/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;


/** 
 * The PersistableModelElementFactory is used to save and recreate an IModelElement object.
 * As such, it implements the IPersistableElement interface for storage
 * and the IElementFactory interface for recreation.
 *
 * @see IMemento
 * @see IPersistableElement
 * @see IElementFactory
 */
public class PersistableModelElementFactory implements IElementFactory, IPersistableElement {

	private static final String KEY= "elementID"; //$NON-NLS-1$
	private static final String FACTORY_ID= "org.eclipse.dltk.ui.PersistableModelElementFactory"; //$NON-NLS-1$

	private IModelElement fElement;
	
	/**
	 * Create a ModelElementFactory.  
	 */
	public PersistableModelElementFactory() {
	}

	/**
	 * Create a ModelElementFactory.  This constructor is typically used
	 * for our IPersistableElement side.
	 */
	public PersistableModelElementFactory(IModelElement element) {
		fElement= element;
	}

	/*
	 * @see IElementFactory
	 */
	public IAdaptable createElement(IMemento memento) {
	
		String identifier= memento.getString(KEY);
		if (identifier != null) {
			return DLTKCore.create(identifier);
		}
		return null;
	}
	
	/*
	 * @see IPersistableElement.
	 */
	public String getFactoryId() {
		return FACTORY_ID;
	}
	/*
	 * @see IPersistableElement
	 */
	public void saveState(IMemento memento) {
		memento.putString(KEY, fElement.getHandleIdentifier());
	}
}
