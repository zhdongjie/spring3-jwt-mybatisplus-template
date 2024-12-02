package com.project.template.common.aspect.annotation;


import com.project.template.common.constants.LogConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
	/**
	 * 业务名称
	 */
	String value() default "";

	/**
	 * 操作类型
	 */
	LogConstant.OperationTypeEnum type() default LogConstant.OperationTypeEnum.OTHER;

	/**
	 * 操作状态
	 */
	LogConstant.OperationStatusEnum status() default LogConstant.OperationStatusEnum.SUCCESS;

	/**
	 * 是否保存请求参数
	 */
	boolean saveParam() default true;

	/**
	 * 是否保存响应
	 */
	boolean saveResult() default true;
}
