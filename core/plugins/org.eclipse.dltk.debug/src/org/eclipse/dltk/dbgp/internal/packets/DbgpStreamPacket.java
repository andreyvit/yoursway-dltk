package org.eclipse.dltk.dbgp.internal.packets;

public class DbgpStreamPacket {
	private static final String STDERR = "stderr";

	private static final String STDOUT = "stdout";

	private String type;

	private String content;

	public DbgpStreamPacket(String type, String content) {
		if (!STDERR.equalsIgnoreCase(type) && !STDOUT.equalsIgnoreCase(type)) {
			throw new IllegalArgumentException("Invalid type value");
		}

		if (content == null) {
			throw new IllegalArgumentException("Content cannot be null");
		}

		this.type = type;
		this.content = content;
	}

	public boolean isStdout() {
		return STDOUT.equalsIgnoreCase(type);
	}

	public boolean isStderr() {
		return STDERR.equalsIgnoreCase(type);
	}

	public String getContent() {
		return content;
	}

	public String toString() {
		return "DbgpStreamPacket (Type: " + type + "; Content: " + content
				+ ";)";
	}
}
