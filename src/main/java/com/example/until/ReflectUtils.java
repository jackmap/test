package com.example.until;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * 
 *
 * @Description:反射工具类
 * @author:     lijianzhou
 * @date:       2016年10月12日
 * Copyright (c) 2016, Sutu. All rights reserved
 */
public class ReflectUtils {
	
	/**
	 * 
	 * @Title:        getAllField 
	 * @Description:  获取类clazz的所有Field，包括其父类的Field，如果重名，以子类Field为准。
	 * @param:        @param clazz
	 * @param:        @return    
	 * @return:       Field[]  Field数组 
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午8:23:48
	 */
    public static Field[] getAllField(Class<?> clazz) {
        ArrayList<Field> fieldList = new ArrayList<Field>();
        Field[] dFields = clazz.getDeclaredFields();
        if (null != dFields && dFields.length > 0) {
            fieldList.addAll(Arrays.asList(dFields));
        }
 
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != Object.class) {
            Field[] superFields = getAllField(superClass);
            if (null != superFields && superFields.length > 0) {
                for(Field field:superFields){
                    if(!isContain(fieldList, field)){
                        fieldList.add(field);
                    }
                }
            }
        }
        Field[] result=new Field[fieldList.size()];
        fieldList.toArray(result);
        return result;
    }
     
    /**
     * 
     * @Title:        isContain 
     * @Description:  检测Field List中是否已经包含了目标field 
     * @param:        @param fieldList
     * @param:        @param field 带检测field
     * @param:        @return    
     * @return:       boolean    
     * @author        lijianzhou
     * @Date          2016年10月12日 下午8:24:11
     */
    public static boolean isContain(ArrayList<Field> fieldList,Field field){
        for(Field temp:fieldList){
            if(temp.getName().equals(field.getName())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * @Title:        getGetMethod 
     * @Description:  java反射bean的get方法   
     * @param:        @param objectClass
     * @param:        @param fieldName
     * @param:        @return    
     * @return:       Method    
     * @author        lijianzhou
     * @Date          2016年10月12日 下午8:24:37
     */
    @SuppressWarnings("unchecked")       
    public static Method getGetMethod(Class objectClass, String fieldName) {       
        StringBuffer sb = new StringBuffer();       
        sb.append("get");       
        sb.append(fieldName.substring(0, 1).toUpperCase());       
        sb.append(fieldName.substring(1));       
        try {       
            return objectClass.getMethod(sb.toString());       
        } catch (Exception e) {       
        }       
        return null;       
    }       
    
    /**
     *      
     * @Title:        getMethods 
     * @Description:  java反射bean的get方法  
     * @param:        @param map
     * @param:        @param objectClass
     * @param:        @return    
     * @return:       Map<String,Method>    
     * @author        lijianzhou
     * @Date          2016年10月12日 下午8:24:49
     */
    @SuppressWarnings("unchecked")       
    public static  Map< String, Method > getMethods(Map< String, Method > map,Class objectClass) {    
    	Method[] methods = objectClass.getMethods();
    	for(Method method:methods){
    		if(map==null) map = new HashMap<String, Method>();
    		map.put(method.getName(), method);
    	}
    	if(objectClass.getSuperclass()==null) return map;
        return ReflectUtils.getMethods(map, objectClass.getSuperclass());
    }       
    
    /**
     * 
     * @Title:        getSetMethod 
     * @Description:  java反射bean的set方法   
     * @param:        @param objectClass
     * @param:        @param fieldName
     * @param:        @return    
     * @return:       Method    
     * @author        lijianzhou
     * @Date          2016年10月12日 下午8:25:06
     */
    @SuppressWarnings("unchecked")       
    public static Method getSetMethod(Class objectClass, String fieldName) {       
        try {       
            Class[] parameterTypes = new Class[1];  
            Map<String, Field> map = ReflectUtils.getParentClassFields(null, objectClass);
            //Field field = objectClass.getDeclaredField(fieldName);  
            Field field = map.get(fieldName);
            parameterTypes[0] = field.getType();       
            StringBuffer sb = new StringBuffer();       
            sb.append("set");       
            sb.append(fieldName.substring(0, 1).toUpperCase());       
            sb.append(fieldName.substring(1));  
            Method method = objectClass.getMethod(sb.toString(), parameterTypes);       
            return method;       
        } catch (Exception e) {       
            e.printStackTrace();       
        }       
        return null;       
    }       
      
    /**
     * 
     * @Title:        invokeSet 
     * @Description:  执行set方法   
     * @param:        @param o
     * @param:        @param fieldName
     * @param:        @param value    
     * @return:       void    
     * @author        lijianzhou
     * @Date          2016年10月12日 下午8:25:19
     */
    public static void invokeSet(Object o, String fieldName, Object value) { 
    	StringBuffer sb = new StringBuffer();  
    	sb.append("set");       
        sb.append(fieldName.substring(0, 1).toUpperCase());       
        sb.append(fieldName.substring(1));  
        Map<String, Method> map = ReflectUtils.getMethods(null, o.getClass());
        //Method method = getSetMethod(o.getClass(), fieldName);    
        Method method = map.get(sb.toString());
        try {       
            method.invoke(o, new Object[] { value });       
        } catch (Exception e) {       
            e.printStackTrace();       
        }       
    }       
    
    /**
     * 
     * @Title:        invokeGet 
     * @Description:  执行get方法   
     * @param:        @param o
     * @param:        @param fieldName
     * @param:        @return    
     * @return:       Object    
     * @author        lijianzhou
     * @Date          2016年10月12日 下午8:25:39
     */
    public static Object invokeGet(Object o, String fieldName) {       
        Method method = getGetMethod(o.getClass(), fieldName);       
        try {       
            return method.invoke(o, new Object[0]);       
        } catch (Exception e) {       
            e.printStackTrace();       
        }       
        return null;       
    }  
    
    /**
     * 
     * @Title:        getParentClassFields 
     * @Description:  获取类实例的父类的属性值 
     * @param:        @param map 类实例的属性值Map
     * @param:        @param clazz 类名
     * @param:        @return  类名.属性名=属性类型
     * @return:       Map<String,Field>    
     * @author        lijianzhou
     * @Date          2016年10月12日 下午8:25:47
     */
	private static Map< String, Field > getParentClassFields ( Map< String, Field > map, Class clazz ){
		Field[] fields = clazz.getDeclaredFields ( );
		for ( Field field : fields )
		{
			if( map==null ) map = new HashMap<String, Field>();
			map.put ( field.getName( ) ,field );
		}
		if ( clazz.getSuperclass ( ) == null )
		{
			return map;
		}
		getParentClassFields ( map, clazz.getSuperclass ( ) );
		return map;
	}
	
    /**
     * 
     * @Title:        getattributeObj 
     * @Description:  获取对象的属性值
     * @param:        @param obj
     * @param:        @param attribute
     * @param:        @param val
     * @param:        @return
     * @param:        @throws NoSuchFieldException
     * @param:        @throws SecurityException
     * @param:        @throws ParseException    
     * @return:       Object    
     * @author        lijianzhou
     * @Date          2016年10月12日 下午8:26:29
     */
//    public static Object getattributeObj(Object obj, String attribute,Object val) throws NoSuchFieldException, SecurityException, ParseException  {   
//    	 Class<?>  objectClass = obj.getClass();
//    	 Map< String, Field > map = ReflectUtils.getParentClassFields(null, objectClass);
//    	 //Field field = objectClass.getDeclaredField(attribute);   
//    	 Field field = map.get(attribute) ;
//    	 if(field == null) return null;
//    	 Class<?> type = field.getType();
//    	 if(!type.isInstance(val)){
//				if(type.getName().equals("java.lang.Long")) {
//					val = new Long(val.toString()) ;
//				}
//				if(type.getName().equals("java.lang.Integer")) {
//					val = new Integer(val.toString()) ;
//				}
//				if(type.getName().equals("java.lang.Short") ) {
//					val = new Short(val.toString()) ;
//				}
//				if(type.getName().equals("java.util.Date")) {
//					String rule = "\\d{1,4}-\\d{1,2}-\\d{1,2}";
//					if(Pattern.compile( rule  ).matcher(val.toString()).matches()){
//						return DateUtil1.parseDate(val.toString(), "yyyy-MM-dd");
//					}
//					rule = "\\d{1,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";
//					if(Pattern.compile( rule  ).matcher(val.toString()).matches()){
//						return DateUtil1.parseDate(val.toString(), "yyyy-MM-dd HH:mm:ss");
//					}
//					rule = "\\d{1,4}/\\d{1,2}/\\d{1,2}";
//					if(Pattern.compile( rule  ).matcher(val.toString()).matches()){
//						return DateUtil1.parseDate(val.toString(), "yyyy/MM/dd");
//					}
//					rule = "\\d{1,4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";
//					if(Pattern.compile( rule  ).matcher(val.toString()).matches()){
//						return DateUtil1.parseDate(val.toString(), "yyyy/MM/dd HH:mm:ss");
//					}
//				}
//				if(type.getName().equals("java.math.BigDecimal")) {
//					val = new BigDecimal(val.toString()) ;
//				}
//    	}
//    	return val;
//    }  
//    
//    public static void main(String[] args) throws Exception {
//    	  TErpOrders o = new TErpOrders();
//    	  String attribute =  "payTime";
//    	  String va = "2016-12-12 12:12:12";
//    	  Object val = ReflectUtils.getattributeObj(o, attribute, va);
//    	  ReflectUtils.invokeSet(o, attribute, val);
//    }
//    	 
}
