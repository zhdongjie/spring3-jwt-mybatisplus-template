package com.project.template.business.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.template.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_login_log")
public class LoginLog extends BaseEntity {

	/**
	 * 创建时间
	 */
	@TableField(value = "`created`")
	private Date created;

	/**
	 * 用户账号
	 */
	@TableField(value = "`username`")
	private String username;

	/**
	 * 登录IP地址
	 */
	@TableField(value = "`ip_address`")
	private String ipAddress;

	/**
	 * 登录地点
	 */
	@TableField(value = "`login_location`")
	private String loginLocation;

	/**
	 * 登录状态
	 */
	@TableField(value = "`status`")
	private String status;

	/**
	 * 备注
	 */
	@TableField(value = "`remark`")
	private String remark;
}
