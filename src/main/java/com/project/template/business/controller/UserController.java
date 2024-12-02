package com.project.template.business.controller;

import com.project.template.common.aspect.annotation.Log;
import com.project.template.common.constants.LogConstant;
import com.project.template.common.domain.dto.DeleteBatchDto;
import com.project.template.common.domain.vo.PageVo;
import com.project.template.common.domain.vo.ResultBean;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.business.domain.dto.insert.InsertUserDto;
import com.project.template.business.domain.dto.select.SelectUserDto;
import com.project.template.business.domain.dto.update.EnableUserDto;
import com.project.template.business.domain.dto.update.UpdateUserDto;
import com.project.template.business.domain.vo.UserVo;
import com.project.template.business.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/user")
public class UserController {
	private final UserService userService;

	@PostMapping
	@Log(value = "新增用户", type = LogConstant.OperationTypeEnum.INSERT)
	public ResponseEntity<ResultBean<ResponseEnum>> insert(@RequestBody InsertUserDto insertUserDto) {
		return ResponseEntity.ok(userService.insert(insertUserDto));
	}

	@DeleteMapping
	@Log(value = "删除用户", type = LogConstant.OperationTypeEnum.DELETE)
	public ResponseEntity<ResultBean<ResponseEnum>> delete(DeleteBatchDto deleteBatchDto) {
		return ResponseEntity.ok(userService.delete(deleteBatchDto));
	}

	@PutMapping("changeStatus")
	@Log(value = "修改用户状态", type = LogConstant.OperationTypeEnum.UPDATE)
	public ResponseEntity<ResultBean<ResponseEnum>> changeStatus(@RequestBody EnableUserDto enableUserDto) {
		return ResponseEntity.ok(userService.changeStatus(enableUserDto));
	}

	@PutMapping("resetPassword/{id}")
	@Log(value = "重置密码", type = LogConstant.OperationTypeEnum.UPDATE)
	public ResponseEntity<ResultBean<ResponseEnum>> resetPassword(@PathVariable("id") Long id) {
		return ResponseEntity.ok(userService.resetPassword(id));
	}

	@PutMapping
	@Log(value = "修改用户信息", type = LogConstant.OperationTypeEnum.UPDATE)
	public ResponseEntity<ResultBean<ResponseEnum>> update(@RequestBody UpdateUserDto updateUserDto) {
		return ResponseEntity.ok(userService.updateUser(updateUserDto));
	}

	@GetMapping
	public ResponseEntity<ResultBean<PageVo<UserVo>>> page(SelectUserDto selectUserDto) {
		return ResponseEntity.ok(userService.pageUser(selectUserDto));
	}
}
