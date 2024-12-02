package com.project.template.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.common.domain.dto.DeleteBatchDto;
import com.project.template.common.domain.vo.PageVo;
import com.project.template.common.domain.vo.ResultBean;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.common.exception.BaseException;
import com.project.template.business.domain.dto.insert.InsertPermissionDto;
import com.project.template.business.domain.dto.select.SelectPermissionDto;
import com.project.template.business.domain.dto.update.UpdatePermissionDto;
import com.project.template.business.domain.entity.Permission;
import com.project.template.business.domain.entity.RolePermission;
import com.project.template.business.domain.vo.PermissionVo;
import com.project.template.business.mapper.PermissionMapper;
import com.project.template.business.mapper.RolePermissionMapper;
import com.project.template.business.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

	private final PermissionMapper permissionMapper;
	private final RolePermissionMapper rolePermissionMapper;

	@Override
	@Transactional(rollbackFor = BaseException.class)
	public ResultBean<ResponseEnum> insert(InsertPermissionDto insertPermissionDto) {
		var permission = BeanUtil.copyProperties(insertPermissionDto, Permission.class);
		var queryWrapper = new LambdaQueryWrapper<Permission>();
		queryWrapper.eq(Permission::getName, permission.getName());
		int hasName = permissionMapper.selectCount(queryWrapper);
		if (hasName != 0) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "权限名已存在");
		}
		int insert = permissionMapper.insert(permission);
		if (insert < 1) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "权限添加失败");
		}
		return ResultBean.success();
	}

	@Override
	@Transactional(rollbackFor = BaseException.class)
	public ResultBean<ResponseEnum> delete(DeleteBatchDto deleteBatchDto) {
		var deleteIdList = deleteBatchDto.getIds();
		var permissionNameList = new StringBuilder();
		try {
			if (CollectionUtils.isEmpty(deleteIdList)) {
				return ResultBean.result(ResponseEnum.INVALID_PARAMETER, "删除权限Id不能为空");
			}
			var queryWrapper = new LambdaQueryWrapper<RolePermission>();
			AtomicInteger deleteCount = new AtomicInteger();
			deleteBatchDto.getIds().forEach(id -> {
				queryWrapper.clear();
				queryWrapper.eq(RolePermission::getPermissionId, id);
				var count = rolePermissionMapper.selectCount(queryWrapper);
				if (count != 0) {
					var permission = permissionMapper.selectById(id);
					permissionNameList.append(permission.getName()).append(",");
				} else {
					deleteCount.addAndGet(permissionMapper.deleteById(id));
				}
			});
			if (deleteCount.get() == 0) {
				return ResultBean.result(ResponseEnum.PLEASE_TRY_AGAIN_LATER, permissionNameList.substring(0, permissionNameList.length() - 1) + " 已使用");
			}
			if (deleteCount.get() != deleteBatchDto.getIds().size()) {
				return ResultBean.result(ResponseEnum.OPERATING_SUCCESS, permissionNameList.substring(0, permissionNameList.length() - 1) + " 已使用");
			}
			return ResultBean.success();
		} catch (Exception e) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "删除权限失败");
		}
	}

	@Override
	@Transactional(rollbackFor = BaseException.class)
	public ResultBean<ResponseEnum> updatePermission(UpdatePermissionDto updatePermissionDto) {
		var permission = BeanUtil.copyProperties(updatePermissionDto, Permission.class);
		var update = permissionMapper.updateById(permission);
		if (update < 1) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "修改权限信息失败");
		}
		return ResultBean.success();
	}

	@Override
	@Transactional(readOnly = true)
	public ResultBean<PageVo<PermissionVo>> pagePermission(SelectPermissionDto selectPermissionDto) {
		var queryWrapper = new LambdaQueryWrapper<Permission>();
		queryWrapper.like(StrUtil.isNotBlank(selectPermissionDto.getName()), Permission::getName, selectPermissionDto.getName())
				.like(StrUtil.isNotBlank(selectPermissionDto.getCode()), Permission::getCode, selectPermissionDto.getCode());
		var page = permissionMapper.selectPage(new Page<>(selectPermissionDto.getCurrent(), selectPermissionDto.getSize()), queryWrapper);
		var permissionVoList = page.getRecords().stream().map(PermissionVo::new).collect(Collectors.toList());
		return ResultBean.success(new PageVo<>(page.getPages(), page.getSize(), page.getTotal(), permissionVoList));
	}

	@Override
	@Transactional(readOnly = true)
	public ResultBean<List<PermissionVo>> listPermission() {
		return ResultBean.success(permissionMapper.selectList(null).stream().map(PermissionVo::new).collect(Collectors.toList()));
	}
}
