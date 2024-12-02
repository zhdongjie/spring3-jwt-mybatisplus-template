package com.project.template.business.service;

import com.project.template.common.domain.vo.ResultBean;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.business.domain.dto.LoginDto;
import com.project.template.business.domain.dto.RegisterDto;
import com.project.template.business.domain.vo.AuthTokenVo;

public interface AuthorizeService {

	/**
	 * 用户登录
	 *
	 * @param loginDto 登录信息
	 * @return 登录响应
	 */
	ResultBean<AuthTokenVo> login(LoginDto loginDto);

	/**
	 * 用户注册
	 * @param registerDto 注册信息
	 * @return 注册响应
	 */
	ResultBean<ResponseEnum> register(RegisterDto registerDto);
}
