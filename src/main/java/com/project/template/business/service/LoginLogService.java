package com.project.template.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.template.common.domain.dto.DeleteBatchDto;
import com.project.template.common.domain.vo.PageVo;
import com.project.template.common.domain.vo.ResultBean;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.business.domain.dto.insert.InsertLoginLogDto;
import com.project.template.business.domain.dto.select.SelectLoginLogDto;
import com.project.template.business.domain.entity.LoginLog;
import com.project.template.business.domain.vo.LoginLogVo;

import java.util.List;

public interface LoginLogService extends IService<LoginLog> {
	ResultBean<ResponseEnum> insert(InsertLoginLogDto insertLoginLogDto);

	ResultBean<ResponseEnum> delete(DeleteBatchDto deleteBatchDto);

	ResultBean<PageVo<LoginLogVo>> pageLoginLog(SelectLoginLogDto selectLoginLogDto);

	ResultBean<List<LoginLogVo>> listLoginLog();
}
