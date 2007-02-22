package org.eclipse.dltk.dbgp.internal.packets;

import org.w3c.dom.Element;

public class DbgpNotifyPacket extends DbgpPacket {
	private String name;

	public DbgpNotifyPacket(Element content, String name) {
		super(content);

		if (name == null) {
			throw new IllegalArgumentException();
		}

		this.name = name;
	}

	public String getName() {
		return name;
	}
}
