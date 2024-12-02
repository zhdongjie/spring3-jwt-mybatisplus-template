package com.project.template.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.template.business.domain.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
	@Select("SELECT DISTINCT p.`id`, p.`name`, p.`code` FROM `sys_user_role` ur" +
			" INNER JOIN `sys_role` r ON ur.`role_id` = r.`id`" +
			" INNER JOIN `sys_role_permission` rp ON ur.`role_id` = rp.`role_id` " +
			" INNER JOIN `sys_permission` p ON p.`id` = rp.`permission_id`" +
			" WHERE" +
			" ur.`user_id` = #{userId}")
	List<Permission> selectListByUserId(@Param("userId") Long userId);

	@Select("SELECT p.`id`, p.`name`, p.`code` FROM `sys_role_permission` rp" +
			" INNER JOIN `sys_permission` p ON p.`id` = rp.`permission_id`" +
			" WHERE" +
			" rp.`role_id` = #{roleId}")
	List<Permission> selectListByRoleId(@Param("roleId") Long roleId);
}
