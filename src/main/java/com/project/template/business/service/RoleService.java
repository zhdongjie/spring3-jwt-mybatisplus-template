package com.project.template.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.template.common.domain.dto.DeleteBatchDto;
import com.project.template.common.domain.vo.PageVo;
import com.project.template.common.domain.vo.ResultBean;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.business.domain.dto.insert.InsertRoleDto;
import com.project.template.business.domain.dto.select.SelectRoleDto;
import com.project.template.business.domain.dto.update.UpdateRoleDto;
import com.project.template.business.domain.entity.Role;
import com.project.template.business.domain.vo.RoleVo;

import java.util.List;

public interface RoleService extends IService<Role> {
	ResultBean<ResponseEnum> insert(InsertRoleDto insertRoleDto);

	ResultBean<ResponseEnum> delete(DeleteBatchDto deleteBatchDto);

	ResultBean<ResponseEnum> updateRole(UpdateRoleDto updateRoleDto);

	ResultBean<PageVo<RoleVo>> pageRole(SelectRoleDto selectRoleDto);

	ResultBean<List<RoleVo>> listRole();
}
