package com.project.template.common.exception;

import com.project.template.common.enums.ResponseEnum;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 488685191232748696L;
	private final ResponseEnum responseEnum;

	public BaseException(ResponseEnum responseEnum, String message) {
		super(message);
		this.responseEnum = responseEnum;
	}
}
