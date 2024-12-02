package com.project.template.business.domain.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoleDto {
	private Long id;

	private String name;

	private List<Long> permissionIdList;
}
