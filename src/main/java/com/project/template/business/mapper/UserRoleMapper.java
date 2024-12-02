package com.project.template.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.template.business.domain.entity.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
	@Delete("<script>"
			+ "delete from sys_user_role where user_id IN "
			+ "        <foreach collection='userIds' item='userId' separator=',' open='(' close=')'> "
			+ "            #{userId}"
			+ "        </foreach>"
			+ "</script>")
	void deleteByUserIdList(@Param("userIds") List<Long> userIds);

	@Delete("delete from `sys_user_role` where `user_id` = #{userId}")
	void deleteByUserId(@Param("userId") Long userId);
}