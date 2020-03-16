package com.wrd.cms.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @ClassName: Md5Util 
 * @Description:对密码进行加密处理
 * @author: 瑞
 * @date: 2020年3月12日 上午9:51:56
 */

public class Md5Util {

	private static String salt="qazwsx1234";
	
	public static String encode(String password){
		
		return DigestUtils.md5Hex(password);
	}
	public static void main(String[] args) {
		Md5Util.encode("123456");
	}
}
