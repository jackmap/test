package com.example.until;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 
 *
 * @Description:日期转换工具类
 * @author:     lijianzhou
 * @date:       2016年10月12日
 * Copyright (c) 2016, Sutu. All rights reserved
 */
public class DateUtil {

	private static ThreadLocal<Map<String, SimpleDateFormat>> dfThreadLocal = new ThreadLocal<Map<String, SimpleDateFormat>>(){
		protected synchronized Map<String, SimpleDateFormat> initialValue() {
			SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat datetimeNumFormat=new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
			Map<String, SimpleDateFormat> map=new HashMap<String,SimpleDateFormat>();
			map.put("datetime", datetimeFormat);
			map.put("date", dateFormat);
			map.put("time", timeFormat);
			map.put("datetimeNum", datetimeNumFormat);
			return map;
		}
	};
	
	private static SimpleDateFormat getDatetimeDF() {
		  return dfThreadLocal.get().get("datetime");
	}
	
	private static SimpleDateFormat getDatetimeNumDF(){
		return dfThreadLocal.get().get("datetimeNum");
	}
	
	private static SimpleDateFormat getDateDF() {
		  return dfThreadLocal.get().get("date");
	}
	
	private static SimpleDateFormat getTimeDF() {
		  return dfThreadLocal.get().get("time");
	}
			
	public static Date now() {
		return new Date();
	}
	
	public static String formatDate(Date date) {
		return getDateDF().format(date);
	}

	public static String formatDatetime(Date date) {
		return getDatetimeDF().format(date);
	}
	
	public static String formatDatetimeNum(Date date) {
		return getDatetimeNumDF().format(date);
	}
	
	public static String formatTime(Date date) {
		return getTimeDF().format(date);
	}

	public static String currentDate() {
		return formatDate(now());
	}
	
	public static String currentDatetime() {
		return formatDatetime(now());
	}
	
	public static String currentDatetimeNum() {
		return formatDatetimeNum(now());
	}
	
	public static String currentTime() {
		return formatTime(now());
	}
	
	public static String formatDatetime(Date date, String pattern) {
		if(date==null) return null;
		SimpleDateFormat patternDF=new SimpleDateFormat(pattern);
		return patternDF.format(date);
	}

	public static Calendar calendar() {
		Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		return cal;
	}

	public static long millis() {
		return System.currentTimeMillis();
	}

	public static int month() {
		return calendar().get(Calendar.MONTH) + 1;
	}

	public static int dayOfMonth() {
		return calendar().get(Calendar.DAY_OF_MONTH);
	}

	public static int dayOfWeek() {
		return calendar().get(Calendar.DAY_OF_WEEK);
	}

	public static int dayOfWeekChina() {
		Calendar c = calendar();
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	public static int dayOfYear() {
		return calendar().get(Calendar.DAY_OF_YEAR);
	}

	public static boolean isBefore(Date src, Date dst) {
		return src.before(dst);
	}

	public static boolean isAfter(Date src, Date dst) {
		return src.after(dst);
	}

	public static boolean isEqual(Date date1, Date date2) {
		return date1.compareTo(date2) == 0;
	}

	/**
	 * 
	 * @Title:        between 
	 * @Description:  判断某个日期是否在某个日期范围
	 * @param:        @param beginDate 日期范围开始
	 * @param:        @param endDate 日期范围结束
	 * @param:        @param src 需要判断的日期
	 * @param:        @return    
	 * @return:       boolean    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午7:55:36
	 */
	public static boolean between(Date beginDate, Date endDate, Date src) {
		return beginDate.before(src) && endDate.after(src);
	}

	/**
	 * 
	 * @Title:        lastDayOfMonth 
	 * @Description:  获得当前月的最后一天 
	 * @param:        @return    
	 * @return:       Date    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午7:56:01
	 */
	public static Date lastDayOfMonth() {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_MONTH, 0); // M月置零
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
		cal.set(Calendar.MILLISECOND, -1);// 毫秒-1
		return cal.getTime();
	}

	/**
	 * 
	 * @Title:        firstDayOfMonth 
	 * @Description:  获得当前月的第一天 
	 * @param:        @return    
	 * @return:       Date    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午7:56:18
	 */
	public static Date firstDayOfMonth() {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		return cal.getTime();
	}
	
	
	/**
	 * 
	 * @Title:        firstDayOfWeek 
	 * @Description:  获得当周的第一天
	 * @param:        @return    
	 * @return:       Date    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午7:56:30
	 */
	public static Date firstDayOfWeek() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.add(Calendar.DATE, 0);
		ca.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		// 当前周第一天
		return ca.getTime();
	}
	
	/**
	 * 
	 * @Title:        weekDay 
	 * @Description:  获取某一周
	 * @param:        @param week
	 * @param:        @return    
	 * @return:       Date    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午7:56:49
	 */
	private static Date weekDay(int week) {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_WEEK, week);
		return cal.getTime();
	}

	/**
	 * 
	 * @Title:        friday 
	 * @Description:  获得周五日期 
	 * @param:        @return    
	 * @return:       Date    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午7:57:17
	 */
	public static Date friday() {
		return weekDay(Calendar.FRIDAY);
	}

	/**
	 * 
	 * @Title:        saturday 
	 * @Description:  获得周六日期
	 * @param:        @return    
	 * @return:       Date    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午7:57:28
	 */
	public static Date saturday() {
		return weekDay(Calendar.SATURDAY);
	}

	/**
	 * 
	 * @Title:        sunday 
	 * @Description:  获得周日日期
	 * @param:        @return    
	 * @return:       Date    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午7:57:38
	 */
	public static Date sunday() {
		return weekDay(Calendar.SUNDAY);
	}

	/**
	 * 
	 * @Title:        parseDatetime 
	 * @Description:  将字符串日期时间转换成java.util.Date类型
	 * @param:        @param datetime 日期时间格式yyyy-MM-dd HH:mm:ss
	 * @param:        @return
	 * @param:        @throws ParseException    
	 * @return:       Date    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午7:57:53
	 */
	public static Date parseDatetime(String datetime) throws ParseException {
			return getDatetimeDF().parse(datetime);
	}

	/**
	 * 
	 * @Title:        parseDate 
	 * @Description:  将字符串日期转换成java.util.Date类型
	 * @param:        @param date 日期时间格式yyyy-MM-dd
	 * @param:        @return
	 * @param:        @throws ParseException    
	 * @return:       Date    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午7:58:15
	 */
	public static Date parseDate(String date) throws ParseException {
		return getDateDF().parse(date);
	}

	/**
	 * 
	 * @Title:        parseDate 
	 * @Description:  将字符串日期转换成java.util.Date类型
	 * @param:        @param date 日期时间格式yyyy-MM-dd
	 * @param:        @param format
	 * @param:        @return
	 * @param:        @throws ParseException    
	 * @return:       Date    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午7:58:33
	 */
	public static Date parseDate(String date, String format)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.parse(date);
	}

	/**
	 * 
	 * @Title:        parseTime 
	 * @Description:  将字符串日期转换成java.util.Date类型
	 * @param:        @param time 时间格式 HH:mm:ss
	 * @param:        @return
	 * @param:        @throws ParseException    
	 * @return:       Date    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午7:58:55
	 */
	public static Date parseTime(String time) throws ParseException {
		return getTimeDF().parse(time);
	}

	/**
	 * 
	 * @Title:        parseDatetime 
	 * @Description:  根据自定义pattern将字符串日期转换成java.util.Date类型
	 * @param:        @param datetime
	 * @param:        @param pattern
	 * @param:        @return
	 * @param:        @throws ParseException    
	 * @return:       Date    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午7:59:13
	 */
	public Date parseDatetime(String datetime, String pattern)throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.parse(datetime);
	}

	/**
	 * 
	 * @Title:        minusDay 
	 * @Description:  计算当前日期的前几天
	 * @param:        @param day
	 * @param:        @return
	 * @param:        @throws ParseException    
	 * @return:       Date    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午7:59:37
	 */
	public static Date minusDay(int day) throws ParseException {
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - day);
		Date endDate = getDateDF().parse(getDateDF().format(date.getTime()));
		return endDate;
	}

	/**
	 * 
	 * @Title:        getWeekStartEndDate 
	 * @Description:  计算当前周的第num周的第一天日期和最后一天日期
	 * @param:        @param num 当前周的第几周，可以是负数，负数即为前几周
	 * @param:        @return    
	 * @return:       Map<String,Date>    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午8:00:13
	 */
	public static Map<String, Date> getWeekStartEndDate(Integer num) {
		Map<String, Date> result = new HashMap<String, Date>();
		Date nowTime = new Date();
		Calendar ca = Calendar.getInstance();
		ca.setTime(nowTime);
		ca.add(Calendar.DATE, 6 * num);
		ca.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		// 当前周第一天
		Date startDate = ca.getTime();
		result.put("startDate", startDate);
		// 当前周最后一天
		ca.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		ca.add(Calendar.WEEK_OF_YEAR, 1);
		Date endDate = ca.getTime();
		result.put("endDate", endDate);
		return result;
	}
	
	/**
	 * 
	 * @Title:        firstDayOfYear 
	 * @Description:  获取当前年份的第一天
	 * @param:        @return    
	 * @return:       String    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午8:00:29
	 */
	public static String firstDayOfYear(){
		Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_YEAR, 1);//本年第一天
        return DateUtil.transferLongToDate("yyyyMMdd", c.getTimeInMillis());
	}
	
	/**
	 * 
	 * @Title:        lastDayOfYear 
	 * @Description:  获取当前年份的最后一天
	 * @param:        @return    
	 * @return:       String    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午8:00:59
	 */
	public static String lastDayOfYear(){
		Calendar currCal=Calendar.getInstance();  
		int currentYear = currCal.get(Calendar.YEAR);  
		currCal.set(Calendar.YEAR, currentYear);  
		currCal.roll(Calendar.DAY_OF_YEAR, 1);  
		return DateUtil.transferLongToDate("yyyyMMdd", currCal.getTimeInMillis() );
	}

	/**
	 * 
	 * @Title:        getMonthStartEndDate 
	 * @Description:  计算当前月的第num月的第一天日期和最后一天日期
	 * @param:        @param num 当前月的第几个月，可以是负数，负数即为前几个月
	 * @param:        @return    
	 * @return:       Map<String,Date>    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午8:01:20
	 */
	public static Map<String, Date> getMonthStartEndDate(Integer num) {
		Map<String, Date> result = new HashMap<String, Date>();
		Date nowTime = new Date();
		Calendar ca = Calendar.getInstance();
		ca.setTime(nowTime);
		ca.add(Calendar.MONTH, num);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date startDate = ca.getTime();
		ca.set(Calendar.DAY_OF_MONTH,
				ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date endDate = ca.getTime();
		result.put("startDate", startDate);
		result.put("endDate", endDate);
		return result;
	}

	/**
	 * 
	 * @Title:        minusDay 
	 * @Description:  当前日期减去天数
	 * @param:        @param date
	 * @param:        @param day
	 * @param:        @return
	 * @param:        @throws ParseException    
	 * @return:       Date    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午8:05:16
	 */
	public static Date minusDay(Date date, int day) throws ParseException {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = date;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - day);
		Date endDate = dft.parse(dft.format(calendar.getTime()));
		return endDate;
	}
	
	public static void main(String[] args) throws ParseException {
		System.out.println( DateUtil.minusDay(new Date(), -1) );
	}

	/**
	 * 
	 * @Title:        transferLongToDate 
	 * @Description:  把毫秒转化成日期
	 * @param:        @param dateFormat (日期格式，例如：MM/ dd/yyyy HH:mm:ss)
	 * @param:        @param millSec (毫秒数)
	 * @param:        @return    
	 * @return:       String    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午8:04:49
	 */
	public static String transferLongToDate(String dateFormat, Long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(millSec);
		return sdf.format(date);
	}
	
	/**
	 * 
	 * @Title:        transferDateToString 
	 * @Description:  把日期转化成字符串日期 
	 * @param:        @param dateFormat (日期格式，例如：MM/ dd/yyyy HH:mm:ss)
	 * @param:        @param date 日期
	 * @param:        @return    
	 * @return:       String    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午8:04:11
	 */
	public static String transferDateToString(String dateFormat, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

	/**
	 * 
	 * @Title:        addDays 
	 * @Description:  当前时间增加天数。
	 * @param:        @param date 正值时时间延后，负值时时间提前。
	 * @param:        @param days
	 * @param:        @return    
	 * @return:       Date    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午8:03:49
	 */
	public static Date addDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, c.get(Calendar.DATE) + days);
		return new Date(c.getTimeInMillis());
	}

	/**
	 * 
	 * @Title:        differDays 
	 * @Description:  计算两个日期相差天数 
	 * @param:        @param start
	 * @param:        @param end
	 * @param:        @return    
	 * @return:       int    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午8:03:39
	 */
	public static int differDays(Date start, Date end) {
		long diff = end.getTime() - start.getTime();
		return (int) (diff / (24 * 60 * 60 * 1000));
	}
	
	/**
	 * 
	 * @Title:        getNowYear 
	 * @Description:  获取本年
	 * @param:        @return    
	 * @return:       String    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午8:03:21
	 */
	public static String getNowYear(){
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = new GregorianCalendar();
		c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_YEAR, 1);//本年第一天
		result = result + sdf.format(c.getTime());
		int year = c.get(Calendar.YEAR);
		c = Calendar.getInstance();  
		c.clear();
        c.set(Calendar.YEAR, year);  
        c.roll(Calendar.DAY_OF_YEAR, -1);
		result = result + sdf.format(c.getTime());
		return result;
	}
	
	/**
	 * 
	 * @Title:        getNowMonth 
	 * @Description:  获取本月 
	 * @param:        @return    
	 * @return:       String    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午8:03:07
	 */
	public static String getNowMonth(){
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = new GregorianCalendar();
		c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);//本月第一天
		result = result + sdf.format(c.getTime());
		c.add(Calendar.MONTH, 1);//本月最后一天
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		result = result + sdf.format(c.getTime());
		return result;
	}
	
	/**
	 * 
	 * @Title:        getNowWeek 
	 * @Description:  获取本周时间
	 * @param:        @return    
	 * @return:       String    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午8:02:47
	 */
	public static String getNowWeek(){
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		result = result + sdf.format(c.getTime());
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		result = result + sdf.format(c.getTime());
		return result;
	}
	
	/**
	 * 
	 * @Title:        isSameDate 
	 * @Description:  判断两个日期是否是同一天
	 * @param:        @param date1
	 * @param:        @param date2
	 * @param:        @return    
	 * @return:       boolean    
	 * @author        lijianzhou
	 * @Date          2016年10月12日 下午8:02:26
	 */
	public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth&& cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
        return isSameDate;
    }
}
