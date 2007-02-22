package org.eclipse.dltk.dbgp.internal;

import org.eclipse.dltk.dbgp.IDbgpNotification;
import org.w3c.dom.Element;

public class DbgpNotification implements IDbgpNotification {
	private Element body;

	private String name;

	public DbgpNotification(String name, Element body) {
		this.body = body;
		this.name = name;
	}

	public Element getBody() {
		return body;
	}

	public String getName() {
		return name;
	}

}
