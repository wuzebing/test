/*
 * 文 件 名:  CommodityInfoMapper.java
 * 版    权:   Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  LIUQIANMING
 * 修改时间:  2015-07-11
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ai.c.cinterface.commodity.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.ai.c.cinterface.commodity.entity.CommodityInfo;

/**
 * 商品信息管理-数据处理
 * 
 * @author  LIUQIANMING
 * @version  [版本号, 2015-07-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository
public interface CommodityInfoMapper
{
    /**
     * 获取商品展示的商品信息
     * <li>分页方法必须包含*ListPage.*</li>
     * 
     * @param params 查询参数
     * @param pageNo 页数
     * @param pageSize 查询数量
     * @return: List<CommodityInfo> 商品列表信息
     */
    List<CommodityInfo> queryComInfoListPage(Map<String, Object> params);
    
    /**
     * 根据商品ID查询商品基本信息
     * 
     * @param commodityID 商品ID
     * @return CommodityInfo 商品信息
     * @see [类、类#方法、类#成员]
     */
    CommodityInfo getComInfoByID(Map<String, Object> params);
    
    CommodityInfo getComByComIdAndSysId(Map<String, Object> params);

}
