package com.project.template.business.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.template.common.base.BaseEntity;
import com.project.template.common.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_user")
public class User extends BaseEntity {

	/**
	 * 创建时间
	 */
	@TableField(value = "`created`")
	private Date created;

	/**
	 * 修改时间
	 */
	@TableField(value = "`updated`")
	private Date updated;

	/**
	 * 用户名
	 */
	@TableField(value = "`username`")
	private String username;

	/**
	 * 登录密码
	 */
	@TableField(value = "`password`")
	private String password;

	/**
	 * 真实姓名
	 */
	@TableField(value = "`real_name`")
	private String realName;

	/**
	 * 性别
	 */
	@TableField("`sex`")
	private String sex;

	/**
	 * 手机号
	 */
	@TableField("`phone_number`")
	private String phoneNumber;

	/**
	 * 状态
	 */
	@TableField(value = "`status`")
	private String status = StatusEnum.ENABLED.getName();
}
