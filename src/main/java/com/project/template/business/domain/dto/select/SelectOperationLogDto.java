package com.project.template.business.domain.dto.select;

import com.project.template.common.domain.dto.PageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SelectOperationLogDto extends PageDto {
	/**
	 * 用户账号
	 */
	private String username;

	/**
	 * 操作状态
	 */
	private String status;

	/**
	 * 业务类型
	 */
	private String type;

	/**
	 * 搜索起始时间
	 */
	private Date startTime;

	/**
	 * 搜索结束时间
	 */
	private Date endTime;
}
