package org.eclipse.dltk.dbgp.internal.packets;

import org.w3c.dom.Element;

public class DbgpResponsePacket extends DbgpPacket {
	private int transactionId;

	public DbgpResponsePacket(Element element, int transactionId) {
		super(element);

		this.transactionId = transactionId;
	}

	public int getTransactionId() {
		return transactionId;
	}
}
