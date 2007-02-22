package org.eclipse.dltk.dbgp.internal.packets;

import org.w3c.dom.Element;

public class DbgpPacket {
	private Element content;

	protected DbgpPacket(Element content) {
		if (content == null) {
			throw new IllegalArgumentException();
		}

		this.content = content;
	}

	public Element getContent() {
		return this.content;
	}
}
