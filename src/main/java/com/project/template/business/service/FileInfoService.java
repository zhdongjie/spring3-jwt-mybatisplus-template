package com.project.template.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.template.common.domain.dto.DeleteBatchDto;
import com.project.template.common.domain.vo.ResultBean;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.business.domain.dto.insert.InsertFileInfoDto;
import com.project.template.business.domain.entity.FileInfo;
import com.project.template.business.domain.vo.FileInfoVo;

import java.util.List;

public interface FileInfoService extends IService<FileInfo> {
	ResultBean<FileInfoVo> upload(InsertFileInfoDto insertFileInfoDto);

	ResultBean<List<FileInfoVo>> uploadBatch(List<InsertFileInfoDto> insertFileInfoDtoList);

	ResultBean<ResponseEnum> deleteByBusinessId(Long businessId);

	ResultBean<ResponseEnum> delete(DeleteBatchDto deleteBatchDto);
}
