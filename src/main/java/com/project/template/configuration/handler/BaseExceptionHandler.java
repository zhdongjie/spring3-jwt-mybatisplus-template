package com.project.template.configuration.handler;

import com.project.template.common.domain.vo.ResultBean;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.common.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class BaseExceptionHandler {
	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ResultBean<ResponseEnum>> handler(BaseException baseException) {
		return ResponseEntity.ok(ResultBean.result(baseException.getResponseEnum(), baseException.getMessage()));
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<ResultBean<ResponseEnum>> handler(IOException ioException) {
		return ResponseEntity.ok(ResultBean.result(ResponseEnum.PLEASE_TRY_AGAIN_LATER, ioException.getMessage()));
	}
}
