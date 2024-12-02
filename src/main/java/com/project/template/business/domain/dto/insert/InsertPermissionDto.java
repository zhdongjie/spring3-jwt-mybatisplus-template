package com.project.template.business.domain.dto.insert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertPermissionDto {
	/**
	 * 权限名称
	 */
	private String name;

	/**
	 * 权限编码
	 */
	private String code;
}
