package com.eshore.datasupport.common.util; 

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;


public class DateUtils {
	
	private final static String YYYYMM2="yyyyMM";
	private final static String YYYYMM="yyyy-MM";
	private final static String YYYYMMDD="yyyy-MM-dd";
	private final static String YYYYMMDDHHMMSS="yyyy-MM-dd HH:mm:ss";
	private final static String YYYY="yyyy";
 
	private DateUtils(){}
	
	/**
	 * 
	 * @param formatStr
	 * @return
	 */
	public static DateFormat getDateFormat(String formatStr){
		return new SimpleDateFormat(formatStr);
	}
	
	public static String getNow(){
		DateFormat formatYYYYMMDDHHMMSS=getDateFormat(YYYYMMDDHHMMSS);
		return formatYYYYMMDDHHMMSS.format(new Date());
	}
	
	public static String[] getDayStartAndEnd(Date date){
		
		DateFormat formatYYYYMMDD=getDateFormat(YYYYMMDD);
		String yyyymmdd = formatYYYYMMDD.format(date);
		String[] s = new String[2];
		s[0] = yyyymmdd + " 00:00:00" ;
		
		Calendar calen = Calendar.getInstance();
		calen.setTime(date);
		calen.add(Calendar.DAY_OF_MONTH, 1);//加1天
		yyyymmdd = formatYYYYMMDD.format(calen.getTime());
		s[1] = yyyymmdd + " 00:00:00" ;
		return s;
	}
	
	public static String[] getWeekStartAndEnd(Date date){
		
		Calendar calen = Calendar.getInstance();
		calen.setTime(date);
		int week=calen.get(Calendar.DAY_OF_WEEK)-1; 
		if(week==0){
			calen.add(Calendar.WEEK_OF_YEAR, -1);
	    }
		calen.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		DateFormat formatYYYYMMDD=getDateFormat(YYYYMMDD);
		String[] s = new String[2];
		s[0] = formatYYYYMMDD.format(calen.getTime())+ " 00:00:00";
		calen.add(Calendar.WEEK_OF_YEAR, 1);
		String yyyymmdd = formatYYYYMMDD.format(calen.getTime());
		s[1] = yyyymmdd + " 00:00:00" ;
		return s;
	}
	/**
	 * 获取date的月初，月末
	 * @param date
	 * @return
	 */
	public static String[] getMonthStartAndEnd(Date date){
		
		DateFormat formatYYYYMM=getDateFormat(YYYYMM);
		String yyyymm = formatYYYYMM.format(date);
		String[] s = new String[2];
		s[0] = yyyymm + "-01 00:00:00" ;
		
		Calendar calen = Calendar.getInstance();
		calen.setTime(date);
		calen.set(Calendar.MONTH, calen.get(Calendar.MONTH)+1);
		String yyyymmdd = formatYYYYMM.format(calen.getTime());
		s[1] = yyyymmdd + "-01 00:00:00" ;
		return s;
	}
	
	public static String[] getMonthStartAndEnd2(Date date){
		
		DateFormat formatYYYYMM2=getDateFormat(YYYYMM2);
		String yyyymm = formatYYYYMM2.format(date);
		String[] s = new String[2];
		s[0] = yyyymm + "01 00:00:00" ;
		
		Calendar calen = Calendar.getInstance();
		calen.setTime(date);
		calen.set(Calendar.MONTH, calen.get(Calendar.MONTH)+1);
		String yyyymmdd = formatYYYYMM2.format(calen.getTime());
		s[1] = yyyymmdd + "01 00:00:00" ;
		return s;
	}
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String[] getYearStartAndEnd(Date date){
		
		DateFormat formatYYYY=getDateFormat(YYYY);
		String yyyy = formatYYYY.format(date);
		String[] s = new String[2];
		s[0] = yyyy + "-01-01 00:00:00" ;
		
		Calendar calen = Calendar.getInstance();
		calen.setTime(date);
		calen.set(Calendar.YEAR, calen.get(Calendar.YEAR)+1);
		String yyyymm = formatYYYY.format(calen.getTime());
		s[1] = yyyymm + "-01-01 00:00:00" ;
		return s;
	}
	
	/**
	 * 年偏移计算
	 * @param date
	 * @param arrow 支持正数负数
	 * @return
	 */
	public static Date getYear(Date date,String arrow){
		String arrowTemp=arrow;
		if(StringUtils.isEmpty(arrowTemp)){
			arrowTemp = "0";
		}
		Calendar calen = Calendar.getInstance();
		calen.setTime(date);
		calen.set(Calendar.YEAR, calen.get(Calendar.YEAR)+Integer.parseInt(arrowTemp));
		return calen.getTime();
	}
	
	/**
	 * 月偏移计算
	 * @param date
	 * @param arrow 支持正数负数
	 * @return
	 */
	public static Date getMonth(Date date,String arrow){
		String arrowTemp=arrow;
		if(StringUtils.isEmpty(arrowTemp)){
			arrowTemp = "0";
		}
		Calendar calen = Calendar.getInstance();
		calen.setTime(date);
		calen.set(Calendar.MONTH, calen.get(Calendar.MONTH)+Integer.parseInt(arrowTemp));
		return calen.getTime();
	}
	/**
	 * 周偏移计算
	 * @param date
	 * @param arrow 支持正数负数
	 * @return
	 */
	public static Date getWeek(Date date,String arrow){
		String arrowTemp=arrow;
		if(StringUtils.isEmpty(arrowTemp)){
			arrowTemp = "0";
		}
		Calendar calen = Calendar.getInstance();
		calen.setTime(date);
		calen.add(Calendar.WEEK_OF_YEAR, Integer.parseInt(arrowTemp));
		return calen.getTime();
	}
	
	/**
	 * 获取天
	 * @param date
	 
	 * @return
	 */
	public static int getDay(Date date){
		 
		Calendar calen = Calendar.getInstance();
		calen.setTime(date);		 
		return calen.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获取月
	 * @param date
	 
	 * @return
	 */
	public static int getMonth(Date date){
		 
		Calendar calen = Calendar.getInstance();
		calen.setTime(date);		 
		return calen.get(Calendar.MONTH);
	}
	/**
	 * 天偏移计算
	 * @param date
	 * @param arrow  支持正数负数
	 * @return
	 */
	public static Date getDay(Date date,String arrow){
		String arrowTemp=arrow;
		if(StringUtils.isEmpty(arrowTemp)){
			arrowTemp = "0";
		}
		Calendar calen = Calendar.getInstance();
		calen.setTime(date);
		calen.add(Calendar.DAY_OF_MONTH, Integer.parseInt(arrowTemp));
		return calen.getTime();
	}
	/**
	 * 小时偏移计算
	 * @param date
	 * @param arrow 支持正数负数
	 * @return
	 */
	public static Date getHour(Date date,String arrow){
		String arrowTemp=arrow;
		if(StringUtils.isEmpty(arrowTemp)){
			arrowTemp = "0";
		}
		Calendar calen = Calendar.getInstance();
		calen.setTime(date);
		calen.add(Calendar.HOUR_OF_DAY, Integer.parseInt(arrowTemp));
		return calen.getTime();
	}
	
	/**
	 * 分钟偏移计算
	 * @param date
	 * @param arrow 支持正数负数
	 * @return
	 */
	public static Date getMinute(Date date,String arrow){
		String arrowTemp=arrow;
		if(StringUtils.isEmpty(arrowTemp)){
			arrowTemp = "0";
		}
		Calendar calen = Calendar.getInstance();
		calen.setTime(date);
		calen.add(Calendar.MINUTE, Integer.parseInt(arrowTemp));
		return calen.getTime();
	}
 

}
