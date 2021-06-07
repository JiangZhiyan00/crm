package com.jiangzhiyan.crm.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

public class UserIdBase64 {

	/**
	 * 解密id
	 * @param encodedUserId cookie中加密的userId
	 * @return 真实id
	 */
	public static Integer decoderUserId(String encodedUserId)  {
		if (StringUtils.isBlank(encodedUserId)) {
			return null;
		}
		try {
			String reversedString = new StringBuffer(encodedUserId).reverse().toString();
			String base64String = reversedString.replaceAll("#", "=");
			int userIdPos = base64String.indexOf("==") + 6;
			String realBase64UserId = base64String.substring(userIdPos);
			String base64Encoded = new String(Base64.getDecoder().decode(realBase64UserId.getBytes()));
			return Integer.parseInt(base64Encoded);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 加密id
	 * @param userId 真实id
	 * @return 加密的id
	 */
	public static String encoderUserId(Integer userId){
		String base64UserIdEncoded = Base64.getEncoder().encodeToString((userId + "").getBytes());
		String currentStringBase64Encoded = Base64.getEncoder().encodeToString((System.currentTimeMillis() + "").getBytes());
		String keyString = currentStringBase64Encoded  
				+ currentStringBase64Encoded.substring(4, 8) + base64UserIdEncoded;
		byte[] codeBytes = keyString.getBytes();
		byte[] orderBytes = new byte[codeBytes.length];
		for(int i=0; i<codeBytes.length; i++){
			orderBytes[i] = codeBytes[codeBytes.length-i-1];
		}
		return new String(orderBytes).replaceAll("=", "#");
	}

	public static void main(String[] args) {
		System.out.println(encoderUserId(42));
		System.out.println(decoderUserId("#IDN1gTM##ANzgTNzUDN1gTMyYTM"));
	}
}