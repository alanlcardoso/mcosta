package br.com.sistema.mcosta.util;

import java.util.Arrays;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

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
	
	public static InternetAddress[] toArrayAddress(List<String> listString) {
		InternetAddress[] addresses = new InternetAddress[listString.size()];
		
		for (int i = 0; i < listString.size(); i++) {
			try {
				addresses[i] = new InternetAddress(listString.get(i));
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}
		
		return addresses;
	}
	
	public static String[] toArrayString(List<String> listString) {
		return listString.toArray(new String[listString.size()]);
	}
	
	public static InternetAddress toAddress(String item) {
		try {
			return new InternetAddress(item);
		} catch (AddressException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
