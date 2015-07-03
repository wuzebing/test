package com.ai.c.base.entity;

/**
 * 公用接口返回对象
 * 
 * @author  CaoHang
 * @version  [版本号, 2014-11-21]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class JsonResult
{
    /** 结果码 */
    private int statusCode;
    
    /** 若成功，则返回结果集；失败则返回错误描述 */
    private Object returnObj;
    
    /**
     * <默认构造函数>
     */
    public JsonResult()
    {
        
    }
    
    public JsonResult(int statusCode, Object returnObj)
    {
        this.statusCode = statusCode;
        this.returnObj = returnObj;
    }
    
    public int getStatusCode()
    {
        return statusCode;
    }
    
    public void setStatusCode(int statusCode)
    {
        this.statusCode = statusCode;
    }
    
    public Object getReturnObj()
    {
        return returnObj;
    }
    
    public void setReturnObj(Object returnObj)
    {
        this.returnObj = returnObj;
    }
    
    @Override
    public String toString()
    {
        return "JsonResult [statusCode=" + statusCode + ", returnObj=" + returnObj + "]";
    }
    
}
