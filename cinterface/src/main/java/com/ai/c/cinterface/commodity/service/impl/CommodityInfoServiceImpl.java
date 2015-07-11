/*
 * 文 件 名:  CommodityInfoServiceImpl.java
 * 版    权:   Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  LIUQIANMING
 * 修改时间:  2015-07-11
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ai.c.cinterface.commodity.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.c.base.dao.Page;
import com.ai.c.cinterface.commodity.entity.CommodityInfo;
import com.ai.c.cinterface.commodity.service.CommodityInfoService;
import com.ai.c.cinterface.commodity.dao.CommodityInfoMapper;
/**
 * 商品信息管理-业务逻辑
 * 
 * @author LIUQIANMING
 * @version [版本号, 2015-07-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class CommodityInfoServiceImpl implements CommodityInfoService{
    /**获取日志记录对象*/
    private static final Logger logger = LoggerFactory.getLogger(CommodityInfoServiceImpl.class);
    
    @Autowired
    private CommodityInfoMapper commodityInfoMapper;
	 /**
     * {@inheritDoc}
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Page<CommodityInfo> queryComInfoListPage(Map<String, Object> params, int pageNo, int pageSize)
        throws Exception
    {
        Page<CommodityInfo> result = new Page<CommodityInfo>(pageNo, pageSize);
        try
        {
            params.put(Page.PAGE, result);//设置分页参数
            List<CommodityInfo> commodities = commodityInfoMapper.queryComInfoListPage(params);//获取商品列表信息
            result.setResult(commodities);
        }
        catch (Exception e)
        {
            // TODO: handle exception 日志记录
        	logger.error(e.toString());
            throw e;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommodityInfo getComInfoByID(Map<String, Object> params)
        throws Exception
    {
        CommodityInfo commodityInfo = null;
        try
        {
            commodityInfo = commodityInfoMapper.getComInfoByID(params);//获取单个商品基本信息
//            //保存图片
//            List<CommodityPicture> commodityPictures =
//                commodityPictureMapper.getPicturesByID(commodityInfo.getCommodityInterID(), null);
//            commodityInfo.setCommodityPictures(commodityPictures);
//            //商品详情图片
//            List<CommodityDetailPicture> commodityDetailPictures =
//                commodityDetailPicMapper.getDetailPicturesByID(commodityInfo.getCommodityInterID(), null);
//            commodityInfo.setCommodityDetailPictures(commodityDetailPictures);

        }
        catch (Exception e)
        {
        	e.printStackTrace();
            logger.error("=====ERROR=====, Invoke interface method " + "getComInfoByID" + params.toString() + e);
            throw e;
        }
        return commodityInfo;
    }

	@Override
	public CommodityInfo getComByComIdAndSysId(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
