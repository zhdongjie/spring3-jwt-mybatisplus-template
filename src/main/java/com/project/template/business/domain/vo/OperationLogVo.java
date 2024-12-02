package com.project.template.business.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.template.common.constants.SystemConstant;
import com.project.template.business.domain.entity.OperationLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationLogVo {

	private Long id;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = SystemConstant.DEFAULT_DATE_FORMAT, timezone = "GMT+8")
	private Date created;

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

	public OperationLogVo(OperationLog operationLog) {
		this.id = operationLog.getId();
		this.created = operationLog.getCreated();
		this.username = operationLog.getUsername();
		this.title = operationLog.getTitle();
		this.type = operationLog.getType();
		this.method = operationLog.getMethod();
		this.params = operationLog.getParams();
		this.result = operationLog.getResult();
		this.status = operationLog.getStatus();
		this.remark = operationLog.getRemark();
	}
}
