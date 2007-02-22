package org.eclipse.dltk.dbgp;

import org.w3c.dom.Element;

public interface IDbgpNotification {
	String getName();

	Element getBody();
}
