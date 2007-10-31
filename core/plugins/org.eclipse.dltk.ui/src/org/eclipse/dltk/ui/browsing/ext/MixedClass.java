/**
 * 
 */
package org.eclipse.dltk.ui.browsing.ext;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

public class MixedClass {
	private List elements = new ArrayList();
	private String name;
	private Image icon;

	public List getElements() {
		return elements;
	}

	public void setElements(List elements) {
		this.elements = elements;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getIcon() {
		return icon;
	}

	public void setIcon(Image icon) {
		this.icon = icon;
	}

}