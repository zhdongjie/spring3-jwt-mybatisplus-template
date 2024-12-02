package com.project.template.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResponseEnum {

	/**
	 * 20000 成功
	 */
	OPERATING_SUCCESS(20000),

	/**
	 * 40000 参数有误
	 */
	INVALID_PARAMETER(40001),

	/**
	 * 40100 未授权
	 */
	UNAUTHORIZED(40100),

	/**
	 * 40400 记录不存在
	 */
	RECORD_NOT_FOUND(40400),

	/**
	 * 40300 拒绝访问
	 */
	FORBIDDEN(40300),

	/**
	 * 50000 服务器内部错误
	 */
	PLEASE_TRY_AGAIN_LATER(50000),
	;

	private Integer code;
}
