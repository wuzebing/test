package com.ai.c.base.resultcode;

public enum ResultCode {
	/**通用返回值**/
	SUCCESS(200,"成功"),PARAMS_NULL(201,"请求参数为空"),
	SYSID_NULL(202,"sysID不存在"),REQUEST_TIMEOUT(203,"访问超时"),
	JSON_CHANGER_ERROR(204,"json转换错误"),GET_TYPE_ERROR(205,"获取内部类型信息出错"),
	GET_STATUS_ERROR(206,"获取内部状态信息出错"),
	IS_EXIST(207, "已存在"),OTHER_ERROR(999,"其他错误"),SYSTEMID_ERROR(990,"商品销售平台错误"),
	/**用户返回值 901-949**/
	USER_NOT_EXISTS(901,"用户不存在"),USER_PASSWORD_ERROR(902,"密码错误"),
	USER_STATUS_ERROR(903,"用户状态错误"),USER_EXISTS(904,"用户名已存在"),
	USER_VCODE_ERROR(906,"验证码不正确"),USER_MAIL_ERROR(907,"邮箱格式不正确"),
	USER_TYPE_ERROR(908,"用户类型不正确"),USER_MAIL_TIMEOUT(915,"验证码已过期"),
	USER_POSTER_ERROR(920,"激活码不可用"),USER_ERROR(905,"用户名或密码错误"),
	MAIL_SEND_ERROR(922,"邮件发送失败"),MAIL_URL_ERROR(923,"激活链接不正确"),MAIL_ERROR(916,"未进行邮箱操作"),
	MAKE_PUB_INFO_ERROR(930,"制作宣传页失败"),RECEIVE_COMMODITY_FAIL(940,"领取失败"),
	USER_NO_AUTHORITY(945,"没有权限操作"),
	/**订单返回值**/
	ORDER_GET_ERROR(351,"获取商品订单出错"),ORDER_STSTUS_NOTUPDATE(353,"此状态不需要处理"),
	ORDER_PARTNER_NOTEXIST(354,"合伙人不存在，处理失败"),ORDER_STSTUS_NULL(355,"未找到推广系统的订单状态，处理失败"),
	ORDER_STSTUS_NOTSEQU(356,"状态未按顺序返回,不需要更新"),ORDER_NOTK(357,"非K项目推广商品，供货商ID为空，处理失败"),
	ORDER_REFEREE_EXIST(370,"对不起，您的订单存在推广人，不符合补录条件."),ORDER_NOTBUY_QRCODE(371,"未查询到扫码记录"),
	ORDER_PAYTIME_ERROR(372,"订单时间不正确"),ORDER_STSTUS_COMMIT(373,"订单未完成交易"),
	ORDER_NOT_EXIST(375,"对不起，未找到记录!"),ORDER_REFEREE_DREAM(376,"对不起，您的订单推广人为Dream，不符合补录条件。"),
	/**安全模块返回值**/
	AUTHENTICATOR_ERROR(551,"Authenticator校验不通过"),
	/**KToken返回值**/
	TOKEN_DIFFER(601,"Token的生成平台与当前平台不一致"),
	TOKEN_ILLEGAL(602,"非法Token"),
	TOKEN_TIMEOUT(603,"Token过期"),
	TOKEN_DECRYPT_ERROR(604,"Token解密失败"),
	TOKEN_EDIT(605,"Token撰改"),
    /**供货商返回值代码251-299**/
	APPROVE_COMMODITYID_NULL(251,"审批（上架）商品参数commodityInterID为空"),
	APPROVE_OPERATORID_NULL(252,"审批（上架）商品参数operatorID为空"),
	APPROVE_STATE_NULL(253,"审批（上架）商品参数commodityState为空"),
	APPROVE_ADDED_FAIL(254,"审批（上架）商品失败"),
	APPROVE_PROMOTION_URL_NULL(252, "天猫推广链接没提供"),
	COMMODITY_ID_NULL(280,"发布商品时，商品ID为空"),COMMODITY_EXIST(291,"发布商品时，商品已存在"),
	SUPPLIER_UPATE_ERROR(270,"供货商更新信息失败"),COMMODITY_NOT_EXIST(292,"商品不存在"),
	SUPPLIER_STATUS_ERROR(272,"查询供货商状态不正确"),COMMODITY_RECEIVE(268,"商品已领取"),
	QRCODE_INVALID(273,"二维码失效"),
	/**流量宝领取流量返回值代码700-710**/
	NOT_EFFECTIVE(700, "兑换码无效"),
	REST_FAIL(701, "流量券发放失败"),
	ACTIVITY_END(702, "活动已结束"),
	GIVE_OUT(703, "流量券已抢光"),
	NOT_SUPPORT_PROVINCE(704, "不支持该手机号所属省份"),
	NOT_SUPPORT_OPERATOR(705,"请填写电信手机号"),
	GIVED(706, "该手机号已抢过"),
	ACTIVITY_CONFILCT(707, "存在活动互斥");
	
	private int code;
	private String desc;
	public int getCode()
    {
        return code;
    }
    
    public String getDesc()
    {
        return desc;
    }
    
    private ResultCode(int code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }
    
    // 普通方法
    public static String getName(int code){
    	for(ResultCode r:ResultCode.values()){
    		if(r.getCode()==code)
    			return r.getDesc();
    	}
    	return "其他错误";
    }
}
