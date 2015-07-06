package com.ai.c.cinterface.user.status;

public enum OtherConst {

	//邮箱注册--userType
	MAILSIGNUP("03"),
	//邮箱找回密码vcode——type
	MAILPWDTYPE("02"),
	//本网手机注册---userType
	TELEPHONESIGNUP("01"),
	//异网手机注册
	OTHEPHONESIGNUP("02"),
	//密码类型
	PASSWORDTYPE("41"),
	//合伙人sysId
	PARTNERSYSID("003"),
	//供货商sysID
	SUPPLIERSYSID("001"),
	//管理员sysId
	ADMINSYSID("005"),
	//K平台sysId
	KPLATSYSID("007"),
	//支付宝sysId
	ALIPAYSYSID("008"),
	//微信sysId
	WEINXINSYSID("009"),
	//天猫sysId
	TMALLSYSID("010"),
	//189.cnsysId
	TIANYISYSID("011"),
	//dataSource单个用户开通
	SINGLEOPEN("00"),
	//dataSource批量用户开通
	BULKSOPEN("09"),
	//dataSource注册下发短信
	SIGNUPSMS("10"),
	//dataSource重置密码下发短信
	FINDPASSWORDSMS("11"),
	//dataSource短信提醒
	SMSALERTS("12"),
	//dataSource营销推送
	MARKETPUSH("18"),
	//dataSource后台导入
	BACKIMPORT("90"),
	//dataSource内置用户
	BUILDINUSER("91");
	private String params;
	
	OtherConst(String params){
		this.params = params;
	}

	public String getParams() {
		return params;
	}
}
