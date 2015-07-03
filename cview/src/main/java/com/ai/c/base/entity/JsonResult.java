package com.ai.c.base.entity;

/**
 * @author LIUQIANMING
 * @createtime 2015-06-22
 */
public class JsonResult {
	private int statusCode;
	private Object returnObj;
	
	public JsonResult(){
		
	}
	
	public JsonResult(int statusCode,Object returnObj){
		this.statusCode = statusCode;
		this.returnObj = returnObj;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Object getReturnObj() {
		return returnObj;
	}

	public void setReturnObj(Object returnObj) {
		this.returnObj = returnObj;
	}

}
