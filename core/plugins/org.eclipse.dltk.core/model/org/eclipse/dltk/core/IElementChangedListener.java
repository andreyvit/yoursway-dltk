/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.core;

/**
 * An element changed listener receives notification of changes to script elements
 * maintained by the script model.
 * <p>
 * This interface may be implemented by clients.
 * </p>
 */
public interface IElementChangedListener {
	
/**
 * Notifies that one or more attributes of one or more script elements have changed.
 * The specific details of the change are described by the given event.
 *
 * @param event the change event
 */
public void elementChanged(ElementChangedEvent event);
}
