/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bus.entity.ActionHis;

/**
 * 用户DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface ActionHisDao {
	
	int insert(ActionHis record);	
	int insertSelective(ActionHis record);
	int deleteByPrimaryKey(@Param("id") Integer id);
	int updateByPrimaryKeySelective(ActionHis record);
	int updateByPrimaryKey(ActionHis record);
	int selectCountByWhere(ActionHis Entity);
	ActionHis selectByPrimaryKey(@Param("id") Integer id);
	ActionHis selectSingleByWhere(ActionHis Entity);
	List<ActionHis> selectListByWhere(ActionHis Entity);

}
