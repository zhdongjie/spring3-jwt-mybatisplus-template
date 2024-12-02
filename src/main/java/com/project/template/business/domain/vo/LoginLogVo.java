package com.project.template.business.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.template.common.constants.SystemConstant;
import com.project.template.business.domain.entity.LoginLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginLogVo {

	private Long id;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = SystemConstant.DEFAULT_DATE_FORMAT, timezone = "GMT+8")
	private Date created;

	/**
	 * 用户账号
	 */
	private String username;

	/**
	 * 登录IP地址
	 */
	private String ipAddress;

	/**
	 * 登录地点
	 */
	private String loginLocation;

	/**
	 * 登录状态
	 */
	private String status;

	/**
	 * 备注
	 */
	private String remark;

	public LoginLogVo(LoginLog loginLog) {
		this.id = loginLog.getId();
		this.created = loginLog.getCreated();
		this.username = loginLog.getUsername();
		this.ipAddress = loginLog.getIpAddress();
		this.loginLocation = loginLog.getLoginLocation();
		this.status = loginLog.getStatus();
		this.remark = loginLog.getRemark();
	}
}
