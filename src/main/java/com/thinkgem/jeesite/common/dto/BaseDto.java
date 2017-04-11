package com.thinkgem.jeesite.common.dto;

import java.io.Serializable;

public class BaseDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5459668280044885683L;
	private Integer limitStart;
	private Integer limitEnd;
	private String strSqlWhere;
	private String strSqlOrderBy;

	public Integer getLimitStart() {
		return limitStart;
	}

	public void setLimitStart(Integer limitStart) {
		this.limitStart = limitStart;
	}

	public Integer getLimitEnd() {
		return limitEnd;
	}

	public void setLimitEnd(Integer limitEnd) {
		this.limitEnd = limitEnd;
	}

	public String getStrSqlWhere() {
		return strSqlWhere;
	}

	public void setStrSqlWhere(String strSqlWhere) {
		this.strSqlWhere = strSqlWhere;
	}

	public String getStrSqlOrderBy() {
		return strSqlOrderBy;
	}

	public void setStrSqlOrderBy(String strSqlOrderBy) {
		this.strSqlOrderBy = strSqlOrderBy;
	}

}
