package com.project.template.business.controller;

import com.project.template.common.aspect.annotation.Log;
import com.project.template.common.constants.LogConstant;
import com.project.template.common.domain.dto.DeleteBatchDto;
import com.project.template.common.domain.vo.PageVo;
import com.project.template.common.domain.vo.ResultBean;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.business.domain.dto.insert.InsertRoleDto;
import com.project.template.business.domain.dto.select.SelectRoleDto;
import com.project.template.business.domain.dto.update.UpdateRoleDto;
import com.project.template.business.domain.vo.RoleVo;
import com.project.template.business.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/role")
public class RoleController {
	private final RoleService roleService;

	@PostMapping
	@Log(value = "新增角色", type = LogConstant.OperationTypeEnum.INSERT)
	public ResponseEntity<ResultBean<ResponseEnum>> insert(@RequestBody InsertRoleDto insertRoleDto) {
		return ResponseEntity.ok(roleService.insert(insertRoleDto));
	}

	@DeleteMapping
	@Log(value = "删除角色", type = LogConstant.OperationTypeEnum.DELETE)
	public ResponseEntity<ResultBean<ResponseEnum>> delete(DeleteBatchDto deleteBatchDto) {
		return ResponseEntity.ok(roleService.delete(deleteBatchDto));
	}

	@PutMapping
	@Log(value = "修改角色", type = LogConstant.OperationTypeEnum.UPDATE)
	public ResponseEntity<ResultBean<ResponseEnum>> update(@RequestBody UpdateRoleDto updateRoleDto) {
		return ResponseEntity.ok(roleService.updateRole(updateRoleDto));
	}

	@GetMapping
	public ResponseEntity<ResultBean<PageVo<RoleVo>>> page(SelectRoleDto selectRoleDto) {
		return ResponseEntity.ok(roleService.pageRole(selectRoleDto));
	}

	@GetMapping("list")
	public ResponseEntity<ResultBean<List<RoleVo>>> list() {
		return ResponseEntity.ok(roleService.listRole());
	}
}
