/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal.packets;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;

import org.eclipse.dltk.dbgp.internal.DbgpRawPacket;
import org.eclipse.dltk.dbgp.internal.DbgpWorkingThread;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlPacketParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DbgpPacketReceiver extends DbgpWorkingThread {
	private static class ResponcePacketWaiter {
		private static final int MIN_TIMEOUT = 5;
		private final HashMap map;
		private boolean terminated;

		public ResponcePacketWaiter() {
			map = new HashMap();
			terminated = false;
		}

		public synchronized void put(DbgpResponsePacket packet) {
			int id = packet.getTransactionId();
			map.put(new Integer(id), packet);
			notifyAll();
		}

		public synchronized DbgpResponsePacket waitPacket(int id, int timeout)
				throws InterruptedException {
			Integer key = new Integer(id);
			while (!terminated && !map.containsKey(key)) {
				if (timeout != 0 && timeout < MIN_TIMEOUT) {
					break;
				}
				long begin = System.currentTimeMillis();

				wait(timeout);

				long delta = System.currentTimeMillis() - begin;
				if (timeout != 0) {
					timeout -= delta;
				}
			}

			if (map.containsKey(key)) {
				return (DbgpResponsePacket) map.remove(key);
			}
			
			if (terminated) {
				throw new InterruptedException();
			}				
			
			return null;
		}

		public synchronized void terminate() {
			terminated = true;
			notifyAll();
		}
	}

	private static class PacketWaiter {
		private final LinkedList queue;
		private boolean terminated;

		public PacketWaiter() {
			terminated = false;
			this.queue = new LinkedList();
		}

		public synchronized void put(DbgpPacket obj) {
			queue.addLast(obj);
			notifyAll();
		}

		public synchronized DbgpPacket waitPacket() throws InterruptedException {
			while (!terminated && queue.isEmpty()) {
				wait();
			}
			
			if (terminated) {
				throw new InterruptedException();
			}							
			
			return (DbgpPacket) queue.removeFirst();
		}

		public synchronized void terminate() {
			terminated = true;
			notifyAll();
		}
	}

	private static final String INIT_TAG = "init";
	private static final String RESPONSE_TAG = "response";
	private static final String STREAM_TAG = "stream";
	private static final String NOTIFY_TAG = "notify";

	private final ResponcePacketWaiter responseWaiter;
	private final PacketWaiter notifyWaiter;
	private final PacketWaiter streamWaiter;

	private final InputStream input;
	private IDbgpRawLogger logger;

	protected void workingCycle() throws Exception {
		try {
			while (!Thread.interrupted()) {
				DbgpRawPacket packet = DbgpRawPacket.readPacket(input);

				if (logger != null) {
					logger.log(packet.toString());
				}

				addDocument(packet.getParsedXml());
			}
		} finally {
			responseWaiter.terminate();
			notifyWaiter.terminate();
			streamWaiter.terminate();
		}
	}

	protected void addDocument(Document doc) {
		Element element = (Element) doc.getFirstChild();
		String tag = element.getTagName();

		// TODO: correct init tag handling without this hack
		if (tag.equals(INIT_TAG)) {
			responseWaiter.put(new DbgpResponsePacket(element, -1));
		} else if (tag.equals(RESPONSE_TAG)) {
			DbgpResponsePacket packet = DbgpXmlPacketParser
					.parseResponsePacket(element);
			responseWaiter.put(packet);
		} else if (tag.equals(STREAM_TAG)) {
			streamWaiter.put(DbgpXmlPacketParser.parseStreamPacket(element));
		} else if (tag.equals(NOTIFY_TAG)) {
			notifyWaiter.put(DbgpXmlPacketParser.parseNotifyPacket(element));
		}
	}

	public DbgpNotifyPacket getNotifyPacket() throws InterruptedException {
		return (DbgpNotifyPacket) notifyWaiter.waitPacket();
	}

	public DbgpStreamPacket getStreamPacket() throws InterruptedException {
		return (DbgpStreamPacket) streamWaiter.waitPacket();
	}

	public DbgpResponsePacket getResponsePacket(int transactionId, int timeout)
			throws InterruptedException {
		return responseWaiter.waitPacket(transactionId, timeout);
	}

	public DbgpPacketReceiver(InputStream input) {
		super("DBGP - Packet receiver");

		if (input == null) {
			throw new IllegalArgumentException();
		}

		this.input = input;
		this.notifyWaiter = new PacketWaiter();
		this.streamWaiter = new PacketWaiter();
		this.responseWaiter = new ResponcePacketWaiter();
	}

	public void setLogger(IDbgpRawLogger logger) {
		this.logger = logger;
	}
}
