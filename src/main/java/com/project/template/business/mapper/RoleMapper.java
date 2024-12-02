package com.project.template.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.template.business.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

	@Select("select r.`id`, r.`name` from `sys_role` r inner join `sys_user_role` ur on r.`id` = ur.`role_id` and ur.`user_id` = #{userId}")
	List<Role> selectListByUserId(@Param("userId") Long userId);
}
