package com.project.template.common.utils;

import cn.hutool.core.util.StrUtil;
import com.project.template.common.constants.SystemConstant;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.common.exception.BaseException;
import io.jsonwebtoken.Claims;

public class AuthUtils {

	/**
	 * 获取当前请求username
	 */
	public static String getUsername() {
		var claims = getUserClaims();
		return claims.getSubject();
	}

	/**
	 * 获取当前请求用户Id
	 */
	public static Long getUserId() {
		var claims = getUserClaims();
		return Long.parseLong(claims.getId());
	}

	private static Claims getUserClaims() {
		var token = ServletUtils.getRequest().getHeader(SystemConstant.AUTHORIZE_KEY);
		if (StrUtil.isBlank(token)) {
			throw new BaseException(ResponseEnum.UNAUTHORIZED, "获取登录信息异常");
		}
		var accessToken = token.replace(SystemConstant.AUTHORIZE_HEADER, "");
		return JwtUtils.parseAccessTokenClaims(accessToken)
				.orElseThrow(() -> new BaseException(ResponseEnum.UNAUTHORIZED, "获取登录信息异常"));
	}
}
