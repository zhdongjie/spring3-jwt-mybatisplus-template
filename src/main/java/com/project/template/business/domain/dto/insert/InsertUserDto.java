package com.project.template.business.domain.dto.insert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertUserDto {
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

	/**
	 * 角色Id列表
	 */
	private List<Long> roleIdList;
}
