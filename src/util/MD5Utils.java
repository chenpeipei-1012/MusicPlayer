package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 工具类：封装了MD5加密算法
 * @author 华为MateBook 13
 *
 */
public class MD5Utils {

	public static String  encryptToMD5(String info) {
		byte[] digesta = null;
		try {
			// 得到一个md5的消息摘要
			MessageDigest alga = MessageDigest.getInstance("MD5");
			
			// 添加要进行计算摘要的信息
			alga.update(info.getBytes());
			// 得到该摘要: 二进制的
			digesta = alga.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// 将摘要转为字符串
		String rs = byte2hex(digesta);
		return rs;
	}
	
	/**
	 * 将二进制转化为16进制字符串
	 * 
	 * @param b 二进制字节数组
	 * @return String
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}
	
	// 测试
	public static void main(String args []){
		String str = MD5Utils.encryptToMD5("123456");
		System.out.println(str);
	}

}
