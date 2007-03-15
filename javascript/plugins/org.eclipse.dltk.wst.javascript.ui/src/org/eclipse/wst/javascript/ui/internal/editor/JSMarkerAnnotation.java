/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.editor;



import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.texteditor.MarkerAnnotation;

/**
 * JavaScript marker annotation enables breakpoint marker
 */
public class JSMarkerAnnotation extends MarkerAnnotation {
	//	private IDebugModelPresentation fPresentation;

	/**
	 * Constructor
	 * @param marker
	 */
	public JSMarkerAnnotation(IMarker marker) {
		super(marker);
	}

	/**
	 * Initializes the annotation's icon representation and its drawing layer
	 * based upon the properties of the underlying marker.
	 */
	protected void initialize() {
		// TODO : what are we doing here? can we remove this?
		//		IMarker marker = getMarker();
		//		if (MarkerUtilities.isMarkerType(marker, IBreakpoint.BREAKPOINT_MARKER)) {
		//			if (fPresentation == null) {
		//				fPresentation= DebugUITools.newDebugModelPresentation();
		//			}
		//				
		//			setLayer(4);
		//			setImage(fPresentation.getImage(marker));					
		//		} else {
		//			super.initialize();
		//		}
	}
}
