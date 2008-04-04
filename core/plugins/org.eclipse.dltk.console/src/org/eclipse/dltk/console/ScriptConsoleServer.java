/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.console;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ScriptConsoleServer implements Runnable {

	public static final int DEFAULT_PORT = 25000;

	protected static final boolean DEBUG = false;

	private static ScriptConsoleServer instance;

	public static ScriptConsoleServer getInstance() {
		if (instance == null) {
			instance = new ScriptConsoleServer();
		}
		return instance;
	}

	private int port;

	private final Map handlers;

	protected ScriptConsoleServer() {
		this.port = DEFAULT_PORT;

		// check for not used port
		while (true) {
			try {
				ServerSocket s = new ServerSocket(this.port);
				if (!s.isBound()) {
					this.port++;
				}
				else {
					s.close();
					break;
				}
			} catch (IOException e) {
				if (DEBUG) {
					e.printStackTrace();
				}
			}
		}

		handlers = new HashMap();

		(new Thread(this)).start();
	}

	public String register(ConsoleRequest request) {
		String id = new Long(System.currentTimeMillis()).toString();
		register(id, request);
		return id;
	}

	public void register(String id, ConsoleRequest request) {
		synchronized (handlers) {
			handlers.put(id, request);
			handlers.notifyAll();
		}
	}

	public int getPort() {
		return port;
	}

	public void run() {
		try {
			ServerSocket server = new ServerSocket(port);

			while (true) {
				final Socket client = server.accept();
				client.setSoTimeout(30000);

				Thread clientHandler = new Thread(new Runnable() {
					public void run() {
						try {
							SocketScriptConsoleIO proxy = new SocketScriptConsoleIO(
									client);

							String id = proxy.getId();

							ConsoleRequest request = null;

							synchronized (handlers) {
								request = (ConsoleRequest) handlers.get(id);
								while (request == null) {
									try {
										handlers.wait();
									} catch (InterruptedException e) {

									}
								}

								handlers.remove(id);
							}

							request.consoleConnected(proxy);
						} catch (IOException e) {
							if (DEBUG) {
								e.printStackTrace();
							}
						}
					}
				});

				clientHandler.start();
			}
		} catch (IOException e) {
			if (DEBUG) {
				e.printStackTrace();
			}
		}
	}
}
