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
import com.project.template.business.domain.dto.insert.InsertOperationLogDto;
import com.project.template.business.domain.dto.select.SelectOperationLogDto;
import com.project.template.business.domain.entity.OperationLog;
import com.project.template.business.domain.vo.OperationLogVo;
import com.project.template.business.mapper.OperationLogMapper;
import com.project.template.business.service.OperationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {

	private final OperationLogMapper operationLogMapper;

	@Override
	@Transactional(rollbackFor = BaseException.class)
	public ResultBean<ResponseEnum> insert(InsertOperationLogDto insertOperationLogDto) {
		var operationLog = BeanUtil.copyProperties(insertOperationLogDto, OperationLog.class);
		int insert = operationLogMapper.insert(operationLog);
		if (insert < 1) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "操作日志添加失败");
		}
		return ResultBean.success();
	}

	@Override
	@Transactional(rollbackFor = BaseException.class)
	public ResultBean<ResponseEnum> delete(DeleteBatchDto deleteBatchDto) {
		var deleteIdList = deleteBatchDto.getIds();
		int deleted = operationLogMapper.deleteBatchIds(deleteIdList);
		if (deleted < 1) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "删除操作日志失败");
		}
		return ResultBean.success();
	}

	@Override
	@Transactional(readOnly = true)
	public ResultBean<PageVo<OperationLogVo>> pageOperationLog(SelectOperationLogDto selectOperationLogDto) {
		var queryWrapper = new LambdaQueryWrapper<OperationLog>();
		queryWrapper.like(StrUtil.isNotBlank(selectOperationLogDto.getUsername()), OperationLog::getUsername, selectOperationLogDto.getUsername())
				.eq(StrUtil.isNotBlank(selectOperationLogDto.getStatus()), OperationLog::getStatus, selectOperationLogDto.getStatus())
				.eq(StrUtil.isNotBlank(selectOperationLogDto.getType()), OperationLog::getType, selectOperationLogDto.getType())
				.ge(ObjUtil.isNotEmpty(selectOperationLogDto.getStartTime()), OperationLog::getCreated, selectOperationLogDto.getStartTime())
				.le(ObjUtil.isNotEmpty(selectOperationLogDto.getEndTime()), OperationLog::getCreated, selectOperationLogDto.getEndTime());
		var page = operationLogMapper.selectPage(new Page<>(selectOperationLogDto.getCurrent(), selectOperationLogDto.getSize()), queryWrapper);
		var operationLogVoList = page.getRecords().stream().map(OperationLogVo::new).collect(Collectors.toList());
		return ResultBean.success(new PageVo<>(page.getPages(), page.getSize(), page.getTotal(), operationLogVoList));
	}

	@Override
	@Transactional(readOnly = true)
	public ResultBean<List<OperationLogVo>> listOperationLog() {
		return ResultBean.success(operationLogMapper.selectList(null).stream().map(OperationLogVo::new).collect(Collectors.toList()));
	}
}
