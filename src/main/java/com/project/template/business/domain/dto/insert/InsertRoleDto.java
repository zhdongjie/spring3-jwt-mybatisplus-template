package com.project.template.business.domain.dto.insert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertRoleDto {
	/**
	 * 角色名称
	 */
	private String name;

	private List<Long> permissionIdList;
}
