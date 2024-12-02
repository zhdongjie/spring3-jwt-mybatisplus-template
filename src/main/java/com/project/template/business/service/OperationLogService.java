package com.project.template.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.template.common.domain.dto.DeleteBatchDto;
import com.project.template.common.domain.vo.PageVo;
import com.project.template.common.domain.vo.ResultBean;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.business.domain.dto.insert.InsertOperationLogDto;
import com.project.template.business.domain.dto.select.SelectOperationLogDto;
import com.project.template.business.domain.entity.OperationLog;
import com.project.template.business.domain.vo.OperationLogVo;

import java.util.List;

public interface OperationLogService extends IService<OperationLog> {
	ResultBean<ResponseEnum> insert(InsertOperationLogDto insertOperationLogDto);

	ResultBean<ResponseEnum> delete(DeleteBatchDto deleteBatchDto);

	ResultBean<PageVo<OperationLogVo>> pageOperationLog(SelectOperationLogDto selectOperationLogDto);

	ResultBean<List<OperationLogVo>> listOperationLog();
}
