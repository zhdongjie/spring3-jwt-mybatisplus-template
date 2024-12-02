package com.project.template.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.common.domain.dto.DeleteBatchDto;
import com.project.template.common.domain.vo.PageVo;
import com.project.template.common.domain.vo.ResultBean;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.common.exception.BaseException;
import com.project.template.business.domain.dto.insert.InsertLoginLogDto;
import com.project.template.business.domain.dto.select.SelectLoginLogDto;
import com.project.template.business.domain.entity.LoginLog;
import com.project.template.business.domain.vo.LoginLogVo;
import com.project.template.business.mapper.LoginLogMapper;
import com.project.template.business.service.LoginLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

	private final LoginLogMapper loginLogMapper;

	@Override
	@Transactional(rollbackFor = BaseException.class)
	public ResultBean<ResponseEnum> insert(InsertLoginLogDto insertLoginLogDto) {
		var loginLog = BeanUtil.copyProperties(insertLoginLogDto, LoginLog.class);
		int insert = loginLogMapper.insert(loginLog);
		if (insert < 1) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "登录日志添加失败");
		}
		return ResultBean.success();
	}

	@Override
	@Transactional(rollbackFor = BaseException.class)
	public ResultBean<ResponseEnum> delete(DeleteBatchDto deleteBatchDto) {
		var deleteIdList = deleteBatchDto.getIds();
		int deleted = loginLogMapper.deleteBatchIds(deleteIdList);
		if (deleted < 1) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "删除权限失败");
		}
		return ResultBean.success();
	}

	@Override
	@Transactional(readOnly = true)
	public ResultBean<PageVo<LoginLogVo>> pageLoginLog(SelectLoginLogDto selectLoginLogDto) {
		var queryWrapper = new LambdaQueryWrapper<LoginLog>();
		queryWrapper.like(StrUtil.isNotBlank(selectLoginLogDto.getUsername()), LoginLog::getUsername, selectLoginLogDto.getUsername())
				.eq(StrUtil.isNotBlank(selectLoginLogDto.getStatus()), LoginLog::getStatus, selectLoginLogDto.getStatus())
				.ge(ObjUtil.isNotEmpty(selectLoginLogDto.getStartTime()), LoginLog::getCreated, selectLoginLogDto.getStartTime())
				.le(ObjUtil.isNotEmpty(selectLoginLogDto.getEndTime()), LoginLog::getCreated, selectLoginLogDto.getEndTime());
		var page = loginLogMapper.selectPage(new Page<>(selectLoginLogDto.getCurrent(), selectLoginLogDto.getSize()), queryWrapper);
		var loginLogVoList = page.getRecords().stream().map(LoginLogVo::new).collect(Collectors.toList());
		return ResultBean.success(new PageVo<>(page.getPages(), page.getSize(), page.getTotal(), loginLogVoList));
	}

	@Override
	@Transactional(readOnly = true)
	public ResultBean<List<LoginLogVo>> listLoginLog() {
		return ResultBean.success(loginLogMapper.selectList(null).stream().map(LoginLogVo::new).collect(Collectors.toList()));
	}
}
