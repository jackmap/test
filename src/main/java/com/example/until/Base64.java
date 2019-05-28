package com.example.until;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 *
 * @Description:Base64编码工具
 * @author:     lijianzhou
 * @date:       2016年10月12日
 * Copyright (c) 2016, Sutu. All rights reserved
 */
public class Base64 {

    /**
     * 
     * @Title:        getBase64 
     * @Description:  将字符串进行BASE64编码 
     * @param:        @param content
     * @param:        @return    
     * @return:       String    
     * @author        lijianzhou
     * @Date          2016年10月12日 下午7:51:43
     */
    public static String getBase64(String content) {
        String result = null;
        if (content == null) {
            return result;
        }
        BASE64Encoder encoder = new BASE64Encoder();
        result = encoder.encode(content.getBytes());
        return result;
    }

    /**
     * 
     * @Title:        getFromBase64 
     * @Description:  将BASE64编码的字符串进行解码 
     * @param:        @param content
     * @param:        @return    
     * @return:       String    
     * @author        lijianzhou
     * @Date          2016年10月12日 下午7:51:54
     */
    public static String getFromBase64(String content) {
        String result = null;
        if (content == null) {
            return result;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(content);
            result = new String(b);
        } catch (Exception ex) {
            result = null;
        }
        return result;
    }
}
