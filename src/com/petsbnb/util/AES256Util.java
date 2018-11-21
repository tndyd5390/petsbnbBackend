package com.petsbnb.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AES256Util {
	/* 
	Copyright ȸ���  

	Licensed under the Apache License, Version 2.0 (the "License"); 
	you may not use this file except in compliance with the License. 
	You may obtain a copy of the License at 

	    http://www.apache.org/licenses/LICENSE-2.0 

	Unless required by applicable law or agreed to in writing, software 
	distributed under the License is distributed on an "AS IS" BASIS, 
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
	See the License for the specific language governing permissions and 
	limitations under the License. 
	*/ 

	 public static byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
	 private static String key = "epdlxjqnstjrrhk!";
	 /**
	  * �Ϲ� ���ڿ��� ������ Ű�� �̿��Ͽ� AES256 ���� ��ȣȭ
	  * @param  String - ��ȣȭ ��� ���ڿ�
	  * @param  String - ���ڿ� ��ȣȭ�� ���� Ű
	  * @return String - key �� ��ȣȭ��  ���ڿ� 
	  * @exception 
	  */
	 
	 
	 public static String strEncode(String str) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	  
	  byte[] textBytes = str.getBytes("UTF-8");
	  AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
	  SecretKeySpec newKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	  Cipher cipher = null;
	  cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	  cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
	  return Base64.encodeBase64String(cipher.doFinal(textBytes));
	 }
	 
	 /**
	  * ��ȣȭ�� ���ڿ��� ������ Ű�� �̿��Ͽ� AES256 ���� ��ȣȭ
	  * @param  String - ��ȣȭ ��� ���ڿ�
	  * @param  String - ���ڿ� ��ȣȭ�� ���� Ű
	  * @return String - key �� ��ȣȭ��  ���ڿ� 
	  * @exception 
	  */ 
	 public static String strDecode(String str) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	  
	  byte[] textBytes = Base64.decodeBase64(str);
	  //byte[] textBytes = str.getBytes("UTF-8");
	  AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
	  SecretKeySpec newKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	  Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	  cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
	  return new String(cipher.doFinal(textBytes), "UTF-8");
	 }
}
