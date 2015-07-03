package com.ai.c.base.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Vector;



/**
 * 日期处理类
 * 
 * @author liuqm
 * @version 1.0
 * @date  2014-08-05 14:18
 */

public class DateUtil {

	/** 注意格里历和儒略历交接时的日期差别 */
	private static transient int gregorianCutoverYear = 1582;
	
	/** 默认的日期格式 */
	private static String defaultDateFormat = "yyyy-MM-dd HH:mm:ss";

	private static DateUtil ldu_ = null;

	public static DateUtil getInstance() {
		if (ldu_ == null) {
			ldu_ = new DateUtil();
		}
		return ldu_;
	}

	/**
	 * 获取指定日期所在月的第一天
	 * 
	 * @param dateStr
	 * @return
	 */
	public String getThisMonthStartDay(String dateStr) {
		return dateStr.substring(0, dateStr.length() - 2) + "01";
	}

	/**
	 * 获取指定日期的上周同日
	 * 
	 * @param dateStr
	 * @param theFormat
	 * @return
	 */
	public String getLastWeekDate(String dateStr, String theFormat) {
		return convertDate(dateStr, theFormat, Calendar.DAY_OF_YEAR, -7);
	}

	/**
	 * 获取指定日期所在月的前一天
	 * 
	 * @param dateStr
	 * @param theFormat
	 * @return
	 */
	public String getLastDate(String dateStr, String theFormat) {
		return convertDate(getThisMonthStartDay(dateStr), theFormat,
				Calendar.DATE, -1);
	}

	/**
	 * 返回上一个统计月份
	 * 
	 * @param acycID
	 * @param theFormat
	 * @return
	 */
	public String getLastMonth(String acycID, String theFormat) {
		return convertDate(
				acycID.substring(0, 4) + "-" + acycID.substring(4, 6) + "-01",
				theFormat, Calendar.MONTH, -1).substring(0, 6);
	}
	
	public String getLastMonth() {
		return convertDate(getToday("yyyyMMdd"),"yyyyMMdd", Calendar.MONTH, -1).substring(0, 6);
	}	
	public String getLastMonth2() {
		return convertDate(getToday("yyyy-MM-dd"),"yyyy-MM-dd", Calendar.MONTH, -1).substring(0, 7);
	}
	/**
	 * 方法描述：返回上月同期的日期
	 * 
	 * @param acycID
	 * @param theFormat
	 * @return
	 */
	public String getLastMonthDate(String acycID, String theFormat) {
		return convertDate(acycID, theFormat, Calendar.MONTH, -1).substring(0,
				8);
	}
	/**
	 * 方法描述：返回去年同期的月份
	 * 
	 * @param acycID
	 * @param theFormat
	 * @return
	 */
	public String getLastYearMonth(String acycID, String theFormat) {
		return convertDate(acycID, theFormat, Calendar.MONTH, -12).substring(0,
				6);
	}

	
	/**
	 * 方法描述：返回上月
	 * 
	 * @param acycID
	 * @param theFormat
	 * @return
	 */
	public String getLastMonth1(String acycID, String theFormat) {
		return convertDate(acycID,"yyyyMMdd", Calendar.MONTH, -1).substring(0,
				6);
	}


	/**
	 * 方法描述：返回去年同期的日期
	 * 
	 * @param acycID
	 * @param theFormat
	 * @return
	 */
	public String getLastYearDate(String acycID, String theFormat) {
		return convertDate(acycID, theFormat, Calendar.YEAR, -1)
				.substring(0, 8);
	}
	/**
	 * 按照指定格式获取指定日期所在月的上个月的第一天
	 * 
	 * @param dateStr
	 * @param theFormat
	 * @return
	 */
	public String getLastMonthStartDay(String dateStr, String theFormat) {
		return convertDate(getThisMonthStartDay(dateStr), theFormat,
				Calendar.MONTH, -1);
	}

	/**
	 * 按照指定格式获取指定日期所在月的上个月的最后一天
	 * 
	 * @param dateStr
	 * @param theFormat
	 * @return
	 */
	public String getLastMonthEndDay(String dateStr, String theFormat) {
		return convertDate(getThisMonthStartDay(dateStr), theFormat,
				Calendar.DAY_OF_YEAR, -1);
	}

	/**
	 * <b>摘要： </b> 获取今天的日期。 <br>
	 * <b>参数： </b> theFormat设置日期输出格式，例如"yyyyMMddHHmmss"<br>
	 * <b>返回值：</b> 指定格式的今天日期，返回值类型是String。
	 */
	public String getToday(String theFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(theFormat,
				Locale.CHINESE);
		Date thedate = new Date();
		return formatter.format(thedate);
	}
	
	/**
	 * 获取今天的日期
	 * <li>日期的格式采用默认格式:yyyy-MM-dd HH:mm:ss</li>
	 * @return
	 */
	public String getToday() {
		return this.getToday(DateUtil.defaultDateFormat);
	}
	
	/**
	 * 方法描述：获取当前时间昨天的日期
	 * @param theFormat 设置日期输出格式，例如"yyyyMMddHHmmss"。 
	 * @return 指定格式的今天日期，返回值类型是String
	 */
	public String getYesterday(String theFormat)
	{
		String todaystr = getToday(theFormat);
		return convertDate(todaystr, theFormat, Calendar.DAY_OF_YEAR, -1);
	}
	/**
	 * <b>摘要： </b> 获取昨天的日期。 <br>
	 * <b>参数： </b> theFormat设置日期输出格式，例如"yyyyMMdd"。 <br>
	 * <b>返回值：</b> 指定格式的昨天日期，返回值类型是String。
	 */
	public String getYesterday(String todaystr, String theFormat) {
		// SimpleDateFormat formatter = new
		// SimpleDateFormat(theFormat,Locale.CHINESE);
		return convertDate(todaystr, theFormat, Calendar.DAY_OF_YEAR, -1);
	}

	/**
	 * <b>摘要： </b> 计算相对于dateStr的日期，如果转换日期为上个月同期日期
	 * dateStr,theFormat="yyyyMMdd"，feildnum=Calendar.WEEK_OF_YEAR，thenum=-4。 <br>
	 * <b>参数： </b> dateStr设置参照的日期，theFormat设置参照日期格式，feildnum处理方式，thenum计算值。 <br>
	 * <b>返回值：</b> 指定格式的相对于dateStr的日期，返回值类型是String。
	 */
	public String convertDate(String dateStr, String theFormat, int feildnum,
			int thenum) {
		SimpleDateFormat formatter = null;
		Calendar cldr = null;
		Date date_pre = null;
		Date date1 = null;
		try {
			formatter = new SimpleDateFormat(theFormat, Locale.CHINESE);
			cldr = new GregorianCalendar();
			date1 = formatter.parse(dateStr);
			cldr.setTime(date1);
			cldr.add(feildnum, thenum);
			date_pre = cldr.getTime();
		} catch (Exception e) {

		}
		if (date_pre == null)
			return formatter.format(date1);
		else
			return formatter.format(date_pre);
	}

	public int calDate(String dateStr1, String datestr2, String theFormat) {
		SimpleDateFormat formatter = null;
		Calendar cldr = null;
		Date date_pre = null;
		Date date1 = null;
		Date date2 = null;
		int num = 0;
		try {
			formatter = new SimpleDateFormat(theFormat, Locale.CHINESE);
			cldr = new GregorianCalendar();
			date1 = formatter.parse(dateStr1);
			date2 = formatter.parse(datestr2);
			num = Integer.parseInt(String.valueOf((date1.getTime() - date2
					.getTime())
					/ 1000 / 60 / 60 / 24));
		} catch (Exception e) {

		}
		return num;
	}

	// 返回两个日期之间的日期（是指日）
	public Vector betweenDates(String dateStr1, String datestr2,
			String theFormat) {
		Vector v1 = new Vector();
		int checknum = calDate(datestr2, dateStr1, theFormat);
		if (checknum > 0) {
			for (int i = 0; i <= checknum; i++) {
				String datestr = convertDate(dateStr1, "yyyyMMdd",
						Calendar.DAY_OF_YEAR, i);
				v1.add(datestr);
			}
			return v1;
		} else if (checknum == 0) {
			v1.add(dateStr1);
			return v1;
		} else {
			return null;
		}
	}

	// 返回两个月份之间的月份
	public Vector betweenMons(String fromDate, String toDate) {
		try {
			if (Double.parseDouble(fromDate) > Double.parseDouble(toDate)) {
				return null;
			}
			Vector v1 = new Vector();
			String currMonth = fromDate;
			v1.add(currMonth);
			while (!currMonth.equals(toDate)) {
				currMonth = convertDate(currMonth, "yyyyMM",
						Calendar.MONTH, 1);
				v1.add(currMonth);
			}

			return v1;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

	/**
	 * <b>摘要： </b> 转换str_d日期的格式。 <br>
	 * <b>参数： </b> str_d需要转换格式的日期，Format_Old设置旧日期格式，Format_New设置新日期格式。 <br>
	 * <b>返回值：</b> 指定格式的相对于str_d的日期，返回值类型是String。
	 */
	public String convertDay_Type(String str_d, String Format_Old,
			String Format_New) {
		SimpleDateFormat sdf = new SimpleDateFormat(Format_Old, Locale.CHINESE);
		SimpleDateFormat sdf2 = new SimpleDateFormat(Format_New, Locale.CHINESE);
		String Str_date = "";
		try {
			Date d1 = sdf.parse(str_d);
			Str_date = sdf2.format(d1);
		} catch (Exception e) {
		}
		return Str_date;
	}

	/*
	 * 返回指定日期的所在周的周几日期；（周日是一周的结束）;thenum取1~7;7表示星期日
	 */
	public String convertDay_Week(String dateStr, int thenum) {
		SimpleDateFormat formatter = null;
		Calendar cldr = null;
		Date date_pre = null;
		Date date1 = null;
		try {
			formatter = new SimpleDateFormat("yyyyMMdd", Locale.CHINESE);
			cldr = new GregorianCalendar();
			date1 = formatter.parse(dateStr);
			cldr.setTime(date1);
			int iWeek = cldr.get(Calendar.DAY_OF_WEEK);

			int i = 0;
			if (iWeek == 1) { // 指定日期是星期日
				i = thenum - iWeek - 6;
			} else {
				i = thenum - iWeek + 1;
			}
			cldr.add(Calendar.DAY_OF_YEAR, i);

			date_pre = cldr.getTime();
		} catch (Exception e) {

		}
		if (date_pre == null)
			return formatter.format(date1);
		else
			return formatter.format(date_pre);
	}

	// 根据传入的日期返回所属的季度，如"200501"，返回"2005年第一季度"
	public String getQuarterName(String sDate) {
		String sQuarter = "";
		if (sDate.length() < 6)
			return "当前日期有误：" + sDate;

		String sYear = sDate.substring(0, 4) + "年";
		int iMM = Integer.parseInt(sDate.substring(4, 6));

		switch (iMM) {
		case 1:
			sQuarter = sYear + "第一季度";
			break;
		case 2:
			sQuarter = sYear + "第一季度";
			break;
		case 3:
			sQuarter = sYear + "第一季度";
			break;
		case 4:
			sQuarter = sYear + "第二季度";
			break;
		case 5:
			sQuarter = sYear + "第二季度";
			break;
		case 6:
			sQuarter = sYear + "第二季度";
			break;
		case 7:
			sQuarter = sYear + "第三季度";
			break;
		case 8:
			sQuarter = sYear + "第三季度";
			break;
		case 9:
			sQuarter = sYear + "第三季度";
			break;
		case 10:
			sQuarter = sYear + "第四季度";
			break;
		case 11:
			sQuarter = sYear + "第四季度";
			break;
		case 12:
			sQuarter = sYear + "第四季度";
			break;
		}

		return sQuarter;
	}

	// 根据传入的日期返回所属的季度，如"200501"，返回"20051"
	public String getQuarterCode(String sDate) {
		String sQuarter = "";
		if (sDate.length() < 6)
			return "当前日期有误：" + sDate;

		String sYear = sDate.substring(0, 4);
		int iMM = Integer.parseInt(sDate.substring(4, 6));

		switch (iMM) {
		case 1:
			sQuarter = sYear + "1";
			break;
		case 2:
			sQuarter = sYear + "1";
			break;
		case 3:
			sQuarter = sYear + "1";
			break;
		case 4:
			sQuarter = sYear + "2";
			break;
		case 5:
			sQuarter = sYear + "2";
			break;
		case 6:
			sQuarter = sYear + "2";
			break;
		case 7:
			sQuarter = sYear + "3";
			break;
		case 8:
			sQuarter = sYear + "3";
			break;
		case 9:
			sQuarter = sYear + "3";
			break;
		case 10:
			sQuarter = sYear + "4";
			break;
		case 11:
			sQuarter = sYear + "4";
			break;
		case 12:
			sQuarter = sYear + "4";
			break;
		}

		return sQuarter;
	}

	// 根据传入的日期返回上一季度的最后月份值
	public String getLastQuaMonth(String sDate) {
		String sQuarter = "";
		if (sDate.length() < 6)
			return "当前日期有误：" + sDate;
		if (sDate.length() > 6)
			sDate = sDate.substring(0, 6); // 截取年月

		String sYear = sDate.substring(0, 4);
		int iMM = Integer.parseInt(sDate.substring(4, 6));

		switch (iMM) {
		case 1:
			sQuarter = convertDate(sDate, "yyyyMM", Calendar.MONTH, -1);
			break;
		case 2:
			sQuarter = convertDate(sDate, "yyyyMM", Calendar.MONTH, -2);
			break;
		case 3:
			sQuarter = sDate;
			break;
		case 4:
			sQuarter = convertDate(sDate, "yyyyMM", Calendar.MONTH, -1);
			break;
		case 5:
			sQuarter = convertDate(sDate, "yyyyMM", Calendar.MONTH, -2);
			break;
		case 6:
			sQuarter = convertDate(sDate, "yyyyMM", Calendar.MONTH, -3);
			break;
		case 7:
			sQuarter = convertDate(sDate, "yyyyMM", Calendar.MONTH, -1);
			break;
		case 8:
			sQuarter = convertDate(sDate, "yyyyMM", Calendar.MONTH, -2);
			break;
		case 9:
			sQuarter = sDate;
			break;
		case 10:
			sQuarter = convertDate(sDate, "yyyyMM", Calendar.MONTH, -1);
			break;
		case 11:
			sQuarter = convertDate(sDate, "yyyyMM", Calendar.MONTH, -2);
			break;
		case 12:
			sQuarter = sDate;
			break;
		}

		return sQuarter;
	}

	// 根据传入的季度返回该季度的开始或最后一个月份值，sFlag="begin":开始月份值；sFlag="end":最后月份值
	public String getQuarMonth(String sQuarter, String sFlag) {
		String sDate = "-1";
		if (sQuarter.length() != 5) {
			System.out.println("传入的季度值有误：" + sQuarter);
			return "-1";
		}

		String sYear = sQuarter.substring(0, 4);
		int iNum = Integer.parseInt(sQuarter.substring(4, 5));

		switch (iNum) {
		case 1:
			if ("begin".equals(sFlag)) {
				sDate = sYear + "01";
			} else if ("end".equals(sFlag)) {
				sDate = sYear + "03";
			}
			break;
		case 2:
			if ("begin".equals(sFlag)) {
				sDate = sYear + "04";
			} else if ("end".equals(sFlag)) {
				sDate = sYear + "06";
			}
			break;
		case 3:
			if ("begin".equals(sFlag)) {
				sDate = sYear + "07";
			} else if ("end".equals(sFlag)) {
				sDate = sYear + "09";
			}
			break;
		case 4:
			if ("begin".equals(sFlag)) {
				sDate = sYear + "10";
			} else if ("end".equals(sFlag)) {
				sDate = sYear + "12";
			}
			break;
		}

		return sDate;
	}

	// 返回报表展示数据默认日期
	public String getDefaultDate(String sTime, String dateType) {
		String tmpDate = "";

		try {
			// 获取正常的默认日期
			if ("month".equals(dateType)) { // 月份
				if (Integer.parseInt(getToday("ddHHmm")) >= Integer
						.parseInt(sTime)) {
					tmpDate = convertDate(getToday("yyyyMM"), "yyyyMM",
							java.util.Calendar.MONTH, -1);
				} else {
					tmpDate = convertDate(getToday("yyyyMM"), "yyyyMM",
							java.util.Calendar.MONTH, -2);
				}
			} else { // 日
				// 9点前不显示昨天数据，显示前天的
				if (Integer.parseInt(getToday("HHmm")) >= Integer
						.parseInt(sTime)) {
					tmpDate = getYesterday(getToday("yyyyMMdd"), "yyyyMMdd");
				} else {
					tmpDate = convertDate(getToday("yyyyMMdd"), "yyyyMMdd",
							java.util.Calendar.DAY_OF_YEAR, -2);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			if ("month".equals(dateType)) { // 月份
				if (Integer.parseInt(getToday("ddHHmm")) >= 50900) {
					tmpDate = convertDate(getToday("yyyyMM"), "yyyyMM",
							java.util.Calendar.MONTH, -1);
				} else {
					tmpDate = convertDate(getToday("yyyyMM"), "yyyyMM",
							java.util.Calendar.MONTH, -2);
				}
			} else { // 日
				// 9点前不显示昨天数据，显示前天的
				if (Integer.parseInt(getToday("HHmm")) >= 900) {
					tmpDate = getYesterday(getToday("yyyyMMdd"), "yyyyMMdd");
				} else {
					tmpDate = convertDate(getToday("yyyyMMdd"), "yyyyMMdd",
							java.util.Calendar.DAY_OF_YEAR, -2);
				}
			}
		} finally {
		}
		return tmpDate;
	}

	/**
	 * 方法描述：判断传入的年是否为闰年
	 * 
	 * @param year
	 * @return
	 */
	public boolean isLeapYear(int year) {
		if (year >= gregorianCutoverYear) {
			return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
		} else {
			return year % 4 == 0;
		}
	}

	/**
	 * <b>摘要： </b> 获取最近的周六日期 <br>
	 * <b>参数： </b> theFormat设置日期输出格式，例如"yyyyMMdd"。
	 */
	public String getNearestWeekEnd(String theFormat) {
		 Calendar cal = Calendar.getInstance();
		 int delay = -cal.get(Calendar.DAY_OF_WEEK) ;
		 Date date = new Date();
	     SimpleDateFormat bartDateFormat = new SimpleDateFormat(theFormat,Locale.US);
	     String todaystr = bartDateFormat.format(date);
		 return convertDate(todaystr, theFormat, Calendar.DAY_OF_YEAR, delay);
	}
	/**
	 * <b>摘要： </b> 获取N天前的日期。 <br>
	 * <b>参数： </b> theFormat设置日期输出格式，例如"yyyyMMdd"。 <br>
	 * <b>返回值：</b> 指定格式的昨天日期，返回值类型是String。
	 */
	public String getXDayBeforeToday(String todaystr, String theFormat, int num) {
		SimpleDateFormat formatter = new SimpleDateFormat(theFormat,
				Locale.CHINESE);
		return convertDate(todaystr, theFormat, Calendar.DAY_OF_YEAR, num);
	}
	
	/**
	 * <b>摘要： </b> 获取N个月前的日期。 <br>
	 * <b>参数： </b> theFormat设置日期输出格式，例如"yyyyMM"。 <br>
	 * <b>返回值：</b> 指定格式的N个月前的日期，返回值类型是String。
	 */
	public String getXMonBeforeToday(String todaystr, String theFormat, int num) {
		SimpleDateFormat formatter = new SimpleDateFormat(theFormat,
				Locale.CHINESE);
		return convertDate(todaystr, theFormat, Calendar.MONTH, num);
	}
	
	/**
	 * <b>摘要： </b> 获取N个星期前的日期。 <br>
	 * <b>参数： </b> theFormat设置日期输出格式，例如"yyyyMM"。 <br>
	 * <b>返回值：</b> 指定格式的N个月前的日期，返回值类型是String。
	 */
	public String getXWeekBeforeToday(String todaystr, String theFormat, int num) {
		SimpleDateFormat formatter = new SimpleDateFormat(theFormat,
				Locale.CHINESE);
		return convertDate(todaystr, theFormat, Calendar.WEEK_OF_YEAR, num);
	}
	public static void main(String [] args){
		DateUtil  du=new DateUtil();
//		String b;
//		String acycID="201405"+"01";
//		b=du.getLastMonth1(acycID, "yyyyMMdd");
//		String y=du.convertDate(acycID,"yyyyMMdd", Calendar.MONTH, -1);
//		System.out.println(y);
//		System.out.println(b);
		String a=du.getToday("yyyy-MM-dd HH:mm:ss");
		System.out.println(a);
	}
}
