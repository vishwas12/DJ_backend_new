package com.dj.app.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.regex.Pattern;

public class CommonUtils {

	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	public static boolean matchEmail(String test) {
		return EMAIL_PATTERN.matcher((CharSequence) Optional.ofNullable(test).orElse("")).matches();
	}

	public static String encodeToMD5(String msg) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(msg.getBytes("UTF8"));
		byte s[] = m.digest();
		String result = "";
		for (int i = 0; i < s.length; i++) {
			result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
		}

		return result;
	}
}
