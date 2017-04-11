package com.thinkgem.jeesite.common.iubl;

import com.thinkgem.jeesite.common.dto.BaseDto;

public class SoaUtils {

	public static void getPsmPageInfo(com.thinkgem.jeesite.common.persistence.Page<?> pageWeb, BaseDto dto) {
		dto.setLimitStart((pageWeb.getPageNo() - 1) * pageWeb.getPageSize());
		dto.setLimitEnd(pageWeb.getPageSize());
	}

}
