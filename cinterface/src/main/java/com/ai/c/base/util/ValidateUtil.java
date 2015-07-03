/*
 * 文 件 名:  ValidateUtil.java
 * 版    权:  AsiaInfo Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-11-28
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ai.c.base.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.ai.c.base.common.Constants;

/**
 * 参数校验工具类
 * 
 * @author  CaoHang
 * @version  [版本号, 2014-11-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class ValidateUtil
{
    /**
     * 分页参数校验，校验完成，进行参数保存
     * 
     * @param request HTTP请求对象
     * @param params 参数对象
     * @see [类、类#方法、类#成员]
     */
    public static int[] validatePageParams(HttpServletRequest request)
    {
        int[] page = new int[2];
        int pageNo = Constants.DefaultValue.PAGE_NO;// 当前页的页号
        int pageSize = Constants.DefaultValue.PAGE_SIZE;// 默认每页数量
        try
        {
            String pageNoStr = request.getParameter(Constants.Params.PAGE_NO);
            String pageSizeStr = request.getParameter(Constants.Params.PAGE_SIZE);
            if (StringUtils.isNotBlank(pageNoStr) && StringUtils.isNotBlank(pageSizeStr))
            {
                pageNo = Integer.valueOf(pageNoStr);
                pageSize = Integer.valueOf(pageSizeStr);
            }
        }
        catch (Exception e)
        {
            //TODO 记录日志
        }
        page[0] = pageNo;
        page[1] = pageSize;
        return page;
    }
    
    /**
     * 判断字符串是否为合法的数字
     * 
     * @param value
     * @return
     */
    public static boolean isDegit(String value){
    	try{
    		Float.parseFloat(value);
        	return true;
    	}catch(NumberFormatException e){
    		return false;
    	}catch(NullPointerException e){
    		return false;
    	}
    	
    }
    
    
    /**
     * 判断字符串是否为合法的手机号
     * <li>验证方式不是非常准确，后期根据需要可以进行重构</li>
     * 
     * @param value
     * @return
     */
    public static boolean isPhone(String value){
    	String regex = "^\\d{11}$";
		return Pattern.matches(regex, value);
    }
    
    
    /**
     * 验证日期是否满足某种格式
     * 
     * @param value
     * @param format
     * @return
     */
    public static boolean validateDate(String value, String format){
    	DateFormat dateFormat;
    	if(StringUtils.isEmpty(format)){
    		dateFormat = TimestampUtil.getDateFormater();
    	}else{
    		dateFormat = TimestampUtil.getDateFormater(format);
    	}
    	try{
    		dateFormat.parse(value);
    		return true;
    	}catch(Exception e){
    		return false;
    	}
    }
}
