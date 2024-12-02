package com.project.template.business.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.template.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_operation_log")
public class OperationLog extends BaseEntity {

	/**
	 * 创建时间
	 */
	@TableField(value = "`created`")
	private Date created;

	/**
	 * 用户账号
	 */
	@TableField(value = "`username`")
	private String username;

	/**
	 * 模块标题
	 */
	@TableField(value = "`title`")
	private String title;

	/**
	 * 业务类型(新增 修改 删除)
	 */
	@TableField(value = "`type`")
	private String type;

	/**
	 * 方法名称
	 */
	@TableField(value = "`method`")
	private String method;

	/**
	 * 请求参数
	 */
	@TableField(value = "`params`")
	private String params;

	/**
	 * 返回参数
	 */
	@TableField(value = "`result`")
	private String result;

	/**
	 * 操作状态(成功 失败)
	 */
	@TableField(value = "`status`")
	private String status;

	/**
	 * 备注
	 */
	@TableField(value = "`remark`")
	private String remark;
}
