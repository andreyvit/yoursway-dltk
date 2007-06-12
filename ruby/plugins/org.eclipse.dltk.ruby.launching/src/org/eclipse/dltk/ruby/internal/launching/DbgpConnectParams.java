package org.eclipse.dltk.ruby.internal.launching;

public class DbgpConnectParams {
	private final String host;
	private final int port;
	private final String key;

	public DbgpConnectParams(String host, int port, String key) {
		super();
		this.host = host;
		this.port = port;
		this.key = key;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getKey() {
		return key;
	}
}
