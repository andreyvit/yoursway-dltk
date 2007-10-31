package org.eclipse.dltk.ui.browsing.ext;

/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Sash;

public class ColumnForm extends Composite {

	public int SASH_WIDTH = 3;

	int columnStyle;
	Sash[] sashes = new Sash[0];
	// Remember background and foreground
	// colors to determine whether to set
	// sashes to the default color (null) or
	// a specific color
	Color background = null;
	Color foreground = null;
	Control[] controls = new Control[0];
	Listener sashListener;
	static final int DRAG_MINIMUM = 20;

	public ColumnForm(Composite parent, int style) {
		super(parent, checkStyle(style));
		super.setLayout(new ColumnFormLayout());
		columnStyle = SWT.VERTICAL;
		if ((style & SWT.BORDER) != 0)
			columnStyle |= SWT.BORDER;
		if ((style & SWT.SMOOTH) != 0)
			columnStyle |= SWT.SMOOTH;
		sashListener = new Listener() {
			public void handleEvent(Event e) {
				onDragSash(e);
			}
		};
	}

	static int checkStyle(int style) {
		int mask = SWT.BORDER | SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT;
		return style & mask;
	}

	public int getStyle() {
		int style = super.getStyle();
		style |= SWT.HORIZONTAL;
		if ((columnStyle & SWT.SMOOTH) != 0)
			style |= SWT.SMOOTH;
		return style;
	}

	Control[] getControls(boolean onlyVisible) {
		Control[] children = getChildren();
		Control[] result = new Control[0];
		for (int i = 0; i < children.length; i++) {
			if (children[i] instanceof Sash)
				continue;
			if (onlyVisible && !children[i].getVisible())
				continue;

			Control[] newResult = new Control[result.length + 1];
			System.arraycopy(result, 0, newResult, 0, result.length);
			newResult[result.length] = children[i];
			result = newResult;
		}
		return result;
	}

	void onDragSash(Event event) {
		Sash sash = (Sash) event.widget;
		int sashIndex = -1;
		for (int i = 0; i < sashes.length; i++) {
			if (sashes[i] == sash) {
				sashIndex = i;
				break;
			}
		}
		if (sashIndex == -1)
			return;

		Control c1 = controls[sashIndex];
//		Control c2 = controls[sashIndex + 1];
		Rectangle b1 = c1.getBounds();
//		Rectangle b2 = c2.getBounds();

		Rectangle sashBounds = sash.getBounds();
//		Rectangle area = getClientArea();
		boolean correction = false;

		correction = b1.width < DRAG_MINIMUM;
		
		int shift = event.x - sashBounds.x;
		Object data1 = c1.getLayoutData();
		if (data1 == null || !(data1 instanceof ColumnFormData)) {
			data1 = new ColumnFormData();
			c1.setLayoutData(data1);
		}
		((ColumnFormData) data1).width = b1.width + shift;
		b1.width = b1.width + shift;
		if (((ColumnFormData) data1).width < DRAG_MINIMUM) {
			((ColumnFormData) data1).width = DRAG_MINIMUM;
			event.x = b1.x + DRAG_MINIMUM;
			b1.width = DRAG_MINIMUM;
			event.doit = false;
		}

		if (correction || (event.doit && event.detail != SWT.DRAG)) {
			c1.setBounds(b1);
			sash.setBounds(event.x, event.y, event.width, event.height);
//			c2.setBounds(b2);
		}
		this.layout();
	}

	public void setBackground(Color color) {
		super.setBackground(color);
		background = color;
		for (int i = 0; i < sashes.length; i++) {
			sashes[i].setBackground(background);
		}
	}

	public void setForeground(Color color) {
		super.setForeground(color);
		foreground = color;
		for (int i = 0; i < sashes.length; i++) {
			sashes[i].setForeground(foreground);
		}
	}


	public void setLayout(Layout layout) {
		checkWidget();
		return;
	}
}
