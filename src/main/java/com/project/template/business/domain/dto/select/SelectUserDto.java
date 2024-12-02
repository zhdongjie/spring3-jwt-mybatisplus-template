package com.project.template.business.domain.dto.select;

import com.project.template.common.domain.dto.PageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SelectUserDto extends PageDto {
	/**
	 * 用户名
	 */
	private String realName;

	/**
	 * 用户手机号
	 */
	private String phoneNumber;

	/**
	 * 用户状态
	 */
	private String status;
}
