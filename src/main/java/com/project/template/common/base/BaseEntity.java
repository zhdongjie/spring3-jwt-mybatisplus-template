package com.project.template.common.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -3479780436799049199L;

	/**
	 * 主键 雪花算法生成id
	 */
	@TableId(type = IdType.ASSIGN_ID, value = "`id`")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
}
