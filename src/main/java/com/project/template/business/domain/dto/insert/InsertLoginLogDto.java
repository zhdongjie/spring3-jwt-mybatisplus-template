package com.project.template.business.domain.dto.insert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertLoginLogDto {

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
}
