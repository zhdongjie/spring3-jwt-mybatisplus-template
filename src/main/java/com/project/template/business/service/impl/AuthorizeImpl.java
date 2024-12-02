package com.project.template.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.template.common.async.AsyncFactory;
import com.project.template.common.async.AsyncManager;
import com.project.template.common.domain.vo.ResultBean;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.common.enums.StatusEnum;
import com.project.template.common.exception.BaseException;
import com.project.template.business.domain.dto.LoginDto;
import com.project.template.business.domain.dto.RegisterDto;
import com.project.template.business.domain.entity.User;
import com.project.template.business.domain.vo.AuthTokenVo;
import com.project.template.business.mapper.UserMapper;
import com.project.template.business.service.AuthorizeService;
import com.project.template.common.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthorizeImpl implements AuthorizeService {
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public ResultBean<AuthTokenVo> login(LoginDto loginDto) {
		var queryWrapper = new LambdaQueryWrapper<User>();
		queryWrapper.eq(User::getUsername, loginDto.getUsername());
		var user = userMapper.selectOne(queryWrapper);
		if (ObjUtil.isEmpty(user)) {
			throw new BaseException(ResponseEnum.RECORD_NOT_FOUND, "该用户不存在");
		}
		if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
			throw new BaseException(ResponseEnum.RECORD_NOT_FOUND, "用户名或密码错误");
		}
		if (StatusEnum.DISABLED.getName().equals(user.getStatus())) {
			throw new BaseException(ResponseEnum.RECORD_NOT_FOUND, "该用户被停用");
		}
		AsyncManager.getInstance().executor(AsyncFactory.loginLog(loginDto.getUsername(), "登录成功"));
		return ResultBean.result(ResponseEnum.OPERATING_SUCCESS, "登录成功", new AuthTokenVo(JwtUtils.createAccessToken(user)));
	}

	@Override
	@Transactional
	public ResultBean<ResponseEnum> register(RegisterDto registerDto) {
		if (!StrUtil.equals(registerDto.getPassword(), registerDto.getConfirmPassword())) {
			throw new BaseException(ResponseEnum.INVALID_PARAMETER, "两次密码不一致");
		}
		var queryWrapper = new LambdaQueryWrapper<User>();
		queryWrapper.eq(User::getUsername, registerDto.getUsername());
		int hasUsername = userMapper.selectCount(queryWrapper);
		if (hasUsername != 0) {
			throw new BaseException(ResponseEnum.INVALID_PARAMETER, "该用户名已存在");
		}
		var registerUser = BeanUtil.copyProperties(registerDto, User.class);
		registerUser.setCreated(new Date());
		var insert = userMapper.insert(registerUser);
		if (insert < 1) {
			throw new BaseException(ResponseEnum.INVALID_PARAMETER, "注册失败");
		}
		return ResultBean.success();
	}
}
