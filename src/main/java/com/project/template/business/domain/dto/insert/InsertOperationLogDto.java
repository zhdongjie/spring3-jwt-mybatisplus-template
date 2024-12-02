package com.project.template.business.domain.dto.insert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertOperationLogDto {

	/**
	 * 用户账号
	 */
	private String username;

	/**
	 * 模块标题
	 */
	private String title;

	/**
	 * 业务类型(新增 修改 删除)
	 */
	private String type;

	/**
	 * 方法名称
	 */
	private String method;

	/**
	 * 请求参数
	 */
	private String params;

	/**
	 * 返回参数
	 */
	private String result;

	/**
	 * 操作状态(成功 失败)
	 */
	private String status;

	/**
	 * 备注
	 */
	private String remark;
}
