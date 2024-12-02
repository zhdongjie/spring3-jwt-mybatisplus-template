package com.project.template.business.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.template.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_permission")
public class Permission extends BaseEntity {

	/**
	 * 名称
	 */
	@TableField("`name`")
	private String name;

	/**
	 * 编码
	 */
	@TableField("`code`")
	private String code;
}
