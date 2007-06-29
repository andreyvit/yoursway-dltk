package org.eclipse.dltk.rhino.dbgp;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Helper {
	private static final BASE64Encoder encoder = new BASE64Encoder();

	private static final BASE64Decoder decoder = new BASE64Decoder();

	public static String encodeString(String s) {
		if (s==null)return"";
		return encoder.encode(s.getBytes());
	}

	public static String decodeString(String base64){
		try {
			if (base64==null)return "";
			return new String(decoder.decodeBuffer(base64));
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	public static String encodeBytes(byte[] bytes) {
		return new String(encoder.encode(bytes));
	}

	public static byte[] decodeBytes(String base64) throws DbgpIOException {
		try {
			return decoder.decodeBuffer(base64);
		} catch (IOException e) {
			throw new DbgpIOException(e);
		}
	}
}
