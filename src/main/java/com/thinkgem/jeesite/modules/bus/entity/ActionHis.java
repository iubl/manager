/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bus.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.dto.BaseDto;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 用户Entity
 * 
 * @author ThinkGem
 * @version 2013-12-05
 */
public class ActionHis extends BaseDto {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String batch;
	private String idCard;
	private String actionId;
	private String createBy;
	private Date createDate;
	private Date createDateStart;
	private Date createDateEnd;
	private String updateBy;
	private Date updateDate;
	private Date updateDateStart;
	private Date updateDateEnd;
	private Boolean delFlag;

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}

	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDateStart() {
		return updateDateStart;
	}

	public void setUpdateDateStart(Date updateDateStart) {
		this.updateDateStart = updateDateStart;
	}

	public Date getUpdateDateEnd() {
		return updateDateEnd;
	}

	public void setUpdateDateEnd(Date updateDateEnd) {
		this.updateDateEnd = updateDateEnd;
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getId() {
		return id;
	}

	public String getBatch() {
		return batch;
	}

	@Length(min = 1, max = 100, message = "登录名长度必须介于 1 和 20 之间")
	@ExcelField(title = "身份证", align = 2, sort = 30)
	public String getIdCard() {
		return idCard;
	}

	@Length(min = 1, max = 100, message = "登录名长度必须介于 1 和 20 之间")
	@ExcelField(title = "活动号", align = 2, sort = 40)
	public String getActionId() {
		return actionId;
	}

	@ExcelField(title = "第一次导入时间", type = 1, align = 2, sort = 50)
	public Date getCreateDate() {
		return createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

}