package com.project.template.common.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {

	/**
	 * 当前页
	 */
	private Long current;

	/**
	 * 数据条数
	 */
	private Long size;
}
