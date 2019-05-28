/**
 * 
 */
package com.example.until;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @Description:XmlUtil 工具类
 * @author:     lijianzhou
 * @date:       2016年4月14日
 * Copyright (c) 2016, Sutu. All rights reserved
 */
public class XmlUtil {
	
	/**
	 * 
	 * @Title:        getNodeValue 
	 * @Description:  根据属性名获取文档属性值
	 * @param:        @param response
	 * @param:        @param nodeName
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午8:13:20
	 */
    public static String getNodeValue(Document response,String nodeName) throws Exception{
        if (response != null) {
            NodeList bufferNodes = response.getElementsByTagName(nodeName);
            String val = bufferNodes.item(0).getFirstChild().getNodeValue();
            return val;    
        }else {
           return null;
        }
    }
}
