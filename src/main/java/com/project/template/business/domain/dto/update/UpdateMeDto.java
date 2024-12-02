package com.project.template.business.domain.dto.update;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMeDto {
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
}
