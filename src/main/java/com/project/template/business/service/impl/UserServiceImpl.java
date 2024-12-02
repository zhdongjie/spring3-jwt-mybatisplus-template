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
import com.project.template.business.domain.dto.insert.InsertUserDto;
import com.project.template.business.domain.dto.select.SelectUserDto;
import com.project.template.business.domain.dto.update.EnableUserDto;
import com.project.template.business.domain.dto.update.UpdateMeDto;
import com.project.template.business.domain.dto.update.UpdateUserDto;
import com.project.template.business.domain.dto.update.UpdateUserPassword;
import com.project.template.business.domain.entity.User;
import com.project.template.business.domain.entity.UserRole;
import com.project.template.business.domain.vo.MeVo;
import com.project.template.business.domain.vo.PermissionVo;
import com.project.template.business.domain.vo.RoleVo;
import com.project.template.business.domain.vo.UserVo;
import com.project.template.business.mapper.PermissionMapper;
import com.project.template.business.mapper.RoleMapper;
import com.project.template.business.mapper.UserMapper;
import com.project.template.business.mapper.UserRoleMapper;
import com.project.template.business.service.UserService;
import com.project.template.configuration.properties.BaseProperties;
import com.project.template.common.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final BaseProperties baseProperties;
	private final UserRoleMapper userRoleMapper;
	private final RoleMapper roleMapper;
	private final PermissionMapper permissionMapper;

	@Override
	@Transactional(rollbackFor = BaseException.class)
	public ResultBean<ResponseEnum> insert(InsertUserDto insertUserDto) {
		var user = BeanUtil.copyProperties(insertUserDto, User.class);
		var queryWrapper = new LambdaQueryWrapper<User>();
		queryWrapper.eq(User::getUsername, user.getUsername());
		int hasUsername = userMapper.selectCount(queryWrapper);
		if (hasUsername != 0) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "用户名已存在");
		}
		user.setCreated(new Date());
		user.setPassword(passwordEncoder.encode(baseProperties.getDefaultPassword()));
		int insert = userMapper.insert(user);
		if (insert < 1) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "用户添加失败");
		}
		insertUserDto.getRoleIdList().forEach(roleId -> userRoleMapper.insert(new UserRole(user.getId(), roleId)));
		return ResultBean.success();
	}

	@Override
	@Transactional(rollbackFor = BaseException.class)
	public ResultBean<ResponseEnum> delete(DeleteBatchDto deleteBatchDto) {
		var deleteIdList = deleteBatchDto.getIds();
		try {
			if (CollectionUtils.isEmpty(deleteIdList)) {
				return ResultBean.result(ResponseEnum.INVALID_PARAMETER, "删除用户Id不能为空");
			}
			userMapper.deleteBatchIds(deleteIdList);
			userRoleMapper.deleteByUserIdList(deleteIdList);
		} catch (Exception e) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "删除用户失败");
		}
		return ResultBean.success();
	}

	@Override
	@Transactional(rollbackFor = BaseException.class)
	public ResultBean<ResponseEnum> changeStatus(EnableUserDto enableUserDto) {
		var user = BeanUtil.copyProperties(enableUserDto, User.class);
		var updateEnableStatus = userMapper.updateById(user);
		if (updateEnableStatus < 1) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "修改状态失败");
		}
		return ResultBean.success();
	}

	@Override
	@Transactional(rollbackFor = BaseException.class)
	public ResultBean<ResponseEnum> resetPassword(Long id) {
		var user = Optional.of(userMapper.selectById(id)).orElseThrow(() -> new BaseException(ResponseEnum.RECORD_NOT_FOUND, "用户不存在"));
		user.setPassword(passwordEncoder.encode(baseProperties.getDefaultPassword()));
		var resetPassword = userMapper.updateById(user);
		if (resetPassword < 1) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "重置密码失败");
		}
		return ResultBean.success();
	}

	@Override
	@Transactional(readOnly = true)
	public ResultBean<PageVo<UserVo>> pageUser(SelectUserDto selectUserDto) {
		var queryWrapper = new LambdaQueryWrapper<User>();
		queryWrapper.like(StrUtil.isNotBlank(selectUserDto.getRealName()), User::getRealName, selectUserDto.getRealName())
				.like(StrUtil.isNotBlank(selectUserDto.getPhoneNumber()), User::getPhoneNumber, selectUserDto.getPhoneNumber())
				.eq(StrUtil.isNotBlank(selectUserDto.getStatus()), User::getStatus, selectUserDto.getStatus());
		var page = userMapper.selectPage(new Page<>(selectUserDto.getCurrent(), selectUserDto.getSize()), queryWrapper);
		var userVoList = page.getRecords()
				.stream()
				.map(user -> {
					var roleList = roleMapper.selectListByUserId(user.getId())
							.stream()
							.map(RoleVo::new)
							.collect(Collectors.toList());
					return new UserVo(user, roleList);
				})
				.collect(Collectors.toList());
		return ResultBean.success(new PageVo<>(page.getPages(), page.getSize(), page.getTotal(), userVoList));
	}

	@Override
	@Transactional(readOnly = true)
	public ResultBean<MeVo> me() {
		var userId = AuthUtils.getUserId();
		var user = userMapper.selectById(userId);
		var meVo = BeanUtil.copyProperties(user, MeVo.class);
		var roleList = roleMapper.selectListByUserId(userId)
				.stream()
				.map(RoleVo::new)
				.collect(Collectors.toList());
		var permissionList = permissionMapper.selectListByUserId(userId)
				.stream()
				.map(PermissionVo::new)
				.collect(Collectors.toList());
		meVo.setRoleList(roleList);
		meVo.setPermissionList(permissionList);
		return ResultBean.success(meVo);
	}

	@Override
	@Transactional(rollbackFor = BaseException.class)
	public ResultBean<ResponseEnum> changePassword(UpdateUserPassword updateUserPassword) {
		var id = AuthUtils.getUserId();
		var user = userMapper.selectById(id);
		if (!passwordEncoder.matches(updateUserPassword.getOldPassword(), user.getPassword())) {
			throw new BaseException(ResponseEnum.RECORD_NOT_FOUND, "旧密码错误");
		}
		if (passwordEncoder.matches(updateUserPassword.getPassword(), user.getPassword())) {
			throw new BaseException(ResponseEnum.RECORD_NOT_FOUND, "新密码不能和旧密码相同");
		}
		var newPassword = passwordEncoder.encode(updateUserPassword.getPassword());
		user.setPassword(newPassword);
		var update = userMapper.updateById(user);
		if (update < 1) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "密码修改失败");
		}
		return ResultBean.success();
	}

	@Override
	@Transactional(rollbackFor = BaseException.class)
	public ResultBean<ResponseEnum> updateUser(UpdateUserDto updateUserDto) {
		var user = BeanUtil.copyProperties(updateUserDto, User.class);
		user.setUpdated(new Date());
		var update = userMapper.updateById(user);
		if (update < 1) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "修改用户信息失败");
		}
		userRoleMapper.deleteByUserId(user.getId());
		updateUserDto.getRoleIdList()
				.forEach(roleId -> userRoleMapper.insert(new UserRole(user.getId(), roleId)));
		return ResultBean.success();
	}

	@Override
	@Transactional(rollbackFor = BaseException.class)
	public ResultBean<ResponseEnum> updateMe(UpdateMeDto updateMeDto) {
		var user = BeanUtil.copyProperties(updateMeDto, User.class);
		user.setId(AuthUtils.getUserId());
		user.setUpdated(new Date());
		var update = userMapper.updateById(user);
		if (update < 1) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "修改用户信息失败");
		}
		return ResultBean.success();
	}
}
