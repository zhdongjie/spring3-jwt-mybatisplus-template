package com.project.template.business.domain.dto.insert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertFileInfoDto {
	/**
	 * 业务ID
	 */
	private Long businessId;

	/**
	 * 文件标题
	 */
	private String title;

	/**
	 * 文件
	 */
	private MultipartFile file;
}
