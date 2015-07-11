package com.ai.c.cinterface.commodity.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品展示图片信息业务对象
 * 
 * @author  LIUQIANMING
 * @version  [版本号, 2014-11-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CommodityPicture implements Serializable
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 3071545624363154294L;
    
    /** 序列ID */
    private Integer sequenceID;
    
    /** 商品内部ID */
    private String commodityInterID;
    
    /** 商品ID */
    private String commodityID;
    
    /** PC端图片URL */
    private String pcPicURL;
    
    /** 手机端图片URL */
    private String mobilePicURL;
    
    /** 商品图片类型 */
    private String picType;
    
    /** 所属系统ID */
    private String systemID;
    
    /** 供货商所在省份ID */
    private String provinceID;
    
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
    
    public String getCommodityInterID()
    {
        return commodityInterID;
    }
    
    public void setCommodityInterID(String commodityInterID)
    {
        this.commodityInterID = commodityInterID;
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
    
    public String getSystemID()
    {
        return systemID;
    }
    
    public void setSystemID(String systemID)
    {
        this.systemID = systemID;
    }
    
    public String getProvinceID()
    {
        return provinceID;
    }
    
    public void setProvinceID(String provinceID)
    {
        this.provinceID = provinceID;
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
    
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }
    
    @Override
    public String toString()
    {
        return "CommodityPicture [sequenceID=" + sequenceID + ", commodityInterID=" + commodityInterID
            + ", commodityID=" + commodityID + ", pcPicURL=" + pcPicURL + ", mobilePicURL=" + mobilePicURL
            + ", picType=" + picType + ", systemID=" + systemID + ", provinceID=" + provinceID + ", createTime="
            + createTime + ", updateTime=" + updateTime + "]";
    }
    
}
