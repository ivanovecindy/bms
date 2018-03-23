package com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
public class DateTimeUtils extends com.util.DateUtil {
	private static Logger logger = Logger.getLogger(DateTimeUtils.class);
	
	public static int compareDateTime(String s1,String s2){
		if(StringUtils.isBlank(s1)){
			return -1;
		}
		if(StringUtils.isBlank(s2)){
			return 1;
		}
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(s1.length() < 11 && s2.length() < 11){
			df=new SimpleDateFormat("yyyy-MM-dd");
		}
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		try{
			c1.setTime(df.parse(s1));
			c2.setTime(df.parse(s2));
		}catch(ParseException e){
			logger.error("日期格式错误!"+e.getMessage());
		}
		int result=c1.compareTo(c2);
		if(result==0){
			logger.debug("c1相等c2");
		}
		else if(result<0){
			logger.debug("c1小于c2");
		}
		else{
			logger.debug("c1大于c2");
		}
		return result;
	}
	
	/**
	 * 比较2个日期大小
	 * @param date1
	 * @param date2
	 * @return	0：相等；小于0：date1小于date2；大于0：date1大于date2
	 */
	public static int compareDate(Date date1, Date date2){
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		try{
			c1.setTime(date1);
			c2.setTime(date2);
		}catch(Exception e){
			logger.error("日期格式错误!"+e.getMessage());
		}
		return c1.compareTo(c2);
	}
	
	/**
	 * 获取系统当前时间的格式字符串
	 * @param datePatten 例如yyyyMMddHHmmssSSS
	 * @return String
	 */
	public static String getYMDHMSS(String datePatten) {
		String strYMDHMSS = "";
		Date currentDateTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(datePatten);
		strYMDHMSS = formatter.format(currentDateTime);
		return strYMDHMSS;
	}
	
	/**
	 * 将字符串转为时间戳
	 * @param user_time
	 * @datePattern "yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String getTime(String user_time,String datePattern) {
		String re_time = null;
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		
		Date d;
		try {
			d = sdf.parse(user_time);
			long l = d.getTime();
			String str = String.valueOf(l);
			re_time = str.substring(0, 10);
		} catch (ParseException e) {
			logger.error("日期格式错误!"+e.getMessage());
		}
		return re_time;
	}
	
	/**
	 * 将时间戳转为字符串
	 * @param cc_time
	 * @datePattern "yyyy-MM-dd HH:mm:ss"
	 * @return String
	 */
	public static String getStrTime(String cc_time,String datePattern) {
		String re_StrTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		// 例如：cc_time=1291778220
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));
		return re_StrTime;
	}
}
