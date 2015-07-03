package com.ai.c.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间格式工具类
 * 
 * @author  CaoHang
 * @version  [版本号, 2014-11-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class TimestampUtil
{
    private static final SimpleDateFormat DATE_FORMAT_DEFAULT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private static final SimpleDateFormat DATEFORMAT_YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
    
    private static final SimpleDateFormat DATEFORMAT_YYYYMM = new SimpleDateFormat("yyyy-MM");
    
    /**
     * 获取指定格式的时间格式化对象
     * 
     * @param format 时间格式
     * @return DateFormat 时间格式化对象
     * @see [类、类#方法、类#成员]
     */
    public static DateFormat getDateFormater(String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf;
    }
    
    /**
     * 获取固定格式的时间格式化对象
     * <li>格式：yyyy-MM-dd HH:mm:ss</li>
     * 
     * @return DateFormat 时间格式化对象
     * @see [类、类#方法、类#成员]
     */
    public static DateFormat getDateFormater()
    {
        return DATE_FORMAT_DEFAULT;
    }
    
    /**
     * 获取固定格式的时间戳
     * <li>格式：yyyy-MM-dd HH:mm:ss</li>
     * 
     * @return String 时间戳
     * @see [类、类#方法、类#成员]
     */
    public static String getTimeStamp(String format)
    {
        return getDateFormater(format).format(new Date());
    }
    
    /**
     * 获取固定格式的时间戳
     * <li>格式：yyyy-MM-dd HH:mm:ss</li>
     * 
     * @return String 时间戳
     * @see [类、类#方法、类#成员]
     */
    public static String getTimeStamp()
    {
        return DATE_FORMAT_DEFAULT.format(new Date());
    }
    
    /**
     * 获取固定格式的时间戳
     * <li>格式：yyyy-MM-dd</li>
     * 
     * @return String 时间戳
     * @see [类、类#方法、类#成员]
     */
    public static String getTimeStampByYYYYMMDD()
    {
        return DATEFORMAT_YYYYMMDD.format(new Date());
    }
    
    /**
     * 获取固定格式的时间戳
     * <li>格式：yyyy-MM</li>
     * 
     * @return String 时间戳
     * @see [类、类#方法、类#成员]
     */
    public static String getTimeStampByYYYYMM()
    {
        return DATEFORMAT_YYYYMM.format(new Date());
    }
    
    /**
     * 解析指定格式的时间戳为时间对象
     * <li>格式：yyyy-MM-dd HH:mm:ss</li>
     * 
     * @param timeStamp 时间戳
     * @return Date 时间对象
     * @see [类、类#方法、类#成员]
     */
    public static Date parserToDate(String timeStamp)
        throws ParseException
    {
        return DATE_FORMAT_DEFAULT.parse(timeStamp);
    }
    
    /**
     * 解析指定格式的时间戳为时间对象
     * <li>格式：yyyy-MM-dd</li>
     * 
     * @param timeStamp 时间戳
     * @return Date 时间对象
     * @see [类、类#方法、类#成员]
     */
    public static Date parserToDateToYYYYMMDD(String timeStamp)
        throws ParseException
    {
        return DATEFORMAT_YYYYMMDD.parse(timeStamp);
    }
    
    /**
     * 解析时间对象为固定格式的时间戳
     * <li>格式：yyyy-MM-dd HH:mm:ss</li>
     * 
     * @param date 日期对象
     * @return String 时间戳
     * @see [类、类#方法、类#成员]
     */
    public static String parserDateToString(Date date)
        throws ParseException
    {
        return DATE_FORMAT_DEFAULT.format(date);
    }
    
    /**
     * 获取默认的日期对象
     * 
     * @return 日期对象
     * @see [类、类#方法、类#成员]
     */
    public static Date getDefaultDate()
    {
        return new Date();
    }
    
    /**
     * 获取昨日日期
     * 
     * @return 日期对象
     * @see [类、类#方法、类#成员]
     */
    public static String getYesterday()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);//得到前一天
        Date date = calendar.getTime();
        String yesterday = DATEFORMAT_YYYYMMDD.format(date);
        
        return yesterday;
    }
    
    /**
     * 获取距今多少天的日期
     * @param days
     * 		距离今天的天数
     * @return
     */
    public static String getPastDate(int days){
    	
    	Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -days);//得到前一天
        Date date = calendar.getTime();
        String yesterday = DATEFORMAT_YYYYMMDD.format(date);
        
        return yesterday;
    }
    
    
    /**
     * 解析时间对象为固定格式的时间戳
     * <li>格式：yyyy-MM-dd</li>
     * 
     * @param date 日期对象
     * @return String 时间戳
     * @see [类、类#方法、类#成员]
     */
    public static String parserDate(Date date)
        throws ParseException
    {
        return DATEFORMAT_YYYYMMDD.format(date);
    }
}
