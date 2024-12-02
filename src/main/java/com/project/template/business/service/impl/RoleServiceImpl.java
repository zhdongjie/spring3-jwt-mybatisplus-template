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
import com.project.template.business.domain.dto.insert.InsertRoleDto;
import com.project.template.business.domain.dto.select.SelectRoleDto;
import com.project.template.business.domain.dto.update.UpdateRoleDto;
import com.project.template.business.domain.entity.Role;
import com.project.template.business.domain.entity.RolePermission;
import com.project.template.business.domain.entity.UserRole;
import com.project.template.business.domain.vo.PermissionVo;
import com.project.template.business.domain.vo.RoleVo;
import com.project.template.business.mapper.PermissionMapper;
import com.project.template.business.mapper.RoleMapper;
import com.project.template.business.mapper.RolePermissionMapper;
import com.project.template.business.mapper.UserRoleMapper;
import com.project.template.business.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
	private final RoleMapper roleMapper;
	private final RolePermissionMapper rolePermissionMapper;
	private final UserRoleMapper userRoleMapper;
	private final PermissionMapper permissionMapper;

	@Override
	@Transactional(rollbackFor = BaseException.class)
	public ResultBean<ResponseEnum> insert(InsertRoleDto insertRoleDto) {
		var role = BeanUtil.copyProperties(insertRoleDto, Role.class);
		var queryWrapper = new LambdaQueryWrapper<Role>();
		queryWrapper.eq(Role::getName, role.getName());
		int hasName = roleMapper.selectCount(queryWrapper);
		if (hasName != 0) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "角色名已存在");
		}
		int insert = roleMapper.insert(role);
		if (insert < 1) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "角色添加失败");
		}
		insertRoleDto.getPermissionIdList().forEach(permissionId -> rolePermissionMapper.insert(new RolePermission(role.getId(), permissionId)));
		return ResultBean.success();
	}

	@Override
	@Transactional(rollbackFor = BaseException.class)
	public ResultBean<ResponseEnum> delete(DeleteBatchDto deleteBatchDto) {
		var deleteIdList = deleteBatchDto.getIds();
		var roleNameList = new StringBuilder();
		try {
			if (CollectionUtils.isEmpty(deleteIdList)) {
				return ResultBean.result(ResponseEnum.INVALID_PARAMETER, "删除角色Id不能为空");
			}
			var queryWrapper = new LambdaQueryWrapper<UserRole>();
			AtomicInteger deleteCount = new AtomicInteger();
			deleteBatchDto.getIds()
					.forEach(id -> {
						queryWrapper.clear();
						queryWrapper.eq(UserRole::getRoleId, id);
						var count = userRoleMapper.selectCount(queryWrapper);
						if (count != 0) {
							var role = roleMapper.selectById(id);
							roleNameList.append(role.getName()).append(",");
						} else {
							deleteCount.addAndGet(roleMapper.deleteById(id));
							rolePermissionMapper.deleteByRoleId(id);
						}
					});
			if (deleteCount.get() == 0) {
				return ResultBean.result(ResponseEnum.PLEASE_TRY_AGAIN_LATER, roleNameList.substring(0, roleNameList.length() - 1) + " 已使用");
			}
			if (deleteCount.get() != deleteBatchDto.getIds().size()) {
				return ResultBean.result(ResponseEnum.OPERATING_SUCCESS, roleNameList.substring(0, roleNameList.length() - 1) + " 已使用");
			}
			return ResultBean.success();
		} catch (Exception e) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "删除角色失败");
		}
	}

	@Override
	@Transactional(rollbackFor = BaseException.class)
	public ResultBean<ResponseEnum> updateRole(UpdateRoleDto updateRoleDto) {
		var role = BeanUtil.copyProperties(updateRoleDto, Role.class);
		var update = roleMapper.updateById(role);
		if (update < 1) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "修改角色信息失败");
		}
		rolePermissionMapper.deleteByRoleId(updateRoleDto.getId());
		updateRoleDto.getPermissionIdList()
				.forEach(permissionId -> rolePermissionMapper.insert(new RolePermission(updateRoleDto.getId(), permissionId)));
		return ResultBean.success();
	}

	@Override
	@Transactional(readOnly = true)
	public ResultBean<PageVo<RoleVo>> pageRole(SelectRoleDto selectRoleDto) {
		var queryWrapper = new LambdaQueryWrapper<Role>();
		queryWrapper.like(StrUtil.isNotBlank(selectRoleDto.getName()), Role::getName, selectRoleDto.getName());
		var page = roleMapper.selectPage(new Page<>(selectRoleDto.getCurrent(), selectRoleDto.getSize()), queryWrapper);
		var roleVoList = page.getRecords()
				.stream()
				.map(role -> {
					var permissionList = permissionMapper.selectListByRoleId(role.getId())
							.stream()
							.map(PermissionVo::new)
							.collect(Collectors.toList());
					return new RoleVo(role, permissionList);
				})
				.collect(Collectors.toList());
		return ResultBean.success(new PageVo<>(page.getPages(), page.getSize(), page.getTotal(), roleVoList));
	}

	@Override
	@Transactional(readOnly = true)
	public ResultBean<List<RoleVo>> listRole() {
		return ResultBean.success(roleMapper.selectList(null).stream().map(RoleVo::new).collect(Collectors.toList()));
	}
}
