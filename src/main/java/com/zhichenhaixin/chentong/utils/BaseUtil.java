package com.zhichenhaixin.chentong.utils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseUtil.class);
	
	/** 每分钟秒数 **/
	public static final int SECONDS_PER_MINUTE = 60;
	/** 每小时分钟数 **/
	public static final int MINUTES_PER_HOUR = 60;
    /** 每天小时数 **/
	public static final int HOURS_PER_DAY = 24;
    /** 每天秒数 **/
	public static final int SECONDS_PER_DAY = (HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE);
    /** 每分钟毫秒数 **/
	public static final long DAY_MILLISECONDS = SECONDS_PER_DAY * 1000L;
	/** Base64识别码 **/
	private static byte[] base64Alphabet = new byte['ÿ'];
	
	/** 
	 * 获取项目绝对路径
	 * @return
	 */
	public static String getRootPath(){	
		//return System.getProperty("user.dir");
		String rootPath = BaseUtil.class.getResource("").toString();
		if(rootPath.length() > 0){
			rootPath = rootPath.replace("file:/", "");
			int index = rootPath.indexOf("WEB-INF/classes/");
			if(index >= 0){
				rootPath = rootPath.substring(0, index);
			}
			index = rootPath.indexOf("classbean/");
			if(index >= 0){
				rootPath = rootPath.substring(0, index);
			}
			rootPath = rootPath.replace("/", File.separator);
			if(rootPath.indexOf(":") < 0 && !rootPath.startsWith("/")){
				rootPath = "/" + rootPath;
			}
		}
		return rootPath;
	}
	

	

	/**
	 * 字符串首字母转小写
	 * @param s 要转换的字符串
	 * @return
	 */
	public static String toLowerCaseFirstOne(String s) {
		if(Character.isLowerCase(s.charAt(0))){
			return s;
		} else {
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();     
		}
	}     
	
	/**
	 * 字符串首字母转大写     
	 * @param s 要转换的字符串
	 * @return
	 */
	public static String toUpperCaseFirstOne(String s) {
		if(Character.isUpperCase(s.charAt(0))){            
			return s;
		} else {
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}
	
	/**
	 * 字符串预处理<p>
	 * 1、空对象转成空字符串
	 * @param str 要转换的字符串
	 * @return
	 */
	public static String trim(String str){
		if(str == null){
			return "";
		}else{
			return str;
		}
	}
	
	/**
	 * 字符串预处理<p>
	 * 1、空对象转成空字符串
	 * @param str 要转换的字符串
	 * @return
	 */
	public static String trimx(String str){
		if(str == null || "null".equalsIgnoreCase(str)){
			return "";
		}else{
			str = str.replaceAll(" ", "");
			return str;
		}
	}
	
	/**
	 * 字符串预处理<p>
	 * 1、空对象转成空字符串
	 * @param str 要转换的字符串
	 * @return
	 */
	public static String trim(Object str){
		if(str == null){
			return "";
		}else{
			return str.toString();
		}
	}
	
	/**
	 * 字符串预处理<p>
	 * 1、空对象转成空字符串
	 * @param str 要转换的字符串
	 * @param maxLen 最大长度
	 * @return
	 */
	public static String trim(Object str, int maxLen){
		String str1 = trim(str);
		if(str1.length() > maxLen){
			str1 = str1.substring(0, maxLen);
		}
		return str1;
	}
	/**
	 * 截取小数点后几个
	 * @param str
	 * @param defaultV
	 * @param maxLen
	 * @return
	 */
	public static String trimDecimal(Object str,String defaultV, int maxLen){
		String str1 = trim(str,defaultV);
		if(str1.indexOf(".") > 0){
			String s = str1.split("\\.")[1];
			if(s.length() > maxLen){
				s = s.substring(0, maxLen);
			}
			str1 = str1.substring(0, str1.indexOf(".") + 1) + s;
		}
		return str1;
	}
	/**
	 * 字符串预处理<p>
	 * 1、空对象转成空字符串
	 * @param str 要转换的字符串
	 * @return
	 */
	public static String trim(String str, String defaultV){
		if(str == null || "".equals(str)){
			return defaultV;
		}else{
			return str.toString();
		}
	}
	
	/**
	 * 字符串预处理<p>
	 * 1、空对象转成空字符串
	 * @param str 要转换的字符串
	 * @return
	 */
	public static String trim(Object str, String defaultV){
		if(str == null || "".equals(str)){
			return defaultV;
		}else{
			return str.toString();
		}
	}
	
	/**
	 * 读取xml进行预处理<p>
	 * 1、空对象转成空字符串
	 * 2、转成大写
	 * @param str 要转换的字符串
	 * @return
	 */
	public static String changeFromXml(Object str){
		return trim(str).toUpperCase();
	}
	
	/**
	 * 获取布尔类型的默认值
	 * @param value 要判断的值
	 * @param defaultValue 默认值
	 * @return
	 */
	public static String getBooleanDefaultValue(String value, String defaultValue){
		if(!"true".equalsIgnoreCase(value) && !"false".equalsIgnoreCase(value)){
			return defaultValue.toUpperCase();
		} else {
			return value.toUpperCase();
		}
	}
	
	/**
	 * 转换为数字
	 * @param value 要转换的字符串
	 * @param defaultValue 转换失败时的默认值
	 * @return
	 */
	public static short getShort(String value, int defaultValue){
		try{
			return (short)Double.parseDouble(value);
		} catch(Exception e){
			return (short)defaultValue;
		}
	}
	
	/**
	 * 转换为数字
	 * @param value 要转换的字符串
	 * @param defaultValue 转换失败时的默认值
	 * @return
	 */
	public static int getInt(String value, int defaultValue){
		try{
			return (int)Double.parseDouble(value);
		} catch(Exception e){
			return defaultValue;
		}
	}
	
	/**
	 * 转换为数字
	 * @param value 要转换的字符串
	 * @param defaultValue 转换失败时的默认值
	 * @return
	 */
	public static long getLong(String value, long defaultValue){
		try{
			return (long)Double.parseDouble(value);
		} catch(Exception e){
			return defaultValue;
		}
	}
	
	/**
	 * 转换为高精度对象
	 * @param value 要转换的字符串
	 * @param defaultValue 转换失败时的默认值
	 * @return
	 */
	public static BigDecimal getBigDecimal(String value, double defaultValue){
		try{
			return new BigDecimal(trim(value));
		} catch(Exception e){
			return new BigDecimal(defaultValue);
		}
	}
	
	/**
	 * 转换为数字
	 * @param value 要转换的字符串
	 * @param defaultValue 转换失败时的默认值
	 * @return
	 */
	public static float getFloat(String value, float defaultValue){
		try{
			return Float.parseFloat(value);
		} catch(Exception e){
			return defaultValue;
		}
	}
	
	/**
	 * 转换为数字
	 * @param value 要转换的字符串
	 * @param defaultValue 转换失败时的默认值
	 * @return
	 */
	public static double getDouble(String value, double defaultValue){
		try{
			return Double.parseDouble(value);
		} catch(Exception e){
			return defaultValue;
		}
	}
	
	/**
	 * 判断字符串是否为空
	 * @param value 字符串
	 * @return
	 */
	public static boolean isNull(String value){
		value = trim(value);
		return "".equals(value);
	}
	
	/**
	 * 判断所有字符串是否都为空
	 * @param value 字符串
	 * @return
	 */
	public static boolean isNullAnd(String...value){
		if(value == null || value.length == 0){
			return true;
		}
		boolean flag = true;
		for(String v : value){
			v = trim(v);
			flag &= ("".equals(v));
			
		}
		return flag;
	}
	
	/**
	 * 判断字符串是否其中之一为空
	 * @param value 字符串
	 * @return
	 */
	public static boolean isNullOr(String...value){
		if(value == null || value.length == 0){
			return true;
		}
		boolean flag = false;
		for(String v : value){
			v = trim(v);
			flag |= ("".equals(v));
			
		}
		return flag;
	}
	
	/**
	 * 判断字符串是否为数字
	 * @param value 字符串
	 * @return
	 */
	public static boolean isNum(String value){
		try{
			Double.parseDouble(value);
			return true;
		} catch(Exception e){ }
		return false;
	}
	
	/**
	 * 转换为SQL日期对象
	 * @param value 要转换的字符串
	 * @param fmt 日期的字符串格式
	 * @return 转换失败时，返回当前系统日期
	 */
	public static java.sql.Date getSqlDate(String value, String fmt){
		Date curD = new Date();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			curD = sdf.parse(value);
		} catch(Exception e){
			e.printStackTrace();
		}
		return new java.sql.Date(curD.getTime());
	}
	
	/**
	 * 获取日期对象
	 * @param value 要转换的字符串
	 * @param fmt 日期的字符串格式
	 * @return
	 */
	public static Date getDate(String value, String fmt){
		return getDate(value, new String[]{fmt});
	}
	
	/**
	 * 获取日期对象
	 * @param value 日期字符串
	 * @param fmt 格式，支持多格式
	 * @return
	 */
	public static Date getDate(String value, String[] fmt){
		Date curD = null;
		for(String f : fmt){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat(f);
				sdf.setLenient(false);
				curD = sdf.parse(value);
				break;
			} catch(Exception e){ }
		}
		
		if(curD == null){
			LOGGER.info("日期[" + value + "]转换错误！");
		}
		
		return curD;
	}
	
	/**
	 * 获取日期对象
	 * @param value 日期字符串
	 * @return
	 */
	public static Date getDate(String value){
		String[] formFmt = {"yyyy-MM-dd HH:mm:ss"
				, "HH:mm:ss"
				, "HH:mm"
				, "yyyy/MM/dd HH:mm:ss"
				, "dd/MM/yyyy HH:mm:ss"
				, "yyyy-MM-dd HH:mm"
				, "yyyy/MM/dd HH:mm"
				, "yyyy-MM-dd HH"
				, "yyyy/MM/dd HH"
				, "yyyy-MM-dd"
				, "yyyy/MM/dd"
				, "yyyy-MM"
				, "yyyy"
				, "HH"
				};
		return getDate(value, formFmt);
	}
	
	/**
	 * 获取日期最早时间
	 * @param d 日期对象
	 * @return
	 */
	public static Date getTimeBegin(Date d){
		return getDate(format(d, "yyyy-MM-dd") + " 00:00:00");
	}
	
	/**
	 * 获取日期最晚时间
	 * @param d 日期对象
	 * @return
	 */
	public static Date getTimeEnd(Date d){
		return getDate(format(d, "yyyy-MM-dd") + " 23:59:59");
	}
	
	/**
	 * 转换boolean类型的字符串<p>
	 * true-启用 false-不启用
	 * @param value 要转换的字符串
	 * @return
	 */
	public static String changeUseStr(String value){
		if("true".equalsIgnoreCase(value)){
			return "是";
		} else {
			return "否";
		}
	}
	
	/**
	 * 转换数字成boolean类型的字符串<p>
	 * 1-true 0-false
	 * @param value 要转换的字符串
	 * @return
	 */
	public static String changeNumToBooleanStr(String value){
		if("1".equalsIgnoreCase(value)){
			return "true";
		} else {
			return "false";
		}
	}
	
	/**
	 * 创建文件夹
	 * @param path 文件夹路径
	 * @return
	 */
	public static boolean createDir(String path){
		boolean flag = false;
		File f = null;
		try{
			f = new File(path);
			if(f.exists()){
				if(f.isDirectory()){
					flag = true;
				}
			} else {
				flag = f.mkdir();
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 通过特定的分隔符连接数组为一个字符串
	 * @param arr 数组对象
	 * @param sep 分隔符
	 * @return
	 */
	public static String joint(String[] arr, String sep){
		if(arr == null || arr.length <= 0){
			return "";
		}
		
		StringBuffer str = new StringBuffer();
		for(int i=0; i<arr.length; i++){
			str.append(arr[i]);
			if(i < arr.length - 1){
				str.append(sep);
			}
		}
		
		return str.toString();
	}
	
	/**
	 * 通过特定的分隔符连接数组为一个字符串
	 * @param arr 数组对象
	 * @param sep 分隔符
	 * @return
	 */
	public static String joint(int[] arr, String sep){
		if(arr == null || arr.length <= 0){
			return "";
		}
		
		StringBuffer str = new StringBuffer();
		for(int i=0; i<arr.length; i++){
			str.append(arr[i]);
			if(i < arr.length - 1){
				str.append(sep);
			}
		}
		
		return str.toString();
	}
	
	/**
	 * 通过特定的分隔符连接数组为一个字符串
	 * @param arr 数组对象
	 * @param sep 分隔符
	 * @return
	 */
	public static String joint(List<String> arr, String sep){
		if(arr == null || arr.size() <= 0){
			return "";
		}
		
		StringBuffer str = new StringBuffer();
		for(int i=0; i<arr.size(); i++){
			str.append(arr.get(i));
			if(i < arr.size() - 1){
				str.append(sep);
			}
		}
		
		return str.toString();
	}
	
	/**
	 * 按照特定的分隔符把容器的值连接成字符串
	 * @param data 要连接的容器
	 * @param separator 分隔符
	 * @param hasQuotation 是否要给值加上引号
	 * @return
	 */
	public static String joint(String[] data, String separator, boolean hasQuotation){
		if(data == null || data.length == 0){
			return hasQuotation ? "''" : "";
		}
		StringBuffer res = new StringBuffer();
		
		for(String d : data){
			d = trim(d);
			if("".equals(d)){
				continue;
			}
			if(hasQuotation){
				res.append("'").append(d).append("'").append(separator);
			} else {
				res.append(d).append(separator);
			}
		}
		if(res.length() > 0){
			res.deleteCharAt(res.length() - 1);
		}
		
		return res.toString();
	}
	
	/**
	 * 拼接集合
	 * @param data 数据集合
	 * @return
	 */
	public static <T> List<T> joint(List<T>...data){
		List<T> res = new ArrayList<T>();
		if(data != null && data.length > 0){
			for(List<T> d : data){
				res.addAll(d);
			}
		}
		return res;
	}
	
	/**
	 * 按照特定的分隔符把容器的值连接成字符串
	 * @param data 要连接的容器
	 * @param separator 分隔符
	 * @param hasQuotation 是否要给值加上引号
	 * @return
	 */
	public static String joint(List<String> data, String separator, boolean hasQuotation){
		if(data == null || data.size() == 0){
			return hasQuotation ? "''" : "";
		}
		StringBuffer res = new StringBuffer();
		
		for(String d : data){
			d = trim(d);
			if("".equals(d)){
				continue;
			}
			if(hasQuotation){
				res.append("'").append(d).append("'").append(separator);
			} else {
				res.append(d).append(separator);
			}
		}
		if(res.length() > 0){
			res.deleteCharAt(res.length() - 1);
		}
		
		return res.toString();
	}
	
	/**
	 * 把字符串按特定的分隔符分隔
	 * @param str 字符串
	 * @param sep 分隔符字符串
	 * @return
	 */
	public static String[] split(String str, String sep){
		str = trim(str);
		if("".equals(str)){
			return new String[0];
		}
		
		return str.split(sep);
	}
	
	/**
	 * 长度不足时，左侧补充对应字符
	 * @param str 原字符串
	 * @param len 长度
	 * @param c 补充字符
	 * @return
	 */
	public static String trimL(String str, int len, char c){
		str = trim(str);
		
		int l = len - str.length();
		for(int i=0; i<l; i++){
			str = c + str;
		}
		return str;
	}
	
	/**
	 * 长度不足时，右侧补充对应字符
	 * @param str 原字符串
	 * @param len 长度
	 * @param c 补充字符
	 * @return
	 */
	public static String trimR(String str, int len, char c){
		str = trim(str);
		
		int l = len - str.length();
		for(int i=0; i<l; i++){
			str = str + c;
		}
		return str;
	}
	
	/**
	 * 格式化日期（默认按yyyy-MM-dd HH:mm:ss格式解析）
	 * @param curDate 要格式化的日期对象
	 * @return
	 */
	public static String format(Date curDate){
		return format(curDate, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 格式化日期
	 * @param curDate 要格式化的日期对象
	 * @param fmt 要格式化的日期格式
	 * @return
	 */
	public static String format(Date curDate, String fmt){
		if(curDate == null) return "";
		try{
			SimpleDateFormat sdf1 = new SimpleDateFormat(fmt);
			return sdf1.format(curDate.getTime());
		} catch(Exception e){
			return "";
		}
	}
	
	/**
	 * 格式化日期
	 * @param curDate 要格式化的日期对象
	 * @param fmt 要格式化的日期格式
	 * @return
	 */
	public static String format(long curDateL, String fmt){
		try{
			SimpleDateFormat sdf1 = new SimpleDateFormat(fmt);
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(curDateL);
			return sdf1.format(c.getTime().getTime());
		} catch(Exception e){
			return "";
		}
	}
	
	/**
	 * 格式化日期
	 * @param value 要格式化的日期字符串
	 * @param formFmt 从-格式
	 * @param toFmt 到-格式
	 * @return
	 */
	public static String format(String value, String formFmt, String toFmt){
		if(value == null) return "";
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(formFmt);
			SimpleDateFormat sdf1 = new SimpleDateFormat(toFmt);
			return sdf1.format(sdf.parse(value));
		} catch(Exception e){
			return "";
		}
	}
	
	/**
	 * 格式化日期
	 * @param value 要格式化的日期字符串
	 * @param formFmt 从-格式集合
	 * @param toFmt 到-格式
	 * @return
	 */
	public static String format(String value, String[] formFmt, String toFmt){
		if(value == null || formFmt == null) return "";
		for(String ff : formFmt){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat(ff);
				SimpleDateFormat sdf1 = new SimpleDateFormat(toFmt);
				return sdf1.format(sdf.parse(value));
			} catch(Exception e){
			}
		}
		return "";
	}
	
	/**
	 * 格式化日期
	 * @param value 要格式化的日期字符串
	 * @param toFmt 到-格式
	 * @return
	 */
	public static String format(String value, String toFmt){
		return format(getDate(value), toFmt);
	}
	
	/**
	 * 获取特定日期的字符串格式
	 * @param date 要格式化的日期
	 * @param fromFmt 要格式化的原日期格式
	 * @param toFmt 要格式化的目标日期格式
	 * @param field 偏移日期字段
	 * @param amount 偏移量
	 * @return
	 */
	public static String format(String date, String fromFmt, String toFmt, int field, int amount){
		String res = "";
		SimpleDateFormat sdf = new SimpleDateFormat(fromFmt);
		SimpleDateFormat sdf1 = new SimpleDateFormat(toFmt);
		Calendar c = Calendar.getInstance();
		try{
			c.setTime(sdf.parse( date ));
			c.add(field, amount);
			res = sdf1.format( c.getTime() );
		} catch(Exception e){
			//e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 转换excel日期格式为特定格式字符串
	 * @param value excel日期格式数据
	 * @param toFmt 转换为日期格式
	 * @return
	 */
	public static String formatExcelDate(String value, String toFmt){
		value = trim(value);
		if("".equals(value) || "".equals(toFmt)){
			return "";
		}
		
		if(isNum(value)){
			return format(getExcelDate(value), toFmt);
		} else {
			return format(value, toFmt);
		}
	}
	
	/**
	 * 获取excel 日期格式值并转为日期对象
	 * @param value excel日期格式
	 * @return
	 */
	public static Date getExcelDate(String value){
		double date = getDouble(value, 0);
		if(date > 0){
			int wholeDays = (int)Math.floor(date);
			int millisecondsInDay = (int)((date - wholeDays) * DAY_MILLISECONDS + 0.5);
			Calendar calendar = new GregorianCalendar();
			setCalendar(calendar, wholeDays, millisecondsInDay, false);
			return calendar.getTime();
		} else {
			return getDate(value);
		}
	}
	
	/**
	 * 获取月份第1天日期
	 * @param month 月份，格式为yyyy-MM、yyyy-MM-dd
	 * @return 返回格式yyyy-MM-dd
	 */
	public static String getFirstDayInMonth(String month){
		if(month.length() == 10){
			month = month.substring(0, 7);
		}
		return month + "-01";
	}
	
	/**
	 * 获取月份最后一天日期
	 * @param month 月份，格式为yyyy-MM、yyyy-MM-dd
	 * @return 返回格式yyyy-MM-dd
	 */
	public static String getLastDayInMonth(String month){
		Calendar day = Calendar.getInstance();
		day.setTime(getDate(month));
		day.set(Calendar.DAY_OF_MONTH, 1);
		day.add(Calendar.MONTH, 1);
		day.add(Calendar.DAY_OF_MONTH, -1);
		return format(day.getTime(), "yyyy-MM-dd");
	}
	
	/**
	 * 获取年份第1天日期
	 * @param year 年份，格式为yyyy
	 * @return 返回格式yyyy-MM-dd
	 */
	public static String getFirstDayInYear(String year){
		return year + "-01-01";
	}
	
	/**
	 * 获取年份最后一天日期
	 * @param year 年份，格式为yyyy
	 * @return 返回格式yyyy-MM-dd
	 */
	public static String getLastDayInYear(String year){
		Calendar day = Calendar.getInstance();
		day.setTime(getDate(year));
		day.set(Calendar.DAY_OF_YEAR, 1);
		day.add(Calendar.YEAR, 1);
		day.add(Calendar.DAY_OF_YEAR, -1);
		return format(day.getTime(), "yyyy-MM-dd");
	}
	
	/**
	 * 设置日历偏移
	 * @param calendar 日期对象
	 * @param wholeDays 偏移天数
	 * @param millisecondsInDay 偏移毫秒
	 * @param use1904windowing 1904年标识
	 */
	public static void setCalendar(Calendar calendar, int wholeDays, int millisecondsInDay, boolean use1904windowing) {
        int startYear = 1900;
        int dayAdjust = -1; // Excel thinks 2/29/1900 is a valid date, which it isn't
        if (use1904windowing) {
            startYear = 1904;
            dayAdjust = 1; // 1904 date windowing uses 1/2/1904 as the first day
        }
        else if (wholeDays < 61) {
            // Date is prior to 3/1/1900, so adjust because Excel thinks 2/29/1900 exists
            // If Excel date == 2/29/1900, will become 3/1/1900 in Java representation
            dayAdjust = 0;
        }
        calendar.set(startYear,0, wholeDays + dayAdjust, 0, 0, 0);
        calendar.set(GregorianCalendar.MILLISECOND, millisecondsInDay);
    }
	
	/**
	 * 格式化数字
	 * @param value 要格式化的数字
	 * @param fmt 格式串
	 * @return
	 */
	public static String format(double value, String fmt){
		try{
			DecimalFormat f = new DecimalFormat(fmt);
			return f.format(value);
		} catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 格式化数字
	 * @param value 要格式化的数字
	 * @return
	 */
	public static String format(double value){
		return format(value, ".000");
	}
	
	/**
	 * 格式化数字
	 * @param num
	 * @param fmt
	 * @return
	 */
	public static String format(BigDecimal num, String fmt){
		DecimalFormat nf = new DecimalFormat(fmt);
		try{
			return nf.format( num.doubleValue() );
		} catch(Exception e){
			return "0";
		}
	}
	
	/**
	 * 格式化数字
	 * @param num
	 * @return
	 */
	public static String format(BigDecimal num){
		return format(num, "0.000");
	}
	
	/**
	 * 日期对象计算
	 * @param dt 日期对象
	 * @param type 
	 * @param offsetNum
	 * @return
	 */
	public static Date addDate(Date dt, int type, int offsetNum){
		Calendar _dt = Calendar.getInstance();
		_dt.setTime(dt);
		_dt.add(type, offsetNum);
		
		return _dt.getTime();
	}
	
	/**
	 * 获取两个日期相差的毫秒数
	 * @param d1 日期1
	 * @param d2 日期2
	 * @return
	 */
	public static long getTimeDiffNum(Date d1, Date d2){
		if(d1 == null || d2 == null){
			return 0;
		}
		long offsetMs = d2.getTime() - d1.getTime();
		if(offsetMs < 0){
			offsetMs = d1.getTime() - d2.getTime();
		}
		return offsetMs;
	}
	
	/**
	 * 获取两个日期相差的毫秒数
	 * @param d1 日期1
	 * @param d2 日期2
	 * @param fmt 日期格式
	 * @return
	 */
	public static long getTimeDiffNum(String d1, String d2, String fmt){
		if(d1 == null || d2 == null){
			return 0;
		}
		Date dt1 = getDate(d1, fmt);
		Date dt2 = getDate(d2, fmt);
		if(dt1 == null || dt2 == null){
			return 0;
		}
		return getTimeDiffNum(dt1, dt2);
	}
	
	/**
	 * 获取两个日期相差的秒数
	 * @param d1 日期1
	 * @param d2 日期2
	 * @return
	 */
	public static long getTimeDiffSecond(Date d1, Date d2){
		if(d1 == null || d2 == null){
			return 0;
		}
		long offsetMs = d2.getTime() - d1.getTime();
		if(offsetMs < 0){
			offsetMs = d1.getTime() - d2.getTime();
		}
		return offsetMs / 1000;
	}
	
	/**
	 * 获取两个日期相差的秒数
	 * @param d1 日期1
	 * @param d2 日期2
	 * @param fmt 日期格式
	 * @return
	 */
	public static long getTimeDiffSecond(String d1, String d2, String fmt){
		if(d1 == null || d2 == null){
			return 0;
		}
		Date dt1 = getDate(d1, fmt);
		Date dt2 = getDate(d2, fmt);
		if(dt1 == null || dt2 == null){
			return 0;
		}
		return getTimeDiffSecond(dt1, dt2);
	}
	
	/**
	 * 获取两个日期相差的分钟数
	 * @param d1 日期1
	 * @param d2 日期2
	 * @return
	 */
	public static long getTimeDiffMinute(Date d1, Date d2){
		if(d1 == null || d2 == null){
			return 0;
		}
		long offsetMs = d2.getTime() - d1.getTime();
		if(offsetMs < 0){
			offsetMs = d1.getTime() - d2.getTime();
		}
		return offsetMs / 1000 / 60;
	}
	
	/**
	 * 获取两个日期相差的分钟数
	 * @param d1 日期1
	 * @param d2 日期2
	 * @param fmt 日期格式
	 * @return
	 */
	public static long getTimeDiffMinute(String d1, String d2, String fmt){
		if(d1 == null || d2 == null){
			return 0;
		}
		Date dt1 = getDate(d1, fmt);
		Date dt2 = getDate(d2, fmt);
		if(dt1 == null || dt2 == null){
			return 0;
		}
		return getTimeDiffMinute(dt1, dt2);
	}
	
	/**
	 * 获取两个日期相差的小时数
	 * @param d1 日期1
	 * @param d2 日期2
	 * @return
	 */
	public static long getTimeDiffHour(Date d1, Date d2){
		if(d1 == null || d2 == null){
			return 0;
		}
		long offsetMs = d2.getTime() - d1.getTime();
		if(offsetMs < 0){
			offsetMs = d1.getTime() - d2.getTime();
		}
		return offsetMs / 1000 / 60 / 60;
	}
	
	/**
	 * 获取两个日期相差的小时数
	 * @param d1 日期1
	 * @param d2 日期2
	 * @param fmt 日期格式
	 * @return
	 */
	public static long getTimeDiffHour(String d1, String d2, String fmt){
		if(d1 == null || d2 == null){
			return 0;
		}
		Date dt1 = getDate(d1, fmt);
		Date dt2 = getDate(d2, fmt);
		if(dt1 == null || dt2 == null){
			return 0;
		}
		return getTimeDiffHour(dt1, dt2);
	}
	
	/**
	 * 获取两个日期相差的天数
	 * @param d1 日期1
	 * @param d2 日期2
	 * @return
	 */
	public static long getTimeDiffDay(Date d1, Date d2){
		if(d1 == null || d2 == null){
			return 0;
		}
		long offsetMs = d2.getTime() - d1.getTime();
		if(offsetMs < 0){
			offsetMs = d1.getTime() - d2.getTime();
		}
		return offsetMs / 1000 / 60 / 60 / 24;
	}
	
	/**
	 * 获取两个日期相差的天数
	 * @param d1 日期1
	 * @param d2 日期2
	 * @param fmt 日期格式
	 * @return
	 */
	public static long getTimeDiffDay(String d1, String d2, String fmt){
		if(d1 == null || d2 == null){
			return 0;
		}
		Date dt1 = getDate(d1, fmt);
		Date dt2 = getDate(d2, fmt);
		if(dt1 == null || dt2 == null){
			return 0;
		}
		return getTimeDiffDay(dt1, dt2);
	}
	
	/**
	 * 获取两个日期相差的月份数
	 * @param d1 日期1
	 * @param d2 日期2
	 * @return
	 */
	public static long getTimeDiffMonth(Date d1, Date d2){
		Calendar c1 = Calendar.getInstance();  
        Calendar c2 = Calendar.getInstance();  
        c1.setTime(d1);  
        c2.setTime(d2);
        if(d1.getTime() < d2.getTime()){
        	c1.setTime(d2);
        	c2.setTime(d1);
        }
        if(c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;  
        int year1 = c1.get(Calendar.YEAR);  
        int year2 = c2.get(Calendar.YEAR);  
        int month1 = c1.get(Calendar.MONTH);  
        int month2 = c2.get(Calendar.MONTH);  
        int day1 = c1.get(Calendar.DAY_OF_MONTH);  
        int day2 = c2.get(Calendar.DAY_OF_MONTH);  
        // 获取年的差值 假设 d1 = 2015-8-16  d2 = 2011-9-30  
        int yearInterval = year1 - year2;  
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数  
        if(month1 < month2 || month1 == month2 && day1 < day2) yearInterval --;  
        // 获取月数差值  
        int monthInterval =  (month1 + 12) - month2  ;  
        if(day1 < day2) monthInterval --;  
        monthInterval %= 12;  
        return yearInterval * 12 + monthInterval;  
	}
	
	/**
	 * 获取两个日期相差的月份数
	 * @param d1 日期1
	 * @param d2 日期2
	 * @param fmt 日期格式
	 * @return
	 */
	public static long getTimeDiffMonth(String d1, String d2, String fmt){
		if(d1 == null || d2 == null){
			return 0;
		}
		Date dt1 = getDate(d1, fmt);
		Date dt2 = getDate(d2, fmt);
		if(dt1 == null || dt2 == null){
			return 0;
		}
		return getTimeDiffMonth(dt1, dt2);
	}
	
	/**
	 * 计算两个日期相差的时间
	 * @param d1 日期1
	 * @param d2 日期2
	 * @return
	 */
	public static String getTimeDiff(Date d1, Date d2){
		return formatTimeInterval(getTimeDiffNum(d1, d2));
	}
	
	/**
	 * 获取时间差（分钟）合计
	 * @param dates 日期对象集合，集合数量必须为偶数个，奇数位为开始日期，偶数位为结束日期
	 * @return
	 */
	public static long getSumTimeDiffMinute(List<Date> dates){
		if(dates == null || dates.size() == 0 || dates.size() % 2 != 0){
			return 0;
		}
		
		int count = dates.size() / 2;
		long num = 0;
		for(int i=0; i<count; i++){
			Date startDate = dates.get(2 * i);
			Date endDate = dates.get(1 + 2 * i);
			num += getTimeDiffMinute(startDate, endDate);
		}
		return num;
	}
	
	/**
	 * 格式化时间长度
	 * @param offsetMs 时间毫秒数
	 * @return
	 */
	public static String formatTimeInterval(long offsetMs){
		int day = 0, hour = 0, minute = 0, second = 0, mSecond = 0;
		long offsetMsTemp = 0;
		//计算天差值
		day = (int)( offsetMs / (1000 * 60 * 60 * 24) );
		offsetMsTemp = day * 1000 * 60 * 60 * 24;
		//计算小时差值
		hour = (int)( ( offsetMs - offsetMsTemp ) / ( 1000 * 60 * 60 ) );
		offsetMsTemp += hour * 1000 * 60 * 60;
		//计算分钟差值
		minute = (int)( ( offsetMs - offsetMsTemp ) / ( 1000 * 60 ) );
		offsetMsTemp += minute * 1000 * 60;
		//计算秒差值
		second = (int)( ( offsetMs - offsetMsTemp ) / 1000 );
		offsetMsTemp += second * 1000;
		//计算秒差值
		mSecond = (int)( offsetMs - offsetMsTemp );
		offsetMsTemp += mSecond;
		//拼接
		StringBuffer res = new StringBuffer();
		if(day > 0){
			res.append(day).append("天");
		}
		if(hour > 0){
			res.append(hour).append("小时");
		}
		if(minute > 0){
			res.append(minute).append("分钟");
		}
		if(second > 0){
			res.append(second).append("秒");
		}
		if(mSecond > 0){
			res.append(mSecond).append("毫秒");
		}
		return res.toString();
	}
	
	/**
	 * 校验两个时间段是否交叉
	 * @param dateStart1 时间段1开始日期
	 * @param dateEnd1 时间段1结束日期
	 * @param dateStart2 时间段2开始日期
	 * @param dateEnd2 时间段2结束日期
	 * @return
	 */
	public static boolean isOverlapping(Date dateStart1, Date dateEnd1, Date dateStart2, Date dateEnd2){
		if(dateStart1 == null || dateEnd1 == null
				|| dateStart2 == null || dateEnd2 == null){
			return false;
		}
		
		if(dateEnd1.getTime() < dateStart1.getTime()){
			return false;
		}
		
		if(dateEnd2.getTime() < dateStart2.getTime()){
			return false;
		}
		
		if(dateStart2.getTime() > dateEnd1.getTime() || dateEnd2.getTime() < dateStart1.getTime()){
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 校验两个时间段是否交叉
	 * @param dateStart1 时间段1开始日期
	 * @param dateEnd1 时间段1结束日期
	 * @param dateStart2 时间段2开始日期
	 * @param dateEnd2 时间段2结束日期
	 * @param fmt 日期格式
	 * @return
	 */
	public static boolean isOverlapping(String dateStart1, String dateEnd1, String dateStart2, String dateEnd2, String fmt){
		return isOverlapping(BaseUtil.getDate(dateStart1, fmt), BaseUtil.getDate(dateEnd1, fmt), BaseUtil.getDate(dateStart2, fmt), BaseUtil.getDate(dateEnd2, fmt));
	}
	
	/**
	 * 校验两个时间段是否交叉
	 * @param dateStart1 时间段1开始日期（按yyyy-MM-dd HH:mm:ss格式解析）
	 * @param dateEnd1 时间段1结束日期（按yyyy-MM-dd HH:mm:ss格式解析）
	 * @param dateStart2 时间段2开始日期（按yyyy-MM-dd HH:mm:ss格式解析）
	 * @param dateEnd2 时间段2结束日期（按yyyy-MM-dd HH:mm:ss格式解析）
	 * @return
	 */
	public static boolean isOverlapping(String dateStart1, String dateEnd1, String dateStart2, String dateEnd2){
		String fmt = "yyyy-MM-dd HH:mm:ss";
		return isOverlapping(BaseUtil.getDate(dateStart1, fmt), BaseUtil.getDate(dateEnd1, fmt), BaseUtil.getDate(dateStart2, fmt), BaseUtil.getDate(dateEnd2, fmt));
	}
	
	/**
	 * 检测开始日期和结束日期是否和容器日期集合存在交叉冲突
	 * @param dateStart 要检测的开始日期对象（按yyyy-MM-dd HH:mm:ss格式解析）
	 * @param dateEnd 要检测的结束日期对象（按yyyy-MM-dd HH:mm:ss格式解析）
	 * @param overlapDate 要检测的日期容器对象，该对象数组必须有两个长度，第一个为开始日期，第二个为结束日期
	 * @return
	 */
	public static boolean isOverlapping(String dateStart, String dateEnd, List<Date[]> overlapDate){
		String fmt = "yyyy-MM-dd HH:mm:ss";
		Date sDate = BaseUtil.getDate(dateStart, fmt);
		Date eDate = BaseUtil.getDate(dateEnd, fmt);
		return isOverlapping(sDate, eDate, overlapDate);
	}
	
	/**
	 * 检测开始日期和结束日期是否和容器日期集合存在交叉冲突
	 * @param dateStart 要检测的开始日期对象
	 * @param dateEnd 要检测的结束日期对象
	 * @param overlapDate 要检测的日期容器对象，该对象数组必须有两个长度，第一个为开始日期，第二个为结束日期
	 * @return
	 */
	public static boolean isOverlapping(Date dateStart, Date dateEnd, List<Date[]> overlapDate){
		if(dateStart == null || dateEnd == null || overlapDate == null){
			return false;
		}
		boolean flag = false;
		for(Date[] dates : overlapDate){
			if(dates == null || dates.length < 2){
				continue;
			}
			
			flag = flag || isOverlapping(dateStart, dateEnd, dates[0], dates[1]);
		}
		return flag;
	}
	
	/**
	 * 获取交叉时间段
	 * @param dateStart1 时段1开始日期
	 * @param dateEnd1 时段1结束日期
	 * @param dateStart2 时段2开始日期
	 * @param dateEnd2 时段2结束日期
	 * @return
	 */
	public static Date[] getOverlappingDate(Date dateStart1, Date dateEnd1, Date dateStart2, Date dateEnd2){
		if(isOverlapping(dateStart1, dateEnd1, dateStart2, dateEnd2)){
			if(dateStart2.getTime() > dateStart1.getTime()){
				dateStart1 = dateStart2;
			}
			if(dateEnd2.getTime() < dateEnd1.getTime()){
				dateEnd1 = dateEnd2;
			}
			return new Date[]{dateStart1, dateEnd1};
		} else {
			return null;
		}
	}
	
	/**
	 * 获取交叉时间段
	 * @param dateStart1 时段1开始日期
	 * @param dateEnd1 时段1结束日期
	 * @param dateStart2 时段2开始日期
	 * @param dateEnd2 时段2结束日期
	 * @param fmt 日期格式
	 * @return
	 */
	public static String[] getOverlappingDate(String dateStart1, String dateEnd1, String dateStart2, String dateEnd2, String fmt){
		Date[] res = getOverlappingDate(BaseUtil.getDate(dateStart1, fmt), BaseUtil.getDate(dateEnd1, fmt), BaseUtil.getDate(dateStart2, fmt), BaseUtil.getDate(dateEnd2, fmt));
		if(res == null || res.length != 2 || res[0] == null || res[1] == null){
			return null;
		} else {
			return new String[]{BaseUtil.format(res[0], fmt), BaseUtil.format(res[1], fmt)};
		}
	}
	
	/**
	 * 获取非交叉时间段（和基准时段比较）
	 * @param baseStart 基准时段开始日期
	 * @param baseEnd 基准时段结束日期
	 * @param crossStart 交叉时段开始日期
	 * @param crossEnd 交叉时段结束日期
	 * @return 非交叉时段集合，奇数位是开始日期，偶数位是结束日期
	 */
	public static List<Date> getNotOverlappingDate(Date baseStart, Date baseEnd, Date crossStart, Date crossEnd){
		if(baseStart == null || baseEnd == null){
			return null;
		}
		List<Date> dts = new ArrayList<Date>();
		if(crossStart == null || crossEnd == null || crossStart.getTime() >= crossEnd.getTime()){
			dts.add(baseStart);
			dts.add(baseEnd);
			return dts;
		}
		
		Date[] cd =getOverlappingDate(baseStart, baseEnd, crossStart, crossEnd);
		if(cd == null){
			dts.add(baseStart);
			dts.add(baseEnd);
		} else {
			if(cd[0].getTime() > baseStart.getTime()){
				dts.add(baseStart);
				dts.add(cd[0]);
			}
			if(cd[1].getTime() < baseEnd.getTime()){
				dts.add(cd[1]);
				dts.add(baseEnd);
			}
		}
		return dts;
	}
	
	/**
	 * 获取非交叉时间段（和基准时段比较）
	 * @param baseStart 基准时段开始日期
	 * @param baseEnd 基准时段结束日期
	 * @param cross 交叉时段
	 * @return 非交叉时段集合，奇数位是开始日期，偶数位是结束日期
	 */
	public static List<Date> getNotOverlappingDate(Date baseStart, Date baseEnd, List<Date> cross){
		if(baseStart == null || baseEnd == null){
			return null;
		}
		List<Date> res = new ArrayList<Date>();
		if(cross == null || cross.size() == 0 || cross.size() % 2 != 0){
			res.add(baseStart);
			res.add(baseEnd);
			return res;
		}
		int count = cross.size() / 2;
		res.add(baseStart);
		res.add(baseEnd);
		for(int i=0; i<count; i++){
			res = getNotOverlappingDate(res, cross.get(i*2), cross.get(i*2+1));
			if(res == null || res.size() == 0){
				continue;
			}
		}
		if(res == null || res.size() == 0){
			return null;
		} else {
			return res;
		}
	}
	
	/**
	 * 获取非交叉时间段（和基准时段比较）
	 * @param base 基准日期,奇数位是开始日期，偶数位是结束日期
	 * @param crossStart 交叉时段开始日期
	 * @param crossEnd 交叉时段结束日期
	 * @return
	 */
	public static List<Date> getNotOverlappingDate(List<Date> base, Date crossStart, Date crossEnd){
		if(base == null || base.size() == 0 || base.size() % 2 != 0){
			return null;
		}
		
		List<Date> res = new ArrayList<Date>();
		int count = base.size() / 2;
		for(int i=0; i<count; i++){
			List<Date> tempD = getNotOverlappingDate(base.get(i*2), base.get(i*2+1), crossStart, crossEnd);
			if(tempD != null && tempD.size() > 0){
				res.addAll(tempD);
			}
		}
		if(res == null || res.size() == 0){
			return null;
		} else {
			return res;
		}
	}
	
	/**
	 * 获取非交叉时间段（和基准时段比较）
	 * @param base 基准时段
	 * @param cross 交叉时段
	 * @return 非交叉时段集合，奇数位是开始日期，偶数位是结束日期
	 */
	public static List<Date> getNotOverlappingDate(List<Date> base, List<Date> cross){
		if(base == null || base.size() == 0 || base.size() % 2 != 0){
			return null;
		}
		
		if(cross == null || cross.size() == 0 || cross.size() % 2 != 0){
			return base;
		}
		
		List<Date> res = new ArrayList<Date>();
		res.addAll(base);
		int count = cross.size() / 2;
		for(int i=0; i<count; i++){
			res = getNotOverlappingDate(res, cross.get(i*2), cross.get(i*2+1));
		}
		
		if(res == null || res.size() == 0){
			return null;
		} else {
			return res;
		}
	}
	
	/**
	 * 获取时间段并集
	 * @param times 时段集合，奇数位是开始日期，偶数位是结束日期
	 * @return 时段集合，奇数位是开始日期，偶数位是结束日期
	 */
	public static List<Date> getUnionDate(List<Date> times){
		if(times == null || times.size() == 0 || times.size() % 2 != 0){
			return null;
		}
		
		List<Date> res = new ArrayList<Date>();
		
		int count = times.size() / 2;
		for(int i=0; i<count; i++){
			Date startDate = times.get(i*2);
			Date endDate = times.get(i*2+1);
			if(res.size() == 0){
				res.add(startDate);
				res.add(endDate);
				continue;
			}
			
			List<Date> resTemp = new ArrayList<Date>();
			boolean isOverlapping = false;
			int count1 = res.size() / 2;
			for(int j=0; j<count1; j++){
				Date _startDate = res.get(j*2);
				Date _endDate = res.get(j*2+1);
				if(isOverlapping(startDate, endDate, _startDate, _endDate)){
					if(orderDateTime(startDate, _startDate) > 0){
						_startDate = startDate;
					}
					if(orderDateTime(_endDate, endDate) > 0){
						_endDate = endDate;
					}
					isOverlapping = true;
				}
				resTemp.add(_startDate);
				resTemp.add(_endDate);
			}
			if(!isOverlapping){
				resTemp.add(startDate);
				resTemp.add(endDate);
			}
			res = resTemp;
		}
		
		return res;
	}
	
	/**
	 * 获取时间段集合中，最小时间和最大时间
	 * @param times 时段集合，奇数位是开始日期，偶数位是结束日期
	 * @return 元素1：最小时间；元素2：最大时间
	 */
	public static List<Date> getMinAndMaxDate(List<Date> times){
		if(times == null || times.size() == 0){
			return null;
		}
		List<Date> res = new ArrayList<Date>();
		Date min = null, max = null;
		for(Date t : times){
			if(min == null){
				min = t;
			} else {
				if(t.getTime() < min.getTime()){
					min = t;
				}
			}
			if(max == null){
				max = t;
			} else {
				if(t.getTime() > max.getTime()){
					max = t;
				}
			}
		}
		res.add(min);
		res.add(max);
		
		return res;
	}
	
	/**
	 * 判断两个日期是否是同一天
	 * @param date1 日期对象1
	 * @param date2 日期对象2
	 * @return
	 */
	public static boolean isSameDate(Date date1, Date date2){
		if(date1 == null || date2 == null){
			return false;
		}
		Calendar d1 = Calendar.getInstance();
		d1.setTime(date1);
		Calendar d2 = Calendar.getInstance();
		d2.setTime(date2);
		
		return d1.get(Calendar.YEAR) == d2.get(Calendar.YEAR)
				&& d1.get(Calendar.MONTH) == d2.get(Calendar.MONTH)
				&& d1.get(Calendar.DAY_OF_MONTH) == d2.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获取32位uuid
	 * @return
	 */
	public static String getUUID32(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 获取16位uuid
	 * @return
	 */
	public static String getUUID16(){
		 int first = new Random(10).nextInt(8) + 1;
         int hashCodeV = UUID.randomUUID().toString().hashCode();
         if (hashCodeV < 0) {//有可能是负数
             hashCodeV = -hashCodeV;
         }
         // 0 代表前面补充0
         // 4 代表长度为4
         // d 代表参数为正数型
         return first + String.format("%015d", hashCodeV);
	}
	
	/**
	 * 添加urlrequest变量
	 * @param url url地址
	 * @param param 变量
	 * @return
	 */
	public static String addRequestParams(String url, String param){
		if(url == null){
			return "";
		}
		if(param.indexOf('&') == 0){
			if(param.length() > 1){
				param = param.substring(1);
			}
		}
		int index = url.indexOf("?");
		if(index >= 0){
			if(index == url.length() - 1){
				return url + param;
			} else {
				return url + "&" + param;
			}
		} else {
			return url + "?" + param;
		}
	}
	
	/**
	 * 拼接生成URL请求参数
	 * @param params 参数集合
	 * @return
	 */
	public static String genRequestParams(Map<String, String> params){
		return genRequestParams(params, new HashMap<String, String>());
	}
	
	/**
	 * 拼接生成URL请求参数
	 * @param params 参数集合
	 * @param keys 要拼接的参数集合
	 * @return
	 */
	public static String genRequestParams(Map<String, String> params, String[] keys){
		if(keys == null || keys.length == 0){
			return genRequestParams(params, new HashMap<String, String>());
		} else {
			Map<String, String> needParam = new HashMap<String, String>();
			for(String key : keys){
				needParam.put(key, key);
			}
			return genRequestParams(params, needParam);
		}
	}
	
	/**
	 * 拼接生成URL请求参数
	 * @param params 参数集合
	 * @param needParam 要拼接的参数集合;key表示URL拼接后传输的字段名，value该参数的来源字段名
	 * @return
	 */
	public static String genRequestParams(Map<String, String> params, Map<String, String> needParam){
		if(params == null){
			return "";
		}
		StringBuffer ps = new StringBuffer();
		if(needParam == null || needParam.size() == 0){
			for(String key : params.keySet()){
				try {
					ps.append("&").append(key).append("=").append(URLEncoder.encode(URLEncoder.encode(BaseUtil.trim(params.get(key)), "UTF-8"), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					LOGGER.info("编码转换失败！", e);
				}
			}
		} else {
			for(String key : needParam.keySet()){
				try {
					ps.append("&").append(BaseUtil.trim(key)).append("=").append(URLEncoder.encode(URLEncoder.encode(BaseUtil.trim(params.get(BaseUtil.trim(needParam.get(key)))), "UTF-8"), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					LOGGER.info("编码转换失败！", e);
				}
			}
		}
		
		return ps.toString();
	}
	
	/**
	 * 拼接生成URL请求参数
	 * @param params 参数集合
	 * @param needKeys 表示URL拼接后传输的字段名
	 * @param needValues 表示对应needKeys参数的来源字段名
	 * @return
	 */
	public static String genRequestParams(Map<String, String> params, String[] needKeys, String[] needValues){
		if(params == null){
			return "";
		}
		if(needKeys == null || needValues == null || needKeys.length != needValues.length || needKeys.length == 0){
			return genRequestParams(params);
		}
		
		Map<String, String> needParams = new HashMap<String, String>();
		for(int i=0; i<needValues.length; i++){
			needParams.put(needKeys[i], needValues[i]);
		}
		
		return genRequestParams(params, needParams);
	}
	
	/**
	 * 获取request参数到集合
	 * @param request request对象
	 * @return
	 */
	public static Map<String, String> getAllParamter(HttpServletRequest request){
		return getAllParamter(request, null, null);
	}
	
	/**
	 * 获取request参数到集合
	 * @param request request对象
	 * @param needKeys 需要获取的参数键集合，为null则传输所有参数
	 * @return
	 */
	public static Map<String, String> getAllParamter(HttpServletRequest request, String[] needKeys){
		return getAllParamter(request, needKeys, null);
	}
	
	/**
	 * 获取request参数到集合
	 * @param request request对象
	 * @param needKeys 需要获取的参数键集合，为null则传输所有参数
	 * @param tranferKeys 需要传输的参数名集合
	 * @return
	 */
	public static Map<String, String> getAllParamter(HttpServletRequest request, String[] needKeys, String[] tranferKeys){
		Map<String, String> requestParams = new HashMap<String, String>();
		if(request == null){
			return requestParams;
		}
		
		if(needKeys == null){ //获取全部的key
			Enumeration<String> keys = request.getParameterNames();
			while(keys.hasMoreElements()){
				String key = keys.nextElement();
				requestParams.put(key, BaseUtil.trim(request.getParameter(key)));
			}
		} else { //获取特定的key
			for(int i=0; i<needKeys.length; i++){
				String key = needKeys[i];
				String value = trim(request.getParameter(key));
				if(tranferKeys == null || tranferKeys.length - 1 < i){
					requestParams.put(key, value);
				} else {
					requestParams.put(tranferKeys[i], value);
				}
			}
		}
		
		return requestParams;
	}

	
	/**
	 * 从request对象获取一组参数值
	 * @param request request对象
	 * @param params 参数数组
	 * @return
	 */
	public static String[] getValueFromRequest(HttpServletRequest request, String[] params){
		if(params == null || params.length == 0){
			return null;
		}
		String[] values = new String[params.length];
		for(int i=0; i<params.length; i++){
			values[i] = BaseUtil.trim(request.getParameter(params[i]));
		}
		return values;
	}
	
	/**
	 * 解码request参数，防止中文乱码
	 * @param request request对象
	 * @param key 要获取的参数key
	 * @return
	 */
	public static String getDecodeParam(HttpServletRequest request, String key){
		key = trim(key);
		if(request == null || "".equals(key)){
			return "";
		}
		
		try {
			String value = URLDecoder.decode(trim(request.getParameter(key)), "UTF-8");
			return value;
		} catch (UnsupportedEncodingException e) {
			LOGGER.info("参数[" + key + "]解码失败！", e);
		}
		
		return "";
	}
	
	/**
	 * 解码request参数，防止中文乱码
	 * @param value 要解码的字符串
	 * @return
	 */
	public static String getDecodeParamValue(String value){
		try {
			return URLDecoder.decode(trim(value), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.info("参数[" + value + "]解码失败！", e);
		}
		
		return "";
	}
	
	/**
	 * 比较两个日期大小
	 * @param date1 日期1
	 * @param date2 日期2
	 * @param fmt 格式
	 * @return 日期1小于日期2时，返回1；日期1等于日期2时，返回0；日期1大于日期2时，返回-1；如格式转换失败，返回0
	 */
	public static int orderDate(String date1, String date2, String fmt){
		Date d1 = getDate(date1, fmt);
		Date d2 = getDate(date2, fmt);
		if(d1 == null || d2 == null){
			return 0;
		}
		
		if(d1.getTime() < d2.getTime()){
			return 1;
		} else if(d1.getTime() == d2.getTime()){
			return 0;
		} else {
			return -1;
		}
	}
	
	/**
	 * 比较两个日期大小
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return 日期1小于日期2时，返回1；日期1等于日期2时，返回0；日期1大于日期2时，返回-1；如格式转换失败，返回0
	 */
	public static int orderDateTime(Date date1, Date date2){
		if(date1 == null || date2 == null){
			return 0;
		}
		if(date1.getTime() < date2.getTime()){
			return 1;
		} else if(date1.getTime() == date2.getTime()){
			return 0;
		} else {
			return -1;
		} 
	}
	
	/**
	 * 比较两个日期大小(只比较日期的年月日部分)
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return 日期1小于日期2时，返回1；日期1等于日期2时，返回0；日期1大于日期2时，返回-1；如格式转换失败，返回0
	 */
	public static int orderDate(Date date1, Date date2){
		if(date1 == null || date2 == null){
			return 0;
		}
		Date d1 = BaseUtil.getDate(BaseUtil.format(date1, "yyyy-MM-dd"), "yyyy-MM-dd");
		Date d2 = BaseUtil.getDate(BaseUtil.format(date2, "yyyy-MM-dd"), "yyyy-MM-dd");
		if(d1.getTime() < d2.getTime()){
			return 1;
		} else if(d1.getTime() == d2.getTime()){
			return 0;
		} else {
			return -1;
		}
	}
	
	/**
	 * 计算地球上任意两点(经纬度)距离
	 * @param long1 第一点经度
	 * @param lat1 第一点纬度
	 * @param long2 第二点经度
	 * @param lat2 第二点纬度
	 * @return 返回距离 单位：米
	 */
	public static double gpsDistance(double long1, double lat1, double long2, double lat2) {
		double a, b, R;
		R = 6378137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2
			* R
			* Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
					* Math.cos(lat2) * sb2 * sb2));
		return d;
	}
	
	/**
	 * 判断项目文件是否存在
	 * @param filePath 文件路径
	 * @return
	 */
	public static boolean isProjectFileExist(String filePath){
		return isFileExist(BaseUtil.getRootPath() + filePath.replaceAll("[/\\]", File.separator));
	}
	
	/**
	 * 判断文件是否存在
	 * @param filePath 文件路径
	 * @return
	 */
	public static boolean isFileExist(String filePath){
		try{
			File f = new File(filePath);
			if(f.isFile() && f.exists()){
				return true;
			}
		} catch(Exception e){ }
		return false;
	}
	
	/**
	 * Base64加密
	 * @param bstr
	 * @return
	 */
	public static String base64Encode(byte[] bstr){
		return new sun.misc.BASE64Encoder().encode(bstr);
	}
	
	/**
	 * Base64解密
	 * @param str
	 * @return
	 */
	public static byte[] base64Decode(String str){
		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer( str );
		} catch (Exception e) { }
		return bt;
	}
	
	/**
	 * 判断是否是内网ip
	 * @param ip 内网ip
	 * @return
	 */
	public static boolean isInnerIp(String ip){
	    String reg = "(10|172|192)\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})";//正则表达式=。 =、懒得做文字处理了、
	    Pattern p = Pattern.compile(reg);
	    Matcher matcher = p.matcher(ip);
	    return matcher.find() || "127.0.0.1".equals(ip) || "localhost".equals(ip);
	}
	
	/**
	 * 异常对象转换为字符串
	 * @param e 异常对象
	 * @return
	 */
	public static String exceptionToString(Throwable e){
		if(e == null){
			return "";
		}
		String str = "";
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try{
			e.printStackTrace(pw);
			str = sw.toString();
		}finally{
			pw.close();
		}
		return str;
	}
	
	/**
	 * 按天数拆分日期区间段
	 * @param startDate 开始日期，格式：yyyy-MM-dd
	 * @param endDate 结束日期，格式：yyyy-MM-dd
	 * @param dayNum 天数,必须大于0
	 * @return startDate:开始日期  endDate：结束日期
	 */
	public static List<Map<String, String>> splitDateRange(String startDate, String endDate, int dayNum){
		List<Map<String, String>> dts = new ArrayList<Map<String, String>>();
		Calendar _sd = Calendar.getInstance();
		Calendar _sdOld = Calendar.getInstance();
		_sd.setTime(getDate(startDate, "yyyy-MM-dd"));
		Date _ed = getDate(endDate, "yyyy-MM-dd");
		do{
			_sdOld.setTime(_sd.getTime());
			_sd.add(Calendar.DAY_OF_YEAR, dayNum - 1);
			
			Map<String, String> dt = new HashMap<String, String>();
			dts.add(dt);
			dt.put("startDate", format(_sdOld.getTime(), "yyyy-MM-dd"));
			if(_sd.getTime().getTime() >= _ed.getTime()){
				dt.put("endDate", format(_ed.getTime(), "yyyy-MM-dd"));
				break;
			} else {
				dt.put("endDate", format(_sd.getTime(), "yyyy-MM-dd"));
			}
			_sd.add(Calendar.DAY_OF_YEAR, 1);
		} while(_sd.getTime().getTime() < _ed.getTime());
		
		return dts;
	}
	
	/**
	 * 按天数拆分日期区间段
	 * @param startDate 开始日期，格式：yyyy-MM-dd HH:mm:ss
	 * @param endDate 结束日期，格式：yyyy-MM-dd HH:mm:ss
	 * @param dayNum 天数,必须大于0
	 * @return startDate:开始日期  endDate：结束日期
	 */
	public static List<Map<String, String>> splitDateTimeRange(String startDate, String endDate, int dayNum){
		List<Map<String, String>> dts = new ArrayList<Map<String, String>>();
		Calendar _sd = Calendar.getInstance();
		Calendar _sdOld = Calendar.getInstance();
		_sd.setTime(getDate(startDate, "yyyy-MM-dd HH:mm:ss"));
		Date _ed = getDate(endDate, "yyyy-MM-dd HH:mm:ss");
		do{
			_sdOld.setTime(_sd.getTime());
			_sd.add(Calendar.HOUR, (dayNum - 1) * 24);
			
			Map<String, String> dt = new HashMap<String, String>();
			dts.add(dt);
			dt.put("startDate", format(_sdOld.getTime(), "yyyy-MM-dd HH:mm:ss"));
			if(_sd.getTime().getTime() >= _ed.getTime()){
				dt.put("endDate", format(_ed.getTime(), "yyyy-MM-dd HH:mm:ss"));
				break;
			} else {
				dt.put("endDate", format(_sd.getTime(), "yyyy-MM-dd HH:mm:ss"));
			}
			_sd.add(Calendar.SECOND, 1);
		} while(_sd.getTime().getTime() < _ed.getTime());
		
		return dts;
	}
	
	/**
	 * 检查字符串是否存在
	 * @param igoreCase 是否大小写敏感
	 * @param str 要查询的字符串
	 * @param queryStr 匹配数组（其中之一匹配即可）
	 * @return
	 */
	public static boolean isStrExist(boolean igoreCase, String str, String...queryStr){
		boolean isExist = false;
		str = trim(str);
		if(!"".equals(str) && queryStr.length > 0){
			for(String qs : queryStr){
				qs = trim(qs);
				if(igoreCase){
					isExist |= qs.equalsIgnoreCase(str);
				} else {
					isExist |= qs.equals(str);
				}
				if(isExist){
					break;
				}
			}
		}
		
		return isExist;
	}
	
	/**
	 * 生成范围内的随机数
	 * @param startNum 随机数开始范围
	 * @param endNum 随机数结束范围
	 * @return
	 */
	public static int randToInt(int startNum, int endNum){
		Random rand = new Random();
		return rand.nextInt(endNum - startNum + 1) + startNum;
	}
	
	/**
	 * 生成范围内的随机数
	 * @param startNum 随机数开始范围
	 * @param endNum 随机数结束范围
	 * @param numSize 生成的随机数个数
	 * @return
	 */
	public static int[] randToInt(int startNum, int endNum, int numSize){
		Random rand = new Random();
		int[] res = new int[numSize];
		for(int i=0; i<numSize; i++){
			res[i] = rand.nextInt(endNum - startNum + 1) + startNum;
		}
		return res;
	}
	
	/**
	 * 是否是Base64字符串
	 * @param src 要判定的字符串
	 * @return
	 */
	public static boolean isBase64(String src) {
		src = src==null? "" :src;
		src = src.replaceAll("[-]", "+").replaceAll("[_]", "/").replaceAll("[.]", "=");
		return isArrayByteBase64(src.getBytes());
	}

	/**
	 * 是否是Base64字节
	 * @param src 要判定的字节
	 * @return
	 */
	public static boolean isBase64(byte src) {
		return (src == 61) || (base64Alphabet[src] != -1);
	}

	/**
	 * 是否是Base64字节数组
	 * @param src 要判定的数组
	 * @return
	 */
	public static boolean isArrayByteBase64(byte[] src) {
		int i = src.length;
		if (i == 0) {
			return true;
		}
		for (int j = 0; j < i; j++) {
			if (!isBase64(src[j]))
				return false;
		}
		return true;
	}

}