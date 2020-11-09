package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * �����ࣺ��װ��MD5�����㷨
 * @author ��ΪMateBook 13
 *
 */
public class MD5Utils {

	public static String  encryptToMD5(String info) {
		byte[] digesta = null;
		try {
			// �õ�һ��md5����ϢժҪ
			MessageDigest alga = MessageDigest.getInstance("MD5");
			
			// ���Ҫ���м���ժҪ����Ϣ
			alga.update(info.getBytes());
			// �õ���ժҪ: �����Ƶ�
			digesta = alga.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// ��ժҪתΪ�ַ���
		String rs = byte2hex(digesta);
		return rs;
	}
	
	/**
	 * ��������ת��Ϊ16�����ַ���
	 * 
	 * @param b �������ֽ�����
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
	
	// ����
	public static void main(String args []){
		String str = MD5Utils.encryptToMD5("123456");
		System.out.println(str);
	}

}
