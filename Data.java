package com.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Data {
	public String event;
	public String year;
	public String month;
	public String day;

	public Data() {
		event = "null";
		year = "1970";
		month = "01";
		day = "01";
	}

	public void display() {
		System.out.println(event);
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
	}

	public String upToFromNow() throws ParseException {
		String str = "距离" + event + "还有：";
		int days = days();
		str = str + days + "天！";
		return str;
	}

	private int days() throws ParseException {
		long today = System.currentTimeMillis();
		long thatDay = getMillis();
		long dif = thatDay - today;
		return milToDay(dif) + 1;
	}

	/* 传入一个long型毫秒数，返回一个整型日期，向下取整 */
	private int milToDay(long dif) {
		// TODO Auto-generated method stub
		dif = dif / 1000;
		int day = (int) (dif / 86400);
		return day;
	}

	private long getMillis() throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = year + month + day;
		long millionSeconds = sdf.parse(str).getTime();// 毫秒
		return millionSeconds;
	}

	/* 格式化Data各项数据(日期添零补全至两位) */
	public boolean format() {
		String temp = "0";
		;
		if (year.length() == 1) {
			year = temp + year;
		}
		if (month.length() == 1) {
			month = temp + month;
		}
		if (day.length() == 1) {
			day = temp + day;
		}
		return true;
	}

	// /* 获取当前对象到当前月底的天数 */
	// private int toTheEndOfMonth() {
	// int d = Integer.parseInt(day);
	// return lengthOfMonth() - d;
	// }
	//
	// /* 获取当前对象从顺序下一月到当年最后一天的天数 */
	// private int toTheEndOfYear() {
	// int d = Integer.parseInt(day);
	// return lengthOfMonth() - d;
	// }
	//
	// private int lengthOfMonth() {
	// int m = Integer.parseInt(month);
	// if (m == 4 || m == 6 || m == 9 || m == 11) {
	// return 30;
	// } else if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10
	// || m == 12) {
	// return 31;
	// } else {
	// return getFebDays();
	// }
	// }
	//
	// /* 当前年份中二月份的最大天数 */
	// private int getFebDays() {
	// // TODO Auto-generated method stub
	// int y = Integer.parseInt(year);
	// if ((y % 4 == 0 && !(y % 100 == 0)) || (y % 400 == 0)) {
	// // 能整除四 同时 不能整除400 || 能整除400
	// return 29;
	// } else
	// return 0;
	// }
}
