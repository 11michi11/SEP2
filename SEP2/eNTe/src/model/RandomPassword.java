package model;

import java.security.SecureRandom;

public class RandomPassword {
	
	private static SecureRandom random = new SecureRandom();
	private static final String dic = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public static String generatePassword(int len) {
		String result = "";
		for (int i = 0; i < len; i++) {
			int index = random.nextInt(dic.length());
			result += dic.charAt(index);
		}
		return result;
	}
}
