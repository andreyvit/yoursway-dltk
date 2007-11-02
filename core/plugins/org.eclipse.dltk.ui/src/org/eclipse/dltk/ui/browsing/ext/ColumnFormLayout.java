package org.eclipse.dltk.ui.browsing.ext;

/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Sash;

/**
 * This class provides the layout for SashForm
 * 
 * @see ColumnForm
 */
class ColumnFormLayout extends Layout {
	protected Point computeSize(Composite composite, int wHint, int hHint,
			boolean flushCache) {
		ColumnForm sashForm = (ColumnForm) composite;
		Control[] cArray = sashForm.getControls(true);
		int width = 0;
		int height = 0;
		if (cArray.length == 0) {
			if (wHint != SWT.DEFAULT)
				width = wHint;
			if (hHint != SWT.DEFAULT)
				height = hHint;
			return new Point(width, height);
		}
		// determine control sizes
		// int maxIndex = 0;
		int maxValue = 0;
		for (int i = 0; i < cArray.length; i++) {

			Point size = cArray[i].computeSize(SWT.DEFAULT, hHint, flushCache);
			if (size.x > maxValue) {
				// maxIndex = i;
				maxValue = size.x;
			}
			height = Math.max(height, size.y);

		}
		// get the ratios
		width += sashForm.getBorderWidth() * 2;
		height += sashForm.getBorderWidth() * 2;
		if (wHint != SWT.DEFAULT)
			width = wHint;
		if (hHint != SWT.DEFAULT)
			height = hHint;
		return new Point(width, height);
	}

	protected boolean flushCache(Control control) {
		return true;
	}

	protected void layout(Composite composite, boolean flushCache) {
		ColumnForm columnForm = (ColumnForm) composite;
		Rectangle area = columnForm.getClientArea();
		if (area.width <= 1 || area.height <= 1)
			return;

		Control[] newControls = columnForm.getControls(true);
		if (columnForm.controls.length == 0 && newControls.length == 0) {
			Rectangle oldBounds = columnForm.getBounds();
			Rectangle bounds = columnForm.getParent().getBounds();
			if (bounds.width != 0) {
				columnForm.setBounds(oldBounds.x, oldBounds.y, bounds.width,
						oldBounds.height);
			}
			return;
		}
		columnForm.controls = newControls;

		Control[] controls = columnForm.controls;

		// keep just the right number of sashes
		if (columnForm.sashes.length < controls.length) {
			Sash[] newSashes = new Sash[controls.length];
			System.arraycopy(columnForm.sashes, 0, newSashes, 0,
					columnForm.sashes.length);
			for (int i = columnForm.sashes.length; i < newSashes.length; i++) {
				newSashes[i] = new Sash(columnForm, columnForm.columnStyle);
				newSashes[i].setBackground(columnForm.background);
				newSashes[i].setForeground(columnForm.foreground);
				newSashes[i]
						.addListener(SWT.Selection, columnForm.sashListener);
			}
			columnForm.sashes = newSashes;
		}
		if (columnForm.sashes.length > controls.length) {
			if (controls.length == 0) {
				for (int i = 0; i < columnForm.sashes.length; i++) {
					columnForm.sashes[i].dispose();
				}
				columnForm.sashes = new Sash[0];
			} else {
				Sash[] newSashes = new Sash[controls.length];
				System.arraycopy(columnForm.sashes, 0, newSashes, 0,
						newSashes.length);
				for (int i = controls.length; i < columnForm.sashes.length; i++) {
					columnForm.sashes[i].dispose();
				}
				columnForm.sashes = newSashes;
			}
		}
		if (controls.length == 0)
			return;
		Sash[] sashes = columnForm.sashes;

		int[] widths = new int[controls.length];
		int total = 0;
		for (int i = 0; i < controls.length; i++) {
			Object data = controls[i].getLayoutData();
			if (data != null && data instanceof ColumnFormData) {
				widths[i] = ((ColumnFormData) data).width;
			} else {
				data = new ColumnFormData();
				controls[i].setLayoutData(data);
				((ColumnFormData) data).width = widths[i] = 200;

			}
			total += widths[i];
		}
		int sashwidth = sashes.length > 0 ? columnForm.SASH_WIDTH
				+ sashes[0].getBorderWidth() * 2 : columnForm.SASH_WIDTH;

		// composite.setSize(total, composite.getSize().y);

		int width = (int) (widths[0]);
		int x = area.x;
		controls[0].setBounds(x, area.y, width, area.height);
		x += width;
		for (int i = 1; i < controls.length; i++) {
			sashes[i - 1].setBounds(x, area.y, sashwidth, area.height);
			x += sashwidth;
			width = (int) (widths[i]);
			controls[i].setBounds(x, area.y, width, area.height);
			x += width;
		}
		if (controls.length > 0) {
			sashes[controls.length - 1].setBounds(x, area.y, sashwidth,
					area.height);
		}

		// we need to set control width to correct value of total space.
		Rectangle oldBounds = columnForm.getBounds();
		Rectangle bounds = columnForm.getParent().getBounds();
		if (bounds.width < x + sashwidth + 3) {
			columnForm.setBounds(oldBounds.x, oldBounds.y, x + sashwidth + 3,
					oldBounds.height);
		} else {
			columnForm.setBounds(oldBounds.x, oldBounds.y, bounds.width,
					oldBounds.height);
		}
		// we need update with to parent size.
	}

}
