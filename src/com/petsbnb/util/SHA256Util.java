package com.petsbnb.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class SHA256Util {
	
	public static String sha256(String str) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		String aes = AES256Util.strEncode("clinfo16");
		String SHA256 = "";
		str = aes + str;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256"); 
			md.update(str.getBytes()); 
			byte byteData[] = md.digest(); 
			StringBuffer sb = new StringBuffer(); 
			for (int i = 0; i < byteData.length; i++) { 
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1)); 
			}
			SHA256 = sb.toString();
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
			SHA256 = null;
		}
		return SHA256;
	}
}
