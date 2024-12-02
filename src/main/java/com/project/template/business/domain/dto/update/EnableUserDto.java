package com.project.template.business.domain.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnableUserDto {

	/**
	 * 用户Id
	 */
	private Long id;

	/**
	 * 启用状态
	 */
	private String status;
}