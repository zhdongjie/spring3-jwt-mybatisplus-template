package com.project.template.business.domain.vo;

import com.project.template.business.domain.entity.FileInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoVo {

	private Long id;

	/**
	 * 业务ID
	 */
	private Long businessId;

	/**
	 * 创建时间
	 */
	private Date created;

	/**
	 * 文件标题
	 */
	private String title;

	/**
	 * 文件路径
	 */
	private String path;

	/**
	 * 文件类型
	 */
	private String type;

	public FileInfoVo(FileInfo fileInfo) {
		this.id = fileInfo.getId();
		this.businessId = fileInfo.getBusinessId();
		this.created = fileInfo.getCreated();
		this.title = fileInfo.getTitle();
		this.path = fileInfo.getPath();
		this.type = fileInfo.getType();
	}
}
