package com.project.template.business.domain.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
	private Long id;

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
	 * 用户角色IdList
	 */
	private List<Long> roleIdList;
}
