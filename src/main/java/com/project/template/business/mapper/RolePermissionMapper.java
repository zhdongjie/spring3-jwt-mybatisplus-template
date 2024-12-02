package com.project.template.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.template.business.domain.entity.RolePermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

	@Delete("delete from `sys_role_permission` where `role_id` = #{roleId}")
	void deleteByRoleId(@Param("roleId") Long roleId);
}