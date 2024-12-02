package com.project.template.configuration.handler;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.project.template.common.constants.SystemConstant;
import com.project.template.common.domain.vo.ResultBean;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.common.utils.JwtUtils;
import com.project.template.common.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AuthorizeHandlerInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if ("OPTIONS".equals(request.getMethod())) {
			return true;
		}
		String token = request.getHeader(SystemConstant.AUTHORIZE_KEY);
		if (StrUtil.isBlank(token)) {
			return forbidden(response);
		}
		if (!token.startsWith(SystemConstant.AUTHORIZE_HEADER)) {
			return forbidden(response);
		}
		var accessToken = token.replace(SystemConstant.AUTHORIZE_HEADER, "");
		if (!JwtUtils.validateAccessTokenWithoutExpiration(accessToken)) {
			return forbidden(response);
		}
		return true;
	}

	private boolean forbidden(HttpServletResponse response) {
		var resultStr = JSON.toJSONString(ResultBean.result(ResponseEnum.FORBIDDEN, "用户禁止访问"));
		ServletUtils.renderString(response, resultStr);
    return false;
	}
}
