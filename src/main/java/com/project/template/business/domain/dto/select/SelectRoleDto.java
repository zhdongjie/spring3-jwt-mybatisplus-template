package com.project.template.business.domain.dto.select;

import com.project.template.common.domain.dto.PageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SelectRoleDto extends PageDto {
	private String name;
}
