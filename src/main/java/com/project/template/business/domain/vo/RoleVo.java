package com.project.template.business.domain.vo;

import com.project.template.business.domain.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class RoleVo {
	/**
	 * 角色Id
	 */
	private Long id;

	/**
	 * 角色名称
	 */
	private String name;

	private List<PermissionVo> permissionList;

	public RoleVo(Role role) {
		this.id = role.getId();
		this.name = role.getName();
	}

	public RoleVo(Role role, List<PermissionVo> permissionList) {
		this.id = role.getId();
		this.name = role.getName();
		this.permissionList = permissionList;
	}
}
