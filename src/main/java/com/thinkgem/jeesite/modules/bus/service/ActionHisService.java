/**
 * Copyright &copy; 2012-2013 <a href="httparamMap://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bus.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.modules.bus.dao.ActionHisDao;
import com.thinkgem.jeesite.modules.bus.entity.ActionHis;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 日志Service
 * 
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
public class ActionHisService {

	@Autowired
	ActionHisDao dao;

	public List<ActionHis> getList(ActionHis dto) {
		dto.setStrSqlOrderBy(" id desc ");
		return dao.selectListByWhere(dto);
	}

	public int getCount(ActionHis dto) {
		return dao.selectCountByWhere(dto);
	}

	public Date isExist(String idCard, String actionId) {

		ActionHis query = new ActionHis();
		query.setIdCard(idCard);
		query.setActionId(actionId);
		query.setDelFlag(false);
		List<ActionHis> list = dao.selectListByWhere(query);
		if (list != null && !list.isEmpty()) {
			return list.get(0).getCreateDate();
		}
		return null;

	}

	public void insert(ActionHis dto) {
		dao.insertSelective(dto);
	}

	public void save(ActionHis dto) {
		dto.setUpdateBy(UserUtils.getUser().getId());
		dto.setUpdateDate(new Date());
		if (dto.getId() == null) {
			dto.setCreateBy(UserUtils.getUser().getId());
			dto.setCreateDate(new Date());
			dto.setDelFlag(false);
			dao.insertSelective(dto);
		} else {
			dao.updateByPrimaryKeySelective(dto);
		}
	}

}
