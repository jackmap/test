package com.example.until;

/**
 * 
 *
 * @Description:字符串转换工具类
 * @author:     lijianzhou
 * @date:       2016年10月12日
 * Copyright (c) 2016, Sutu. All rights reserved
 */
public class ChangeString {

	/**
	 * 
	 * @Title:        changeString 
	 * @Description:  替换单个字符串，驼峰转换下划线
	 * @param:        @param str 待替换字符串
	 * @param:        @param type 0：驼峰转下划线		1：下划线转驼峰
	 * @param:        @return    
	 * @return:       String    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午7:53:59
	 */
	public static String changeString(String str, int type){
		String newStr = "";
		//循环待替换的字符串
		for (int i = 0; i < str.length(); i++) {
			//截取每个字符做比对
			char c = str.charAt(i);
			boolean isRight = false;
			//判断转换类型
			if(type == 0){
				isRight = Character.isUpperCase(c);
			} else {
				isRight = '_' == c;
			}
			
			//判断是否需要替换
			if (isRight) {
				//替换字符
				if (type == 0){
					newStr += "_" + Character.toLowerCase(c);
				} else {
					//取下一位
					i += 1;
					if (i == str.length()) {
						break;
					}
					newStr += Character.toUpperCase(str.charAt(i));
				}
			} else {
				newStr += c;
			}
			
		}
		return newStr;
	}
	
	public static String captureName(String name){
		 char[] cs=name.toCharArray();
	     cs[0]-=32;
	     return String.valueOf(cs);
	}
	
	public static void main(String[] args) {
		System.out.println(captureName(changeString("BORDER".toLowerCase(),1)));
	}
}
