package org.eclipse.dltk.dbgp.internal;

import java.util.List;

import org.eclipse.dltk.dbgp.IDbgpProperty;

public class DbgpProperty implements IDbgpProperty {

	private String name;

	private String fullName;

	private String type;

	private String value;

	private int size;

	private int childrenCount;

	private List availableChildren;

	private boolean constant;

	public DbgpProperty(String name, String fullName, String type,
			String value, int size, int childrenCount, List availableChildren,
			boolean constant) {
		this.name = name;
		this.fullName = fullName;
		this.type = type;
		this.value = value;
		this.size = size;
		this.childrenCount = childrenCount;
		this.availableChildren = availableChildren;
		this.constant = constant;
	}

	public String getFullName() {
		return fullName;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getSize() {
		return size;
	}

	public boolean hasChildren() {
		return getChildrenCount() > 0;
	}

	public int getChildrenCount() {
		return childrenCount;
	}

	public List getAvailableChildren() {
		return availableChildren;
	}

	public boolean isConstant() {
		return constant;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("DbgpProperty (Name: " + name
				+ "; Full name: " + fullName + "; Type: " + type + "; Value: "
				+ value + ")");
		// Iterator iter = availableChildren.iterator();
		//		
		// while(iter.hasNext()){
		// sb.append('\n');
		// sb.append('\t');
		// sb.append(iter.next().toString());
		// }

		return sb.toString();
	}
}
