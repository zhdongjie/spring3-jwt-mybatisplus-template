package com.project.template.common.domain.vo;

import com.project.template.common.enums.ResponseEnum;
import lombok.Data;

@Data
public class ResultBean<T> {

	private int code;
	private String message;
	private long timestamp;
	private T data;

	public static <T> ResultBean<T> success() {
		return resultBean(ResponseEnum.OPERATING_SUCCESS.getCode(), "操作成功", null);
	}

	public static <T> ResultBean<T> success(T data) {
		return resultBean(ResponseEnum.OPERATING_SUCCESS.getCode(), "操作成功", data);
	}

	public static <T> ResultBean<T> fail() {
		return resultBean(ResponseEnum.PLEASE_TRY_AGAIN_LATER.getCode(), "操作失败", null);
	}

	public static <T> ResultBean<T> result(ResponseEnum responseEnum, String message) {
		return resultBean(responseEnum.getCode(), message, null);
	}

	public static <T> ResultBean<T> result(ResponseEnum responseEnum, String message, T data) {
		return resultBean(responseEnum.getCode(), message, data);
	}

	private static <T> ResultBean<T> resultBean(int code, String message, T data) {
		ResultBean<T> resultBean = new ResultBean<>();
		resultBean.setCode(code);
		resultBean.setMessage(message);
		resultBean.setData(data);
		resultBean.setTimestamp(System.currentTimeMillis());
		return resultBean;
	}
}
