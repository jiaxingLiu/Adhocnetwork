package com.zhichenhaixin.chentong.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 日期格式工具类
 * @author pwl
 *
 */
public class DateFormatUtil {
	
	public static String getDateFormat(Integer dataType,Long time){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		//获取时间的年
		int yearKey=calendar.get(Calendar.YEAR);
		
		//获取时间的月
		int monthKey=calendar.get(Calendar.MONTH)+1;
		
		//获取时间的日
		int dateKey=calendar.get(Calendar.DATE);
		
		//获取今天的第几周
		int weekKey=calendar.get(Calendar.WEEK_OF_YEAR);
		
		//获取今天的第几个小时
		int hourKey=calendar.get(Calendar.HOUR_OF_DAY);
		
		switch (dataType) {
		case 1:
			
			return hourKey+"时";
		case 2:
			
			return yearKey+"/"+monthKey+"/"+dateKey;
		case 3:
			
			return yearKey+"/"+weekKey;
		case 4:
	
			return yearKey+"/"+monthKey;
		case 5:
			
			return yearKey+"";
		}
		return null;
	}
	/**
	 * 是否是闰年
	 * @param time
	 * @return
	 */
	public static boolean isLeapYear(Long time) {
	     //2.创建Calendar对象
	     Calendar calendar = Calendar.getInstance();
	     int year=calendar.get(Calendar.YEAR);
	     //3.位置那一年的三月1日
	     calendar.set(year, 2, 1);   // 三月需要写成2
	     //4.将日减去1
	     calendar.add(Calendar.DAY_OF_MONTH, -1);
	     //5.判断是否是29天
	     return calendar.get(Calendar.DAY_OF_MONTH) == 29;
	}
	
	/**
	 * 日期格式化
	 */
	public static String dateFormat(Long time){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd ahh:mm:ss");
		Date date = new Date(time);
		return sdf.format(date);
	}
	/**
	 * 时间毫秒数转成指定日期格式
	 * @param time
	 * @param formatStr
	 * @return
	 */
	public static String dateFormat(Long time,String formatStr){
		SimpleDateFormat sdf=new SimpleDateFormat(formatStr);
		Date date = new Date(time);
		return sdf.format(date);
	}
	/**
	 * 日期格式化
	 */
	public static String dateFormatFile(Long time){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd号 hh时mm分ss秒");
		Date date = new Date(time);
		return sdf.format(date);
	}
}
