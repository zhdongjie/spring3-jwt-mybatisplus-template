package com.project.template.business.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.template.common.constants.SystemConstant;
import com.project.template.business.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = SystemConstant.DEFAULT_DATE_FORMAT, timezone = "GMT+8")
	private Date created;

	/**
	 * 修改时间
	 */
	@JsonFormat(pattern = SystemConstant.DEFAULT_DATE_FORMAT, timezone = "GMT+8")
	private Date updated;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 手机号
	 */
	private String phoneNumber;

	private String status;

	private List<RoleVo> roleList;

	public UserVo(User user, List<RoleVo> roleList) {
		this.id = user.getId();
		this.created = user.getCreated();
		this.updated = user.getUpdated();
		this.username = user.getUsername();
		this.realName = user.getRealName();
		this.sex = user.getSex();
		this.phoneNumber = user.getPhoneNumber();
		this.status = user.getStatus();
		this.roleList = roleList;
	}
}
