package com.fn.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatetimeUtil {

	public final static String DATETIME_PATTERN = "yyyyMMddHHmmss";

	public final static String TIME_STAMP_PATTERN = "yyyyMMddHHmmssSSS";

	public final static String DATE_PATTERN = "yyyyMMdd";

	public final static String TIME_PATTERN = "HHmmss";

	public final static String STANDARD_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public final static String STANDARD_DATETIME_PATTERN_HM = "yyyy-MM-dd HH:mm";

	public final static String STANDARD_DATE_PATTERN = "yyyy-MM-dd";

	public final static String STANDARD_TIME_PATTERN = "HH:mm:ss";

	public final static String STANDARD_DATETIME_PATTERN_SOLIDUS = "yyyy/MM/dd HH:mm:ss";

	public final static String STANDARD_DATETIME_PATTERN_SOLIDUS_HM = "yyyy/MM/dd HH:mm";

	public final static String STANDARD_DATE_PATTERN_SOLIDUS = "yyyy/MM/dd";

	private DatetimeUtil() {
		super();
	}

	public static Timestamp currentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Integer currentTimeSeconds() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	public static String currentDatetime() {
		return formatDate(new Date());
	}

	public static Timestamp parseDate(String dateStr, String pattern) throws ParseException {
		Date d = DatetimeUtil.parse(dateStr, pattern);
		return new Timestamp(d.getTime());
	}

	public static Timestamp parseDate(String dateStr) throws ParseException {
		return parseDate(dateStr, STANDARD_DATE_PATTERN);
	}

	public static java.sql.Date parseSQLDate(String dateStr, String pattern) throws ParseException {
		Date d = parse(dateStr, pattern);
		return new java.sql.Date(d.getTime());
	}

	public static java.sql.Date parseSQLDate(String dateStr) throws ParseException {
		Date d = parse(dateStr, STANDARD_DATE_PATTERN);
		return new java.sql.Date(d.getTime());
	}

	public static Timestamp getFutureTime(int month) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, month);
		return new Timestamp(c.getTimeInMillis());
	}

	/**
	 * 显示今天时间
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String today() {
		return formatDateTime(new Date());
	}

	public static String formatDate(Timestamp t) {
		return formatDate(new Date(t.getTime()));
	}

	public static String formatDate(Timestamp t, String pattern) {
		return formatDate(new Date(t.getTime()), STANDARD_DATE_PATTERN);
	}

	public static String formatDateTime(Timestamp t, String pattern) {
		return formatDate(new Date(t.getTime()), STANDARD_DATETIME_PATTERN);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String formatDate(Date date) {
		return formatDate(date, STANDARD_DATE_PATTERN);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, STANDARD_DATETIME_PATTERN);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null)
			return null;
		DateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 获取当前时间 yyyy-MM-dd
	 * 
	 * @return yyyy-MM-dd 格式的日期
	 */
	public static Date getShortDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(0);
		Date currentTime_2 = formatter.parse(dateString, pos);

		return currentTime_2;
	}

	/**
	 * 获取当前时间 yyyy-MM-dd
	 * 
	 * @return yyyy-MM-dd格式的日期字符串
	 */
	public static String getShortDateString() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);

		return dateString;
	}

	/**
	 * 解析日期
	 * 
	 * @param dateStr
	 *            yyyy-MM-dd
	 * @return
	 */
	public static Date parse(String dateStr) {
		return parse(dateStr, STANDARD_DATE_PATTERN);
	}

	/**
	 * 解析日期
	 * 
	 * @param dateStr
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date parseTime(String dateStr) {
		return parse(dateStr, STANDARD_DATETIME_PATTERN);
	}

	public static Date parse(String dateStr, String pattern) {

		try {
			DateFormat format = new SimpleDateFormat(pattern);
			return format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 当月的第一天
	 * 
	 * @return
	 */
	public static String firstDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return DatetimeUtil.formatDate(calendar.getTime()) + " 00:00:00";
	}

	/**
	 * 当月的最后一天
	 * 
	 * @return
	 */
	public static String lastDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return DatetimeUtil.formatDate(calendar.getTime()) + " 23:59:59";
	}

	/**
	 * @Description 秒转换成日期 yyyy-MM-dd HH:mm
	 * @param dateStr
	 * @return
	 */
	public static String getDateStr(int time) {
		DateFormat format = new SimpleDateFormat(STANDARD_DATETIME_PATTERN_HM);
		return format.format(new Date((long) time * 1000));
	}

	/**
	 * @Description 秒转换成时间 mm:ss
	 * @param timeStr
	 * @return
	 */
	public static String getTimeStr(Short time) {
		int minute = time / 60;
		int second = time % 60;
		String str = "";
		if (second < 10) {
			str = minute + ":0" + second;
		}
		if (minute < 10) {
			str = "0" + str;
		}
		return str;
	}

	/**
	 * @Description 时间类型转换,返回秒
	 * @param dateStr
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static int getTimeStamp(String dateStr) {
		Date date = parseTime(dateStr);
		return (int) (date.getTime() / 1000);
	}

	/**
	 * @Description 时间换算成秒
	 * @param timeStr
	 *            mm:ss
	 * @return
	 */
	public static int getSeconds(String timeStr) {
		String[] str = timeStr.split(":");
		int minute = Integer.parseInt(str[0]);
		int second = Integer.parseInt(str[1]);
		return minute * 60 + second;
	}

}
