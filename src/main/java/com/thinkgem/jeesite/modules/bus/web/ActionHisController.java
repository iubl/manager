/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bus.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.iubl.SoaUtils;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.bus.entity.ActionHis;
import com.thinkgem.jeesite.modules.bus.service.ActionHisService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 用户Controller
 * 
 * @author ThinkGem
 * @version 2013-8-29
 */
@Controller
@RequestMapping(value = "${adminPath}/bus/action_his")
public class ActionHisController extends BaseController {

	@Autowired
	private ActionHisService svcActionHis;

	@RequestMapping(value = { "list", "" })
	public String list(ActionHis actionHis, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (actionHis.getCreateDateStart() == null) {
			actionHis.setCreateDateStart(DateUtils.addDays(new Date(), -7));
		}
		Page<ActionHis> page = new Page<ActionHis>(request, response);
		SoaUtils.getPsmPageInfo(page, actionHis);
		page.setCount(svcActionHis.getCount(actionHis));
		page.setList(svcActionHis.getList(actionHis));
		model.addAttribute("page", page);
		return "modules/bus/ActionHis";
	}

	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(ActionHis dto, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "导出" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<ActionHis> list = svcActionHis.getList(dto);
			new ExportExcel("导出", ActionHis.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/bus/action_his/list?repage";
	}

	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes, HttpServletResponse response) {
		try {
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<ActionHis> list = ei.getDataList(ActionHis.class);
			logger.debug(JsonMapper.toJsonString(list));
			for (int i = 0; i < list.size(); i++) {
				ActionHis tmp = list.get(i);
				if (tmp.getIdCard() == null || "".equals(tmp.getIdCard()) || tmp.getActionId() == null || "".equals(tmp.getActionId())) {
					list.remove(i);
					i--;
					continue;
				}
				Date lastDate = svcActionHis.isExist(tmp.getIdCard(), tmp.getActionId());
				if (lastDate != null) {
					tmp.setCreateDate(lastDate);
				} else {
					svcActionHis.save(tmp);
					list.remove(i);
					i--;
					continue;
				}
			}
			if (list.isEmpty()) {
				addMessage(redirectAttributes, "已成功导入:无重复记录");

			} else {
				new ExportExcel("失败列表", ActionHis.class).setDataList(list).write(response, "失败列表.xlsx").dispose();
			}

		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/bus/action_his/list?repage";
	}

	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "导入模板.xlsx";
			List<User> list = Lists.newArrayList();
			list.add(UserUtils.getUser());
			new ExportExcel("模板", ActionHis.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/bus/action_his/list?repage";
	}

}
