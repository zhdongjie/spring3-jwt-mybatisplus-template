package com.project.template.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.template.common.domain.dto.DeleteBatchDto;
import com.project.template.common.domain.vo.PageVo;
import com.project.template.common.domain.vo.ResultBean;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.business.domain.dto.insert.InsertPermissionDto;
import com.project.template.business.domain.dto.select.SelectPermissionDto;
import com.project.template.business.domain.dto.update.UpdatePermissionDto;
import com.project.template.business.domain.entity.Permission;
import com.project.template.business.domain.vo.PermissionVo;

import java.util.List;

public interface PermissionService extends IService<Permission> {
	ResultBean<ResponseEnum> insert(InsertPermissionDto insertPermissionDto);

	ResultBean<ResponseEnum> delete(DeleteBatchDto deleteBatchDto);

	ResultBean<ResponseEnum> updatePermission(UpdatePermissionDto updatePermissionDto);

	ResultBean<PageVo<PermissionVo>> pagePermission(SelectPermissionDto selectPermissionDto);

	ResultBean<List<PermissionVo>> listPermission();
}
