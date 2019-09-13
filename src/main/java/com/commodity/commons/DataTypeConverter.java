package com.commodity.commons;

public class DataTypeConverter {

	public static byte[] stringTo32Byte(String string) {
		byte[] byteValueLen32 = new byte[32];

		if (string != null) {
			byte[] byteValue = string.getBytes();
			System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
		}
		return (byteValueLen32);
	}

	 public static String convertToUTF8(String s) {
	        String out = null;
	        try {
	            out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
	        } catch (java.io.UnsupportedEncodingException e) {
	            return null;
	        }
	        return out;
	    }
	 

	public static String  Bytes32toString(byte[] bytes) {
		
		 String string = new String(bytes);
		
		return string;
	}

	// String to 64 length HexString (equivalent to 32 Hex lenght)

	
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
} 