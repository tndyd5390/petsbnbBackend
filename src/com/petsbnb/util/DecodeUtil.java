package com.petsbnb.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class DecodeUtil {

	public static List<HashMap<Object,Object>> decodeName(List<HashMap<Object,Object>> listMap) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
		List<HashMap<Object,Object>> rsltMap = new ArrayList<HashMap<Object,Object>>();
		
		for(HashMap<Object, Object> hMap : listMap) {
			String decodeName = AES256Util.strDecode(hMap.get("userName").toString());
			hMap.put("userName", decodeName);
			rsltMap.add(hMap);
		}
		
		return rsltMap;
	}
}
