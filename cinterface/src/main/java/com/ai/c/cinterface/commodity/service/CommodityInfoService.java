/*
 * 文 件 名:  CommodityInfoService.java
 * 版    权:   Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  LIUQIANMING
 * 修改时间:  2015-07-11
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ai.c.cinterface.commodity.service;

import java.util.Map;

import com.ai.c.base.dao.Page;
import com.ai.c.cinterface.commodity.entity.CommodityInfo;

/**
 * 商品信息管理-业务处理
 * 
 * @author LIUQIANMING
 * @version [版本号, 2015-07-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface CommodityInfoService {

    /**
     * 获取商品展示的商品信息
     * <li>分页方法必须包含*ListPage.*</li>
     * 
     * @param params 查询参数
     * @param pageNo 页数
     * @param pageSize 查询数量
     * @throws Exception 异常
     * @return: List<CommodityInfo> 商品列表信息
     */
    Page<CommodityInfo> queryComInfoListPage(Map<String, Object> params, int pageNo, int pageSize)
        throws Exception;
    
   
    
    /**
     * 根据商品ID、商品状态、合伙人ID查询商品信息
     * <li>商品ID，必选</li>
     * <li>商品状态，可选</li>
     * <li>合伙人ID，用于区分用户是否登录，可选</li>
     * 
     * @param commodityID 商品ID
     * @param userID 合伙人ID，用于区分用户是否登录
     * @return CommodityInfo 商品信息
     * @throws Exception 异常
     * @see [类、类#方法、类#成员]
     */
    CommodityInfo getComInfoByID(Map<String, Object> params)
        throws Exception;
    
    CommodityInfo getComByComIdAndSysId(Map<String, Object> params);

}
