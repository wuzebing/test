package com.ai.c.cinterface.admin.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class AdminInfo implements Serializable{

	private static final long serialVersionUID = -2118138798089024162L;
	/** 自增列 */
	private BigInteger sequenceId;
	/** 用户id */
	private String userId;
	/** 真实姓名 */
	private String realName;
	/** 证件类型 */
	private Integer idtype;
	/** 省编码 */
	private String provinceId;
	/** 区号编码 */
	private String areaId;
	/** 证件号码 */
	private String idNumber;
	/** 手机号码 */
	private String phoneNumber;
	/** 常用邮箱 */
	private String email;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	public BigInteger getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(BigInteger sequenceId) {
		this.sequenceId = sequenceId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Integer getIdtype() {
		return idtype;
	}
	public void setIdtype(Integer idtype) {
		this.idtype = idtype;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
