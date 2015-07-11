package com.ai.c.cinterface.commodity.entity;
/*
 * 文 件 名:  CommodityInfo.java
 * 版    权:  AsiaInfo Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:   LIUQIANMING
 * 修改时间:  2015-07-11 
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
import java.util.Date;
import java.util.List;

import com.ai.c.cinterface.commodity.entity.CommodityDetailPicture;
import com.ai.c.cinterface.commodity.entity.CommodityPicture;

/**
 * 商品信息业务基本对象
 * 
 * @author  LIUQIANMING
 * @version  [版本号, 2015-07-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CommodityInfo {
	/** 商品ID*/
	private String commodityId;
	/** 商品名称*/
	private String commodityName;
	/** 商品价格*/
	private int commodityPrice;
	/** 状态信息*/
	private String StatusId;
	/** 标记*/
	private String remark;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 商品PC展示图片*/
    private String picPicture;
    /** 商品Mobile展示图片*/
    private String mobilePicture;
    /** 扩展属性 Start */
    /** 商品展示图片 */
    private List<CommodityPicture> commodityPictures;
    
    /** 商品详情图片 */
    private List<CommodityDetailPicture> commodityDetailPictures;
    
	public String getPicPicture() {
		return picPicture;
	}
	public void setPicPicture(String picPicture) {
		this.picPicture = picPicture;
	}
	public String getMobilePicture() {
		return mobilePicture;
	}
	public void setMobilePicture(String mobilePicture) {
		this.mobilePicture = mobilePicture;
	}
	public List<CommodityPicture> getCommodityPictures() {
		return commodityPictures;
	}
	public void setCommodityPictures(List<CommodityPicture> commodityPictures) {
		this.commodityPictures = commodityPictures;
	}
	public List<CommodityDetailPicture> getCommodityDetailPictures() {
		return commodityDetailPictures;
	}
	public void setCommodityDetailPictures(
			List<CommodityDetailPicture> commodityDetailPictures) {
		this.commodityDetailPictures = commodityDetailPictures;
	}
	public String getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public int getCommodityPrice() {
		return commodityPrice;
	}
	public void setCommodityPrice(int commodityPrice) {
		this.commodityPrice = commodityPrice;
	}
	public String getStatusId() {
		return StatusId;
	}
	public void setStatusId(String statusId) {
		StatusId = statusId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	

}
