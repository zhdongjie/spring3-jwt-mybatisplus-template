package com.project.template.business.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.template.common.constants.SystemConstant;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MeVo {

	@JsonFormat(pattern = SystemConstant.DEFAULT_DATE_FORMAT, timezone = "GMT+8")
	private Date created;

	private String username;

	private String realName;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 手机号
	 */
	private String phoneNumber;

	private List<RoleVo> roleList;

	private List<PermissionVo> permissionList;
}
