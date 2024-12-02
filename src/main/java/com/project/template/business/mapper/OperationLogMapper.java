package com.project.template.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.template.business.domain.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {
}
