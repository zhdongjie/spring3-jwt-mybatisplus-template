package com.project.template.business.controller;

import com.project.template.common.aspect.annotation.Log;
import com.project.template.common.constants.LogConstant;
import com.project.template.common.domain.dto.DeleteBatchDto;
import com.project.template.common.domain.vo.PageVo;
import com.project.template.common.domain.vo.ResultBean;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.business.domain.dto.insert.InsertPermissionDto;
import com.project.template.business.domain.dto.select.SelectPermissionDto;
import com.project.template.business.domain.dto.update.UpdatePermissionDto;
import com.project.template.business.domain.vo.PermissionVo;
import com.project.template.business.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/permission")
public class PermissionController {
	private final PermissionService permissionService;

	@PostMapping
	@Log(value = "新增权限", type = LogConstant.OperationTypeEnum.INSERT)
	public ResponseEntity<ResultBean<ResponseEnum>> insert(@RequestBody InsertPermissionDto insertPermissionDto) {
		return ResponseEntity.ok(permissionService.insert(insertPermissionDto));
	}

	@DeleteMapping
	@Log(value = "删除权限", type = LogConstant.OperationTypeEnum.DELETE)
	public ResponseEntity<ResultBean<ResponseEnum>> delete(DeleteBatchDto deleteBatchDto) {
		return ResponseEntity.ok(permissionService.delete(deleteBatchDto));
	}

	@PutMapping
	@Log(value = "修改权限", type = LogConstant.OperationTypeEnum.UPDATE)
	public ResponseEntity<ResultBean<ResponseEnum>> update(@RequestBody UpdatePermissionDto updatePermissionDto) {
		return ResponseEntity.ok(permissionService.updatePermission(updatePermissionDto));
	}

	@GetMapping
	public ResponseEntity<ResultBean<PageVo<PermissionVo>>> page(SelectPermissionDto selectPermissionDto) {
		return ResponseEntity.ok(permissionService.pagePermission(selectPermissionDto));
	}

	@GetMapping("list")
	public ResponseEntity<ResultBean<List<PermissionVo>>> list() {
		return ResponseEntity.ok(permissionService.listPermission());
	}
}
