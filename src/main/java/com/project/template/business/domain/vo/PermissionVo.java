package com.project.template.business.domain.vo;

import com.project.template.business.domain.entity.Permission;
import lombok.Data;


@Data
public class PermissionVo {

	private Long id;

	/**
	 * 权限名称
	 */
	private String name;

	/**
	 * 权限编码
	 */
	private String code;

	public PermissionVo(Permission permission) {
		this.id = permission.getId();
		this.name = permission.getName();
		this.code = permission.getCode();
	}
}
