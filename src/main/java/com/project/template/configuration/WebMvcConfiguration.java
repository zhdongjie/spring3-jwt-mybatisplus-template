package com.project.template.configuration;

import com.project.template.common.constants.SystemConstant;
import com.project.template.configuration.handler.AuthorizeHandlerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {
	private final AuthorizeHandlerInterceptor authorizeHandlerInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		 registry.addInterceptor(authorizeHandlerInterceptor).addPathPatterns("/system/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/file/**")
				.addResourceLocations("file:" + System.getProperty("user.dir") + SystemConstant.CHARACTER_FORWARD_SLASH);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
				// 所有接口
				.addMapping("/**")
				// 是否发送 Cookie
				.allowCredentials(true)
				// 支持域
				.allowedOriginPatterns("*")
				// 支持方法
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("*")
				.exposedHeaders("*");
	}
}
