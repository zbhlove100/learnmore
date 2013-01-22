package utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {	
		
	
	public static String encode(String password) {
		String afterStr = null;
		BASE64Encoder encoder = new BASE64Encoder();
		
		if (password != null) {
			afterStr = encoder.encode(password.getBytes());
		}
		return afterStr;
	}

	public static String decode(String password) {
		byte[] afterByte = null;
		BASE64Decoder decoder = new BASE64Decoder();
		
		if (password != null) {
			try {
				afterByte = decoder.decodeBuffer(password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new String(afterByte);
	}

}
