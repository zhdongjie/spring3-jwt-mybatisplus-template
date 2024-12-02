package com.project.template.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum StatusEnum {

	/**
	 * 启用
	 */
	ENABLED("启用", 1),

	/**
	 * 禁用
	 */
	DISABLED("禁用", -1),

	/**
	 * 成功
	 */
	SUCCESS("success", 1),

	/**
	 * 失败
	 */
	FAIL("fail", -1),

	;

	/**
	 * 状态名称
	 */
	private String name;

	/**
	 * 状态值
	 */
	private Integer value;
}