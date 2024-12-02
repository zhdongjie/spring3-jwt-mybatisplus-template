package com.project.template.business.domain.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePermissionDto {
	private Long id;

	private String name;

	private String code;
}
