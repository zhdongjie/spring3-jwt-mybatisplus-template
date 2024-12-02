package com.project.template.business.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokenVo {
	/**
	 * 用户token
	 */
	private String accessToken;
}
