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
public class SelectLoginLogDto extends PageDto {
	/**
	 * 用户账号
	 */
	private String username;

	/**
	 * 登录状态
	 */
	private String status;

	/**
	 * 搜索起始时间
	 */
	private Date startTime;

	/**
	 * 搜索结束时间
	 */
	private Date endTime;
}