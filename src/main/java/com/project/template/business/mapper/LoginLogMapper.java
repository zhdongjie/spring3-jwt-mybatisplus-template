package com.project.template.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.template.business.domain.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLog> {
}
