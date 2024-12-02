package com.project.template.business.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_file_info")
public class FileInfo implements Serializable {

	private static final long serialVersionUID = 6028902399219143791L;

	@TableId(type = IdType.ASSIGN_ID, value = "`id`")
	private Long id;

	/**
	 * 业务ID
	 */
	@TableField(value = "`business_id`")
	private Long businessId;

	/**
	 * 创建时间
	 */
	@TableField(value = "`created`")
	private Date created;

	/**
	 * 文件标题
	 */
	@TableField(value = "`title`")
	private String title;

	/**
	 * 文件路径
	 */
	@TableField(value = "`path`")
	private String path;

	/**
	 * 文件类型
	 */
	@TableField(value = "`type`")
	private String type;

}
