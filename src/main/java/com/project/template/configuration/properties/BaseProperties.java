package com.project.template.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "base")
public class BaseProperties {
	/**
	 * 用户默认密码
	 */
	private String defaultPassword = "888888";

	/**
	 * 文件上传路径
	 */
	private String filePath = "uploadFile";
}
