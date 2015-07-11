/*
 * 文 件 名:  CommodityDetailPicture.java
 * 版    权:   Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:   LIUQIANMING
 * 修改时间:  2015-07-11 
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ai.c.cinterface.commodity.entity;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品详情图片信息业务对象
 * 
 * @author  LIUQIANMING
 * @version  [版本号, 2015-07-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CommodityDetailPicture implements Serializable
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 7301347309158148368L;
    
    /** 序列ID */
    private Integer sequenceID;
 
    /** 商品ID */
    private String commodityID;
    
    /** PC端图片URL */
    private String pcPicURL;
    
    /** 手机端图片URL */
    private String mobilePicURL;
    
    /** 商品图片类型 */
    private String picType;
    
    /** 商品详情 (富文本)**/
    private String detailInfo;
    
    /** 所属系统ID */
    private String sysId;
    
    /** 图片排序字段 */
    private int orderID;
    
    /** 创建时间 */
    private Date createTime;
    
    /** 更新时间 */
    private Date updateTime;
    
    public Integer getSequenceID()
    {
        return sequenceID;
    }
    
    public void setSequenceID(Integer sequenceID)
    {
        this.sequenceID = sequenceID;
    }
    
    public String getCommodityID()
    {
        return commodityID;
    }
    
    public void setCommodityID(String commodityID)
    {
        this.commodityID = commodityID;
    }
    
    public String getPcPicURL()
    {
        return pcPicURL;
    }
    
    public void setPcPicURL(String pcPicURL)
    {
        this.pcPicURL = pcPicURL;
    }
    
    public String getMobilePicURL()
    {
        return mobilePicURL;
    }
    
    public void setMobilePicURL(String mobilePicURL)
    {
        this.mobilePicURL = mobilePicURL;
    }
    
    public String getPicType()
    {
        return picType;
    }
    
    public void setPicType(String picType)
    {
        this.picType = picType;
    }

    
    public int getOrderID()
    {
        return orderID;
    }
    
    public void setOrderID(int orderID)
    {
        this.orderID = orderID;
    }
    public Date getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    
    public Date getUpdateTime()
    {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }
    
    public String getDetailInfo() {
		return detailInfo;
	}

	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
	}

	@Override
    public String toString()
    {
        return "CommodityDetailPicture [sequenceID=" + sequenceID
            + ", commodityID=" + commodityID + ", pcPicURL=" + pcPicURL + ", mobilePicURL=" + mobilePicURL
            + ", picType=" + picType + ", sysId=" + sysId + ", orderID=" + orderID
            + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
    }
    
}
