package com.project.template.business.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
	/**
	 * 登录名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;
}
