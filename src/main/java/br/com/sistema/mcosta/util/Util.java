package br.com.sistema.mcosta.util;

import java.util.Arrays;

public class Util {

	public static byte[] toPrimitives(Byte[] oBytes) {
		byte[] bytes = new byte[oBytes.length];
		for (int i = 0; i < oBytes.length; i++) {
			bytes[i] = oBytes[i];
		}
		return bytes;
	}

	public static Byte[] toObjects(byte[] bytesPrim) {
		Byte[] bytes = new Byte[bytesPrim.length];
		Arrays.setAll(bytes, n -> bytesPrim[n]);
		return bytes;
	}
}
