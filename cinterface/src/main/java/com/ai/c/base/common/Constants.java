package com.ai.c.base.common;

/**
 * 常量类定义
 * 
 * @author  CaoHang
 * @version  [版本号, 2014-11-19]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface Constants
{
    /**格式*/
    interface Format
    {
        String CHARSET = "UTF-8";
        
        String CONTENTTYPE = "binary/octet-stream";
        
    }
    
    /**系统标识*/
    interface System
    {
        String LOCAHOST = "locahost";
        
        String KEY = "key";
        
        String IP = "ip";
        
        String TIMESTAMP = "timeStamp";
        
        String TOKEN = "token";
        
        String SYSID = "sysId";
        
        String AUTHENTICATOR = "authenticator";
        
    }
    
    /**特殊符号*/
    interface SpecialSymbol
    {
        String DOLLAR = "\\$";
        
        String DOLLAR_LINK = "$";
        
        String NULL = "null";
        
        /**英文逗号*/
        String COMMA = ",";
        
        /**英文下划线**/
        String UNDERLINE = "_";
        
        /**中文逗号*/
        String COMMA_CHINESE = "，";
        
        String EMPTY = "";
        
        String OBLIQUE_LINE = "/";
        
        String POINT = ".";
        
        String SINGLE_QUOTATION = "'";
    }
    
    /**请求参数标识*/
    interface Params
    {
        /** sequenceID */
        String SEQUENCE_ID = "sequenceID";
        
        /** token */
        String TOKEN = "token";
        
        /** 系统ID */
        String SYSTEM_ID = "systemID";
        
        /** 销售平台ID */
        String SALE_TYPE = "saleType";
        
        /** 平台ID */
        String C_SYS_ID = "cSysId";
        
        /** 分页参数-页数 */
        String PAGE_NO = "pageNo";
        
        /** 分页参数-每页数量 */
        String PAGE_SIZE = "pageSize";
        
        /** 图片类型 */
        String PIC_TYPE = "picType";
        
        /** 排行数量 */
        String TOP = "top";
        
        /** 排行类型 */
        String TOP_TYPE = "topType";
        
        /** ID */
        String ID = "ID";
        
        /** 用户状态 */
        String USER_STATUS = "userStatus";
        
        /** 合伙人ID */
        String USER_ID = "userID";
        
        /** 供货商ID */
        String SUPPLIER_ID = "supplierID";
        
        /** 供货商名称 */
        String SUPPLIER_NAME = "supplierName";
        
        /** 供货商昵称 */
        String NICK_NAME = "nickName";
        
        /** 排序标识 */
        String ORDER = "orderColumn";
        
        /** 排序类型 */
        String ORDERTYPE = "orderType";
        
        /** 商品宣传类型 */
        String PUB_TYPE = "pubType";
        
        /** 商品内部ID */
        String COMMODITY_INTERID = "commodityInterID";
        
        /** 商品ID */
        String COMMODITY_ID = "commodityID";
        
        /** 商品品牌 */
        String BRAND = "brand";
        
        /** 开始价格,查询条件 **/
        String START_COMMODITY_CPRICE = "startCommodityPrice";
        
        /** 结束价格，查询条件 **/
        String END_COMMODITY_PRICE = "endCommodityPrice";
        
        /** 商品是否在展示区显示 */
        String commodity_IS_DISPLAY = "isDisplay";
        
        /** 多个商品ID */
        String COMMODITY_IDS = "commodityIDs";
        
        /** 多个商品ID */
        String IDS = "ids";
        
        /** 商品名称 */
        String COMMODITY_NAME = "commodityName";
        
        /** 商品类型 */
        String COMMODITY_TYPE = "commodityType";
        
        /** 商品简述 */
        String COMMODITY_RESUME = "commodityResume";
        
        /** 商品广告语 */
        String ADVERTISEMENT = "advertisement";
        
        /** 商品详情 */
        String COMMODITY_DETAILS = "commodityDetails";
        
        /** 商品价格 */
        String COMMODITY_PRICE = "commodityPrice";
        
        /** 商品价格的上限**/
        String MAX_COMMODITY_PRICE = "maxCommodityPrice";
        
        /** 商品返利金额 */
        String CONDITION_PRICE = "conditionPrice";
        
        /** K平台 商品返利金额 */
        String K_CONDITION_PRICE = "kConditionPrice";
        
        /** 供货商 商品返利金额 */
        String SUPPLIER_CONDITION_PRICE = "supplierConditionPrice";
        
        /** 商品地址 */
        String COMMODITY_URL = "commodityUrl";
        
        /** 商品描述*/
        String COMMODITY_DESCURLS = "commodityDescUrls";
        
        /** 商品展示图片地址*/
        String COMMODITY_PICURL = "commodityPicUrl";
        
        /** 商品详情图片地址 */
        String COMMODITY_DETAIL_PICURLS = "commodityDetailPicUrls";
        
        /** 商品状态 */
        String COMMODITY_STATE = "commodityState";
        
        /** 商品审核操作类型 **/
        String APPROVE_OPERATE_TYPE = "operateType";
        
        /** 是否参与活动 */
        String FLAG = "flag";
        
        /** 返利ID */
        String CONDITION_ID = "conditionID";
        
        /** 返利金额 */
        String ALLOWANCE = "allowance";
        
        /** 返利状态 */
        String CONDITION_STATE = "conditionState";
        
        /** 返利类型 */
        String CONDITION_TYPE = "conditionType";
        
        /** 返利规则类型 */
        String REBATE_TYPE = "rebateType";
        
        /** 商品推广URL */
        String PROMOTION_URL = "promotionUrl";
        
        /** 是否已配置商品推广URL */
        String IS_CONFIGURED = "isConfigured";
        
        /** 合约价格 */
        String CONTRACT_PRICE = "contractPrice";
        
        /** 是否显示*/
        String IS_SHOW = "isShow";
        
        /** 合约名称 */
        String TREATY_NAME = "treatyName";
        
        /** 套餐名称 */
        String OFFER_NAME = "offerName";
        
        /** 套餐价格 */
        String PACKAGE_PRICE = "packagePrice";
        
        /**号池名称*/
        String NUMBER_NAME = "numberName";
        
        /**渠道号*/
        String CHANNEL_NUMBER = "channelNumber";
        
        /**店铺*/
        String SHOP = "shop";
        
        /**省份ID*/
        String PROVINCE_ID = "provinceID";
        
        /**合伙人省份ID*/
        String PARTNER_PROVINCE_ID = "partnerProvinceID";
        
        /**供货商省份ID*/
        String SUPPLIER_PROVINCE_ID = "supplierProvinceID";
        
        /**创建时间*/
        String CREATE_TIME = "createTime";
        
        /**更新时间*/
        String UPDATE_TIME = "updateTime";
        
        /**开始时间*/
        String START_TIME = "startTime";
        
        /**结束时间*/
        String END_TIME = "endTime";
        
        /**当前时间*/
        String CURRENT_TIME = "currentTime";
        
        /**商品套餐*/
        String COMMODITY_OFFERS = "commodityOffers";
        
        /**商品属性*/
        String COMMODITY_PROPERTIES = "commodityProperties";
        
        /**商品发布渠道*/
        String COMMODITY_SALETYPES = "commoditySaleTypes";
        
        /**商品发布区域*/
        String COMMODITY_SALESCOPES = "commoditySaleScopes";
        
        /**商品发布目标用户群*/
        String COMMODITY_TARGETGROUPS = "commodityTargetGroups";
        
        String DATASOURCE = "dataSource";
        
        String REMARK = "remark";
        
        String LIMITID = "limitID";
        
        String START_VALUE = "startValue";
        
        String END_VALUE = "endValue";
        
        String IP_ADDRESS = "ipAddress";
        
        String ISSUANCE_STATE = "issuanceState";
        
        String ISSUANCE_CONDITION = "issuanceCondition";
        
        String ISSUANCE_AFTER = "issuanceAfter";
        
        String SUB_SECTIONS = "subSections";
        
        String OPERATOR_ID = "operatorID";

        /**店铺编码*/
        String SHOP_CODE = "shopCode";
        
        /**推广人信息*/
        String REFEREE = "referee";
        
        /**渠道编码*/
        String CHANNEL_CODE = "channelCode";
        
    }
    
    /**默认参数值*/
    interface DefaultValue
    {
        /**当前页的页号，序号从1开始，默认为1*/
        int PAGE_NO = 1;
        
        /**查询数量*/
        int PAGE_SIZE = Integer.MAX_VALUE;
        
        /**默认排序字段，更新时间*/
        String ORDER_UPDATETIME = "updateTime";
        
        /**默认排序，降序*/
        String ORDERTYPE_DESC = "DESC";
        
        /**默认用户状态*/
        String USER_STATUS = "00";
        
        /**默认序列前缀*/
        String PREFIXID = "99";
        
        /**商品内部ID序列前缀*/
        String PREFIXID_COMMODITY_INTERID = "88";
        
        /**默认价格：0*/
        String PRICE_ZERO = "0";
        
    }
    
    /**结果码*/
    interface Code
    {
        String ACTION_RESULT = "001";
    }
    
    /**isConfigured字段，是否需要*/
    interface IsConfigured
    {
        /**已配置*/
        String YES = "1";
        
        /**没有配置*/
        String NO = "0";
    }
    
    /** 图片类型 */
    interface ImageType
    {
        /** 手机 */
        String IMAGE_MOBILE = "mobile";
        
        /** PC */
        String IMAGE_PC = "pc";
    }
    
    /** 终端类型 */
    interface TerminalType
    {
        /**PC端*/
        String PC = "0";
        
        /**移动端*/
        String MOBILE = "1";
    }
    
    /**商品审核操作类型**/
    interface ApproveOperateType{
    	 
    	String PASS = "0";	//通过操作
    	
    	String NO_PASS = "1"; // 不通过操作
    }
}