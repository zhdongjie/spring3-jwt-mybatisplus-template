package com.project.template.common.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageVo<T> {
	/**
	 * 当前页码
	 */
	private Long pageNum;

	/**
	 * 每页大小
	 */
	private Long pageSize;

	/**
	 * 总页数
	 */
	private Long total;

	/**
	 * 返回数据
	 */
	private List<T> data;
}
